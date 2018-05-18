package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TravelChk
 */
@WebServlet(description = "travelChk", urlPatterns = { "/travelChk" })
public class TravelChk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TravelChk() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실습
				request.setCharacterEncoding("utf-8");
				String[] travelArr = (request.getParameterValues("traver"));
				String result = "";

				for(int i = 0; i<travelArr.length-1;i++)
				{
					result += travelArr[i]+" ";
				}
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>");
				out.println("관광지 선택 결과");
				out.println("</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>");
				out.println("당신이 선택한 관광지 입니다.");
				out.println("</h2>");
				out.println("<hr>");
				out.println(result);
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
