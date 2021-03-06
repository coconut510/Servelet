package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;
import notice.model.vo.NoticeComment;
import notice.model.vo.Notice;

public class NoticeDao {
	public ArrayList<Notice> getCurrnetPage(Connection conn, int currentPage, int recordCountPerPage, String search, String searchType) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
//		this.search = search;
		// 시작페이지 계산
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		// 끝 페이지 계산
		int end = currentPage * recordCountPerPage;
		String 	query = "select * from ("+
					"select notice.* , row_number() over(order by noticeno desc) as num from notice where "+ searchType+" like '%"+search+"%' ) "+
							"where num between ? and ?";
//			System.out.println(query);
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next()) 
			{
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
				list.add(n);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage, String search, String searchType) {
		// 게시물의 토탈 개수를 구해야함
		int recordTotalCount = 0;
		// 총 게시물 개수 저장 변수(정보가 없으므로 초기값은  0)
		String query = "select count(*) as total from notice"+
				 " where "+searchType +" like '%"+search+"%'";
//		this.search = search;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				recordTotalCount = rset.getInt("total");
//				System.out.println("찾은 전체 게시물 " + recordTotalCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		int pageTotalCount = 0;// navi 토탈 카운트
		// 페이지당 10 개씩 보이게 만들어서 navi list 를 만들려면?
		// 토탈 게시물이 124개라고 했을때 124%10을 한후 +1만큼
		// 만들어야함
		// 만약 나머지가 0으로 떨어진다면 +1을 하지 않음
		if(recordTotalCount%recordCountPerPage!=0) {
			pageTotalCount = recordTotalCount/recordCountPerPage +1;
		}
		else
		{
			pageTotalCount = recordTotalCount/recordCountPerPage;
		}		
//		System.out.println("전체 페이지 " + pageTotalCount);
		// 사용자가 현재 페이지가 1 페이지 인데 더 아래 페이지를 요청할 경우
		// 혹은 마지막 페이지가 120 페이지 인데 다음 페이지를
		// 요청할 경우에 대한 에러 방지 설정
		// 1보다 아래를 요청하면 1로 셋팅
		// 마지막보다 다음 페이지를 요청하면 마지막 페이지 값으로 셋팅
		if(currentPage<1) {
			currentPage = 1;
		}else if(currentPage>pageTotalCount) {
			currentPage =pageTotalCount;
		}
		// 현재 페이지를 기점으로 시작 navi와 끝 navi를 만들어야함.
		// 현재 페이지가 1이라면? 1~5
		// 현재 페이지가 3이라면? 1~5
		// 현재 페이지가 7이라면? 6~10
		// 시작 페이지 구하는 공식
		// -> ((현재페이지-1)/리스트개수) * 리스트개수 + 1
		// 1페이지 > ((1-1)/5) * 5 +1 = 1;
		// 7 페이지 > ((7-1)/5) * 5 + 1 = 6;
		// 12 페이지 > ((12-1/5) * 5 + 1 = 11;
		
		int startNavi = ((currentPage-1)/naviCountPerPage) * naviCountPerPage + 1;
		
		// 끝 NAVI 구하는 공식(쉬움)
		// 시작 navi + 보여줄 navi 개수 - 1;
		// 만약 시작 페이지가 1이라면 끝은
		// 1+ 5 -1 = 5;  1~5
		int endNavi = startNavi + naviCountPerPage -1;
		
		// 끝 navi를 구할때 주의 해야 할점
		// 토탈 navi가 122라고 할떄!(현재 페이지는 121이라고 할떄)
		// 시작 navi =>  (121-1)/ 5 * 5+1 = 121
		// 끝 navi = > 121 + 5 -1 = 125;
		// 이렇기 때문에 마지막 navi 부분은 아래 코드를 추가 해야함
		
		if(endNavi>pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		// 페이지 네비에서 사용할 '<' 모양과 '> 모양을 사용하기 위해
		// 필요한 변수 2개 생성(시작과 끝은 필요 없으므로)
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi==1) {
			needPrev = false;
		}
		if(endNavi==pageTotalCount) {
			needNext = false;
		}
		
		// 여기까지 기본적이 구조는 끝남
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {// 시작이 1페이지가 아니라면!
			sb.append("<a href='/notice?currentPage="+(startNavi-1)+ "&search="+search+"&searchType="+ searchType+"'> < </a>");
		}
		
		for(int i = startNavi;i<=endNavi;i++)
		{
			if(i==currentPage) {
				sb.append("<a href='/notice?currentPage="+i+"&search="+search+"&searchType="+ searchType+"'><B> "+i+" </B></a>");
			}
			else {
				sb.append("<a href='/notice?currentPage="+i+"&search="+search+"&searchType="+ searchType+"'> "+i+" </a>");
			}
		}
		
		if(needNext) {// 끝페이지가 아니라면
			sb.append("<a href='/notice?currentPage="+(endNavi+1)+ "&search="+search+"&searchType="+ searchType+"'> > </a>");
		
		}
//		System.out.println("검색어 " + search);
		return sb.toString();
	}
	public Notice noticeSelect(Connection conn, int noticeNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from notice where noticeno=?";
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return n;	
	}
	public int noticeUpdate(Connection conn, int noticeNo, String subject, String contents) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update notice set subject=?, contents=? where noticeno=?";
		Notice n = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setInt(3, noticeNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int noticeWrite(Connection conn, Notice n) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into notice values (SEQ_NOTICE.NEXTVAL,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, n.getSubject());
			pstmt.setString(2, n.getContents());
			pstmt.setString(3, n.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int noticeDelete(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from notice where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public ArrayList<NoticeComment> noticeComment(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from noticecomment where noticeno=?";
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next())
			{
				NoticeComment nc = new NoticeComment();
				nc.setCommentNo(rset.getInt("commentno"));
				nc.setNoticeNo(rset.getInt("noticeno"));
				nc.setContent(rset.getString("content"));
				nc.setUserId(rset.getString("userid"));
				nc.setRegDate(rset.getDate("regdate"));
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	public int commentWrite(Connection conn, NoticeComment nc) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "insert into noticecomment values (SEQ_NOTICECOMMENT.NEXTVAL,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,nc.getNoticeNo());
			pstmt.setString(2, nc.getContent());
			pstmt.setString(3, nc.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;		
	}
	
	public int commentEdit(Connection conn, int commentNo, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "update noticecomment set content=? where COMMENTNO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,comment);
			pstmt.setInt(2,commentNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	public int commentDelete(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "delete from noticecomment where commentno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,commentNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}


}
