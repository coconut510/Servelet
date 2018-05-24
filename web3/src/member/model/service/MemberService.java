package member.model.service;

import java.sql.*;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	private MemberDao mdao = new MemberDao();
	public Member selectOne(String userId, String userPwd)
	{
		Connection conn= JDBCTemplate.conn();
		Member m = mdao.selectOne(conn, userId, userPwd);
		JDBCTemplate.close(conn);
		return m;
	}
	public ArrayList<Member> selectList() {
		
		Connection conn = JDBCTemplate.conn();
		ArrayList<Member> list = mdao.selectList(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	public int memberActiveChange(String userId, String active) {
		Connection conn = JDBCTemplate.conn();
		int result = mdao.memberActiveChange(conn, userId, active);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
}
