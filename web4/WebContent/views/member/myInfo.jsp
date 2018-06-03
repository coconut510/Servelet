<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.vo.*"
    import="member.model.service.*"
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <script>
  	window.onload=function(){
	 <%
	 	Member m_session = (Member)session.getAttribute("user");
	 	System.out.println("정보 " +  m_session.getUserName() );
	 	
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
<c:set var="member" value="<%=m %>"/>
	<center>
	<form action="/editMyInfo" method="post">
            ID : <input type="text" value="${member.userId}" readonly name="userId" id="userId"/><br>
            PW : <input type="password" value="${member.userPwd}" name="userPwd"/><br>
            PW(re): <input type="password" value="${member.userPwd}" name="userPwd_re"/><br>
            Name : <input type="text" value="${member.userName}" name="userName"><br>
            Age : <input type="text" value="${member.age}" readonly name="age"><br>
            Email : <input type="email" value="${member.email}" name="email"><br>
            Phone : <input type="text" value="${member.phone}" name="phone"><br>
		            주소 : <input type="text" value="${member.address}" name="addr"><br>
		            Gender :    <input type="radio" name="gender" readonly value="M" id="M">남
		                        <input type="radio" name="gender" readonly value="F" id="F">여 <br>
		            취미 : <input type="text" value="${member.hobby}" name="hobby"><br>
            <input type="submit" value="수정하기"/>
            <button type="button" onclick="back();">취소</button>
        </form>
        <a href="/index.jsp">메인페이지로 이동하기</a>
        </center>
</body>
</html>