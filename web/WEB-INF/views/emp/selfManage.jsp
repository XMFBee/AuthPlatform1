<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.gs.bean.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>个人资料</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <%-- 地址 --%>
    <link rel="stylesheet" href="/static/css/city-picker/city-picker.css">

    <style>
        /* 让显示详细信息的窗口中的所有Input都不显示边框 */
        #detailWindow input {
            border:0 solid white;
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
    User user = (User)session.getAttribute("user");
    String iconSrcString = user.getUserIcon();
    String userGender = user.getUserGender();
%>

<div class="container" id="detailWindow">
    <div class="panel-body" style="padding-bottom:0px;">
        <div class="modal-body">
            <form role="form" class="form-horizontal" id="detailForm" method="post">
                <div class="row">
                    <div class="form-right col-md-7">
                        <div class="form-group">
                            <img id="detailUserIcon" class="img-circle" style="width:180px;height:180px;"
                                 src=""><br/>
                        </div>
                        <div class="form-group pull-left">
                            <label class=" control-label">姓名：</label>
                            <input type="text" name="userName" value="${sessionScope.user.userName}" class="form-control"
                                   style="margin-left: 30px;" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">邮箱：</label>
                            <input style="margin-left: 20px;" type="text" value="${sessionScope.user.userEmail}"
                                   class="form-control" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">手机号码：</label>
                            <input type="number" value="${sessionScope.user.userPhone}" class="form-control" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">身份证：</label>
                            <input style="margin-left: 10px;width: 300px;" type="number" class="form-control"
                                   value="${sessionScope.user.userIdentity}" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">创建时间：</label>
                            <input id="detailCreatedTime" value="${sessionScope.user.userCreatedTime}" type="text" class="form-control" disabled="true">
                        </div>
                    </div>
                    <div class="form-left col-md-5">
                        <div class="form-group pull-left">
                            <label class="control-label">角色：</label>
                            <input value="${sessionScope.user.role.roleName}" class="form-control" type="text" disabled="true"
                                   style="margin-left: 30px;">
                        </div>
                        <div class="form-group pull-left">
                            <label class=control-label">用户描述：</label>
                            <input type="text" disabled="true" value="${sessionScope.user.userDes}" class="form-control">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">性别：</label>
                            <input id="detailGender" type="text" class="form-control" value="${sessionScope.user.userGender}"
                                   disabled="true" style="margin-left: 30px;">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">生日：</label>
                            <input id="detailBirthday" value="${sessionScope.user.userBirthday}"
                                   class="form-control" disabled="true" style="margin-left: 30px;">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">底薪：</label>
                            <input type="number" value="${sessionScope.user.userSalary}" name="userSalary" class="form-control"
                                   style="margin-left: 30px;" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">昵称：</label>
                            <input type="text" value="${sessionScope.user.userNickname}" class="form-control" disabled="true" style="margin-left: 30px;">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">所属公司：</label>
                            <input value="${sessionScope.user.company.companyName}" class="form-control" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label" >地址：</label>
                            <input type="text" id="userAddress" value="${sessionScope.user.userAddress}" class="form-control" style="margin-left: 20px;" disabled="true">
                        </div>
                        <div class="form-group pull-left">
                            <label class="control-label">上一次登录时间：</label>
                            <input id="detailLoginTime" value="${sessionScope.user.userLoginedTime}" type="text" class="form-control" disabled="true">
                        </div>
                        <p class="clearfix"></p>
                    </div>
                </div>
                <%-- 暂时先不添加修改功能，完善工资后再回头完成修改 --%>
                <div class="modal-footer">
                    <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content" style="width:900px; margin-left:-150px;">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
                <div class="modal-header" style="overflow:auto;">
                    <h3>修改人员信息</h3>
                </div>
                <form role="form" class="form-horizontal" id="editForm" >
                    <input type="hidden" value="${sessionScope.user.userId}" name="userId" class="form-control" method="post"/>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">姓名：</label>
                            <div class="col-md-8">
                                <input type="text" name="userName" value="${sessionScope.user.userName}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">Email：</label>
                            <div class="col-md-8">
                                <input type="text" name="userEmail" value="${sessionScope.user.userEmail}" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">手机号码：</label>
                            <div class="col-md-8">
                                <input type="number" name="userPhone" value="${sessionScope.user.userPhone}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">身份证：</label>
                            <div class="col-md-8">
                                <input type="number" name="userIdentity" value="${sessionScope.user.userIdentity}" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">性别：</label>
                            <div class="col-md-8">
                                <select id="editUserGender" name="userGender"  define="genderTempObj.gender" class="form-control" style="width: 50%;">
                                    <option value='N'>未选择</option>
                                    <option value='M'>男</option>
                                    <option value='F'>女</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">用户描述：</label>
                            <div class="col-md-8">
                                <input type="text" name="userDes" value="${sessionScope.user.userDes}" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">昵称：</label>
                            <div class="col-md-8">
                                <input type="text" name="userNickname" value="${sessionScope.user.userNickname}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group col-md-6 pull-left">
                            <label class="col-md-4 control-label">生日：</label>
                            <div class="col-md-8">
                                <input id="editDatetimepicker" readonly="true" value="${sessionScope.user.userBirthday}"
                                       name="userBirthday" class="form-control">
                            </div>
                        </div>
                        <p class="clearfix"></p>
                    </div>
                    <div>
                        <div class="form-group col-md-12 pull-right">
                            <label class="col-md-2 control-label" style="top: 5px;right:5px">地址：</label>
                            <div style="position: relative;margin-top:10px;" class="col-md-10">
                                <input id="editAddress" type="text" class="col-md-5" value="${sessionScope.user.userAddress}" name="userAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span id="editError"></span>
                        <button type="button" class="btn btn-default" onclick="closeModals('editWindow', 'editForm')">关闭</button>
                        <button id="editButton" onclick="editSubmit();" type="submit" class="btn btn-primary btn-sm" title="双击我">
                            保存
                        </button>
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
<script src="/static/js/backstage/emp/selfManage.js"></script>

<%-- 地址选择 --%>
<script src="/static/js/city-picker/city-picker.data.js"></script>
<script src="/static/js/city-picker/city-picker.js"></script>

</body>

<script>
    var iconSrc = "<%=iconSrcString %>";  // 登录的用户的头像
    var gender = "<%=userGender %>";    // 登录的用户的性别

    var us= "<%=user %>";    // 登录的用户的性别
    console.log(us); // 也就是说刷新这样的页面，session还是旧的
</script>
</html>
