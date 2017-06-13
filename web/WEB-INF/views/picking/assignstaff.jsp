<%--
  Created by IntelliJ IDEA.
  User: yaoyong
  Date: 2017/4/11
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>指派员工</title>
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
<style>
    .sd{
        font-weight: bold;
    }
    a {
        color: #337ab7;
    }
    #accInfoTable tr {
        font-family:"微软雅黑";
        line-height:30px;
    }
    .search {
        display: none;
    }
    .close {
        right:20px;
        position: relative;
    }
</style>
<body>
<%@include file="../backstage/contextmenu.jsp"%>
<div class="container">
 <%--   <div class="nav">
        <ul id="myTab" class="nav nav-tabs">
            <li class="pull-right" onclick = "hasDispatcher()">
                <a  data-toggle="tab" >
                    <h4>进行中</h4>
                </a>
            </li>
            <li class="active pull-right" onclick = "noDispatcher()">
                <a  data-toggle="tab" >
                    <h4>未指定</h4>
                </a>
            </li>
        </ul>
    </div>--%>
    <div class="panel-body" style="padding-bottom:0px;"  >
        <table id="recordTable" style="table-layout: fixed" data-search=true>
            <thead>
            <tr >
                <th data-width="110" data-field="workInfo" data-formatter = "formatterUser">
                    员工
                </th>
                <th data-width="110" data-field="carplate" data-formatter = "formatterPlate">
                    车牌
                </th>
                <th data-width="110" data-field="carmodel">
                    车型
                </th>
                <th data-width="100" data-hide="all" data-field="hours">
                    所需工时
                </th>
                <th data-width="180" data-hide="all" data-field="record.recordCreatedTime" data-formatter="formatterDate">
                    创建时间
                </th>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司总技师">
                    <th data-width="180" data-hide="all" data-field="todoCell" data-formatter="todoCell">
                        操作
                    </th>
                </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <button type="button" class="btn btn-default" onclick="noDispatcher()">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>未指派
            </button>
            <button type="button" class="btn btn-default" onclick="hasDispatcher()">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>已指派
            </button>
        </div>
    </div>
</div>

<!-- 指派员工弹窗 -->
<shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司总技师">
    <div class="modal fade" id="appointModal" aria-hidden="true" data-backdrop="static" keyboard="false" style="overflow:auto; ">
    <div class="modal-dialog">
        <div class="modal-content">
            <span class="glyphicon glyphicon-remove closeModal"  data-dismiss="modal" aria-hidden="true" style="right:10px; top:10px"></span>
            <div class="modal-header" style="overflow:auto;width: 95%">
                <h4>指派员工</h4>
                <input type="text" id="recordId" style="display: none;"/>
            </div>
            <table id="designatedForm" >
                <thead>
                <tr>
                    <th data-radio="true"></th>
                    <th data-width="100" data-field="userName">姓名</th>
                    <th data-width="70" data-field="userGender" data-formatter="formatterGender">性别</th>
                    <th data-formatter="formatterRole">用户角色</th>
                </tr>
                </thead>
            </table>
            <div class="modal-footer" style="border: none;">
                <span id="editError" style="color: red;"></span>
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" onclick="submitDispatcher()" class="btn btn-success">
                    指派
                </button>
            </div>
           <%-- <form class="form-horizontal" id="appointForm" method="post">
                <input type="text" name="recordId" define="record.recordId" style="display:none"/>
                <div class="modal-header" style="overflow:auto;">
                    <h4 class="sd">请选择指定的员工</h4>
                </div>
                <hr>
                <div class="form-group">
                    <label class="col-sm-3 control-label">请选择员工：</label>
                    <select   class="js-example-basic-multiple addemp" name="userId"  style="width:300px;">
                    </select>
                </div>
                <div class="modal-footer" style="border: none;">
                    <span id="editError" style="color: red;"></span>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" onclick="submitDispatcher()" class="btn btn-success">
                        确认
                    </button>
                </div>
            </form>--%>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</shiro:hasAnyRoles>

<!-- 维修保养明细弹窗 -->
<div class="modal fade" id="accsInfo" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="margin-right:0;width:95%;">
                <span class="glyphicon glyphicon-remove closeModal"  data-dismiss="modal" aria-hidden="true"></span>
                <h4 class="modal-title" id="myModalLabel">维修保养明细</h4>
            </div>
            <div class="modal-body">
                <table id="accInfoTable" style="table-layout: fixed">
                    <thead>
                    <tr >
                        <th data-width="110" data-field="carmodel" data-formatter="infoFormatter"></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/form/jquery.validate.js"></script>
<script src="/static/js/backstage/picking/assignstaff.js"></script>
</body>

</html>
