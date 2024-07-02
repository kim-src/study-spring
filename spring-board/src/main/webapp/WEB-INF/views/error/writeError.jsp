<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="msg"></div>
</body>
<script>
	let count = 5;
	let timer = '';
	
	function error() {
		
		if(count == 0) {
			clearInterval(timer);
			location.href = "/notice/list.do";
		} else {
			count--;
			tiemr = setInterval(error, 1000);
		}
		
		let msg = '파일 등록에 실패했습니다. ${count}초 후 페이지 이동합니다.';
		$('#msg').empty();
		$('#msg').append(msg);
		
	}
	
	error();
	
</script>
</html>