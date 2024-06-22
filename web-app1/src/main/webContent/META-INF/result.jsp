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
  <%
     //자바코드를 사용하는 영역
     String userName = request.getParameter("userName");
     int  userAge =   Integer.valueOf(request.getParameter("userAge"));
     
  %>
  <div>
    이름 : <span><%=userName %></span><br>
    나이 : <span><%=userAge%></span><br>
  </div>
</body>
</html>