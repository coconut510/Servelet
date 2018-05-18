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
			String fir = (request.getParameter("firstValue"));
			String giho = (request.getParameter("giho"));
			String sec = (request.getParameter("secondValue"));
			String result = "";
			if(giho.equals("+")) result = (Integer.parseInt(fir) + Integer.parseInt(sec))+"";
			else if (giho.equals("-")) result = (Integer.parseInt(fir) - Integer.parseInt(sec))+"";
			else if (giho.equals("*")) result = (Integer.parseInt(fir) * Integer.parseInt(sec))+"";
			else result = (Double.parseDouble(fir) / Double.parseDouble(sec))+"";
		%>

		<%=fir %>  <%=giho %>  <%=sec %> = <%=result %>

</body>
</html>