<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=<device-width>, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<!-- get 메서드를 이용함 -->
	<form id="frm" action="/result.jsp" method="get">
		<div>
			<label for="userName">이름 : </label>
			<input type="text" id="userName" name="userName">
			<label for="userAge">나이 : </label>
			<input type="text" id="userAge" name="userAge">
		</div>
		<!-- button 타입을 submit으로 설정하면 유효성 검사가 이루어지지 않는 문제가 있음 -->
		<!-- 유효성 검사는 필수 데이터 누락을 방지할 수 있기 때문에 중요함 -->
		<!-- JavaScript에서 유효성 검사를 마친 후 submit 기능을 부여하였음 -->
		<div>
			<!-- onclick으로 클릭이 발생됐을 때 실행될 함수를 설정하였음 -->
			<button type="button" onclick="sendData();">전송</button>
			<button type="reset">취소</button>
		</div>
	</form>
</body>
<script>
	
	// if 조건문을 이용하여 유효성 검사 기능을 부여함
	// 콘텐츠가 존재하지 않을 경우 false를 return하도록 설정함
	function validated() {
		const userName = document.querySelector('#userName');
		const userAge = document.querySelector('#userAge');
		
		// 공백을 포함한 길이가 0인 경우 안내문을 호출함
		// 등호(= 기호)가 3개인 경우 값뿐만 아니라 데이터 타입까지 일치해야 됨
		if(userName.value.trim().length === 0) {
			alert('이름을 입력하세요.');
			userName.focus();
			return false;
		}
		
		if(userAge.value.trim().length === 0) {
			alert('나이를 입력하세요.');
			userAge.focus();
			return false;
		}
		
		// if 조건문에 해당되지 않을 경우 true을 return하도록 설정함
		return true;
	}
	
	// 유효성 검사 결과가 true일 경우 sendData 함수로 웹 페이지를 제출함
	function sendData() {
		
		if(validated() == true) {
			const frm = document.querySelector('#frm');
			frm.submit();
		}
		
	}
	
</script>
</html>