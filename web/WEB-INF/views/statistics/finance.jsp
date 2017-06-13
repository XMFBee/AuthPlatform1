<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>收支记录统计</title>
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link href="/static/css/select2/select2.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
</head>
<style>
    .company,.project,.proname,.startime,.endtime{
        margin-left:25px;
        float: left;
    }

</style>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<script type="text/javascript" src="/static/js/echarts/echarts.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-select/select2.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>


<body>
<%@include file="../backstage/contextmenu.jsp" %>


<div class='container-fluid'>
    <h2 class='page-header'>财务图表</h2>
    <!--
        选项卡：通过BS的类来控制选项卡的样式
    -->
    <ul class='nav nav-tabs'>
        <li class='active'><a href='#tab1' data-toggle='tab'>按年查找</a></li>
        <li><a href='#tab2' data-toggle='tab'>按月查找</a></li>
        <li><a href='#tab3' data-toggle='tab'>按日查找</a></li>
        <li><a href='#tab4' data-toggle='tab'>按季度查找</a></li>
        <li><a href='#tab5' data-toggle='tab'>按周查找</a></li>
    </ul>

    <!--
        选项卡的内容定义
    -->
    <div class='tab-content'>
        <div class='tab-pane active' id='tab1'>
            <div class="form-inline">
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <div class="company" id="companySelect" style="width: 300px;">
                    <label>公司:</label>
                    <select id="yearCompanyId" name="companyId" class="form-control select2 companyName" style="width: 80%;" >
                    </select>
                </div>
                </shiro:hasAnyRoles>
                <div id="start" class="startime">

                </div>
                <div id="start" class="startime">
                    <label>
                        开始时间:
                    </label>
                    <div class="input-group">
                        <input type="text"  class="form-control form_Year" id="startYearInput" name="addtime" placeholder="请选择开始时间">
                        <span class="input-group-addon" id="startYear"><span class="glyphicon glyphicon-time"
                                                                             aria-hidden="true"></span></span>
                    </div>
                </div>
                <div id="end" class="endtime" >
                    <label>结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control form_Year" id="endYearInput" name="addtime"  placeholder="请选择结束时间">
                        <span class="input-group-addon" id="endYear"><span class="glyphicon glyphicon-time"
                                                                           aria-hidden="true"></span></span>
                    </div>
                </div>
                <button id="yearBtnId" type="button" onclick="selectYears()" class="btn btn-default" >
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class='tab-pane' id='tab2'>
            <div class="form-inline">
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <div class="company" id="companySelect" style="width: 300px;">
                        <label>公司:</label>
                        <select id="monthCompanyId" name="companyId" class="form-control select2 companyName" style="width: 80%;" >
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div id="start" class="startime">
                    <label>
                        开始时间:
                    </label>
                    <div class="input-group">
                        <input type="text"  class="form-control form_Month" id="startMonthInput" name="addtime" placeholder="请选择开始时间">
                        <span class="input-group-addon" id="startMonth"><span class="glyphicon glyphicon-time"
                                                                             aria-hidden="true"></span></span>
                    </div>
                </div>
                <div id="end" class="endtime" >
                    <label>结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control form_Month" id="endMonthInput" name="addtime"  placeholder="请选择结束时间">
                        <span class="input-group-addon" id="endMonth"><span class="glyphicon glyphicon-time"
                                                                           aria-hidden="true"></span></span>
                    </div>
                </div>
                <button id="monthBtnId" type="button" onclick="selectMonth()" class="btn btn-default" >
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class='tab-pane' id='tab3'>
            <div class="form-inline">
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <div class="company" id="companySelect" style="width: 300px;">
                        <label>公司:</label>
                        <select id="dayCompanyId" name="companyId" class="form-control select2 companyName" style="width: 80%;" >
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div id="start" class="startime">
                    <label>
                        开始时间:
                    </label>
                    <div class="input-group">
                        <input type="text"  class="form-control form_Day" id="startDayInput" name="addtime" placeholder="请选择开始时间">
                        <span class="input-group-addon" id="startDay"><span class="glyphicon glyphicon-time"
                                                                              aria-hidden="true"></span></span>
                    </div>
                </div>
                <div id="end" class="endtime" >
                    <label>结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control form_Day" id="endDayInput" name="addtime"  placeholder="请选择结束时间">
                        <span class="input-group-addon" id="endDay"><span class="glyphicon glyphicon-time"
                                                                            aria-hidden="true"></span></span>
                    </div>
                </div>
                <button id="dayBtnId" type="button" onclick="selectDay()" class="btn btn-default" >
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class='tab-pane' id='tab4'>
            <div class="form-inline">
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <div class="company" id="companySelect" style="width: 300px;">
                        <label>公司:</label>
                        <select id="quarterCompanyId" name="companyId" class="form-control select2 companyName" style="width: 80%;" >
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div id="start" class="startime">
                    <label>
                        开始时间:
                    </label>
                    <div class="input-group">
                        <input type="text"  class="form-control form_Day" id="startQuarterInput" name="addtime" placeholder="请选择开始时间">
                        <span class="input-group-addon" id="startQuarter"><span class="glyphicon glyphicon-time"
                                                                                aria-hidden="true"></span></span>
                    </div>
                </div>
                <div id="end" class="endtime" >
                    <label>结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control form_Day" id="endQuarterInput" name="addtime"  placeholder="请选择结束时间">
                        <span class="input-group-addon" id="endQuarter"><span class="glyphicon glyphicon-time"
                                                                              aria-hidden="true"></span></span>
                    </div>
                </div>
                <button id="quarterBtnId" type="button" onclick="selectQuarter()" class="btn btn-default" >
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class='tab-pane' id='tab5'>
            <div class="form-inline">
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <div class="company" id="companySelect" style="width: 300px;">
                        <label>公司:</label>
                        <select id="weekCompanyId" name="companyId" class="form-control select2 companyName" style="width: 80%;" >
                        </select>
                    </div>
                </shiro:hasAnyRoles>
                <div id="start" class="startime">
                    <label>
                        开始时间:
                    </label>
                    <div class="input-group">
                        <input type="text"  class="form-control form_Day" id="startWeekInput" name="addtime" placeholder="请选择开始时间">
                        <span class="input-group-addon" id="startWeek"><span class="glyphicon glyphicon-time"
                                                                                aria-hidden="true"></span></span>
                    </div>
                </div>
                <div id="end" class="endtime" >
                    <label>结束时间：</label>
                    <div class="input-group">
                        <input type="text" class="form-control form_Day" id="endWeekInput" name="addtime"  placeholder="请选择结束时间">
                        <span class="input-group-addon" id="endWeek"><span class="glyphicon glyphicon-time"
                                                                              aria-hidden="true"></span></span>
                    </div>
                </div>
                <button id="weekBtnId" type="button" onclick="selectWeek()" class="btn btn-default" >
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
                </button>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>

</div>

<!-- 显示Echarts图表 -->
<div style="height:600px;min-height:100%;margin:0 auto;" id="main"></div>


<script>



</script>
<!-- 显示Echarts图表 -->

<script type="text/javascript" src="/static/js/backstage/statistics/finance.js"></script>
<script src="/static/js/backstage/statistics/myEcharts.js"></script>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
</div>
<%--<script>
    function showCompany(){
        var statr = $("#start");
        var end = $("#end");
        var yaerbtnid = $("#yearBtnId");
        statr.css("display","block");
    };
    function showStart(){
        var statr = $("#start");
        var end = $("#end");
        var yaerbtnid = $("#yearBtnId");
        end.css("display","block");
    };
    function showEnd(){
        var statr = $("#start");
        var end = $("#end");
        var yaerbtnid = $("#yearBtnId");
        yaerbtnid.css("display","block");
    };
</script>--%>
</body>
</html>