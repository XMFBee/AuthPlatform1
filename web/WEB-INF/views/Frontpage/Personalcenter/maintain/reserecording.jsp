<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>维修保养记录管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/js/plugins/layui/css/layui.css">
</head>
<body>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table">
            <thead>
            <tr>
                <th data-checkbox="true" data-field="status" ></th>
                <th data-field="checkin.userName" data-width="50">维修保养登记人</th>
                <th data-field="startTime" data-formatter="formatterDate">维修保养开始时间</th>
                <th data-field="endTime" data-formatter="formatterDate">维修保养预估结束时间</th>
                <th data-field="actualEndTime" data-formatter="formatterDate">维修保养实际结束时间</th>
                <th data-field="recordCreatedTime" data-formatter="formatterDate">维修保养记录创建时间</th>
                <th data-field="pickupTime" data-formatter="formatterDate">维修保养车主提车时间</th>
                <th data-field="recordDes">维修保养记录描述</th>
                <th data-field="recordStatus" data-formatter="showStatusFormatter">记录状态</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script>
    $(function () {
        initTable("table",'/maintainRecord/queryByOwner')
    })
</script>
</body>
</html>
