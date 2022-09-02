<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/write/${id}" method="post">
		<div class="mb-3 mt-3">
			<input type="text" class="form-control" placeholder="Enter title"
				name="title" value="${updateDto.title}">
		</div>
		<div class="mb-3">
			<textarea class="form-control" rows="8" name="content">${updateDto.content}</textarea>
		</div>
		<div style="position: absolute; display: inline-block; right: 50%">
			<button type="submit" class="btn btn-primary">수정완료</button>
		</div>
	</form>
	<div style="margin-top: -10px">
		<form action="/boards/delete/${id}" method="get">
			<button type="submit" class="btn btn-primary">삭제하기</button>
		</form>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>

