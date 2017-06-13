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
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/select2.min.css">

    <link rel="stylesheet" href="/static/css/fileinput.css">

    <link rel="stylesheet" href="/static/css/city-picker/city-picker.css">

    <title>员工基本信息</title>

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
                <th data-width="100" data-field="userName">姓名</th>
                <th data-width="70" data-field="userGender" data-formatter="formatterGender">性别</th>
                <th data-formatter="formatterRole">用户角色</th>
                <th data-width="150" data-field="company" data-formatter="companyFormatter">所属公司</th>
                <th data-field="userEmail">用户邮箱</th>
                <th data-field="userPhone">用户手机号</th>
            <shiro:hasAnyRoles name="系统超级管理员, 系统普通管理员, 公司超级管理员,公司普通管理员,汽车公司人力资源管理部">
                <th data-field="userStatus" data-formatter="formatterStatus" data-events="tableTodoCellFun">操作</th>
            </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
        <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司人力资源管理部">
            <button id="searchRapid" type="button" class="btn btn-success" onclick="searchRapidStatus();">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用人员记录
            </button>
            <button id="searchDisable" type="button" class="btn btn-danger" onclick="searchDisableStatus();">
                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用人员记录
            </button>
            <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <%--<button id="btn_return" type="button" class="btn btn-default" onclick="showReturn();">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>辞退
            </button>--%>
        </shiro:hasAnyRoles>
        <div class="input-group" style="width:300px;float:left;padding:0;margin:0 0 0 -1px;">
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
        </div>
    </div>
</div>

<!-- 添加弹窗 aria-hidden="true" 默认隐藏 data-backdrop="static" 点击模态窗底层不会关闭模态窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width:900px;">
        <div class="modal-content" data-options="resizable:true,modal:true">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h3>添加人员信息</h3>
                </div>
                <form class="form-horizontal" role="form" id="addForm" method="post" enctype="multipart/form-data">
                    <div class="col-md-7">
                        <div class="form-group">
                            <label class="col-md-3 control-label">用户头像：</label>
                            <div class="col-md-9" style="margin-left: 30px;">
                                <div class="container kv-main">
                                <div class="ibox-title">
                                    <div class="input-group">   <%-- FileInput边框和组件组在一起 --%>
                                        <div class="input-group-btn"></div> <%-- 用来显示选中的图片 --%>
                                        <input id="file" name="userIconTemp" type="file" class="form-control" multiple class="file-loading"/>
                                    </div>
                                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="form-group">
                            <label class="col-md-3 control-label">姓名：</label>
                            <div class="col-md-9">
                                <input type="text" name="userName" placeholder="请输入姓名"
                                   class="form-control userName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">昵称：</label>
                            <div class="col-md-9">
                                <input type="text" name="userNickname" placeholder="请输入昵称"
                                       class="form-control userNickname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">性别：</label>
                            <div class="col-md-9">
                                <select id="addUserGender" name="userGender"
                                        class="js-example-tags form-control userGender">
                                    <option value="N">请选择性别</option>
                                    <option value="M">男</option>
                                    <option VALUE="F">女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">邮箱：</label>
                            <div class="col-md-9">
                                <input type="email" name="userEmail" placeholder="请输入邮箱"
                                       class="form-control userEmail">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">电话：</label>
                            <div class="col-md-9">
                                <input type="number" id="addUserPhone" placeholder="手机号码为11位"
                                       class="form-control userPhone" maxlength="11"
                                       aria-describedby="sizing-addon2" name="userPhone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">生日：</label>
                            <div class="col-md-9">
                                <input id="addDatetimepicker" placeholder="请选择生日" readonly="true" type="text"
                                       name="userBirthday" class="form-control datetimepicker"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label">角色：</label>
                            <div class="col-md-9">
                                <select id="addUserRole" name="roleId" class="js-example-tags userRole"
                                        style="width: 100%;"></select>
                            </div>
                        </div>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司人力资源管理部">
                        <div class="form-group" id="addSalary" style="display: none;">
                            <label class="col-md-3 control-label">底薪：</label>
                            <div class="col-md-9">
                                <input id="addUserSalary" type="number" min="0" name="userSalary"
                                       placeholder="请输入底薪" class="form-control">
                            </div>
                        </div>
                    </shiro:hasAnyRoles>
                    </div>
                    <div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">身份证：</label>
                            <div class="col-md-8">
                                <input type="number" id="addUserIdentity" name="userIdentity" maxlength="18"
                                       placeholder="请输入身份证号" class="form-control userIdentity">
                            </div>
                        </div>
                        <div class="form-group col-md-6">
                            <label class="col-md-4 control-label">用户描述：</label>
                            <div class="col-md-8">
                                <textarea id="addUserDes" class="form-control" name="userDes" placeholder="请输入用户描述"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-12">
                        <label class="col-md-2 control-label" style="top: 5px;right:5px">地址：</label>
                        <div class="col-md-10">
                            <div style="position: relative;">
                                <input data-toggle="city-picker" class="col-md-4" name="userAddress"/>
                            </div>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                    <div class="modal-footer">
                        <span id="addError"></span>
                        <button type="button" class="btn btn-default" onclick="closeModals('addWindow','addForm')">关闭</button>
                        <button id="addButton" onClick="addSubmit();" type="button" class="btn btn-sm btn-success">添加
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div><!-- /.modal-body -->
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content" style="width:900px; margin-left:-150px;">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h3>修改人员信息</h3>
                </div>
                <form role="form" class="form-horizontal" id="editForm" method="post">
                    <input type="hidden" define="emp.userId" name="userId" class="form-control"/>
                    <input id="editLoginTime" type="hidden" class="form-control" disabled="true">
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">姓名：</label>
                            <div class="col-md-8">
                                <input type="text" name="userName" define="emp.userName" class="form-control">
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">邮箱：</label>
                            <div class="col-md-8">
                                <input type="text" name="userEmail" define="emp.userEmail" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">手机号码：</label>
                            <div class="col-md-8">
                                <input type="number" name="userPhone" define="emp.userPhone" class="form-control">
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">身份证：</label>
                            <div class="col-md-8">
                                <input type="number" name="userIdentity" define="emp.userIdentity" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">角色：</label>
                            <div class="col-md-8">
                                <select id="editUserRole" name="roleId" define="emp.role.roleId"
                                    class="js-example-tags userRole" style="width: 100%;"></select>
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">用户描述：</label>
                            <div class="col-md-8">
                                <input type="text" name="userDes" define="emp.userDes" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">性别：</label>
                            <div class="col-md-8">
                                <select name="userGender" define="emp.userGender" class="form-control" style="width: 50%;">
                                    <option value='N'>未选择</option>
                                    <option value='M'>男</option>
                                    <option value='F'>女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">生日：</label>
                            <div class="col-md-8">
                                <input id="editDatetimepicker" readonly="true" type="date" define="emp.userBirthday"
                                   name="userBirthday" class="form-control datetimepicker">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司人力资源管理部">
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">底薪：</label>
                            <div class="col-md-8">
                                <input type="number" define="emp.userSalary" name="userSalary" class="form-control">
                            </div>
                        </div>
                    </shiro:hasAnyRoles>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">昵称：</label>
                            <div class="col-md-8">
                                <input type="text" name="userNickname" define="emp.userNickname" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-12 pull-right">
                            <label class="col-md-2 control-label" style="top: 9px;right:5px">地址：</label>
                            <div style="position: relative;margin-top:10px;" class="col-md-10">
                                <input id="editAddress" type="text" class="col-md-5" define="emp.userAddress" name="userAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="editError"></span>
                        <button type="button" class="btn btn-default" onclick="closeModals('editWindow', 'editForm')">关闭</button>
                        <button id="editButton" onclick="editSubmit();" class="btn btn-primary btn-sm" title="双击我">
                            保存
                        </button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 详细信息弹窗 -->
<div class="modal fade" id="detailWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content" style="width:850px; margin-left:-121px;">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('detailWindow', 'detailForm')"></span>
                <div class="modal-header">
                    <h3>详细信息</h3>
                </div>
                <form role="form" class="form-horizontal" id="detailForm" method="post">
                    <div class="row">
                        <div class="form-right col-md-7">
                            <div class="form-group">
                                <img id="detailUserIcon" src="" class="img-circle" style="width:180px;height:180px;">
                            </div>
                            <div class="form-group pull-left">
                                <label class=" control-label">姓名：</label>
                                <input type="text" name="userName" define="emp.userName" class="form-control"
                                   style="margin-left: 30px;" disabled="true"> <%-- &lt;%&ndash;&lt;%&ndash;此果文字会变成灰色，不可编辑。&ndash;%&gt;&ndash;%&gt;--%>
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">邮箱：</label>
                                <input style="margin-left: 20px;" type="text" define="emp.userEmail" class="form-control" disabled="true">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">手机号码：</label>
                                <input type="number" define="emp.userPhone" class="form-control" disabled="true">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">身份证：</label>
                                <input style="margin-left: 10px;" type="number" class="form-control"
                                   define="emp.userIdentity" disabled="true">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">创建时间：</label>
                                <input id="detailCreatedTime" type="text" class="form-control" disabled="true">
                            </div>
                        </div>
                        <div class="form-left col-md-5">
                            <div class="form-group pull-left">
                                <label class="control-label">角色：</label>
                                <input define="emp.role.roleName" class="form-control" type="text" disabled="true"
                                   style="margin-left: 30px;">
                            </div>
                            <div class="form-group pull-left">
                                <label class=control-label">用户描述：</label>
                                <input type="text" disabled="true" define="emp.userDes" class="form-control">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">性别：</label>
                                <input id="detailGender" type="text" class="form-control"
                                     disabled="true" style="margin-left: 30px;">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">生日：</label>
                                <input id="detailBirthday" type="date" define="emp.userBirthday"
                                   class="form-control" disabled="true" style="margin-left: 30px;">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">底薪：</label>
                                <input type="number" define="emp.userSalary" name="userSalary" class="form-control"
                                   style="margin-left: 30px;" disabled="true">
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">昵称：</label>
                                <input type="text" define="emp.userNickname" class="form-control" disabled="true" style="margin-left: 30px;">
                            </div>
                            <%-- 当角色不为系统的管理员的时候就显示此div --%>
                            <shiro:hasAnyRoles name="系统超级管理员, 系统普通管理员">
                                <div class="form-group pull-left">
                                    <label class="control-label">所属公司：</label>
                                    <input define="emp.company.companyName" class="form-control" disabled="true">
                                </div>
                            </shiro:hasAnyRoles>
                            <div class="form-group pull-left">
                                <label class="control-label" >地址：</label>
                                <input id="address" type="text" class="form-control" name="userAddress" disabled="true"/>
                            </div>
                            <div class="form-group pull-left">
                                <label class="control-label">上一次登录时间：</label>
                                <input id="detailLoginTime" type="text" class="form-control" disabled="true">
                            </div>
                            <p class="clearfix"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/backstage/emp/jquery-ui.js"></script>
<script src="/static/js/backstage/emp/jquery.formFillTemp.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/backstage/emp/empInFormation.js"></script>

<%-- 地址选择 --%>
<script src="/static/js/city-picker/city-picker.data.js"></script>
<script src="/static/js/city-picker/city-picker.js"></script>

<%-- 文件上传 --%>
<script src="/static/js/fileInput/fileinput.js"></script>
<script src="/static/js/fileInput/zh.js"></script>

</body>
<script>

    var loginRoleName = "<%= name %>";
    var userId = "<%= userId %>";

    var tableTodoCellFun = {
        'click .detail': function (e, value, row, index) {
             showDetail(row);
        }
    }
</script>
</html>
