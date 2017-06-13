$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/appointment/queryByPager'); // 初始化表格
            initSelect2("carBrand", "请选择品牌", "/carBrand/queryAllCarBrand");
            initSelect2("carColor", "请选择颜色", "/carColor/queryAllCarColor");
            initSelect2("carPlate", "请选择车牌", "/carPlate/queryAllCarPlate");

            $("#app").bootstrapSwitch({
                onText:"是",
                offText:"否",
                onColor:"success",
                offColor:"danger",
                size:"small",
                onSwitchChange:function(event,state){
                    if(state==true){
                        initTableNotTollbar("appTable", "/userBasicManage/queryCarByRoleName");
                        $('#addForm').data('bootstrapValidator').resetForm();
                        $("#appWindow").modal('show');
                        $("#addWindow").modal("hide");
                    }else if(state==false){
                        $("#addUserName").attr("readOnly",false);
                        $("#addUserPhone").attr("readOnly",false);
                        clearAddForm();
                    }
                }
            })
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


$("#addCarBrand").change(function(){
    var div = $("#addModelDiv");
    var select = $("#addCarBrand").select2("val");
    div.css("display","block");
    $('#addCarModel').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    initSelect2("carModel", "请选择车型", "/carModel/queryByBrandId/"+ select);
});

$("#editCarBrand").change(function(){
    var div = $("#editModelDiv");
    var select = $("#editCarBrand").select2("val");
    div.css("display","block");
    $('#editCarModel').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    initSelect2("carModel", "请选择车型", "/carModel/queryByBrandId/"+select);
});

// 查看全部可用
function showAvailable() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/appointment/queryByPager'); // 初始化表格
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
function showDisable() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/appointment/queryByPagerDisable');
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
function statusFormatter(value, row, index) {
            if(value == 'Y') {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/appointment/statusOperate?appointmentId='+row.appointmentId+'&appoitmentStatus=Y'+"\")'>禁用</a>";
            } else {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/appointment/statusOperate?appointmentId='+ row.appointmentId+'&appoitmentStatus=N'+ "\")'>激活</a>";
            }
}

function formatterGender(value, row, index){
    if(row.userGender != 'N'){
        return "女";
    }else if(row.userGender != 'M'){
        return "男";
    }else{
        return "未选择";
    }
}

/** 判断是否选中 */
function checkAppointment(combox) {
    var val = combox.value;
    if (val == "Y") {
        //调用函数，初始化表格
        initTableNotTollbar("appTable", "/appointment/queryByPager");
        //当点击查询按钮的时候执行
        $("#search").bind("click", initTable);
        $("#addWindow").modal('hide');
        $("#appWindow").modal('show');
    } else {
        $("#appWindow").modal('hide');
        $("#addWindow").modal('show');
        $("input[type=reset]").trigger("click");
    }
}

// 模糊查询
function blurredQuery(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/appointment/blurredQuery?text='+text+'&value='+vaule);
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

// 关闭预约
function closeAppWin() {
    user = null;
    $('#app').bootstrapSwitch('state', false);
    $("#appWindow").modal('hide');
    $("#addWindow").modal('show')
}

var user;

// 选择预约记录
function checkApp() {
    var row = $("#appTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        swal({title:"",
            text:"请先选择一位本店车主记录",
            confirmButtonText:"确认",
            type:"error"})
        return false;
    } else {
        user = row[0];
        if(user.userName != null && user.userName != "" && user.userPhone != null && user.userPhone != "") {
            setData(user);
            $("#appWindow").modal('hide');
            $("#addWindow").modal("show");
        }else{
            swal({title:"",
                text:"请先将此本店车主信息完善",
                confirmButtonText:"确认",
                type:"error"})
        }
    }
}

function setData(user1) {
    user = null;
    $("#addUserName").attr("readOnly",true);
    $("#addUserPhone").attr("readOnly",true);
    $("#addUserId").val(user1.userId);
    $("#addUserName").val(user1.userName);
    $("#addUserPhone").val(user1.userPhone);
}

/** 清除添加的form表单信息 */
function clearAddForm() {
    $("input[type=reset]").trigger("click");
}

function showEdit(){
    initDateTimePicker('editForm', 'arriveTime','editArriveTime');
    var row =  $('#table').bootstrapTable('getSelections');
    if(row.length >0) {
        if(row[0].userId != null && row[0].userId !=""){
            $("#editUserName").attr("readOnly",true);
            $("#editUserPhone").attr("readOnly",true);
        }
        $("#editWindow").modal('show'); // 显示弹窗
        $("#editButton").removeAttr("disabled");
        var appointment = row[0];
        $('#editMaintainOrFix').val(appointment.maintainOrFix);
        $('#editCarBrand').html('<option value="' + appointment.brand.brandId + '">' + appointment.brand.brandName + '</option>').trigger("change");
        $('#editCarColor').html('<option value="' + appointment.color.colorId + '">' + appointment.color.colorName + '</option>').trigger("change");
        $('#editCarModel').html('<option value="' + appointment.model.modelId + '">' + appointment.model.modelName + '</option>').trigger("change");
        $('#editCarPlate').html('<option value="' + appointment.plate.plateId + '">' + appointment.plate.plateName + '</option>').trigger("change");
        $("#editForm").fill(appointment);
        $('#editArriveTime').val(formatterDate(appointment.arriveTime));
        validator('editForm'); // 初始化验证
    }else{
        swal({
            title:"",
            text: "请选择要修改的预约记录", // 主要文本
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText:"确定", // 提示按钮上的文本
            type:"warning"}) // 提示类型
    }
}

function showAdd(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initDateTimePicker('addForm', 'arriveTime','addArriveTime'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm'); // 初始化验证
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

function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '用户名长度必须在1到6位之间'
                    }
                }
            },
            userPhone: {
                message: '用户手机号失败',
                validators: {
                    notEmpty: {
                        message: '用户手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号码必须为11位'
                    },
                    regexp: {
                        regexp: /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
                        message: '请输入正确的手机号'
                    }
                }
            },
            brandId: {
                message: '汽车品牌验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车品牌不能为空'
                    }
                }
            },
            colorId: {
                message: '汽车颜色验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车颜色不能为空'
                    }
                }
            },
            modelId: {
                message: '汽车车型验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车车型不能为空'
                    }
                }
            },
            carPlate: {
                message: '车牌号码验证失败',
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    }, stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码必须为5位'
                    },
                }
            },
            plateId: {
                message: '汽车车牌验证失败',
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }
                }
            },
            arriveTime: {
                message: '汽车到店时间验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车到店时间不能为空'
                    }
                }
            },
            appCreatedTime: {
                message: '预约记录时间验证失败',
                validators: {
                    notEmpty: {
                        message: '预约记录时间不能为空'
                    }
                }
            }

        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/appointment/add", formId, "addWindow");

            } else if (formId == "editForm") {
                formSubmit("/appointment/update", formId, "editWindow");

            }
        })

}

function formatterImg(value, row, index){
    if(row.userIcon !=null){
        return [
            '<img style="width:120px;height:40px;" src="/'+ value +'">'
        ]
    }
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
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
                    // 设置select2的值为空
                    $("#addCarBrand").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                    $("#addCarModel").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                    $("#addCarColor").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                    $("#addCarPlate").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                }
                $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
            } else if (data.result == "fail" ) {
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
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
