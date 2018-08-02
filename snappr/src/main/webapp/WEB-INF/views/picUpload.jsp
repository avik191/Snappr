<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta charset="utf-8">
    </head>
    <body>
        <form id="upload_form" enctype="multipart/form-data" method="post">
        <input type="file" name="file1" id="file1"><br>
        <input type="button" value="Upload File" onclick="uploadFile()">
        <progress id="progressBar" value="0" max="100" style="width:300px;"></progress>
        </form>
    <div style='text-align: right;position: fixed;z-index:9999999;bottom: 0; width: 100%;cursor: pointer;line-height: 0;display:block !important;'><a title="Hosted on free web hosting 000webhost.com. Host your own website for FREE." target="_blank" href="https://www.000webhost.com/?utm_source=000webhostapp&amp;utm_campaign=000_logo&amp;utm_medium=website_sify4321&amp;utm_content=footer_img"><img src="https://cdn.rawgit.com/000webhost/logo/e9bd13f7/footer-powered-by-000webhost-white2.png"  alt="www.000webhost.com"></a></div></body>
    
    <script>
		    function _(el){
		        return document.getElementById(el);
		    }
		    function uploadFile(){
		        var file = _("file1").files[0];
		        // alert(file.name+" | "+file.size+" | "+file.type);
		        var formdata = new FormData();
		        formdata.append("file1", file);
		        var ajax = new XMLHttpRequest();
		        ajax.upload.addEventListener("progress", progressHandler, false);
		        ajax.addEventListener("load", completeHandler, false);
		        ajax.addEventListener("error", errorHandler, false);
		        ajax.addEventListener("abort", abortHandler, false);
		        ajax.open("POST", "handlePicUpload");
		        ajax.send(formdata);
		    }
		    function progressHandler(event){
		        var percent = (event.loaded / event.total) * 100;
		        _("progressBar").value = Math.round(percent);
		    }
		    function completeHandler(event){
		        console.log(event.target.responseText);
		        _("progressBar").value = 0;
		    }
		    function errorHandler(event){
		        console.log("Upload Failed");
		    }
		    function abortHandler(event){
		        console.log("Upload Aborted");
		    }
    </script>
</html>