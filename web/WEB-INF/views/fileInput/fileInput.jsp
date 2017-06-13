<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/fileinput.css">
</head>
<body>
<!--  右键菜单 -->
<ul role="menu" id="itemMenu" style="widht:100px;cursor:hand;" class="dropdown-menu">
    <li ><a onclick="itemOnclik1();">刷新</a></li>
    <li class="divider"></li>
    <li ><a onclick="itemOnclik2();">关闭当前页</a></li>
    <li class="divider"></li>
    <li ><a onclick="itemOnclik3();">关闭其他页面</a></li>
    <li class="divider"></li>
    <li ><a onclick="itemOnclik4();">关闭所有页面</a></li>
</ul>

<div class="container kv-main">

    <div class="ibox-title">
        <div class="input-group">
            <div class="input-group-btn">
            </div>
            <input id="txt_file" name="txt_file" type="file" class="form-control" multiple class="file-loading"
                   placeholder="请选择或输入一个你想上传的相册类型,默认当天日期为类型!"/>
        </div>
    </div>

</div>

<%--<div class="container kv-main">--%>

<%--<div class="ibox-title">--%>
<%--<h4 style="color: #00a2d4">上传回忆</h4>--%>

<%--<div class="input-group">--%>
<%--<div class="input-group-btn">--%>
<%--<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">--%>
<%--相册类型--%>
<%--<span class="caret"></span>--%>
<%--</button>--%>
<%--<ul id="photoTypeList" class="dropdown-menu">--%>
<%--<c:forEach items="${photoTypes}" var="pt">--%>
<%--<li class="divider"></li>--%>
<%--<a href="#">--%>
<%--<li onclick="clickType(this);">${pt.title}</li>--%>
<%--</a>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<input id="photoType" name="photoType" type="text" class="form-control"--%>
<%--placeholder="请选择或输入一个你想上传的相册类型,默认当天日期为类型!"/>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<input id="file-1" name="file" type="file" class="file" multiple data-overwrite-initial="true"/>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/fileInput/fileinput.js"></script>
<script src="/static/js/fileInput/zh.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script>
    $(function () {
        //0.初始化fileinput
        var oFileInput = new FileInput();
        oFileInput.Init("txt_file", "/file/addFile");
    });

    //初始化fileinput
    var FileInput = function () {
        var oFile = new Object();
        //初始化fileinput控件（第一次初始化）
        oFile.Init = function(ctrlName, uploadUrl) {
            var control = $('#' + ctrlName);
            //初始化上传控件的样式
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: uploadUrl, //上传的地址
                allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
                showUpload: true, //是否显示上传按钮
                showCaption: false,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                dropZoneEnabled: true,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount: 10, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            }).on("fileuploaded", function(event, data) {
                // data 为controller返回的json
                if(data.response.result =='success'){
                    alert('处理成功');
                }
            });
        }
        return oFile;
    };
</script>
</body>
</html>
