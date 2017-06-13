$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/charge/queryByPager'); // 初始化表格
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

function currentStatusFormatter(value, row, index){
    if(value != 'N'){
        return "用户已确认";
    }else{
        return "用户未确认";
    }
}

// 激活或禁用
function statusFormatter(value, row, index) {
            if(value == 'Y') {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/charge/statusOperate?id='+row.chargeBillId+'&status=Y'+"\")'>禁用</a>";
            } else {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/charge/statusOperate?id='+ row.chargeBillId+'&status=N'+ "\")'>激活</a>";
            }
}

// 查看全部可用
function showAvailable(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/charge/queryByPager');
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
function showDisable(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/charge/queryByPagerDisable');
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

// 模糊查询
function blurredQuery(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/charge/blurredQuery?text='+text+'&value='+vaule);
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

function showMoney(){
    var row =  $('#table').bootstrapTable('getSelections');
    if(row.length ==1) {
        if(row[0].actualPayment != null && row[0].actualPayment != ""){
            if(row[0].maintainRecord.currentStatus !='已收费'){
                if(row[0].cdStatus !="N") {
                    swal({
                        title: "",
                        text: "是否确认已经收到维修保养费用!",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "我确定",
                        cancelButtonText: "再考虑一下",
                        closeOnConfirm: true,
                        closeOnCancel: false
                    }, function (ifCor) {
                        if (ifCor) {
                            $("#inOutMoneyId").val(row[0].actualPayment);
                            $("#addWin").modal('show');
                            validator1("addForm", row[0].chargeBillId, row[0].maintainRecordId);
                        } else {
                            swal({
                                title: "",
                                text: "已取消",
                                confirmButtonText: "确认",
                                type: "error"
                            });
                        }
                    })
                }else{
                    // 未用户确认
                    swal({
                        title: "",
                        text: "该收费单据用户未确认， 确定确认收到维修保养费用了吗?",
                        type: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#DD6B55",
                        confirmButtonText: "我确定",
                        cancelButtonText: "再考虑一下",
                        closeOnConfirm: true,
                        closeOnCancel: false
                    }, function (ifCor) {
                        if (ifCor) {
                            $("#inOutMoneyId").val(row[0].actualPayment);
                            $("#addWin").modal('show');
                            validator1("addForm", row[0].chargeBillId, row[0].maintainRecordId);
                        } else {
                            swal({
                                title: "",
                                text: "已取消",
                                confirmButtonText: "确认",
                                type: "error"
                            });
                        }
                    })
                }
            }else{
                swal({
                    title:"",
                    text: "此记录已确认收费!", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
        }else{
                    swal({
                        title:"",
                        text: "此记录还未收到维修保养费用, 不能确认收费", // 主要文本
                        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type:"warning"}) // 提示类型
                }
    }else{
        swal({
            title:"",
            text: "请先选择要确认收费的记录且只能选择一条", // 主要文本
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText:"确定", // 提示按钮上的文本
            type:"warning"}) // 提示类型
    }
}


/** 选择收入类型窗体 */
function inOpenCheckInType() {
    initTableNotTollbar("inTable", "/incomingType/queryByPager");
    $("#addWin").modal('hide');
    $("#inWin").modal('show');
    validator1("")
}

function inCheckInType(){
    $("#inWin").modal('hide');
    $("#addWin").modal('show');
    var row = $("#inTable").bootstrapTable('getSelections');
    $("#inTypeId").val(row[0].inTypeId);
    $("#inTypeName").val(row[0].inTypeName);
}

function editSubmit(){
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled", "disabled");
    } else {
        $("#editButton").removeAttr("disabled");
    }
}

function showEdit(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#table').bootstrapTable('getSelections');
            if(row.length >0) {
                if(row[0].maintainRecord.currentStatus != '已收费'){
                    $("#editWindow").modal('show'); // 显示弹窗
                    var chargeBill = row[0];
                    $("#editForm").fill(chargeBill);
                    $("#editPaymentMethod").val(chargeBill.paymentMethod);
                    $('#chargeCreatedTime').val(formatterDate(chargeBill.chargeCreatedTime))
                    validator('editForm'); // 初始化验证
                }else{
                    swal({
                        title:"",
                        text: "请收费单据已经确认， 不可再次修改", // 主要文本
                        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type:"error"}) // 提示类型
                }
            }else{
                swal({
                    title:"",
                    text: "请先选择要修改的单据", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
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

// 所有明细window中的打印按钮
function showPrint(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
                var row =  $('#table').bootstrapTable('getSelections'); // 选中的维修保养记录
                if(row.length >0) {
                    window.parent.addChargeBillPrint(row[0].chargeBillId);
                }else{
                    swal({
                        title:"",
                        text: "请先选择要修改的单据", // 主要文本
                        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type:"warning"}) // 提示类型
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

function addInSubmit() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").attr("disabled", "disabled");
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
            paymentMethod: {
                message: '付款方式验证失败',
                validators: {
                    notEmpty: {
                        message: '付款方式不能为空'
                    }
                }
            },
            chargeBillMoney: {
                message: '总金额验证失败',
                validators: {
                    notEmpty: {
                        message: '总金额不能为空'
                    }
                }
            },
            actualPayment: {
                message: '实际付款验证失败',
                validators: {
                    notEmpty: {
                        message: '实际付款不能为空'
                    }
                }
            },chargeTime: {
                message: '收费时间验证失败',
                validators: {
                    notEmpty: {
                        message: '收费时间不能为空'
                    }
                }
            },chargeBillDes: {
                message: '收费单据描述验证失败',
                validators: {
                    notEmpty: {
                        message: '收费单据描述不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit("/charge/edit", formId, "editWindow");
            }
        })
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
                    type:"success"
                })// 提示窗口, 修改成功
                $('#table').bootstrapTable('refresh');
                $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
            } else if (data.result == "fail") {
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
                $("#"+formId).removeAttr("disabled");
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

function validator1(formId, chargeBillId, recordId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inTypeName: {
                message: '收入类型验证失败',
                validators: {
                    notEmpty: {
                        message: '收入类型不能为空'
                    }
                }
            },
            inOutMoney: {
                message: '收入金额验证失败',
                validators: {
                    notEmpty: {
                        message: '收入金额不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if(formId == 'addForm'){
                formSubmit1("/incomingOutgoing/add", formId, "addWin", chargeBillId, recordId);
            }
        })
}

function formSubmit1(url, formId, winId, chargeBillId, recordId){
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
              $.post("/charge/updateDate/" + chargeBillId + "/" + recordId, function (data) {
                  if (data.result == "success") {
                      $('#' + winId).modal('hide');
                      swal({
                          title: "",
                          text: data.message,
                          confirmButtonText: "确定", // 提示按钮上的文本
                          type: "success"
                      })// 提示窗口, 修改成功
                      $('#table').bootstrapTable('refresh');
                      $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                      $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
                  }else if (data.result == "fail") {
                      swal({title:"",
                          text:data.message,
                          confirmButtonText:"确认",
                          type:"error"})
                      $("#"+formId).removeAttr("disabled");
                  }else if (data.result == "notLogin") {
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
                  }
               });
            }else if (data.result == "fail") {
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
                $("#"+formId).removeAttr("disabled");
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
                }
                if(formId == 'addForm') {
                    $("#addButton").removeAttr("disabled");
                }else if(formId == 'editForm'){
                    $("#editButton").removeAttr("disabled");
                }
        }, "json");
}