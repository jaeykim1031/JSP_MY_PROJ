<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="m_board.M_BoardDTO"%>

<%
List<M_BoardDTO> boardList = new ArrayList<>();

try {
	boardList = (List<M_BoardDTO>) session.getAttribute("boardList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<center>
		<h1>목록</h1>
		<hr>
		<table border="1" width="1000px">
			<tr>
				<th bgcolor="lightyellow" width="100px">글 번호</th>
				<th bgcolor="lightyellow" width="200px">제목</th>
				<th bgcolor="lightyellow" width="150px">글쓴이</th>
				<th bgcolor="lightyellow" width="150px">등록일</th>
				<th bgcolor="lightyellow" width="100px">조회수</th>
			</tr>
			<%
			for (M_BoardDTO k : boardList) {
			%>

			<tr>
				<td align="center"><%=k.getId()%></td>
				<td><a href="getBoard.do?id=<%=k.getId()%>"> <%=k.getM_title()%></a></td>
				<td><%=k.getM_write()%></td>
				<td><%=k.getRegdate()%></td>
				<td><%=k.getCnt()%></td>

			</tr>
			<%
			}

			session.removeAttribute("boardList");

			} catch (Exception e) {
			 response.sendRedirect("getBoardList.do");
				e.printStackTrace(); 
			}
			%>
		</table>
		<br> <a href="index.jsp"> 홈으로 </a> <br> <a
			href="insertBoard.jsp"> 새 글 쓰기 </a>
		<p /> <a href= "deleteBoard.jsp"> 글 삭제</a>
			
</body>
</center>
</html>