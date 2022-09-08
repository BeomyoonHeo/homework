<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<!-- 사용자에게 입력을 받는 페이지 - Form -->
<!-- 원래 put 요청을 해야하지만 javascript를 배우지 않아 post로 사용 -->
<div class="container">
	<form action="/boards/${boards.id}/update" method="post">
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title" name="title"
				value="${boards.title}" required="required">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" name="content" required="required" maxlength="50">${boards.content}</textarea>
		</div>
		<button type="submit" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>

<%@ include file="../layout/footer.jsp"%>

