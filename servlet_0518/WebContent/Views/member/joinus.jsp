<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="member.model.service.*" import = "member.model.vo.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<script>	
		window.onload =function() {
			<%
			Member m = new Member();
			request.setCharacterEncoding("UTF-8");
			m.setMemberId(request.getParameter("userId"));
			m.setMemberPwd(request.getParameter("userPwd"));
			m.setMemberName(request.getParameter("userName"));
			m.setMemberAge(Integer.parseInt(request.getParameter("userAge")));
			m.setMemberAddr(request.getParameter("userAddr"));
			
			boolean result = new MemberService().insertMember(m);
			if(result){%>
					alert("회원가입이 완료되었습니다.");
					location.href="memberIndex.html";
				<%}else{%>
					alert("회원가입에 실패했습니다.");
					history.go(-1);
				<%}
			%>
		};
		</script>
</head>
<body>
</body>
</html>