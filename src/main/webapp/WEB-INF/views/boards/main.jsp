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
		    <c:forEach var="boards" items="${boardsList}">
			<tr>
				<td>${boards.id}</td>
				<td><a href="/boards/${boards.id}">${boards.title}</a></td>
				<td>${boards.username}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
<div class="container mt-3">
	<h2>${pagingDto.currentPage + 1}</h2>
  <ul class="pagination">
  <c:choose>
  <c:when test="${pagingDto.isFirst}">
  <li class="page-item disabled"><a class="page-link" href="/?page=${pagingDto.currentPage - 1}">Previous</a></li>
  </c:when>
  <c:otherwise>
  <li class="page-item"><a class="page-link" href="/?page=${pagingDto.currentPage - 1}">Previous</a></li>
  </c:otherwise>
  </c:choose>
  <c:forEach var="item" begin="0" end="${pagingDto.totalPage - 1}">
    <li class="page-item"><a class="page-link" href="/?page=${item}">${item + 1}</a></li>
  </c:forEach>
    <c:choose>
    <c:when test="${pagingDto.isLast}">
    <li class="page-item disabled"><a class="page-link" href="/?page=${pagingDto.currentPage + 1}">Next</a></li>
    </c:when>
    <c:otherwise>
    <li class="page-item"><a class="page-link" href="/?page=${pagingDto.currentPage + 1}">Next</a></li>
    </c:otherwise>
    </c:choose>
    
  </ul>
</div>
</div>

<%@ include file="../layout/footer.jsp"%>

