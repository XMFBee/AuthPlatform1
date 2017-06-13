<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>短信发送提醒</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-field="checkin.userName" >车主姓名</th>
                <th data-field="user.userPhone" >车主手机号码</th>
                <%--<th data-field="sendMsg">短信内容</th>--%>
                <th data-field="sendTime" data-formatter="formatterDate">发送时间</th>
                <th data-field="sendCreatedTime" data-formatter="formatterDate">发送记录创建时间</th>
                <th data-formatter="formatterSendMsg" data-width="100">查看发送内容</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <%--<button type="button" class="btn btn-w-m btn-info" onclick="showAdd();">发送短信提醒</button>--%>
                <button type="button" class="btn btn-w-m btn-info" onclick="showMessageSend()">发送短信提醒</button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <div class="input-group" style="width:350px;float:right;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">车主姓名<span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">车主姓名</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">发送内容</a></li>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a onclick="onclikLi(this)">车主手机号码</a></li>--%>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javascript:;" onclick="blurredQuery()"><span
                            class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div>
                <!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" data-backdrop="static" keyboard:false style="overflow:auto; ">
    <div class="modal-dialog" style="width: 720px;height: auto;">
        <div class="modal-content" style="overflow:hidden;">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
            <form class="form-horizontal" id="addForm" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>添加短信发送提醒的信息</h4>
                </div>
                <br/>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">用户名：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--&lt;%&ndash;<input type="text" name="userId" placeholder="请选择用户" class="form-control">&ndash;%&gt;--%>
                        <%--<select id="addUserName" name="userId" class="form-control js-example-basic-multiple messageSend"--%>
                                <%--multiple="multiple" style="width:100%">--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">发送时间：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--<input id="addSendTime" name="sendTime" readonly class="layui-input">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">发送记录创建时间：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--<input id="addSendCreatedTime" name="sendCreatedTime" readonly class="layui-input">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">消息模板：</label>
                    <div class="col-sm-7">
                        <select name="messageSendText" onchange="messageTypeChange(this)"
                                class="form-control js-data-example-ajax">
                            <option value="模板1">模板1</option>
                            <option value="模板2">模板2</option>
                        </select>
                    </div>
                </div>
                <div id="sendMsgDiv" class="form-group">
                    <label class="col-sm-3 control-label">短信内容：</label>
                    <div class="col-sm-7">
                        <textarea id="addSendMsg" type="text" name="sendMsg" placeholder="请输入短信内容" style="height: 100px;"
                                  maxlength="500" class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default"
                                onclick="closeMessageSend()">关闭
                        </button>
                        <button id="addButton" class="btn btn-sm btn-success" type="button" onclick="addSubmit()">发送</button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="showMessageSendWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeMessageSendUserWin()"></span>
                <h4>请选择车主</h4>
                <table class="table table-hover" id="showMessageSendTable" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th data-checkbox="true"></th>
                        <th data-field="checkin.userName">车主姓名</th>
                        <th data-field="checkin.user.userEmail">车主邮箱</th>
                        <th data-field="checkin.user.userPhone">车主电话号码</th>
                    </tr>
                    </thead>
                </table>
                <div id="messageSendToolbar" class="btn-group">
                    <button type="button" class="btn btn-w-m btn-info" onclick="showMessageSendUser();">短信提醒车主</button>
                </div>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" onclick="closeMessageSendUserWin()">关闭
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<div id="sendMsgWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"
                      onclick="closeMessageSendModals()"></span>
                <h4>查看短信内容</h4>
                <form class="form-horizontal" id="sendMsgForm" method="post">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">短信内容：</label>
                        <div class="col-sm-7">
                        <textarea id="sendMsgText" type="text"  placeholder="请输入短信内容" style="height: 200px;"
                                  maxlength="500" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/custManage/messagesend.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/plugins/layui/layui.js" charset="utf-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<%--<script>--%>
    <%--layui.use('laydate', function(){--%>

        <%--var laydate = layui.laydate;--%>

        <%--var addSendTime = {--%>
            <%--format: 'yyyy-MM-dd hh:mm:ss',--%>
            <%--min: laydate.now(), //设定最小日期为当前日期--%>
            <%--max: '2099-12-30 23:59:59', //最大日期--%>
            <%--istime: true,--%>
            <%--istoday: false,--%>
            <%--festival: true--%>
        <%--};--%>

        <%--document.getElementById('addSendTime').onclick = function () {--%>
            <%--addSendTime.elem = this;--%>
            <%--laydate(addSendTime);--%>
        <%--}--%>

        <%--var addSendCreatedTime = {--%>
            <%--format: 'yyyy-MM-dd hh:mm:ss',--%>
            <%--max: '2099-12-30 23:59:59', //最大日期--%>
            <%--istime: true,--%>
            <%--istoday: false,--%>
            <%--festival: true--%>
        <%--};--%>

        <%--document.getElementById('addSendCreatedTime').onclick = function () {--%>
            <%--addSendCreatedTime.elem = this;--%>
            <%--laydate(addSendCreatedTime);--%>
        <%--}--%>

    <%--});--%>
<%--</script>--%>
</body>
</html>
