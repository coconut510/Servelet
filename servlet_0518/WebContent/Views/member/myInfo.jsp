<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		// 세션을 가지고 접속했다면 해당 세션값을 사용
		// 세션이 없는 상태로 접속했다면 null을 리턴
	    //System.out.println("세션 확인2 " + session.getAttribute("user"));
	    Member m = (Member)session.getAttribute("user");
		if(m != null)
		{
			//Member m = (Member)session.getAttribute("user");
		
	%>
	<script>
		function back()
		{
			history.go(-1);
		}
	</script>
		<center>
			나의정보 <br>
			<form action="memberUpdate.jsp" method="post">
				ID : <input type="text" name="userId" value=<%=m.getMemberId() %> readonly /> <br>
				PW : <input type="password" name="userPwd"
				value=<%=m.getMemberPwd() %> /> <br>
				PW_re: <input type="password" name="userPwd_re" value=<%=m.getMemberPwd() %> /> <br>
				Name : <input type="text" name="userName"
	         value=<%=m.getMemberName() %> readonly /> <br>
				Age : <input type="text" name="userAge" 
				 value=<%=m.getMemberAge() %> readonly /> <br>
				Addr : <input type="text" name="userAddr"
				value=<%=m.getMemberAddr() %> /> <br>
			
			<input type="submit" value="정보수정"/>
			
            </form>
            <button onclick="back();">돌아가기</button>
		</center>

	<% }else{ %>
		로그인 후에 이용해주세요!<br>
		<a href="memberIndex.html">로그인 페이지로</a>
	<%} %>
</body>
</html>