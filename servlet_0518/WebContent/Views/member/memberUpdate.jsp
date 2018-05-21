<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.*" 
    import="member.model.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
	<%
		//Member m =(Member)session.getAttribute("user");
		//String pass = request.getParameter("userPwd");
		//String addr = request.getParameter("userAddr");
		//m.setMemberPwd(pass);
		//m.setMemberAddr(addr);
		Member m = new Member();
		request.setCharacterEncoding("UTF-8");
		//System.out.println(request.getParameter("userId"));
		//System.out.println(request.getParameter("userPwd"));
		//System.out.println(request.getParameter("userAddr"));
		m.setMemberId(request.getParameter("userId"));
		m.setMemberPwd(request.getParameter("userPwd"));
		m.setMemberAddr(request.getParameter("userAddr"));
		if(new MemberService().updateMember(m)){
			m = new MemberService().selectMember(request.getParameter("userId"),
					request.getParameter("userPwd"));
			session.setAttribute("user", m);
		%>
			<h2>회원정보가 변경되었습니다.</h2>
			<a href="memberIndex.html">로그인 페이지로</a>
		<% }else{ %>
			<h2>회원정보 변경에 실패했습니다.</h2>
			<a href="myInfo.jsp">로그인 페이지로</a>
		<% }%>
		<% session.setAttribute("user",m); %>
	
</body>
</html>