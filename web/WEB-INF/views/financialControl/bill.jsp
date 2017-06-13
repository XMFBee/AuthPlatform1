<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>收费单据管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true" data-field="status"></th>
                <th data-width="90" data-field="maintainRecord.checkin.userName">车主姓名</th>
                <th data-width="130" data-field="maintainRecord.checkin.userPhone">车主手机</th>
                <th data-width="90" data-field="maintainRecord.checkin.brand.brandName">汽车品牌</th>
                <th data-width="90" data-field="maintainRecord.checkin.model.modelName">汽车车型</th>
                <th data-width="90" data-field="maintainRecord.checkin.color.colorName">汽车颜色</th>
                <th data-width="90" data-field="maintainRecord.checkin.plate.plateName">汽车车牌</th>
                <th data-width="90" data-field="maintainRecord.checkin.carPlate">车牌号码</th>
                <th data-width="180" data-field="maintainRecord.pickupTime" data-formatter="formatterDate">维修保养记录提车时间
                </th>
                <th data-width="150" data-field="maintainRecord.recordDes">维修保养记录描述</th>
                <th data-width="90" data-field="paymentMethod">付款方式</th>
                <th data-width="90" data-field="chargeBillMoney">总金额</th>
                <th data-width="90" data-field="actualPayment">实际付款</th>
                <th data-width="180" data-field="chargeTime" data-formatter="formatterDate">收费时间</th>
                <th data-width="180" data-field="chargeCreatedTime" data-formatter="formatterDate">收费单据创建时间</th>
                <th data-width="130" data-field="chargeBillDes">收费单据描述</th>
                <th data-width="130" data-field="chargeBillStatus" data-formatter="showStatusFormatter">收费单据状态</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询可用收费单据
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询禁用收费单据
                </button>
            </shiro:hasAnyRoles>
            <button id="btn_add" type="button" class="btn btn-success" onclick="showAdd();">
                <span class="glyphicon glyphicon-earch " aria-hidden="true"></span>查询收费单据打印页面
            </button>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员">
                <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">车主/电话/汽车公司/车牌号<span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">车主/电话/汽车公司/车牌号</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">车主</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">电话</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">汽车公司</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">车牌号</a></li>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javaScript:;" onclick="blurredQuery()"><span
                            class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div>
                <!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div aria-hidden="true" data-backdrop="static" keyboard:false
     class="modal fade" id="add" aria-hidden="true">
    <div class="modal-dialog" style="width: 1500px; height: 1000px">
        <div class="modal-content" >
            <div class="container" >
                <div class="modal-body">
                    <span class="glyphicon glyphicon-remove closeModal"  onclick="closeModals('add', 'addForm')"></span>
                    <form class="form-horizontal" role="form" id="addForm">
                        <div class="col-sm-12" style="text-align: center">
                            <h3><strong>收&nbsp;费&nbsp;单&nbsp;据</strong></h3>
                        </div>

                        <div class="col-sm-12" style="border: 1px solid black;height:350px;font-size: 20px; padding-top: 20px;">
                            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">汽车公司 : </div>
                                <div id="companyName" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.maintainRecord.checkin.company.companyName != null}">
                                            chargeBill.companyName
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-sm-1" style="width: 130px;margin-left:120px;display:inline;float:left;">收款方式 : </div>
                                <div id="paymentMethod" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.chargeBillDes != null}">
                                            ${chargeBill.chargeBillDes}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">总金额：</div>
                                <div id="totalPrice" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.chargeBillMoney != null}">
                                            ${chargeBill.chargeBillMoney}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-sm-4" style="width: 130px;margin-left:120px;display:inline;float:left;">实际付款：</div>
                                <div id="newestPrice" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.actualPayment != null}">
                                            ${chargeBill.actualPayment}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">收款事由 : </div>
                                <div id="chargeBillDes" class="col-sm-10" style="border-bottom: 1px solid black;display:inline;float:left;width:850px;">
                                    <c:choose>
                                        <c:when test="${chargeBill.chargeBillDes!= null}">
                                            ${chargeBill.chargeBillDes}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="float:right;">
                                <div class="col-sm-7"></div>
                                <div class="col-sm-2" style="display:inline;float:left;">公司电话 : </div>
                                <div id="companyTel" class="col-sm-1" style="border-bottom: 1px solid black;width:190px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.maintainRecord.checkin.company.companyTel != null}">
                                            ${chargeBill.maintainRecord.checkin.company.companyTel}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="float:right;">
                                <div class="col-sm-7"></div>
                                <div class="col-sm-2" style="display:inline;float:left;">公司地址 : </div>
                                <div id="companyAddress" class="col-sm-1" style="border-bottom: 1px solid black;width:190px;display:inline;float:left;">
                                    <c:choose>
                                        <c:when test="${chargeBill.maintainRecord.checkin.company.companyAddress != null}">
                                            ${chargeBill.maintainRecord.checkin.company.companyAddress}
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="float:right;">
                                <div class="col-sm-7"></div>
                                <div class="col-sm-2" style="display:inline;float:left;">车主签字 : </div>
                                <div class="col-sm-1" style="border-bottom: 1px solid black;width:100px;display:inline;float:left;">
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </div>
                            </div>
                            <p class="clear"></p>
                            <div class="col-sm-12" style="float:right;">
                                <div class="col-sm-7"></div>
                                <div class="col-sm-2" style="display:inline;float:left;">单据日期 : </div>
                                <div id="chargeCreatedTime" class="col-sm-1" style="width:230px;display:inline;float:left;">
                                    <fmt:formatDate value="${chargeBill.chargeCreatedTime}" pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/clearingOut/chargeDocuments.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/financialControlJS/bill.js"></script>
</body>
</html>
