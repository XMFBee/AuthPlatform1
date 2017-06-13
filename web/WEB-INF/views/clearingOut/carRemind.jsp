<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>提车提醒管理</title>
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
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-checkBox="true" data-field="status"></th>
                <th data-width="90" data-field="checkin.userName">
                    车主姓名
                </th>
                <th data-width="120" data-field="checkin.userPhone">
                    车主电话
                </th>
                <th data-width="90" data-field="checkin.brand.brandName">
                    汽车品牌
                </th>
                <th data-width="90" data-field="checkin.color.colorName">
                    汽车颜色
                </th>
                <th data-width="90" data-field="checkin.model.modelName">
                    汽车车型
                </th>
                <th data-width="90" data-field="checkin.plate.plateName">
                    汽车车牌
                </th>
                <th data-width="90" data-field="checkin.carPlate">
                    车牌号码
                </th>
                <th data-width="90" data-field="checkin.ifClearCar" data-formatter="showYesOrNoFormatter">
                    是否洗车
                </th>
                <th data-width="150" data-field="checkin.carThings">
                    车上物品描述
                </th>
                <th data-width="150" data-field="checkin.intactDegrees">
                    汽车完好度描述
                </th>
                <th data-width="150" data-field="checkin.userRequests">
                    用户要求描述
                </th>
                <th data-width="100" data-field="checkin.maintainOrFix">
                    保养&nbsp;|&nbsp;维修
                </th>
                <th data-width="180" data-field="startTime" data-formatter="formatterDate">维修保养开始时间</th>
                <th data-width="190" data-field="endTime" data-formatter="formatterDate">维修保养预估结束时间</th>
                <th data-width="190" data-field="actualEndTime" data-formatter="formatterDate">维修保养实际结束时间</th>
                <th data-width="190" data-field="recordCreatedTime" data-formatter="formatterDate">维修保养记录创建时间</th>
                <th data-width="190" data-field="pickupTime" data-formatter="formatterDate">维修保养车主提车时间</th>
                <th data-width="160" data-field="recordDes">维修保养记录描述</th>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <th data-width="110" data-field="checkin.company.companyName">
                        汽车公司
                    </th>
                </shiro:hasAnyRoles>
                <th data-width="100" data-hide="all" data-field="currentStatus">
                    当前状态
                </th>
                <th data-width="90" data-field="recordStatus" data-formatter="showStatusFormatter">
                    记录状态
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_available" type="button" class="btn btn-danger" onclick="showNoRemind();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>未提醒记录
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_disable" type="button" class="btn btn-success" onclick="showYesRemind();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>已提醒记录
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_bell" type="button" class="btn btn-default" onclick="showBell();">
                            <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>提醒
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_bellAll" type="button" class="btn btn-default" onclick="showBellAll();">
                            <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>全部提醒
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <button id="btn_bellAll" type="button" class="btn btn-default" onclick="showClearOut();">
                    <span class="glyphicon glyphicon-yen" aria-hidden="true"></span>结算出厂
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

<div id="addWindow" class="modal fade" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
                <form role="form" class="form-horizontal" id="addForm">
                    <input type="hidden" id="maintainRecordId" name="maintainRecordId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h3>生成维修保养收费单据</h3>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">收费总金额(元)：</label>
                        <div class="col-sm-6">
                            <input id="money" class="form-control" readonly="true" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">折扣后总金额(元)：</label>
                        <div class="col-sm-6">
                            <input id="disCountMoney" name="chargeBillMoney" class="form-control" readonly="true" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">付款方式：</label>
                        <div class="col-sm-6">
                            <select id="addPaymentMethod" class="js-example-tags form-control" name="paymentMethod">
                                <option value="现金">现金</option>
                                <option value="支付宝">支付宝</option>
                                <option value="微信">微信</option>
                                <option value="财付通">财付通</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">实际付款金额(元)：</label>
                        <div class="col-sm-6">
                            <input type="number" name="actualPayment" min="1"
                                   placeholder="请输入实际付款金额" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">收费单据描述：</label>
                        <div class="col-sm-6">
                            <textarea type="textarea" name="chargeBillDes" class="form-control" placeholder="请输入收费单据描述" maxlength="100"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                onclick="closeModals('addWindow','addForm')">关闭
                        </button>
                        <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-success">生成
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
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
<script src="/static/js/backstage/clearingOut/carRemind.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/contextmenu.js"></script>
</body>
</html>
