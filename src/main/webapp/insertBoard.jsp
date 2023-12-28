<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 쓰기</title>
</head>
<body>
	<center>

		<h1>글 쓰기</h1>
		<hr>
		<form method="post" action="insertBoard.do">
			<table border="1" cellpadding='10' cellspacing="5">
				<tr>
					<td bgcolor="lightgreen">제목</td>
					<td><input type="text" name="m_title"></td>
				</tr>
				<tr>
					<td bgcolor="lightgreen">글쓴이</td>
					<td><input type="text" name="m_write"></td>
				</tr>
				<tr>
					<td bgcolor="lightgreen">내용</td>
					<td><textarea name="m_cont" cols="50" rows="15"> </textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" ailgn="center"><input type="submit"
						value="새 글 등록"></td>
				</tr>


			</table>

		</form>

		<p />
		<a href="index.jsp"> 홈으로 </a>
		<p />
		<a href="getBoardList.jsp"> 글 목록 </a>
</body>
</center>

</html>