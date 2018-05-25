package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name = "MemberJoin", urlPatterns = { "/memberJoin" })
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		String name = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String addr = request.getParameter("addr");
		String gender = request.getParameter("gender");
		String hobby = request.getParameter("hobby");
		
		
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		m.setUserName(name);
		m.setAge(age);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(addr);
		m.setGender(gender);
		m.setHobby(hobby);
		
		int result = new MemberService().joinMember(m);
		if(result>0)
		{
			response.sendRedirect("/views/member/joinSuccess.jsp");
		}
		else
		{
			response.sendRedirect("/views/member/joinFail.jsp");
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
