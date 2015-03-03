package com.excilys.cdb.controler.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ConnectionManager implements IConnectionManager {

	instance;
	protected BoneCP connectionPool;

	private final static String PROPERTY_CONFIG = "config.properties";
	private final static String PROPERTY_CHAINE_DB = "chaineConnect";
	private final static String PROPERTY_USER_NAME = "adminDB";
	private final static String PROPERTY_USER_PASSW = "passwDB";
	private final static String PROPERTY_DRIVER_DB = "driver";

	private final String chaineConnect;
	private final String adminDB;
	private final String passwDB;
	private final String driver;
	

	
	private ConnectionManager() {
		// Chemin du fichier contenant les informations de connexions
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		try {
			classLoader.getResourceAsStream(PROPERTY_CONFIG);
			properties.load(classLoader.getResourceAsStream(PROPERTY_CONFIG));
		} catch (IOException e) {
			System.out.println("Unable to load config file.");
			throw new Error("pas de fichier de config ?");
		}
		
		chaineConnect = properties.getProperty(PROPERTY_CHAINE_DB);
		adminDB = properties.getProperty(PROPERTY_USER_NAME);
		passwDB = properties.getProperty(PROPERTY_USER_PASSW);
		driver = properties.getProperty(PROPERTY_DRIVER_DB);

		try {
			Class.forName(driver).newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new Error("impossible de se connecter");
		}
		
		try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(chaineConnect);
            config.setUsername(adminDB);
            config.setPassword(passwDB);
            config.setMinConnectionsPerPartition( 5 );
            config.setMaxConnectionsPerPartition( 10 );
            config.setPartitionCount( 2 );
            connectionPool = new BoneCP( config );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
	}

	@Override
	public Connection getConnection() {
        try {
			return connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Error("impossible de se connecter");
		}
    }

	@Override
	public void closeAll() throws Exception {
		try {
			connectionPool.close();
			connectionPool = null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error("impossible de fermer la pool");
		}
	}

}
