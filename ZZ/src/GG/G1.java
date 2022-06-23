package GG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class G1 {

	private Connection conn; 
	private Statement stmt; 
	private PreparedStatement pstmt; 
	
	public G1() {
		String url="jdbc:mariadb://localhost:3306/java";
		String user="root";
		String pwd="1";System.out.println("°¨ÀÚ");
		try {
			Class.forName("org.mariadb.jdbc.Driver"); 
			conn=DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
	}public Connection getConn() {
		return conn;
	}
	public Statement getStatement() {
		try {
			stmt=conn.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
		
	}
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			pstmt=conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

}

