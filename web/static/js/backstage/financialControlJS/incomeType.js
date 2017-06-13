

/**
 * 初始化表格
 */
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable("table", "/incomingType/queryByPager"); // 初始化表格
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

});


/**
 * 页面状态显示
 * @param index
 * @param row
 * @returns {*}
 */
function statusFormatter(index, row) {
    /*处理数据*/
    if(row.inTypeStatus == 'Y') {
        return "&nbsp;&nbsp;可用";
    } else {
        return "&nbsp;&nbsp;禁用";
    }

}

/**
 * 激活禁用显示
 * @param index
 * @param row
 * @returns {string}
 */
function openStatusFormatter(index, row) {
    /*处理数据*/
    if(row.inTypeStatus == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='active(\""+'/incomingType/statusOperate?id='+row.inTypeId+'&status=Y'+"\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='active(\""+'/incomingType/statusOperate?id='+row.inTypeId+'&status=N'+"\")'>激活</a>";
    }

}


/**
 * 查询禁用支出类型
 * @param id
 */
function searchDisableStatus() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/incomingType/queryByPagerDisable');
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

/**
 * 查询激活支出类型
 * @param id
 */
function searchRapidStatus() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/incomingType/queryByPager');
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


/**
 * 修改窗口
 */
function showEdit(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#editButton").removeAttr("disabled");
            var row =  $('table').bootstrapTable('getSelections');
            if(row.length >0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
                $("#edit").modal('show'); // 显示弹窗
                var incomingType = row[0];
                $("#editForm").fill(incomingType);
                validator("editForm");
            }else{
                swal({
                    title:"",
                    text:"请先选择要修改的收入类型",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
            }
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

/**
 * 添加窗口
 */
function showAdd(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#inTypeName").val("");
            $("#add").modal('show');
            $("#addButton").removeAttr("disabled");
            validator("addForm");
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

/**
 * 前台验证
 * @param formId
 */
function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inTypeName: {
                message: '收入类型名称不能为空',
                validators: {
                    notEmpty: {
                        message: '收入类型名称不能为空',
                        remote:'该类型已经存在'
                    },
                    remote: {
                        url: '/incomingType/checkInTypeName',//验证收入类型名称
                        message: '该类型已存在',//提示消息
                        data: {
                            inTypeId: $("#" + formId + " input[name=inTypeId]").val(),
                            inTypeName: $("#" + formId + " input[name=inTypeName]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }

                }
            },
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/incomingType/add", formId, "add");

            } else if (formId == "editForm") {
                formSubmit("/incomingType/update", formId, "edit");

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

function formSubmit(url, formId, winId){
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $('#' + winId).modal('hide');
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"})// 提示窗口, 修改成功
                $('#table').bootstrapTable('refresh');
                if(formId == 'addForm'){
                    $("input[type=reset]").trigger("click"); // 移除表单中填的值
                    $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
                }
                $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
            } else if (data.result == "fail") {
                swal({title:"",
                    text:"添加失败",
                    confirmButtonText:"确认",
                    type:"error"})
                $("#"+formId).removeAttr("disabled");
            }
        }, "json");
}
