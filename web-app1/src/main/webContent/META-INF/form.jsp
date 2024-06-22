<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
   <form id="frm" action="/result.jsp" method="post">
      <div>
	        <label for="userName">이름 :</label>
	        <input type ="text" id="userName" name="userName">
	         <label for="userAge">나이 :</label>
	        <input type ="text" id="userAge" name="userAge">
      </div>
      <div>
        <button type="button" onclick="sendData();">전송</button>
        <button type="reset">취소</button>
      </div>
   </form>
</body>
<script>

    function validated() {
    	const userName = document.querySelector('#userName');
    	const userAge = document.querySelector('#userAge');
    	
    	if(userName.value.trim().length === 0 ) {
    		alert('사용자 이름을 입력하십시오');
    		userName.focus();
    		return false;
    	}
    	
    	if(userAge.value.trim().length === 0 ) {
    		alert('사용자 나이을 입력하십시오');
    		userAge.focus();
    		return false;
    	}
    	
    	return true;
    }
    
    function sendData() {
    	
    	if(validated()) {
    		const frm = document.querySelector('#frm');
    		//폼을 통한 데이터 전송
    		frm.submit();
    	}
    }


</script>
</html>