<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>富头像上传编辑器</title>

    <link href="/static/jsp/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/jsp/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="/static/jsp/animate.css" rel="stylesheet">

    <link href="/static/jsp/style.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper wrapper-content">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>富头像上传编辑器</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="dropdown-toggle" data-toggle="dropdown" href="<%=path %>/form_editors.html#">
                            <i class="fa fa-wrench"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="<%=path %>/form_editors.html#">选项1</a>
                            </li>
                            <li><a href="<%=path %>/form_editors.html#">选项2</a>
                            </li>
                        </ul>
                        <a class="close-link">
                            <i class="fa fa-times"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs" id="avatar-tab">
                        <li class="active" id="upload"><a href="javascript:;">本地上传</a>
                        </li>
                        <li id="webcam"><a href="javascript:;">视频拍照</a>
                        </li>
                        <li id="albums"><a href="javascript:;">相册选取</a>
                        </li>
                    </ul>
                    <div class="m-t m-b">
                        <div id="flash1">
                            <p id="swf1">本组件需要安装Flash Player后才可使用，请从<a
                                    href="http://www.adobe.com/go/getflashplayer">这里</a>下载安装。</p>
                        </div>
                        <div id="editorPanelButtons" style="display:none">
                            <p class="m-t">
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="src_upload">是否上传原图片？</label>
                            </p>
                            <p>
                                <a href="javascript:;" class="btn btn-w-m btn-primary button_upload"><i
                                        class="fa fa-upload"></i> 上传</a>
                                <a href="javascript:;" class="btn btn-w-m btn-white button_cancel">取消</a>
                            </p>
                        </div>
                        <p id="webcamPanelButton" style="display:none">
                            <a href="javascript:;" class="btn btn-w-m btn-info button_shutter"><i
                                    class="fa fa-camera"></i> 拍照</a>
                        </p>
                        <div id="userAlbums" style="display:none">
                            <a href="<%=path %>/static/img/a1.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a1.jpg" alt="示例图片1"/>
                            </a>
                            <a href="<%=path %>/static/img/a2.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a2.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a3.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a3.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a4.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a4.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a5.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a5.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a6.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a6.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a7.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a7.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a8.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a8.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a9.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a9.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a10.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a10.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a11.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a11.jpg" alt="示例图片2"/>
                            </a>
                            <a href="<%=path %>/static/img/a12.jpg" class="fancybox" title="选取该照片">
                                <img src="<%=path %>/static/img/a12.jpg" alt="示例图片2"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 全局js -->
<script src="/static/jsp/jquery.min.js?v=2.1.4"></script>
<script src="/static/jsp/bootstrap.min.js?v=3.3.6"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<!-- 自定义js -->
<script src="/static/jsp/content.js?v=1.0.0"></script>
<!-- fullavatareditor -->
<script type="text/javascript" src="/static/scripts/swfobject.js"></script>
<script type="text/javascript" src="/static/scripts/fullAvatarEditor.js"></script>
<script type="text/javascript" src="/static/scripts/jQuery.Cookie.js"></script>
<%--<script type="text/javascript" src="/static/scripts/test.js"></script>--%> <%-- 自己加上的 --%>

<%--<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>--%>
<!--统计代码，可删除-->
</body>

<script>

    swfobject.addDomLoadEvent(function () {
        //------------------------------------------------------------------------------示例一
        var webcamAvailable = false;
        var currentTab = 'upload';
        var sourcePic1Url = $.Cookie('swf1');
        var sourcePic2Url = $.Cookie('swf2');
        if (sourcePic2Url == null) {
            sourcePic2Url = "http://www.baidu.com/img/bdlogo.png";
        }
        var $parentDocument = $(parent.document);
        var jpgj= $parentDocument.find("img.img-circle.m-t-xs.img-responsive");
        var $jpgj = $(jpgj);
        var callback = function (json) {
            var id = this.id;
            switch (json.code) {
                case 2:
                    //如果加载原图成功，说明进入了编辑面板，显示保存和取消按钮，隐藏拍照按钮
                    if (json.type == 0) {
                        if (id == "swf1") {
                            $('#webcamPanelButton').hide();
                            $('#editorPanelButtons').show();
                        }
                    }
                    //否则会转到上传面板
                    else {
                        //隐藏所有按钮
                        if (id == "swf1") $('#editorPanelButtons,#webcamPanelButton').hide();
                    }
                    break;
                case 3:
                    //如果摄像头已准备就绪且用户已允许使用，显示拍照按钮。
                    if (json.type == 0) {
                        if (id == "swf1") {
                            $('.button_shutter').removeClass('Disabled');
                            $('#webcamPanelButton').show();
                            webcamAvailable = true;
                        }
                    }
                    else {
                        if (id == "swf1") {
                            webcamAvailable = false;
                            $('#webcamPanelButton').hide();
                        }
                        //如果摄像头已准备就绪但用户已拒绝使用。
                        if (json.type == 1) {
                            swal({title:"",
                                text:"用户拒绝使用摄像头!",
                                confirmButtonText:"确认",
                                type:"error"});
                        }
                        //如果摄像头已准备就绪但摄像头被占用。
                        else {
                            swal({title:"",
                                text:"摄像头被占用!",
                                confirmButtonText:"确认",
                                type:"error"});
                        }
                    }
                    break;
                case 4:
                    swal({title:"",
                        text:"您选择的原图片文件大小（" + json.content + "）超出了指定的值(2MB)。",
                        confirmButtonText:"确认",
                        type:"error"});
                    break;
                case 5:
                    //如果上传成功
                    if (json.type == 0) {
                        var e = this;
                        var html = $('<div class="imgList"/>');
                        for (var i = 0; i < json.content.avatarUrls.length; i++) {
                            html.append('<dl><dt>头像图片' + (i + 1) + '</dt><dd><img src="' + json.content.avatarUrls[i] + '" /></dd></dl>');
                        }
                        var button = [];
                        //如果上传了原图，给个修改按钮，感受视图初始化带来的用户体验度提升
                        if (json.content.sourceUrl) {
                            button.push({
                                text: '修改头像', callback: function () {
                                    this.close();
                                    $.Cookie(id, json.content.sourceUrl);
                                    location.reload();
                                    //e.call('loadPic', json.content.sourceUrl);
                                }
                            });
                            $jpgj.attr("src",json.content.sourceUrl + "?ran=" +parseInt(Math.random()*1000000));
                        } else {
                            $.Cookie(id, null);
                        }
                        button.push({text: '关闭窗口'});

                        $.dialog({
                            title: '图片已成功保存至服务器',
                            content: html,
                            button: button,
                            mask: true,
                            draggable: false
                        });
                    } else {
                        swal({title:"",
                            text:"上传头像失败,原因未知,请联系管理员",
                            confirmButtonText:"确认",
                            type:"error"});
                    }
                    break;
            }
        };
        var swf1 = new fullAvatarEditor('swf1', 335, {
            id: 'swf1',
            upload_url: '/icon/upload',
            method : "post",
            src_url: sourcePic1Url,			//默认加载的原图片的url
            tab_visible: false,				//不显示选项卡，外部自定义
            button_visible: false,				//不显示按钮，外部自定义
            src_upload: 2,						//是否上传原图片的选项：2-显示复选框由用户选择，0-不上传，1-上传
            checkbox_visible: false,			//不显示复选框，外部自定义
            browse_box_align: 38,				//图片选择框的水平对齐方式。left：左对齐；center：居中对齐；right：右对齐；数值：相对于舞台的x坐标
            webcam_box_align: 38,				//摄像头拍照框的水平对齐方式，如上。
            avatar_sizes: '258*200',			//定义单个头像
            avatar_sizes_desc: '258*200像素',	   //头像尺寸的提示文本。
            browse_box_align: 'left',            //头像选择框对齐方式
            webcam_box_align: 'left',            //头像拍照框对齐方式
            //头像简介
            avatar_intro: '     最终会生成下面这个尺寸的头像',
            avatar_tools_visible: true			//是否显示颜色调整工具
        }, callback);
        //选项卡点击事件
        $('#avatar-tab li').click(function () {
            if (currentTab != this.id) {
                currentTab = this.id;
                $(this).addClass('active');
                $(this).siblings().removeClass('active');
                //如果是点击“相册选取”
                if (this.id === 'albums') {
                    //隐藏flash
                    hideSWF();
                    showAlbums();
                }
                else {
                    hideAlbums();
                    showSWF();
                    if (this.id === 'webcam') {
                        $('#editorPanelButtons').hide();
                        if (webcamAvailable) {
                            $('.button_shutter').removeClass('Disabled');
                            $('#webcamPanelButton').show();
                        }
                    }
                    else {
                        //隐藏所有按钮
                        $('#editorPanelButtons,#webcamPanelButton').hide();
                    }
                }
                swf1.call('changepanel', this.id);
            }
        });
        //复选框事件
        $('#src_upload').change(function () {
            swf1.call('srcUpload', this.checked);
        });
        //点击上传按钮的事件
        $('.button_upload').click(function () {
            swf1.call('upload');
        });
        //点击取消按钮的事件
        $('.button_cancel').click(function () {
            var activedTab = $('#avatar-tab li.active')[0].id;
            if (activedTab === 'albums') {
                hideSWF();
                showAlbums();
            }
            else {
                swf1.call('changepanel', activedTab);
                if (activedTab === 'webcam') {
                    $('#editorPanelButtons').hide();
                    if (webcamAvailable) {
                        $('.button_shutter').removeClass('Disabled');
                        $('#webcamPanelButton').show();
                    }
                }
                else {
                    //隐藏所有按钮
                    $('#editorPanelButtons,#webcamPanelButton').hide();
                }
            }
        });
        //点击拍照按钮的事件
        $('.button_shutter').click(function () {
            if (!$(this).hasClass('Disabled')) {
                $(this).addClass('Disabled');
                swf1.call('pressShutter');
            }
        });
        //从相册中选取
        $('#userAlbums a').click(function () {
            var sourcePic = this.href;
            swf1.call('loadPic', sourcePic);
            //隐藏相册
            hideAlbums();
            //显示flash
            showSWF();
            return false;
        });
        //隐藏flash的函数
        function hideSWF() {
            //将宽高设置为0的方式来隐藏flash，而不能使用将其display样式设置为none的方式来隐藏，否则flash将不会被加载，隐藏时储存其宽高，以便后期恢复
            $('#flash1').data({
                w: $('#flash1').width(),
                h: $('#flash1').height()
            })
                    .css({
                        width: '0px',
                        height: '0px',
                        overflow: 'hidden'
                    });
            //隐藏所有按钮
            $('#editorPanelButtons,#webcamPanelButton').hide();
        }

        function showSWF() {
            $('#flash1').css({
                width: $('#flash1').data('w'),
                height: $('#flash1').data('h')
            });
        }

        //显示相册的函数
        function showAlbums() {
            $('#userAlbums').show();
        }

        //隐藏相册的函数
        function hideAlbums() {
            $('#userAlbums').hide();
        }

        //------------------------------------------------------------------------------示例二
        var swf2 = new fullAvatarEditor('swf2', {
            id: 'swf2',
            upload_url: '/icon/upload',	//上传图片的接口地址
            src_url: sourcePic2Url,		//默认加载的原图片的url
            src_upload: 2,				//是否上传原图片的选项：2-显示复选框由用户选择，0-不上传，1-上传
            avatar_scale: 2,				//头像保存时的缩放系数
            avatar_intro: '最终头像的尺寸为以下尺寸 * 2(设置的缩放系数)',	//头像尺寸的提示文本。其间用"|"号分隔，
            avatar_sizes_desc: '100*100像素，缩放系数为2，保存后的大小为200*200像素。|50*50像素，缩放系数为2，保存后的大小为100*100像素。|32*32像素，缩放系数为2，保存后的大小为64*64像素。'
        }, callback);
    });

</script>

</html>
