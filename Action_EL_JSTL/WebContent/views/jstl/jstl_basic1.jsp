<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="num" value="100"/> <!-- page 영역에 저장 -->
	<c:set var="num" value="200" scope="request"/> <!-- request영역에 저장 -->
	<c:set var="num" value="300" scope="session"/> <!-- session영역에 저장 -->
	값1 : ${pageScope.num}<br>
	값2 : ${requestScope.num}<br>
	값3 : ${sessionScope.num}<br>
</body>
</html>