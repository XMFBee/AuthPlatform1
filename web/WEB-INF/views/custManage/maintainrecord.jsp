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
    <title>维修保养记录管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
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
        <table id="table">
            <thead>
            <tr>
                <th data-radio="true" data-field="status" ></th>
                <th data-field="checkin.userName" data-width="50">维修保养登记人</th>
                <th data-field="startTime" data-formatter="formatterDate">维修保养开始时间</th>
                <th data-field="endTime" data-formatter="formatterDate">维修保养预估结束时间</th>
                <th data-field="actualEndTime" data-formatter="formatterDate">维修保养实际结束时间</th>
                <th data-field="recordCreatedTime" data-formatter="formatterDate">维修保养记录创建时间</th>
                <th data-field="pickupTime" data-formatter="formatterDate">维修保养车主提车时间</th>
                <th data-field="recordDes">维修保养记录描述</th>
                <%--<th data-field="recordStatus" data-formatter="formatterStatus">维修保养记录状态</th>--%>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用保养记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用保养记录
                </button>
            </shiro:hasAnyRoles>
            <%--<button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">--%>
                <%--<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增--%>
            <%--</button>--%>
            <%--<button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">--%>
                <%--<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改--%>
            <%--</button>--%>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" style="overflow:auto; ">
    <div class="modal-dialog" style="width: 800px;height: auto;">
        <div class="modal-content" style="overflow:hidden;">
            <form class="form-horizontal" id="addForm" method="post">
                <div class="modal-header" style="overflow:auto;">
                    <h4>请填写维修保养记录信息</h4>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养登记人：</label>
                    <div class="col-sm-7">
                        <select id="record_check" name="checkinId" placeholder="请选择维修保养登记人" class="form-control js-data-example-ajax">
                            <option value="aaa">aaa</option>
                            <option value="bbb">bbb</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养开始时间：</label>
                    <div class="col-sm-7">
                        <input id="start" name="startTime" readonly placeholder="维修保养开始时间" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养预估结束时间：</label>
                    <div class="col-sm-7">
                        <input id="end" name="endTime" readonly placeholder="维修保养预估结束时间" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养实际结束时间：</label>
                    <div class="col-sm-7">
                        <input id="end2" name="actualEndTime" readonly placeholder="维修保养实际结束时间" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养记录创建时间：</label>
                    <div class="col-sm-7">
                        <input id="created" name="recordCreatedTime" readonly placeholder="维修保养记录创建时间" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养车主提车时间：</label>
                    <div class="col-sm-7">
                        <input id="pickup" name="pickupTime" readonly placeholder="维修保养车主提车时间" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养记录描述：</label>
                    <div class="col-sm-7">
                        <textarea type="text"  name="recordDes" placeholder="请输入维修保养记录描述" style="height: 120px;"
                                  class="form-control"></textarea>
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
<div class="modal fade" id="editWindow" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px;height: auto;">
        <div class="modal-content">
            <form class="form-horizontal" id="editForm" method="post">
                <input type="hidden" name="recordId" define="MaintainRecord.recordId">
                <input type="hidden" name="recordStatus" define="MaintainRecord.recordStatus">
                <div class="modal-header" style="overflow:auto;">
                    <h4>请修改维修保养记录信息</h4>
                </div>
                <br/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养登记人：</label>
                    <div class="col-sm-7">
                        <input type="text" name="checkinId" readonly define="MaintainRecord.checkin.userName" placeholder="请选择维修保养登记人" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养开始时间：</label>
                    <div class="col-sm-7">
                        <input id="start_edit" name="startTime" readonly define="MaintainRecord.startTime" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养预估结束时间：</label>
                    <div class="col-sm-7">
                        <input id="end_edit" name="endTime" readonly define="MaintainRecord.endTime" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养实际结束时间：</label>
                    <div class="col-sm-7">
                        <input id="end2_edit" name="actualEndTime" readonly  define="MaintainRecord.actualEndTime" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养记录创建时间：</label>
                    <div class="col-sm-7">
                        <input id="created_edit" name="recordCreatedTime" readonly define="MaintainRecord.recordCreatedTime" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养车主提车时间：</label>
                    <div class="col-sm-7">
                        <input id="pickup_edit" name="pickupTime" readonly define="MaintainRecord.pickupTime" class="layui-input">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">维修保养记录描述：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="recordDes" define="MaintainRecord.recordDes" placeholder="请输入维修保养记录描述" style="height: 120px;"
                                  class="form-control"></textarea>
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
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/custManage/maintainrecord.js"></script>
<script src="/static/js/form/jquery.validate.js"></script>
<script src="/static/js/plugins/layui/layui.js" charset="utf-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script>
    layui.use('laydate', function () {
        //日期范围限制
        var start = {
            elem: '#start',
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-12-30 23:59:59', //最大日期
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
                end2.min = datas; //开始日选好后，重置结束日的最小日期
                end2.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#end',
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        var end2 = {
            elem: '#end2',
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
                pickup.min = datas; //开始日选好后，重置结束日的最小日期
                pickup.start = datas //将结束日的初始值设定为开始日
            }
        };

        var created = {
            elem: '#created',
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        var pickup = {
            elem: '#pickup',
            format: 'YYYY-MM-DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                end2.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        document.getElementById('start').onclick = function(){
            start.elem = this;
            laydate(start);
        }

        document.getElementById('end').onclick = function(){
            end.elem = this;
            laydate(end);
        }

        document.getElementById('end2').onclick = function(){
            end2.elem = this;
            laydate(end2);
        }

        document.getElementById('created').onclick = function(){
            created.elem = this;
            laydate(created);
        }

        document.getElementById('pickup').onclick = function(){
            pickup.elem = this;
            laydate(pickup);
        }

        //日期范围限制
        var start_edit = {
            elem: '#start_edit',
            format: 'YYYY-MM-DD hh:mm:ss',
//            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-12-30 23:59:59', //最大日期
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                end_edit.min = datas; //开始日选好后，重置结束日的最小日期
                end_edit.start = datas //将结束日的初始值设定为开始日
                end2_edit.min = datas; //开始日选好后，重置结束日的最小日期
                end2_edit.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end_edit = {
            elem: '#end_edit',
            format: 'YYYY-MM-DD hh:mm:ss',
//            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start_edit.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        var end2_edit = {
            elem: '#end2_edit',
            format: 'YYYY-MM-DD hh:mm:ss',
//            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start_edit.max = datas; //结束日选好后，重置开始日的最大日期
                pickup_edit.min = datas; //开始日选好后，重置结束日的最小日期
                pickup_edit.start = datas //将结束日的初始值设定为开始日
            }
        };

        var created_edit = {
            elem: '#created_edit',
            format: 'YYYY-MM-DD hh:mm:ss',
//            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                start_edit.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        var pickup_edit = {
            elem: '#pickup_edit',
            format: 'YYYY-MM-DD hh:mm:ss',
//            min: laydate.now(),
            max: '2099-12-30 23:59:59',
            istime: true,
            istoday: false,
            festival: true,
            choose: function (datas) {
                end2_edit.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };

        document.getElementById('start_edit').onclick = function(){
            start_edit.elem = this;
            laydate(start_edit);
        }

        document.getElementById('end_edit').onclick = function(){
            end_edit.elem = this;
            laydate(end_edit);
        }

        document.getElementById('end2_edit').onclick = function(){
            end2_edit.elem = this;
            laydate(end2_edit);
        }

        document.getElementById('created_edit').onclick = function(){
            created_edit.elem = this;
            laydate(created_edit);
        }

        document.getElementById('pickup_edit').onclick = function(){
            pickup_edit.elem = this;
            laydate(pickup_edit);
        }
    });
</script>
</body>
</html>
