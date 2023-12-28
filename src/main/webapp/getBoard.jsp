<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="m_board.M_BoardDTO"%>

<%
	M_BoardDTO board = new M_BoardDTO();
	
	board = (M_BoardDTO) session.getAttribute("board");

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내용</title>
</head>
<body>
	<center>
		<h1> 글 내용 </h1>
		<hr><hr>
		<br><br>
		<form method="post" action = "updateBoard.do">
		
		
			<input type ="hidden" name = "id" value= "<%= board.getId() %>"> 
			<table border="1" width="700px" cellpadding="5px">
				<tr>
					<td bgcolor="lightblue" align="center">제목</td>
					<td><input type="text" name="m_title"
						value="<%=board.getM_title()%>"></td>
				</tr>

				<tr>
					<td bgcolor="lightblue">작성자</td>
					<td><input type="text" name="m_write"
						value="<%=board.getM_write()%>"></td>
				</tr>

				<tr>
					<td bgcolor="lightblue">내용</td>
					<td><textarea rows="10" cols="70"
							value="<%=board.getM_cont()%>"></textarea></td>
				</tr>

				<tr>
					<td bgcolor="lightblue">등록일</td>
					<td><%=board.getRegdate()%></td>
				</tr>

				<tr>
					<td bgcolor="lightblue">조회수</td>
					<td><%=board.getCnt()%></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="글 수정"></td>
					<td></td>
				</tr>
				
			</table>
		</form>
		<br> <br>
		<a href = "deleteBoard.do?seq=<%= board.getId() %>" > 
			글 삭제
		</a>
		
		<p /> <a href="index.jsp"> 홈으로 </a>
		<p /> <a href="getBoardList.jsp"> 글 목록 </a>
		<p /> <a = href="insertBoard.jsp"> 새 글 쓰기</a>
		
		</form>
		

</body>
</center>
</html>