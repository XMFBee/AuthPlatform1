$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            initTable('table', '/maintainRemind/queryByPager'); // 初始化表格

            // initSelect2("user", "请选择用户", "/maintainRemind/queryCombox");
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
});

function formatterreMindMsg(value, row, index) {
    return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='mindMsgShow(\""+'/maintainRemind/queryById/'+ row.remindId+"\")'>查看保养提醒消息</a>";
}

function mindMsgShow(url) {
    $.get(url, function(data){
        // alert(data.sendMsg);
        document.getElementById('remindMsgText').value = data.remindMsg;
    },"json");
    $("#remindMsgWindow").modal('show');
    document.getElementById('remindMsgText').readOnly = true;
}

// 模糊查询
function blurredQuery(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/maintainRemind/blurredQuery?text=' + text + '&value=' + vaule);
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

function showRemindUser() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            $("#RemindUserWindow").modal('show');
            initTableRemindNotTollbar("showRemindUserTable", "/maintainRemind/queryByPagerNull");
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

function closeRemindUserWin() {
    $("#RemindUserWindow").modal('hide');
}

// function layuiDateTime(){
//     layui.use('laydate', function () {
//         layui.laydate({
//             format: 'yyyy-MM-dd hh:mm:ss',
//             max: '2099-12-30 23:59:59',
//             istime: true,
//             istoday: false,
//             festival: true
//         })
//     })
// }

//显示弹窗
function showEdit() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            var row = $("#table").bootstrapTable('getSelections');
            if (row.length > 0) {
                // alert(row[0].remindId);
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled"); // 移除不可点击
                var MaintainRemind = row[0];
                $("#editForm").fill(MaintainRemind);
                $("#editLastMaintainTime").val(formatterDate(MaintainRemind.lastMaintainTime));
                $("#editRemindTime").val(formatterDate(MaintainRemind.remindTime));
                $("#editRemindCreatedTime").val(formatterDate(MaintainRemind.remindCreatedTime));
                $('#editUserId').val(MaintainRemind.user.userId);
                $('#editUserName').val(MaintainRemind.user.userName);
                validator('editForm');
            } else {
                swal({
                    "title": "",
                    "text": "请先选择一条数据",
                    "type": "warning"
                })
            }
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

function showCheckUser() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            $("#showUserWindow").modal('show');
            initTableNotTollbar("addUserTable", "/maintainRemind/queryByPagerNull");
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

function closeUserWin() {
    $("#showUserWindow").modal('hide');
    // $("#addWindow").modal('show');
}

function checkUser() {
    var row = $('#addUserTable').bootstrapTable('getSelections');
    if (row.length != 1) {
        swal({
            "title": "",
            "text": "只能选择一条数据",
            "type": "warning"
        })
    } else {
        // alert(row[0].user.userId);
        // alert(row[0].user.userName);
        $("#addRemindId").val(row[0].remindId);
        $("#addLastMaintainTime").val(formatterDate(row[0].lastMaintainTime));
        $("#addLastMaintainMileage").val(row[0].lastMaintainMileage);
        $('#addUserId').val(row[0].user.userId);
        $('#addUserName').val(row[0].user.userName);
        $("#showUserWindow").modal('hide');
    }
}

// //显示添加
// function showAdd() {
//     $("#addWindow").modal('show');
//     $("#addButton").removeAttr("disabled");
//     validator('addForm'); // 初始化验证
// }

function showAddRemindUser() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            initDateTimePicker('addForm', 'lastMaintainTime', 'addLastMaintainTime');
            var row = $('#showRemindUserTable').bootstrapTable('getSelections');
            if (row.length != 1) {
                swal({
                    title: "",
                    text: "请先选择需要保养提醒的车主", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                }) // 提示类型
            } else {
                // alert(row[0].checkin.user.userId);
                // alert(row[0].checkin.user.userName);
                $("#addRemindId").val(row[0].remindId);
                $("#addLastMaintainTime").val(formatterDate(row[0].actualEndTime));
                $("#addLastMaintainMileage").val(row[0].checkin.carMileage);
                $('#addUserId').val(row[0].checkin.user.userId);
                $('#addCheckinName').val(row[0].checkin.user.userName);
                $('#addUserName').val(row[0].checkin.user.userName);
                $("#addUserEmail").val(row[0].checkin.user.userEmail);
                $("#addUserPhone").val(row[0].checkin.user.userPhone);
                $("#RemindUserWindow").modal('hide');
                $("#addWindow").modal('show');
                $("#addButton").removeAttr("disabled");
                validator('addForm'); // 初始化验证
            }
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

function closeaddWindow() {
    $("#addWindow").modal('hide');
    $("#RemindUserWindow").modal('show');
    $("#addForm").data('bootstrapValidator').destroy(); // 销毁此form表单
    $("#addForm").data('bootstrapValidator', null);// 此form表单设置为空
}

function remindTypeChange(obj) {
    if(obj.value == '短信提醒') {
        // document.getElementById('addRemindMsg').style.display='none'; //div隐藏
        document.getElementById("addRemindMsg").value = "【汽车之家】尊敬的"+$('#addCheckinName').val()+"车主， 你的爱车已经需要再次维修保养了， 请速速前来本店。 ";
        document.getElementById("addRemindMsg").readOnly = true;
    } else if(obj.value == '邮箱提醒') {
        document.getElementById("addRemindMsg").value = "";
        document.getElementById("addRemindMsg").readOnly = false;
    }
}

function closeRemindModals() {
    $("#remindMsgWindow").modal('hide');
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
                message: '车主验证失败',
                validators: {
                    notEmpty: {
                        message: '车主不能为空'
                    }
                }
            },
            // lastMaintainTime: {
            //     message: '上次维修保养时间验证失败',
            //     validators: {
            //         notEmpty: {
            //             message: '上次维修保养时间不能为空'
            //         }
            //     }
            // },
            // lastMaintainMileage: {
            //     message: '上次汽车行驶里程验证失败',
            //     validators: {
            //         notEmpty: {
            //             message: '上次汽车行驶里程不能为空'
            //         }
            //     }
            // },
            remindMsg: {
                message: '维修保养提醒内容验证失败',
                validators: {
                    notEmpty: {
                        message: '维修保养提醒内容不能为空'
                    }
                }
            },
            remindTime: {
                message: '维修保养提醒时间验证失败',
                validators: {
                    notEmpty: {
                        message: '维修保养提醒时间不能为空'
                    }
                }
            },
            remindType: {
                message: '维修保养提醒方式验证失败',
                validators: {
                    notEmpty: {
                        message: '维修保养提醒方式不能为空'
                    }
                }
            },
            remindCreatedTime: {
                message: '提醒记录创建时间验证失败',
                validators: {
                    notEmpty: {
                        message: '提醒记录创建时间不能为空'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/maintainRemind/insert", formId, "addWindow");

            } else if (formId == "editForm") {
                formSubmit("/maintainRemind/update", formId, "editWindow");
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
                    // $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
                }
                $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
            } else if (data.result == "fail") {
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                })
                if (formId == 'addForm') {
                    $("#addButton").removeAttr("disabled");
                } else if (formId == 'editForm') {
                    $("#editButton").removeAttr("disabled");
                }
            } else if (data.result == "notLogin") {
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
                if (formId == 'addForm') {
                    $("#addButton").removeAttr("disabled");
                } else if (formId == 'editForm') {
                    $("#editButton").removeAttr("disabled");
                }
            }
        }, "json");
}

function initTableRemindNotTollbar(tableId, url) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        showToggle: true, // 显示详情
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",
        toolbar: "#remindToolbar",//排序方式
        sidePagination: "server", //表示服务端请求


        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum: $("#orderNum").val()
            };
            return param;
        },
    });
}