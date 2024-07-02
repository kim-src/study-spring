<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
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
		width: 700px;
		margin: 0 auto;
	}
	
	.tables {
		width: 100%;
		border: 1px solid black;
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	
	.tables th, td {
		border: 1px solid black;
		height: 40px;
	}
	
	.tables td {
		text-align: left;
		padding: 10px;
	}
	
	.text-title {
		margin-bottom: 10px;
		font-weight: 700;
	}
	
	.text-contents{
		height: 300px;
		padding: 20px;
	}
</style>
<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<section class="container">
		<div class="main">
			<div class="text-title">
				<h3>글 내용 보기</h3>
			</div>
			<table class="tables">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<tbody>
					<tr>
						<th>글번호</th>
						<td>${notice.noId}</td>
					</tr>
					<tr>
						<th>글제목</th>
						<td>${notice.title}</td>
					</tr>
					<tr>
					<tr>
						<th>조회수</th>
						<td>${notice.readCnt}</td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:if test="${notice.file != null}">
								<a href="javascript:void(0)" onclick="download(${notice.file.fileId})">
									${notice.file.fileName}
								</a>
							</c:if>
							<c:if test="${notice.file == null}">
								<div>첨부파일 없음</div>
							</c:if>
						</td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td class="text-contents">
							<div>${notice.contents}</div>
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" name="nowPage" id="nowPage" value="${nowPage}">
			<div>
				<button type="button" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger">삭제</button>
				<button type="button" class="btn btn-success" onclick="goList();">목록</button>
			</div>
		</div>
	</section>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>

	function goList() {
		location.href = "/notice/list.do?nowPage=" + $('#nowPage').val();
	}

</script>
</html>