<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
		margin: 0;
		padding: 0;
	}
	
	.container {
		margin-top: 5%;
		text-align: center;
	}
	
	.main {
		height: 600px;
		width: 1200px;
		margin: 0 auto;
	}
	
	.table {
		width: 100%;
		border-top: 1px solid black;
		border-bottom: 1px solid black;
		border-collapse: collapse;
	}
	
	.table th, td {
		border-top: 1px solid black;
		border-bottom: 1px solid black;
	}
	
	.table th {
		height: 40px;
		color: white;
		background-color: #c9c9c9;
		font-size: 1.2rem;
	}
	
	.table td {
		height: 40px;
	}
	
	.btn-area {
		text-align: right;
		margin-bottom: 10px;
	}
	
	.pagination {
		width: 1200px;
		text-align: center;
		margin: 0 auto;
	}
</style>

<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">

</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<section class="container">
		<div class="main">
			<!-- New -->
			<div class="btn-area">
				<button onclick="goWrite();">작성하기</button>
			</div>
			
			
			<input type="hidden" id="nowPage" name="nowPage" value="${data.nowPage}">
			
			<!-- Table -->
			<table class="table">
				<colgroup>
					<col width="10%">
					<col width="40%">
					<col width="10%">
					<col width="10%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>글번호</th>
						<th>글제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>등록일</th>
						<th>수정일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${data.noticeList}">
						<tr>
							<td>${item.noId}</td>
							<td>
								<a href="javascript:void(0);"
								   onclick="goDetail(${item.noId})">
								${item.title}
								</a>
							</td>
							<td>${item.writer}</td>
							<td>${item.readCnt}</td>
							<td>${item.createDate}</td>
							<td>${item.updateDate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="pagination">
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    ${data.pageHtml}
			  </ul>
			</nav>
		</div>
	</section>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>

	function goWrite() {
		const nowPage = $('#nowPage').val();
		location.href = '/notice/write.do?nowPage=' + nowPage;
	}

	function movePage(nowPage) {
		// 페이지 이동
		location.href = '/notice/list.do?nowPage=' + nowPage;
	}
	
	function goDetail(noId) {
		const nowPage = $('#nowPage').val();
		// 페이지 이동
		location.href = '/notice/detail.do?nowPage=' + nowPage + '&noId=' + noId;
	}

</script>
</html>