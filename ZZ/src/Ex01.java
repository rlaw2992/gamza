import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class Ex01 {

	private Connection conn; 
	private Statement stmt; 
	private PreparedStatement pstmt; 
	
	public Ex01() {
		String url="jdbc:mariadb://localhost:3306/java";
		String user="root";
		String pwd="1";
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
	public void selectList1() {
		String query="select * from ex02";
		Statement smt;
		try {
			smt = conn.createStatement();
			ResultSet rs=smt.executeQuery(query);
			while(rs.next()) {
				System.out.println(rs.getString(1)+"년 "+rs.getString(2)+"월 "+rs.getString(3)+"일 "+rs.getString(4));
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
				e.printStackTrace(); // 출력문

		}
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

