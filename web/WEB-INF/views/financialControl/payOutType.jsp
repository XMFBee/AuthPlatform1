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

    <title>支出类型管理</title>
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
                <th data-field="outTypeName">支出类型</th>
                <th data-field="createTime" data-formatter="formatterDate">
                    类型创建时间
                </th>
                <th data-formatter="statusFormatter">记录状态</th>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                    <th data-formatter="openStatusFormatter">操作</th>
                </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="searchDisable" type="button" class="btn btn-danger" onclick="searchDisableStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用支出类型
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="searchRapid" type="button" class="btn btn-success" onclick="searchRapidStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用支出类型
                </button>
            </shiro:hasAnyRoles>

        </div>

    </div>
</div>

<!-- 添加弹窗 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="add" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('add', 'addForm')"></span>
                    <form class="form-horizontal" role="form" id="addForm" method="post">
                        <div class="modal-header" style="overflow:auto;">
                            <h4>添加支出类型的信息</h4>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">支出类型名称：</label>
                            <div class="col-sm-7">
                                <input type="text" id="outTypeName" name="outTypeName" placeholder="请输入支出类型名称"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-8">
                                <button type="button" class="btn btn-default" data-dismiss="modal"
                                        onclick="closeModals('add', 'addForm')">关闭
                                </button>
                                <button id="addButton" class="btn btn-sm btn-success" onclick="addSubmit()"
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

<!-- 修改弹窗 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="edit" role="form" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="container">
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal"
                          onclick="closeModals('edit', 'editForm')"></span>
                    <form class="form-horizontal" id="editForm">
                        <input type="hidden" name="outTypeId" define="outGoingType.outTypeId">
                        <input type="hidden" name="outTypeStatus" define="outGoingType.outTypeStatus">
                        <div class="modal-header" style="overflow:auto;">
                            <h4>修改支出类型的信息</h4>
                        </div>
                        <br/>
                        <div class="form-group">
                            <label class="col-sm-3 control-label">支出类型名称：</label>
                            <div class="col-sm-7">
                                <input type="text" id="outUpdateTypeName" define="outGoingType.outTypeName"
                                       name="outTypeName" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-8">
                                <button type="button" class="btn btn-default" data-dismiss="modal"
                                        onclick="closeModals('edit', 'editForm')">关闭
                                </button>
                                <button id="editButton" class="btn btn-sm btn-success" onclick="editSubmit()"
                                        type="button">
                                    保 存
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
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
<script src="/static/js/backstage/financialControlJS/payOutType.js"></script>
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>

</body>
</html>
