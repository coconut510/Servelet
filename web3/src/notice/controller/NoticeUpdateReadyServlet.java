package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateReadyServlet
 */
@WebServlet(name = "NoticeUpdateReady", urlPatterns = { "/noticeUpdateReady" })
public class NoticeUpdateReadyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateReadyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		Notice n = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!=null && ((Member)session.getAttribute("user")).getUserId().equals("admin"))
		{
			n = new NoticeService().noticeSelect(noticeNo);
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeUpdateReady.jsp");
			request.setAttribute("notice", n);
			view.forward(request, response);
		}
		if(n!=null)
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeSelect.jsp");
			request.setAttribute("notice", n);
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
