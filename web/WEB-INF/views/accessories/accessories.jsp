<%--
  Created by IntelliJ IDEA.
  User: XiaoQiao
  Date: 2017/4/11
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>库存管理</title>
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
    <div class="panel-body">
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
            <table id="table">
                <thead>
                <tr>
                    <th data-radio="true" data-field="status"></th>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <th data-field="company.companyName">汽车公司</th>
                    </shiro:hasAnyRoles>
                    <th data-field="accessoriesType.accTypeName">配件类别</th>
                    <th data-field="supply.supplyName">供应商</th>
                    <th data-field="accName">配件名称</th>
                    <th data-field="accCommodityCode">配件商品条码</th>
                    <th data-field="accDes">配件描述</th>
                    <th data-field="accPrice">配件价格</th>
                    <th data-field="accSalePrice">配件售价</th>
                    <th data-field="accTotal">配件数量</th>
                    <th data-field="accIdle">配件可用数量</th>
                    <th data-field="accUsedTime" data-formatter="formatterDateTime">最近一次领料时间</th>
                    <th data-field="accBuyedTime" data-formatter="formatterDateTime">最近一次购买时间</th>
                    <th data-field="accCreatedTime" data-formatter="formatterDateTime">配件创建时间</th>
                    <th data-field="accStatus" data-formatter="showStatusFormatter">
                        记录状态
                    </th>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员">
                        <th data-width="100" data-field="accStatus" data-formatter="statusFormatter">
                            操作
                        </th>
                    </shiro:hasAnyRoles>
                </tr>
                </thead>
            </table>
        </shiro:hasAnyRoles>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用配件库存
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable1();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用配件库存
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司采购人员,系统超级管理员,系统普通管理员">
                <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">配件名称/供应商/配件类型<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles><span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">配件名称/供应商/配件类型<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles></a></li>
                            <li class="divider"></li>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                                <li><a onclick="onclikLi(this)">汽车公司</a></li>
                                <li class="divider"></li>
                            </shiro:hasAnyRoles>
                            <li><a onclick="onclikLi(this)">配件名称</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">供应商</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">配件类型</a></li>
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

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" style="overflow:auto; " data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 60%">
        <div class="modal-content">
            <div class="modal-body" onkeydown="keydown('addButton')">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow','addForm')"
                      data-dismiss="modal"></span>
                <form class="form-horizontal" role="form" id="addForm" method="post">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加配件库存信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">供应商：</label>
                        <div class="col-sm-7">
                            <select id="addSupply" class="js-example-tags supply" name="supplyId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件类型：</label>
                        <div class="col-sm-7">
                            <select id="addAccType" class="js-example-tags accType" name="accTypeId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="accName" placeholder="请输入配件名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件商品条码：</label>
                        <div class="col-sm-7">
                            <input type="number" name="accCommodityCode" placeholder="请输入配件商品条码" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件价格：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accPrice" placeholder="请输入配件价格" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件售价：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accSalePrice" placeholder="请输入配件售价" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件数量：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accTotal" placeholder="请输入配件数量" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件可用数量：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accIdle" placeholder="请输入配件可用数量" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件描述：</label>
                        <div class="col-sm-7">
                        <textarea type="text" name="accDes" placeholder="请输入相关内容" style="height: 100px;"
                                  class="form-control"></textarea>
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
                    <div class="modal-header" style="overflow:auto;">
                        <input type="hidden" name="accId" define="Accessories.accId"/>
                        <h4>修改配件库存信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">供应商：</label>
                        <div class="col-sm-7">
                            <select id="editSupply" class="js-example-tags supply" define="Accessories.supplyId"
                                    name="supplyId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件类型：</label>
                        <div class="col-sm-7">
                            <select id="editAccType" class="js-example-tags accType" define="Accessories.accTypeId"
                                    name="accTypeId" style="width:100%">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件名称：</label>
                        <div class="col-sm-7">
                            <input type="text" name="accName" define="Accessories.accName" placeholder="请输入配件名称"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件商品条码：</label>
                        <div class="col-sm-7">
                            <input type="number" name="accCommodityCode" define="Accessories.accCommodityCode"
                                   placeholder="请输入配件商品条码" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件价格：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accPrice" define="Accessories.accPrice" placeholder="请输入配件价格"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件售价：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accSalePrice" define="Accessories.accSalePrice"
                                   placeholder="请输入配件售价"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件数量：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accTotal" define="Accessories.accTotal" placeholder="请输入配件数量"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件可用数量：</label>
                        <div class="col-sm-7">
                            <input type="number" min="0" name="accIdle" define="Accessories.accIdle" placeholder="请输入配件可用数量"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件描述：</label>
                        <div class="col-sm-7">
                        <textarea type="text" name="accDes" define="Accessories.accDes" placeholder="请输入相关内容"
                                  style="height: 100px;"
                                  class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('editWindow','editForm')"
                                    data-dismiss="modal">关闭
                            </button>
                            <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-success">保存
                            </button>
                            <input type="reset" name="reset" style="display: none;"/>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除弹窗 -->
<div class="modal fade" id="del" aria-hidden="true">
    <div class="modal-dialog" style="overflow:hidden;">
        <form action="/table/edit" method="post">
            <div class="modal-content">
                <input type="hidden" id="delNoticeId"/>
                <div class="modal-footer" style="text-align: center;">
                    <h2>确认删除吗?</h2>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="sumbit" class="btn btn-primary" onclick="del()">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

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
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/accessories/accessories.js"></script>
</body>
</html>