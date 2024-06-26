<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		const memPassword = document.querySelector('#memPassword');
		
		if($.trim(memId.val()).length === 0) {
			alert('Enter your ID.');
			return false;
		}
		
		if($.trim(memPassword.value).length === 0) {
			alert('Enter your Password.');
			memPassword.focus();
			return false;
		}
		
		return true;
		
	}
	
	function goLogin() {
		
		if(validate()) {
			alert('Welcome !');
		}
		
	}

</script>
</html>