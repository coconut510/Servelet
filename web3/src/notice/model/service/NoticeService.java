package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	private NoticeDao ndao = new NoticeDao();
	public PageData noticeAll(int currentPage, String search) {
		Connection conn = JDBCTemplate.conn();
		// Service에서는 2가지 값을 정해야함
		//1. page당 보이는 리스트의 개수(게시물의 개수)
		int recordCountPerPage = 10;
		
		//2. 현재 위치를 중심으로 시작 navi에서 부터 끝 navi개수
		int naviCountPerPage = 5;
		
		// SErvice에서는 DAO에 2가지 요청을 진행 해야함
		// 1. 현재 페이지 리스트
		ArrayList<Notice> noticeList = ndao.getCurrnetPage(conn, currentPage, recordCountPerPage,search);
		
		System.out.println("게시판 길이 " + noticeList.size());
		// 2. 현재 중심으로 만들어지는 navi 리스트
		String pageNavi = ndao.getPageNavi(conn, currentPage, recordCountPerPage,naviCountPerPage,search);
		System.out.println("네비 내용 " + pageNavi);
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

}
