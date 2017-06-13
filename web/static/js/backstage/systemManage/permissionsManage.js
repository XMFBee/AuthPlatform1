/*



$("#editForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

$("#addForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

function showAvailable(){
    alert("可用");
}

function showDisable(){
    alert("禁用");
}

function showEdit(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
        $("#editButton").removeAttr("disabled");
        $("#edit").modal('show'); // 显示弹窗
        var ceshi = row[0];
        $("#editForm").fill(ceshi);
    }else{
        swal({
            title:"",
            text: "请先选择要修改的权限", // 主要文本
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText:"确定", // 提示按钮上的文本
            type:"warning"}) // 提示类型
    }
}

function showAdd(){
    $("input[type=reset]").trigger("click");
    $("#addButton").removeAttr("disabled");
    $("#add").modal('show');
}

function showDel(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
        swal(
            {title:"",
                text:"您确定要禁用此权限吗",
                type:"warning",
                showCancelButton:true,
                confirmButtonColor:"#DD6B55",
                confirmButtonText:"我确定",
                cancelButtonText:"再考虑一下",
                closeOnConfirm:false,
                closeOnCancel:false
            },function(isConfirm){
                if(isConfirm)
                {
                    swal({title:"",
                        text:"禁用成功",
                        type:"success",
                        confirmButtonText:"确认",
                    },function(){
                    })
                }
                else{
                    swal({title:"",
                        text:"已取消",
                        confirmButtonText:"确认",
                        type:"error"})
                }
            })
    }else{
        swal({
            title:"",
            text: "请先选择要禁用的权限", // 主要文本
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText:"确定", // 提示按钮上的文本
            type:"warning"}) // 提示类型
    }
}

$(document).ready(function() {
    $("#addForm").validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules : {
            permissionName : {
                required : true,
            },
            permissionDes : {
                required : true,
            },
            permissionZHName : {
                required : true,
            },
            modulePermission : {
                required : true,
            },
        },
        messages : {
            permissionName : "请输入权限名称",
            permissionDes : "请输入权限描述",
            permissionZHName : "请输入权限中文名称",
            modulePermission : "请选择权限对应的模块"
        },
        errorPlacement : function(error, element) {
            $("#addButton").removeAttr("disabled");
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight : function(element) {
            $("#addButton").removeAttr("disabled");
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success : function(label) {
            $("#addButton").removeAttr("disabled");
            var el=label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function(form) {
            $.post("/table/add",
                $("#addForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#add").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title:"",
                            text: data.message,
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type:"success"})// 提示窗口, 修改成功
                    } else if (data.result == "fail") {
                        swal({title:"",
                            text:"添加失败",
                            confirmButtonText:"确认",
                            type:"error"})
                    }
                }, "json"
            );
        }
    })

    $("#editForm").validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules : {
            permissionName : {
                required : true,
            },
            permissionDes : {
                required : true,
            },
            permissionZHName : {
                required : true,
            },
            modulePermission : {
                required : true,
            },
        },
        messages : {
            permissionName : "请输入权限名称",
            permissionDes : "请输入权限描述",
            permissionZHName : "请输入权限中文名称",
            modulePermission : "请选择权限对应的模块"
        },
        errorPlacement : function(error, element) {
            $("#editButton").removeAttr("disabled");
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight : function(element) {
            $("#editButton").removeAttr("disabled");
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success : function(label) {
            $("#editButton").removeAttr("disabled");
            var el=label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function(form) {
            $.post("/table/edit",
                $("#editForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#edit").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title:"",
                            text: data.message,
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type:"success"})// 提示窗口, 修改成功
                    } else if (data.result == "fail") {
                        swal({title:"",
                            text:"修改失败",
                            confirmButtonText:"确认",
                            type:"error"})
                    }
                }, "json"
            );
        }
    })
});
*/


// 下面自己的

$(function(){
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            todoCellEvent();
            initSelect("#moduleSelect", "/module/queryAll" ,"moduleId", "moduleName");
            initTable('table', '/permission/queryAll'); // 初始化表格
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

function blurredQuery(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽修公司接待员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            var button = $("#ulButton");// 获取模糊查询按钮
            var text = button.text();// 获取模糊查询按钮文本
            var vaule = $("#ulInput").val();// 获取模糊查询输入框文本
            initTable('table', '/permission/blurredQuery?text='+text+'&value='+vaule);
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
};
// 表格
function formatterfun(element, row ,index){
    console.log(row);
    return (row.moduleId!=null&& row.moduleId!= "")? element.moduleName: "<p style='color:#aaa'>暂无模块</p>";
}

function formatterstatus(element, row, index) {
    return element=== 'Y'? '<p>可用</p>':
        '<p>不可用</p>'
}

function todoCell(element, row ,index){
    var statusIconClass =  row.permissionStatus === 'Y'? '': '';
    if(row.permissionStatus === "Y"){
        return '<div style="color:#428bca">' +
            '<a class="edit"  href="javascript:void(0)"><span class="glyphicon glyphicon-edit" ></span></a>' +
            '&nbsp;&nbsp;&nbsp;'
        /*'<a class="updstatus" href="javascript:void(0)"><span class="glyphicon glyphicon-remove" ></span></a>' +
         '</div> ';*/
    }/*else {
     return '<button id="btn_available"  type="button" class="btn btn-success updstatus">还原</button>';
     }*/
}

function todoCellEvent(){
    window.operateEvents = {
        'click .edit': showEdit
        /*'click .updstatus': function (e, value, row, index) {
         var msg;
         var coldMsg = "您确定要禁用此权限吗";
         var returnMsg = "您确定要启用此权限吗";
         var sucMsg;
         if(row.permissionStatus === 'Y' ){
         msg = coldMsg;
         sucMsg = "禁用成功";
         }else {
         msg=returnMsg;
         sucMsg="启用成功";
         }

         swal(
         {title:"",
         text:msg,
         type:"warning",
         showCancelButton:true,
         confirmButtonColor:"#DD6B55",
         confirmButtonText:"我确定",
         cancelButtonText:"再考虑一下",
         closeOnConfirm:false,
         closeOnCancel:false
         },
         function(isConfirm){
         if(isConfirm)
         {
         $.get("/permission/updateStatus?permissionId="+ row.permissionId+"&permissionStatus="+row.permissionStatus,
         function(data) {
         var typeTitle = "还原";
         var result = {msg: "失败", code: "error"};
         var msg = "失败";

         if(row.permissionStatus === "Y") {
         typeTitle = "删除";
         }
         if(data.result === "success") {
         result.msg = "成功";
         result.code="success";
         if(row.permissionStatus === "Y") {
         canUse();
         }else {
         recycle();
         }

         }
         swal({title:"",
         text:typeTitle + result.msg,
         type:result.code,
         confirmButtonText:"确认",
         })
         })
         }
         else{
         swal({title:"",
         text:"已取消",
         confirmButtonText:"确认",
         type:"error"})
         }
         })
         }*/
    };
}

// 显示与关闭弹窗 , 表单
function initSelect(selectId, url, name, value){
    $.get(url, function (data) {
        $(selectId).empty();//清空下拉框
        $.each(data, function (i, item) {
            $(selectId).append("<option value='" + data[i][name] + "'>&nbsp;" + data[i][value]+ "</option>");
        });
    })
}
function saveEdit(){
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#subButton").attr("disabled","disabled");
    } else {
        $("#subButton").removeAttr("disabled");
    }
}
function validator(formId) {
    console.log($("input[name=permissionId]").val());
    var perIdEl = $("#"+formId + " input[name=permissionId]")[0];
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            permissionZhname: {
                message: '权限名称验证失败',
                validators: {
                    notEmpty: {
                        message: '权限名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '权限名称必须在1至6个字符之间'
                    },
                    regexp: {
                        regexp: /^[^$&,""''\s]+$/,
                        message: '权限名称不允许存在符号'
                    },
                    remote: {
                        url: '/permission/checkPerZhName',//验证地址
                        message: '该名称的模块已存在',//提示消息
                        data: {
                            permissionId: $("input[name=permissionId]").val(),
                            permissionZhname: $("input[name=permissionZhname]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            },
            permissionDes: {
                message: '权限描述验证失败',
                validators: {
                    notEmpty: {
                        message: '权限描述不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 100,
                        message: '权限描述必须在3至100个字符之间'
                    }
                }
            },
        }
    }) .on('success.form.bv', function (e) {
        formSubmit("/permission/update", formId, editServlet);
    })
}

function formSubmit(url, formId,fun){
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            editServlet(data);
        }
    );
}

function editServlet(data) {
    var result = {code: "error", msg: "修改失败"};
    if(data.result === "success") {
        result.code = "success";
        result.msg = "修改成功"
    }
    swal({
            title:"",
            text: result.msg, // 主要文本
            confirmButtonText:"确定", // 提示按钮上的文本
            type: result.code},
        function (){
            closeModal();
            $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
        }) // 提示类型
}

function showEdit (e, value, row, index) {
    var permission = row;
    $("#editForm").fill(permission);
    $("#edit .modal-header> input").val("edit");
    $("#edit .modal-header> h4").text("修改权限管理信息");
    validator("editForm");
    $("#edit").modal('show'); // 显示弹窗
}

function closeModal(){
    $("#edit").modal("hide");
    $("#subButton").removeAttr("disabled");
    $("#editForm input[type=reset]").trigger("click"); // 移除表单中填的值
    $("#editForm").data('bootstrapValidator').destroy(); // 销毁此form表单
    $("#editForm").data('bootstrapValidator', null);// 此form表单设置为空
}



/*function addServlet(data) {
    var result = {};
    result.code = "error"
    result.msg = "添加失败";
    if(data.result === "success") {
        result.code = "success";
        result.msg = "添加成功"
    }
    swal({
        title:"",
        text: result.msg, // 主要文本
        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
        confirmButtonText:"确定", // 提示按钮上的文本
        type:result.code},function (){
        $("#subButton").removeAttr("disabled");
        closeModal();
        canUse();
    }) // 提示类型
}*/

//    function showAdd(){
//        $("#edit .modal-header> input").val("add");
//        $("#edit .modal-header> h3").text("添加权限");
//        $("#edit").modal('show'); // 显示弹窗
//    }

/*function canUse() {
    $("#toolbar2").css("display","none");
    $("#toolbar").css("display","block");
    initTable('table', '/permission/queryAll',"#toolbar"); // 初始化表格
}*/


// 批量删除与还原
//    function updStatuses(){
//        var rows =  $('#table').bootstrapTable('getSelections');
//        var status = "删除";
//        if(rows.length >0) {
//            var row = rows[0];
//            var permissionStatus = "Y";
//            if(row.permissionStatus === 'N') {
//                status = "还原";
//                permissionStatus = "N";
//            }
//            swal(
//                    {title:"",
//                        text:"您确定要"+status+"此权限吗",
//                        type:"warning",
//                        showCancelButton:true,
//                        confirmButtonColor:"#DD6B55",
//                        confirmButtonText:"我确定",
//                        cancelButtonText:"再考虑一下",
//                        closeOnConfirm:false,
//                        closeOnCancel:false
//                    },function(isConfirm){
//                        if(isConfirm)
//                        {
//                            var ids = [];
//                            $.each(rows, function(index,ele){
//                                ids.push(ele.permissionId);
//                            });
//                            $.post("/permission/updateStatuses",
//                            "status="+permissionStatus+"&permissionIdsStr="+ids.join(","),
//                                    function (data){
//                                        if(data.result === "success"){
//                                            swal({title:"",
//                                                text:status + "成功",
//                                                type:"success",
//                                                confirmButtonText:"确认",
//                                            },function(){
//                                                if(row.permissionStatus === 'N') {
//                                                    recycle();
//                                                } else {
//                                                    canUse();
//                                                }
//                                            })
//                                        }  else {
//                                            swal({title:"",
//                                                text:status + "失败",
//                                                type:"error",
//                                                confirmButtonText:"确认",
//                                            },function(){
//                                            })
//                                        }
//                                    }
//                            );
//                        }
//                        else{
//                            swal({title:"",
//                                text:"已取消",
//                                confirmButtonText:"确认",
//                                type:"error"})
//                        }
//                    })
//        }else{
//            swal({
//                title:"",
//                text: "请先选择要改变的权限", // 主要文本
//                confirmButtonColor: "#DD6B55", // 提示按钮的颜色
//                confirmButtonText:"确定", // 提示按钮上的文本
//                type:"warning"}) // 提示类型
//        }
//
//    }

// function recycle() {
//  $("#toolbar2").css("display","block");
//  $("#toolbar").css("display","none");
//  initTable('table', '/permission/queryRecycle', "#toolbar2"); // 初始化表格
//  }
//
