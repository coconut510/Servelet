<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% 
		int a = 999;// _jspService 메소드의 지역변수 
	%>
	<%!
		int data = 999; // 해당 jsp 클래스의 전역변수 (멤버변수))
	%>
	<%
		System.out.println(data);
	%>
	<%! 
		public void dataPrint()
		{
			System.out.println(data);
		}
	%>	
</body>
</html>