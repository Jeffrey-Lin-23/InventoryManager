package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	private String dbUrl = "jdbc:mysql://localhost:3306/IMS";
	private String dbUserName = "root";
	private String dbPassword = "Adsl3310";
	private String jdbcDriverName = "com.mysql.jdbc.Driver";
	
	public Connection getConnection()throws Exception{
		Class.forName(jdbcDriverName);
		Connection con=DriverManager.getConnection(dbUrl, dbUserName,dbPassword);
		return con;		
	}
	public void closeCon(Connection con)throws Exception{
		if(con!=null) {}
		con.close();
	}


	public static void main (String[] args) {
		DBUtil dbUtil = new DBUtil();
		try {
			dbUtil.getConnection();
			System.out.println("Success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Fail");
		}
	}
}