/**
 * Created by 不曾有黑夜 on 2017/5/25.
 */
function addApp() {
    $.post("/addApp",$("#form1").serialize(),function (data) {
        if(data.result=="success"){
            alert(data.message);
        }else{
            alert(data.message)
        }
    })
}
$(function () {
    validator('addForm'); // 初始化验证
    initDateTimePicker('addForm', 'arriveTime', 'addArriveTime'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name, 第三个参数为input的id
    initSelect2("company", "请选择公司", "/company/queryAllCompany");
    initSelect2("carBrand", "请选择品牌", "/carBrand/queryAllCarBrand");
    initSelect2("carColor", "请选择颜色", "/carColor/queryAllCarColor");
    initSelect2("carPlate", "请选择车牌", "/carPlate/queryAllCarPlate");
    initSelect2("carModel", "请选择车型", "/carModel/queryAllCarBrand");
})

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
                        max: 6,
                        message: '用户名长度必须在1到6位之间'
                    }
                }
            },
            userPhone: {
                message: '用户手机号失败',
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
                    }
                }
            },
            companyId: {
                message: '选择公司验证失败',
                validators: {
                    notEmpty: {
                        message: '公司不能为空'
                    }
                }
            },
            brandId: {
                message: '汽车品牌验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车品牌不能为空'
                    }
                }
            },
            colorId: {
                message: '汽车颜色验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车颜色不能为空'
                    }
                }
            },
            modelId: {
                message: '汽车车型验证失败',
                validators: {
                    notEmpty: {
                        message: '汽车车型不能为空'
                    }
                }
            },
            carPlate: {
                message: '车牌号码验证失败',
                validators: {
                    notEmpty: {
                        message: '车牌号码不能为空'
                    }, stringLength: {
                        min: 5,
                        max: 5,
                        message: '车牌号码必须为5位'
                    },
                }
            },
            plateId: {
                message: '汽车车牌验证失败',
                validators: {
                    notEmpty: {
                        message: '车牌不能为空'
                    }
                }
            },
            arriveTime: {
                message: '到店时间验证失败',
                validators: {
                    notEmpty: {
                        message: '到店时间不能为空'
                    }
                }
            }
        }
    })
        .on('success.form.bv', function (e) {
            if (formId == "addForm") {
                formSubmit("/appointmentAdd", formId);

            }
        })
}
function addSubmit(){

    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#addButton").attr("disabled","disabled");
    } else {
        $("#addButton").removeAttr("disabled");
    }
}

function formSubmit(url, formId){
    $.post(url,
        $("#" + formId).serialize(),
        function (data) {
            if (data.result == "success") {
                $("input[type=reset]").trigger("click"); // 移除表单中填的值
                $("#addButton").removeAttr("disabled"); // 移除不可点击
                // 设置select2的值为空
                $("#addCompany").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                $("#addCarBrand").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                $("#addCarModel").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                $("#addCarColor").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                $("#addCarPlate").html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "success"
                })
            } else if (data.result == "fail" ) {
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                })
            }
        }, "json");
}