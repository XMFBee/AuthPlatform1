<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>汽车车型管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table">
            <thead>
                <tr>
                    <th data-radio="true"></th>
                    <th data-field="modelName">
                        车型名字
                    </th>
                    <th data-field="modelDes">
                        车型描述
                    </th>
                    <th data-field="carBrand.brandName">
                        品牌名称
                    </th>
                    <th data-field="modelStaus" data-formatter="showStatusFormatter">
                        车型状态
                    </th>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <th data-field="modelStaus" data-formatter="statusFormatter">
                            操作
                        </th>
                    </shiro:hasAnyRoles>
                </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用汽车车型记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用汽车车型记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                <div class="input-group" style="width:300px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">车型名称/品牌名称</button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">车型名称/品牌名称</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">车型名称</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">品牌名称</a></li>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javaScript:;" onclick="blurredQuery()"><span class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div><!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false >
    <div class="modal-dialog" style="width: 700px;height: auto;">
        <div class="modal-content" style="overflow:hidden;">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
            <form class="form-horizontal" role="form" id="addForm" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>添加汽车车型信息</h4>
                </div>
                <br/>

                <div class="form-group">
                    <label class="col-sm-3 control-label">汽车品牌：</label>
                    <div class="col-sm-7">
                        <select id="addCarBrand" class="js-example-tags carBrand" name="brandId" style="width:100%">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">车型名称：</label>
                    <div class="col-sm-7">
                        <input type="text" id="addmodelName" name="modelName" placeholder="请输入该车型名称" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">车型描述：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="modelDes" placeholder="请输入关于该车型的描述" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('addWindow', 'addForm')">关闭</button>
                        <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-sm btn-success">添加</button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
            <form class="form-horizontal" role="form" id="editForm">
                <div class="modal-header" style="overflow:auto;">
                    <h4>修改汽车车型的信息</h4>
                </div>
                <input type="hidden" name="modelId" define="carModel.modelId">
                <input type="hidden" name="modelStaus" define="carModel.modelStaus">
                <div id="editModelDiv" class="form-group">
                    <label class="col-sm-3 control-label">品牌名称：</label>
                    <div class="col-sm-7">
                        <select id="editCarBrand" class="js-example-tags carBrand" name="brandId" style="width:100%">
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">车型名称：</label>
                    <div class="col-sm-7">
                        <input type="text" id="editmodelName" name="modelName" define="carModel.modelName" placeholder="请输入该车型名字" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">车型描述：</label>
                    <div class="col-sm-7">
                        <textarea type="textarea" name="modelDes" define="carModel.modelDes" placeholder="请输入关于该车型的描述" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('editWindow', 'editForm')">关闭</button>
                        <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-sm btn-success">保存</button>
                    </div>
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
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/basicInfoManage/carModel.js"></script>
</body>
</html>
