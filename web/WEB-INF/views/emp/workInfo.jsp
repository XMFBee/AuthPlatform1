<%@ page import="com.gs.bean.User" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>员工工单管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">

<%--<link rel="stylesheet" href="/static/css/table/table.css">--%>
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-body">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <th data-field="company" data-formatter="formatterCompany">
                    公司
                </th>
                </shiro:hasAnyRoles>
                <th data-field="user" data-formatter="formatterUserNickName">
                    指派用户
                </th>
                <th data-field="workAssignTime" data-formatter="formatterDate">
                    工单指派时间
                </th>
                <th data-field="workCreatedTime"  data-formatter="formatterDateTime">
                    工单创建时间
                </th>
                <th data-field="workStatus" data-formatter="statusFormatter">
                    工单状态
                </th>
                <th data-field="showAccs" data-formatter="showCell" data-events="showCellEvn">
                    查看
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <%--<button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">--%>
                <%--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>指派员工--%>
            <%--</button>--%>
            <button id="searchRapid" type="button" class="btn btn-success" onclick="showComplete();">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>已完成工单记录
            </button>
            <button id="searchDisable" type="button" class="btn btn-danger" onclick="showDisable();">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>未完成工单记录
            </button>


            <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                <div class="input-group-btn">
                    <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                            data-toggle="dropdown">员工<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司<span class="caret"></span></shiro:hasAnyRoles></button>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">员工/汽车公司</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">员工</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">汽车公司</a></li>
                        </ul>
                    </shiro:hasAnyRoles>
                </div><!-- /btn-group -->
                <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                <a href="javaScript:;" onclick="blurredQuery()"><span
                        class="glyphicon glyphicon-search search-style"></span></a>
                </input>
            </div><!-- /input-group -->


        </div>
    </div>
</div>

<%-- 物料明细弹窗 --%>
<div class="modal fade" id="accsInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="margin-right:0;width:95%;">
                <span class="glyphicon glyphicon-remove closeModal"  data-dismiss="modal" aria-hidden="true"></span>
                <h4 class="modal-title" id="myModalLabel">工单配件明细</h4>
            </div>
            <div class="modal-body">
                <table id="workInfoAccDetailTable" style="table-layout: fixed">
                    <thead>
                    <tr >
                        <th data-width="50"  data-field="accessories.accName">配件名称</th>
                        <th data-width="110"  data-formatter="accInfoFormat">所需数量</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal" >关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" aria-hidden="true" style="overflow:auto; ">
    <div class="modal-dialog" style="height: auto; overflow:auto;">
        <div class="modal-content" style="overflow:auto;">
            <form class="form-horizontal" role="form" id="editForm" method="post">
                <input type="hidden" name="workId" define="WorkInfo.workId"/>
                <input type="hidden" name="workStatus" define="WorkInfo.workStatus"/>
                <div class="modal-header" style="overflow:auto;">
                    <h4>请填写订单信息</h4>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">指派用户：</label>
                    <div class="col-sm-7">
                       <%-- <select id="editUserId" class="js-example-tags user" define="user.userNickname" name="userId" style="width:100%;">
                        </select>--%>
                        <select id="editUserId" class="js-example-tags user" define="user.userNickname" name="userId" style="width:100%;"></select>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-primary">修改</button>
                    </div>
                </div>
            </form>

        </div><!-- /.modal-content -->
    </div>
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

<% String userId = ((User)session.getAttribute("user")).getUserId(); %>
<script>
    var userId = "<%= userId%>";
</script>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/backstage/emp/workInFo.js"></script>
</body>
<script>

</script>
</html>
