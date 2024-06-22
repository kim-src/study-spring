<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=<device-width>, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<section>
		<form id="frm" method="get" action="/web-app1/form_result2.jsp">
			<div>
				<label for="myName">이름 : </label>
				<input type="text" id="myName" name="myName"><br>
			 	<label for="myAge">나이 : </label>
				<input type="text" id="myAge" name="myAge"><br>
				<label>취미 : </label>
				<input type="checkbox" id="walk" name="hobby" value="산책">산책
				<input type="checkbox" id="movie" name="hobby" value="영화감상">영화감상
				<input type="checkbox" id="music" name="hobby" value="음악듣기">음악듣기
			</div>
		</form>
		<div>
			<button type="button" onclick="send();">전송</button>
		</div>
	</section>
	<script>
		function send() {
			const form = document.querySelector('#frm');
			form.submit();
		}
	</script>
</body>
</html>