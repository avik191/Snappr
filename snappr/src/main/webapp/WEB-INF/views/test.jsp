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
var s = '';
function crunchifyAjax() {
	var jsonUrl ='/snappr/test/ajax';
	
	console.log(jsonUrl);
	$.ajax({
        url : jsonUrl,
        dataSrc : '',
        data : {start:0, limit:2},
        success : function(data) {
        	for (var i = 0; i < data.length; i++) { 
        		s+='<b>Name : </b>'+data[i].name+'</br>'+
 				'<b>Email : </b>'+data[i].email+'</br>'+
 				'<img src="'+data[i].path+'" /></br>'; 
        		
        	}
        	 
        	 $('#result').html(s);
        }
    });
    
}
   // setInterval(crunchifyAjax, 3000);
////////////////////////////////////////////////////////   
   
   $(document).ready(function(){
 
 var limit = 4;
 var start = 0;
 var action = 'inactive';
 var s='';
   
   
  function load_country_data(limit, start)
 {
		var jsonUrl ='/snappr/test/ajax';
  $.ajax({
   url:jsonUrl,
   method:"POST",
   data:{limit:limit, start:start},
   cache:false,
   success:function(data)
   {
    
	   for (var i = 0; i < data.length; i++) { 
   		s+='<b>Name : </b>'+data[i].name+'</br>'+
			'<b>Email : </b>'+data[i].email+'</br>'+
			'<img src="'+data[i].path+'" /></br>'+
			'<a href="https://www.google.com">Google</a></br>'; 
   		
   		}
   	 
   	 $('#result').html(s);
	   
	   
    if(data == null)
    {
     action = 'active';
    }
    else
    {
     action = "inactive";
    }
   }
  });
 }

 if(action == 'inactive')
 {
  action = 'active';
  load_country_data(limit, start);
 }
 $(window).scroll(function(){
  if($(window).scrollTop() + $(window).height() > $("#result").height() && action == 'inactive')
  {
   action = 'active';
   start = start + limit;
   setTimeout(function(){
    load_country_data(limit, start);
   }, 3000);
  }
 });
 
});
   
   
   
   
</script>
</head>
<body>

        <div id="result"></div>


</body>
</html>