package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class EditMyInfoServlet
 */
@WebServlet(name = "EditMyInfo", urlPatterns = { "/editMyInfo" })
public class EditMyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditMyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Member m = new Member();
		m.setUserId(request.getParameter("userId"));
		m.setUserPwd(request.getParameter("userPwd"));
		m.setUserName(request.getParameter("userName"));
		m.setEmail(request.getParameter("email"));
		m.setPhone(request.getParameter("phone"));
		m.setAddress(request.getParameter("addr"));
		m.setHobby(request.getParameter("hobby"));
		
		int result = new MemberService().editMemberInfo(m);
		
		if(result>0)
		{
			HttpSession session = request.getSession(false);
			RequestDispatcher view = request.getRequestDispatcher("/views/member/myInfo.jsp");
			request.setAttribute("update", m);
			view.forward(request, response);
			session.setAttribute("user", m);
		}
		else
		{
			response.sendRedirect("/views/member/infoEditError.jsp");
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
