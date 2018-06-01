package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;

import notice.model.service.NoticeService;import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet(name = "NoticeUpdate", urlPatterns = { "/noticeUpdate" })
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		int result = new NoticeService().noticeUpdate(noticeNo,subject, contents);
		if(result>0)// 공지 수정 성공.
		{
			Notice n = new NoticeService().noticeSelect(noticeNo);
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/noticeSelect.jsp");
			request.setAttribute("notice", n);
			request.setAttribute("noticeConfirm", "fin");
			view.forward(request, response);
		}
		else// 공지 수정 실패.
		{
			response.sendRedirect("/views/notice/noticeUpdateError.html");
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
