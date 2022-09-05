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
	<h2>${param.page + 0}</h2>
  <ul class="pagination">
  <c:choose>
  <c:when test="${param.page == 0 || param.page == null}">
  <li class="page-item disabled"><a class="page-link" href="/?page=2">Previous</a></li>
  </c:when>
  <c:otherwise>
  <li class="page-item"><a class="page-link" href="/?page=${param.page - 1}">Previous</a></li>
  </c:otherwise>
  </c:choose>
    <li class="page-item"><a class="page-link" href="#">1</a></li>
    <li class="page-item"><a class="page-link" href="#">2</a></li>
    <li class="page-item"><a class="page-link" href="#">3</a></li>
    <c:choose>
    <c:when test="">
    <li class="page-item"><a class="page-link" href="/?page=${param.page + 1}">Next</a></li>
    </c:when>
    </c:choose>
    
  </ul>
</div>
</div>

<%@ include file="../layout/footer.jsp"%>

