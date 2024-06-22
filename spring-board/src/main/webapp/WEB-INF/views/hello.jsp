<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>2024-06-20 강의 실습</div>
	<h1>${msg} Spring 작동 메커니즘</h1>
	<!-- EL 사용 아닐 경우 -->
	<% String msg = (String)request.getAttribute("msg"); %>
</body>
</html>