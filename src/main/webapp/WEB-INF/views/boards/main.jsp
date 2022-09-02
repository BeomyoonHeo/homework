<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>번호</th>
				<th>게시글제목</th>
				<th>작성자이름</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards}">
				<tr class="table-${boardcolors.boardColors(board.id mod 6)}">
					<td>${board.id}</td>
					<td><a href="/boards/${board.id}">${board.title}</a></td>
					<td>${board.usersName}</td>
			</c:forEach>
			</tr>
		</tbody>
	</table>
	<div style="text-align: right">
		<c:choose>
			<c:when test="${empty principal}">

			</c:when>
			<c:otherwise>
			<form action="/boards/update" method="get"><button type="submit" class="btn btn-primary">내 글 수정하기</button></form>
			</c:otherwise>
		</c:choose>
	</div>
</div>


<%@ include file="../layout/footer.jsp"%>

