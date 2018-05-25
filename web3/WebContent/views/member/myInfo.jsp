<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.vo.*"
    import="member.model.service.*"
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <script>
  	window.onload=function(){
	 <%
	 	Member m_session = (Member)session.getAttribute("user");
	 
	 	String userId = m_session.getUserId();
		 String userPwd = m_session.getUserPwd();	
		 Member m = new MemberService().selectOne(userId, userPwd);
		 
		 if(m.getGender().equals("M")){%>  
			document.getElementById("M").checked="checked";
	   <%}else{%>
	      document.getElementById("F").checked="checked"; 
	    <%}
	    
	    if(!m_session.getUserPwd().equals(m.getUserPwd()) ||
	    	!m_session.getUserName().equals(m.getUserName()) ||
	    	!m_session.getEmail().equals(m.getEmail()) ||
	    	!m_session.getPhone().equals(m.getPhone()) ||
	    	!m_session.getAddress().equals(m.getAddress()) ||
	    	!m_session.getHobby().equals(m.getHobby()) ){%> 
	    	alert("회원정보가 수정됐습니다.");
	    	
	    <%}%>
  	}
  </script>
</head>
<body>
	<center>
	<form action="/editMyInfo" method="post">
            ID : <input type="text" value="<%=m.getUserId() %>" readonly name="userId" id="userId"/><br>
            PW : <input type="password" value="<%= m.getUserPwd()%>" name="userPwd"/><br>
            PW(re): <input type="password" value="<%=m.getUserPwd() %>" name="userPwdRe"/><br>
            Name : <input type="text" value="<%=m.getUserName()%>" name="userName"><br>
            Age : <input type="text" value="<%=m.getAge() %>" readonly name="age"><br>
            Email : <input type="email" value="<%=m.getEmail() %>" name="email"><br>
            Phone : <input type="text" value="<%=m.getPhone() %>" name="phone"><br>
		            주소 : <input type="text" value="<%=m.getAddress() %>" name="addr"><br>
		            Gender :    <input type="radio" name="gender" readonly value="M" id="M">남
		                        <input type="radio" name="gender" readonly value="F" id="F">여 <br>
		            취미 : <input type="text" value="<%=m.getHobby() %>" name="hobby"><br>
            <input type="submit" value="수정하기"/>
            <button type="button" onclick="back();">취소</button>
        </form>
        <a href="/index.jsp">메인페이지로 이동하기</a>
        </center>
</body>
</html>