package notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.NoticeComment;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSelectServlet
 */
@WebServlet(name = "NoticeSelect", urlPatterns = { "/noticeSelect" })
public class NoticeSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		//3. 비즈니스 로직 공지사항 내용.
		Notice n = new NoticeService().noticeSelect(noticeNo);
		
		//3. 비즈니스 로직(댓글내용)
		ArrayList<NoticeComment> commentList = new NoticeService().noticeComment(noticeNo);
		if(n!=null)
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeSelect.jsp");
			request.setAttribute("notice", n);
			request.setAttribute("comment", commentList);
			view.forward(request, response);
		}
		else
		{
			response.sendRedirect("/views/notice/noticeError.html");
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
