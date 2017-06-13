<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>人员角色管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/systemManage/bootstrap.vertical-tabs.css">
    <link rel="stylesheet" href="/static/css/systemManage/icon.css">
    <link rel="stylesheet" href="/static/css/systemManage/gijgo.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <style>
        .gj-checkbox-md input[type="checkbox"]:checked + span::after {
            top: 0px;
        }
        .gj-checkbox-md input[type="checkbox"]:indeterminate + span::after {
            top:0px;
        }
        h3{
            font-weight: bold;
        }
        li[data-id|=module]>div[data-role="wrapper"]{
            font-weight: bold;
        }

        #addForm .form-control[readonly] {
            background-color:#fff;
            border:none;
            box-shadow: none;
        }
        .close {
            right:20px;
            top: 10px;
            position: relative;
        }
    </style>
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>
<div class="head">
</div>
<div class="container">
    <div class="panel-body" style="padding-bottom:0px;" >
        <div class="col-xs-3">
            <ul class="nav nav-tabs tabs-left" id="bar">
            </ul>
        </div>
        <div class="col-xs-9" >
            <div class="tab-content" id="panel">
                <div class="tab-pane active" id="home">
                    <div class="panel title" style="margin-bottom:1px">
                        <h4 class="col-md-4"></h4>
                        <div class="col-md-8" >
                            <div style="float: right;">
                                <button type="button" class="btn btn-default" style="margin-right:5px;" onclick="showEdit()"><span class="glyphicon glyphicon-edit" style="margin-right:5px"></span>修改</button>
                                <%--<button type="button" class="btn btn-default" style="margin-right:5px;" onclick="showAdd()"><span class="glyphicon glyphicon-plus" style="margin-right:5px"></span>添加</button>--%>
                                <%--<button type="button" class="btn btn-default" onclick="showDel()"><span class="	glyphicon glyphicon-minus" style="margin-right:5px"></span>回收</button>--%>
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <hr />
                    <div class="panel permissions">
                        <div class="panel-heading">
                            <h4>拥有的权限</h4>
                            <span class="glyphicon glyphicon-edit" style="float:right" onclick="showEditPermission()">修改</span>
                        </div>
                        <div class="panel-body" id="permissionPanel">
                            <div id="dnyTree" ></div>
                        </div>
                    </div>
                </div>
               <%-- <div class="tab-pane" id = "recyclebin">
                    <div class="panel" style="margin-bottom:1px;">
                        <h3>这是回收站</h3>
                    </div>
                    <div class="panel" style="margin-bottom:1px">
                        <table class="table table-hover" id="recycleTable">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="roleName">
                                    角色名称
                                </th>
                                <th data-field="roleDes">
                                    角色简介
                                </th>
                                <th data-field="todoCell" data-formatter="todoCell">
                                    操作
                                </th>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>--%>
            </div>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addModal" aria-hidden="true" data-backdrop="static"  keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" role="form" id="addForm" >
                <span class="glyphicon glyphicon-remove closeModal" onclick = "formModalclose('addModal', 'addForm' )"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h4></h4><input data-flag="flag" style="display: none;">
                </div>
                <hr/>
                <input define="role.roleId" type="text" name="roleId" style="width:100%;display: none" />
                <div class="form-group">
                    <label class="col-sm-3 control-label">角色名称：</label>
                    <div class="col-sm-7">
                        <input type="text" define="role.roleName" readonly data-field="roleName" name="roleName" placeholder="请输入角色名称"  class="form-control" style="width:100%;"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">角色描述：</label>
                    <div class="col-sm-7">
                        <textarea type="textarea"  name="roleDes" define="role.roleDes" data-field="roleDes" class="form-control"  placeholder="请输入角色描述"
                                  rows="3" maxlength="500"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            onclick = "formModalclose('addModal', 'addForm' )">关闭
                    </button>
                    <button id="editButton" type="button" class="btn btn-success btn-sm" onclick = "editSave()"  >保存</button>
                    <input type="reset" name="reset" style="display: none;"/>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 修改角色权限弹窗 -->
<div class="modal fade" id="editPermission" aria-hidden="true" data-backdrop="static"  keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <span class="glyphicon glyphicon-remove closeModal"  data-dismiss="modal" aria-hidden="true"></span>
            <div class="panel-heading">
                <h4 style="display: inline-block">修改角色权限</h4>
            </div>
            <div class="panel-body" style="width: 60%;margin-left: auto;margin-right: auto;">
                <div class="col-md-12">
                    <div id = "staTree" ></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"  data-dismiss="modal">取消</button>
                <button  type="button" id="subRolePer" class="btn btn-success btn-sm" onclick="savePermission()">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/systemManage/gijgo.js"></script>
<script src="/static/js/backstage/systemManage/roleManage.js"></script>
</body>
</html>
