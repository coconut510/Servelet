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
	
	<c:out value="${num}" />
	<br>
	<c:out value="${data}" default="없음" />
	<br>
	<%
		String desc1 = "<br> : br 태그는 줄개행을 진행함";
		String desc2 = "<label> : label 태그는 글자를 표현하고 감싸는 태그";
	%>
	<c:out value="<%= desc1%>" escapeXml="false" /><br>
	<c:out value="<%= desc2%>" escapeXml="true" /><br>
	<c:out value="<br>태그는 줄행입니다." escapeXml="false" />
	<br>
	<c:set var="num1" value="100"/>
	<c:set var="num2" value="200"/>
	<br>
	<c:if test="${num1>num2}" var="result">
		num1이 더 큽니다.<br>
	</c:if>
	
	<br>
	<c:set var="choise" value="3"/>
	
	<c:choose>
		<c:when test="$(choice==1)" >
			1을 선택하였습니다.<br>
		</c:when>
		<c:when test="$(choice==2)" >
			2를 선택하였습니다.<br>
		</c:when>
		<c:otherwise>
			그 외를 선택하였습니다.<br>
		</c:otherwise>
	</c:choose>

	결과 :${result }
	<br>
	<c:forEach begin="1" end="10" var="i">
		${i}<br>
	</c:forEach>
	
	
	<c:forEach begin="0" items="${list}" var="m" varStatus="i">
		${i.count }번째 학생
		이름 : ${m.name}
		나이 : ${m.age}
		주소 : ${m.addr}
		<br>
	</c:forEach>


	
</body>
</html>