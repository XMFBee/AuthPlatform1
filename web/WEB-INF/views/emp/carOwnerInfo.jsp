<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page import="com.gs.bean.User"%>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/city-picker/city-picker.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/select2.min.css">

    <link rel="stylesheet" href="/static/css/fileinput.css">

    <title>车主管理</title>

    <style>
        .table th, .table td {
            text-align: center;
            vertical-align: middle!important;
        }

        fieldset {
            margin: 1em 0;
        }

        fieldset legend {
            font: bold 14px/2 "\5fae\8f6f\96c5\9ed1";
        }

        /* 给添加窗口的select2设置宽度*/
        .addWindow .editUserRole .form-group {
            width: 690px;
        }

        /* 让显示详细信息的窗口中的所有Input都不显示边框 */
        #detailWindow input {
            border:0px;
        }
        .form-right .form-group{
            padding-top:10px;
            padding-left: 25%;
        }
        #detailWindow .form-group input{
            width: 200px;
        }
        .form-group label{
            padding:0;
        }
        .form-left{
            padding-top:20px;
        }

        #detailForm .form-control[disabled], .form-control[readonly], fieldset[disabled] .form-control {
            background: #fff;
        }
        #detailForm .form-control {
            box-shadow: none;
        }

        .select2-container .select2-selection--multiple {
            height: auto;
        }
    </style>

</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<%
    String name =((User)session.getAttribute("user")).getRole().getRoleName();
    String userId =((User)session.getAttribute("user")).getUserId();
%>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-width="100" data-field="userName">车主姓名</th>
                <th data-width="100" data-field="userNickname">车主昵称</th>
                <th data-width="100" data-field="userGender" data-formatter="formatterGender">车主性别</th>
                <th data-width="150" data-field="company" data-formatter="companyFormatter">所属公司</th>
                <th data-width="100" data-formatter="formatterRole">车主角色</th>
                <th data-width="160" data-field="userEmail">车主邮箱</th>
                <th data-width="130" data-field="userPhone">车主手机号</th>
                <th data-width="100" data-field="userSalary">车主底薪</th>
                <th data-width="150" data-field="userIdentity">车主身份证号</th>
                <th data-width="220" data-field="userAddress">车主地址</th>
                <th data-width="130" data-field="userDes">车主描述</th>
                <shiro:hasAnyRoles name="系统超级管理员, 系统普通管理员">
                    <th data-width="100" data-field="userStatus" data-formatter="showStatusFormatter">
                        记录状态
                    </th>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员, 系统普通管理员">
                    <th data-width="140" data-field="userStatus" data-formatter="formatterStatus">操作</th>
                </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="searchRapid" type="button" class="btn btn-success" onclick="searchRapidStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用车主记录
                </button>
                <button id="searchDisable" type="button" class="btn btn-danger" onclick="searchDisableStatus();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用车主记录
                </button>
            </shiro:hasAnyRoles>
            <%--<div class="input-group" style="width:300px;float:left;padding:0;margin:0 0 0 -1px;">
                <div class="input-group-btn">
                    <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;" data-toggle="dropdown">
                        姓名/手机号/角色名称
                        <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                            /所属公司
                        </shiro:hasAnyRoles>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu pull-right">
                        <li><a onclick="onclikLi(this)">姓名</a></li>
                        <li class="divider"></li>
                        <li><a onclick="onclikLi(this)">手机号</a></li>
                        <li class="divider"></li>
                        <li><a onclick="onclikLi(this)">角色名称</a></li>
                        <li class="divider"></li>
                        <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                            <li><a onclick="onclikLi(this)">所属公司</a></li>
                            <li class="divider"></li>
                        </shiro:hasAnyRoles>
                        <li><a onclick="onclikLi(this)"></a></li>
                    </ul>
                </div>
                <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                <a href="javaScript:;" onclick="blurredQuery()">
                    <span class="glyphicon glyphicon-search search-style"></span>
                </a>
                </input>
            </div>--%>
        </div>
    </div>

    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
    <script src="/static/js/backstage/emp/jquery-ui.js"></script>
    <script src="/static/js/backstage/emp/jquery.formFillTemp.js"></script>
    <script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
    <script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
    <script src="/static/js/sweetalert/sweetalert.min.js"></script>
    <script src="/static/js/contextmenu.js"></script>
    <script src="/static/js/backstage/main.js"></script>
    <script src="/static/js/city-picker/city-picker.data.js"></script>
    <script src="/static/js/city-picker/city-picker.js"></script>
    <script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
    <script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="/static/js/select2/select2.js"></script>
    <script src="/static/js/backstage/emp/carOwnerInfo.js"></script>


    <%-- 文件上传 --%>
    <script src="/static/js/fileInput/fileinput.js"></script>
    <script src="/static/js/fileInput/zh.js"></script>

</body>

</html>
