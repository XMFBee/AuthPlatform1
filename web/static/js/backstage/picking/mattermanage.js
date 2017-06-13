/*
$(function () {
    $('#table').bootstrapTable('hideColumn', 'id');

    $("#addSelect").select2({
            language: 'zh-CN'
        }
    );

    //绑定Ajax的内容
    // $.getJSON("/table/queryType", function (data) {
    //     $("#addSelect").empty();//清空下拉框
    //     $.each(data, function (i, item) {
    //         $("#addSelect").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
    //     });
    // })
//            $("#addSelect").on("select2:select",
//                    function (e) {
//                        alert(e)
//                        alert("select2:select", e);
//            });
});

function showEdit(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
        $("#edit").modal('show'); // 显示弹窗
        var ceshi = row[0];
        $("#editForm").fill(ceshi);
    }else{
        //layer.msg("请先选择某一行", {time : 1500, icon : 2});
        layer.alert("请先选择某一行");
    }
}

function showAdd(){

    $("#add").modal('show');
}

function formatRepo(repo){return repo.text}
function formatRepoSelection(repo){return repo.text}

function showDel(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
        $("#del").modal('show');
    }else{
        $("#tanchuang").modal('show');
    }
}

function checkAdd(){
    var id = $('#addId').val();
    var name = $('#addName').val();
    var price = $('#addPrice').val();
    var reslist=$("#addSelect").select2("data"); //获取多选的值
    alert(reslist.length)
    if(id != "" && name != "" && price != ""){
        return true;
    }else{
        var error = document.getElementById("addError");
        error.innerHTML = "请输入正确的数据";
        return false;
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
                    title:"",
                    text: data.message,
                    type:"success"})// 提示窗口, 修改成功
            } else if (data.result == "fail") {
                //$.messager.alert("提示", data.result.message, "info");
            }
        }, "json"
    );
}*/


window.btnevent ={
    "click .ok": y,
    "click .no": n
};
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司库管人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            initAcquisitionTab(); //初始化表格
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
        } else if(data.result == 'notRole'){
            swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"})
        }
    })
});

function initAcquisitionTab(){
    initTable('materialsTable', '/flow/queryAcquisition'); // 重置领料表格
    $('#materialsTable').bootstrapTable('hideColumn', 'endUser');
    $('#materialsTable').bootstrapTable('showColumn', 'todoCell');
};
function initReturnedTab(){
    initTable('materialsTable', '/flow/queryReturned'); // 重置退料表格
    $('#materialsTable').bootstrapTable('hideColumn', 'endUser');
    $('#materialsTable').bootstrapTable('showColumn', 'todoCell');
};
function initHistoryTab(){
    initTable('materialsTable', '/flow/queryAccManagerHistory'); // 重置历史表格
    $('#materialsTable').bootstrapTable('hideColumn', 'todoCell');
    $('#materialsTable').bootstrapTable('showColumn', 'endUser');
};

function todoCell(el, row, index) {
    var wantCount = row.varsMap.accCount;
    var canUseCount = row.varsMap.acc.accIdle ;
    var disable = "";

    var okbtn = '<button type="button"  class="btn btn-primary ok" style="margin-right:10px">同意'
        + '</button>';
    if(wantCount > 0 && wantCount > canUseCount) {
        okbtn = '<button type="button" disabled class="btn btn-primary ok" style="margin-right:10px" >同意'
            + '</button>';
    }
    var nobtn = '<button type="button" class="btn btn-warning no" > 不同意'
        + '</button>';
    return okbtn+ nobtn;
}


function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        }
    }).on('success.form.bv', function (e) {
        formSubmit("/materials/doReview", "reviewModal", formId, "subButton1");
//            if (formId == "materialsForm") {
//                formSubmit("/flow/subForm", "addWindow", formId,"subButton1");
//            } else if (formId == "editForm") {
//                formSubmit("/checkin/edit","editWindow", formId, "subButton2");
//            }
    })
}

function formSubmit(url, modalId, formId, btnId) {
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            var type="error";
            if(data.result === "success"){
                type = "success";
                $('#materialsTable').bootstrapTable('refresh');
                $("#subButton1").removeAttr("disabled");
            }

            swal({
                title:"",
                text: data.message,
                confirmButtonText:"确定", // 提示按钮上的文本
                type:type})// 提示窗口, 修改成功
            closeModal(modalId, formId);
        }
    )
}

function endUserFormatter(el, row, index) {
    var workInfo = row.workInfo;
    if(workInfo != null) {
        return workInfo.user.userName;
    }
    return "";
}

function accCountFormatter(el, row, index) {
    return Math.abs(el);
}

function dateTimeFormatter(el, row, index) {
    var proIns = row.processInstance;
    var begin = formatterDate(proIns.startTime);
    var end = "";
    var htmlTest = begin;
    if(proIns.endTime != null ) {
        end = formatterDate(proIns.endTime)
        htmlTest += "<br/>"+ end;
    }
    return htmlTest;
}

function checkReview() {
    $("#reviewForm").data('bootstrapValidator').validate();
    if ($("#reviewForm").data('bootstrapValidator').isValid()) {
        $("#subButton1").attr("disabled","disabled");
    } else {
        $("#subButton1").removeAttr("disabled");
    }
}

function closeModal(modalId, formId) {
    $("#"+ modalId).modal("hide");
    $('#'+ formId + " input[type=reset]").trigger("click"); // 移除表单中填的值
    $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
}

function showModal(modalId ,formId, obj) {
    validator(formId);
    $("#"+modalId).modal("show");
    $("#"+formId).fill(obj);
}

function y(e, value, row, index) {
    var resp = {};
    resp.isOK = true;
    resp.processInstanceId = row.processInstanceId;
    $("#subButton1").removeAttr("disable");
    showModal("reviewModal","reviewForm", resp);
}
function n(e, value, row, index) {
    var resp = {};
    resp.isOK = false;
    resp.processInstanceId = row.processInstanceId;
    $("#subButton1").removeAttr("disable");
    showModal("reviewModal","reviewForm", resp);
}

function reqMsgFormatter(value, row, index) {
    var valView = "";
    if(value.length>10) {
         valView = value.slice(0,10)+"...";
    } else {
        valView = value;
    }
    var cellHtml = '<div title="'+ value +'">' + valView + '</div>'
    return cellHtml;
}
