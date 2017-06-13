<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>投诉管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/js/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
</head>
<body>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table">
            <thead>
            <tr>
                <th data-checkbox="true"></th>
                <th data-field="user.userName">投诉人</th>
                <th data-field="complaintCreatedTime" data-formatter="formatterDate">投诉时间</th>
                <th data-field="complaintContent">投诉内容</th>
                <th data-field="company.companyName">投诉公司</th>
                <th data-formatter="formatterUserName">投诉回复人</th>
                <th data-field="complaintReply">投诉回复内容</th>
                <th data-field="complaintReplyTime">投诉回复时间</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" data-backdrop="static" keyboard:false style="overflow:auto; ">
    <div class="modal-dialog" style="width: 780px;height: auto;">
        <div class="modal-content" style="overflow:hidden;">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
            <form class="form-horizontal" role="form" id="addForm" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>请填写投诉信息</h4>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">投诉人：</label>
                    <div class="col-md-7 col-sm-7">
                        <input class="form-control" name="userName" type="text" value="${sessionScope.frontUser.userName}">
                    </div>
                </div>
                <div id="addCompanyBox" class="form-group">
                    <label class="col-sm-3 control-label">选择公司：</label>
                    <div class="col-sm-7">
                        <select id="addCompany"class="js-example-tags company" name="companyId" style="width:100%">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">投诉时间：</label>
                    <div class="col-sm-7">
                        <input id="addComplaintCreatedTime" name="complaintCreatedTime" readonly onclick="getDate('addComplaintCreatedTime')" class="form-control datetimepicker"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">投诉内容：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="complaintContent" placeholder="请输入投诉内容" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button id="closebtn" type="button" class="btn btn-default"
                                onclick="closeModals('addWindow', 'addForm')">关闭
                        </button>
                        <button id="addButton" class="btn btn-sm btn-success" type="button" onclick="addSubmit()">保 存</button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </div>
            </form>
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
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/plugins/layui/layui.js" charset="utf-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script>

    $(function () {
        initTable('table', '/complaint/queryByPagerComplaintUser'); // 初始化表格
        initDateTimePicker('addForm', 'complaintCreatedTime', 'addComplaintCreatedTime');
        initSelect2("user", "请选择用户", "/complaint/queryCombox");
        initSelect2("admin", "请选择回复人", "/complaint/queryCombox");
        initSelect2("company", "请选择公司", "/company/queryAllCompany");

    });
    function showAdd(){
        $("#addWindow").modal('show');
        $("#addButton").removeAttr("disabled");
        validator('addForm'); // 初始化验证
    }

    function addSubmit(){
        $("#addForm").data('bootstrapValidator').validate();
        if ($("#addForm").data('bootstrapValidator').isValid()) {
            $("#addButton").attr("disabled","disabled");
        } else {
            $("#addButton").removeAttr("disabled");
        }
    }

    function validator(formId) {
        $('#' + formId).bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                 userName: {
                     message: '投诉人验证失败',
                     validators: {
                         notEmpty: {
                             message: '投诉人不能为空'
                         }
                     }
                 },
                 complaintCreatedTime: {
                     message: '投诉时间验证失败',
                     validators: {
                         notEmpty: {
                             message: '投诉时间不能为空'
                         }
                     }
                 },
                 complaintContent: {
                     message: '投诉内容验证失败',
                     validators: {
                         notEmpty: {
                             message: '投诉内容不能为空'
                         }
                     }
                 },
                companyId: {
                    message: '投诉公司验证失败',
                    validators: {
                        notEmpty: {
                            message: '投诉公司内容不能为空'
                        }
                    }
                },

            }
        })
                .on('success.form.bv', function (e) {
                    if (formId == "addForm") {
                        formSubmit("/Userinsert", formId, "addWindow");
                    }
                })
    }

    function formSubmit(url, formId){
        $.post(url,
                $("#" + formId).serialize(),
                function (data) {
                    if (data.result == "success") {
                        swal({
                            title: "",
                            text: data.message,
                            confirmButtonText: "确认",
                            type: "success"
                        })
                        $("#closebtn").click();
                    } else if (data.result == "fail" ) {
                        swal({
                            title: "",
                            text: data.message,
                            confirmButtonText: "确认",
                            type: "error"
                        })
                    }
                }, "json");
    }
    function formatterUserName(value, row, index) {
        if(row.admin != null) {
            return row.admin.userName;
        }
    }
</script>
</body>
</html>
