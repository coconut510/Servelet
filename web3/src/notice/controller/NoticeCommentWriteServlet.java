package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;

/**
 * Servlet implementation class NoticeCommentWriteServlet
 */
@WebServlet(name = "NoticeCommentWrite", urlPatterns = { "/noticeCommentWrite" })
public class NoticeCommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeCommentWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String userId = ((Member)session.getAttribute("user")).getUserId();
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String comment = request.getParameter("comment");
		
		NoticeComment nc = new NoticeComment();
		nc.setNoticeNo(noticeNo);
		nc.setContent(comment);
		nc.setUserId(userId);
	
		int result = new NoticeService().commentWrite(nc);
		if(result>0) // 성공했을때.
		{
			response.sendRedirect("/noticeSelect?noticeNo="+noticeNo);
		}
		else { // 실패했을때.
			response.sendRedirect("/views/notice/Error.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
