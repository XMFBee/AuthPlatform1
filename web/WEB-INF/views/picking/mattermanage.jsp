<%--
  Created by IntelliJ IDEA.
  User: yaoyong
  Date: 2017/4/11
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>物料管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <style>
        .close {
            right:20px;
            position: relative;
        }
    </style>
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>
<div class="container">
    <%--<div class="nav">
        <ul id="myTab" class="nav nav-tabs">
            <li class="pull-right" onclick="initHistoryTab()">
                <a href="#historyPanel" data-toggle="tab">
                    <h4><span class="glyphicon glyphicon-time" ></span>&nbsp;历史记录</h4>
                </a>
            </li>
            <li class="pull-right" onclick = "initReturnedTab()">
                <a href="#workInfoPanel" data-toggle="tab">
                    <h4>退料管理</h4>
                </a>
            </li>
            <li class="active pull-right" onclick = "initAcquisitionTab()">
                <a href="#workInfoPanel" data-toggle="tab">
                    <h4>领料管理</h4>
                </a>
            </li>
        </ul>
    </div>--%>

    <div  class="tab-content">
        <div id="workInfoPanel" class="tab-pane fade in active panel-body " data-toggle="tab" style="padding-bottom:0px;"  >
            <table id="materialsTable">
                <thead>
                <tr>
                    <th data-field="varsMap.startName">申请人</th>
                    <th data-field="endUser" data-formatter="endUserFormatter">审批人</th>
                    <th data-field="varsMap.acc.accName">物料名称</th>
                    <th data-field="varsMap.accCount"  data-formatter="accCountFormatter">数量</th>
                    <th data-field="varsMap.acc.accIdle">可用数量</th>
                    <%--<th data-field="varsMap.acc.accUnit">计量单位</th>--%>
                    <th data-field="varsMap.acc.accPrice">单价</th>
                    <th data-field="varsMap.reqMsg" data-formatter = "reqMsgFormatter" >申请原因</th>
                    <th data-field="processInstance.startTime" data-formatter="dateTimeFormatter">时间
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <shiro:hasAnyRoles name="汽车公司库管人员,公司超级管理员,公司普通管理员">
                        <th data-field="todoCell" data-formatter="todoCell" data-events="btnevent">操作</th>
                    </shiro:hasAnyRoles>

                </tr>
                </thead>
            </table>
            <div id = "toolbar" class="btn-group">
                <button type="button" class="btn btn-default" onclick = "initAcquisitionTab()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>领料管理
                </button>
                <button type="button" class="btn btn-default" onclick = "initReturnedTab()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>退料管理
                </button>
                <button type="button" class="btn btn-default" onclick="initHistoryTab()">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>历史记录
                </button>
            </div>
        </div>
    </div>
</div>

    <div class="modal fade" id="reviewModal" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"  onclick = "closeModal('reviewModal','form')"></span>
                <form role="form" class="form-horizontal" id="reviewForm">
                    <div class="modal-header">
                    </div>
                    <input type="text" name="isOK"  readonly  define = "obj.isOK" style="display: none;"/>
                    <input type="text" name="processInstanceId"  readonly define="obj.processInstanceId"  style="display: none;"/>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">结果说明：</label>
                        <div class="col-sm-7">
                                <textarea type="textarea" class="form-control" placeholder="请输入审核结果说明"
                                          name="respMsg" rows="3" maxlength="100"></textarea>
                        </div>
                    </div>
                    <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司库管人员">
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default"
                                    onclick = "closeModal('reviewModal','reviewForm')">关闭
                            </button>
                            <button id="subButton1" type="button" onclick="checkReview()" class="btn btn-success">保存</button>
                            <input type="reset" name="reset" style="display: none;"/>
                        </div>
                    </shiro:hasAnyRoles>
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
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/picking/mattermanage.js"></script>
</body>

</html>
