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
    function back()
    {
        history.go(-1);
    }
    </script>
</head>
<body>
	<%
		Member m = (Member)session.getAttribute("user");
		String id = m.getMemberId();
		String pass = m.getMemberPwd();
		%>
			<h2><%=id %> 회원 탈퇴하려면 비밀번호를 입력해주세요.</h2>
			 비밀번호 입력 : <input type="password" id="pwd">
			<button onclick="confirmDel();">확인</button>
			<script>			
				function confirmDel(){
					if(document.getElementById("pwd").value=="<%=pass%>")
					{
						var confirmChk = window.confirm("정말로 탈퇴하시겠습니까?");
						
						if(confirmChk){
							<%
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
						else
						{
							alert("회원 탈퇴를 취소했습니다.");
							history.go(-1);
						}
					}
					else
					{
						alert("비밀번호가 틀렸습니다.");
					}
				}
			</script>

    <!--  <h2 id="curId">mslove 계정을 삭제하려면, 회원정보를 다시 입력해주세요.</h2>
    <form action="userDelConfirm.jsp" method="post">
	        아이디 입력 : <input type="text" name="id" />
	        비밀번호 입력 : <input type="password" name="pwd">
        <input type="submit" value="삭제하기"/>
        <button type="button" onclick="back();">취소</button>
    </form>
    -->
</body>
</html>