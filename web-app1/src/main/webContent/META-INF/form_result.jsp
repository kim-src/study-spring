<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
	<%
		String myName = request.getParameter("myName");
		int myAge = Integer.parseInt(request.getParameter("myAge"));
		String[] hobbies = request.getParameterValues("hobby");
		
		out.print("<div><h4>" + myName + "</h4></div>");
		out.print("<div><h4>" + myAge + "</h4></div>");
		
		if(hobbies != null) {
			for(String hobby : hobbies) {
				out.print("<div><h4>" + hobby + "</h4></div>");
			}
		}
	%>
</body>
</html>