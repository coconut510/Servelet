package file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.model.service.FileService;
import file.model.vo.DataFile2;

/**
 * Servlet implementation class FileRemoveServlet
 */
@WebServlet(name = "FileRemove", urlPatterns = { "/fileRemove" })
public class FileRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRemoveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 파일 삭제시 진행해야 되는 것 2가지
		// 1. DB에서 정보 삭제
		// 		- 비즈니스 로직 처리(DELETE)
		// 2. 실제 디렉토리 에서 실제 파일을 삭제
		//		- a. 경로를 DB에서 알아온 후 삭제
		// 		- b. 경로가 항상 같다면 해당되는 디렉토리를 정적으로 명시하여 삭제
		
		request.setCharacterEncoding("utf-8");
		String fileName = request.getParameter("fileName");
		Timestamp uploadTime = Timestamp.valueOf( request.getParameter("uploadTime"));
		DataFile2 df2 = new FileService().selectOne(fileName,uploadTime);
		int result = new FileService().deleteFile(fileName,uploadTime);
		if(result>0)
		{
			if(df2!=null) {
				File file = new File(df2.getFilePath());
				if(file.delete()) 
				{				
					response.sendRedirect("/fileList2");
				}
				else
				{
					System.out.println("로컬 삭제 실패");
				}
			}
			else
			{
				System.out.println("선택 실패");
			}	
		}
		else
		{
			System.out.println("DB 삭제 실패");
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
