<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 정보</title>
<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">
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
							<c:if test="${notice.file ne null}">
								<a href="javascript:void(0)" onclick="downFile(${notice.file.fileId})">
									${notice.file.fileName}
								</a>
							</c:if>
							<c:if test="${notice.file eq null}">
								첨부파일 없음
							</c:if>
						</td>
					</tr>
					<tr>
						<th>글 내용</th>
						<td class="text-contents">
							${notice.contents}
						</td>
					</tr>
				</tbody>
			</table>
			<input type="hidden" id="nowPage" name="nowPage" value="${nowPage}">
			<input type="hidden" id="noId" name="noId" value="${notice.noId}">
			<div>
				<button type="button" class="btn btn-primary" onclick="writeNotice();">수정</button>
				<button type="button" class="btn btn-danger" onclick="deleteNotice();">삭제</button>
				<button type="button" class="btn btn-success" onclick="goList();">목록</button>
			</div>
		</div>
	</section>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>
	
	function writeNotice() {
		const nowPage = $('#nowPage').val(); 
		const noId = $('#noId').val();
		location.href = '/notice/update/view.do?nowPage=' + nowPage + '&noId=' + noId;
	}
	
	function deleteNotice() {
		const nowPage = $('#nowPage').val(); 
		const noId = $('#noId').val();
		if(confirm('정말 삭제하시겠습니까?')) {
			location.href = '/notice/delete.do?nowPage=' + nowPage + '&noId=' + noId;
		}
	}

	function goList() {
		location.href = "/notice/list.do?nowPage=" + $('#nowPage').val();
	}
	
	function downFile(fileId) {
		location.href = "/notice/file/down.do?fileId=" + fileId;
	}

</script>
</html>