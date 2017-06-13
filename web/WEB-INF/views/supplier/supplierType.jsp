<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>供应商类型管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;"  >
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-width="110" data-field="supplyTypeName">供应商类型名称</th>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <th data-width="130" data-field="company.companyName">供应商类型所属公司</th>
                </shiro:hasAnyRoles>
                <th data-width="180" data-field="supplyTypeDes">供应商类型描述内容</th>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                    <th data-width="100" data-field="supplyTypeStatus" data-formatter="showStatusFormatter">
                        记录状态
                    </th>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                    <th data-width="100" data-field="supplyTypeStatus" data-formatter="statusFormatter">
                        操作
                    </th>
                </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                <button id="searchDisable" type="button" class="btn btn-danger" onclick="searchDisableStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用供应商类型
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                <button id="searchRapid" type="button" class="btn btn-success" onclick="searchRapidStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用供应商类型
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->

<div id="addWindow" class="modal fade" style="overflow-y:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width:60%">
        <div class="modal-content" >
            <div class="modal-body" onkeydown="keydown('addButton')">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow','addForm')" ></span>
                    <form class="form-horizontal"  role="form" id="addForm" method="post">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加供应商类型信息</h4>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">供应商类型名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="supplyTypeName" id="addSTName" placeholder="请输入供应商类型名称" class="form-control">
                        </div>
                    </div>
                   <%-- <div class="form-group">
                        <label class="col-sm-3 control-label">供应商类型所属公司：</label>
                        <div class="col-sm-7">
                            <select id="addCompanyName" class="js-example-tags company" name="companyId" style="width:100%">
                            </select>
                        </div>
                    </div>--%>
                     <div class="form-group">
                            <label class="col-sm-3 control-label">供应商类型描述：</label>
                            <div class="col-sm-7">
                            <textarea type="text" name="supplyTypeDes" placeholder="请输入供应商类型描述内容" style="height:100px;"
                                      class="form-control"></textarea>
                            </div>
                      </div>
                    <div class="modal-footer" >
                        <span id="addError"></span>
                        <button type="button" class="btn btn-default" onclick="closeModals('addWindow','addForm')">关闭 </button>
                        <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-success">添加</button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div id="editWindow" class="modal fade" style="overflow-y:scroll" data-backdrop="static">
    <div class="modal-dialog" style="width:60%">
        <div class="modal-content">
            <div class="modal-body" onkeydown="keydown('addButton')">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow','editForm')"></span>
                <form  class="form-horizontal"  id="editForm" method="post">
                    <input type="hidden" name="supplyTypeId" define="supplyType.supplyTypeId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改供应商类型信息</h4>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">供应商类型名称：</label>
                        <div class="col-sm-7">
                            <input type="text" id="editSTName" name="supplyTypeName"  define="supplyType.supplyTypeName"  placeholder="请输入供应商类型" class="form-control">
                        </div>
                    </div>
                   <%-- <div class="form-group">
                        <label class="col-sm-3 control-label">供应商类型所属公司：</label>
                        <div class="col-sm-7">
                            <select id="editCompanyName" class="js-example-tags company" define="supply.companyId" name="companyId" style="width:100%">
                            </select>
                        </div>
                    </div>--%>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">供应商类型描述：</label>
                        <div class="col-sm-7">
                            <input type="text" name="supplyTypeDes" define="supplyType.supplyTypeDes" placeholder="请输入供应商类型描述内容"
                                   style="height:100px;" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer" >
                        <span id="editError"></span>
                        <button type="button" class="btn btn-default" onclick="closeModals('editWindow','editForm')">关闭</button>
                        <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-success">保存</button>
                    </div>
                </form>
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
<script src="/static/js/backstage/supplier/supplierType.js"></script>
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>

</body>
</html>