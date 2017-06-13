<%--
  Created by IntelliJ IDEA.
  User: XiaoQiao
  Date: 2017/4/11
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>配件分类管理</title>
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
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员">
            <table id="table">
                <thead>
                <tr>
                    <th data-radio="true" data-field="status"></th>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <th data-field="company.companyName">汽车公司</th>
                    </shiro:hasAnyRoles>
                    <th data-field="accTypeName">配件分类名称</th>
                    <th data-field="accTypeDes">配件分类描述</th>
                    <th data-field="accTypeStatus" data-formatter="showStatusFormatter">配件分类状态</th>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                        <th data-width="100" data-field="accTypeStatus" data-formatter="statusFormatter">
                            操作
                        </th>
                    </shiro:hasAnyRoles>
                </tr>
                </thead>
            </table>
        </shiro:hasAnyRoles>

        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用配件分类
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用配件分类
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员">
                <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">配件分类名称<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles><span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">配件分类名称<shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">/汽车公司</shiro:hasAnyRoles></a></li>
                            <li class="divider"></li>
                            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                                <li><a onclick="onclikLi(this)">汽车公司</a></li>
                                <li class="divider"></li>
                            </shiro:hasAnyRoles>
                            <li><a onclick="onclikLi(this)">配件分类名称</a></li>
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
                <form class="form-horizontal" id="addForm" method="post">
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加配件分类信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件分类名称：</label>
                        <div class="col-sm-7">
                            <input type="text" id="addTypeName"
                                   name="accTypeName" placeholder="请输入配件分类名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件分类描述：</label>
                        <div class="col-sm-7">
                        <textarea type="text" name="accTypeDes" placeholder="请输入相关内容" style="height: 100px;"
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
                <form class="form-horizontal" id="editForm" method="post">
                    <input type="hidden" name="accTypeId" define="AccessoriesType.accTypeId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改配件分类信息</h4>
                    </div>
                    <br/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件分类名称：</label>
                        <div class="col-sm-7">
                            <input type="text" id="editTypeName" name="accTypeName" define="AccessoriesType.accTypeName"
                                   placeholder="请输入配件分类名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">配件分类描述：</label>
                        <div class="col-sm-7">
                        <textarea type="text" name="accTypeDes" define="AccessoriesType.accTypeDes"
                                  placeholder="请输入相关内容" style="height: 100px;"
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
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/accessories/accessories_type.js"></script>
</body>
</html>
