<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.vo.*" import="java.util.ArrayList"
    %>
    
   <%
   	ArrayList<Member> list = (ArrayList<Member>)request.getAttribute("memberList");
   %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 관리 페이지</title>
</head>
<body>
	<h1>관리자 : 회원관리 페이지 </h1>
	<table border="1">
	    <tr><th>아이디</th><th>이름</th><th>나이</th><th>이메일</th><th>연락처</th><th>주소</th><th>성별</th><th>취미</th><th>가입일자</th><th>활성화</th></tr>
	    <% for(Member m : list) {%>
	    	<tr>
	    	<td><%=m.getUserId() %></td>
	    	<td><%=m.getUserName() %></td>
	    	<td><%=m.getAge() %></td>
	    	<td><%=m.getEmail() %></td>
	    	<td><%=m.getPhone() %></td>
	    	<td><%=m.getAddress() %></td>
	    	<%if(m.getGender().equals("M")){ %>
	    		<td>남자</td>
	    	<% }else{ %>
	    		<td>여자</td>
	    	<%} %>
	    	<td><%=m.getHobby() %></td>
	    	<td><%=m.getEnrollDate() %></td>
	    	<td>
	    		<form action="memberActivation" method="post">
	    			<input type="hidden" value="<%=m.getUserId() %>" name="userId"/>
	    			<input type="hidden" value="<%=m.getActivation() %>" name="active"/>
	    			<input type="submit" value="<%=m.getActivation() %>" style="width:100%;"/>
	    		</form>
	    	</td>
	    	</tr>
	    <%} %>
	</table>
	<script>
		function back()
		{
			location.href="/index.jsp";
		}
	</script>
	<button onclick="back();">뒤로가기 </button>
</body>
</html>