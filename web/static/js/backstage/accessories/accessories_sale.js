var contentPath = '';
var roles = "公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员";

var countNum;
//初始化表格
$(function () {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accSale/queryByPage','#toolbar'); // 初始化表格
            initSelect2("company", "请选择所属公司", "/company/queryAllCompany");
            initSelect2("accInv", "请选择配件", "/accInv/queryAllAccInv");
            initSelect2("incoming", "请选择收入类型", "/incomingType/queryAllIncoming");
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
    })
});

// 查看全部可用
function showAvailable() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accSale/queryByPage','#toolbar');
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
// 查看全部禁用
function showDisable() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable('table', '/accSale/queryByPagerDisable','#toolbar');
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

// 模糊查询
function blurredQuery() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/accSale/blurredQuery?text=' + text + '&value=' + vaule,'#toolbar');
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
                $('#editCompany').html('<option value="' + ceshi.company.companyId + '">' + ceshi.company.companyName + '</option>').trigger("change");
                $('#editAccInv').html('<option value="' + ceshi.accessories.accId + '">' + ceshi.accessories.accName + '</option>').trigger("change");
                $('#editDateTimePicker').val(formatterDate(ceshi.accSaledTime));
                $("#editForm").fill(ceshi);
                validator('editForm'); // 初始化验证
            } else {
                swal({
                    "title": "",
                    "text": "请选择配件销售信息",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
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
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            validator('addForm');
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
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\"" + '/accSale/statusOperate?accSaleId=' + row.accSaleId + '&accSaleStatus=Y' + "\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\"" + '/accSale/statusOperate?accSaleId=' + row.accSaleId + '&accSaleStatus=N' + "\")'>激活</a>";
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

function addSubmit() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").removeAttr("disabled");
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
                            $("#addAccInv").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                            $("#addCompany").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                            $("#addAccType").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
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
                        $("#" + formId).removeAttr("disabled");
                    }
                }, "json");
        } else if (data.result == "notLogin") {
            swal({
                title:'',
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
                title:'',
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            inTypeId: {
                message: '收入类型不能为空',
                validators: {
                    notEmpty: {
                        message: '收入类型不能为空'
                    }
                }
            },
            companyId: {
                message: '所属公司不能为空',
                validators: {
                    notEmpty: {
                        message: '所属公司不能为空'
                    }
                }
            },
            accId: {
                message: '配件名称不能为空',
                validators: {
                    notEmpty: {
                        message: '配件名称不能为空'
                    }
                }
            },
            accSaledTime: {
                message: '配件销售时间不能为空',
                validators: {
                    notEmpty: {
                        message: '配件销售时间不能为空'
                    }
                }
            },
            accSaleCount: {
                message: '配件销售数量不能为空',
                validators: {
                    notEmpty: {
                        message: '配件销售数量不能为空'
                    }
                }
            },
            accSalePrice: {
                message: '配件销售单价不能为空',
                validators: {
                    notEmpty: {
                        message: '配件销售单价不能为空'
                    }
                }
            },
            accSaleDiscount: {
                message: '配件销售折扣不能为空',
                validators: {
                    notEmpty: {
                        message: '配件销售折扣不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        if (formId == "addForm") {
            formSubmit(contentPath + "/accSale/addAccSale", formId, "addWindow");
        } else if (formId == "editForm") {
            formSubmit(contentPath + "/accSale/updateAccSale", formId, "editWindow");
        }
    })
}

function Addcalculate() {
    //销售数量
    var countNum = document.getElementById("addCountNum").value;
    //销售单价
    var buyPrice = document.getElementById("addSalePrice").value;
    //销售折扣
    var buyDiscount = document.getElementById("addSaleDiscount").value;
    //销售总价
    var addBuyTotal = document.getElementById("addSaleTotal");
    //如果有折扣，则加入折扣进行计算，如果没有最终价格为销售总价。
    var addBuyMoney = document.getElementById("addSaleMoney");
    if (countNum != null && countNum != "" && buyPrice != null && buyPrice != "") {
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
    //销售数量
    var countNum = document.getElementById("editSaleNum").value;
    //销售单价
    var buyPrice = document.getElementById("editSalePrice").value;
    //销售折扣
    var buyDiscount = document.getElementById("editSaleDiscount").value;
    //销售总价
    var addBuyTotal = document.getElementById("editSaleTotal");
    //如果有折扣，则加入折扣进行计算，如果没有最终价格为销售总价。
    var addBuyMoney = document.getElementById("editSaleMoney");
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

function checkAppointment() {
    initTable("accTable", "/accInv/queryByPage","#toolbar1");
    $('#addForm').data('bootstrapValidator').resetForm();
    $("#addWindow").modal('hide');
    $("#appWindow").modal('show');
}
function closeAppWin() {
    $("#appWindow").modal('hide');
    $("#addWindow").modal('show');
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
        var selectRow = $("#accTable").bootstrapTable('getSelections');
        var accInv=selectRow[0];
        if (accInv != null) {
            $("#addCountNum").attr("max",accInv.accIdle)
            $("#addInvId").val(accInv.accId);
            $('#addAccInv').val(accInv.accName);
            $("#addSalePrice").val(accInv.accSalePrice)
            $("#addCountNum").val(accInv.accIdle)
            $("#appWindow").modal('hide');
            $("#addWindow").modal('show');
        } else {
            return false;
        }
    }
}


function initTable(tableId, url,toolbarId) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        showToggle: true, // 显示详情
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "checkinId",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar :toolbarId,// 指定工具栏
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

// 模糊查询
function accblurredQuery() {
    $.post(contentPath + "/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var button = $("#ulButton1");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput1").val();// 获取模糊查询输入框文本
            initTable('accTable', '/accInv/blurredQuery?text=' + text + '&value=' + vaule,'#toolbar1');
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

//模糊查询li点击事件
function onclikLi1(lis) {
    var button = $("#ulButton1");
    button.text($(lis).text());
    button.append("<span class='caret'></span>");
}