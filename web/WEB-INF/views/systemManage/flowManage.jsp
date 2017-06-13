<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>流程管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
<style>
    .disabled, .disabled:hover, .disabled:active, .disabled:focus,
    #table .btn-danger.disabled:hover,
    #table .btn-danger.disabled:focus
    {
        background-color: #aaa;
        border: 1px solid #aaa;
    }
    #table .btn-danger.disabled:focus,
    .disabled:focus {
        outline: none;
    }
</style>
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-heading" style="height:90px; border-bottom: 1px solid #ddd">
        <h3 style="display: inline;line-height:70px;" >流程管理</h3>
    </div>
    <div class="panel-body" style="padding-bottom:0px;"  >
        <table id="table">
            <thead>
            <tr>
                <th  data-field="flowName" >名称</th>
                <th data-field="lastModified" data-formatter = "formatterDate">最后修改时间
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </th>
                <th data-field="price" data-formatter = "flowStatusFormatter">状态</th>
                <th data-field="todoCell" data-formatter = "todoCell" data-events= "todoCellBtnEvent">操作</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group"></div>
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
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/backstage/systemManage/flowManage.js"></script>

</body>
</html>
