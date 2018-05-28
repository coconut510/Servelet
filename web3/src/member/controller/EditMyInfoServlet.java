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
		HttpSession session = request.getSession(false);
//		Member tempM = (Member)session.getAttribute("user");
//		System.out.println("세션 멤버 " + tempM.getUserName());
		Member m = new Member();
		String pwdRe = request.getParameter("userPwd_re");
		String pwd = request.getParameter("userPwd");
//		String name = request.getParameter("userName")!=null?request.getParameter("userName"):tempM.getUserName();
//		String email = request.getParameter("email")!=null?request.getParameter("email"):tempM.getEmail();
//		String phone = request.getParameter("phone")!=null?request.getParameter("phone"):tempM.getPhone();
//		String addr = request.getParameter("addr")!=null?request.getParameter("addr"):tempM.getAddress();
//		String hobby = request.getParameter("hobby")!=null?request.getParameter("hobby"):tempM.getHobby();
		if(pwdRe.equals(pwd)) 
		{
			m.setUserId(request.getParameter("userId"));
			m.setUserPwd(request.getParameter("userPwd"));
			m.setUserName(request.getParameter("userName"));
			m.setEmail(request.getParameter("email"));
			m.setPhone(request.getParameter("phone"));
			m.setAddress( request.getParameter("addr"));
			m.setHobby(request.getParameter("hobby"));
			
			int result = new MemberService().editMemberInfo(m);
			System.out.println("정보 수정 결과 " + result);
			if(result>0)
			{	
//				response.sendRedirect("/views/member/myInfo.jsp");
				RequestDispatcher view = request.getRequestDispatcher("/views/member/myInfo.jsp");
				request.setAttribute("user", m);
				view.forward(request, response);
//				session.setAttribute("user", m);
			}
			else
			{
				response.sendRedirect("/views/member/infoEditError.jsp");
			}		
		}
		else
		{
			response.sendRedirect("/views/member/infoEditErroPwd.jsp");
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
