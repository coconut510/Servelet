<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.vo.*"
    %>
    
  <%
  	Member m = (Member)session.getAttribute("user");
  	
  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
<script type="text/javascript">
	function callLogout(){
		var formT = document.getElementsByTagName('form');
        formT.action="logout";
        formT.submit();
	}
    function signOut()
    {
        var chk = window.confirm("정말로 탈퇴하시겠습니까?");
        if(chk){
            var deleteForm = document.getElementById("delete");
            deleteForm.style.display ="inline";
            console.log("버튼클릭");
        }
    }
</script>
</head>
<body>
<% if(m==null){ %>
	<fieldset style="width:300px; height: 100px;">
    <legend>로그인</legend>
    <form action="login" method="post" style="display:inline;" action="">
        ID : <input type="text" placeholder="ID를 입력하세요" name="userId"/><br>
        PW : <input type="password" placeholder="PW를 입력하세요" name="userPwd"/><br>
        <input type="submit" value="로그인"/>
    </form>
    <a href="/views/member/joinus.html">회원가입 </a>
    <a href="">ID 찾기 </a>
    <a href="">PW 찾기 </a>
</fieldset>

<%}else{ %>
	<h2>로그인 성공했습니다.</h2><br>
	[<%= m.getUserName()%>]님 환영합니다.<br>
	<a href="/views/member/myInfo.jsp">마이페이지</a><br>
	<a href="logout">로그아웃</a><br>
	<label onclick="signOut();" id="deleteBtn">회원탈퇴</label>
		<form action="deleteMember" method="post" style="display:none;" id="delete">
		<label style="color:red;">비밀번호 입력 :</label> 	
		<input type="password" name="userPwd"/>
			<input type="submit" value="확인"/>
		</form>
 
	<br>
	<a href="/notice">공지사항</a><br>
	<a href="/views/file/upload.html">업로드</a><br>
	<a href="/fileList"> 다운로드</a>
	
	<br>
	
	<a href="/views/file/upload2.html">업로드2</a><br>
	<a href="/fileList2">다운로드2</a>
<%
	if(m.getUserId().equals("admin"))
	{%>
		<a href="/allMember">전체회원조회</a><br>
	<%}
	
} %>
	

</body>
</html>