<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-switch/bootstrap-switch.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <title>接待登记管理</title>
</head>
<body>

<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true"></th>
                <th data-width="100" data-field="userName">
                    车主姓名
                </th>
                <th data-width="115" data-field="userPhone">
                    车主电话
                </th>
                <th data-width="100" data-field="brand.brandName">
                    汽车品牌
                </th>
                <th data-width="100" data-field="model.modelName">
                    汽车车型
                </th>
                <th data-width="100" data-field="color.colorName">
                    汽车颜色
                </th>
                <th data-width="100" data-field="plate.plateName">
                    汽车车牌
                </th>
                <th data-width="100" data-field="carPlate">
                    车牌号码
                </th>
                <th data-width="180" data-field="arriveTime" data-formatter="formatterDate">
                    到店时间
                </th>
                <th data-width="160" data-field="carMileage">
                    汽车行驶里程(km)
                </th>
                <th data-width="160" data-field="nowOil">
                    汽车当前油量(L)
                </th>
                <th data-width="90" data-field="ifClearCar" data-formatter="showYesOrNoFormatter">
                    是否洗车
                </th>
                <th data-width="150" data-field="carThings">
                    车上物品描述
                </th>
                <th data-width="150" data-field="intactDegrees">
                    汽车完好度描述
                </th>
                <th data-width="150" data-field="userRequests">
                    用户要求描述
                </th>
                <th data-width="100" data-field="maintainOrFix">
                    保养&nbsp;|&nbsp;维修
                </th>
                <th data-width="180" data-field="checkinCreatedTime" data-formatter="formatterDate">
                    登记时间
                </th>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <th data-width="110" data-field="company.companyName">
                    汽车公司
                </th>
            </shiro:hasAnyRoles>
                <th data-width="90" data-field="checkinStatus" data-formatter="showStatusFormatter">
                    记录状态
                </th>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <th data-width="100" data-field="checkinStatus" data-formatter="statusFormatter">
                    操作
                </th>
            </shiro:hasAnyRoles>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                          <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                              <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用登记记录
                          </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用登记记录
                        </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员">
                        <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
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
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
                <form role="form" class="form-horizontal" id="addForm">
                    <input id="addUserId" type="hidden" name="userId"/>
                    <input id="addAppointmentId" type="hidden" name="appointmentId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加登记记录信息</h4>
                    </div>
                    <div id="customerDiv" class="form-group">
                        <label class="col-sm-3 control-label">是否注册车主：</label>
                        <div class="col-sm-7">
                            <input id="customer" type="checkbox" />
                        </div>
                    </div>
                    <div id="appDiv" class="form-group">
                        <label class="col-sm-3 control-label">是否预约：</label>
                        <div class="col-sm-7">
                            <input id="app" type="checkbox" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养&nbsp;|&nbsp;维修：</label>
                        <div class="col-sm-7">
                            <select id="addMaintainOrFix" class="js-example-tags form-control" name="maintainOrFix">
                                <option value="保养">保养</option>
                                <option value="维修">维修</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车主姓名：</label>
                        <div class="col-sm-7">
                            <input type="text" id="addUserName" placeholder="请输入车主姓名" name="userName"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车主电话：</label>
                        <div class="col-sm-7">
                            <input type="number" id="addUserPhone" placeholder="请输入车主电话" name="userPhone"
                                   class="form-control" style="width:100%"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车品牌：</label>
                        <div class="col-sm-7" id="carBrandSpan" readonly="true">
                            <select id="addCarBrand" class="js-example-tags carBrand" name="brandId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div id="addModelDiv" style="display: none" class="form-group">
                        <label class="col-sm-3 control-label">汽车车型：</label>
                        <div class="col-sm-7">
                            <select id="addCarModel" class="js-example-tags carModel" name="modelId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车颜色：</label>
                        <div class="col-sm-7">
                            <select id="addCarColor" class="js-example-tags carColor" name="colorId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车车牌：</label>
                        <div class="col-sm-7">
                            <select id="addCarPlate" class="js-example-tags carPlate" name="plateId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车牌号码：</label>
                        <div class="col-sm-7">
                            <input id="addPlate" type="text" name="carPlate" placeholder="请输入车牌号码" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">到店时间：</label>
                        <div class="col-sm-7">
                            <!-- 当设置不可编辑后, 会修改颜色, 在min.css里搜索.form-control{background-color:#eee;opacity:1} -->
                            <input id="addDatetimepicker" placeholder="请选择到店时间" onclick="getDate('addDatetimepicker')"
                                   readonly="true" type="text" name="arriveTime"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否洗车：</label>
                        <div class="col-sm-7">
                            <select class="js-example-tags form-control" name="ifClearCar">
                                <option value="N">否</option>
                                <option value="Y">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">行驶里程(km)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="carMileage" placeholder="请输入汽车行驶里程" min="1" class="form-control"
                                   style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">当前油量(L)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="nowOil" placeholder="请输入汽车当前油量" min="1" class="form-control"
                                   style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车上物品描述：</label>
                        <div class="col-sm-7">
                                <textarea class="form-control" placeholder="请输入车上物品描述" name="carThings"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车完好度描述：</label>
                        <div class="col-sm-7">
                                <textarea class="form-control" placeholder="请输入汽车完好度描述" name="intactDegrees"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户要求描述：</label>
                        <div class="col-sm-7">
                                <textarea class="form-control" placeholder="请输入用户要求描述" name="userRequests"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                onclick="closeModals('addWindow', 'addForm')">关闭
                        </button>
                        <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-success">添加
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"  onclick="closeModals('editWindow', 'editForm')"></span>
                <form role="form" class="form-horizontal" id="editForm">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改登记记录信息</h4>
                    </div>
                    <input type="hidden" define="checkin.checkinId" name="checkinId"/>
                    <input type="hidden" define="checkin.userId" name="userId"/>
                    <input type="hidden" define="checkin.appointmentId" name="appointmentId"/>
                    <input type="hidden" define="checkin.checkinStatus" name="checkinStatus"/>
                    <input type="hidden" define="checkin.companyId" name="companyId"/>
                    <input id="checkinCreatedTime" type="hidden" name="checkinCreatedTime"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">保养&nbsp;|&nbsp;维修：</label>
                        <div class="col-sm-7">
                            <select id="editMaintainOrFix"
                                    class="js-example-tags form-control" name="maintainOrFix">
                                <option value="保养">保养</option>
                                <option value="维修">维修</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">是否洗车：</label>
                        <div class="col-sm-7">
                            <select id="editIfClearCar" class="js-example-tags form-control" name="ifClearCar">
                                <option value="N">否</option>
                                <option value="Y">是</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车主姓名：</label>
                        <div class="col-sm-7">
                            <input type="text" id="editUserName" name="userName" placeholder="车主姓名" define="checkin.userName"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车主电话：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editUserPhone" name="userPhone" placeholder="车主电话" define="checkin.userPhone"
                                   class="form-control" style="width:100%"/>
                        </div>
                    </div>

                    <div id="editModelDiv" class="form-group">
                        <label class="col-sm-3 control-label">汽车品牌：</label>
                        <div class="col-sm-7">
                            <select id="editCarBrand" class="js-example-tags carBrand" define="checkin.brandId"
                                    name="brandId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车车型：</label>
                        <div class="col-sm-7">
                            <select id="editCarModel" class="js-example-tags carModel" define="checkin.modelId"
                                    name="modelId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车颜色：</label>
                        <div class="col-sm-7">
                            <select id="editCarColor" class="js-example-tags carColor" define="checkin.colorId"
                                    name="colorId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车车牌：</label>
                        <div class="col-sm-7">
                            <select id="editCarPlate" class="js-example-tags carPlate" define="checkin.plateId"
                                    name="plateId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车牌号码：</label>
                        <div class="col-sm-7">
                            <input id="editPlate" type="text" name="carPlate" placeholder="车牌号码"
                                   define="checkin.carPlate"
                                   class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">到店时间：</label>
                        <div class="col-sm-7">
                            <!-- 当设置不可编辑后, 会修改颜色, 在min.css里搜索.form-control{background-color:#eee;opacity:1} -->
                            <input id="editDatetimepicker" placeholder="请选择到店时间" readonly="true" type="text"
                                   name="arriveTime"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">行驶里程(km)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="carMileage" placeholder="请输入汽车行驶里程" min="1"
                                   define="checkin.carMileage" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">当前油量(L)：</label>
                        <div class="col-sm-7">
                            <input type="number" name="nowOil" placeholder="请输入汽车当前油量" min="1" define="checkin.nowOil"
                                   class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">车上物品描述：</label>
                        <div class="col-sm-7">
                                <textarea type="textarea" class="form-control" placeholder="请输入车上物品描述"
                                          define="checkin.carThings" name="carThings"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">汽车完好度描述：</label>
                        <div class="col-sm-7">
                                <textarea type="textarea" class="form-control" placeholder="请输入汽车完好度描述"
                                          define="checkin.intactDegrees" name="intactDegrees"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">用户要求描述：</label>
                        <div class="col-sm-7">
                                <textarea type="textarea" class="form-control" placeholder="请输入用户要求描述"
                                          define="checkin.userRequests" name="userRequests"
                                          rows="3" maxlength="500"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default"
                                onclick="closeModals('editWindow', 'editForm')">关闭
                        </button>
                        <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-success">保存
                        </button>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="appWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 90%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeAppWin()"></span>
                <h3>选择预约记录</h3>
                <table class="table table-hover" id="appTable" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th data-radio="true"></th>
                        <th data-width="90" data-field="userName">
                            车主姓名
                        </th>
                        <th data-width="130" data-field="userPhone">
                            车主电话
                        </th>
                        <th data-width="90" data-field="brand.brandName">
                            汽车品牌
                        </th>
                        <th data-width="90" data-field="color.colorName">
                            汽车颜色
                        </th>
                        <th data-width="90" data-field="model.modelName">
                            汽车车型
                        </th>
                        <th data-width="90" data-field="plate.plateName">
                            汽车车牌
                        </th>
                        <th data-width="90" data-field="carPlate">
                            车牌号码
                        </th>
                        <th data-width="190" data-field="arriveTime" data-formatter="formatterDate">
                            预计到店时间
                        </th>
                        <th data-width="100" data-field="maintainOrFix">
                            维修&nbsp;|&nbsp;保养
                        </th>
                        <th data-width="190" data-field="appCreatedTime" data-formatter="formatterDate">
                            预约创建时间
                        </th>
                        <th data-width="100" data-field="currentStatus">
                            当前状态
                        </th>
                        <th data-width="100" data-field="appoitmentStatus" data-formatter="showStatusFormatter">
                            记录状态
                        </th>
                    </thead>
                </table>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" onclick="closeAppWin()">关闭
                    </button>
                    <input type="button" class="btn btn-success" onclick="checkApp()" value="确定">
                    </input>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</div>

<!--本店用户弹窗-->
<div id="customerWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog"style="width: 90%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeCustomerWin()"></span>
                <h3>选择车主</h3>
                <table class="table table-hover" id="customerTable"style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th data-radio="true"></th>
                        <th data-width="140" data-field="userIcon"data-formatter="formatterImg">
                            用户头像
                        </th>
                        <th data-width="90" data-field="userName">
                            用户姓名
                        </th>
                        <th data-width="140" data-field="userEmail">
                            用户邮箱
                        </th>
                        <th data-width="130" data-field="userPhone">
                            用户电话
                        </th>
                        <th data-width="180" data-field="userIdentity">
                            用户身份证
                        </th>
                        <th data-width="90" data-field="userNickname">
                            用户昵称
                        </th>
                        <th data-width="90" data-field="userGender" data-formatter="formatterGender">
                            用户性别
                        </th>
                        <th data-width="170" data-field="userBirthday"data-formatter="formatterDate">
                            用户生日
                        </th>
                        <th data-width="170" data-field="userAddress">
                            用户地址
                        </th>
                        <th data-width="130" data-field="userDes">
                            用户描述
                        </th>
                        <th data-width="90" data-field="userStatus" data-formatter="showStatusFormatter">
                            用户状态
                        </th>
                    </thead>
                    <tbody>
                    </tbody>

                </table>
                <div style="height: 100px;"></div>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" onclick="closeCustomerWin()">关闭
                    </button>
                    <input type="button" class="btn btn-success" onclick="checkCustomer()" value="确定">
                    </input>
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
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-switch/bootstrap-switch.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/maintenanceReception/reception.js"></script>
</body>
</html>
