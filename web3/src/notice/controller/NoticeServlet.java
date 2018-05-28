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
import notice.model.vo.Notice;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet(name = "Notice", urlPatterns = { "/notice" })
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 한글이 있을경우 인코딩
		request.setCharacterEncoding("utf-8");
		// 2. view 에서 넘겨준 값이 있을경우 받아서 변수에 저장
		String search = request.getParameter("search");
		if(search==null) search = "";
		// 3. 비즈니스 로직

		int currentPage;// 현재 페이지 값을 저장하는 변수
		
		if(request.getParameter("currentPage")==null) currentPage = 1;
		else currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		PageData pd = new NoticeService().noticeAll(currentPage,search);
		
		if(pd!=null)
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/notice.jsp");
			request.setAttribute("pageData", pd);
			view.forward(request, response);
		}
		else
		{
			response.sendRedirect("/views/notice/Error.html");
		}
		// 4. view 결과값 출력(jsp 페이지)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
