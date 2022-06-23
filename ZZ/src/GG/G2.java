package GG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class G2 {

	public static void main(String[] args) {
		G1 ex=new G1();
		Scanner sc= new Scanner(System.in);

		
		while(true) {
			System.out.print("1.�����Է� 2.������� 3.�������� 4.�������� 0.����");
			int s=Integer.parseInt(sc.nextLine());
			if(s==1) {
				System.out.print("��");
				String year=sc.nextLine();
				System.out.print("��");
				String month=sc.nextLine();
				System.out.print("��");
				String day=sc.nextLine();
				System.out.print("�ð�");
				String TIME=sc.nextLine();
				System.out.print("����");
				String NAME=sc.nextLine();
				System.out.print("��������=");
				String title=sc.nextLine();
				String query="insert into po01 values(null,?,?,?,?,?,?)";
				PreparedStatement pstmt=ex.getPreparedStatement(query); // �Լ� �ܿ��
				try {
					pstmt.setString(1, year);
					pstmt.setString(2, month);
					pstmt.setString(3, day);
					pstmt.setString(4, TIME);
					pstmt.setString(5, NAME);				
					pstmt.setString(6, title);
					pstmt.executeUpdate();
					pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				}else if(s==2) {
					Statement stmt=ex.getStatement();
					try {
						
						ResultSet rs=stmt.executeQuery("select * from po01");
						while(rs.next()) {
							System.out.println(rs.getInt(1)+"."+ rs.getString(2)+"�� "+rs.getString(3)+"�� "
						+rs.getString(4)+"�� "+rs.getString(6)+" : "+rs.getString(7));
						}
						rs.close();
						stmt.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					}else if(s==3) {
						System.out.print("���� �� ����=");
						String NAME=sc.nextLine();
												
						String query="DELETE FROM po01 WHERE NAME=?";
						PreparedStatement pstmt=ex.getPreparedStatement(query);
						try {
							pstmt.setString(6,NAME);
							pstmt.executeUpdate();
							pstmt.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						
					 }else if(s==4) {
				            System.out.print("���� �� ��ȣ=");
				            int num=Integer.parseInt(sc.nextLine());
				            System.out.print("��");
				            int year=Integer.parseInt(sc.nextLine());
				            System.out.print("��");
				            int month=Integer.parseInt(sc.nextLine());
				            System.out.print("��");
				            int day=Integer.parseInt(sc.nextLine());
				            System.out.print("�ð�");
				            int time=Integer.parseInt(sc.nextLine());
				            System.out.print("����");
				            String NAME=sc.nextLine();
				            System.out.print("��������=");
				            String title=sc.nextLine();

				            
				            
				            String query="UPDATE po01 SET year=?, month=?, day=?, time=?, name=?, title=? WHERE num=?";
				            PreparedStatement pstmt=ex.getPreparedStatement(query);
				            try {
				               pstmt.setInt(1, year);
				               pstmt.setInt(2, month);
				               pstmt.setInt(3, day);
				               pstmt.setInt(4, time);
				               pstmt.setString(5, NAME);
				               pstmt.setString(6, title);
				               pstmt.setInt(7, num);
				               pstmt.executeUpdate();
				               pstmt.close();
				            } catch (SQLException e) {
				               e.printStackTrace();
				            }

				}else if(s==0) {
					System.out.println("���α׷�����.");
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
