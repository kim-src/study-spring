<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" session = "false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>

<style>
	body {
		margin: 0px;
		padding: 0px;
	}
	
	.container {
		height: 100vh;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: #808080;
	}
	
	.content {
		width: 600px;
		height: 300px;
		border: 1px solid black;
		border-radius: 15px;
		box-shadow: 1px -3px 15px 6px #ffffff;
		background-color: #fffcfc;
	}
	
	.txt-title {
		text-align: center;
		font-size: 40px;
		font-weight: 600;
		margin-top: 15px;
		margin-bottom: 30px;
	}
	
	.login-frm {
		list-style-type: none;
	}
	
	.login-frm > li {
		margin-bottom: 15px;
	}
	
	.login-frm > li > label {
		display: inline-block;
		width: 120px;
		text-align: right;
		font-weight: bold;
		height: 30px;
	}
	
	.txt-field {
		width: 300px;
		height: 40px;
		margin-left: 5px;
		outline: none;
		border: none;
		border-bottom: 2px solid black;
		font-size: 18px;
	}
	
	.btn-area {
		text-align: center;
		margin-top: 20px;
	}
	
	.btn {
		width: 100px;
		height: 50px;
		background-color: white;
		border: none;
		font-size: 20px;
		color: white;
		font-weight: bold;
		border-radius: 8px;
		cursor: pointer;
	}
	
	.btn.btn-ok {
		background-color: #6495ed;
	}
	
	.btn.btn-join {
		background-color: #20b2aa;
	}
	
	.btn.btn-board {
		background-color: green;
	}
</style>
</head>
<body>
	<main class="container">
		<div class="content">
			<div class="txt-title">Login</div>
			<ul class="login-frm">
				<li>
					<label for="memId">아이디 : </label>
					<input type="text" class="txt-field" id="memId" name="memId">
				</li>
				<li>
					<label for="memPassword">비밀번호 : </label>
					<input type="password" class="txt-field" id="memPassword" name="memPassword">
				</li>
				<li>
					<div class="btn-area">
						<button type="button" class="btn btn-ok" onclick="goLogin();">로그인</button>
						<button type="button" class="btn btn-join">회원가입</button>
						<button type="button" class="btn btn-board" onclick="goList();">게시판</button>
					</div>
				</li>
			</ul>
		</div>
	</main>
</body>
<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script>

	function validate() {
		
		const memId = $('#memId');
		const memPassword = $('#memPassword');
		
		if($.trim(memId.val()).length === 0) {
			alert('Enter your ID.');
			return false;
		}
		
		if($.trim(memPassword.val()).length === 0) {
			alert('Enter your Password.');
			memPassword.focus();
			return false;
		}
		
		return true;
		
	}
	
	function goLogin() {
		
		if(validate()) {
			
			const dataParm = {
					memId : $('#memId').val(),
					memPassword : $('#memPassword').val()
			}
			
			// 로그인 처리(비동기 처리용 에이젝스 활용)
			// 에이젝스가 성공하면 done이 붙고 콜백함수 실행(파라미터 1개)
			// 에이젝스가 실패하면 fail이 붙고 콜백함수 실행(파라미터 3개)(보통 서버에서 오류)
			// 파라미터는 Object 형태로 설정
			$.ajax({
				
				url : '/login/proc.do',
				type : 'get',
				dataType : 'json',
				data : dataParm
				
			}).done(function(resp) {
				
				if(resp.resultCode === 200) {
					location.href = "/notice/list.do";
				} else {
					alert('Please Check your ID or Password.');
				}
				
			}).fail(function(xhr, status, error) {
				console.log('Server Error');
			});
			
		}
		
	}
	
	function goList() {
		location.href = "/notice/list.do"	
	}
	
	// 자동 실행 함수
	// Enter 입력으로 로그인 하는 방법
	$(document).ready(function() {
		$('#memPassword').on('keyup', function(e) {
			// Enter 엔터키 = 13
			if(e.keyCode === 13) {
				// 엔터로 무조건 로그인 시도가 가능할지
				// validate();
				goLogin();
			}
		});
	});

</script>
</html>