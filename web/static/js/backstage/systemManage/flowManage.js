$(function () {
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            initTable('table', '/flow/queryAllFile', "#toolbar"); // 初始化表格
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
            var swa = swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"
                },function() {
                location.href='/backstage/home';
            })

        }
    });
});

function initTable(tableId, url) {

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
        showColumns: false,  //显示下拉框勾选要显示的列
        showRefresh: false,  //显示刷新按钮
        showToggle: false, // 显示详情
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "checkinId",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar : "#toolbar",// 指定工具栏
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


function flowStatusFormatter(el, row, index) {
    var stat = checkDeployTime(row);
    if(stat === "NEW") {
        return "<p style='color: red;'>已有新版本,请重新部署</p>";
    } else if(stat === "OK"){
        return "已经部署";
    } else if(stat === "NO") {
        return "未部署";
    }
}
function deployDateFormatter(el, row, index) {
    if(row.flowDeployDate != null) {
        formatterDate(row.flowDeployDate,row,index);
    }
    return "";
}
function todoCell(el, row, index) {
    var stat = checkDeployTime(row);
    var btn = "";
    if(stat === "OK") {
        btn = '<button  type="button"   class="btn btn-danger cold disabled " >'
            + '禁用'
            + '</button>';
    } else if (stat === "NEW") {
        btn = '<button  type="button" class="btn btn-warning upd" style="margin-right:10px;" >'
            + '更新'
            + '</button>';
        btn +=  '<button  type="button"  class="btn btn-danger cold disabled "  >'
            + '禁用'
            + '</button>';
    } else if(stat === "NO") {
        btn = '<button  type="button" class="btn btn-primary deploy" >'
            + '部署'
            + '</button>';
    }
    return btn;
}

window.todoCellBtnEvent = {
    "click .cold" :     coldBtnClick ,
    "dblclick .cold" :  updBtnClick ,
    "click .upd"    :   function(e, val, row, idx) {updateProceDef(e.currentTarget, "/flow/deployFile", row.fileName);},
    "click .deploy" : function(e, val, row, idx) {updateProceDef(e.currentTarget,"/flow/deployFile", row.fileName);}
};
function coldBtnClick(e, val, row, idx) {
    var enterCtrlKey = e.ctrlKey;
    var curBtn = e.currentTarget;
    var $curBtn = $(curBtn);
    if(!enterCtrlKey) {
        if (!$curBtn.hasClass("disabled")) {
            swal(
                {title:"",
                    text:"确定删除该流程模型吗",
                    type:"warning",
                    showCancelButton:true,
                    confirmButtonColor:"#DD6B55",
                    confirmButtonText:"我确定",
                    cancelButtonText:"再考虑一下",
                    closeOnConfirm:false,
                    closeOnCancel:false
                },function(isConfirm){
                    if(isConfirm){
                        removeProceDef("/flow/removeProcessDeploy",row.flowKey);
                    }else{
                        swal({title:"", text:"已取消",  confirmButtonText:"确认", type:"success"})
                    }
                })
        }
    }
}
function updBtnClick(e,val, row, idx) {
    var enterCtrlKey = e.ctrlKey;
    var curBtn = e.currentTarget;
    var $curBtn = $(curBtn);
    if(enterCtrlKey) {
        if($curBtn.hasClass("disabled")) {
            $curBtn.removeClass("disabled");
        } else {
            $curBtn.addClass("disabled");
        }
    }
}
function updateProceDef(btn, url, fileName) {
    $(btn).attr("disabled");
    $.post(url, "fileName="+fileName,
        function(data) {
            if(data.result === "success"){
                swal({ title:"", text: data.message, type:"success"});// 提示窗口, 修改成功
                $('#table').bootstrapTable("refresh");
            } else {
                swal({ title:"", text: data.message, type:"error"});// 提示窗口, 修改失败
            }
        });

}
function removeProceDef(url, flowKey) {
    $.post(url, "flowKey="+flowKey,
        function (data) {
            if(data.result === "success"){
                swal({ title:"", text: data.message, type:"success"});// 提示窗口, 修改成功
                $('#table').bootstrapTable("refresh");
            } else {
                swal({ title:"", text: data.message, type:"error"});// 提示窗口, 修改失败
            }
        }
    )
}

function checkDeployTime(row) {
    var time = row.deployDatetime;
    if(time != undefined && time != null && time != "") {
        if(time < row.lastModified) {
            return "NEW";
        }
        return "OK";
    } else {
        return "NO";
    }
}

