<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/summernote.css">
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/summerNote/summernote.js"></script>
    <script src="/static/js/summerNote/summernote-zh-CN.js"></script>
    <script>
        $(function () {
            $('#summernote').summernote({
                lang: 'zh-CN', // default: 'en-US'
                height: 150,   //set editable area's height
                // 重写图片上传
                onImageUpload: function(files, editor, welEditable) {
                    sendFile(files[0],editor,welEditable);
                }
            });
            $('#summernote').summernote('code', 'js赋值');
        });

        function sendFile(files,editor,welEditable) {
            data = new FormData();
            data.append("file", files);
            $.ajax({
                data: data,
                type: "POST",
                url: "/summberNote/add",
                cache: false,
                contentType: false,
                processData: false,
                success: function(data) {
                    alert(data.fileName);
                    editor.insertImage(welEditable, "img/" + data.fileName);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }

        function submit1(){
            alert($('#summernote').summernote('code'));
        }
        function submit2(){
            var str = $('#summernote').summernote('code');
            $.get("/summerNote/add/"+str,
                    function (data) {
                        alert(data.message)
                    });
        }
    </script>
</head>
<body>
<div class="container">
        <div id="summernote" class="summernote"></div>
        <button onclick="submit1()" >取值</button>
        <button onclick="submit2()" >提交</button>
</div>
</body>
</html>
