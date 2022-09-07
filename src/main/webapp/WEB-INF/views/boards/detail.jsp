<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />
	<c:choose>
		<c:when test="${detail.usersId == principal.id}">
		<div class="d-flex">
			<form action="/boards/write/${detail.id}">
				<button class="btn btn-warning">수정하기</button>
			</form>
			<form action="/boards/${detail.id}/delete" method="post">
				<button class="btn btn-danger">삭제</button>
			</form>
		</div>
		</c:when>
	</c:choose>



	<div>
		<h3>${detail.title}</h3>
	</div>
	<hr />
	<div>${detail.content}</div>

</div>

<%@ include file="../layout/footer.jsp"%>

