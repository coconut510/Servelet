package member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;

/**
 * Servlet implementation class EL_Test4Servlet
 */
@WebServlet(name = "EL_Test4", urlPatterns = { "/eL_Test4" })
public class EL_Test4Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EL_Test4Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Member> list = new ArrayList<Member>();
		list.add(new Member("홍길동", 20, "경기도"));
		list.add(new Member("김말똥", 30, "서울시"));
		list.add(new Member("고길동", 40, "인천시"));
		
//		RequestDispatcher view = request.getRequestDispatcher("/views/el/el_test4.jsp");
		RequestDispatcher view = request.getRequestDispatcher("/views/jstl/jstl_basic1.jsp");
		request.setAttribute("members", list);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
