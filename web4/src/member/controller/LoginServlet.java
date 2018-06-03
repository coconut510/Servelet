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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 전송값에 한글이 있을경우를 처리할 수 있도록 인코딩 처리
		// 2. View 에서 전송한 데이터를 받아 변수에 저장
		// 3. 비즈니스 로직 처리(Controller-> Service-> Dao -> DB 처리후 리턴)
		// 4. 처리 결과에 따라 성공/ 실패 페이지 리턴
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member m = new MemberService().selectOne(userId, userPwd);
		response.setContentType("text/html; charset=UTF-8");
		if(m!=null )
		{
			if(m.getActivation().equals("Y")) {
				
				boolean result = new MemberService().changePwdCheck(userId);
				HttpSession session = request.getSession();
				session.setAttribute("user", m);
				if(!result)  // 비밀번호 변경한지 90일이 안됐을때.
				{		
					
					RequestDispatcher view = request.getRequestDispatcher("/views/member/loginSuccess.jsp");
					request.setAttribute("member", m);
					view.forward(request, response);
				}
				else // 비밀번호 변경한지 90일 넘었을때.
				{
					response.sendRedirect("/views/member/passChange.jsp");
				}
			}
			else
			{
				response.sendRedirect("/views/member/loginVan.jsp");
			}
		}
		else
		{
			response.sendRedirect("/views/member/loginFail.jsp");
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
