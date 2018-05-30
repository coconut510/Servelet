<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="notice.model.vo.*"
    %>
    
 <%
 	Notice n = (Notice)request.getAttribute("notice");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 사항 수정</title>
</head>
<body>

<form action="/noticeUpdate" method="post">
	글번호 : <%=n.getNoticeNo() %><br>
	글쓴이 : <%=n.getUserId() %><br>
	작성일 : <%=n.getRegDate() %><br>
	<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>" />
	글제목 : <input type="text" name="subject" size=89 value="<%=n.getSubject() %>" /><br>
	<textarea rows="20" cols="100" name="contents" style="resize:none"><%=n.getContents() %></textarea>
	<br>
	<input type="submit" value="수정하기">
</form>

</body>
</html>