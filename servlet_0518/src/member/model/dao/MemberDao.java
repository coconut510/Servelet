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

}
