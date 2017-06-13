<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/iconfont.css" />
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="/static/h-ui/css/style.css" />
<link rel="stylesheet" href="/static/css/select2.min.css">
<link rel="stylesheet" href="/static/css/sweetalert.css">

<title>个人中心</title>
<meta name="keywords" content="">
<meta name="description" content="">
	<style>
	</style>
</head>
<body>
<div id="bodyDiv" style="display: none">
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl" style="background: #93a1a1;"> <h2 class="logo navbar-logo f-l mr-10 hidden-xs" style="padding: 0;">个人中心</h2>
				<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
				<nav class="nav navbar-nav">
					<ul class="cl">
						<li class=""><a href="/home" class="dropDown_A"><i class="Hui-iconfont">&#xe6d4;</i> 去首页</a>
					</li>
				</ul>
			</nav>
				<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li class="dropDown dropDown_hover">
							<a href="#" class="dropDown_A">${sessionScope.frontUser.userName} <i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="/changeaccount">切换账户</a></li>
								<li><a href="/outusers">退出</a></li>
						</ul>
					</li>

				</ul>
			</nav>
		</div>
	</div>
	</header>

	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt><i class="Hui-iconfont">&#xe62c;</i> 账号管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/accountinfo" data-title="账号信息" href="javaScript:;">账号信息</a></li>
						<li><a data-href="/editpwd" data-title="修改密码" href="javaScirpt:;">修改密码</a></li>
					</ul>
			</dd>
		</dl>
			<dl id="menu-picture">
				<dt><i class="Hui-iconfont">&#xe6b4;</i> 我的预约<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/myrese" data-title="查看我的预约" href="javascript:void(0)">查看我的预约</a></li>
						<li><a data-href="/appointmenting" data-title="我要预约" href="javascript:void(0)">我要预约</a></li>
					</ul>
				</dd>
			</dl>
			<dl id="menu-product">
				<dt><i class="Hui-iconfont">&#xe637;</i> 维修保养<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/userrese" data-title="维修保养记录" href="javascript:void(0)">维修保养记录</a></li>
						<li><a data-href="/prompt" data-title="维修保养提醒" href="javascript:void(0)">维修保养提醒</a></li>
						<li><a data-href="/schedule" data-title="查看维修保养进度" href="javascript:void(0)">查看维修保养进度</a></li>
						<li><a data-href="/details" data-title="维修保养明细" href="javascript:void(0)">维修保养明细</a></li>

				</ul>
			</dd>
		</dl>
			<dl id="menu-comments">
				<dt><i class="Hui-iconfont">&#xe70c;</i> 消费统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/cdocument" data-title="收费单据" href="javascript:void(0)">收费单据</a></li>
						<li><a data-href="/statistics/consume" data-title="消费统计" href="javascript:void(0)">消费统计</a></li>
						<li><a data-href="/mycomment" data-title="投诉管理" href="javascript:void(0)">投诉管理</a></li>
				</ul>
			</dd>
		</dl>

	</div>
	</aside>
	<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active">
						<span title="主页" data-href="/welcome">主页</span>
						<em></em></li>
			</ul>
		</div>
	</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
		</div>
	</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前 </li>
			<li id="closeall">关闭全部 </li>
	</ul>
	</div>
</div>
<script type="text/javascript" src="/static/h-ui/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.admin.js"></script>
<script type="text/javascript" src="/static/h-ui/js/jquery.contextmenu.r2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
$(function() {
    var roles = "车主";
    $.post('/isOwnerLogin/' + roles, function (data) {
        if (data.result == "success") {
            $("#bodyDiv").css("display","block");
        }else if (data.result == 'notLogin') {
            swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"}
                ,function(isConfirm){
                    if(isConfirm){
                        window.location = "home";
                    }else{
                        window.location = "home";
                    }
                })
        }
    });

})

</script>


</body>
</html>