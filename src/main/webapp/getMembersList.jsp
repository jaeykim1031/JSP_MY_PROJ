<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import = "java.util.*" %>
<%@ page import = "member.MemberDTO" %>

<%
 List<MemberDTO> memberList = new ArrayList<>();
 try
 {
	 memberList = (List<MemberDTO>) session.getAttribute("memberList");
 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멤버 페이지 리스트</title>
</head>
<body>

<h1>list</h1>
<table border = "1" width = "700px">
<tr>
<th bgcolor="orange" width = "100px">아이디</th>
<th bgcolor="orange" width = "100px">비밀번호</th>
<th bgcolor="orange" width = "100px">전화번호</th>
<th bgcolor="orange" width = "100px">이메일</th>
<th bgcolor="orange" width = "100px">등록일</th>
<th bgcolor="orange" width = "100px">주소</th>
<th bgcolor="orange" width = "100px">권한</th>
</tr>
<% for(MemberDTO k : memberList) { %>

<tr>
<td><a href = "getMember.us?id=<%=k.getId()%>"><%= k.getId() %></a></td>
<td><%=k.getPassword() %></td>
<td><%=k.getPhone() %></td>
<td><%=k.getEmail() %></td>
<td><%=k.getRegdate() %></td>
<td><%=k.getAddr() %></td>
<td><%=k.getRole() %></td>
</tr>
<%
}
session.removeAttribute("memberList");
}

catch(Exception e)
{
	response.sendRedirect("getMemberList.us");
	e.printStackTrace();
}
%>
</table>

<br> <a href="http://localhost:8181/JSP_MY_PROJ">홈으로</a>
<p /> <a href= "insertMember.jsp"> 새 맴버 등록</a>
<p /> <a href= "deleteMember.jsp"> 맴버삭제</a>

</body>
</html>