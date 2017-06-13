<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>常见故障管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/fileinput.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table"
               data-toggle="table"
               data-toolbar="#toolbar"
               data-url="/table/query"
               data-method="post"
               data-query-params="queryParams"
               data-pagination="true"
               data-search="true"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-page-size="10"
               data-height="543"
               data-id-field="id"
               data-page-list="[5, 10, 20]"
               data-cach="false"
               data-click-to-select="true"
               data-single-select="true">
            <thead>
            <tr>
                <th data-radio="true" data-field="status"></th>
                <th data-field="faultsName">故障名称</th>
                <th data-field="recordTime">记录时间</th>
                <th data-field="level">等级</th>
                <th data-field="faultsDes">故障描述</th>
                <th data-field="faultsImages">故障截图</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
            </button>
            <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
            </button>
            <button id="btn_delete" type="button" class="btn btn-default" onclick="showDel();">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
            </button>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog" style="overflow:hidden;">
        <div class="modal-content" style="overflow:hidden;">
            <form class="form-horizontal" role="form" onsubmit="return checkAdd()" id="showAddFormWar" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>请填写相关的故障信息</h4>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障名称：</label>
                    <div class="col-sm-7">
                        <input type="text" name="faultsName" placeholder="请输入故障的名称" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">记录时间：</label>
                    <div class="col-sm-7">
                        <input type="text" name="recordTime" define="companyInfo.companyOpenTime" placeholder="请选择发现故障的时间"
                               value="2012-05-15 21:05"
                               id="addDateTimePicker" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障等级：</label>
                    <div class="col-sm-7">
                        <input type="text" name="level" placeholder="请预估故障的等级" class="form-control"></input>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障描述：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="faultsDes" placeholder="请输入该故障相关的描述" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障截图：</label>
                    <div class="col-lg-7">
                        <div class="ibox-title">
                            <div class="input-group" style="padding-left: 15px;">
                                <input id="add_commonFaultsImages" define="commonFaults.faultsImages" name="txt_file"
                                       type="file" class="form-control" multiple
                                       class="file-loading"
                                       placeholder="请选择或输入一个你想上传的相册类型,默认当天日期为类型!"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-sm btn-success" type="submit">保 存</button>
                    </div>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" aria-hidden="true" style="overflow:auto;">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" role="form" onsubmit="return checkAdd()" id="showEditFormWar" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>请填写相关的故障信息</h4>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障名称：</label>
                    <div class="col-sm-7">
                        <input type="text" name="faultsName" placeholder="请输入故障的名称" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">记录时间：</label>
                    <div class="col-sm-7">
                        <input type="text" name="recordTime" define="companyInfo.companyOpenTime" placeholder="请选择发现故障的时间"
                               value="2012-05-15 21:05"
                               id="editDateTimePicker" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障等级：</label>
                    <div class="col-sm-7">
                        <input type="text" name="level" placeholder="请预估故障的等级" class="form-control"></input>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障描述：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="faultsDes" placeholder="请输入该故障相关的描述" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">故障截图：</label>
                    <div class="col-lg-7">
                        <div class="ibox-title">
                            <div class="input-group" style="padding-left: 15px;">
                                <input id="edit_commonFaultsImages" define="commonFaults.faultsImages" name="txt_file"
                                       type="file" class="form-control" multiple
                                       class="file-loading"
                                       placeholder="请选择或输入一个你想上传的相册类型,默认当天日期为类型!"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button class="btn btn-sm btn-success" type="submit">保 存</button>
                    </div>
                </div>
            </form>
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
<script src="/static/js/fileInput/fileinput.js"></script>
<script src="/static/js/fileInput/zh.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/form/jquery.validate.js"></script>
<script src="/static/js/backstage/basicInfoManage/commonFaults.js"></script>
</body>
</html>
