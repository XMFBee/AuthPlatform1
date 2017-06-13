$(function () {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            initTable('table', '/messageSend/queryByPager'); // 初始化表格

            $(".messageSend").select2({
                tags: true,
                language: 'zh-CN',
                minimumResultsForSearch: -1,
                maximumSelectionLength: 5,
                placeholder: '请选择车主',
                ajax: {
                    url: "/messageSend/queryCombox",
                    processResults: function (data, page) {
                        var parsed = data;
                        var arr = [];
                        for (var x in parsed) {
                            arr.push(parsed[x]);
                        }
                        return {
                            results: arr
                        };
                    }
                },

            });
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
    // $("#addButton").click(function(){
    //     var v =$("#addUserName").select2("val");
    //     $("#addUserName").val(v);
    //     $("#addForm").submit();//表单的id
    // });
});

function formatterSendMsg(value, row, index) {
    return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='sendMsgShow(\""+'/messageSend/queryById/'+ row.messageId+"\")'>查看短信内容</a>";
}

function sendMsgShow(url) {
    $.get(url, function(data){
            // alert(data.sendMsg);
            document.getElementById('sendMsgText').value = data.sendMsg;
        },"json");
    $("#sendMsgWindow").modal('show');
    document.getElementById('sendMsgText').readOnly = true;
}

function closesendMsgWindow() {
    $("#sendMsgWindow").modal('hide');
}

function messageTypeChange(obj) {
    if(obj.value == '模板1') {
        document.getElementById('sendMsgDiv').style.display='';
        // document.getElementById('addRemindMsg').style.display='none'; //div隐藏
        document.getElementById("addSendMsg").value = "【汽车之家】亲，本店开启了促销活动， 快来给你的爱车进行维修保养吧~";
        document.getElementById("addSendMsg").readOnly = true;
    } else if(obj.value == '模板2') {
        document.getElementById('sendMsgDiv').style.display='';
        document.getElementById("addSendMsg").value = "【汽车之家】尊敬的车主您好， 您已经很久没有进行维修保养了， 快来预约吧。";
        document.getElementById("addSendMsg").readOnly = true;
    }
}

function closeMessageSendModals() {
    $("#sendMsgWindow").modal('hide');
}

// 模糊查询
function blurredQuery() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/messageSend/blurredQuery?text=' + text + '&value=' + vaule);
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
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            var row = $("#table").bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var MessageSend = row[0];
                $("#editForm").fill(MessageSend);
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

//显示添加
function showAdd() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm'); // 初始化验证
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

function validator(formId, userIds, userPhone) {
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
            sendTime: {
                message: '发送时间验证失败',
                validators: {
                    notEmpty: {
                        message: '发送时间不能为空'
                    }
                }
            },
            sendCreatedTime: {
                message: '发送记录创建时间验证失败',
                validators: {
                    notEmpty: {
                        message: '发送记录创建时间不能为空'
                    }
                }
            },
            sendMsg: {
                message: '短信内容验证失败',
                validators: {
                    notEmpty: {
                        message: '短信内容不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '短信内容不能超过500个字符'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/messageSend/insert/" + userIds + "/" + userPhone, formId, "addWindow");

            } else if (formId == "editForm") {
                formSubmit("/messageSend/update", formId, "editWindow");
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
                    // $("#addUserName").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
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

function showMessageSend() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            $("#showMessageSendWindow").modal('show');
            document.getElementById('sendMsgDiv').style.display='none'; //div隐藏
            initTableMessageNotTollbar("showMessageSendTable", "/maintainRecord/queryByPagerSuccess");
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

function closeMessageSend() {
    document.getElementById('sendMsgDiv').style.display='none'; //div隐藏
    $("#addWindow").modal('hide');
    $("#showMessageSendWindow").modal('show');
    $("#addForm").data('bootstrapValidator').destroy(); // 销毁此form表单
    $("#addForm").data('bootstrapValidator', null);// 此form表单设置为空
}


function showMessageSendUser() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == 'success') {
            var row = $('#showMessageSendTable').bootstrapTable('getSelections');
            if (row.length < 1) {
                swal({
                    title: "",
                    text: "请先选择需要短信提醒的车主", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
            } else if (row.length >= 1) {
                var userIds = "";
                var userPhone = "";
                $.each(row, function (index, value, item) {
                    if (userIds == "") {
                        userIds = row[index].checkin.user.userId;
                    } else {
                        userIds += "," + row[index].checkin.user.userId
                    }
                    if (userPhone == "") {
                        userPhone = row[index].checkin.user.userPhone;
                    } else {
                        userPhone += "," + row[index].checkin.user.userPhone;
                    }
                });
                $("#addSendMsg").val(row[0].sendMsg);
                $("#showMessageSendWindow").modal('hide');
                $("#addWindow").modal('show');
                $("#addButton").removeAttr("disabled");
                validator('addForm', userIds, userPhone); // 初始化验证

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

function closeMessageSendUserWin() {
    $("#showMessageSendWindow").modal('hide');
}

function initTableMessageNotTollbar(tableId, url) {
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
        toolbar: "#messageSendToolbar",//排序方式
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