package dao;

public class DBConnection {

	private static Connection connection;

	private Connection getNewConnection() {
		Properties connProperties = this.getConnProperties();
		try {
			System.out.printf("Nova conexão com banco %s. \n", connProperties.get("url"));
			Connection conn = DriverManager.getConnection(connProperties.getProperty("url"), this.getConnProperties());
			if (!conn.isClosed()) {
				System.out.println("Conexão com o banco de dados realizada com sucesso.");
				return conn;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.err.printf("Não foi possível realizar conexão com o banco de dados %s.\n", connProperties.get("url"));
		return null;
	}

	public synchronized Connection getConnection() {
		if (connection == null) {
			connection = this.getNewConnection();
		}
		return connection;
	}

	private Properties getConnProperties() {
		Properties prop = new Properties();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("database.properties");
			prop.load(stream);
		} catch (IOException ex) {
			System.err.println("Não foi possível realizar conexão com o banco de dados.");
			ex.printStackTrace();
		}
		return prop;
	}

}
