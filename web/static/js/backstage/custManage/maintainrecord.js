$(function () {
    initTable('table', '/maintainRecord/queryByPager'); // 初始化表格
});

// 查看全部可用
function showAvailable() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            initTable('table', '/maintainRecord/queryByPager');
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
    });
}
// 查看全部禁用
function showDisable() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            initTable('table', '/maintainRecord/queryByPagerDisable');
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
    });
}

//显示弹窗
function showEdit() {
    var row = $("#table").bootstrapTable('getSelections');
    if (row.length > 0) {
        $("#editWindow").modal('show'); // 显示弹窗
        var MaintainRecord = row[0];
        $("#editForm").fill(MaintainRecord);
        $("#start_edit").val(formatterDate(MaintainRecord.startTime));
        $("#end_edit").val(formatterDate(MaintainRecord.endTime));
        $("#end2_edit").val(formatterDate(MaintainRecord.actualEndTime));
        $("#created_edit").val(formatterDate(MaintainRecord.recordCreatedTime));
        $("#pickup_edit").val(formatterDate(MaintainRecord.pickupTime));
    } else {
        swal({
            "title": "",
            "text": "请先选择一条数据",
            "type": "warning"
        })
    }
}

//显示添加
function showAdd() {
    $("#addWindow").modal('show');
}


function formatRepo(repo) {
    return repo.text
}
function formatRepoSelection(repo) {
    return repo.text
}

//显示删除
function showInactive(id) {
    var row = $('#table').bootstrapTable('getSelections');
    if (row.length > 0) {
        swal({
            title: "您确定要冻结吗？",
            text: "您确定要冻结这条数据？",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            cancelButtonText: "取消",
            confirmButtonText: "是的，我要冻结",
            confirmButtonColor: "#ec6c62"
        }, function () {
            $.ajax({
                url: "/maintainRecord/inactive/" + id,
                type: "DELETE"
            }).done(function (data) {
                swal("操作成功!", "已成功冻结数据！", "success");
                $('#table').bootstrapTable("refresh");
            }).error(function (data) {
                swal("OMG", "冻结操作失败了!", "error");
            });
        });
    } else {
        swal({
            "title": "",
            "text": "请先选择一条数据",
            "type": "warning"
        })
    }
}

function showActive(id) {
    var row = $('#table').bootstrapTable('getSelections');
    if (row.length > 0) {
        $(function () {
            $.ajax({
                url: "/maintainRecord/active/" + id,
                type: "DELETE"
            }).done(function (data) {
                swal("操作成功!", "已成功解冻数据！", "success");
                $('#table').bootstrapTable("refresh");
            }).error(function (data) {
                swal("OMG", "解冻操作失败了!", "error");
            });
            $('#table').bootstrapTable("refresh");
        });
    } else {
        swal({
            "title": "",
            "text": "请先选择一条数据",
            "type": "warning"
        })
    }
}

function formatterStatus(value, row, index) {
    if (row.recordStatus == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='showInactive(\"" + row.recordId + "\")'>禁用</a>"
    } else if (row.recordStatus == 'N') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='showActive(\"" + row.recordId + "\")'>激活</a>";
    }
}

//前端验证
$(document).ready(function () {
    $("#addForm").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            checkinId: {
                required: true,
                minlength: 2
            },
            startTime: {
                required: true,
                minlength: 2
            },
            endTime: {
                required: true,
                minlength: 2
            },
            actualEndTime: {
                required: true,
                minlength: 2
            },
            recordCreatedTime: {
                required: true,
                minlength: 2
            },
            pickupTime: {
                required: true,
                minlength: 2
            },
            recordDes: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            checkinId: "请选择维修保养登记人",
            startTime: "请选择维修保养开始时间",
            endTime: "请选择维修保养预估结束时间",
            actualEndTime: "请选择维修保养实际结束时间",
            recordCreatedTime: "请选择维修保养记录创建时间",
            pickupTime: "请选择维修保养车主提车时间",
            recordDes: "请输入维修保养记录描述",
        },
        errorPlacement: function (error, element) {
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success: function (label) {
            var el = label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function (form) {
            $.post("/maintainRecord/insert",
                $("#addForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#addWindow").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title: "",
                            text: data.message,
                            confirmButtonText: "确定", // 提示按钮上的文本
                            type: "success"
                        })// 提示窗口, 修改成功
                    } else if (data.result == "fail") {
                        swal({
                            title: "",
                            text: "添加失败",
                            confirmButtonText: "确认",
                            type: "error"
                        })
                    }
                }, "json"
            );
        }
    })
    $("#editForm").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            checkinId: {
                required: true,
                minlength: 2
            },
            startTime: {
                required: true,
                minlength: 2
            },
            endTime: {
                required: true,
                minlength: 2
            },
            actualEndTime: {
                required: true,
                minlength: 2
            },
            recordCreatedTime: {
                required: true,
                minlength: 2
            },
            pickupTime: {
                required: true,
                minlength: 2
            },
            recordDes: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            checkinId: "请选择维修保养登记人",
            startTime: "请选择维修保养开始时间",
            endTime: "请选择维修保养预估结束时间",
            actualEndTime: "请选择维修保养实际结束时间",
            recordCreatedTime: "请选择维修保养记录创建时间",
            pickupTime: "请选择维修保养车主提车时间",
            recordDes: "请输入维修保养记录描述",
        },
        errorPlacement: function (error, element) {
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success: function (label) {
            var el = label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function (form) {
            $.post("/maintainRecord/update",
                $("#editForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#editWindow").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title: "",
                            text: data.message,
                            confirmButtonText: "确定", // 提示按钮上的文本
                            type: "success"
                        })// 提示窗口, 修改成功
                    } else if (data.result == "fail") {
                        swal({
                            title: "",
                            text: "修改失败",
                            confirmButtonText: "确认",
                            type: "error"
                        })
                    }
                }, "json"
            );
        }
    })
});