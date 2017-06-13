var accTypeName;
var contentPath = ''
var roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";

//初始化表格
$(function () {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accType/queryByPage'); // 初始化表格
            initSelect2("company", "请选择所属公司", "/company/queryAllCompany");
        } else if (data.result == 'notLogin') {
            swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                }
                , function (isConfirm) {
                    if (isConfirm) {
                        top.location = "/user/loginPage";
                    } else {
                        top.location = "/user/loginPage";
                    }
                })
        } else if (data.result == 'notRole') {
            swal({
                title: "",
                text: data.message,
                confirmButtonText: "确认",
                type: "error"
            })
        }
    })

});

// 查看全部可用
function showAvailable() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accType/queryByPage');
        } else if (data.result == "notLogin") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
        } else if (data.result = "notRole") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}
// 查看全部禁用
function showDisable() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accType/queryByPagerDisable');
        } else if (data.result == "notLogin") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
        } else if (data.result = "notRole") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

// 模糊查询
function blurredQuery() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/accType/blurredQuery?text=' + text + '&value=' + vaule);
        } else if (data.result == "notLogin") {
            swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"}
                ,function(isConfirm){
                    if(isConfirm){
                        top.location = "/user/loginPage";
                    }else{
                        top.location = "/user/loginPage";
                    }
                })
        } else if (data.result = "notRole") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

//显示弹窗
function showEdit() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var row = $('table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var ceshi = row[0];
                $("#editForm").fill(ceshi);
                $('#editCompany').html('<option value="' + ceshi.company.companyId + '">' + ceshi.company.companyName + '</option>').trigger("change");
                validator('editForm');
            } else {
                swal({
                    "title": "",
                    "text": "请选择配件分类信息",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    "type": "warning"
                })
            }
        } else if (data.result == "notLogin") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
        } else if (data.result = "notRole") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

//显示添加
function showAdd() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm'); // 初始化验证
        } else if (data.result == "notLogin") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
        } else if (data.result = "notRole") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}
//格式化页面上的配件分类状态
function formatterStatus(value) {
    if (value == "Y") {
        return "可用";
    } else {
        return "不可用";
    }
}

// 激活或禁用
function statusFormatter(value, row, index) {
    if (value == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\"" + '/accType/statusOperate?accTypeId=' + row.accTypeId + '&accTypeStatus=Y' + "\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\"" + '/accType/statusOperate?accTypeId=' + row.accTypeId + '&accTypeStatus=N' + "\")'>激活</a>";
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
            accTypeName: {
                message: '配件分类名称不能为空',
                validators: {
                    notEmpty: {
                        message: '配件分类名称不能为空'
                    },
                    remote: {
                        url: '/accType/queryNameByOne',
                        message: '该配件名称已存在',
                        delay :  2000,
                        type: 'POST',
                        data: {
                            accTypeId: $("#"+formId + " input[name=accTypeId]").val(),
                            accTypeName: $("#" + formId + " input[name=accTypeName]").val()
                        }
                    },
                }
            },
            companyId: {
                message: '所属公司不能为空',
                validators: {
                    notEmpty: {
                        message: '所属公司不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        if (formId == "addForm") {
            formSubmit("/accType/addAccType", formId, "addWindow");

        } else if (formId == "editForm") {
            formSubmit("/accType/updateAccType", formId, "editWindow");

        }
    })
}


function addSubmit() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").attr("disabled", "disabled");
    } else {
        $("#addButton").removeAttr("disabled");
    }
}

function editSubmit() {
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled", "disabled");
    } else {
        $("#editButton").removeAttr("disabled");
    }
}

function formSubmit(url, formId, winId) {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            $.post(url,
                $("#" + formId).serialize(),
                function (data) {
                    if (data.result == "success") {
                        $('#' + winId).modal('hide');
                        swal({
                            title: "",
                            text: data.message,
                            confirmButtonText: "确定", // 提示按钮上的文本
                            type: "success"
                        })// 提示窗口, 修改成功
                        $('#table').bootstrapTable('refresh');
                        if (formId == 'addForm') {
                            $("input[type=reset]").trigger("click"); // 移除表单中填的值
                            $("#addButton").removeAttr("disabled"); // 移除不可点击
                            $("#addCompany").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                        }
                        $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                        $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
                    } else if (data.result == "fail") {
                        swal({
                            title: "",
                            text: "添加失败",
                            confirmButtonText: "确认",
                            type: "error"
                        })
                        $("#" + formId).removeAttr("disabled");
                    }
                }, "json");
        } else if (data.result == "notLogin") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
        } else if (data.result = "notRole") {
            swal({
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}
