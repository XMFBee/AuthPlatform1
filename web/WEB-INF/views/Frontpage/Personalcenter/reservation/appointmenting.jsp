<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-switch/bootstrap-switch.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <title>维修保养预约</title>
</head>
<body>


<div class="container">
    <div class="form-box">
        <form role="form" class="form-horizontal" id="addForm">
            <input id="addUserId" type="hidden" name="userId"/>
            <div class="modal-header" style="overflow:auto;">
                <h4>添加预约信息</h4>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">保养&nbsp;|&nbsp;维修：</label>
                <div class="col-sm-7">
                    <select  class="js-example-tags form-control" name="maintainOrFix">
                        <option value="保养">保养</option>
                        <option value="维修">维修</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">车主姓名：</label>
                <div class="col-sm-7">
                    <input type="text" id="addUserName" value="${sessionScope.frontUser.userName}" placeholder="请输入车主姓名" name="userName" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">车主电话：</label>
                <div class="col-sm-7">
                    <input type="number" id="addUserPhone" value="${sessionScope.frontUser.userPhone}" placeholder="请输入车主电话" name="userPhone" class="form-control" style="width:100%"/>
                </div>
            </div>
            <div id="addCompanyBox" class="form-group">
                <label class="col-sm-3 control-label">选择公司：</label>
                <div class="col-sm-7">
                    <select id="addCompany"class="js-example-tags company" name="companyId" style="width:100%">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">汽车品牌：</label>
                <div class="col-sm-7">
                    <select  id="addCarBrand" class="js-example-tags carBrand" name="brandId" style="width:100%">
                    </select>
                </div>
            </div>
            <div id="addModelDiv" class="form-group">
                <label class="col-sm-3 control-label">汽车车型：</label>
                <div class="col-sm-7">
                    <select id="addCarModel"class="js-example-tags carModel" name="modelId" style="width:100%">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">汽车颜色：</label>
                <div class="col-sm-7">
                    <select  id="addCarColor" class="js-example-tags carColor" name="colorId" style="width:100%">
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">汽车车牌：</label>
                <div class="col-sm-7">
                    <select  id="addCarPlate" class="js-example-tags carPlate" name="plateId" style="width:100%">
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">车牌号码：</label>
                <div class="col-sm-7">
                    <input  type="number" name="carPlate" placeholder="请输入车牌号码" class="form-control"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">到店时间：</label>
                <div class="col-sm-7">
                    <input id="addArriveTime" placeholder="请选择到店时间" onclick="getDate('addArriveTime')"
                           readonly="true" type="text" name="arriveTime"
                           class="form-control datetimepicker"/>
                </div>
            </div>
            <div class="modal-footer">

                <button id="addButton" style="width: 200px;" type="button" onclick="addSubmit()" class="btn btn-success">添加
                </button>
                <input type="reset" name="reset" style="display: none;"/>
            </div>
        </form>
    </div>
</div>

<!-- 添加弹窗 -->


</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-switch/bootstrap-switch.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script>
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
</script>
</body>
</html>
