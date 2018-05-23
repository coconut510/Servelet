package member.model.dao;

import java.sql.*;

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
				result = true;// 아이디가 있음.
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

	public boolean idCheck(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		try {
			String query = "INSERT INTO MEMBER VALUES(?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setInt(4, m.getMemberAge());
			pstmt.setString(5, m.getMemberAddr());
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				result = true;// 아이디 생성 성공적으로.
				conn.commit();				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean deleteUser(Connection conn, String id, String pass) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		try {
			String query = "DELETE FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PWD = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				result = true;// 아이디 삭제 성공적으로.
				conn.commit();				
			}
			else {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

}
