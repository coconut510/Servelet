<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.service.*" import="member.model.vo.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPw");
		Member m = new MemberService().selectMember(userId,userPwd);
	%>
	<%
		if(m!=null)
		{
			session = request.getSession(true);
			// 해당 클라이언트가 세션값을 가지고 접속하지 않으면 새로 발급
			// 첫 로그인시에는 세션값이 없기 때문에 무조건 새롭게 발급해야 함
			// 세션값이 없으면 새롭게 발급하는 것은 인자값으로 true를 주면 됨
			session.setAttribute("user",m);
			
		}
		else{
			
		}
	%>
	<%
		if(m!=null){ %>
				<%= "<h2>["+m.getMemberName() + "]님 환영합니다.</h2>" %> <br>
				<a href="myInfo.jsp">마이페이지</a>
				 <a href="logout.jsp">로그아웃</a> 
				 <a href="memberDel.jsp">회원탈퇴</a>	
		<% }else{ %>
			로그인에 실패하였습니다<br>
			ID 또는 PW를 확인해주세요<br>
			<a href="memberIndex.html"> 로그인 페이지로 이동</a>
		<% }%>
</body>
</html>