

/**
 * 初始化表格
 */
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable("table", "/incomingOutgoing/queryByPager"); // 初始化表格
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

//格式化不带时分秒的时间值。
function formatterDate(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    } else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day + ""
    }
}

/**
 * 收支类型状态判断显示
 * @param value
 * @param row
 * @param index
 * @returns {*}
 */
function ioTypeFormatter(value, row, index) {
    if (row.outgoingType != null) {
        return "支出类型:" +row.outgoingType.outTypeName
    } else {
        return "收入类型:" +row.incomingType.inTypeName
    }
}

/**
 * 状态显示
 * @param value
 * @returns {*}
 */
function statusFormatter(value) {
    /*处理数据*/
    if (value == 'Y') {
        return "&nbsp;&nbsp;可用";
    } else {
        return "&nbsp;&nbsp;禁用";
    }

}

function returnButton() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable("table", "/incomingOutgoing/queryByPager"); // 初始化表格
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
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var value = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/incomingOutgoing/blurredQuery?text='+text+'&value='+value);
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
 * 查询禁用支出类型
 * @param id
 */
function searchDisableStatus() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/incomingOutgoing/queryByPagerDisable');
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
            initTable("table", "/incomingOutgoing/queryByPager"); // 初始化表格
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
 * 操作禁用激活
 * @param index
 * @param row
 * @returns {string}
 */
function openStatusFormatter(value, row) {
    /*处理数据*/
    if (value == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='active(\""+'/incomingOutgoing/statusOperate?id='+row.inOutId+'&status=Y'+"\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/incomingOutgoing/statusOperate?id='+row.inOutId+'&status=N'+"\")'>激活</a>";
    }

}


function showEdit(){

    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#editButton").removeAttr("disabled");
            var row =  $('table').bootstrapTable('getSelections');
            if(row.length >0) {
                $("#editIOWin").modal('show'); // 显示弹窗
                var io = row[0];
                $("#editIOWin").fill(io);
                validator("editIOForm");
                if ($("#inType").val() == "") {
                    $("#inTypeDiv").hide();
                }
                if ($("#outType").val() == "") {
                    $("#outTypeDiv").hide();
                }

            }else{
                swal({
                    title:"",
                    text:"请先选择要修改的收支记录",
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

function outAddWin(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#addOutWin").modal('show');
            $("#addOutButton").removeAttr("disabled");
            validator("addOutForm");
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

function inAddWin(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#addInWin").modal('show');
            $("#addInButton").removeAttr("disabled");
            validator("addInForm");
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

$("#addOutForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

$("#addInForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

$("#editIOForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});


/** 选择支出类型窗体 */
function openCheckOutType() {
    initTableNotTollbar("outTable", "/outGoingType/queryByPager");
    $('#addOutForm').data('bootstrapValidator').resetForm();
    $("#addOutWin").modal('hide');
    $("#outWin").modal('show');
}


/** 关闭支出类型 */
function closeOutTypeWin() {
    $("input[type=reset]").trigger("click");

    $("#outWin").modal('hide');
    $("#addOutWin").modal('show')
}

/** 选择支出类型 */
function checkOutType () {
    var selectRow = $("#outTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal({
            title:"",
            text:"请先选择支出类型",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
        return false;
    } else {
        $("#outWin").modal('hide');
        $("#addOutWin").modal('show');
        var outType = selectRow[0];
        $("#outTypeName").val(outType.outTypeName);
        $("#outTypeId").val(outType.outTypeId);
        $("#addOutWin").modal('show');
    }
}

/** 选择人员 */
function checkAppointment() {
    initTableNotTollbar("appTable", "/userBasicManage/queryByPager");
    $("#add").modal('hide');
    $("#personnelWin").modal('show');
}


/** 关闭人员管理窗口 */
function closePersonnelWin() {
    $("input[type=reset]").trigger("click");
    $("#personnelWin").modal('hide');
    $("#add").modal('show')
}

/** 选择人员 */
function checkPersonnel () {
    var selectRow = $("#appTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal({
            title:"",
            text:"请先选择收入类型",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
        return false;
    } else {
        $("#personnelWin").modal('hide');
        $("#add").modal('show');
        var user = selectRow[0];
        $("#userName").val(user.userName);
        $("#userId").val(user.userId);
        $("#addWin").modal('show');
    }
}

/** 选择收入类型窗体 */
function inOpenCheckInType() {
    initTableNotTollbar("inTable", "/incomingType/queryByPager");
    $('#addInForm').data('bootstrapValidator').resetForm();
    $("#addInWin").modal('hide');
    $("#inWin").modal('show');
}


/** 关闭收入类型 */
function inCloseInTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#inWin").modal('hide');
    $("#addInWin").modal('show')
}



/** 选择收入类型 */
function inCheckInType () {
    var selectRow = $("#inTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal({
            title:"",
            text:"请先选择收入类型",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
        return false;
    } else {
        $("#inWin").modal('hide');
        $("#addInWin").modal('show');
        var inType = selectRow[0];
        $("#inTypeName").val(inType.inTypeName);
        $("#inTypeId").val(inType.inTypeId);
        $("#addinWin").modal('show');
    }
}

/** 选择人员 */
function inCheckAppointment() {
    initTableNotTollbar("inAppTable", "/userBasicManage/queryByPager");
    $("#addInWin").modal('hide');
    $("#inPersonnelWin").modal('show');
}


/** 关闭人员管理窗口 */
function inClosePersonnelWin() {
    $("input[type=reset]").trigger("click");
    $("#inPersonnelWin").modal('hide');
    $("#addInWin").modal('show')
}

/** 选择人员 */
function inCheckPersonnel () {
    var selectRow = $("#inAppTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        $("#inPersonnelWin").modal('hide');
        $("#addInWin").modal('show');
        var user = selectRow[0];
        $("#inUserName").val(user.userName);
        $("#inUserId").val(user.userId);
        $("#addWin").modal('show');
    }
}




/** 选择支出类型窗体 */
function updateOpenCheckOutType() {
    initTableNotTollbar("updateOutTable", "/outGoingType/queryByPager");

    $("#editIOWin").modal('hide');
    $("#updateOutWin").modal('show');
}


/** 关闭支出类型 */
function updateCloseOutTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#updateOutWin").modal('hide');
    $("#editIOWin").modal('show')
}

/** 选择支出类型 */
function updateCheckOutType () {
    var selectRow = $("#updateOutTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        $("#updateOutWin").modal('hide');
        $("#editIOWin").modal('show');
        var outType = selectRow[0];
        $("#outType").val(outType.outTypeName);
        $("#updateOutTypeId").val(outType.outTypeId);
    }
}

/**
 * 选择人员
 */
function updateCheckAppointment() {
    initTableNotTollbar("updateAppTable", "/userBasicManage/queryByPager");
    $("#editIOWin").modal('hide');
    $("#updatePersonnelWin").modal('show');
}


/** 关闭人员管理窗口 */
function updateClosePersonnelWin() {
    $("input[type=reset]").trigger("click");
    $("#updatePersonnelWin").modal('hide');
    $("#editIOWin").modal('show')
}

/** 选择人员 */
function updateCheckPersonnel () {
    var selectRow = $("#updateAppTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        $("#updatePersonnelWin").modal('hide');
        var user = selectRow[0];
        $("#updateUserName").val(user.userName);
        $("#updateUserId").val(user.userId);
        $("#editIOWin").modal('show');
    }
}


function updateInOpenCheckInType() {
    initTableNotTollbar("updateInTable", "/incomingType/queryByPager");
    $("#editIOWin").modal('hide');
    $("#updateInWin").modal('show');
}


/** 关闭收入类型 */
function updateInCloseInTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#updateInWin").modal('hide');
    $("#editIOWin").modal('show')
}



/** 选择收入类型 */
function updateInCheckInType () {
    var selectRow = $("#updateInTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal('选择失败', "只能选择一条数据", "error");
        return false;
    } else {
        $("#updateInWin").modal('hide');
        var inType = selectRow[0];
        console.log(inType.inTypeName + inType.inTypeId)
        $("#inType").val(inType.inTypeName);
        $("#updateInTypeId").val(inType.inTypeId);
        $("#editIOWin").modal('show');
    }
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
                validators: {
                    notEmpty: {
                        message: '请选择收支类型',
                    },
                }
            },
            outTypeName: {
                validators: {
                    notEmpty: {
                        message: '请选择收支类型',
                    },
                }
            },
            inOutMoney: {
                validators: {
                    notEmpty: {
                        message: '请输入收支金额',
                    },
                    numeric: {message: '请输入合法的数字'}
                }
            },

            inOutCreatedUserName: {
                message: '请选择创建人',
                validators: {
                    notEmpty: {
                        message: '请选择创建人',
                    }

                }
            },
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addOutForm") {
                formSubmit("/incomingOutgoing/add", formId, "addOutWin");
            } else if (formId == "addInForm") {
                formSubmit("/incomingOutgoing/add", formId, "addInWin");
            } else if (formId == "editIOForm") {
                formSubmit("/incomingOutgoing/update", formId, "editIOWin");
            }
        })

}

function addInSubmit() {
    $("#addInForm").data('bootstrapValidator').validate();
    if ($("#addInForm").data('bootstrapValidator').isValid()) {
        $("#addInButton").attr("disabled", "disabled");
    } else {
        $("#addInButton").removeAttr("disabled");
    }
}

function addOutSubmit() {
    $("#addOutForm").data('bootstrapValidator').validate();
    if ($("#addOutForm").data('bootstrapValidator').isValid()) {
        $("#addOutButton").attr("disabled", "disabled");
    } else {
        $("#addOutButton").removeAttr("disabled");
    }
}

function editSubmit() {
    $("#editIOForm").data('bootstrapValidator').validate();
    if ($("#editIOForm").data('bootstrapValidator').isValid()) {
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
                if (formId == 'addOutForm') {
                    $('#table').bootstrapTable('refresh');
                    $("input[type=reset]").trigger("click"); // 移除表单中填的值
                    $("#addOutButton").removeAttr("disabled"); // 移除不可点击
                }
                if  (formId == 'addInForm') {
                    $('#table').bootstrapTable('refresh');
                    $("input[type=reset]").trigger("click"); // 移除表单中填的值
                    $("#addInButton").removeAttr("disabled"); // 移除不可点击
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
}

