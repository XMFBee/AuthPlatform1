<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <div id="toolbar" class="btn-group">
                <button id="btn_editAcc" type="button" class="btn btn-default" onclick="showSchedule();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看维修保养进度
                </button>
            </div>
    </div>
</div>

<%--查看进度--%>
<div id="ScheduleWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                <h3 class="m-t-none m-b">车辆维修保养进度</h3>
                <div id="maintenance" style="padding-top:50px;"></div>
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
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/main.js"></script>
<%--<script src="/static/js/backstage/basicInfoManage/maintenance.js"></script>--%>
<script>
    /*初始化表格*/
    $(function(){
                initTable('table','/Order/queryByFrontpage');//初始化表格
    });

    // 激活或禁用
    function showStatusFormatter1(value) {
        if(value == 'Y') {
            return "已完成";
        } else {
            return "未完成";
        }
    }

    // 显示所有进度
    function showSchedule(){
        var row =  $('#table').bootstrapTable('getSelections');
        if(row.length >0) {
            $.post("/maintainSchedule/queryScheduleByRecord/" + row[0].recordId, function (data) {
                // 查询出此记录所有明细进行forEach
                var htmlDiv = "";
                if(data.length > 0){
                    if(row[0].workStatus != 'Y') {
                        $("#ifConfirm").css("display", "none");
                        $("#toolbars").css("display", "block");
                        $.each(data, function(index, value, item) {
                            if (htmlDiv == "") {// 假如这个字符串刚开始设置,
                                htmlDiv = "<button class='btn btn-info btn-circle btn-lg' type='button' onclick='edit(\""+data[index].maintainScheduleId+','+ data[index].maintainRecordId+','+data[index].maintainScheduleDes+','+data[index].msCreatedTime+"\")' style='border-radius:45px;height:45px;'>" + (index + 1) + "</button>" +
                                    "<div style='border-bottom: 1px solid black;display:inline;'>&nbsp;&nbsp;" + data[index].maintainScheduleDes + "&nbsp;&nbsp;</div>"
                            } else {
                                htmlDiv += "<button class='btn btn-info btn-circle btn-lg' type='button' onclick='edit(\""+data[index].maintainScheduleId+','+ data[index].maintainRecordId+','+data[index].maintainScheduleDes+','+data[index].msCreatedTime+"\")' style='border-radius:45px;height:45px;'>" + (index + 1) + "</button>" +
                                    "<div style='border-bottom: 1px solid black;display:inline;' >&nbsp;&nbsp;" + data[index].maintainScheduleDes + "&nbsp;&nbsp;</div>";
                            }
                        });
                    }else{
                        $("#ifConfirm").css("display", "block");
                        $("#toolbars").css("display", "none");
                        $.each(data, function(index, value, item) {
                            if (htmlDiv == "") {// 假如这个字符串刚开始设置,
                                htmlDiv = "<button class='btn btn-info btn-circle btn-lg' type='button' style='border-radius:45px;height:45px;' disabled='disabled'>" + (index + 1) + "</button>" +
                                    "<div style='border-bottom: 1px solid black;display:inline;'>&nbsp;&nbsp;" + data[index].maintainScheduleDes + "&nbsp;&nbsp;</div>"
                            } else {
                                htmlDiv += "<button class='btn btn-info btn-circle btn-lg' type='button' style='border-radius:45px;height:45px;' disabled='disabled'>" + (index + 1) + "</button>" +
                                    "<div style='border-bottom: 1px solid black;display:inline;'>&nbsp;&nbsp;" + data[index].maintainScheduleDes + "&nbsp;&nbsp;</div>";
                            }
                        });
                    }
                    htmlDiv+= "<button class='btn btn-info btn-circle btn-lg' type='button' style='border-radius:45px;height:45px;' disabled='disabled'>"+(data.length+1)+"</button>";
                }else{
                    if(row[0].workStatus != 'Y') {
                        $("#ifConfirm").css("display", "none");
                        $("#toolbars").css("display", "block");
                    }else{
                        $("#ifConfirm").css("display", "block");
                        $("#toolbars").css("display", "none");
                    }
                    htmlDiv = "<h4>此维修保养记录暂无维修保养进度</h4>";
                }
                $("#maintenance").html(htmlDiv);
            });
            $("#ScheduleWindow").modal('show');
        }else{
            swal({
                title:"",
                text: "请选择要查看进度的维修保养记录", // 主要文本
                confirmButtonColor: "#dd2e32", // 提示按钮的颜色
                confirmButtonText:"确定", // 提示按钮上的文本
                type:"warning"}) // 提示类型
        }
    }

    function statusFormatter(index, row) {
        /*处理数据*/
        if (row.workStatus == 'Y') {
            return "&nbsp;&nbsp;已完成";
        } else {
            return "&nbsp;&nbsp;未完成";
        }

    }

    function openStatusFormatter(index, row) {
        /*处理数据*/
        if (row.workStatus == 'Y') {
            return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='active(\""+'/maintainSchedule/statusOperate?id='+row.workId+'&status=Y'+"\")'>未完成</a>";
        } else {
            return "&nbsp;&nbsp;<button type='button' class='btn btn-danger' onclick='inactive(\""+'/maintainSchedule/statusOperate?id='+row.workId+'&status=N'+"\")'>已完成</a>";
        }
    }

    function openAddSchedule(){
        initTableNotTollbar("addScheudleTable","/maintainRecord/queryByPager");
        // initTableNotTollbar("addScheudleTable","/maintainRecord/queryByPager");
        // initTable("maintainRecord","请选择保养记录编号","/maintainRecord/queryByPager");
        $("#addWindow").modal('hide');
        $("#AddScheduleWindow").modal('show');
    }

    function closeAddSchedule(){
        $("#AddScheduleWindow").modal('hide');
        $("#addWindow").modal('show');
    }

</script>
</body>
</html>
