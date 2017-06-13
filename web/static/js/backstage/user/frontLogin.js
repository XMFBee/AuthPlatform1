var userPhone;
var rtphone;
var rtemail;
$(function () {
    // 监听手机输入的唯一验证
    $('#phone').bind('input propertychange', function() {
        userPhone = $("#phone").val();
        $("#phonecode-caveat").css("display","none");
    });
    validator('loginForm');
    validator2('regform');
})

// 登录按钮
function loginSubmit() {
    $("#loginForm").data('bootstrapValidator').validate();
    if ($("#loginForm").data('bootstrapValidator').isValid()) {
        $("#loginForm").attr("disabled", "disabled");
    } else {
        $("#loginForm").removeAttr("disabled");
    }
}

// 注册按钮
function regSubmit() {
    $("#regform").data('bootstrapValidator').validate();
    if ($("#regform").data('bootstrapValidator').isValid()) {
        $("#regform").attr("disabled", "disabled");
    } else {
        $("#regform").removeAttr("disabled");
    }
}

// 点击发送短信
var wait = 60;
function  sendCode(button) {
    time(button);
}

function time(o) {
    if (wait == 0) {
        $("#phonecode-caveat").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取短信验证码";
        wait = 60;
    } else {
        var phone = $("#phone").val();
        if(phone!= null && phone != ""){
            if(phone.length != 11){
            }else{
                $.get("/user/sendSms?phone="+phone, function (data) {
                    if(data.result == "success"){
                        o.setAttribute("disabled", true);
                        o.innerHTML = wait + "秒后可以重新发送";
                        wait--;
                        setTimeout(function () {
                                time1(o, phone)
                        }, 1000)
                    }else if(data.result == "fail"){
                        o.innerHTML = "发送失败"
                    }
                });
            }
        }else{
            $("#phonecode-caveat").html("请先输入手机号码");
            $("#phonecode-caveat").css("display","block");
        }
    }
}

function time1(o, phone) {
    if (wait == 0) {
        $("#phonecode-caveat").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取短信验证码";
        wait = 60;
    } else {
           o.setAttribute("disabled", true);
           o.innerHTML = wait + "秒后可以重新发送";
           wait--;
           setTimeout(function () {
               time1(o, phone)
           }, 1000)
    }
}

/*用户注册验证*/
function validator2(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userPhone: {
                message: '手机号错误',
                validators: {
                    notEmpty: {
                        message: '请先输入手机号码'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '手机号码格式错误'
                    },
                    remote: {
                        url: '/user/queryPhoneByOne',
                        message: '该手机号已存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            phonecode: {
                message: '验证码验证错误',
                validators: {
                    notEmpty: {
                        message: '请先输入验证码'
                    }
                }
            },
            userPwd: {
                message: '用户密码不能为空',
                validators: {
                    notEmpty: {
                        message: '用户密码不能为空'
                    }, identical: {
                        field: 'password2',
                        message: '两次输入的密码不一致'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '用户密码长度必须在6到18位之间'
                    }
                }
            },
            password2: {
                message: '密码不一致',
                validators: {
                    notEmpty: {
                        message: '用户密码不能为空'
                    },
                    identical: {
                        field: 'userPwd',
                        message: '两次输入的密码不一致'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '用户密码长度必须在6到18位之间'
                    }
                }
            },
        }
    }).on('success.form.bv', function (e) {
            $.post("/user/register", $("#regform").serialize(), function (data) {
                if (data.result == "success") {
                    swal({
                        title: "",
                        text: data.message,
                        confirmButtonText: "确认",
                        type: "success"
                    }, function (isConfirm) {
                        $.post("/user/login1",$("#regform").serialize(),function (data) {
                            if(data.result=="success"){
                                window.location.href="/backstageIndex";
                            }else if(data.result == "isOwner"){
                                window.location.href="/userpage";
                            }else if(data.result=="fail"){
                                swal({
                                    title: "",
                                    text: data.message,
                                    confirmButtonText: "确认",
                                    type: "error"
                                })
                                $("#" + formId).removeAttr("disabled");
                            }
                        });
                    })
                    $("#" + formId).removeAttr("disabled");
                } else if (data.result == "fail") {
                    swal({
                        title: "",
                        text: data.message,
                        confirmButtonText: "确认",
                        type: "error"
                    })
                    $("#" + formId).removeAttr("disabled");
                }else if(data.result == "notPhoneCode"){
                    swal({
                        title: "",
                        text: data.message,
                        confirmButtonText: "确认",
                        type: "error"
                    })
                    $("#" + formId).removeAttr("disabled");
                }
            })
    })
}

// 登录验证
function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userEmail: {
                message: '邮箱/手机号',
                validators: {
                    notEmpty: {
                        message: '邮箱/手机号'
                    }
                }
            },
            userPwd: {
                message: '用户密码不能为空',
                validators: {
                    notEmpty: {
                        message: '用户密码不能为空'
                    }
                }
            },
            checkCode: {
                message: '验证码不能为空',
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            },
        }
    }).on('success.form.bv', function (e) {
        if (formId == "loginForm") {
            $.post("/user/login",$("#loginForm").serialize(),function (data) {
                if(data.result=="success"){
                    window.location.href="/backstageIndex";
                }else if(data.result == "isOwner"){
                    window.location.href="/userpage";
                }else if(data.result=="fail"){
                    swal({
                        title: "",
                        text: data.message,
                        confirmButtonText: "确认",
                        type: "error"
                    })
                    $("#" + formId).removeAttr("disabled");
                }
            })
        }
    })
}

/**
 * 以下为找回密码
 */
function showAdd(){
    $("#addWindow").modal('show');
    $('#phoneInput').bind('input propertychange', function() {
        rtphone = $("#phoneInput").val();
        $("#phoneButton").removeAttr("disabled");
        $("#phoneCodePrint").css("display","none");
    });
    $('#emailInput').bind('input propertychange', function() {
        rtemail = $("#emailInput").val();
        $("#emailButton").removeAttr("disabled");
        $("#emailCodePrint").css("display","none");
    });
    validator3('addForm');
    validator4('addForm1')
}

// 手机验证确定按钮
function rtPhoneSubmit() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#phoneButton").attr("disabled", "disabled");
    } else {
        $("#phoneButton").removeAttr("disabled");
    }
}

// 邮箱验证确定按钮
function rtEmailSubmit() {
    $("#addForm1").data('bootstrapValidator').validate();
    if ($("#addForm1").data('bootstrapValidator').isValid()) {
        $("#emailButton").attr("disabled", "disabled");
    } else {
        $("#emailButton").removeAttr("disabled");
    }
}

// 点击发送短信
var phoneCode1 = 60;
function phoneCode(button) {
    button.setAttribute("disabled", true);
    phoneCodeTime(button);
}

function phoneCodeTime(o) {
    if (phoneCode1 == 0) {
        $("#phoneCodePrint").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取短信验证码";
        phoneCode1 = 60;
    } else {
        var phone = $("#phoneInput").val();
        if(phone!= null && phone != ""){
            if(phone.length != 11){
                o.removeAttribute("disabled", true);
            }else{
                $.get("/user/rtPhoneCode?phone="+phone, function (data) {
                    if(data.result == "success"){
                        $("#phoneCodePrint").css("display","none");
                        o.setAttribute("disabled", true);
                        o.innerHTML = phoneCode1 + "秒后可以重新发送";
                        phoneCode1--;
                        setTimeout(function () {
                            phoneCodeTime1(o, phone)
                        }, 1000)
                    }else if(data.result == "fail"){
                        o.innerHTML = "发送失败"
                    }
                });
            }
        }else{
            o.removeAttribute("disabled", true);
            $("#phoneCodePrint").html("请先输入手机号码");
            $("#phoneCodePrint").css("display","block");
        }
    }
}

function phoneCodeTime1(o, phone) {
    if (phoneCode1 == 0) {
        $("#phoneCodePrint").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取短信验证码";
        phoneCode1 = 60;
    } else {
        o.setAttribute("disabled", true);
        o.innerHTML = phoneCode1 + "秒后可以重新发送";
        phoneCode1--;
        setTimeout(function () {
            phoneCodeTime1(o, phone)
        }, 1000)
    }
}

// 点击发送邮箱
var emailCode1 = 60;
function emailCodes(button) {
    button.setAttribute("disabled", true);
    emailCodeTime(button);
}

function emailCodeTime(o) {
    if (emailCode1 == 0) {
        $("#emailCodePrint").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取邮箱验证码";
        emailCode1 = 60;
    } else {
        var phone = $("#emailInput").val();
        if(phone!= null && phone != ""){
            if(!(/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/).test(phone)){
                o.removeAttribute("disabled", true);
            }else{
                $.get("/user/rtEmailCode?email="+phone, function (data) {
                    if(data.result == "success"){
                        $("#emailCodePrint").css("display","none");
                        o.setAttribute("disabled", true);
                        o.innerHTML = emailCode1 + "秒后可以重新发送";
                        emailCode1--;
                        setTimeout(function () {
                            emailCode1Time1(o, phone)
                        }, 1000)
                    }else if(data.result == "fail"){
                        o.innerHTML = "发送失败"
                    }
                });
            }
        }else{
            o.removeAttribute("disabled", true);
            $("#emailCodePrint").html("请先输入邮箱");
            $("#emailCodePrint").css("display","block");
        }
    }
}

function emailCode1Time1(o, phone) {
    if (emailCode1 == 0) {
        $("#emailCodePrint").css("display","none");
        o.removeAttribute("disabled");
        o.innerHTML = "获取邮箱验证码";
        emailCode1 = 60;
    } else {
        o.setAttribute("disabled", true);
        o.innerHTML = emailCode1 + "秒后可以重新发送";
        emailCode1--;
        setTimeout(function () {
            emailCode1Time1(o, phone)
        }, 1000)
    }
}

function closem(){
    $("#addWindow").modal("hide");
    $("input[type=reset]").trigger("click"); // 移除表单中填的值
    $("#addForm").data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#addForm').data('bootstrapValidator', null);// 此form表单设置为空
    $("#addForm1").data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#addForm1').data('bootstrapValidator', null);// 此form表单设置为空
}

// 找回密码表单验证
function validator3(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            rtphone: {
                message: '手机号不能为空',
                validators: {
                    notEmpty: {
                        message: '手机号不能为空'
                    },
                    regexp: {
                        regexp: /^1[3578]\d{9}$/,
                        message: '请输入正确的手机号'
                    },
                    remote: {
                        url: '/user/queryPhoneIsNull',
                        message: '该手机号码绑定账号不存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            phoneCodeInput :{
                message: '验证码不能为空',
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            },
            rtpwd1: {
                message: '密码不能为空',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },identical: {
                        field: 'rtpwdagin',
                        message: '两次输入密码不一致'
                    }
                }
            },
            rtpwdagin: {
                message: '密码不能为空',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'rtpwd1',
                        message: '两次输入密码不一致'
                    }
                }
            },
        }
    }).on('success.form.bv', function (e) {
        var phone = $("#phoneInput").val();
        var code = $("#codePhone").val();
        var pwd = $("#pwdPhone").val();
        $.get("/user/phoneConfirm/"+phone+"/"+code+"/"+pwd,function (data) {
            if(data.result=="success"){
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "success"
                }, function (isConfirm) {
                    $("#addWindow").modal('hide');
                    $("#phoneButton").removeAttr("disabled");
                    $("#addForm").data('bootstrapValidator').destroy(); // 销毁此form表单
                    $('#addForm').data('bootstrapValidator', null);// 此form表单设置为空
                });
            }else if(data.result=="fail"){
                $("#phoneButton").removeAttr("disabled");
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                });
            }
        })
    })
}

// 找回密码表单验证
function validator4(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            rtemail: {
                message: '邮箱不能为空',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    regexp:{
                        regexp:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
                        message:'请输入正确的邮箱'
                    },
                    remote: {
                        url: '/user/queryEmailIsNull',
                        message: '该邮箱绑定账号不存在',
                        delay :  2000,
                        type: 'GET'
                    }
                }
            },
            emailCodeInput: {
                message: '验证码不能为空',
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            },
            rtpwd2: {
                message: '密码不能为空',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'rtpwdagin1',
                        message: '两次输入密码不一致'
                    }
                }
            },
            rtpwdagin1: {
                message: '密码不能为空',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'rtpwd2',
                        message: '两次输入密码不一致'
                    }
                }
            },
        }
    }).on('success.form.bv', function (e) {
        var email = $("#emailInput").val();
        var code = $("#codeEmail").val();
        var pwd = $("#pwdEmail").val();
        $.get("/user/emailConfirm/"+email+"/"+code+"/"+pwd,function (data) {
            if(data.result=="success"){
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "success"
                }, function (isConfirm) {
                    $("#addWindow").modal('hide');
                    $("#emailButton").removeAttr("disabled");
                    $("#addForm1").data('bootstrapValidator').destroy(); // 销毁此form表单
                    $('#addForm1').data('bootstrapValidator', null);// 此form表单设置为空
                });
            }else if(data.result=="fail"){
                $("#emailButton").removeAttr("disabled");
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                });
            }
        })
    })
}