<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>汽车维修保养系统</title>

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="/static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/static/css/animate.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link href="/static/css/style.min.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper" style="margin-bottom: 20px;display: none">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header" style="padding-top: 20px;">
                    <div class="dropdown profile-element">
                            <span>
                                <c:choose>
                                    <c:when test="${user.userIcon != null}">
                                        <img alt="image" class="img-circle m-t-xs img-responsive" style="height:90px;width:90px;" src=/${user.userIcon} />
                                    </c:when>
                                    <c:otherwise>
                                        <img alt="image" class="img-circle m-t-xs img-responsive" style="height:90px;width:90px;" src="/static/img/default.png" />
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">
                                   <c:choose>
                                       <c:when test="${user.userNickname != null}">
                                           ${user.userNickname}
                                       </c:when>
                                       <c:otherwise>
                                           ${user.userName}
                                       </c:otherwise>
                                   </c:choose>

                               </strong></span>
                                <span class="text-muted text-xs block">${user.role.roleName}<b class="caret"></b></span>
                                </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a class="J_menuItem" href="/emp/selfManage">个人资料</a>
                            </li>
                            <li><a class="J_menuItem" href="/edit">修改头像</a>
                            </li>
                            <li><a class="J_menuItem" href="/user/updatePwdPage">修改密码</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="/changeaccount">安全退出</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/table/tableIndex">表格和select2</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/file/fileIndex">文件上传</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/summerNote/summerNote">summernote</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/vaildate/index">jqueryVaildate</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/error/notFound">404</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a class="J_menuItem" href="/error/serverError">500</a>--%>
                <%--</li>--%>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">基础信息管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/companyInfo">公司信息管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/carBrand">汽车品牌管理</a>
                                </li>
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/carModel">车型管理</a>
                                </li>
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/carColor">汽车颜色管理</a>
                                </li>
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/carPlate" target="_blank">车牌管理</a>
                                </li>
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/upkeepItem" target="_blank">保养项目管理</a>
                                </li>
                                <li>
                                    <a class="J_menuItem" href="/basicInfo/fixItem" target="_blank">维修项目管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                    <li>
                        <a href="#">
                            <i class="fa fa-user"></i>
                            <span class="nav-label">&nbsp;人员管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司人力资源管理部">
                                <li>
                                    <a class="J_menuItem" href="/emp/empInformation">人员基本信息管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                                <li>
                                    <a class="J_menuItem" href="/emp/empWages">工资管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                                <li>
                                    <a class="J_menuItem" href="/emp/workInfo">工单查询</a>
                                </li>
                            </shiro:hasAnyRoles>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                                <li>
                                    <a class="J_menuItem" href="/emp/carOwnerInfo">车主管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                    <li>
                        <a href="#"><i class="fa fa-users"></i> <span class="nav-label">供应商管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/supplier/supplierType">供应商类型管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="/supplier/supplierInformation">供应商管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司采购人员">
                    <li>
                        <a href="#"><i class="fa fa-cubes"></i> <span class="nav-label">配件管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                                <li><a class="J_menuItem" href="/accessories/type">配件分类管理</a>
                                </li>
                            </shiro:hasAnyRoles>
                            <li><a class="J_menuItem" href="/accessories/accessories">配件库存管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/accessories/buy">配件采购管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/accessories/sale">配件销售管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                    <li>
                        <a href="#"><i class="fa fa-phone"></i> <span class="nav-label">&nbsp;维修保养预约管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/customerBooking/phoneReservation">所有预约</a>
                            </li>

                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师">
                    <li>
                        <a href="#"><i class="fa fa-car"></i> <span class="nav-label">维修保养接待管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                                <li><a class="J_menuItem" href="/maintenanceReception/reception">接待登记管理</a></li>
                            </shiro:hasAnyRoles>
                            <li><a class="J_menuItem" href="/maintenanceReception/subsidiary">维修保养明细管理</a></li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                    <li>
                        <a href="#"><i class="fa fa-ambulance"></i> <span class="nav-label">派工领料管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司总技师">
                                <li><a class="J_menuItem" href="pickingControll/assignstaffIndex">指派员工</a></li>
                            </shiro:hasAnyRoles>
                            <li><a class="J_menuItem" href="pickingControll/materialsIndex">我的工单</a></li>
                            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司库管人员">
                                <li><a class="J_menuItem" href="pickingControll/mattermanageIndex">物料管理</a></li>
                            </shiro:hasAnyRoles>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师">
                    <li>
                        <a href="#"><i class="fa fa-wrench"></i> <span class="nav-label">维修保养进度管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="maintenanceController/maintenanceIndex">维修保养进度管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                    <li>
                        <a href="#"><i class="fa fa-calculator"></i> <span class="nav-label">结算提车</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/clearingOut/carRemindIndex">提车提醒</a>
                            </li>
                            <li><a class="J_menuItem" href="/clearingOut/chargeDocumentsIndex">收费单据管理</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-user-plus"></i> <span class="nav-label">客户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <%--<li><a class="J_menuItem" href="/custManage/maintainrecordIndex">维修保养记录管理</a>--%>
                            <%--</li>--%>
                            <li><a class="J_menuItem" href="/custManage/maintainremindIndex">维修保养提醒</a>
                            </li>
                            <li><a class="J_menuItem" href="/custManage/messagesendIndex">短信群发提醒</a>
                            </li>
                            <li><a class="J_menuItem" href="/custManage/complaintIndex">投诉管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/custManage/tracklistIndex">跟踪回访管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                    <li>
                        <a href="#"><i class="fa fa-cny"></i> <span class="nav-label">&nbsp;&nbsp;财务管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/financialView/payOutType">支出类型管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/financialView/incomeType">收入类型管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/financialView/piRecording">收支记录管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/financialView/salary">工资管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/financialView/bill">对账单管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                    <li>
                        <a href="#"><i class="fa fa-bar-chart"></i> <span class="nav-label">报表统计</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/statistics/maintain">下单统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/pay">支付统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/repertory">库存统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/inventoryUsing">库存领料统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/inventoryUsing1">库存退料统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/vindicate">维修保养统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/workord">员工工单统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/maintaintype">维修项目统计</a>
                            </li>
                            <li><a class="J_menuItem" href="/statistics/finance">财务统计</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <li>
                        <a href="#"><i class="fa fa-gear"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="/systemManage/roleManageIndex">角色管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/systemManage/moduleManageIndex">模块管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/systemManage/perManageIndex">权限管理</a>
                            </li>
                            <li><a class="J_menuItem" href="/systemManage/flowIndex">流程管理</a>
                            </li>
                        </ul>
                    </li>
                </shiro:hasAnyRoles>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                    <%--<form role="search" class="navbar-form-custom" method="post" action="http://www.zi-han.net/theme/hplus/search_results.html">--%>
                    <%--<div class="form-group">--%>
                    <%--<input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">--%>
                    <%--</div>--%>
                    <%--</form>--%>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <%--<li class="dropdown">--%>
                    <%--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">--%>
                    <%--<i class="fa fa-envelope"></i> <span class="label label-warning">16</span>--%>
                    <%--</a>--%>
                    <%--<ul class="dropdown-menu dropdown-messages">--%>
                    <%--<li class="m-t-xs">--%>
                    <%--<div class="dropdown-messages-box">--%>
                    <%--<a href="profile.html" class="pull-left">--%>
                    <%--<img alt="image" class="img-circle" src="/static/img/a7.jpg">--%>
                    <%--</a>--%>
                    <%--<div class="media-body">--%>
                    <%--<small class="pull-right">46小时前</small>--%>
                    <%--<strong>小四</strong> 这个在日本投降书上签字的军官，建国后一定是个不小的干部吧？--%>
                    <%--<br>--%>
                    <%--<small class="text-muted">3天前 2014.11.8</small>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li>--%>
                    <%--<div class="dropdown-messages-box">--%>
                    <%--<a href="profile.html" class="pull-left">--%>
                    <%--<img alt="image" class="img-circle" src="/static/img/a4.jpg">--%>
                    <%--</a>--%>
                    <%--<div class="media-body ">--%>
                    <%--<small class="pull-right text-navy">25小时前</small>--%>
                    <%--<strong>国民岳父</strong> 如何看待“男子不满自己爱犬被称为狗，刺伤路人”？——这人比犬还凶--%>
                    <%--<br>--%>
                    <%--<small class="text-muted">昨天</small>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li>--%>
                    <%--<div class="text-center link-block">--%>
                    <%--<a class="J_menuItem" href="mailbox.html">--%>
                    <%--<i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>--%>
                    <%--</a>--%>
                    <%--</div>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <%--<li class="dropdown">--%>
                    <%--<a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">--%>
                    <%--<i class="fa fa-bell"></i> <span class="label label-primary">8</span>--%>
                    <%--</a>--%>
                    <%--<ul class="dropdown-menu dropdown-alerts">--%>
                    <%--<li>--%>
                    <%--<a href="mailbox.html">--%>
                    <%--<div>--%>
                    <%--<i class="fa fa-envelope fa-fw"></i> 您有16条未读消息--%>
                    <%--<span class="pull-right text-muted small">4分钟前</span>--%>
                    <%--</div>--%>
                    <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li>--%>
                    <%--<a href="profile.html">--%>
                    <%--<div>--%>
                    <%--<i class="fa fa-qq fa-fw"></i> 3条新回复--%>
                    <%--<span class="pull-right text-muted small">12分钟钱</span>--%>
                    <%--</div>--%>
                    <%--</a>--%>
                    <%--</li>--%>
                    <%--<li class="divider"></li>--%>
                    <%--<li>--%>
                    <%--<div class="text-center link-block">--%>
                    <%--<a class="J_menuItem" href="notifications.html">--%>
                    <%--<strong>查看所有 </strong>--%>
                    <%--<i class="fa fa-angle-right"></i>--%>
                    <%--</a>--%>
                    <%--</div>--%>
                    <%--</li>--%>
                    <%--</ul>--%>
                    <%--</li>--%>
                    <%--<!--<li class="hidden-xs">--%>
                    <%--<a href="index_v1.html" class="J_menuItem" data-index="0"><i class="fa fa-cart-arrow-down"></i> 购买</a>--%>
                    <%--</li>-->--%>
                    <li class="dropdown hidden-xs">
                        <a class="right-sidebar-toggle" aria-expanded="false">
                            <i class="fa fa-tasks"></i> 主题
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="/backstage/home">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right" style="padding:0; margin:0;"><i class="fa fa-forward"></i>
            </button>
        </div>
        <div class="row J_mainContent" id="content-main" style="height:84%;">
            <iframe id="j_iframe" class="J_iframe" name="iframe0" width="100%" height="100%" src="/backstage/home" frameborder="0" data-id="/backstage/home" seamless></iframe>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

            <ul class="nav nav-tabs navs-3">

                <li class="active">
                    <a data-toggle="tab" href="#tab-1">
                        <i class="fa fa-gear"></i> 主题
                    </a>
                </li>
                <%--<li class=""><a data-toggle="tab" href="#tab-2">--%>
                <%--通知--%>
                <%--</a>--%>
                <%--</li>--%>
                <%--<li><a data-toggle="tab" href="#tab-3">--%>
                <%--项目进度--%>
                <%--</a>--%>
                <%--</li>--%>
            </ul>

            <div class="tab-content">
                <div id="tab-1" class="tab-pane active">
                    <%--<div class="sidebar-title">--%>
                    <%--<h3> <i class="fa fa-comments-o"></i> 主题设置</h3>--%>
                    <%--<small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>--%>
                    <%--</div>--%>
                    <div class="skin-setttings">
                        <%--<div class="title">主题设置</div>--%>
                        <div class="setings-item">
                            <span>收起左侧菜单</span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                    <label class="onoffswitch-label" for="collapsemenu">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                            <span>固定顶部</span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                    <label class="onoffswitch-label" for="fixednavbar">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                    <label class="onoffswitch-label" for="boxedlayout">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="title">皮肤选择</div>
                        <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                        </div>
                        <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                        </div>
                        <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                        </div>
                    </div>
                </div>
                <div id="tab-2" class="tab-pane">

                    <div class="sidebar-title">
                        <h3> <i class="fa fa-comments-o"></i> 最新通知</h3>
                        <small><i class="fa fa-tim"></i> 您当前有10条未读信息</small>
                    </div>

                    <div>

                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a1.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">

                                    据天津日报报道：瑞海公司董事长于学伟，副董事长董社轩等10人在13日上午已被控制。
                                    <br>
                                    <small class="text-muted">今天 4:21</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a2.jpg">
                                </div>
                                <div class="media-body">
                                    HCY48之音乐大魔王会员专属皮肤已上线，快来一键换装拥有他，宣告你对华晨宇的爱吧！
                                    <br>
                                    <small class="text-muted">昨天 2:45</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a3.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">
                                    写的好！与您分享
                                    <br>
                                    <small class="text-muted">昨天 1:10</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a4.jpg">
                                </div>

                                <div class="media-body">
                                    国外极限小子的炼成！这还是亲生的吗！！
                                    <br>
                                    <small class="text-muted">昨天 8:37</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a8.jpg">
                                </div>
                                <div class="media-body">

                                    一只流浪狗被收留后，为了减轻主人的负担，坚持自己觅食，甚至......有些东西，可能她比我们更懂。
                                    <br>
                                    <small class="text-muted">今天 4:21</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a7.jpg">
                                </div>
                                <div class="media-body">
                                    这哥们的新视频又来了，创意杠杠滴，帅炸了！
                                    <br>
                                    <small class="text-muted">昨天 2:45</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a3.jpg">

                                    <div class="m-t-xs">
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                        <i class="fa fa-star text-warning"></i>
                                    </div>
                                </div>
                                <div class="media-body">
                                    最近在补追此剧，特别喜欢这段表白。
                                    <br>
                                    <small class="text-muted">昨天 1:10</small>
                                </div>
                            </a>
                        </div>
                        <div class="sidebar-message">
                            <a href="#">
                                <div class="pull-left text-center">
                                    <img alt="image" class="img-circle message-avatar" src="/static/img/a4.jpg">
                                </div>
                                <div class="media-body">
                                    我发起了一个投票 【你认为下午大盘会翻红吗？】
                                    <br>
                                    <small class="text-muted">星期一 8:37</small>
                                </div>
                            </a>
                        </div>
                    </div>

                </div>
                <div id="tab-3" class="tab-pane">

                    <div class="sidebar-title">
                        <h3> <i class="fa fa-cube"></i> 最新任务</h3>
                        <small><i class="fa fa-tim"></i> 您当前有14个任务，10个已完成</small>
                    </div>

                    <ul class="sidebar-list">
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>市场调研</h4> 按要求接收教材；

                                <div class="small">已完成： 22%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                </div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>可行性报告研究报上级批准 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 48%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 48%;" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>立项阶段</h4> 东风商用车公司 采购综合综合查询分析系统项目进度阶段性报告武汉斯迪克科技有限公司

                                <div class="small">已完成： 14%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <span class="label label-primary pull-right">NEW</span>
                                <h4>设计阶段</h4>
                                <!--<div class="small pull-right m-t-xs">9小时以后</div>-->
                                项目进度报告(Project Progress Report)
                                <div class="small">已完成： 22%</div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>拆迁阶段</h4> 科研项目研究进展报告 项目编号: 项目名称: 项目负责人:

                                <div class="small">已完成： 22%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                </div>
                                <div class="small text-muted m-t-xs">项目截止： 4:00 - 2015.10.01</div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>建设阶段 </h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 48%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 48%;" class="progress-bar"></div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="small pull-right m-t-xs">9小时以后</div>
                                <h4>获证开盘</h4> 编写目的编写本项目进度报告的目的在于更好的控制软件开发的时间,对团队成员的 开发进度作出一个合理的比对

                                <div class="small">已完成： 14%</div>
                                <div class="progress progress-mini">
                                    <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                </div>
                            </a>
                        </li>

                    </ul>

                </div>
            </div>

        </div>
    </div>
    <!--右侧边栏结束-->
    <!--mini聊天窗口开始-->
    <div class="small-chat-box fadeInRight animated">

        <div class="heading" draggable="true">
            <small class="chat-date pull-right">
                2015.9.1
            </small> 与 Beau-zihan 聊天中
        </div>

        <div class="content">

            <div class="left">
                <div class="author-name">
                    Beau-zihan <small class="chat-date">
                    10:02
                </small>
                </div>
                <div class="chat-message active">
                    你好
                </div>

            </div>
            <div class="right">
                <div class="author-name">
                    游客
                    <small class="chat-date">
                        11:24
                    </small>
                </div>
                <div class="chat-message">
                    你好，请问H+有帮助文档吗？
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Beau-zihan
                    <small class="chat-date">
                        08:45
                    </small>
                </div>
                <div class="chat-message active">
                    有，购买的H+源码包中有帮助文档，位于docs文件夹下
                </div>
            </div>
            <div class="right">
                <div class="author-name">
                    游客
                    <small class="chat-date">
                        11:24
                    </small>
                </div>
                <div class="chat-message">
                    那除了帮助文档还提供什么样的服务？
                </div>
            </div>
            <div class="left">
                <div class="author-name">
                    Beau-zihan
                    <small class="chat-date">
                        08:45
                    </small>
                </div>
                <div class="chat-message active">
                    1.所有源码(未压缩、带注释版本)；
                    <br> 2.说明文档；
                    <br> 3.终身免费升级服务；
                    <br> 4.必要的技术支持；
                    <br> 5.付费二次开发服务；
                    <br> 6.授权许可；
                    <br> ……
                    <br>
                </div>
            </div>


        </div>
        <div class="form-chat">
            <div class="input-group input-group-sm">
                <input type="text" class="form-control"> <span class="input-group-btn"> <button
                    class="btn btn-primary" type="button">发送
                </button> </span>
            </div>
        </div>

    </div>
</div>
<script src="/static/js/jquery.min.js?v=2.1.4"></script>
<script src="/static/js/bootstrap.min.js?v=3.3.6"></script>
<script src="/static/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/static/js/plugins/layer/layer.min.js"></script>
<script src="/static/js/hplus.min.js?v=4.1.0"></script>
<script src="/static/js/contabs.min.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/plugins/pace/pace.min.js"></script>
<script>
    $(function() {
        var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
        $.post("/user/isLogin/"+roles, function (data) {
            if(data.result == 'success'){
                $("#wrapper").css("display","block");
                function bodyScroll(event){
                    event.preventDefault();
                }
                document.body.addEventListener('touchmove',bodyScroll(event),false);
                document.body.removeEventListener('touchmove',bodyScroll(event),false);
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
                        type:"error"},
                    function(isConfirm){
                        if(isConfirm){
                            top.location = "/user/loginPage";
                        }else{
                            top.location = "/user/loginPage";
                        }
                    })
            }
        });
    });

    function doNothing(){
        window.event.returnValue=false;
        return false;
    }
</script>
</body>
</html>
