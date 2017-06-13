<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>保养项目管理</title>
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
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true" data-field="status"></th>
                <th data-width="150" data-field="maintainName">保养项目名称</th>
                <th data-width="150" data-field="maintainHour">保养项目工时</th>
                <th data-width="150" data-field="maintainMoney">保养项目基础费用</th>
                <th data-width="150" data-field="maintainManHourFee">保养项目工时费</th>
                <th data-width="150" data-field="maintainDes">保养项目描述</th>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <th data-width="150" data-field="company.companyName">保养项目所属公司</th>
                </shiro:hasAnyRoles>
                <th data-width="150" data-field="maintainStatus" data-formatter="showStatusFormatter">保养项目状态</th>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                    <th data-width="150" data-field="maintainStatus" data-formatter="statusFormatter">
                        操作
                    </th>
                </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles
                    name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用保养记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles
                    name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用保养记录
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
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_addAcc" type="button" class="btn btn-default" onclick="showAddacc();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加配件
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_editAcc" type="button" class="btn btn-default" onclick="showDetail();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看配件
                </button>
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<div id="accWindow" class="modal fade" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"
                      onclick="closeModals('accWindow', 'accForm')"></span>
                <form role="form" class="form-horizontal" id="accForm">
                    <input type="hidden" name="maintainId" define="MaintainFixMap.maintainId">
                    <input type="hidden" id="addaccId" name="accId">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>选择配件</h4>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件名称：</label>
                        <div class="col-sm-9">
                            <input id="addacc" class="form-control" placeholder="请选择配件" readonly="true"
                                   style="width:52%;">
                            </input>
                            <button type="button" class="btn btn-default" onclick="showAcc('accWindow');">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看配件
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件数量：</label>
                        <div class="col-sm-7">
                            <input type="number" name="accCount" placeholder="请输入配件数量" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="modal-footer" style="border: none">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" onclick="closeModals('accWindow', 'accForm')">关闭
                        </button>
                        <button id="accButton" type="button" onclick="accaddSubmit()" class="btn btn-success">添加
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div id="accEditWindow" class="modal fade" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"
                      onclick="closeModals('accEditWindow', 'accEditForm')"></span>
                <form role="form" class="form-horizontal" id="accEditForm">
                    <input type="hidden" id="editmaintainId" name="mainAccId">
                    <input type="hidden" id="editaccId" name="accId">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>选择配件</h4>
                    </div>
                    <hr>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件名称：</label>
                        <div class="col-sm-9">
                            <input id="editaccName" class="form-control" placeholder="请选择配件" readonly="true"
                                   style="width:52%;">
                            </input>
                            <button type="button" class="btn btn-default" onclick="showEidtAcc('accEditWindow');">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看配件
                            </button>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件数量：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editaccCount" name="accCount" placeholder="请输入配件数量" min="1" class="form-control"
                                   style="width:100%"/>
                        </div>
                    </div>

                    <div class="modal-footer" style="border: none">
                        <button type="button" class="btn btn-default"
                                data-dismiss="modal" onclick="closeModals('accEditWindow', 'accEditForm')">关闭
                        </button>
                        <button id="accEidtButton" type="button" onclick="accEditaddSubmit()" class="btn btn-success">修改
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--所有配件--%>
<div class="modal fade" id="accAllWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width:90%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeWindow()"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h4>选择配件</h4>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3 control-label">配件类型：</label>
                    <div class="col-sm-7">
                        <select id="addAccessories" class="js-example-tags AccessoriesType"
                                onchange="queryByTypeId(this)" name="accId" style="width:100%">
                        </select>
                    </div>
                </div>
                <table class="table table-hover" id="table1">
                    <thead>
                    <tr>
                        <th data-field="state" data-radio="true"></th>
                        <th data-field="accName">
                            配件名称
                        </th>
                        <th data-field="accIdle">
                            配件库可用数
                        </th>
                        <th data-field="accSalePrice">
                            配件单价
                        </th>
                    </tr>
                    </thead>
                </table>
                <div class="modal-footer" style="border: none">
                    <button id="closeButton" type="button" class="btn btn-default"
                            onclick="accAllcloseWindow()">关闭
                    </button>
                    <button id="itemButton" type="button" onclick="itemSubmit()" class="btn btn-success">确定
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="accEidtAllWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width:90%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeEidtWindow()"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h4>选择配件</h4>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3 control-label">配件类型：</label>
                    <div class="col-sm-7">
                        <select id="editAccessories" class="js-example-tags AccessoriesType"
                                onchange="queryByTypeId2(this)" name="accId" style="width:100%">
                        </select>
                    </div>
                </div>
                <table class="table table-hover" id="table2">
                    <thead>
                    <tr>
                        <th data-radio="true"></th>
                        <th data-field="accName">
                            配件名称
                        </th>
                        <th data-field="accIdle">
                            配件库可用数
                        </th>
                        <th data-field="accSalePrice">
                            配件单价
                        </th>
                    </tr>
                    </thead>
                </table>
                <div class="modal-footer" style="border: none">
                    <button id="closeEidtButton" type="button" class="btn btn-default"
                            onclick="accEditAllcloseWindow()">关闭
                    </button>
                    <button id="itemEditButton" type="button" onclick="itemEditSubmit()" class="btn btn-success">确定
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="detailWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 90%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closedetailWindow()"></span>
                <form role="form" class="form-horizontal" id="detailForm">
                    <h3 class="m-t-none m-b">此保养项目记录下的所有配件</h3>
                    <hr>
                    <table class="table table-hover" id="detailTable">
                        <thead>
                        <tr>
                            <th data-radio="true" data-field="status"></th>
                            <th data-field="maintainFix.maintainName">保养项目名称</th>
                            <th data-field="maintainFix.maintainHour">保养项目工时</th>
                            <th data-field="maintainFix.maintainMoney">保养项目基础费用</th>
                            <th data-field="maintainFix.maintainManHourFee">保养项目工时费</th>
                            <th data-field="maintainFix.maintainDes">保养项目描述</th>
                            <th data-field="company.companyName">保养项目所属公司</th>
                            <th data-field="accessories.accName">配件名称</th>
                            <th data-field="accCount">配件数量</th>
                        </thead>
                    </table>
                    <div id="detailToolbar" class="btn-group">
                        <button id="btn_editDetail" type="button" class="btn btn-default" onclick="showdetai();">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改配件
                        </button>
                    </div>
                    <div class="modal-footer" style="border: none">
                        <button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" style="overflow:auto;" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 65%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"
                      onclick="closeModals('addWindow', 'addForm')"></span>
                <form role="form" class="form-horizontal" id="addForm">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加保养项目的信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="maintainName" placeholder="请输入保养项目的名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目工时：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainHour" placeholder="请输入保养项目工时" min="1" class="form-control"
                                   style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目基础费用：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainMoney" placeholder="请输入保养项目基础费用" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目工时费：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainManHourFee" placeholder="请输入保养项目工时费" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目描述：</label>
                        <div class="col-sm-7">
                        <textarea type="text" name="maintainDes" placeholder="请输入保养项目描述" style="height: 100px;"
                                  class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('addWindow', 'addForm')">关闭</button>
                            <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-sm btn-success">
                                添加
                            </button>
                            <input type="reset" name="reset" style="display: none;"/>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog" style="width: 65%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
                <form form role="form" class="form-horizontal" id="editForm">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改保养项目的信息</h4>
                    </div>
                    <input type="hidden" name="maintainId" define="MaintainFixMap.maintainId">
                    <input type="hidden" name="maintainStatus" define="MaintainFixMap.maintainStatus">
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="maintainName" define="MaintainFixMap.maintainName"
                                   placeholder="请输入保养项目名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目工时：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainHour" define="MaintainFixMap.maintainHour"
                                   placeholder="请输入保养项目工时" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目基础费用：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainMoney" define="MaintainFixMap.maintainMoney"
                                   placeholder="请输入保养项目基础费用" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目工时费：</label>
                        <div class="col-sm-7">
                            <input type="number" name="maintainManHourFee" define="MaintainFixMap.maintainManHourFee"
                                   placeholder="请输入保养项目工时费" min="1" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养项目描述：</label>
                        <div class="col-sm-7">
                                <textarea type="textarea" class="form-control" placeholder="请输入保养项目描述"
                                          define="MaintainFixMap.maintainDes" name="maintainDes"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('editWindow', 'editForm')">关闭</button>
                            <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-sm btn-success">
                                保存
                            </button>
                        </div>
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
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/form/jquery.validate.js"></script>
<script src="/static/js/backstage/basicInfoManage/maintainItem.js"></script>
</body>
</html>
