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
				<tr>
					<td>${board.id}</td>
					<td><a href="/boards/${board.id}">${board.title}</a></td>
					<td>${board.usersName}</td>
					<td style="text-align: right">
					<form action="/boards/update/${board.id}" method="post">
					<button type="submit" class="btn btn-primary">수정하기
					</button>
					</form>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<%@ include file="../layout/footer.jsp"%>

