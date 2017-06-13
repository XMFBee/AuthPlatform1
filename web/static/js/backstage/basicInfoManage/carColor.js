
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/carColor/queryByPagerCarColor'); // 初始化表格
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

function showEdit() {
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if (data.result == 'success') {
            var row = $('#table').bootstrapTable('getSelections');
            if (row.length > 0) {
                $("#editWindow").modal('show'); // 显示弹窗
                $("#editButton").removeAttr("disabled");
                var ceshi = row[0];
                $("#editForm").fill(ceshi);
                validator('editForm');
                // $('#editButton').trigger("click");
                // $('#editcolorName').bind('input propertychange', function() {
                //     colorName = $("#editcolorName").val();
                // });
            } else {
                swal({
                    title: "",
                    text: "请选择要修改的汽车颜色记录", // 主要文本
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                }) // 提示类型
            }
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
    });
    // var row = $('table').bootstrapTable('getSelections');
    // if (row.length > 0) {
    //     $("#editWindow").modal('show'); // 显示弹窗
    //     $("#editButton").removeAttr("disabled");
    //     var ceshi = row[0];
    //     $("#editForm").fill(ceshi);
    //     validator('editForm');
    // } else {
    //     swal({
    //         "title": "",
    //         "text": "请先选择一条数据",
    //         "type": "warning"
    //     })
    // }
}

function showAdd() {
    // $("#addWindow").modal('show');
    // $("#addButton").removeAttr("disabled");
    // validator('addForm'); // 初始化验证
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if (data.result == 'success') {
            $("#addWindow").modal('show');
            $("#addButton").removeAttr("disabled");
            // $('#addcolorName').bind('input propertychange', function() {
            //     colorName = $("#addcolorName").val();
            // });
            validator('addForm'); // 初始化验证
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
            colorName: {
                message: '颜色名称验证失败',
                validators: {
                    notEmpty: {
                        message: '颜色名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 5,
                        message: '颜色名称长度必须在1到5位之间'
                    },
                    remote: {
                        url: '/carColor/querycolorName',
                        message: '该颜色名称已存在',
                        delay :  2000,
                        type: 'POST',
                        data: {
                            colorId: $("#"+formId + " input[name=colorId]").val(),
                            colorName: $("#" + formId + " input[name=colorName]").val()
                        }
                    }
                }
            },
            colorHex: {
                message: '颜色的16进制值验证失败',
                validators: {
                    notEmpty: {
                        message: '颜色的16进制值不能为空'
                    }
                }
            },
        }
    })
        .on('success.form.bv', function (e) {
            // alert("123213213")
            if (formId == "addForm") {
                formSubmit("/carColor/addCarColor", formId, "addWindow");

            } else if (formId == "editForm") {

                formSubmit("/carColor/updateCarColor", formId, "editWindow");

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

function editSubmit(){
    setTimeout(function () {
        console.log("editSub");
        $("#editForm").data('bootstrapValidator').validate();
        if ($("#editForm").data('bootstrapValidator').isValid()) {
            $("#editButton").attr("disabled","disabled");
        } else {

            $("#editButton").removeAttr("disabled");
        }
    },100)
}

function formSubmit(url, formId, winId){

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
                    // $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
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

    // $.post(url,
    //     $("#" + formId).serialize(),
    //     function (data) {
    //         if (data.result == "success") {
    //             $('#' + winId).modal('hide');
    //             swal({
    //                 title:"",
    //                 text: data.message,
    //                 confirmButtonText:"确定", // 提示按钮上的文本
    //                 type:"success"})// 提示窗口, 修改成功
    //             $('#table').bootstrapTable('refresh');
    //             if(formId == 'addForm'){
    //                 $("input[type=reset]").trigger("click"); // 移除表单中填的值
    //                 $('#addForm').data('bootstrapValidator').resetForm(true); // 移除所有验证样式
    //                 $("#addButton").removeAttr("disabled"); // 移除不可点击
    //                 $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
    //                 $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
    //             }
    //         } else if (data.result == "fail") {
    //             swal({title:"",
    //                 text:"添加失败",
    //                 confirmButtonText:"确认",
    //                 type:"error"})
    //             $("#"+formId).removeAttr("disabled");
    //         }
    //     }, "json");
}


//获取hex颜色值后转换成rgb颜色值后自动添加到rgb颜色框中
function showAddHex() {
    var a = document.getElementById("addColor").value;
    if (a.substr(0, 1) == "#") a = a.substring(1);
    // if (a.length != 6)return alert("请输入正确的十六进制颜色码！")
    a = a.toLowerCase()
    b = new Array();
    for (x = 0; x < 3; x++) {
        b[0] = a.substr(x * 2, 2)
        b[3] = "0123456789abcdef";
        b[1] = b[0].substr(0, 1)
        b[2] = b[0].substr(1, 1)
        b[20 + x] = b[3].indexOf(b[1]) * 16 + b[3].indexOf(b[2])
    }
    var rbgNumber = b[20] + "," + b[21] + "," + b[22];
    var rgbColor = document.getElementById("addrgbColor");
    rgbColor.value = rbgNumber;
}

//获取hex颜色值后转换成rgb颜色值后自动添加到rgb颜色框中
function showEditHex() {
    var a = document.getElementById("editColor").value;
    if (a.substr(0, 1) == "#") a = a.substring(1);
    // if (a.length != 6)return alert("请输入正确的十六进制颜色码！")
    a = a.toLowerCase()
    b = new Array();
    for (x = 0; x < 3; x++) {
        b[0] = a.substr(x * 2, 2)
        b[3] = "0123456789abcdef";
        b[1] = b[0].substr(0, 1)
        b[2] = b[0].substr(1, 1)
        b[20 + x] = b[3].indexOf(b[1]) * 16 + b[3].indexOf(b[2])
    }
    var rbgNumber = b[20] + "," + b[21] + "," + b[22];
    var rgbColorinput = document.getElementById("editrgbColor");
        rgbColorinput.value = rbgNumber;
}


function getkey(e, n) {
    var keynum;
    if (window.event) keynum = e.keyCode; else if (e.which) keynum = e.which;
    if (keynum == 13) {
        if (n == 0) showRGB(); else showRGB2();
    }
}


//颜色控件初始化
$(document).ready(function () {
    $('#addColor').each(function () {
        $(this).minicolors({
            control: $(this).attr('data-control') || 'hue',
            defaultValue: $(this).attr('data-defaultValue') || '',
            inline: $(this).attr('data-inline') === 'true',
            letterCase: $(this).attr('data-letterCase') || 'lowercase',
            opacity: $(this).attr('data-opacity'),
            position: $(this).attr('data-position') || 'bottom left',
            change: function (hex, opacity) {
                if (!hex) return;
                if (opacity) hex += ', ' + opacity;
                try {
                    console.log(hex);
                } catch (e) {
                }
            },
            theme: 'bootstrap'
        });
    });
});


//颜色控件初始化
$(document).ready(function () {
    $('#editColor').each(function () {
        $(this).minicolors({
            control: $(this).attr('data-control') || 'hue',
            defaultValue: $(this).attr('data-defaultValue') || '',
            inline: $(this).attr('data-inline') === 'true',
            letterCase: $(this).attr('data-letterCase') || 'lowercase',
            opacity: $(this).attr('data-opacity'),
            position: $(this).attr('data-position') || 'bottom left',
            change: function (hex, opacity) {
                if (!hex) return;
                if (opacity) hex += ', ' + opacity;
                try {
                    console.log(hex);
                } catch (e) {
                }
            },
            theme: 'bootstrap'
        });
    });
});


// 激活或禁用
function statusFormatter(value, row, index) {
    if(value == 'Y') {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/carColor/statusOperate?id='+row.colorId+'&status=Y'+"\")'>禁用</a>";
    } else {
        return "&nbsp;&nbsp;<button type='button' class='btn btn-success' onclick='active(\""+'/carColor/statusOperate?id='+ row.colorId+'&status=N'+ "\")'>激活</a>";
    }
}

// 查看全部可用
function showAvailable(){
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initTable('table', '/carColor/queryByPagerCarColor');
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
            initTable('table', '/carColor/queryByPagerDisable');
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