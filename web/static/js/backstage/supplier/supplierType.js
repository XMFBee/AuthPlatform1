$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if (data.result == 'success') {

            initTable("table", "/supplyType/queryByPager"); // 初始化表格
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
    });

});


function showEdit(){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if (data.result == 'success') {
            var row = $('#table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var supplyType = row[0];
                $('#editCompanyName').html('<option value="' + supplyType.company.companyId + '">' + supplyType.company.companyName + '</option>').trigger("change");
                $("#editForm").fill(supplyType);
                validator('editForm');
                /*$('#editSTName').bind('input propertychange', function() {
                    userPhone = $("#editSTName").val();
                });*/
            } else {
                swal({
                    title: "",
                    text: "请先选择要修改的供应商类型", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                }) // 提示类型
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

function showAdd(){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if (data.result == 'success') {
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm'); // 初始化验证
        }else if (data.result == 'notLogin') {
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

function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            supplyTypeName: {
                message: '供应商类型名称验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商类型名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '供应商类型名称长度必须在1到6位之间'
                    },
                    remote: {
                        url: '/supplyType/queryNameByOne',
                        message: '该供应商类型名称已存在',
                        data: {
                            supplyTypeId: $("#" + formId + " input[name=supplyTypeId]").val(),
                            supplyTypeName: $("#" + formId + " input[name=supplyTypeName]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'GET'
                    }
                }
            },
            companyId: {
                message: '供应商类型所属公司验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商类型所属公司不能为空'
                    }
                }
            }/*,
            supplyTypeDes: {
                message: '供应商类型描述内容验证失败',
                validators: {
                    notEmpty: {
                        message: '供应商类型描述内容不能为空'
                    }
                }
            }*/
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/supplyType/addSupplyType", formId, "addWindow");

            } else if (formId == "editForm") {
                formSubmit("/supplyType/updateSupplyType", formId, "editWindow");

            }
        })

}

function addSubmit(){
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").attr("disabled","disabled");
    } else {
        $("#addButton").removeAttr("disabled");
    }
}

function editSubmit(){
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled","disabled");
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
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
                    // 设置select2的值为空
                    $("#addCompanyName").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
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
                if(formId == 'addForm') {
                    $("#addButton").removeAttr("disabled");
                }else if(formId == 'editForm'){
                    $("#editButton").removeAttr("disabled");
                }
            }else if (data.result == "notLogin") {
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
                if(formId == 'addForm') {
                    $("#addButton").removeAttr("disabled");
                }else if(formId == 'editForm'){
                    $("#editButton").removeAttr("disabled");
                }
            }
        }, "json");
}



// 激活或禁用
function statusFormatter(value, row, index) {
    if(value == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/supplyType/statusOperate?id='+row.supplyTypeId+'&status=Y'+"\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/supplyType/statusOperate?id='+ row.supplyTypeId+'&status=N'+ "\")'>激活</a>";
    }
}

// 查看全部可用
function searchRapidStatus(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/supplyType/queryByPager');
        }else if(data.result == 'notLogin'){
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
        }else if(data.result == 'notRole'){
            swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"})
        }
    });
}
// 查看全部禁用
function searchDisableStatus(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/supplyType/queryByPagerDisable');
        }else if(data.result == 'notLogin'){
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
        }else if(data.result == 'notRole'){
            swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"})
        }
    });
}

/*回车登录*/
function keydown(buttonId){
    if(event.keyCode == 13){
        document.getElementById(buttonId).click();
    }
}



