<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>年会</title>
    <link href="/views/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/views/bootstrap/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
<body>
<div class="htmleaf-container">
    <header class="htmleaf-header">
        <h1 style="text-align: center">2017-年会游戏</h1>
    </header>
    <div class="container kv-main">
        <form id="game" enctype="multipart/form-data">
            <div class="form-group">
                <label>奖项名称：</label>
                <input id="name" name="name" class="form-control" type="text" value="${game.gameName}">
            </div>
            <div class="form-group">
                <label>滚动时间(sm)：</label>
                <input id="rollTimes" name="rollTimes" class="form-control" type="number" value="${game.rollTimes}">
            </div>
            <div class="form-group">
                <label>亲吻时间(sm)：</label>
                <input id="gameTimes" name="gameTimes" class="form-control" type="number" value="${game.gameTimes}">
            </div>
        </form>
        <hr>
        <form enctype="multipart/form-data">
            <div class="form-group">
                <label>获奖照片：</label>
                <input id="file-0" class="file" name="file" type="file" multiple data-min-file-count="1">
            </div>
        </form>
        <hr>
        <form enctype="multipart/form-data">
            <div class="form-group">
                <label>入围照片：</label>
                <input id="file-1" type="file" name="file" multiple class="file" data-min-file-count="1">
            </div>
            <hr>
            <div class="form-group">
                <button id="saveBnt" class="btn btn-warning" type="button" onclick="submitForm()">保存</button>
                <c:if test="game.status ==0">
                    <button class="btn btn-info" type="button" onclick="start()">开始</button>
                </c:if>
                <button class="btn btn-primary" type="button" onclick="result()">查看结果</button>
            </div>
        </form>
    </div>
</div>
</body>
    <script src="/views/js/jquery.min.js" type="text/javascript"></script>
    <script src="/views/bootstrap/fileinput/js/fileinput.js" type="text/javascript"></script>
    <!--简体中文-->
    <script src="/views/bootstrap/fileinput/js/locales/zh.js" type="text/javascript"></script>
    <!--繁体中文-->
    <script src="/views/bootstrap/fileinput/js/locales/zh-TW.js" type="text/javascript"></script>
    <script src="/views/bootstrap/js/bootstrap.min.js"></script>
    <script src="/views/js/app.js"></script>
    <script>
        var winAvatarPreview = [];
        var winAvatarPreviewConfig = [];
        <c:if test="${game.winAvatar != null}">
            winAvatarPreview.push("<img src='/temp/${game.winAvatar.url}' class='file-preview-image' alt='${game.winAvatar.fileName}' title='${game.winAvatar.fileName}' style='width: 100px;height: 100px'>");
            winAvatarPreviewConfig.push({
                caption: "${game.winAvatar.url}",
                width: '80px',
                url: '/localhost/avatar/delete',
                key: 1
            });
        </c:if>
        var shortlistPreview = [];
        var shortlistPreviewConfig = [];
        <c:forEach items="${game.shortlist}" var="sl">
            shortlistPreview.push("<img src='/temp/${sl.url}' class='file-preview-image' alt='${sl.fileName}' title='${sl.fileName}' style='width: 100px;height: 100px'>");
            shortlistPreviewConfig.push({
                caption: "${sl.url}",
                width: '80px',
                url: '/localhost/avatar/delete',
                key: 1
            });
        </c:forEach>

        $("#file-0").fileinput({
            language: 'zh',
            uploadUrl: ctx+'/game/uploadWinningPictures',
            allowedFileExtensions : ['jpg', 'png','gif'],
            overwriteInitial: false,
            dropZoneEnabled: false,
            showUpload: false,
            showRemove: false,
            maxFileSize: 1000,
            maxFilesNum: 1,
            initialPreview: winAvatarPreview,
            initialPreviewConfig: winAvatarPreviewConfig,
            allowedFileTypes: ['image']
        });

        $("#file-1").fileinput({
            language: 'zh',
            uploadUrl: ctx+'/game/uploadWqfacPictures',
            allowedFileExtensions : ['jpg', 'png','gif'],
            overwriteInitial: false,
            showUpload: false,
            maxFileSize: 1000,
            maxFilesNum: 100,
            initialPreview: shortlistPreview,
            initialPreviewConfig: shortlistPreviewConfig,
            allowedFileTypes: ['image']
            /*uploadExtraData: function(previewId, index) { //上传额外参数
             var obj = {};
             obj.fodder = '1111';
             console.log(obj);
             return obj;
             }*/
            /*slugCallback: function(filename) {
             return filename.replace('(', '_').replace(']', '_');
             }*/
        });

        function submitForm() {
            $.ajax({
                url: ctx+"/game/addGame",
                type: "post",
                data: $("#game").serialize(),
                success: function (res) {
                    $('#file-0').fileinput('upload');
                    $('#file-1').fileinput('upload');
                }
            });
        }
        function start() {
            $.ajax({
                url: ctx+"/game/setStatus/1",
                type: "get",
                success: function (res) {
                    if(res.code == 1){
                        $("#saveBnt").hide();
                        alert(res.message);
                    } else{
                        alert("开始");
                    }
                }
            });
        }
        function result() {
            window.location.href = ctx+"/views/html/data.html";
        }
    </script>
</html>
