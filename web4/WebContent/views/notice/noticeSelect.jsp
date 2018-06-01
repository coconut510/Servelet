<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="notice.model.vo.*"
    import="member.model.vo.*"
    import="java.util.ArrayList"
    %>
    
 <%
 	Notice n = (Notice)request.getAttribute("notice");
 	Member m = null;
 	String id = "";
 	String pw = "";
 	if(session.getAttribute("user")!=null)
 	{
 		m = (Member)session.getAttribute("user");
		 id= m.getUserId();
		 pw = m.getUserPwd();
 	}
 	ArrayList<NoticeComment> ncList = (ArrayList<NoticeComment>)request.getAttribute("comment");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 사항 내용</title>
	
	<script>
		window.onload = function(){
		}	
		function back()
		{
			location.href="/notice";
		}
		function delConfirm()
		{
			if(m!=null)
			{
				var passChk = window.prompt("비밀번호를 한번더 입력해주세요.");
	            var originPass = "";
	            <% if(pw.length() > 0){ %>           
		            if(passChk== <%=pw %>)
		           	{
		            	var confirm = window.confirm("정말 삭제하시겠습니까?");
		            	if(confirm)
		           		{
		           			location.href="/noticeDelete?noticeNo="+<%=n.getNoticeNo()%>; 
		           		}
		            	else
		           		{
		           			history.go(-1);
		           		}
		           	}
		            else
		           	{
		           		alert("비밀번호가 틀렸습니다.");
		           	}
	            <% }%>
			}
		}
		function noticPopup()
		{
			alert("로그인을 먼저 진행해주세요");
			window.open("/views/member/login_popup.html","_blank","width=400px,height=200px");
		}
		function editComment(id)
		{
			document.getElementById("tempEditBtn"+id).style.display = "none";
			document.getElementById("editForm"+id).style.display="inline";
			document.getElementById("showComment"+id).style.display="none";
		}
		function cancle(id)
		{
			document.getElementById("tempEditBtn"+id).style.display = "inline";
			document.getElementById("editForm"+id).style.display="none";
			document.getElementById("showComment"+id).style.display="inline";
		}
		
	</script>
</head>
<body>
글번호 : <%=n.getNoticeNo() %><br>
글쓴이 : <%=n.getUserId() %><br>
작성일 : <%=n.getRegDate() %><br>
글제목 : <%=n.getSubject() %><br>
<textarea rows="20" cols="100" readonly style="resize:none"><%=n.getContents() %></textarea>
<br>
<button type="button" onclick="back()">목록</button>

<% if(id.equals("admin")){ %>
<form action="noticeUpdateReady" style="display:inline;">
	<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
	<input type="submit" value="수정">
</form>
<form action="/views/notice/noticeDel.jsp?noticeNo=<%=n.getNoticeNo() %>" style="display:inline;">
	<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
	<button type="button" onclick="delConfirm();">삭제 </button>
</form>
<form action="/views/notice//noticeWrite.jsp" style="display:inline;">
	<input type="submit" value="글쓰기">
</form>
<%} %>
<h1>댓글</h1>
<form action="/noticeCommentWrite" method="get">
<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
<% if(((Member)session.getAttribute("user"))==null){ %>
	<textarea rows="5" onclick="noticPopup();" cols="100" name="comment" placeholder="로그인 한 사용자만 댓글작성이 가능합니다." readonly  style="resize:none;" ></textarea>
<%}else{ %>
	<textarea rows="5" cols="100" name="comment" placeholder="댓글을 작성하세요" style="resize:none;"></textarea>
	<%} %>
	<br>
	<input type="submit" value="댓글작성" />
</form>
<%for(NoticeComment nc : ncList){ %>
	작성자 :<%=  nc.getUserId() %>/ 작성일:<%=nc.getRegDate() %><br>
	<% if(((Member)session.getAttribute("user"))!=null){
		if( ((Member)session.getAttribute("user")).getUserId().equals(nc.getUserId())){%>	
		<label id="showComment<%=nc.getCommentNo() %>"><%=nc.getContent() %></label>
		<button id="tempEditBtn<%=nc.getCommentNo() %>" onclick="editComment(<%=nc.getCommentNo() %>);">수정하기</button>
		<form action="/commentEdit" method="post" id="editForm<%=nc.getCommentNo()%>" style="display:none;">
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
			<input type="hidden" name="commentNo" value = "<%=nc.getCommentNo()%>"/>
			<input type="text" name="editCommentVal" id="editInput"/>
			<input type="submit" value="수정하기" />
			<button type="button" onclick="cancle(<%=nc.getCommentNo() %>);">취소</button>
		</form>
		<form action="/commentDelete" method="post" style="display:inline">
			<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo()%>"/>
			<input type="hidden" name="commentNo" value="<%=nc.getCommentNo()%>" />
			<input type="submit" value="삭제"/>
		</form>
	<%}
	}%>
	<br>
<%} %>

</body>
</html>