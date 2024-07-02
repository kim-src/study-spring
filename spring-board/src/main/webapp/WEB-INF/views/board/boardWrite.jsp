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
	
	.text-area {
		width: 100%;
		height: 100px;
		resize: none;
		padding: 10px;
		border-radius: 5px;
		font-size: 1.1rem;
	}
</style>
<link rel="stylesheet" href="/webjars/bootstrap/4.6.2/css/bootstrap.min.css">
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<section class="container">
		<div class="main">
			<div class="text-title">
				<h3>게시글 작성</h3>
			</div>
			<!-- 이진법 binary 타입이기 때문에 GET 요청하면 전송이 완전하지 않을 수 있음 -->
			<!-- enctype = 인코딩 타입 -->
			<form id="frm" action ="/notice/add.do" method="post" enctype="multipart/form-data">
				<table class="tables">
					<colgroup>
						<col width="20%">
						<col width="80%">
					</colgroup>
					<tbody>
						<tr>
							<th>글제목</th>
							<td>
								<input type="text" class="form-control" id="title" name="title">
							</td>
						</tr>
						<tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<input type="file" class="form-control-file" id="file" name="file">
							</td>
						</tr>
						<tr>
							<th>글 내용</th>
							<td class="text-contents">
								<textarea class="text-area" id="contents" name="contents"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" id="nowPage" value="${nowPage}">
			</form>
			<div>
				<button type="button" class="btn btn-primary" onclick="writeNotice();">글쓰기</button>
				<button type="button" class="btn btn-secondary" onclick="goList();">취소</button>
			</div>
		</div>
	</section>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>

	function goList() {
		location.href = "/notice/list.do?nowPage=" + $('#nowPage').val();
	}
	
	function validate() {
		const title = $('#title');
		const contents = $('#contents');
		
		if($.trim(title.val()).length === 0) {
			alert('글 제목을 입력하세요.');
			title.focus();
			return false;
		}
		
		if($.trim(contents.val()).length === 0) {
			alert('글 내용을 입력하세요.');
			contents.focus();
			return false;
		}
		
		return true;
	}
	
	function writeNotice() {

		const frm = $('#frm');
		if(validate() == true) {
			// form을 서버로 제출
			frm.submit();
		}
		
	}

</script>
</html>