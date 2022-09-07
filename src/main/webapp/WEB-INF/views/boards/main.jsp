<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
			<c:forEach var="boards" items="${boardsList}">
				<tr>
					<td>${boards.id}</td>
					<td><a href="/boards/${boards.id}">${boards.title}</a></td>
					<td>${boards.username}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!--	<div class="container mt-3">
		<h3>totalCount: ${pagingDto.totalCount}</h3>
		<h3>totalPage: ${pagingDto.totalPage}</h3>
		<h3>currentPage: ${pagingDto.currentPage}</h3>
		<h3>isLast: ${pagingDto.last}</h3>
		<h3>isFirst: ${pagingDto.first}</h3>  -->

</div>
<div class="d-flex justify-content-center">
	<ul class="pagination">
		<!--			<c:choose>
				<c:when test="${pagingDto.first}">
					<li class="page-item disabled"><a class="page-link"
						href="/boards?page=${pagingDto.currentPage-1}">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="/boards?page=${pagingDto.currentPage-1}">Previous</a></li>
				</c:otherwise>
			</c:choose> -->
			<li class='page-item ${pagingDto.first?"disabled":""}'><a class="page-link"
				href="/boards?page=${pagingDto.currentPage-1}">Previous</a></li>
			<c:forEach var="page" begin="${pagingDto.startPageNum}" end="${pagingDto.lastPageNum}">
				<li class='page-item ${pagingDto.currentPage == page-1 ? "active" : ""}'><a
					class="page-link" href="/boards?page=${page - 1}">${page}</a></li>
			</c:forEach>
			<li class='page-item ${pagingDto.last?"disabled":""}'><a class="page-link"
				href="/boards?page=${pagingDto.currentPage+1}">Next</a></li>
		<!-- 		<c:choose>
			<c:when test="${pagingDto.last}">
				<li class="page-item disabled"><a class="page-link"
					href="/boards/${pagingDto.currentPage+1}">Next</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="/boards?page=${pagingDto.currentPage+1}">Next</a></li>
			</c:otherwise>
		</c:choose> -->
	</ul>
	</div>

<%@ include file="../layout/footer.jsp"%>

