package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WelcomRadio
 */
@WebServlet("/welcomRadio")
public class WelcomRadio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomRadio() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gender = (request.getParameter("gender"));
		String email = (request.getParameter("email"));
		String hi = (request.getParameter("welcomMent"));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("관광지 선택 결과");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>");
		out.println("클라이언트의 입력 정보");
		out.println("</h3>");
		out.println("<hr>");
		out.println("<h3>");
		out.println("성별 : " + gender);
		out.println("</h3><br>");
		out.println("<h3>");
		out.println("메인 수신 여부 : " + email);
		out.println("</h3><br>");
		out.println("<h3>");
		out.println("가입 인사 : " + hi);
		out.println("</h3>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
