<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.service.*" import="member.model.vo.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
	window.onload = function(){
		<%
		String id = request.getParameter("id");
		String pass = request.getParameter("pwd");
		
		boolean result = new MemberService().deleteUser(id,pass);
		if(result){
			session.removeAttribute("user");
			%>
			alert("회원탈퇴에 성공했습니다.");
			location.href="memberIndex.html";
		<%}else{%>
			alert("회원탈퇴에 실패했습니다. 아이디와 비밀번호를 확인해주세요.");
			history.go(-1);
		<%}	%>
	}
</script>
</head>
<body>
	
</body>
</html>