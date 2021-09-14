package controllers;

import dao.DBConnection;

public abstract class Controller {

	protected DBConnection dbConnection;
	protected Properties sqls;

	private Properties getQueries() {
		Properties prop = new Properties();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("sql.properties");
			prop.load(stream);
		} catch (IOException ex) {
			System.err.println("Não foi possível ler o arquivo de SQLs.");
			ex.printStackTrace();
		}
		return prop;
	}

	public Controller() {
		this.dbConnection = new DBConnection();
		this.sqls = this.getQueries();
	}

}
