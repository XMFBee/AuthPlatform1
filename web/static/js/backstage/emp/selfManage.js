$(function() {
    if(iconSrc == null || iconSrc == "null" || iconSrc == "") {
        $("#detailUserIcon").attr("src","/static/img/default.png");
    } else {
        iconSrc = "/" + iconSrc;
        $("#detailUserIcon").attr("src", iconSrc);
    }

    // var gender = $("#detailGender").val();
    var resultGender = formatterSex(gender);
    $("#detailGender").val(resultGender);

    var birthday = $("#detailBirthday").val();
    if(birthday != null && birthday != "") {
        $('#detailBirthday').val(formatterDate(new Date(birthday)));
    } else {
        $('#detailBirthday').val("未选择生日");
    }

    var createdTime = $("#detailCreatedTime").val();  /* 创建时间 */
    var formatterCreateTime = formatterDateTime(createdTime);
    $("#detailCreatedTime").val(formatterCreateTime);

    var loginTime = $("#detailLoginTime").val();  /* 登录时间 */
    var formatterLoginTime = formatterDateTime(loginTime);
    if(formatterLoginTime == null || formatterLoginTime == '') {
        $("#detailLoginTime").val("未登录过");
    } else {
        $("#detailLoginTime").val(formatterLoginTime);
    }

    var addressString = $("#userAddress").val();
    if(addressString != null && addressString != "null" && addressString != 0) {
        $("#userAddress").val(addressString);
    } else {
        $("#userAddress").val("未选择");
    }
});

// 修改窗口有问题*************************************************************************************
function showEdit(){
    // 初始化时间框, 第一参数是form表单id, 第二参数是input的name
    initDatePicker('editForm', 'userBirthday', 'editDatetimepicker');
    var birthday = $('#editDatetimepicker').val();
    $('#editDatetimepicker').val(formatterDate(birthday));
    // $('#editUserGender').val(gender);
    // var gender = $("#editUserGender").val();
    var resultGender = formatterSex(gender);
    $("#editUserGender").val(resultGender);
    var genderTempObj = {"gender": gender};
    $("#editForm").fill(genderTempObj);
    $("#editWindow").modal('show'); // 显示弹窗
    $("#editButton").removeAttr("disabled");
    initCityPicker("editAddress");//初始化三级地区联动
    validator('editForm');
}

function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                message: '用户名验证失败',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '用户名长度必须在1到10位之间'
                    }
                }
            },
            userEmail: {
                message: '邮箱验证失败',
                validators: {
                    remote: {
                        url: '/userBasicManage/queryIsEmailByOne',//验证邮箱
                        message: '该邮箱已存在',//提示消息
                        data: {
                            userId: $("#"+formId + " input[name=userId]").val(),
                            userEmail: $("#" + formId + " input[name=userEmail]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            },
            userIdentity: {
                message: '身份证验证失败',
                validators: {
                    notEmpty: {
                        message: '身份证不能为空'
                    },
                    stringLength: {
                        min:18,
                        max: 18,
                        message: '身份证长度为18位'
                    },
                    remote: {
                        url: '/userBasicManage/queryIsIdentityByOne',//验证身份证
                        message: '该身份证已存在',//提示消息
                        data: {
                            userId: $("#" + formId + " input[name=userId]").val(),
                            userIdentity: $("#" + formId + " input[name=userIdentity]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            },
            userPhone: {
                message: '手机号验证失败',
                validators: {
                    notEmpty: {
                        message: '用户手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号码必须为11位'
                    },
                    regexp: {
                        regexp: /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/,
                        message: '请输入正确的手机号'
                    },
                    remote: {
                        url: '/userBasicManage/queryIsPhoneByOne',//验证号码
                        message: '该手机号已存在',//提示消息
                        data: {
                            userId: $("#" + formId + " input[name=userId]").val(),
                            userPhone: $("#" + formId + " input[name=userPhone]").val()
                        },
                        delay :  2000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type: 'POST'//请求方式
                    }
                }
            },
        }
    })
    .on('success.form.bv', function (e) {
        formSubmit("/userBasicManage/updateSelfManage", formId, "editWindow");
    })
}

function formSubmit(url, formId, winId) {
    $.post(url, $("#" + formId).serialize(), function (data) {
        if (data.result == "success") {
            $('#' + winId).modal('hide');
            swal({
                title: "",
                text: data.message,
                confirmButtonText: "确定", // 提示按钮上的文本
                type: "success"
            })// 提示窗口, 修改成功
            $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
        } else if (data.result == "fail") {
            swal({
                title: "",
                text: "修改失败",
                confirmButtonText: "确认",
                type: "error"
            })
            if (formId == 'editForm') {
                $("#editButton").removeAttr("disabled");
            }
        } else if (data.result == "notLogin") {
            swal({
                title: "",
                text: data.message,
                confirmButtonText: "确认",
                type: "error"
            }, function (isConfirm) {
                if (isConfirm) {
                    top.location = "/user/loginPage";
                } else {
                    top.location = "/user/loginPage";
                }
            })
            if (formId == 'editForm') {
                $("#editButton").removeAttr("disabled");
            }
        }
    }, "json");
}


//   提交修改的数据
function editSubmit() {
    // window.parent.refresh(window.name);
    $("#editForm").data('bootstrapValidator').validate();
    if ($("#editForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled","disabled");
    } else {
        $("#editButton").removeAttr("disabled");
    }
}

function formatterSex(val) {
    if (val == 'N') {
        return "未选择";
    } else if (val == 'M') {
        return "男"
    } else if (val == 'F') {
        return "女"
    }
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
        return year + "-" + month + "-" + day
    }
}

//格式化带时分秒的时间值。
function formatterDateTime(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    } else {
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
