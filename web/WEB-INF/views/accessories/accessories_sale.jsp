<%--
  Created by IntelliJ IDEA.
  User: XiaoQiao
  Date: 2017/4/11
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>配件销售管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>
<div class="container ">
    <div class="panel-body" style="padding-bottom:0px;">
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员">
            <table id="table">
                <thead>
                <tr>
                    <th data-radio="true" data-field="status"></th>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <th data-field="company.companyName">汽车公司</th>
                    </shiro:hasAnyRoles>
                    <th data-field="accessories.accName">配件名称</th>
                    <th data-field="accSaledTime" data-formatter="formatterDate">配件销售时间</th>
                    <th data-field="accSaleCount">配件销售数量</th>
                    <th data-field="accSalePrice">配件销售单价</th>
                    <th data-field="accSaleTotal">配件销售总价</th>
                    <th data-field="accSaleDiscount">配件销售折扣</th>
                    <th data-field="accSaleMoney">配件销售最终价</th>
                    <th data-field="accSaleCreatedTime" data-formatter="formatterDateTime">销售记录创建时间</th>
                    <th data-field="accSaleStatus" data-formatter="showStatusFormatter">
                        记录状态
                    </th>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员">
                        <th data-width="100" data-field="accSaleStatus" data-formatter="statusFormatter">
                            操作
                        </th>
                    </shiro:hasAnyRoles>
                </tr>
                </thead>
            </table>
        </shiro:hasAnyRoles>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用配件销售
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用配件销售
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员">
                <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">配件名称<shiro:hasAnyRoles
                                name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles><span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">配件名称<shiro:hasAnyRoles
                                    name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles></a></li>
                            <li class="divider"></li>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                                <li><a onclick="onclikLi(this)">汽车公司</a></li>
                                <li class="divider"></li>
                            </shiro:hasAnyRoles>
                            <li><a onclick="onclikLi(this)">配件名称</a></li>
                            <li class="divider"></li>
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
<!-- 增加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" style="overflow:auto; " data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-body" onkeydown="keydown('addButton')">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow','addForm')"
                      data-dismiss="modal"></span>
                <form class="form-horizontal" role="form" id="addForm" method="post">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加配件销售信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">收入类型</label>
                        <div class="col-sm-7">
                            <select id="addIncom" class="js-example-tags incoming" name="inTypeId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件：</label>
                        <div class="col-sm-7">
                            <%--<select id="addAccInv" class="js-example-tags accInv" onchange="takeAccPrice();" name="accId" style="width:100%">--%>
                            <%--</select>--%>
                            <input type="hidden" id="addInvId" name="accId"/>
                            <input readonly="true" onclick="checkAppointment();" type="text" name="accName"
                                   id="addAccInv" placeholder="请选择配件" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售时间：</label>
                        <div class="col-sm-7">
                            <input id="addDateTimePicker" placeholder="请选择配件销售时间" onclick="getDate('addDateTimePicker')"
                                   readonly="true" type="text" name="accSaledTime"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售数量：</label>
                        <div class="col-sm-7">
                            <input type="number" id="addCountNum" min="1" name="accSaleCount" onchange="Addcalculate();"
                                   placeholder="请输入配件销售数量" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售单价：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" id="addSalePrice" name="accSalePrice"
                                   onchange="Addcalculate();"
                                   placeholder="请输入配件销售单价" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售折扣：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0.0" step="0.1" max="1" id="addSaleDiscount"
                                   name="accSaleDiscount" onchange="Addcalculate();" placeholder="请输入配件销售折扣"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售总价：</label>
                        <div class="col-sm-7">
                            <input type="number" readonly="true" min="0.0" step="0.1" id="addSaleTotal"
                                   name="accSaleTotal"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售最终价：</label>
                        <div class="col-sm-7">
                            <input type="number" readonly="true" min="0.0" step="0.1" id="addSaleMoney"
                                   name="accSaleMoney"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('addWindow','addForm')"
                                    data-dismiss="modal">关闭
                            </button>
                            <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-success">添加
                            </button>
                            <input type="reset" name="reset" style="display: none;"/>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 60%;">
        <div class="modal-content">
            <div class="modal-body" onkeydown="keydown('editButton')">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow','editForm')"
                      data-dismiss="modal"></span>
                <form class="form-horizontal" role="form" id="editForm" method="post">
                    <input type="hidden" name="accSaleId" define="AccessoriesSale.accSaleId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改配件销售信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件：</label>
                        <div class="col-sm-7">
                            <select id="editAccInv" class="js-example-tags accInv" onchange=""
                                    define="AccessoriesSale.accId" readonly="true"
                                    name="accId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售时间：</label>
                        <div class="col-sm-7">
                            <input id="editDateTimePicker" placeholder="请选择配件销售时间"
                                   readonly="true" type="text" name="accSaledTime"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售数量：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editSaleNum" name="accSaleCount" onchange="Editcalculate()"
                                   define="AccessoriesSale.accSaleCount"
                                   placeholder="请输入配件销售数量" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售单价：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editSalePrice" name="accSalePrice" onchange="Editcalculate()"
                                   define="AccessoriesSale.accSalePrice"
                                   placeholder="请输入配件销售单价" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售折扣：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editSaleDiscount" name="accSaleDiscount" onchange="Editcalculate()"
                                   define="AccessoriesSale.accSaleDiscount"
                                   placeholder="请输入销售折扣，0.1代表1折" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售总价：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editSaleTotal" name="accSaleTotal"
                                   define="AccessoriesSale.accSaleTotal"
                                   placeholder="请输入配件销售总价" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件销售最终价：</label>
                        <div class="col-sm-7">
                            <input type="number" id="editSaleMoney" name="accSaleMoney"
                                   define="AccessoriesSale.accSaleMoney"
                                   placeholder="请输入配件销售最终价" class="form-control">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('editWindow','editForm')"
                                    data-dismiss="modal">关闭
                            </button>
                            <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-success">保存
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="appWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeAppWin()"></span>
                <h3>请选择配件</h3>
                <table id="accTable">
                    <thead>
                    <tr>
                        <th data-radio="true" data-field="status"></th>
                        <th data-field="accessoriesType.accTypeName">配件所属类别</th>
                        <th data-field="supply.supplyName">所属供应商</th>
                        <th data-field="accName">配件名称</th>
                        <th data-field="accDes">配件描述</th>
                        <th data-field="accPrice">配件价格</th>
                        <th data-field="accSalePrice">配件售价</th>
                        <th data-field="accTotal">配件数量</th>
                        <th data-field="accIdle">配件可用数量</th>
                    </tr>
                    </thead>
                </table>
                <div id="toolbar1" class="btn-group">
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
                        <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable1();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用配件库存
                        </button>
                    </shiro:hasAnyRoles>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
                        <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable1();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用配件库存
                        </button>
                    </shiro:hasAnyRoles>
                    <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                        <div class="input-group-btn">
                            <button type="button" id="ulButton1" class="btn btn-default" style="border-radius:0px;"
                                    data-toggle="dropdown">配件名称</button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="onclikLi1(this)">配件名称</a></li>
                                <li class="divider"></li>
                                <li><a onclick="onclikLi1(this)">配件类型</a></li>
                                <li class="divider"></li>
                            </ul>
                        </div>
                        <input id="ulInput1" class="form-control" onkeypress="if(event.keyCode==13) {accblurredQuery();}">
                        <a href="javaScript:;" onclick="accblurredQuery()"><span
                                class="glyphicon glyphicon-search search-style"></span></a>
                        </input>
                    </div>
                </div>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" onclick="closeAppWin()">关闭
                    </button>
                    <input type="button" class="btn btn-primary" onclick="checkApp()" value="确定">
                    </input>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 提示弹窗 -->
<div class="modal fade" id="tanchuang" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                提示
            </div>
            <div class="modal-body">
                请先选择某一行
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
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
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/backstage/accessories/accessories_sale.js"></script>
</body>
</html>
