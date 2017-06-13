/**
 * 初始化表格
 */
/**
 * 初始化表格
 */
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable("table", "/salary/queryByPager"); // 初始化表格
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


function importData() {
    $("#exceWin").modal('show');
    initTableNotTollbar("exceTable", "/outGoingType/queryByPager");
}

/** 关闭支出类型 */
function exceCloseOutTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#exceWin").modal('hide');
}

var outId;
/** 选择支出类型 */
function exceCheckOutType () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var selectRow = $("#exceTable").bootstrapTable('getSelections');
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
                $("#exceWin").modal('hide');
                var outType = selectRow[0];
                console.log(outType.outTypeId + "aaaaaaaaaaaaaaaaaaa")
                $("#outId").val(outType.outTypeId);
                $("#exceWin").modal('hide');
                $("#import").modal('show');
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

$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("txt_file", "");
});


function fileCloseModals() {
    $("#import").modal('hide');
}

function fileSubmit() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var outId = $("#outId").val();
            var formData = new FormData();
            var txt_file = document.getElementById("txt_file").files[0];
            console.log(outId + "outId")
            formData.append("outId", outId);
            formData.append("txt_file", txt_file);
            $.ajax({
                url: "/salary/readExcel",
                type: "POST",
                data: formData,
                processData: false,
                contentType: false,
                success: function (data1) {
                    $("#import").modal('hide');
                    initTable("table", "/salary/queryByPager"); // 初始化表格
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

}

//初始化fileinput
var FileInput = function () {
    var oFile = new Object();
    //初始化fileinput控件（第一次初始化）
    oFile.Init = function(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: "/salary/readExcel?outId="+outId, //上传的地址
            allowedFileExtensions: ['xls', 'xlsx'],//接收的文件后缀
            showUpload: false, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: true,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount:true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        }).on("fileuploaded", function(event, data) {
            // data 为controller返回的json
            if(data.response.result =='success'){
                initTable("table", "/salary/queryByPager"); // 初始化表格
                $("#import").modal('hide');
            }
        });
    }
    return oFile;
};


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





function showEdit() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            $("#editButton").removeAttr("disabled");
            $("input[type=reset]").trigger("click");
            var row = $('table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#edit").modal('show'); // 显示弹窗
                var editDate = document.getElementById("salaryTimeUpdate");
                var salary = row[0];
                $('#editDateTimePicker').val(formatterDate(salary.salaryTime));
                $("#editForm").fill(salary);
                validator("editForm");

            } else {
                swal({
                    title:"",
                    text: "请先选择要修改的工资信息",
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


$('#editDateTimePicker').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn: 1,
    autoclose: 1,
});

$('#addDateTimePicker').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn: 1,
    autoclose: 1,
});
function showAdd() {
    var roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initDateTimePicker('addForm', 'salaryTime', 'addDateTimePicker'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name, 第三个参数为input的id
            $("input[type=reset]").trigger("click");
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

/** 选择人员 */
function checkAppointment() {
    initTableNotTollbar("appTable", "/userBasicManage/queryByPager");
    $('#addForm').data('bootstrapValidator').resetForm();
    $("#add").modal('hide');
    $("#personnelWin").modal('show');
}


/** 关闭人员管理窗口 */
function closePersonnelWin() {
    $("#personnelWin").modal('hide');
    $("#add").modal('show')
}

/** 选择人员 */
function checkPersonnel() {
    var selectRow = $("#appTable").bootstrapTable('getSelections');
    if (selectRow.length != 1) {
        swal({
            title:"",
            text:"请先选择员工",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
        return false;
    } else {
        var user = selectRow[0];
        if (user.userSalary != null) {
            $("#personnelWin").modal('hide');
            $("#add").modal('show');
            $("#userName").val(user.userName);
            $("#userId").val(user.userId);
            $("#userSalary").val(user.userSalary);
            $("#addWin").modal('show');
        } else {
            swal('选择失败', "此用户没底薪，请去人员管理为用户设置底薪", "error");
            return false;
        }

    }
}

function checkEdit() {
    $.post("/table/edit",
        $("#editForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $("#edit").modal('hide'); // 关闭指定的窗口
                $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                swal({
                    title: "",
                    text: data.message,
                    type: "success"
                })// 提示窗口, 修改成功
            } else if (data.result == "fail") {
                //$.messager.alert("提示", data.result.message, "info");
            }
        }, "json"
    );
}

// 模糊查询
function blurredQuery() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var value = $("#ulInput").val();// 获取模糊查询输入框文本
            console.log(text + value)
            initTable('table', '/salary/blurredQuery?text=' + text + '&value=' + value);
        } else if (data.result == "notLogin") {
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
        } else if (data.result = "notRole") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

/**
 * 根据工资查询
 */
function selectSalary() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            var start = $("#startSalary");// 获取模糊查询按钮
            var end = $("#endSalary").val();// 获取模糊查询输入框文本
            console.log(start + end)
            initTable('table', '/salary/selectSalary?start=' + start + '&end=' + end);
        } else if (data.result == "notLogin") {
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
        } else if (data.result = "notRole") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
}

/**
 * 还原
 */
function returnSalary() {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/" + roles, function (data) {
        if (data.result == "success") {
            initTable("table", "/salary/queryByPager"); // 初始化表格
        } else if (data.result == "notLogin") {
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
        } else if (data.result = "notRole") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText: "确认", // 提示按钮上的文本
                type: "error"
            })
        }
    })
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
            userName: {
                message: '员工不能为空',
                validators: {
                    notEmpty: {
                        message: '请选择员工',
                    }

                }
            },
            salaryTime: {
                message: '请选择工资发放时间',
                validators: {
                    notEmpty: {
                        message: '请选择工资发放时间'
                    }
                }
            }

        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                var userSalary = 0;
                var minusSalary = 0;
                var prizeSalary = 0;

                userSalary = $("#userSalary").val();
                minusSalary = $("#minusSalary").val();
                prizeSalary = $("#prizeSalary").val();

                if ($("#prizeSalary").val() == '') {
                    prizeSalary = 0;
                }
                if ($("#minusSalary").val() == '') {
                    minusSalary = 0;
                }


                var totalSalary = (parseInt(userSalary) + parseInt(prizeSalary)) - parseInt(minusSalary);
                console.log(totalSalary + "aaaaaaaaaaaaaaaaa")
                if (totalSalary >= 0) {
                    $("#outWin").modal('show');
                    initTableNotTollbar("outTable", "/outGoingType/queryByPager");
                    $("#addWin").modal('hide');
                } else {

                    $("#inWin").modal('show');
                    initTableNotTollbar("inTable", "/incomingType/queryByPager");
                    $("#addWin").modal('hide');
                }

            } else if (formId == "editForm") {
                formSubmit("/salary/update", formId, "edit");

            }
        })

}


/** 关闭支出类型 */
function closeOutTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#outWin").modal('hide');
    $("#addWin").modal('show')
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
        var outType = selectRow[0];
        $("#inoutId").val(outType.outTypeId);
        console.log(outType.outTypeId);
        $("#addWin").modal('hide');
        formSubmit("/salary/add?outId="+outType.outTypeId, "addForm", "add");
    }
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
        var inType = selectRow[0];
        $("#inoutId").val(inType.inTypeId);
        console.log(inType.inTypeId);
        $("#addWin").modal('hide');
        formSubmit("/salary/add?inId="+inType.inTypeId, "addForm", "add");
    }
}

/** 关闭收入类型 */
function inCloseInTypeWin() {
    $("input[type=reset]").trigger("click");
    $("#inWin").modal('hide');
    $("#addWin").modal('show')
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
                    $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
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



