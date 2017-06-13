var showCellEvn = {
    'click .allAcc': function (e, val, row, index) {
        initTableNotTollbar('workInfoAccDetailTable',"/materials/recordAccsByPager?recordId="+ row.recordId);
        $("#accsInfo").modal("show");
        // TODO
    }
}
var contentPath = ''
var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部"
/**
 *初始化表格
 * @type {string}
 */
$(function () {
    $.post(contentPath + "/user/isLogin/" + roles, function (data){
        if(data.result == "success"){
            initTable("table","/Order/queryByPager");//初始化表格
           // initSelect2("maintainRecord","请选择维修保养记录","/Order/queryByPager");
            //initSelect2("user","请选择员工","/Order/queryByPager");
            initSelect2("user","请选择员工","/userBasicManage/queryAll");
        }else if(data.result == "notLogin"){
            swal({title:"",
            text:data.message,
            confirmButtonText:"确认",
            type:"error"},
            function(isConfirm){
               if(isConfirm){
                   top.location ="/user/loginPage";
               } else{
                   top.location="/user/loginPage";
               }
            })
        }else if(data.result == 'notRole'){
            swal({
                title:"",
                confirmButtonText:"确认",
                type:"error"})
        }
    });
});

/*表格验证*/
function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            recordId: {
                message: '工单保养记录验证失败',
                validators: {
                    notEmpty: {
                        message: '保养记录编号不能为空'
                    }
                }
            },
            userId: {
                message: '指派用户验证失败',
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
            workAssignTime: {
                message: '工单指派时间验证失败',
                validators: {
                    notEmpty: {
                        message: '工单指派时间不能为空'
                    }
                }
            },
            workCreatedTime: {
                message: '工单创建时间验证失败',
                validators: {
                    notEmpty: {
                        message: '工单创建时间不能为空'
                    }
                }
            }
        }
    })

        .on('success.form.bv', function (e) {
            if (formId == "editForm") {
                formSubmit("/Order/update", formId, "editWindow");

            } /*else if (formId == "editForm") {
                formSubmit("/Order/edit", formId, "editWindow");

            }*/
        })
}

function editSubmit(){
    $("#editForm").data('bootstrapValidator').validate();
    if($("#editForm").data('bootstrapValidator').isValid()){//已验证
        $("#editButton").attr("disbled","disabled");
    }else{
        $("#editButton").removeAttr("disabled");
    }
}

$('#editDateTimePicker').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language: 'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn: 1,
    autoclose: 1,
});


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
                    type: "success"})// 提示窗口, 修改成功
                $('#table').bootstrapTable('refresh');
                if (formId == 'addForm') {
                    $("input[type=reset]").trigger("click"); // 移除表单中填的值
                    $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
                    $("#addButton").removeAttr("disabled"); // 移除不可点击
                    $("#" + formId).data('bootstrapValidator').destroy();//销毁此from表单
                    $('#' + fromId).data('bootstrapValidator',null);//此from表单设置为空
                    $("#editUserId").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                }
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

//修改
function showEdit() {
    /*//$("#editButton").removeAttr("disabled");
    initDatePicker('editForm', 'workAssignTime'); // 初始化时间框*/
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles,function(data){
        if(data.result == 'success') {
            var row = $('#table').bootstrapTable('getSelections');//选择器
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr('disabled');//按钮只能点击一次
                var workInfo=row[0]
                var editDate = document.getElementById("editDateTimePicker");
                $("#editDateTimePicker").val(formatterDate(workInfo.workAssignTime));
                $("#editUserId").html('<option value="' + workInfo.user.userId + '">' + workInfo.user.userNickname + '</option>').trigger("change");
                $("#editForm").fill(workInfo);
                validator('editForm');
            } else {
                swal({
                    title: "",
                    text: "请先选择要修改的工单信息",   //主要文本
                    confirmButtonText:"确定",     //提示按钮上的文本
                    type: "warning"     //提示类型
                })
            }
        }else if(data.result =='notLogin'){
            swal({
                title:"",
                text: data.message,
                confirmButtonText:"确认",
                type: "error"
            },function (isConfirm) {
                if(isConfirm){
                    top.location = "/user/loginPage";
                }else{
                    top.location = "/user/loginPage";
                }
            })
        } else if(data.result == 'notRole'){
            swal({
                title:"",
                text: data.result.message,
                confirmButtonText: "确认",
                type:"error"
            })
        }
    })
}

/*// 初始化没有分秒的时间框
 function initDatePicker(formId, field){
 $(".datetimepicker").datetimepicker({
 minView: "month", //选择日期后，不会再跳转去选择时分秒
 language: 'zh-CN',
 format: 'yyyy-mm-dd',
 initialDate: new Date(),
 autoclose: true,
 todayHighligh:true,
 todayBtn :true, // 显示今日按钮
 autoclose: 1
 }).on('hide',function(e) {
 $('#'+formId).data('bootstrapValidator')
 .updateStatus(field, 'NOT_VALIDATED',null)
 .validateField(field);
 });
 }*/

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

function formatterCompany(value, row, index) {
    return row.maintainRecord.checkin.company.companyName
}

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

function formatterUserNickName(el, row) {
    if(el) {
        return el.userNickname
    }else {
        return "<p style='color:#aaa'>暂无指派给员工</p>"
    }
}

// 查看全部激活
function showComplete() {
    initTable('table', '/Order/queryByPager');
}
// 查看全部禁用
function showDisable() {
    initTable('table', '/Order/queryByPagerDisable');
}


/**
 * 禁用激活
 * @param index
 * @param row
 * @returns {*}
 */
function statusFormatter(index, row) {
    /*处理数据*/
    if (row.workStatus == 'N') {
        return "&nbsp;&nbsp;未完成";
    } else {
        return "&nbsp;&nbsp;已完成";
    }

}


/**
 * 操作禁用激活
 * @param index
 * @param row
 * @returns {string}
 */
function openStatusFormatter(index, row) {
    console.log(userId);
    /*处理数据*/
    if(row.userId) {
        if (row.userId == userId) {
            if (row.workStatus == 'N') {
                return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='active(\"" + '/Order/statusOperate?id=' + row.workId + '&status=Y' + "\")'>点击完成</a>";
            } else {
                // return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\"" + '/Order/statusOperate?id=' + row.workId + '&status=N' + "\")'>已完成</a>";
                return "&nbsp;&nbsp;<span>已完成</span>";
            }
        } else {
            if (row.workStatus == 'N') {
                return "&nbsp;&nbsp;<span style='color:#aaa'>进行中</span>"
            } else {
                return "&nbsp;&nbsp;<span style='color:#aaa'>已完成</span>"
            }
        }
    } else {
        return "&nbsp;&nbsp;<span style='color:#aaa'>未派工</span>"
    }
}

function setUserId() {
    $.get("/order/userId", function (data) {
        if(data) {
            userId = data.userId
        }
    })
}

function blurredQuery() {
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/Order/blurredQuery?text='+text+'&value='+vaule);
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

function showCell() {
    return '<button type="button"  class="btn btn-default allAcc"><span style="margin-right:10px" class="glyphicon glyphicon-list-alt"></span>查看物料清单</button>'
}

function accInfoFormat(element, row, index){
    return row.materialCount;
}
