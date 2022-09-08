<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />
	<c:choose>
		<c:when test="${detail.usersId == principal.id}">
			<!-- put과 delete메서드를 사용해야아지만 자바스크립트를 배우지 않은 관계로
		 post를 사용한다. -->
			<div class="d-flex">
				<a class="btn btn-warning" href="/boards/${detail.id}/updateForm">수정하기</a>
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

