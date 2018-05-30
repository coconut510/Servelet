<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"   
    import="member.model.vo.*" 
  %>
 <%
 	Member m = (Member)session.getAttribute("user");
 	String userId = m.getUserName();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script>
		window.onload = function(){
			if(opener!=null)// 자신이 팝업창 일때.
			{
				opener.location.reload();
				window.close();
			}
		}
	</script>
</head>
<body>
	<h2>로그인 성공했습니다.</h2><br>
	[<%= userId%>]님 환영합니다.<br>
	<a href="/index.jsp">메인페이지로 돌아가기</a>
</body>
</html>