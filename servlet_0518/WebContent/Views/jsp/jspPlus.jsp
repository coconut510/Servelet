<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int fir = Integer.parseInt(request.getParameter("firstValue")); %>
<% int sec = Integer.parseInt(request.getParameter("secondValue")); %>

<%=fir%>+<%=sec%>=<%=fir+sec%>

</body>
</html>