var contentPath = ''
var roles = "公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员";
//初始化表格
$(function () {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accBuy/queryByPage'); // 初始化表格
            initSelect2("company", "请选择所属公司", "/company/queryAllCompany");
            initSelect2("accType", "请选择配件分类", "/accType/queryAllAccType");
            initSelect2("supply", "请选择供应商", "/supply/queryAllSupply");
            initSelect2("accInv", "请选择配件名称", "/accInv/queryAllAccInv");
            initSelect2("outgoing", "请选择支出类型", "/outGoingType/queryAllOutGoing");
            $("#app").bootstrapSwitch({
                onText:"是",
                offText:"否",
                onColor:"success",
                offColor:"danger",
                size:"small",
                onSwitchChange:function(event,state){
                    if(state==true){
                        app = true;
                        initTableNotTollbar("accTable", "/accInv/queryByPage");
                        $("#appWindow").modal('show');
                    }else if(state==false){
                        app = false;
                    }
                }
            })
            $("#appWindow").on("hide.bs.modal", function () {
                $("#addWindow").modal('show')
                $('#app').bootstrapSwitch('state', false);
            });
        } else if(data.result == 'notLogin'){
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
    })
});

// 关闭预约
function closeAppWin() {
    $("#appWindow").modal('hide');
    $("#addWinow").modal('show')
}

// 选择预约记录
function checkApp() {
    var row = $("#accTable").bootstrapTable('getSelections');
    if (row.length != 1) {
        swal({title:"",
            text:"请选择一个配件",
            confirmButtonText:"确认",
            type:"error"})
        return false;
    } else {
        appointment = row[0];
        setData(appointment);
        $("#appWindow").on("hide.bs.modal", function () {
            $('#app').bootstrapSwitch('state', true);
        });
        $("#appWindow").modal('hide');
    }
}

// 查看全部可用
function showAvailable() {
    initTable('table', '/accBuy/queryByPage');
}
// 查看全部禁用
function showDisable() {
    initTable('table', '/accBuy/queryByPagerDisable');
}

// 模糊查询
function blurredQuery() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/accBuy/blurredQuery?text=' + text + '&value=' + vaule);
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

//显示弹窗
function showEdit() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var row = $('table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var ceshi = row[0];
                var editDate = document.getElementById("editDateTimePicker");
                $("#editForm").fill(ceshi);
                $("#editDateTimePicker").val(formatterDate(ceshi.accBuyTime))
                $('#editCompany').html('<option value="' + ceshi.company.companyId + '">' + ceshi.company.companyName + '</option>').trigger("change");
                $('#editAccInv').html('<option value="' + ceshi.accessories.accId + '">' + ceshi.accessories.accName + '</option>').trigger("change");
                $('#editSupply').html('<option value="' + ceshi.supply.supplyId + '">' + ceshi.supply.supplyName + '</option>').trigger("change");
                $('#editAccType').html('<option value="' + ceshi.accessoriesType.accTypeId + '">' + ceshi.accessoriesType.accTypeName + '</option>').trigger("change");
                validator('editForm'); // 初始化验证
            } else {
                swal({
                    "title": "",
                    "text": "请选择配件采购信息",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
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
            initDatePicker('addForm', 'accBuyTime'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name
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
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\"" + '/accBuy/statusOperate?accBuyId=' + row.accBuyId + '&accBuyStatus=Y' + "\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\"" + '/accBuy/statusOperate?accBuyId=' + row.accBuyId + '&accBuyStatus=N' + "\")'>激活</a>";
    }
}

//格式化带时分秒的时间值。
function formatterDateTime(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }
}

//格式化不带时分秒的时间值
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

//展示冻结状态的采购记录
function showInactiveAccBuy() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            $.post(contentPath + "/accBuy/queryAccBuyStatus?accBuyStatus=N", function (data) {
                $("#table").load(data);
            })
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
//展示激活状态的采购记录
function showActiveAccBuy() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {

            $.post(contentPath + "/accBuy/queryAccBuyStatus?accBuyStatus=Y", function (data) {
                $("#table").load(data);
            })
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

$('#addDateTimePicker').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn: 1,
    autoclose: 1,
});

$('#editDateTimePicker').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn: 1,
    autoclose: 1,
});


function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            outgoingId:{
                message: '支出类型不能为空',
                validators: {
                    notEmpty: {
                        message: '支出类型不能为空'
                    }
                }
            },
            companyId: {
                message: '所属公司名称不能为空',
                validators: {
                    notEmpty: {
                        message: '所属公司名称不能为空'
                    }
                }
            },
            supplyId: {
                message: '所属供应商不能为空',
                validators: {
                    notEmpty: {
                        message: '所属供应商不能为空'
                    }
                }
            },
            accTypeId: {
                message: '配件分类不能为空',
                validators: {
                    notEmpty: {
                        message: '配件分类不能为空'
                    }
                }
            },
            accName: {
                message: '配件名称不能为空',
                validators: {
                    notEmpty: {
                        message: '配件名称不能为空'
                    }
                }
            },
            accBuyTime: {
                message: '购买时间不能为空',
                validators: {
                    notEmpty: {
                        message: '购买时间不能为空'
                    }
                }
            },
            accBuyCount: {
                message: '购买数量不能为空',
                validators: {
                    notEmpty: {
                        message: '购买数量不能为空'
                    }
                }
            },
            accBuyPrice: {
                message: '购买单价不能为空',
                validators: {
                    notEmpty: {
                        message: '购买单价不能为空'
                    }
                }
            },
            accBuyDiscount: {
                message: '购买折扣不能为空',
                validators: {
                    notEmpty: {
                        message: '购买折扣不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        if (formId == "addForm") {
            formSubmit(contentPath + "/accBuy/addAccBuy", formId, "addWindow");

        } else if (formId == "editForm") {
            formSubmit(contentPath + "/accBuy/updateAccBuy", formId, "editWindow");
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
                        var accId=document.getElementById("accInvId")
                        accId.value="";
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
                            $("#addAccType").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                            $("#addSupply").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
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

function Addcalculate() {
    //购买数量
    var countNum = document.getElementById("addCountNum").value;
    //购买单价
    var buyPrice = document.getElementById("addBuyPrice").value;
    //购买折扣
    var buyDiscount = document.getElementById("addBuyDiscount").value;
    //购买总价
    var addBuyTotal = document.getElementById("addBuyTotal");
    //如果有折扣，则加入折扣进行计算，如果没有最终价格为购买总价。
    var addBuyMoney = document.getElementById("addBuyMoney");
    if (countNum != null && buyPrice != null && countNum != "" && buyPrice != "") {
        //计算出的总价需要进行四舍五入计算。
        var totalPrice = Math.floor(parseFloat(buyPrice * 100 * countNum)) / 100;
        //把计算后的值，填入购买总价中。
        addBuyTotal.value = totalPrice;
        if (buyDiscount != null && buyDiscount != "") {
            addBuyMoney.value = Math.ceil(totalPrice * buyDiscount);
        } else {
            addBuyMoney.value = totalPrice;
        }
    }
}

function Editcalculate() {
    //购买数量
    var countNum = document.getElementById("editBuyNum").value;
    //购买单价
    var buyPrice = document.getElementById("editBuyPrice").value;
    //购买折扣
    var buyDiscount = document.getElementById("editBuyDiscount").value;
    //购买总价
    var addBuyTotal = document.getElementById("editBuyTotal");
    //如果有折扣，则加入折扣进行计算，如果没有最终价格为购买总价。
    var addBuyMoney = document.getElementById("editBuyMoney");
    if (countNum != null && buyPrice != null && countNum != "" && buyPrice != "") {
        //计算出的总价需要进行四舍五入计算。
        var totalPrice = Math.floor(parseFloat(buyPrice * 100 * countNum)) / 100;
        //把计算后的值，填入购买总价中。
        addBuyTotal.value = totalPrice;
        if (buyDiscount != null && buyDiscount != "") {
            addBuyMoney.value =Math.ceil(totalPrice * buyDiscount);
        } else {
            addBuyMoney.value = totalPrice;
        }
    }
}


var appointment;
/** 监听switch的监听事件 */
function appOnChange() {
    if ($('#app').bootstrapSwitch('state')) {
        if (appointment != null && appointment != "" && appointment != undefined) {
            setData(appointment);
        }
    } else {
        if (appointment != null && appointment != "" && appointment != undefined) {
            clearAddForm();
        }
    }
}

function setData(appointment) {
    $("#accInvId").val(appointment.accId);
    $("#accInvName").val(appointment.accName);
    $("#addDateTimePicker").val(formatterDate(appointment.accBuyedTime));
    $("#addBuyPrice").val(appointment.accPrice);
    $("#addBuyTotal").val(appointment.accPrice);
    $('#addCompany').html('<option value="' + appointment.company.companyId + '">' + appointment.company.companyName + '</option>').trigger("change");
    $('#addSupply').html('<option value="' + appointment.supply.supplyId + '">' + appointment.supply.supplyName + '</option>').trigger("change");
    $('#addAccType').html('<option value="' + appointment.accessoriesType.accTypeId + '">' + appointment.accessoriesType.accTypeName + '</option>').trigger("change");
}

/** 清除添加的form表单信息 */
function clearAddForm() {
    $('#accInvId').html('').trigger("change");
    $('#accInvName').html('').trigger("change");
    $('#addDateTimePicker').html('').trigger("change");
    $('#addCountNum').html('').trigger("change");
    $('#addBuyPrice').html('').trigger("change");
    $('#addBuyTotal').html('').trigger("change");
    $('#addCompany').html('').trigger("change");
    $('#addSupply').html('').trigger("change");
    $('#addAccType').html('').trigger("change");
    $("input[type=reset]").trigger("click");
}
