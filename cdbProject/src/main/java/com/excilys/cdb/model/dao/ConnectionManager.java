package com.excilys.cdb.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static ConnectionManager instance;
	private Connection connection;

	private final static String PROPERTY_CONFIG = "config.properties";
	private final static String PROPERTY_CHAINE_DB = "chaineConnect";
	private final static String PROPERTY_USER_NAME = "adminDB";
	private final static String PROPERTY_USER_PASSW = "passwDB";
	private final static String PROPERTY_DRIVER_DB = "driver";

	private final String chaineConnect;
	private final String adminDB;
	private final String passwDB;
	private final String driver;

	public static ConnectionManager getInstance() {
		if (ConnectionManager.instance == null) {
			ConnectionManager.instance = new ConnectionManager();
		}

		return ConnectionManager.instance;
	}

	/**
	 * Constructeur en private pour faire de cette classe un singleton
	 */
	private ConnectionManager() {
		// Chemin du fichier contenant les informations de connexions
		// String configPath = PROPERTY_CONFIG;

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		Properties properties = new Properties();
		try {
			// FileInputStream in = new FileInputStream(configPath);
			classLoader.getResourceAsStream(PROPERTY_CONFIG);
			properties.load(classLoader.getResourceAsStream(PROPERTY_CONFIG));
			// in.close();
		} catch (IOException e) {
			System.out.println("Unable to load config file.");
			throw new Error("pas de fichier de config ?");
		}
		// Chargement des propriétés de la connexion
		chaineConnect = properties.getProperty(PROPERTY_CHAINE_DB);
		adminDB = properties.getProperty(PROPERTY_USER_NAME);
		passwDB = properties.getProperty(PROPERTY_USER_PASSW);
		driver = properties.getProperty(PROPERTY_DRIVER_DB);

	}

	/**
	 * Méthode appelé pour créer la connexion
	 */
	public Connection openConnection() {

		try {
			if (connection != null) {
				closeConnection();
			}
			Class.forName(driver).newInstance();

			connection = DriverManager.getConnection(chaineConnect, adminDB,
					passwDB);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage() + " ! ");
			throw new Error("impossible de se connecter");
			// connection = null;
		}

		return connection;
	}

	public void closeConnection() throws SQLException {
		try {
			connection.close();
			connection = null;
		} catch (SQLException e) {
			throw e;
		}
	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
