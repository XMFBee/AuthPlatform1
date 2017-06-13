<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>权限管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
</head>
<style>
    .coldStatus {
        color: #999
    }
    a {
        color: #337ab7;
    }
    .modal-header h3 {
        font-family: '微软雅黑';
    }
    .close {
        right:20px;
        top: 10px;
        position: relative;
    }

</style>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-heading" style="height:90px; border-bottom: 1px solid #ddd">
        <h3 style="display: inline;line-height:70px;" >权限管理</h3>
    </div>
<%--    <div class="nav">
        <ul id="myTab" class="nav nav-tabs">
            <li class="pull-right" onclick = "recycle()">
                <a  data-toggle="tab" >
                    <h4>回收站</h4>
                </a>
            </li>
            <li class="active pull-right" onclick = "canUse()">
                <a  data-toggle="tab" >
                    <h4>可使用</h4>
                </a>
            </li>
        </ul>
    </div>--%>
    <div class="panel-body" style="padding-bottom:0px;"  >
        <table id="table">
            <thead>
            <tr>
                <th data-field="permissionZhname">权限名称</th>
                <th  data-field="permissionDes">权限描述</th>
                <th data-field="permissionStatus" data-formatter=formatterstatus>权限状态</th>
                <th data-field="module" data-formatter=formatterfun>权限所属模块</th>
                <th data-field="todoCell"  data-formatter=todoCell data-events=operateEvents>操作</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <div class="input-group" style="width:350px;float:left;padding:0;margin:0 0 0 -1px;">
                <div class="input-group-btn">
                    <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                            data-toggle="dropdown">名称/描述/所属模块<span class="caret"></span></button>
                    <ul class="dropdown-menu pull-right">
                        <li><a onclick="onclikLi(this)">名称/描述/所属模块</a></li>
                        <li class="divider"></li>
                        <li><a onclick="onclikLi(this)">名称</a></li>
                        <li class="divider"></li>
                        <li><a onclick="onclikLi(this)">描述</a></li>
                        <li class="divider"></li>
                        <li><a onclick="onclikLi(this)">所属模块</a></li>
                        <li class="divider"></li>
                    </ul>
                </div><!-- /btn-group -->
                <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                <a href="javaScript:;" onclick="blurredQuery()"><span
                        class="glyphicon glyphicon-search search-style"></span></a>
                </input>
            </div><!-- /input-group -->
         </div>
     </div>
 </div>
<!-- 修改弹窗 -->
<div class="modal fade" id="edit" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <span class="glyphicon glyphicon-remove closeModal" onclick = "closeModal()"></span>
            <div class="modal-header">
                <h4></h4>
            </div>
            <hr/>
            <form id="editForm" class="form-horizontal">
                <input type="text" name="permissionId" define="permission.permissionId" style="display:none">
                <div class="form-group">
                    <label class="col-sm-3 control-label">权限名称：</label>
                    <div class="col-sm-7">
                        <input type="text" name="permissionZhname" define="permission.permissionZhname" class="form-control" maxlength="20"   placeholder="请输入权限名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">权限所属模块：</label>
                    <div class="col-sm-7">
                        <select id="moduleSelect" name="module.moduleId"  class="js-example-basic-multiple form-control" define="permission.module.moduleId"  style="width:300px;"></select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">权限描述：</label>
                    <div class="col-sm-7">
                        <textarea type="textarea"  name="permissionDes"  define="permission.permissionDes" class="form-control"  placeholder="请输入权限描述"
                                  rows="3" maxlength="100"></textarea>
                    </div>
                </div>
            </form>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" onclick = "closeModal()">关闭
                    </button>
                    <button id="subButton"  class="btn btn-success" onclick="saveEdit()">保存</button>
                    <input type="reset" name="reset" style="display: none;"/>
                </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/backstage/systemManage/jquery.formFillTemp.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/backstage/systemManage/permissionsManage.js"></script>
</body>

<script>
</script>
</html>
