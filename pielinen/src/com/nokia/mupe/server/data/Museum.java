package com.nokia.mupe.server.data;

import java.util.Properties;
import java.io.FileInputStream;
import java.sql.*;
import javax.sql.*;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Museum {
	
	private static final String dbPropertiesPath = "data/dbconnection.properties";	

	private static String dbHost;
	private static int dbPort;
	private static String dbUser;
	private static String dbPassword;
	private static String dbDatabase;
	
	private static Connection connection = null;

	private Museum() {};
	
	private static void loadProperties() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(dbPropertiesPath));
		}
		catch(java.io.FileNotFoundException e) {
			System.err.println(e);
		}
		catch(java.io.IOException e) {
			System.err.println(e);
		}
		Museum.dbHost = properties.getProperty("host");
		Museum.dbPort = Integer.parseInt(properties.getProperty("port"));
		Museum.dbUser = properties.getProperty("user");
		Museum.dbPassword = properties.getProperty("password");
		Museum.dbDatabase = properties.getProperty("database");
	}
	
	public static void initialize() {
		connect();
	}
	
	private static void connect() {
		/*
		try {
			Museum.connection = DriverManager.getConnection("jdbc:mysql://" +
			dbHost + ":" + Integer.toString(dbPort) + "/" +
			dbDatabase, dbUser, dbPassword);
		}
		catch(Exception e) {
			System.err.println("Couldn't connect to the database:");
			System.err.println(e);
		}
		*/
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("PIELINEN");
		dataSource.setPassword("PIELINEN");
		dataSource.setServerName("newt.homelinux.net");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("PIELINEN");
		try {
			Museum.connection = dataSource.getConnection();
		}
		catch(Exception e) {
			System.err.println("Couldn't connect!");
			System.err.println(e);
		}
	}
	
	public static Connection getConnection() {
		if(Museum.connection == null) {
			loadProperties();
			connect();
		}
		return Museum.connection;
	}
	
	public static IGroup getGroup(String id) {
		return Group.query(id);
	}
}
