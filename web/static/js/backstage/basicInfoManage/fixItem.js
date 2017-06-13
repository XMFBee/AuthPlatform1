$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/maintain/queryByPagerService'); // 初始化表格
            // 初始化select2, 第一个参数是class的名字, 第二个参数是select2的提示语, 第三个参数是select2的查询url
            initSelect2("company", "请选择公司", "/company/queryAllCompany");
            initSelect2("AccessoriesType", "请选择配件类型", "/accType/queryAllAccType");
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

// 查看全部可用
function showAvailable(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/maintain/queryByPagerService');
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
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/maintain/queryByPagerDisableService');
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

function queryByTypeId(obj){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTableNotTollbar("table1", "/accInv/queryByIdAcc?id=" + obj.value);
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

function queryByTypeId2(obj){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTableNotTollbar("table2", "/accInv/queryByIdAcc?id=" + obj.value);
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

function showEdit() {
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row = $('#table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var MaintainFixMap = row[0];
                $('#editcompany').html('<option value="' + MaintainFixMap.company.companyId + '">' + MaintainFixMap.company.companyName + '</option>').trigger("change");
                $("#editForm").fill(MaintainFixMap);
                validator('editForm');
            } else {
                swal({
                    "title": "",
                    "text": "请先选择一条数据",
                    "type": "warning"
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

function showAdd() {
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
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
            maintainName: {
                message: '维修项目名称验证失败',
                validators: {
                    notEmpty: {
                        message: '维修项目名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '维修项目名称长度必须在1到10位之间'
                    },
                    remote: {
                        url: '/maintain/querymaintainNameMaintain',
                        message: '该维修项目名称已存在',
                        delay :  2000,
                        type: 'POST',
                        data: {
                            maintainId: $("#"+formId + " input[name=maintainId]").val(),
                            maintainName: $("#" + formId + " input[name=maintainName]").val()
                        }
                    }
                }
            },
            maintainHour: {
                message: '维修项目工时验证失败',
                validators: {
                    notEmpty: {
                        message: '维修项目工时不能为空'
                    }
                }
            },
            maintainManHourFee: {
                message: '维修项目工时费验证失 败',
                validators: {
                    notEmpty: {
                        message: '维修项目工时费不能为空'
                    }
                }
            },
            maintainMoney: {
                message: '维修项目基础费用验证失败',
                validators: {
                    notEmpty: {
                        message: '维修项目基础费用不能为空'
                    }
                }
            },
            accCount: {
                message: '配件个数验证失败',
                validators: {
                    notEmpty: {
                        message: '配件个数不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
                if (formId == "addForm") {
                    formSubmit("/maintain/addService", formId, "addWindow");

                } else if (formId == "editForm") {
                    formSubmit("/maintain/update", formId, "editWindow");

                } else if (formId == "accForm") {
                     formSubmit("/maintain/accadd", formId, "accWindow");

            } else if (formId == "accEditForm"){
                    formSubmit("/maintain/accedit",formId,"accEditWindow")
                }
            })
}

function addSubmit(){
    setTimeout(function () {
        $("#addForm").data('bootstrapValidator').validate();
        if ($("#addForm").data('bootstrapValidator').isValid()) {
            $("#addButton").attr("disabled","disabled");
        } else {
            $("#addButton").removeAttr("disabled");
        }
    },100)

}

function accaddSubmit(){
    setTimeout(function () {
    $("#accForm").data('bootstrapValidator').validate();
    if ($("#accForm").data('bootstrapValidator').isValid()) {
        $("#accButton").attr("disabled","disabled");
    } else {
        $("#accButton").removeAttr("disabled");
    }
    },100)
}

function accEditaddSubmit(){
    $("#accEditForm").data('bootstrapValidator').validate();
    if ($("#accEditForm").data('bootstrapValidator').isValid()) {
        $("#accEidtButton").attr("disabled","disabled");
    } else {
        $("#accEidtButton").removeAttr("disabled");
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

function showdetai() {
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#detailTable').bootstrapTable('getSelections');
            if(row.length >0) {
                $("#detailWindow").modal('hide');
                $("#accEidtButton").removeAttr("disabled");
                // var MaintainFixMap = row[0];
                // $("#accForm").fill(MaintainFixMap);
                $("#editmaintainId").val(row[0].mainAccId);
                $("#editaccId").val(row[0].accId);
                $("#editaccCount").val(row[0].accCount);
                $("#editaccName").val(row[0].accessories.accName);
                $("#detailWindow").modal('hide');
                $("#accEditWindow").modal('show');
                validator('accEditForm'); // 初始化验证
            }else{
                swal({
                    title:"",
                    text: "请选择要添加配件的维修项目", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
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
                    // $('#addForm').data('bootstrapValidator').resetForm(true); //
                    $("#addButton").removeAttr("disabled"); // 移除不可点击移除所有验证样式

                    $("#addCompany").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                } else if(formId == 'accForm'){
                    $("input[type=reset]").trigger("click"); // 移除表单中填的值
                    // $('#accForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
                    $("#accButton").removeAttr("disabled"); // 移除不可点击

                    $("#addAccessories").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                    // $("#accWindow").modal("hide");
                }
                $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
                $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
            } else if (data.result == "fail") {
                swal({title:"",
                    text:"添加失败",
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
            }else if(data.result == 'notRole'){
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
            }
        }, "json");
}
// 激活或禁用
function statusFormatter(value, row, index) {
    if(value == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/maintain/statusOperate?id='+row.maintainId+'&status=Y'+"\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/maintain/statusOperate?id='+ row.maintainId+'&status=N'+ "\")'>激活</a>";
    }
}

$("#accForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

// 添加配件窗口
function showAddacc(){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#table').bootstrapTable('getSelections');
            if(row.length >0) {
                $("#accButton").removeAttr("disabled");
                var MaintainFixMap = row[0];
                $("#accForm").fill(MaintainFixMap);
                $("#accWindow").modal('show');
                validator('accForm'); // 初始化验证
            }else{
                swal({
                    title:"",
                    text: "请选择要添加配件的维修项目", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
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

function showAcc(windowId){
    $("#"+ windowId).modal('hide');
    $("#accAllWindow").modal('show');
    $("#closeButton").addClass(windowId);
}

function showEidtAcc(windowId) {
    $("#"+ windowId).modal('hide');
    $("#accEidtAllWindow").modal('show');
    $("#closeEidtButton").addClass(windowId);
}

//所有配件窗口关闭按钮
function accAllcloseWindow(){
    $("#accAllWindow").modal('hide');
    $("#accWindow").modal('show');
}

//所有配件窗口关闭按钮
function accEditAllcloseWindow(){
    $("#accEidtAllWindow").modal('hide');
    $("#accEditWindow").modal('show');
}

//所有配件窗口关闭图标
function closeWindow(){
    $("#accAllWindow").modal('hide');
    $("#accWindow").modal('show');
}

//所有配件窗口关闭图标
function closeEidtWindow(){
    $("#accEidtAllWindow").modal('hide');
    $("#accEditWindow").modal('show');
}
function closeModalsdetailWindow(){
    $("#detailWindow").modal('hide')
}

// 在所有项目中点击确定
function itemSubmit(){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#table1').bootstrapTable('getSelections');
            if(row.length >0) {
                $("#accAllWindow").modal('hide');
                if($("#closeButton").hasClass('accWindow')){
                    $("#addaccId").val(row[0].accId);
                    $("#addacc").val(row[0].accName);
                    $("#accWindow").modal('show');
                    $("#closeButton").removeClass('accWindow');
                }else if($("#closeButton").hasClass('editWindow')){
                    $("#editItemId").val(row[0].maintainId);
                    $("#editItem").val(row[0].maintainName);
                    $("#editWindow").modal('show');
                    $("#closeButton").removeClass('editWindow');
                }
            }else{
                swal({
                    title:"",
                    text: "请先选择维修保养项目", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
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

// 在所有项目中点击确定
function itemEditSubmit(){
    var roles = "公司超级管理员,公司普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#table2').bootstrapTable('getSelections');
            if(row.length >0) {
                $("#accEidtAllWindow").modal('hide');
                if($("#closeEidtButton").hasClass('accEditWindow')){
                    $("#editaccId").val(row[0].accId);
                    $("#editaccName").val(row[0].accName);
                    $("#accEditWindow").modal('show');
                    $("#closeEidtButton").removeClass('accEditWindow');
                }else if($("#closeEidtButton").hasClass('editWindow')){
                    $("#editItemId").val(row[0].maintainId);
                    $("#editItem").val(row[0].maintainName);
                    $("#editWindow").modal('show');
                    $("#closeEidtButton").removeClass('editWindow');
                }
            }else{
                swal({
                    title:"",
                    text: "请先选择维修保养项目", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
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



// 显示所有明细
function showDetail(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var row =  $('#table').bootstrapTable('getSelections');
            if(row.length >0) {
                maintainId = row[0].maintainId;
                $("#detailWindow").modal('show');
                initDetailTable('detailTable', '/maintain/queryByDetailsByPager?maintainId='+row[0].maintainId);
            }else{
                swal({
                    title:"",
                    text: "请选择要查看明细的维修保养记录", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"warning"}) // 提示类型
            }
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


function initDetailTable(tableId, url) {
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
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar : "#detailToolbar",// 指定工具栏
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