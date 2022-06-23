import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ex02 {

	public static void main(String[] args) {
		Ex01 ex=new Ex01();
		Scanner sc= new Scanner(System.in);

		
		while(true) {
			System.out.print("1.일정입력 2.일정출력 3.일정삭제 4.일정수정 0.종료");
			int s=Integer.parseInt(sc.nextLine());
			if(s==1) {
				System.out.print("년");
				String year=sc.nextLine();
				System.out.print("월");
				String month=sc.nextLine();
				System.out.print("일");
				String day=sc.nextLine();
				System.out.print("일정내용=");
				String tittle=sc.nextLine();
				String query="insert into ex02 values(?,?,?,?)";
				PreparedStatement pstmt=ex.getPreparedStatement(query); // 함수 외우기
				try {
					pstmt.setString(1, year);
					pstmt.setString(2, month);
					pstmt.setString(3, day);
					pstmt.setString(4, tittle);
					pstmt.executeUpdate();
					pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}else if(s==2) {
					Statement stmt=ex.getStatement();
					try {
						
						ResultSet rs=stmt.executeQuery("select * from ex02");
						while(rs.next()) {
							System.out.println(rs.getString(1)+"년 "+rs.getString(2)+"월 "+rs.getString(3)+"일 "+rs.getString(4));
						}
						rs.close();
						stmt.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					}else if(s==3) {
						System.out.print("삭제 할 날짜=");
						int year=Integer.parseInt(sc.nextLine());
						int month=Integer.parseInt(sc.nextLine());
						int day=Integer.parseInt(sc.nextLine());
						
						String query="DELETE FROM ex02 WHERE year =?&& month =? && day=?";
						PreparedStatement pstmt=ex.getPreparedStatement(query);
						try {
							pstmt.setInt(1,year);
							pstmt.setInt(2,month);
							pstmt.setInt(3,day);
							pstmt.executeUpdate();
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
			}else if(s==4) {
				System.out.print("수정 할 날짜=");
				int year=Integer.parseInt(sc.nextLine());
				int month=Integer.parseInt(sc.nextLine());
				int day=Integer.parseInt(sc.nextLine());
				String tittle=sc.nextLine();
				
				
				String query="UPDATE ex02 SET year=?,month=?,day=? WHERE tittle=? year=?,month=?,day=?";
				PreparedStatement pstmt=ex.getPreparedStatement(query);
				try {
					pstmt.setInt(1,year);
					pstmt.setInt(2, month);
					pstmt.setInt(3, day);
					pstmt.setString(4, tittle);
					pstmt.setInt(5,year);
					pstmt.setInt(6, month);
					pstmt.setInt(7, day);
					pstmt.executeUpdate();
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}else if(s==0) {
					System.out.println("프로그램종료.");
					try {
						ex.getConn().close();
						
						System.exit(0);
					} catch (SQLException e) {
						e.printStackTrace();
						
				}
			}
		}
	}
}


