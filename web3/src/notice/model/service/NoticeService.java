package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	private NoticeDao ndao = new NoticeDao();
	public PageData noticeAll(int currentPage, String search, String searchType) {
		Connection conn = JDBCTemplate.conn();
		// Service에서는 2가지 값을 정해야함
		//1. page당 보이는 리스트의 개수(게시물의 개수)
		int recordCountPerPage = 10;
		
		//2. 현재 위치를 중심으로 시작 navi에서 부터 끝 navi개수
		int naviCountPerPage = 5;
		
		// SErvice에서는 DAO에 2가지 요청을 진행 해야함
		// 1. 현재 페이지 리스트
		ArrayList<Notice> noticeList = ndao.getCurrnetPage(conn, currentPage, recordCountPerPage,search,searchType);
		
//		System.out.println("게시판 길이 " + noticeList.size());
		// 2. 현재 중심으로 만들어지는 navi 리스트
		String pageNavi = ndao.getPageNavi(conn, currentPage, recordCountPerPage,naviCountPerPage,search,searchType);
//		System.out.println("네비 내용 " + pageNavi);
		PageData pd = null;
		if(!noticeList.isEmpty() && !pageNavi.isEmpty())
		{
			pd = new PageData();
			pd.setNoticeList(noticeList);
			pd.setPageNavi(pageNavi);
		}
		
		JDBCTemplate.close(conn);
		
		return pd;
	}
	public Notice noticeSelect(int noticeNo) {
		Connection conn = JDBCTemplate.conn();
		Notice notice = ndao.noticeSelect(conn, noticeNo);
		JDBCTemplate.close(conn);
		return notice;
	}
	public int noticeUpdate(int noticeNo, String subject, String contents) {
		Connection conn = JDBCTemplate.conn();
		int result = ndao.noticeUpdate(conn, noticeNo, subject, contents);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	public int noticeWrite(Notice n) {
		Connection conn = JDBCTemplate.conn();
		int result = ndao.noticeWrite(conn, n);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}
	public int noticeDelete(int noticeNo) {
		Connection conn = JDBCTemplate.conn();
		int result = ndao.noticeDelete(conn, noticeNo);
		if(result>0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		return result;
	}

}
