package member.model.dao;

import java.sql.*;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;

public class MemberDao {

	public Member selectOne(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where userid=? and userpwd=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setGender(rset.getString("gender"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setActivation(rset.getString("activation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public ArrayList<Member> selectList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next())
			{
				Member m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setGender(rset.getString("gender"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setActivation(rset.getString("activation"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return list;
	}

	public int memberActiveChange(Connection conn, String userId, String active) {
	
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set activation=? where userid=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, active);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
//			System.out.println("바뀐 결과 " + result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int joinMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values (?,?,?,?,?,?,?,?,?,sysdate,?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getGender());
			pstmt.setString(9, m.getHobby());
//			pstmt.setString(10, "sysdate");
			pstmt.setString(10, "Y");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public boolean idCheck(Connection conn, String id) {
		Statement stmt = null;
		ResultSet rset = null;
		boolean result = false;
		try {
			stmt = conn.createStatement();
			
			String query = "SELECT * FROM MEMBER WHERE userid = '" + id + "'";
			
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
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return result;
	}

	public int editMemberInfo(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set userpwd=?, username=?,  email=?, phone=?,address=?, hobby=?"
				+ "where userid=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getUserName());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getHobby());
			pstmt.setString(7, m.getUserId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
