<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">

    <title>支出管理</title>
</head>
<body>

<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-formatter="ioTypeFormatter">
                    收支类型
                </th>
                <th data-field="inOutMoney">
                    收支金额
                </th>
                <th data-field="user.userName">
                    创建人
                </th>
                <th data-field="inOutCreatedTime" data-formatter="formatterDate">
                    创建时间
                </th>
                <th data-field="inOutStatus" data-formatter="statusFormatter">
                    记录状态
                </th>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                    <th data-field="inOutStatus" data-formatter="openStatusFormatter">
                        操作
                    </th>
                </shiro:hasAnyRoles>

            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="o_add" type="button" class="btn btn-default" onclick="outAddWin();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加其它支出记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="i_add" type="button" class="btn btn-default" onclick="inAddWin();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加其它收入记录
                </button>
            </shiro:hasAnyRoles>
<%--            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>--%>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="searchDisable" type="button" class="btn btn-danger" onclick="searchDisableStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用收支记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="searchRapid" type="button" class="btn btn-success" onclick="searchRapidStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用收支记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">收入类型<span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">收入类型</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">支出类型</a></li>
                            <li class="divider"></li>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javaScript:;" onclick="blurredQuery()"><span
                            class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div>
                <!-- /input-group -->
            </shiro:hasAnyRoles>
            <button id="searchRapid" type="button" class="btn btn-success" onclick="returnButton();">
                <span aria-hidden="true"></span>还原
            </button>
        </div>
    </div>
</div>

<!-- 添加支出类型 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="addOutWin" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal"  onclick="closeModals('addOutWin', 'addOutForm')"></span>
                <form class="form-horizontal" role="form" id="addOutForm" method="post">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加其它支出记录的信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">支出类型：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="outTypeId" readonly="true" name="outTypeId">
                            <input type="text" onclick="openCheckOutType();" readonly="true" id="outTypeName"
                                   name="outTypeName" placeholder="请选择支出类型" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">支出金额：</label>
                        <div class="col-sm-7">
                            <input type="number" name="inOutMoney" placeholder="请输入支出金额" class="form-control">
                        </div>
                    </div>
                  <%--  <div class="form-group">
                        <label class="col-sm-3 control-label">创建人：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="userId" readonly="true" name="inOutCreatedUser">
                            <input onclick="checkAppointment();" type="text" readonly="true" name="inOutCreatedUserName"
                                   id="userName" placeholder="请输入支出记录创建人" class="form-control">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('addOutWin', 'addOutForm')">关闭</button>
                            <button class="btn btn-sm btn-success" id="addOutButton" onclick="addOutSubmit()"
                                    type="button">添加
                            </button>
                        </div>
                    </div>
                </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--支出管理弹窗显示--%>
<div id="outWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择支出类型</h3>
                        <table class="table table-hover" id="outTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true"></th>
                                <th data-field="outTypeName">
                                    支出类型
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="closeOutTypeWin()">关闭
                            </button>
                            <input type="button" class="btn btn-success" onclick="checkOutType()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<%--人员管理窗弹窗显示
<div id="personnelWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择人员</h3>
                        <table class="table table-hover" id="appTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true" data-field="status"></th>
                                <th data-field="userName">姓名</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="closePersonnelWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="checkPersonnel()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>--%>

<%--收入管理弹窗显示--%>
<div  id="inWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择收入类型</h3>
                        <table class="table table-hover" id="inTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true"></th>
                                <th data-field="inTypeName">
                                    收入类型
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="inCloseInTypeWin()">关闭
                            </button>
                            <input type="button" class="btn btn-success" onclick="inCheckInType()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%--
&lt;%&ndash;人员管理窗弹窗显示&ndash;%&gt;
<div id="inPersonnelWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择人员</h3>
                        <table class="table table-hover" id="inAppTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true" data-field="status"></th>
                                <th data-field="userName">姓名</th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="inClosePersonnelWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="inCheckPersonnel()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

--%>

<!-- 添加收入类型 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="addInWin" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal"  onclick="closeModals('addInWin', 'addInForm')"></span>
                <form class="form-horizontal" id="addInForm" method="post">
                    <input type="reset" name="reset" style="display: none;"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加其它收入记录的信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收入类型：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="inTypeId" readonly="true" name="inTypeId">
                            <input type="text" onclick="inOpenCheckInType();" readonly="true" id="inTypeName"
                                   name="inTypeName" placeholder="请选择收入类型" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收入金额：</label>
                        <div class="col-sm-7">
                            <input type="number" name="inOutMoney" placeholder="请输入收入金额" class="form-control">
                        </div>
                    </div>
                   <%-- <div class="form-group">
                        <label class="col-sm-3 control-label">创建人：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="inUserId" readonly="true" name="inOutCreatedUser">
                            <input onclick="inCheckAppointment();" type="text" readonly="true"
                                   name="inOutCreatedUserName" id="inUserName" placeholder="请输入收入记录创建人"
                                   class="form-control">
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('addInWin', 'addInForm')">关闭</button>
                            <button class="btn btn-sm btn-success" type="button" id="addInButton"
                                    onclick="addInSubmit()">添加
                            </button>
                        </div>
                    </div>
                </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<%--支出管理弹窗显示--%>
<div id="updateOutWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择支出类型</h3>
                        <table class="table table-hover" id="updateOutTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true"></th>
                                <th data-field="outTypeName">
                                    收支类型
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="updateCloseOutTypeWin()">关闭
                            </button>
                            <input type="button" class="btn btn-success" onclick="updateCheckOutType()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<%--<div id="updatePersonnelWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择人员</h3>
                        <table class="table table-hover" id="updateAppTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true" data-field="status"></th>
                                <th data-field="userName">姓名</th>

                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="updateClosePersonnelWin()">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="updateCheckPersonnel()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>--%>

<%--修改收支记录--%>
<div id="updateInWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择支出类型</h3>
                        <table class="table table-hover" id="updateInTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true"></th>
                                <th data-field="inTypeName">
                                    收入类型
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" onclick="updateInCloseInTypeWin()">关闭
                            </button>
                            <input type="button" class="btn btn-success" onclick="updateInCheckInType()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<!-- 修改弹窗 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="editIOWin" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal"  onclick="closeModals('editIOWin', 'editIOForm')"></span>
                <form class="form-horizontal" role="form" id="editIOForm" method="post">
                    <input type="reset" name="reset" style="display: none;"/>
                    <input type="hidden" define="io.inOutId" name="inOutId">
                    <input type="hidden" define="io.inOutCreatedUser" name="inOutCreatedUser">
                    <input type="hidden" define="io.companyId" name="companyId">
                    <div class="modal-header" style="overflow:auto;">
                        <p>修改收支记录的信息</p>
                    </div>

                    <div id="inTypeDiv" class="form-group">
                        <label class="col-sm-3 control-label">收入类型：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="updateInTypeId" define="io.incomingType.inTypeId" name="inTypeId">
                            <input define="io.incomingType.inTypeName" id="inType" type="text" readonly="true"
                                   class="form-control">
                            <button type="button" class="btn btn-default" onclick="updateInOpenCheckInType();">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>选择收入类型
                            </button>
                        </div>
                    </div>


                    <div id="outTypeDiv" class="form-group">
                        <label class="col-sm-3 control-label">支出类型：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="updateOutTypeId" define="io.outgoingType.outTypeId"
                                   name="outTypeId">
                            <input define="io.outgoingType.outTypeName" type="text" id="outType" readonly="true"
                                   class="form-control">
                            <button type="button" class="btn btn-default" onclick="updateOpenCheckOutType();">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>选择支出类型
                            </button>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">收支金额：</label>
                        <div class="col-sm-7">
                            <input type="text" define="io.inOutMoney" name="inOutMoney" class="form-control">
                        </div>
                    </div>
                  <%--  <div class="form-group">
                        <label class="col-sm-3 control-label">收支记录创建人：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="updateUserId" define="io.inOutCreatedUser" name="inOutCreatedUser">
                            <input define="io.user.userName" id="updateUserName" type="text" readonly="true"
                                   class="form-control">
                            <button type="button" class="btn btn-default" onclick="updateCheckAppointment();">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>选择创建人
                            </button>
                        </div>
                    </div>--%>
                    <div class="modal-footer">
                        <span id="editError"></span>
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" onclick="closeModals('editIOWin', 'editIOForm')">关闭
                        </button>
                        <button type="button" id="editButton" onclick="editSubmit()" class="btn btn-primary btn-sm">保存
                        </button>
                    </div>
                </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除弹窗 -->
<div class="modal fade" id="del" aria-hidden="true">
    <div class="modal-dialog">
        <form action="/table/edit" method="post">
            <div class="modal-content">
                <input type="hidden" id="delNoticeId"/>
                <div class="modal-footer" style="text-align: center;">
                    <h2>确认删除吗?</h2>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="sumbit" class="btn btn-primary" onclick="del()">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 提示弹窗 -->
<div class="modal fade" id="tanchuang" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                提示
            </div>
            <div class="modal-body">
                请先选择某一行
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/financialControlJS/piRecording.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>

</body>
</html>
