<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<div>
		<h4>${param.myName}</h4>
	</div>
	<div>
		<h4>${param.myAge}</h4>
	</div>
	<c:forEach var="item" items="${paramValues.hobby}">
		<div>
			<h4>${item}</h4>
		</div>
	</c:forEach>
</body>
</html>