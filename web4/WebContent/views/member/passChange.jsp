<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="member.model.vo.*"
    %>
  <%
	Member m = ((Member)session.getAttribute("user"));
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
    function back()
    {
        location.href="/index.jsp";    
    }
</script>
</head>
<body>
	<h1>패스워드를 변경하신지 90일이 지났습니다</h1>
	<h1>보안상 해킹을 당할 우려가 있으므로 변경을 권장합니다.</h1>
	
	<form action="/editMyInfo" method="post">	    
		<input type="hidden" name="userId" value="<%=m.getUserId() %>">
		<input type="hidden" name="userName" value="<%=m.getUserName() %>">
		<input type="hidden" name="email" value="<%=m.getEmail() %>">
		<input type="hidden" name="phone" value="<%=m.getPhone() %>">
		<input type="hidden" name="addr" value="<%=m.getAddress() %>">
		<input type="hidden" name="hobby" value="<%=m.getHobby() %>">
	    비밀번호 : <input type="password" name="userPwd"><br>
	    비밀번호 확인 : <input type="password" name="userPwd_re"><br>
	    <input type="submit" value="변경하기">
	    <button type="button" onclick="back();">나중에 변경하기</button>
	</form>
</body>
</html>