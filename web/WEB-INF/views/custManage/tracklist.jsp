<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/11
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>跟踪回访管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
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
                <th data-field="user.userName">回访人</th>
                <th data-field="checkin.userName">跟踪回访的车主</th>
                <th data-field="serviceEvaluate" data-formatter="formatterserviceEvaluate">本次服务评价</th>
                <th data-field="trackContent">回访问题</th>
                <th data-field="trackCreatedTime" data-formatter="formatterDate">跟踪回访创建时间</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
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
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主">
                <div class="input-group" style="width:350px;float:right;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">跟踪回访车主<span class="caret"></span></button>
                        <ul class="dropdown-menu pull-right">
                            <%--<li><a onclick="onclikLi(this)">跟踪回访用户/回访问题/服务评价</a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a onclick="onclikLi(this)">回访人</a></li>--%>
                            <%--<li class="divider"></li>--%>
                            <li><a onclick="onclikLi(this)">跟踪回访车主</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">回访问题</a></li>
                            <%--<li class="divider"></li>--%>
                            <%--<li><a onclick="onclikLi(this)">服务评价</a></li>--%>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javascript:;" onclick="blurredQuery()"><span
                            class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div>
                <!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" data-backdrop="static" keyboard:false style="overflow:auto; ">
    <div class="modal-dialog" style="width: 750px;height: auto;">
        <div class="modal-content" style="overflow:hidden;">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
            <form class="form-horizontal" id="addForm" method="post">
                <input id="addTrackUserId" type="hidden" name="trackUser" />
                <div class="modal-header" style="overflow:auto;">
                    <h4>添加跟踪回访的信息</h4>
                </div>
                <br/>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">回访人：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--&lt;%&ndash;<select id="addAdminName" name="userId" class="form-control js-data-example-ajax admin"&ndash;%&gt;--%>
                                <%--&lt;%&ndash;style="width:100%">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                        <%--<input type="text" readonly value="${sessionScope.user.userName}" class="form-control">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">跟踪回访的车主：</label>
                    <div class="col-sm-7">
                        <%--<select id="addUserName" name="trackUser" class="form-control js-data-example-ajax user"--%>
                        <%--style="width:100%">--%>
                        <%--</select>--%>
                        <input id="addTrackUser" type="text" readonly class="form-control" style="width:70%">
                        <button type="button" class="btn btn-default" onclick="showRemindUser()">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>请选择车主
                        </button>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">本次服务评价：</label>
                    <div class="col-sm-7">
                        <%--<input type="number" name="serviceEvaluate" min="1" max="10" placeholder="请输入本次服务评价"--%>
                               <%--class="form-control"></input>--%>
                        <select name="serviceEvaluate" class="form-control js-data-example-ajax">
                            <option value="10">10分（好）</option>
                            <option value="9">9分（一般）</option>
                            <option value="8">8分（差）</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">回访问题：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="trackContent" placeholder="请输入回访问题内容" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">跟踪回访创建时间：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--<input id="addTrackCreatedTime" name="trackCreatedTime" readonly onclick="getDate('addTrackCreatedTime')" class="form-control datetimepicker"/>--%>
                        <%--&lt;%&ndash;onclick="layui.laydate({elem: this, min: laydate.now(), format: 'yyyy-MM-dd hh:mm:ss', max: '2099-06-16 23:59:59'})"&ndash;%&gt;--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default"
                                onclick="closeTrackListModals()">关闭
                        </button>
                        <button id="addButton" class="btn btn-sm btn-success" type="button" onclick="addSubmit()">保 存
                        </button>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 750px;height: auto;">
        <div class="modal-content">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
            <form class="form-horizontal" id="editForm" method="post">
                <input type="hidden" name="trackId" define="TrackList.trackId"/>
                <div class="modal-header" style="overflow:auto;">
                    <h4>修改跟踪回访的信息</h4>
                </div>
                <br/>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">回访人：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--&lt;%&ndash;<select id="editAdminName" name="userId" class="form-control js-data-example-ajax admin"&ndash;%&gt;--%>
                        <%--&lt;%&ndash;style="width:100%">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>
                        <%--<input type="text" name="userId" readonly define="TrackList.userId" class="form-control"--%>
                               <%--value="${sessionScope.user.userName}">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">跟踪回访的车主：</label>
                    <div class="col-sm-7">
                        <%--<select id="editUserName" name="trackUser" class="form-control js-data-example-ajax user"--%>
                                <%--style="width:100%">--%>
                        <%--</select>--%>
                            <input id="editUserName" type="text" readonly class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">本次服务评价：</label>
                    <div class="col-sm-7">
                        <%--<input type="number" name="serviceEvaluate" min="1" max="10 " define="TrackList.serviceEvaluate"--%>
                               <%--placeholder="请输入本次服务评价" class="form-control"></input>--%>
                        <select name="serviceEvaluate" define="TrackList.serviceEvaluate" class="form-control js-data-example-ajax">
                            <option value="10">10分（好）</option>
                            <option value="9">9分（一般）</option>
                            <option value="8">8分（差）</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">回访问题：</label>
                    <div class="col-sm-7">
                        <textarea type="text" name="trackContent" define="TrackList.trackContent"
                                  placeholder="请输入回访问题内容" style="height: 100px;"
                                  class="form-control"></textarea>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                    <%--<label class="col-sm-3 control-label">跟踪回访创建时间：</label>--%>
                    <%--<div class="col-sm-7">--%>
                        <%--<input id="editTrackCreatedTime" name="trackCreatedTime" readonly--%>
                               <%--define="TrackList.trackCreatedTime" class="form-control datetimepicker">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <div class="col-sm-offset-8">
                        <button type="button" class="btn btn-default"
                                onclick="closeModals('editWindow', 'editForm')">关闭
                        </button>
                        <button id="editButton" class="btn btn-sm btn-success" type="button" onclick="editSubmit()">
                            保存
                        </button>
                    </div>
                </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="showRemindWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 80%">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeUserWin()"></span>
                <h4>请选择车主</h4>
                <table class="table table-hover" id="addRemindTable" style="table-layout: fixed">
                    <thead>
                    <tr>
                        <th data-radio="true"></th>
                        <th data-field="checkin.userName" data-width="100">维修保养登记人</th>
                        <th data-field="startTime" data-formatter="formatterDate">维修保养开始时间</th>
                        <th data-field="actualEndTime" data-formatter="formatterDate">维修保养实际结束时间</th>
                        <th data-field="pickupTime" data-formatter="formatterDate">维修保养车主提车时间</th>
                        <th data-field="recordDes">维修保养记录描述</th>
                    </tr>
                    </thead>
                </table>
                <div class="modal-footer" style="overflow:hidden;">
                    <button id="closeButton" type="button" class="btn btn-default" onclick="closeUserWin()">关闭
                    </button>
                    <input type="button" class="btn btn-primary" onclick="checkRemind()" value="确定">
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
<script src="/static/js/backstage/custManage/tracklist.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/plugins/layui/layui.js" charset="utf-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<%--<script>--%>
    <%--layui.use('laydate', function () {--%>
        <%--var laydate = layui.laydate;--%>

        <%--var addTrackCreatedTime = {--%>
            <%--format: 'yyyy-MM-dd hh:mm:ss',--%>
            <%--min: laydate.now(), //设定最小日期为当前日期--%>
            <%--max: '2099-12-30 23:59:59', //最大日期--%>
            <%--istime: true,--%>
            <%--istoday: false,--%>
            <%--festival: true--%>
        <%--};--%>

        <%--document.getElementById('addTrackCreatedTime').onclick = function () {--%>
            <%--addTrackCreatedTime.elem = this;--%>
            <%--laydate(addTrackCreatedTime);--%>
        <%--}--%>

        <%--var editTrackCreatedTime = {--%>
            <%--format: 'yyyy-MM-dd hh:mm:ss',--%>
            <%--max: '2099-12-30 23:59:59', //最大日期--%>
            <%--istime: true,--%>
            <%--istoday: false,--%>
            <%--festival: true--%>
        <%--};--%>

        <%--document.getElementById('editTrackCreatedTime').onclick = function () {--%>
            <%--editTrackCreatedTime.elem = this;--%>
            <%--laydate(editTrackCreatedTime);--%>
        <%--}--%>
    <%--});--%>
<%--</script>--%>
</body>
</html>
