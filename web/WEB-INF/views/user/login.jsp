<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/3
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>公司入驻</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/static/css/animate.min.css" rel="stylesheet">
    <link href="/static/css/style.min.css?v=4.1.0" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/city-picker/city-picker.css">
</head>
<style>
    body{
        background: url(/static/img/Frontpage/515037.jpg);
        font-family:Microsoft YaHei,Arial,simsun, Helvetica, sans-serif;
    }
    .float-e-margins{
        border-radius:5px;
        background: rgba(0,0,0,0.2);
    }
    .form-group label{
        color: white;
        font-size: 17px;
    }
    .form-group .control-label span{
        font-size: 13px;
    }
    #backindex:hover{
        color: white;
    }
</style>
<body class="gray-bg">
<div class="row">
    <div class="col-sm-6 col-md-6" style="margin: 10px 25%;">
        <span style="font-size: 20px"><a id="backindex" href="/index">返回主页</a></span>
        <div class="ibox float-e-margins">
            <div class="ibox-title" style="padding:15px 0;background:black;text-align: center;border-color: black;">
                <span style="font-size: 30px;color: #fff;">公司入驻</span>
            </div>

            <div class="ibox-content" style="background: rgba(0,0,0,0.7);">
                <form class="form-horizontal" id="loginForm">
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司名称</label>
                        <div class="col-sm-7 col-md-7">
                            <input type="text" name="companyName" placeholder="请输入公司名称" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司联系方式</label>
                        <div class="col-sm-7 col-md-7">
                            <input type="number" name="companyTel" placeholder="请输入公司联系方式" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司负责人</label>
                        <div class="col-sm-7 col-md-7">
                            <input type="text" name="companyPricipal" placeholder="请输入公司负责人" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司负责人电话</label>
                        <div class="col-sm-7 col-md-7">
                            <input type="number" name="companyPricipalphone" placeholder="请输入公司负责人电话" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司成立时间</label>
                        <div class="col-sm-7 col-md-7">
                            <input type="text" id="addDatetimepicker" name="companyOpendate" readonly="true" onclick="getDate('addDatetimepicker')" placeholder="请选择公司成立时间" class="form-control" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司规模</label>
                        <div class="col-sm-7 col-md-7">
                            <select type="date" name="companySize" placeholder="请选择公司规模" class="form-control" >
                                <option>10人以下</option>
                                <option>11-50人</option>
                                <option>51-100人</option>
                                <option>101-150人</option>
                                <option>150人以上</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4  col-md-4 control-label">公司地址</label>
                        <div class="col-sm-7 col-md-7">
                            <div style="position: relative;">
                                <input id="address" type="text" class="col-md-4" name="companyAddress"/>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-8">
                            <button class="btn btn-block btn-success " type="button" id="loginButton" onclick="loginSubmit()">
                                入驻
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/jquery.min.js?v=2.1.4"></script>
<script src="/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/static/js/plugins/layer/layer.min.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/hplus.min.js?v=4.1.0"></script>
<script src="/static/js/contabs.min.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>
<script src="/static/js/backstage/user/backstageLogin.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/jquery.cxselect.js"></script>
<script src="/static/js/city-picker/city-picker.data.js"></script>
<script src="/static/js/city-picker/city-picker.js"></script>
<script>

    $.cxSelect.defaults.url = '/static/js/cityData.min.json';
    $('#city_china').cxSelect({
        selects: ['province', 'city', 'area']
    });

    $(function () {
        initDatePicker('loginForm', 'companyOpendate', 'addDatetimepicker'); // 初始化时间框, 第一参数是form表单id, 第二参数是input的name, 第三个参数为input的id
        function bodyScroll(event) {
            event.preventDefault();
        }

        document.body.addEventListener('touchmove', bodyScroll(event), false);
        document.body.removeEventListener('touchmove', bodyScroll(event), false);
    });
    function doNothing() {
        window.event.returnValue = false;
        return false;
    }
</script>
</body>
</html>
