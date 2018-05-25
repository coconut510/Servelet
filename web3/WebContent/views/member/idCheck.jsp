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
	function check()
	{
		var ckeckId = document.getElementById("checkId").value;	
		console.log("id " + checkId);
		if(ckeckId==""){	
			alert("아이디를 입력해주세요.");
			return;
		}
		else{
			location.href="idCheck.jsp?checkId="+ckeckId;
		}
	}
	window.onload = function(){
		<%
			String id = request.getParameter("checkId");
			if(id!=null) // checkId값이 null이 아닌 경우만 비즈니스 로직 처리.
			{
				boolean idCheck = new MemberService().idCheck(id);
				if(idCheck){%>
					document.getElementById("message").innerHTML="이미 존재하는 아이디 입니다." 
				<%}else {%>
				var useYesNo = window.confirm("사용가능한 ID 입니다. 사용하시겠습니까?");
				if(useYesNo){
					// 자신(팝업)을 호출한 부모의 userId에 ID값을 넣어줌
					opener.document.getElementById('userId').value='<%=id%>';
					
					window.close();// 해당 팝업창 종료.
				}
				else{
					document.getElementById("checkId").value = "";
					//document.getElementById("message").innerHTML="사용할수 있는 아이디 입니다.";
				}				
			<%}
			}
			%>
	}
</script>
</head>
<body>

중복 확인할 ID 입력 : <input type="text" id="checkId"/>
<button onclick="check();" >중복체크</button><br>
<span id="message" style="color:red;"></span>
</body>
</html>