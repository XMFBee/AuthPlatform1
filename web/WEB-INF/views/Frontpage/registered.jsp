<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号登录</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/sweetalert.css">
<link rel="stylesheet" href="/static/css/table/table.css">
<link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
<link rel="stylesheet" href="/static/css/registeredStyle.css">
<style>
    .nav-tabs li.bv-tab-error>a{color:#555}
</style>
<body>
    <div class="main">
        <%--顶部导航栏--%>
        <div class="nav nav-first">
            <div class="nav-left">
                <ul class="nav-left-ul">
                    <c:choose>
                        <c:when test="${sessionScope.frontUser != null}">
                            <c:if test="${sessionScope.frontUser.userName != null}">
                                <li id="placelogin">欢迎您，${sessionScope.frontUser.userName}</li>
                                <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                                <a href="/home" id="loginreg"><li>返回首页</li></a>
                                <div class="clearfix"></div>
                            </c:if>
                            <c:if test="${sessionScope.frontUser.userName == null}">
                                <li id="placelogin">欢迎您，${sessionScope.frontUser.userPhone}</li>
                                <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                                <a href="/home" id="loginreg"><li>返回首页</li></a>
                                <div class="clearfix"></div>
                            </c:if>
                        </c:when>

                        <c:otherwise>
                            <li id="placelogin">欢迎您，请登录</li>
                            <a href="/home" id="loginreg"><li>返回首页</li></a>
                            <div class="clearfix"></div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>

        <div class="login-content" id="background-img">
            <div class="content wow fadeInLeft animated" id="login" onkeydown="keydown()">
                <div class="form-box">
                    <div class="form-logo">
                        <h2 style="color: white;margin-top: 0;">登录</h2>
                    </div>
                    <div class="form-content">
                        <form class="form" id="loginForm" >
                            <div class="form-group">
                                <input type="text" name="userEmail" id="email" class="form-control" placeholder="请输入邮箱/手机号">
                            </div>
                            <div class="form-group">
                                <input type="password" name="userPwd" class="form-control" placeholder="请输入密码">
                            </div>
                            <div class="form-group">
                                <div class="row">
                                    <div class="col-md-3 col-sm-3" style="width: 29.6%">
                                    <a href="javascript:;">
                                        <img style="vertical-align:middle; height:35px;" src="/captcha" onclick="this.src='/captcha?time=Math.random();'"></a>
                                    </div>
                                    <div class="col-md-8 col-sm-8" style="padding-right: 0px;"><input type="text" name="checkCode"  placeholder="验证码" class="form-control"></div>
                                </div>

                            </div>
                            <div class="form-group">
                                <div>
                                <%--<label style="overflow: hidden;display: inline;"><input type="checkbox" value="记住账号" style="position: relative;top:3px;width: 16px;height: 16px;"> <span style="font-size: 16px;">记住账号</span></label>--%>
                                    <label><a class="bounceInDown" style="font-size: 16px;margin-left: 10px;" href="javaScript:;" onclick="showAdd();">忘记密码？</a></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="button" class="btn btn-success btn-block" value="登录" id="loginButton" onclick="loginSubmit()">登录</button>
                                <button type="reset" class="btn btn-info btn-block" onclick="reg()" value="没有账号，立即注册">没有账号，立即注册</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
                <div class="content wow fadeInRight animated" id="reg" name="regs" onkeydown="keydownres()">
                    <div class="form-box">
                        <div class="form-logo">
                            <h2 style="color: white">注册账号</h2>
                        </div>
                        <div class="form-content">
                            <form class="form" id="regform">
                                <div class="form-group">
                                    <input name="userPhone" type="text" id="phone" class="form-control" placeholder="请输入您的手机号" >
                                    <span class="caveat" id="phonecode-caveat"></span>
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-xs-7 col-md-6">
                                            <input name="phonecode"  type="text" id="phone-code" class="form-control" style="width: 110%;" placeholder="请输入验证码" >
                                        </div>
                                        <div class="col-xs-5 col-md-5">
                                            <a id="codeId" class="btn btn-info" style="margin-bottom: 0;width:123%;" onclick="sendCode(this);">获取短信验证码</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input name="userPwd" type="password" id="password1" class="form-control" placeholder="请输入密码" >
                                </div>
                                <div class="form-group">
                                    <input name="password2" type="password" id="password2" class="form-control" placeholder="请再次输入密码" >
                                </div>
                                <div class="form-group">
                                    <button type="button" id="resbtn" class="btn btn-success btn-block" onclick="regSubmit()">确认注册</button>
                                    <button type="reset" class="btn btn-info btn-block" onclick="reg()">登录</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
        </div>

            <div id="addWindow" class="modal fade" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static"
                 keyboard:false>
                <div class="modal-dialog" style="width: 50%;">
                    <div class="modal-content">
                        <div class="modal-body">
                             <span class="glyphicon glyphicon-remove closeModal" onclick="closem()"></span>
                            <div class="modal-header" style="overflow:auto;">
                                <h4>找回密码</h4>
                            </div>
                            <div class="tabs-container">
                                <ul class="nav nav-tabs">
                                    <li class="active"><a data-toggle="tab" href="#tab-1"> 手机验证</a></li>
                                    <li class=""><a data-toggle="tab" href="#tab-2">邮箱验证</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div id="tab-1" class="tab-pane active">
                                        <div class="panel-body">
                                            <form id="addForm" role="form" class="form-horizontal">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">手 机：</label>
                                                        <div class="col-sm-7">
                                                            <div class="fl"><input id="phoneInput" type="text" placeholder="请输入手机号码" class="form-control" name="rtphone"/></div>
                                                            <span class="caveat" id="phoneCodePrint"></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">验证码：</label>
                                                        <div class="col-sm-4">
                                                            <input id="codePhone" type="text" name="phoneCodeInput" placeholder="请输入验证码" class="form-control"/>
                                                        </div>
                                                        <button id="phoneCode" type="button" class="btn btn-primary col-sm-3"
                                                                style="width: 146px;margin: 0" onclick="phoneCodeTime(this)">发送验证码</button>
                                                    </div>
                                                <div id="pwdbox" class="zoomIn">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">新密码：</label>
                                                        <div class="col-sm-7">
                                                            <div class="fl"><input id="pwdPhone" type="password" placeholder="请输入新密码" class="form-control" name="rtpwd1" value=""
                                                                                   class="ipt email"/></div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">再次输入密码：</label>
                                                        <div class="col-sm-7">
                                                            <div class="fl"><input type="password" class="form-control" name="rtpwdagin" placeholder="请再次输入密码"
                                                                                   class="ipt email"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="reset" name="reset" style="display: none;"/>
                                            </form>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" onclick="closem()">关闭</button>
                                                <button id="phoneButton" type="button" onclick="rtPhoneSubmit()" class="btn btn-success">确认</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="tab-2" class="tab-pane">
                                        <div class="panel-body">
                                            <form id="addForm1" role="form" class="form-horizontal">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">邮 箱：</label>
                                                        <div class="col-sm-7">
                                                            <input id="emailInput" type="text" class="form-control" placeholder="请输入邮箱" name="rtemail"/>
                                                            <span class="caveat" id="emailCodePrint"></span>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">验证码：</label>
                                                        <div class="col-sm-4">
                                                            <input id="codeEmail" type="text" name="emailCodeInput" placeholder="请输入验证码" class="form-control"/>
                                                        </div>
                                                        <button id="emailCode" type="button" value="发送验证码" class="btn btn-primary col-sm-3"
                                                                style="width: 146px;margin: 0" onclick="emailCodes(this)">发送验证码</button>
                                                    </div>
                                                <div id="pwdbox1" class="zoomIn">
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">新密码：</label>
                                                        <div class="col-sm-7">
                                                            <div class="fl"><input id="pwdEmail" type="password" class="form-control" placeholder="请输入新密码" name="rtpwd2" value=""
                                                                                   class="ipt email"/></div>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-3 control-label">再次输入密码：</label>
                                                        <div class="col-sm-7">
                                                            <div class="fl"><input type="password" class="form-control" placeholder="请再次输入密码" name="rtpwdagin1" value=""
                                                                                   class="ipt email"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <input type="reset" name="reset" style="display: none;"/>
                                            </form>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" onclick="closem()">关闭</button>
                                                <button id="emailButton" type="button" onclick="rtEmailSubmit()" class="btn btn-success">确认</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/backstage/user/frontLogin.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/registeredgen.js"></script>
</body>
</html>
