$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/checkin/queryByPager'); // 初始化表格

            initSelect2("carColor", "请选择颜色", "/carColor/queryAllCarColor"); // 初始化select2, 第一个参数是class的名字, 第二个参数是select2的提示语, 第三个参数是select2的查询url
            initSelect2("carBrand", "请选择品牌", "/carBrand/queryAllCarBrand");
            initSelect2("carPlate", "请选择车牌", "/carPlate/queryAllCarPlate");

            $("#app").bootstrapSwitch({
                onText:"是",
                offText:"否",
                onColor:"success",
                offColor:"danger",
                size:"small",
                onSwitchChange:function(event,state){
                    if(state==true){
                        initTableNotTollbar("appTable", "/appointment/queryByCurrentStatus");
                        $('#addForm').data('bootstrapValidator').resetForm();
                        $("#appWindow").modal('show');
                        $("#addWindow").modal("hide");
                    }else if(state==false){
                        $("#addUserName").attr("readOnly",false);
                        $("#addUserPhone").attr("readOnly",false);
                        $("#customerDiv").css("display", "block");
                        clearAddForm();
                    }
                }
            })
            $("#customer").bootstrapSwitch({
                onText:"是",
                offText:"否",
                onColor:"success",
                offColor:"danger",
                size:"small",
                onSwitchChange:function(event,state){
                    if(state==true){
                        // 点击开关, 开关为开时, 显示所有本店用户
                        initTableNotTollbar("customerTable", "/userBasicManage/queryCarByRoleName");
                        $('#addForm').data('bootstrapValidator').resetForm();
                        $("#addWindow").modal("hide");
                        $("#customerWindow").modal('show');
                    }else if(state==false){
                        $("#addUserName").attr("readOnly",false);
                        $("#addUserPhone").attr("readOnly",false);
                        $("#appDiv").css("display", "block");
                        clearAddForm1();
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

function formatterImg(value, row, index){
    if(row.userIcon !=null){
        return [
            '<img style="width:120px;height:40px;" src="/'+ value +'">'
        ]
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

// 这个方法别看
$("#addCarBrand").change(function(){
    var div = $("#addModelDiv");
    var select = $("#addCarBrand").select2("val");
    div.css("display","block");
    $('#addCarModel').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    initSelect2("carModel", "请选择车型", "/carModel/queryByBrandId/"+ select);
});
// 这个别看
$("#editCarBrand").change(function(){
    var div = $("#editModelDiv");
    var select = $("#editCarBrand").select2("val");
    div.css("display","block");
    $('#editCarModel').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    initSelect2("carModel", "请选择车型", "/carModel/queryByBrandId/"+select);
});

// 激活或禁用
function statusFormatter(value, row, index) {
            if(value == 'Y') {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/checkin/statusOperate?id='+row.checkinId+'&status=Y'+"\")'>禁用</a>";
            } else {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/checkin/statusOperate?id='+ row.checkinId+'&status=N'+ "\")'>激活</a>";
            }
}

// 查看全部可用
function showAvailable(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
                if(data.result == 'success'){
                    initTable('table', '/checkin/queryByPager');
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
            initTable('table', '/checkin/queryByPagerDisable');
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
            initTable('table', '/checkin/blurredQuery?text='+text+'&value='+vaule);
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
    appointment = null;
    $('#app').bootstrapSwitch('state', false);
    $("#appWindow").modal('hide');
    $("#addWindow").modal('show')
}


// 关闭用户
function closeCustomerWin() {
    customer = null;
    $('#customer').bootstrapSwitch('state', false);
    $("#customerWindow").modal('hide');
    $("#addWindow").modal('show')
}

var appointment;
var user;
var userPhone;

// 选择预约记录
function checkApp() {
    var row = $("#appTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        swal({title:"",
            text:"请先选择一条预约记录",
            confirmButtonText:"确认",
            type:"error"})
        return false;
    } else {
        appointment = row[0];
        if(appointment.userName != null && appointment.userName != "" && appointment.userPhone != null && appointment.userPhone != "") {
            setData(appointment);
            $("#customerDiv").css("display", "none");
            $("#appWindow").modal('hide');
            $("#addWindow").modal("show");
        }else{
            swal({title:"",
                text:"请先将此预约记录车主信息完善",
                confirmButtonText:"确认",
                type:"error"})
        }
    }
}

// 选择车主记录
function checkCustomer() {
    var row = $("#customerTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        swal({title:"",
            text:"请先选择一位本店车主记录",
            confirmButtonText:"确认",
            type:"error"})
        return false;
    } else {
        user = row[0];
        if(user.userName != null && user.userName != "" && user.userPhone != null && user.userPhone != ""){
            setData1(user);
            $("#appDiv").css("display", "none");
            $("#customerWindow").modal('hide');
            $("#addWindow").modal("show");
        }else{
            swal({title:"",
                text:"请先将此本店车主信息完善",
                confirmButtonText:"确认",
                type:"error"})
        }
    }
}

function setData(app) {
    appointment = null;
    $("#addUserName").attr("readOnly",true);
    $("#addUserPhone").attr("readOnly",true);
    $("#addUserName").val(app.userName);
    $("#addUserPhone").val(app.userPhone);
    $("#addUserId").val(app.userId);
    $("#addPlate").val(app.carPlate);
    $("#addAppointmentId").val(app.appointmentId);
    $('#addCarBrand').html('<option value="' + app.brand.brandId + '">' + app.brand.brandName + '</option>').trigger("change");
    $('#addCarColor').html('<option value="' + app.color.colorId + '">' + app.color.colorName + '</option>').trigger("change");
    $('#addCarModel').html('<option value="' + app.model.modelId + '">' + app.model.modelName + '</option>').trigger("change");
    $('#addCarPlate').html('<option value="' + app.plate.plateId + '">' + app.plate.plateName + '</option>').trigger("change");
    $('#addDatetimepicker').val(formatterDate(app.arriveTime))
    $("#addMaintainOrFix").val(app.maintainOrFix);
    $("#addUserName").attr("readOnly",true);
    $("#addUserPhone").attr("readOnly",true);
}

function setData1(user) {
    userPhone = null;
    $("#addUserName").attr("readOnly",true);
    $("#addUserPhone").attr("readOnly",true);
    $("#addUserName").val(user.userName);
    $("#addUserPhone").val(user.userPhone);
    $("#addUserId").val(user.userId);
    $("#addUserName").attr("readOnly",true);
    $("#addUserPhone").attr("readOnly",true);
}

/** 清除添加的form表单信息 */
function clearAddForm() {
    $("#addUserId").val("");
    $("#addAppointmentId").val("");
    $('#addCarBrand').html('').trigger("change");
    $('#addCarColor').html('').trigger("change");
    $('#addCarModel').html('').trigger("change");
    $('#addCarPlate').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}

/** 清除添加的form表单信息 */
function clearAddForm1() {
    $("#addUserId").val("");
    $("#addAppointmentId").val("");
    $("input[type=reset]").trigger("click");
}

function showEdit(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initDateTimePicker('editForm', 'arriveTime', 'editDatetimepicker'); // 初始化时间框
            var row =  $('#table').bootstrapTable('getSelections');
            if(row.length >0) {
                if(row[0].userId != null && row[0].userId != "" && row[0].appointmentId != null && row[0].appointmentId != ""){
                    $("#editUserName").attr("readOnly",true);
                    $("#editUserPhone").attr("readOnly",true);
                }
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var checkin = row[0];
                $("#editMaintainOrFix").val(checkin.maintainOrFix);
                $("#editIfClearCar").val(checkin.ifClearCar);
                $('#editCarBrand').html('<option value="' + checkin.brand.brandId + '">' + checkin.brand.brandName + '</option>').trigger("change");
                $('#editCarColor').html('<option value="' + checkin.color.colorId + '">' + checkin.color.colorName + '</option>').trigger("change");
                $('#editCarModel').html('<option value="' + checkin.model.modelId + '">' + checkin.model.modelName + '</option>').trigger("change");
                $('#editCarPlate').html('<option value="' + checkin.plate.plateId + '">' + checkin.plate.plateName + '</option>').trigger("change");
                $('#editDatetimepicker').val(formatterDate(checkin.arriveTime));
                $('#checkinCreatedTime').val(formatterDate(checkin.checkinCreatedTime));
                $("#editForm").fill(checkin);
                validator('editForm');
            }else{
                swal({
                    title:"",
                    text: "请先选择要修改的登记记录", // 主要文本
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

function showAdd(){
    var roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initDateTimePicker('addForm', 'arriveTime', 'addDatetimepicker'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name, 第三个参数为input的id
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm'); // 初始化验证
            $('#addUserPhone').bind('input propertychange', function() {
                userPhone = $("#addUserPhone").val();
            });
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
                message: '车主姓名验证失败',
                validators: {
                    notEmpty: {
                        message: '车主姓名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '车主姓名长度必须在1到6位之间'
                    }
                }
            },
            userPhone: {
                message: '车主手机验证失败',
                validators: {
                    notEmpty: {
                        message: '车主电话不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '车主电话必须为11位'
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
            plateId: {
                message: '汽车车牌验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车车牌不能为空'
                    }
                }
            },
            carMileage: {
                message: '汽车当前行驶里程验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车当前行驶里程不能为空'
                    }
                }
            },
            carPlate: {
                message: '汽车车牌号验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车车牌号不能为空'
                    },
                    stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码必须为5位'
                    },
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
            nowOil: {
                message: '汽车当前油量验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车当前油量不能为空'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/checkin/add", formId, "addWindow");

            } else if (formId == "editForm") {
                formSubmit("/checkin/edit", formId, "editWindow");

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