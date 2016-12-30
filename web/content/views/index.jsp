<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>年会</title>
</head>
<body>
<h1>springMVC字节流输入上传文件</h1>
<form name="userForm1" action="/game/addGame" enctype="multipart/form-data" method="post">
    <div id="newUpload1">
        <input type="file" name="file">
    </div>
    <input type="button" id="btn_add1" value="增加一行">
    <input type="submit" value="上传">
</form>

<script src="../js/jquery.min.js"></script>
<script src="../js/app.js"></script>
<script>
    i = 1;
    j = 1;
    $(document).ready(function(){
        $("#btn_add1").click(function(){
            document.getElementById("newUpload1").innerHTML+='<div id="div_'+i+'"><input  name="file" type="file"  /><input type="button" value="删除"  onclick="del_1('+i+')"/></div>';
            i = i + 1;
        });
        $("#btn_add2").click(function(){
            document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';
            j = j + 1;
        });
    });
    function del_1(o){
        document.getElementById("newUpload1").removeChild(document.getElementById("div_"+o));
    }
    function del_2(o){
        document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
    }
</script>
</body>
</html>
