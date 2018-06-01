package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import member.model.service.*;
/**
 * Servlet implementation class DeleteMemberServlet
 */
@WebServlet(name = "DeleteMember", urlPatterns = { "/deleteMember" })
public class DeleteMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		String pass = request.getParameter("userPwd");
		Member m = (Member)session.getAttribute("user");
		if(m.getUserPwd().equals(pass))
		{
			int result = new MemberService().deleteMember(m.getUserId(), pass);
			System.out.println("회원 탈퇴 결과" + result);
			if(result>0) {
				response.sendRedirect("/views/member/signOut.jsp");
				session.removeAttribute("user");
			}
			else
			{
				response.sendRedirect("/views/member/Error.html");
			}
		}
		else
		{
			session.setAttribute("passwordError", "true");
			response.sendRedirect("/index.jsp");
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
