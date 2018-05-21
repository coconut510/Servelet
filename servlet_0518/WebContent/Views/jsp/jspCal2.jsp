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
			String fir = (request.getParameter("val1"));
			String yun = (request.getParameter("yun"));
			String sec = (request.getParameter("val2"));
			String result = "";
			if(yun.equals("+")) result = (Integer.parseInt(fir) + Integer.parseInt(sec))+"";
			else if (yun.equals("-")) result = (Integer.parseInt(fir) - Integer.parseInt(sec))+"";
			else if (yun.equals("*")) result = (Integer.parseInt(fir) * Integer.parseInt(sec))+"";
			else result = (Double.parseDouble(fir) / Double.parseDouble(sec))+"";
	%>
 	입력한 첫번째 값 : <%=fir %><br>
	입력한 연산자 : <%=yun %><br>
	입력한 두번째 값 : <%=sec %><br>
	결과<br>
	<%=fir %><%=yun %> <%=sec %>=<%=result %>
</body>
</html>