package mywebapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mywebapp.dao.model.impl.PetDaoJdbcImpl;
import mywebapp.dao.model.impl.PetDaoJpaImpl;
import mywebapp.dao.model.interfaces.PetDao;

public class MySqlDaoFactory {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/zoo";

	private final static String USER = "root";
	private final static String PASSWORD = "Jianqiao(3)";
	
	public static Connection createConnection() {
		Connection conn = null;
		try {
			System.out.println("Registering Driver.......");
			Class.forName(JDBC_DRIVER).newInstance();

			// Open a connection:
			System.out.println("Openning conncetion.......");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public PetDao getPetDao() {
		return new PetDaoJdbcImpl();
	}
	
	
	
	
}
