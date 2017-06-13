<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>维修保养进度管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
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
                <th data-radio="true" data-field="status"></th>
                <th data-field="maintainRecord.checkin.userName">车主姓名</th>
                <th data-field="maintainRecord.checkin.userPhone">车主电话</th>
                <th data-field="maintainRecord.checkin.brand.brandName">汽车品牌</th>
                <th data-field="maintainRecord.checkin.color.colorName">汽车颜色</th>
                <th data-field="maintainRecord.checkin.plate.plateName">车牌号码</th>
                <th data-field="maintainRecord.checkin.company.companyName">汽修公司</th>
                <th data-field="maintainRecord.checkin.model.modelName">汽车车型</th>
                <th data-field="maintainRecord.checkin.carPlate">车牌名称</th>
                <th data-field="maintainRecord.checkin.maintainOrFix">维修|保养</th>
                <th data-field="maintainRecord.checkin.intactDegrees">车身完好度</th>
                <th data-field="maintainRecord.checkin.carThings">车上物品</th>
                <th data-field="maintainRecord.checkin.userRequests">用户要求描述</th>
                <th data-width="90" data-field="workStatus" data-formatter="showStatusFormatter1">
                    工单状态
                </th>
            </tr>
            </thead>
        </table>
        <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
            <div id="toolbar" class="btn-group">
                <button id="btn_editAcc" type="button" class="btn btn-default" onclick="showSchedule();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看维修保养进度
                </button>
            </div>
        </shiro:hasAnyRoles>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="addWindow" aria-hidden="true" style="overflow:auto; ">
    <div class="modal-dialog">
        <div class="modal-content" style="overflow:hidden;">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow','addForm')"></span>
                <form class="form-horizontal" role="form" id="addForm" method="post">
                    <input type="hidden" name="maintainRecordId" id="addMaintainRecordId"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>添加车辆维修保养进度描述</h4>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">进度描述：</label>
                        <div class="col-sm-7">
                            <textarea type="textarea" class="form-control" row="8" name="maintainScheduleDes" maxlength="200" placeholder="请输入保养进度描述"></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('addWindow','addForm')">关闭</button>
                            <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-success">保存</button>
                        </div>
                        <input type="reset" name="reset" style="display: none;"/>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" aria-hidden="true" style="overflow:auto; ">
    <div class="modal-dialog">
        <div class="modal-content" style="overflow:hidden;">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow','editForm')"></span>
                <form class="form-horizontal" role="form" id="editForm" method="post">
                    <input type="hidden" id="editMaintainScheduleId" name="maintainScheduleId"/>
                    <input type="hidden" id="editMaintainRecordId" name="maintainRecordId"/>
                    <input type="hidden" id="editMsCreatedTime" name="msCreatedTime"/>
                    <div class="modal-header" style="overflow:auto;">
                        <h4>修改车辆维修保养进度描述</h4>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">进度描述：</label>
                        <div class="col-sm-7">
                            <textarea id="editMaintainScheduleDes" name="maintainScheduleDes" placeholder="请输入保养进度描述" row="50" class="form-control" ></textarea>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="col-sm-offset-8">
                            <button type="button" class="btn btn-default" onclick="closeModals('editWindow','editForm')">关闭</button>
                            <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-success">保存</button>
                        </div>
                    </div>
                </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<%--维修保养记录弹窗--%>
<div id="AddScheduleWindow" class="modal fade" aria-hidden="true" style="overflow:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12 b-r">
                        <h3 class="m-t-none m-b">维修保养记录</h3>
                        <table class="table table-hover" id="addScheudleTable" data-height="550">
                            <thead>
                            <tr>
                                <th data-checkbox="true"></th>
                                <th data-field="checkin.userName" data-width="50">保养登记人</th>
                                <th data-field="startTime" data-formatter="formatterDate">保养开始时间</th>
                                <th data-field="endTime" data-formatter="formatterDate">养预估结束时间</th>
                                <th data-field="actualEndTime" data-formatter="formatterDate">保养实际结束时间</th>
                                <th data-field="recordCreatedTime" data-formatter="formatterDate">保养记录创建时间</th>
                                <th data-field="pickupTime" data-formatter="formatterDate">保养车主提车时间</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="modal-footer" style="overflow:hidden;">
                            <button type="button" class="btn btn-default" onclick="closeAddSchedule();">关闭
                            </button>
                            <input type="button" class="btn btn-primary" onclick="checkPersonnel();" value="确定">
                            </input>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%--查看进度--%>
<div id="ScheduleWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static" keyboard:false>

    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-body">
                <div id="ifConfirm" style="background: url('/static/img/materialsFlag1.png')-50px -50px no-repeat;position: absolute;z-index:999;width: 200px;height: 200px;background-size:200px;display: none"></div>
                <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                <h3 class="m-t-none m-b">车辆维修保养进度</h3>
                <shiro:hasAnyRoles name="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部">
                    <div id="toolbars" class="btn-group" style="padding-top: 10px;">
                        <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增进度描述
                        </button>
                        <button id="btn_edit" type="button" class="btn btn-default" onclick="showOk();">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>确认完成
                        </button>
                    </div>
                </shiro:hasAnyRoles>
                <div id="maintenance" style="padding-top:80px;"></div>
                <div class="modal-footer" style="border: none">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入jquery -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/backstage/basicInfoManage/maintenance.js"></script>

</body>
</html>
