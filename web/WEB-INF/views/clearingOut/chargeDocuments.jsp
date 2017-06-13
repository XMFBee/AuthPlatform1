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
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;"  >
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
                <th data-width="180" data-field="maintainRecord.pickupTime" data-formatter="formatterDate">维修保养记录提车时间</th>
                <th data-width="150" data-field="maintainRecord.recordDes">维修保养记录描述</th>
                <th data-width="90" data-field="paymentMethod">付款方式</th>
                <th data-width="110" data-field="chargeBillMoney">总金额(元)</th>
                <th data-width="120" data-field="actualPayment">实际付款(元)</th>
                <th data-width="180" data-field="chargeTime" data-formatter="formatterDate">收费时间</th>
                <th data-width="180" data-field="chargeCreatedTime" data-formatter="formatterDate">收费单据创建时间</th>
                <th data-width="130" data-field="chargeBillDes">收费单据描述</th>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <th data-width="110" data-field="maintainRecord.checkin.company.companyName">
                        汽车公司
                    </th>
                </shiro:hasAnyRoles>
                <th data-width="100" data-hide="all" data-field="maintainRecord.currentStatus">
                    记录状态
                </th>
                <th data-width="130" data-field="chargeBillStatus" data-formatter="showStatusFormatter">收费单据状态</th>
                <th data-width="130" data-field="cdStatus" data-formatter="currentStatusFormatter">单据当前状态</th>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <th data-width="90" data-field="chargeBillStatus" data-formatter="statusFormatter">操作</th>
            </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用收费单据
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用收费单据
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司财务人员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showMoney();">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>确认收费
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_Export" type="button" class="btn btn-default"><a href="/charge/exportExcel">
                            <span class="glyphicon glyphicon-export" aria-hidden="true"></span>导出</a>
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_print" type="button" class="btn btn-default" onclick="showPrint();">
                            <span class="glyphicon glyphicon-print" aria-hidden="true"></span>打印
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                            <div class="input-group-btn">
                                <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                        data-toggle="dropdown">车主/电话<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles>/车牌号<span class="caret"></span></button>
                                <ul class="dropdown-menu pull-right">
                                    <li><a onclick="onclikLi(this)">车主/电话<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles>/车牌号</a></li>
                                    <li class="divider"></li>
                                    <li><a onclick="onclikLi(this)">车主</a></li>
                                    <li class="divider"></li>
                                    <li><a onclick="onclikLi(this)">电话</a></li>
                                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                                        <li class="divider"></li>
                                        <li><a onclick="onclikLi(this)">汽车公司</a></li>
                                    </shiro:hasAnyRoles>
                                    <li class="divider"></li>
                                    <li><a onclick="onclikLi(this)">车牌号</a></li>
                                </ul>
                            </div><!-- /btn-group -->
                            <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                            <a href="javaScript:;" onclick="blurredQuery()"><span
                                    class="glyphicon glyphicon-search search-style"></span></a>
                            </input>
                        </div><!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
                <form id="editForm" role="form" class="form-horizontal">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改收费单据</h4>
                    </div>
                    <input type="hidden" define="chargeBill.chargeBillId" name="chargeBillId"/>
                    <input type="hidden" define="chargeBill.maintainRecordId" name="maintainRecordId"/>
                    <input type="hidden" define="chargeBill.chargeBillStatus" name="chargeBillStatus"/>
                    <input type="hidden" define="chargeBill.cdStatus" name="cdStatus"/>
                    <input id="chargeCreatedTime" type="hidden" name="chargeCreatedTime"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label" >付款方式：</label>
                        <div class="col-sm-7">
                            <select id="editPaymentMethod" class="js-example-tags form-control" name="paymentMethod">
                                <option value="现金">现金</option>
                                <option value="支付宝">支付宝</option>
                                <option value="微信">微信</option>
                                <option value="财付通">财付通</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">总金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="chargeBillMoney" min="0" define="chargeBill.chargeBillMoney" placeholder="请输入总金额" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">实际付款(元)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="actualPayment" min="0" define="chargeBill.actualPayment" placeholder="请输入实际付款" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收费单据描述：</label>
                        <div class="col-sm-7">
                            <textarea type="textarea" name="chargeBillDes" class="form-control" define="chargeBill.chargeBillDes" placeholder="请输入收费单据描述" maxlength="100"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                onclick="closeModals('editWindow','editForm')">关闭
                        </button>
                        <button onclick="editSubmit()" id="editButton" class="btn btn-success btn-sm">保存</button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="addWin" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWin', 'addForm')"></span>
                <form class="form-horizontal" id="addForm" method="post">
                    <input type="reset" name="reset" style="display: none;"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>收入添加</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收入类型：</label>
                        <div class="col-sm-7">
                            <input type="hidden" id="inTypeId" name="inTypeId">
                            <button type="button" class="btn btn-default" onclick="inOpenCheckInType();">
                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看收入类型
                            </button>
                            <input type="text" id="inTypeName" name="inTypeName" readonly="true" placeholder="请选择收入类型" class="form-control" style="width:56%;">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收入金额(元)：</label>
                        <div class="col-sm-7">
                            <input type="text" id="inOutMoneyId" name="inOutMoney" readonly="true" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('addWin', 'addForm')">关闭</button>
                            <button class="btn btn-sm btn-success" type="button" id="addButton"
                                    onclick="addInSubmit()">保 存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%--收入管理弹窗显示--%>
<div id="inWin" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">选择收入类型</h3>
                        <table class="table table-hover" id="inTable"
                               data-height="550">
                            <thead>
                            <tr>
                                <th data-radio="true"></th>
                                <th data-field="inTypeName">
                                    收入类型
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            </tbody>

                        </table>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" odata-dismiss="modal">关闭
                            </button>
                            <input type="button" class="btn btn-success" onclick="inCheckInType()" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/clearingOut/chargeDocuments.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
</body>
</html>
