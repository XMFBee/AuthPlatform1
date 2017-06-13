<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link href="/static/css/animate.min.css" rel="stylesheet">
    <link href="/static/css/style.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
</head>
<%@include file="contextmenu.jsp" %>
<body class="gray-bg" style="background-color: #f3f3f4;font-size: 0px;">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司库管人员">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <h5>库存提示</h5>
                </div>
                <div class="ibox-content">
                    <c:choose>
                        <c:when test="${remindInfo.size()>0}">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>提醒人</th>
                                    <th>提醒内容</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${remindInfo}" var="re">
                                    <tr>
                                        <td>${re.user.userName}</td>
                                        <td>${re.remindDes}</td>
                                        <td>
                                            <button type="button" onclick="deleteRemind('${re.remindId}')" class="btn btn-success">知道了</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise>
                            暂无数据
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>最新入驻的公司</h5>
                    </div>
                    <div class="ibox-content">
                        <c:choose>
                            <c:when test="${companyInfo.size()>0}">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>公司Logo</th>
                                        <th>公司名称</th>
                                        <th>公司负责人</th>
                                        <th>公司电话</th>
                                        <th>公司地址</th>
                                        <th>公司规模</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${companyInfo}" var="cp">
                                        <tr>
                                            <td class="client-avatar">
                                                <c:choose>
                                                    <c:when test="${cp.companyLogo != null}">
                                                        <img alt="image" src="/${cp.companyLogo}" height="30px" width="30px">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img alt="image" src="/static/img/default.png" height="30px" width="30px">
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${cp.companyName}</td>
                                            <td>${cp.companyPricipal}</td>
                                            <td>${cp.companyTel}</td>
                                            <td>${cp.companyAddress}</td>
                                            <td>${cp.companySize}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                暂无数据
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>公司人员</h5>
                    </div>
                    <div class="ibox-content">
                        <c:choose>
                            <c:when test="${userinfo.size()>0}">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>头像</th>
                                        <th>人员名称</th>
                                        <th>手机号</th>
                                        <th>邮箱</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userinfo}" var="us">
                                        <tr>
                                            <td class="client-avatar">
                                                <c:choose>
                                                    <c:when test="${us.userIcon != null}">
                                                        <img alt="image" src="/${us.userIcon}" height="30px" width="30px">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img alt="image" src="/static/img/default.png" height="30px" width="30px">
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${us.userName}</td>
                                            <td>${us.userPhone}</td>
                                            <td>${us.userEmail}</td>
                                            <br/>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                暂无数据
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>预约记录</h5>
                    </div>
                    <div class="ibox-content">
                        <c:choose>
                            <c:when test="${appinfo.size()>0}">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>车主</th>
                                        <th>车主电话</th>
                                        <th>维修/保养</th>
                                        <th>预约时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${appinfo}" var="app">
                                        <tr>
                                            <td>${app.userName}</td>
                                            <td>${app.userPhone}</td>
                                            <td>${app.maintainOrFix}</td>
                                            <td><fmt:formatDate value="${app.appCreatedTime}"
                                                                pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate></td>
                                            <br/>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>已完成的维修保养记录</h5>
                    </div>
                    <div class="ibox-content">
                        <c:choose>
                            <c:when test="${maininfo.size()>0}">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>维修保养项目名称</th>
                                        <th>项目所需费用</th>
                                        <th>维修/保养</th>
                                        <th>维修时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${maininfo}" var="main">
                                        <tr>
                                            <td>${main.maintainName}</td>
                                            <td>${main.maintainMoney}</td>
                                            <td>${main.maintainOrFix}</td>
                                            <td>${main.maintainHour}</td>
                                            <br/>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                暂无数据
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>支出记录</h5>
                        </div>
                        <div class="ibox-content">
                            <c:choose>
                                <c:when test="${outgoInfo.size()>0}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>所属用户</th>
                                            <th>支出类型</th>
                                            <th>支出金额</th>
                                            <th>创建时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${outgoInfo}" var="out">
                                            <tr>
                                                <td>${out.user.userName}</td>
                                                <td>${out.outgoingType.outTypeName}</td>
                                                <td>${out.inOutMoney}</td>
                                                <td><fmt:formatDate value="${out.inOutCreatedTime}"
                                                                    pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    暂无数据
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>收入记录</h5>
                        </div>
                        <div class="ibox-content">
                            <c:choose>
                                <c:when test="${incomInfo.size()>0}">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th>所属用户</th>
                                            <th>收入类型</th>
                                            <th>收入金额</th>
                                            <th>创建时间</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${incomInfo}" var="in">
                                            <tr>
                                                <td>${in.user.userName}</td>
                                                <td>${in.incomingType.inTypeName}</td>
                                                <td>${in.inOutMoney}</td>
                                                <td><fmt:formatDate value="${in.inOutCreatedTime}"
                                                                    pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate></td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    暂无数据
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </shiro:hasAnyRoles>
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/content.min.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script>
    function deleteRemind(remindId) {
        $.post("/remind/deleteRemind?remindId="+remindId,function(data){
            if(data.result=="success"){
                swal(
                     {title:"",
                         text:"你确定吗",
                         type:"warning",
                         showCancelButton:true,
                         confirmButtonColor:"#DD6B55",
                         confirmButtonText:"我确定",
                         cancelButtonText:"额，好像没做完！",
                         closeOnConfirm:false,
                         closeOnCancel:false
                     },function(isConfirm){
                         if (isConfirm) {
                             swal({
                                 title: "提示",
                                 text: data.message,
                                 type: "success",
                                 confirmButtonText: "确认",
                             }, function () {
                                 javascript:location.reload();
                             })
                         }
                         else{
                             swal({title:"提示",
                                 text:"已取消",
                                 confirmButtonText:"确认",
                                 type:"error"})
                         }
                     }
                 )
            }
        });
    }
</script>
</div>
</body>
</html>