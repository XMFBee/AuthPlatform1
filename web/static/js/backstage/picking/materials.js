/*
$(function () {
    $('#table').bootstrapTable('hideColumn', 'id');

    $("#addSelect").select2({
            language: 'zh-CN'
        }
    );

    //绑定Ajax的内容
    $.getJSON("/table/queryType", function (data) {
        $("#addSelect").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#addSelect").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })
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
        swal({
            title:"",
            text:"请先选择一行数据",
            type:"warning"})// 提示窗口, 修改成功
    }
}

function showAdd(){

    $("#add").modal('show');
}

function formatRepo(repo){return repo.text}
function formatRepoSelection(repo){return repo.text}



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
                $.messager.alert("提示", data.result.message, "info");
            }
        }, "json"
    );
}*/


$(function(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            initSelect2("materialsCombobox", "请选择配件", "/materials/queryAllAccInv"); // 初始化select2, 第一个参数是class的名字, 第二个参数是select2的提示语, 第三个参数是select2的查询url
            $(".select2.select2-container.select2-container--default").css("width", "");
            mySelfRecord();
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



function initTable(tableId, url, toolbar) {
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
        toolbar : "#" + toolbar,// 指定工具栏
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
        }
    });
}


//未知
function queryParams(params) {   //设置查询参数
    var param = {
        pageNumber: params.pageNumber,
        pageSize: params.pageSize,
        orderNum : $("#orderNum").val()
    };
    return param;
}

// 弹窗显示一块
function showDel(t){
    var $t = $(t)[0];
    var td = $($t).parents()[1];
    var td1 = $(td).prev();
    var input = $(td).find("input")[1];
    var $input = $(input);
    var max = parseInt($input.attr("max"));
    var min = parseInt($input.attr("min"));
    var val = parseInt($input.val());
    if( val > max){
        $input.val(max);
    } else if(val <min){
        $input.val(min);
    }
    var workStatus = $("#workStatus").val();
    if(val == 0) {
        if(workStatus == 'N') {
            $input.val(1);
        } else if(workStatus == 'Y'){
            $input.val(-1);
        }
    }
    var materials = {};
    var accName = td1.text();
    var recordId = $("#seachRecordId").val();
    var accIdel = $(td).find("input")[0];
    var accCount =  $input.val();
    materials.recordId = recordId;
    materials.accId = $(accIdel).val();
    materials.accName = accName;
    materials.type = "领料";
    materials.accCount = accCount;
    materials.accCountView = accCount;
    if(materials.accCount < 0 ) {
        materials.type= "退料";
        materials.accCountView = -parseInt(accCount);
    }
    validator("materialsForm");
    $("#subButton1").removeAttr("disabled");
    $("#materialsForm").fill(materials);
    $("#formTag").val("add");
    $("#application").modal("show");

}
function showAppend(){
    validator("appendMaterialsForm");
    var recordIdEl = $("#appendMaterialsForm").find("input[name=maintainRecordId]");
    var recordId = $("#seachRecordId").val();
    $(recordIdEl).val(recordId);
    $("#subButton2").removeAttr("disabled");
    $("#appendModal").modal("show");
    $("#accsInfo").modal('hide'); // 关闭指定的窗口
}
function showWorkInfoDetail(recordId){
    initTable('workInfoAccDetailTable',"/materials/recordAccsByPager?recordId="+recordId);
    $("#accsInfo").modal("show");
    $("#seachRecordId").val(recordId);
}

// 表格数据初始一块
function initHistory(){
    // historyPanel
    initTable('materialsTable', '/materials/finishWorkByUser',"toolbar1"); // 初始化表格
    $("#historyPanel").removeClass(" panel-body active in");
    $("#reviewingPanel").removeClass(" panel-body active in");
    $("#workInfoPanel").addClass(" panel-body active in");
    // initTable('historyTable', '/materials/history', "toolbar3"); // 初始化表格
    // $("#reviewingPanel").removeClass(" panel-body active in");
    // $("#workInfoPanel").removeClass(" panel-body active in");
    // $("#historyPanel").addClass(" panel-body active in");
}
function initReviewing() {
    // reviewingPanel
    initTable("reviewingTable","/flow/queryUserFlowing","toolbar2");
    $("#workInfoPanel").removeClass(" panel-body active in");
    $("#historyPanel").removeClass(" panel-body active in");
    $("#reviewingPanel").addClass(" panel-body active in");

}


function mySelfRecord() {
    // workInfoPanel
    initTable('materialsTable', '/dispatching/userWorksByPager',"toolbar1"); // 初始化表格
    $("#historyPanel").removeClass(" panel-body active in");
    $("#reviewingPanel").removeClass(" panel-body active in");
    $("#workInfoPanel").addClass(" panel-body active in");
}

// 初始表格用得到的基本方法
function abcd(tableId, url) {
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
        strictSearch: false,
        uniqueId: "accessories.accId",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server", //表示服务端请求
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize
            };
            return param;
        },
    });
}

// 验证一块
function checkForm(formId, btnId) {
    $("#"+ formId).data('bootstrapValidator').validate();
    if ($("#"+ formId).data('bootstrapValidator').isValid()) {
        $("#"+btnId).attr("disabled","disabled");
    } else {
        $("#"+btnId).removeAttr("disabled");
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
            materialCount: {
                message: '数量验证失败',
                validators: {
                    notEmpty: {
                        message: '数量不能为空'
                    },
                    numeric: {
                        message: '数量至少为1'
                    }
                }
            },
            accId: {
                message: '配件验证失败',
                validators: {
                    notEmpty: {
                        message: '请选择配件'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        if (formId == "materialsForm") {
            var tag = $("#formTag").val(); // edit  add
            if(tag === "add") {
                formSubmit("/flow/subForm", "application", formId,"subButton1", "workInfoAccDetailTable");
            } else if(tag === "edit") {
                formSubmit("/flow/reSubForm", "application", formId,"subButton1", "reviewingTable");
            }
        } else if (formId == "appendMaterialsForm") {
            formSubmit("/materials/insert","appendModal", formId, "subButton2", "materialsTable");
        }
    })
}

// 向与后台连接一块
function formSubmit(url, modalId ,formId, subBtnId,tableId){
    $.post(url,
        $("#"+ formId).serialize(),
        function (data) {
            if (data.result == "success") {
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确认",
                    type:"success"});// 提示窗口, 修改成功
                $('#'+tableId).bootstrapTable("refresh");
                $("#"+subBtnId).removeAttr("disable");
            } else if (data.result == "fail") {
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确认",
                    type:"error"}) // 提示窗口, 修改失败
            }
            closeModal(modalId,formId);
        });
}
function removeMaterialsProInst(url, proInstId) {
    $.get(url +"?processInstanceId="+proInstId ,function(data){
        if(data.result === "success") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText:"确认",
                type:"success"});// 提示窗口, 修改成功
            $('#reviewingTable').bootstrapTable('refresh');
        } else if(data.result === "fail") {
            swal({
                title:"",
                text: data.message,
                confirmButtonText:"确认",
                type:"error"});// 提示窗口, 修改成功
        }
        // closeModal("application", "materialsForm");
    } )
}

// tableFormatter一块
function todoCell(ele, row, index) {
    $("#workStatus").val(row.workStatus);
    return "<button class='btn btn-default' onclick='showWorkInfoDetail(\"" + row.maintainRecord.recordId +"\")'><span class='glyphicon glyphicon-list-alt icon-list-alt' style='margin-right: 5px;' ></span><span>查看清单</span></button>";
}

function reTodoBtn(ele, row, index) {
    var retodoHtml = [];
    if (row.varsMap.respMsg != null) {
        retodoHtml.push('<button type="button" class="btn btn-success resub" style="margin-right:10px">');
        retodoHtml.push('<span class="glyphicon glyphicon-edit" style="margin-right: 3px;"></span>');
        retodoHtml.push('重新提交');
        retodoHtml.push('</button>');
    }
    retodoHtml.push('<button type="button"  class="btn btn-danger removeMa">');
    retodoHtml.push('<span class="glyphicon glyphicon-remove" style="margin-right: 3px;"></span>');
    retodoHtml.push('取消申请');
    retodoHtml.push('</button>');
    return retodoHtml.join("");
}
function countFormatter(ele, row, index) {
    return ele;
}
function userRequestsFormatter(ele ,row, index) {
    var reqView = "";
    if(ele) {
        if(ele.length>10) {
            reqView = ele.slice(0,10)+ "...";
        } else {
            reqView = ele;
        }
    } else {
        reqView = "<p style='color:#aaa'> 暂无留言</p>"
        ele = "暂无留言"
    }
    var reqHtml = "<span title='"+ ele +"'> "+ reqView +" </span>"
    return reqHtml;
}


function getReturnAndUseCount(counts) {
    var rAu = {};
    var rtn = 0;
    var use = 0;
    for(var i = 0,len = counts.length; i<len ;i++ ) {
        var count = counts[i];
        if(count < 0) { rtn+= parseInt(count) }
        else { use+= parseInt(count) }
    }

    rAu.rtn = rtn;
    rAu.use = use;
    return rAu;
}
function accInfoFormat(element, row, index){
    var rAu = {rtn: 0, use: 0};
    if(row.other.materialURTemp) {
        rAu = getReturnAndUseCount(row.other.materialURTemp.varsMap.accCount);
    }
    console.log(row)
    var min ;
    if(!isnull(row.materialUse)&&!isnull(row.materialReturn)){
        min = row.materialUse.accCount- row.materialReturn.accCount;
    } else if(!isnull(row.materialUse)){
        min = row.materialUse.accCount;
    } else {
        min = 0;
    }
    var max = row.materialCount - min;
    var htmltest = [];
    htmltest.push("<input style='display:none' value=" + row.accessories.accId+ " /> ");
    htmltest.push("<div style='display: inline-block;' class='col-md-7'>");
    htmltest.push("<span >所需数量: </span>");
    htmltest.push(row.materialCount);
    htmltest.push("</span></br>");
    htmltest.push("<span >审核中: </span>");
    if(rAu.use != 0 || rAu.rtn != 0) {
        htmltest.push("<span style='color:red'>");
        if(rAu.use != 0) {htmltest.push("<small style='margin-left:10px;'>领取: <b>"+ rAu.use +"</b> </small>");}
        if(rAu.rtn != 0) {htmltest.push("<small style='margin-left:10px;'>退回: <b>"+ rAu.rtn +"</b></small>");}
        htmltest.push("</span>");
    } else {
        htmltest.push("<span style='color:#aaa; margin-left:10px;'> 暂无! </span>");
    }
    htmltest.push("</span></br>");

    htmltest.push("<span '>已领取: </span>");
    htmltest.push("<span style='margin-right:10px;'>");
    htmltest.push(min);
    var numMax = (max-rAu.use);
    var numMin = (-min-rAu.rtn);
    var workStatus = $("#workStatus").val();
    if(workStatus == 'Y'){
        var numMax = -1;
    }
    htmltest.push("<input type='number'  max="+ numMax +" min="+ numMin +" class='form-control text-center'  value='0'  style='width:100px;margin-left:20px;display: inline-block; width:80px'>");
    htmltest.push("</span>");
    htmltest.push("<span class='glyphicon glyphicon-question-sign' title='正为领料,负为退料'>");
    htmltest.push("</span>");
    htmltest.push("</div>");
    htmltest.push("<div style='float:right;height:100%;text-align: center;' class='col-md-4'>");

    if(min >= row.materialCount ){
        htmltest.push("<div style='position: relative;top:-10px;right:-10px;height:60px' class='materialsSuc' ></div>");

    }else if ((min+rAu.use) >= row.materialCount) {
        htmltest.push("<span class='bgFont'><p>请耐心等待</p><p>审核完成</p></span>");
    } else{
        htmltest.push("<button type='button' class='btn btn-success' onclick='showDel(this);' style='height:60px;top:10px;position:inherit; width:100%;'>领料/退料</button>");
    }
    htmltest.push("</div>");
    return htmltest.join("");
}
function reviewUserFormatter(el, row, index) {
    var varsMap = row.varsMap;
    var reviewCellHtml = "";
    reviewCellHtml= "未审核";
    if(varsMap.respMsg!=null && varsMap.respMsg != undefined && varsMap.respMsg != "") {
        reviewCellHtml = '<span style="margin-right:5px;">未通过</span><span  class="glyphicon glyphicon-question-sign" title="原因: '+ varsMap.respMsg +'"></span>'
    }
    /*reviewCellHtml.push("<p>审核人:</p> "+ row.workInfo.user.userName + " </br>");
     reviewCellHtml.push("<p>审核时间:</p> " + formatterDate(row.taskTemp.endTime)+ " </br>");
     reviewCellHtml.push("<p>原因:</p> " + varsMap.respMsg);*/
    return reviewCellHtml;
}

function noSucMsgPopEve() {
    $(".noSucMsg").popover();
}
function todoType(ele, row, index) {
    if(ele === 'U'){
        return '领料';
    }else {
        return '退料';
    }
}
// 在上方方法里有用到的
function isnull(obj){
    if(obj===null) {
        return true;
    }
    return false;
}
var resubbtnevent = {
    "click .resub" : resub,
    "click .removeMa" :removeMa
}

// 取消或重新提交订单一块
function removeMa(e, value, row, index) {
    swal(
        {title:"",
            text:"确定删除该申请吗",
            type:"warning",
            showCancelButton:true,
            confirmButtonColor:"#DD6B55",
            confirmButtonText:"我确定",
            cancelButtonText:"再考虑一下",
            closeOnConfirm:false,
            closeOnCancel:false
        },function(isConfirm){
            if(isConfirm){
                removeMaterialsProInst("/flow/removeMaterialProInst", row.processInstanceId);
            }else{
                swal({title:"",
                    text:"已取消",
                    confirmButtonText:"确认",
                    type:"success"})
            }
        })
}
function  resub(e, value, row, index) {
    var varsMap = row.varsMap;
    var materials = {};
    materials.recordId = varsMap.recordId;
    materials.accId = varsMap.accId;
    materials.accCount = varsMap.accCount;
    if(materials.accCount > 0)  materials.type = "领料";
    else                        materials.type = "退料";
    materials.accName = varsMap.accName;
    materials.accCountView = Math.abs(varsMap.accCount);
    materials.reqMsg =varsMap.reqMsg;
    materials.processInstanceId = row.processInstanceId;
    $("#formTag").val("edit");
    var reSubFormNumInput = $("#materialsForm input[type=number]");
    $(reSubFormNumInput).removeAttr("readonly");
    validator("materialsForm");
    $("#subButton1").removeAttr("disabled");
    $("#materialsForm").fill(materials);
    $("#application").modal("show");
}

// 通用的零碎的方法
function  closeModal(modalId,formId) {
    $("#"+ modalId).modal("hide");
    $('#'+ formId + " input[type=reset]").trigger("click"); // 移除表单中填的值
    $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
}

/*
function showDel(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
        $("#del").modal('show');
    }else{
        $("#tanchuang").modal('show');
    }
}*/

function formatterCarPlate(val, row, index) {
    var plateName = row.record.checkin.plate.plateName;
    return plateName + " · " + val;
}

function formatterPlate(el, row) {
    var checkin = row.maintainRecord.checkin;
    return checkin.plate.plateName + " · " + checkin.carPlate;
}