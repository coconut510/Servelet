package member.model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	public MemberService() {}// 디폴트 생성자
	public Member selectMember(String userId, String userPwd)
	{
		Connection conn = null;
		// 커넥션 코드 작성 후에
		Member m = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","login_member","login_member");
			conn.setAutoCommit(false);
			
		
			m = new MemberDao().selectMember(conn, userId, userPwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return m;
	}
	
	public boolean updateMember(Member m)
	{
		Connection conn = null;
		boolean chk = false;
		System.out.println(" 연결");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","login_member","login_member");
			conn.setAutoCommit(false);
//			System.out.println(" 연결2 " + conn.getMetaData());
		
			chk = new MemberDao().updateMember(conn, m);
//			System.out.println("결과 : " + chk);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chk;
	}
	public boolean idCheck(String id )
	{
		boolean chk = false;
		Connection conn = null;
		System.out.println(" 연결");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","login_member","login_member");
			conn.setAutoCommit(false);
		
			chk = new MemberDao().idCheck(conn, id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return chk;
	}
}
