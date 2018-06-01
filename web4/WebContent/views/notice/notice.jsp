<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.model.vo.*" import="java.util.*"
    import="member.model.vo.*"
    %>
    
   <%
   		PageData pd = (PageData)request.getAttribute("pageData");
   		ArrayList<Notice> noticeList = pd.getNoticeList();// 현재 페이지 리스트
   		String pageNavi = pd.getPageNavi();  // 네비 리스트.
   		String id ="";
   		if(session.getAttribute("user")!=null)
   		{
   			id= ((Member)session.getAttribute("user")).getUserId();
   		}
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<h1>공지사항</h1>
		<table border="1">
				<tr><th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th></tr>
			<%for(Notice n :noticeList){ %>
				<tr>
					<td><%=n.getNoticeNo() %></td>
					<td><a href="/noticeSelect?noticeNo=<%=n.getNoticeNo()%>"><%=n.getSubject() %></a></td>
					<td><%=n.getUserId() %></td>
					<td><%=n.getRegDate() %></td>
				</tr>
			<%} %>
		</table>
		<label><%=pageNavi %></label><br>
		<form action="notice" method="get" style="display:inline;">
			제목<input type="radio" name="searchType" value="subject" checked="checked"/>
			작성자<input type="radio" name="searchType" value="userid"/>
			<input type="hidden" name="currentPage" value="1">
			<input type="text" name="search" value="<%=request.getAttribute("search")%>"/>
			<input type="submit" value="검색"/>
		</form>
		<%if(id.equals("admin")){ %>
		<form action="/views/notice/noticeWrite.jsp" style="display:inline;">
			<input type="submit" value="글쓰기">
		</form>
		<%} %>
	</center>
</body>
</html>