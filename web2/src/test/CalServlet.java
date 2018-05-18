package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalServlet
 */
@WebServlet(name = "CalServlet", urlPatterns = { "/calServlet" })
public class CalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 실습
				request.setCharacterEncoding("utf-8");
				String fir = (request.getParameter("firstValue"));
				String giho = (request.getParameter("giho"));
				String sec = (request.getParameter("secondValue"));
				String result = "";
				if(giho.equals("+")) result = (Integer.parseInt(fir) + Integer.parseInt(sec))+"";
				else if (giho.equals("-")) result = (Integer.parseInt(fir) - Integer.parseInt(sec))+"";
				else if (giho.equals("*")) result = (Integer.parseInt(fir) * Integer.parseInt(sec))+"";
				else result = (Double.parseDouble(fir) / Double.parseDouble(sec))+"";
			
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>");
				out.println("계산결과");
				out.println("</title>");
				out.println("</head>");
				out.println("<body>");
				out.println(fir + giho + sec +"="+ result);
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
