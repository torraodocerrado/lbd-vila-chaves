package controllers;

import models.Catchphrase;
import models.Persona;

public class PersonaController extends Controller {

	public boolean insert(Persona persona) {
		try {
			String sql = this.sqls.getProperty("insert_persona");
			PreparedStatement pstmt = this.dbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, persona.getName());
			pstmt.setString(2, persona.getGender());
			pstmt.setInt(3, persona.getAge());
			int insertedPersona = pstmt.executeUpdate();
			int insertedPhrase = 0;
			pstmt.close();

			if (insertedPersona > 0) {
				sql = this.sqls.getProperty("insert_catchphrase");
				for (Catchphrase catchphrase : persona.getCatchphrases()) {
					pstmt = this.dbConnection.getConnection().prepareStatement(sql);
					pstmt.setString(1, persona.getName());
					pstmt.setString(2, catchphrase.getPhrase());
					pstmt.setString(3, catchphrase.getMood());
					insertedPhrase = insertedPhrase + pstmt.executeUpdate();
					pstmt.close();
				}
				System.out.printf("Personsa salva com sucesso. %s personagem inserido e %s bordões.\n", insertedPersona,
						insertedPhrase);
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(Persona persona) {
		try {
			String sql = this.sqls.getProperty("delete_persona");
			PreparedStatement pstmt = this.dbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, persona.getName());
			int deletedPersona = pstmt.executeUpdate();
			pstmt.close();
			if (deletedPersona > 0) {
				System.out.printf("Personsa apagada com sucesso: %s personagem deletado.\n", deletedPersona);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Persona find_by_name(String name) {
		try {
			String sql = this.sqls.getProperty("find_by_name_persona");
			PreparedStatement pstmt = this.dbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, name);

			ResultSet rs = pstmt.executeQuery();
			Persona persona = null;
			while (rs.next()) {
				persona = new Persona(rs.getString("name"), rs.getString("gender"), rs.getInt("age"));
			}
			pstmt.close();

			return this.find_catchphrases(persona);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Persona find_catchphrases(Persona persona) {
		if (persona != null) {
			try {
				String sql = this.sqls.getProperty("find_by_name_catchphrase");

				PreparedStatement pstmt = this.dbConnection.getConnection().prepareStatement(sql);
				pstmt.setString(1, persona.getName());
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					persona.addCatchphrases(rs.getString("phrase"), rs.getString("mood"));
				}
				pstmt.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return persona;
	}

}
