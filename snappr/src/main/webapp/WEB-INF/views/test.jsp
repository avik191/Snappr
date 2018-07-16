<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script type="text/javascript">
function crunchifyAjax() {
	var jsonUrl ='/snappr/test/ajax';
	var s = '';
	console.log(jsonUrl);
	$.ajax({
        url : jsonUrl,
        dataSrc : '',
        success : function(data) {
        	for (var i = 0; i < data.length; i++) { 
        		s+='<b>Name : </b>'+data[i].name+'</br>'+
 				'<b>Email : </b>'+data[i].email+'</br>'; 
        		
        	}
        	 
        	 $('#result').html(s);
        }
    });
    
}
    setInterval(crunchifyAjax, 3000);
</script>
</head>
<body>

        <div id="result"></div>


</body>
</html>