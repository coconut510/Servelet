package member.model.dao;

import java.sql.*;
import java.sql.SQLException;

import member.model.vo.Member;

public class MemberDao {

	public Member selectMember(Connection conn, String userId, String userPwd) {
		
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + userId + "' and + MEMBER_PWD = '"+userPwd + "'" ;
			
			rset = stmt.executeQuery(query);
			
			if(rset.next())
			{
				m = new Member();
				m.setMemberId(rset.getString("MEMBER_ID"));
				m.setMemberPwd(rset.getString("MEMBER_PWD"));
				m.setMemberAge(rset.getInt("MEMBER_AGE"));
				m.setMemberName(rset.getString("MEMBER_NAME"));
				m.setMemberAddr(rset.getString("MEMBER_ADDR"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}

	public boolean updateMember(Connection conn,Member m) {
		Statement stmt = null;
		int result = 0;
		System.out.println("연결3");
		try {
			stmt = conn.createStatement();
			System.out.println("연결4");
			String query = "UPDATE MEMBER "
					+ "SET MEMBER_PWD='"+m.getMemberPwd()+"',"
							+ "MEMBER_ADDR='"+m.getMemberAddr()+"' "
					+ "WHERE MEMBER_ID = '" + m.getMemberId() + "'";
			
//			System.out.println("쿼리 " + query);
//			System.out.println(m.getMemberPwd());
//			System.out.println(m.getMemberAddr());
//			System.out.println( m.getMemberId() );
			result = stmt.executeUpdate(query);
			if(result>0)conn.commit();
//			System.out.println("결과 DAO : " + result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result>0;
	}

	public boolean idCheck(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		boolean result = false;
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + id + "'";
			
			rset = stmt.executeQuery(query);
			
			if(rset.next())
			{
				result = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
