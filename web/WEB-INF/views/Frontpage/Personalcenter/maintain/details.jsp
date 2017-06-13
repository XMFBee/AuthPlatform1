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
    <title>维维修保养明细管理</title>
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

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true" data-field="status"></th>
                <th data-width="90" data-field="checkin.userName">
                    车主姓名
                </th>
                <th data-width="120" data-field="checkin.userPhone">
                    车主电话
                </th>
                <th data-width="90" data-field="checkin.brand.brandName">
                    汽车品牌
                </th>
                <th data-width="90" data-field="checkin.color.colorName">
                    汽车颜色
                </th>
                <th data-width="90" data-field="checkin.model.modelName">
                    汽车车型
                </th>
                <th data-width="90" data-field="checkin.plate.plateName">
                    汽车车牌
                </th>
                <th data-width="90" data-field="checkin.carPlate">
                    车牌号码
                </th>
                <th data-width="100" data-field="checkin.maintainOrFix">
                    保养&nbsp;|&nbsp;维修
                </th>
                <th data-width="90" data-field="checkin.ifClearCar" data-formatter="showYesOrNoFormatter">
                    是否洗车
                </th>
                <th data-width="150" data-field="checkin.carThings">
                    车上物品描述
                </th>
                <th data-width="150" data-field="checkin.intactDegrees">
                    汽车完好度描述
                </th>
                <th data-width="150" data-field="recordDes">
                    维修保养记录描述
                </th>
                <th data-width="160" data-field="startTime" data-formatter="formatterDate">维修保养开始时间</th>
                <th data-width="190" data-field="endTime" data-formatter="formatterDate">维修保养预估结束时间</th>
                <th data-width="190" data-field="actualEndTime" data-formatter="formatterDate">维修保养实际结束时间</th>
                <th data-width="190" data-field="recordCreatedTime" data-formatter="formatterDate">维修保养记录创建时间</th>
                <th data-width="190" data-field="pickupTime" data-formatter="formatterDate">维修保养车主提车时间</th>
                <th data-width="100" data-hide="all" data-field="currentStatus">
                    当前状态
                </th>
                <th data-width="90" data-field="recordStatus" data-formatter="showStatusFormatter">
                    记录状态
                </th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showDetail();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>查看明细
                </button>
        </div>
    </div>
</div>

<!-- 明细表格 -->
<div id="detailWindow" class="modal fade" aria-hidden="true" style="overflow-y:scroll" data-backdrop="static"
     keyboard:false>
    <div class="modal-dialog" style="width: 90%">
        <div class="modal-content">
            <div class="modal-body">
                <div class="modal-body">
                    <div id="ifConfirm" style="background: url('/static/img/userCornfirm.png')-50px -50px no-repeat;position: absolute;z-index:999;width: 250px;height: 250px;background-size:250px;display: none"></div>
                    <span class="glyphicon glyphicon-remove closeModal" data-dismiss="modal"></span>
                    <h3 class="m-t-none m-b">此维修保养记录下的所有明细</h3>
                    <table class="table table-hover" id="detailTable">
                        <thead>
                        <tr>
                            <th data-radio="true" data-field="status"></th>
                            <th data-width="100" data-field="maintainFix.maintainName">
                                项目名称
                            </th>
                            <th data-width="100" data-field="maintainFix.maintainHour">
                                项目公时
                            </th>
                            <th data-width="100" data-field="maintainFix.maintainOrFix">
                                维修|保养
                            </th>
                            <th data-width="100" data-field="maintainFix.maintainDes">
                                项目描述
                            </th>
                            <th data-width="100" data-field="maintainDiscount" data-formatter="formatterDiscount">
                                项目折扣
                            </th>
                            <th data-width="100" data-field="maintainFix.maintainMoney">
                                原价
                            </th>
                            <th data-width="110" data-field="maintainFix.maintainMoney"
                                data-formatter="formatterDiscountMoney">
                                折扣后价钱
                            </th>
                            <th data-width="100" data-field="mdcreatedTime" data-formatter="formatterDate">
                                明细创建时间
                            </th>
                        </thead>
                    </table>
                    <div id="detailToolbar" class="btn-group">
                        <button id="btn_conif" type="button" class="btn btn-default" onclick="showConrm();">
                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>确认明细
                        </button>
                    </div>
                <div class="modal-footer" style="overflow:hidden;">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
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
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script>
    $(function () {
         initTable('table', '/maintainRecord/queryByOwner'); // 初始化表格
    });

    // 折扣
    function formatterDiscount(value) {
        if(value >= 1) {
            return "无折扣";
        } else {
            var str = value.toString();;
            var str1 = str.split('.')[1];
            str1 += "折";
            return str1;
        }
    }

    // 折扣后价钱
    function formatterDiscountMoney(value, row, index){
        var disCount = row.maintainDiscount;
        return value * disCount;
    }

    var recordId = "";

    // 显示所有明细
    function showDetail() {
        var row = $('#table').bootstrapTable('getSelections');
        if (row.length > 0) {
            recordId = row[0].recordId;
            $("#detailWindow").modal('show');
            if (row[0].ifConfirm == 'Y') {
                // 把用户已签字盖章显示
                $("#ifConfirm").css("display", "block");
                $("#detailToolbar").css("display", "none");
            } else {
                $("#ifConfirm").css("display", "none");
                $("#detailToolbar").css("display", "block");
            }
            initTable1('detailTable', '/maintainDetail/queryByOwner/' + row[0].recordId + '');
        }
    }

    // 所有明细window的用户确认按钮
    function showConrm(){
        var roles = "车主";
        $.post("/isOwnerLogin/"+roles, function (data) {
            if(data.result == 'success'){
                tableData = $("#detailTable").bootstrapTable('getData');//获取表格的所有内容行
                if(tableData.length > 0){
                    swal(
                        {title:"",
                            text:"确定确定了吗? 将开始进行维修保养",
                            type:"warning",
                            showCancelButton:true,
                            confirmButtonColor:"#DD6B55",
                            confirmButtonText:"我确定",
                            cancelButtonText:"再考虑一下",
                            closeOnConfirm:false,
                            closeOnCancel:false
                        },function(isConfirm){
                            if(isConfirm){
                                userConfirm();// 点击确定确认
                            }else{
                                swal({title:"",
                                    text:"已取消",
                                    confirmButtonText:"确认",
                                    type:"error"})
                            }
                        })
                }else{
                    swal({
                        title:"",
                        text: "请先生成维修保养明细", // 主要文本
                        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type:"error"}) // 提示类型
                }
            }else if(data.result == 'notLogin'){
                swal({title:"",
                        text:data.message,
                        confirmButtonText:"确认",
                        type:"error"}
                    ,function(isConfirm){
                        if(isConfirm){
                            top.location = "/user/loginPage";
                        }else{
                            top.location = "/user/loginPage";
                        }
                    })
            }else if(data.result == 'notRole'){
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
            }
        });
    }


    // 点击确定确定用户已签字
    function userConfirm(){
        var roles = "车主";
        $.post("/isOwnerLogin/"+roles, function (data) {
            if(data.result == 'success'){
                var tableData = $("#detailTable").bootstrapTable('getData');//获取表格的所有内容行
                var tableDataLength = tableData.length;
                var ids = "";// 设置一个字符串
                $.each(tableData, function(index, value, item) {
                    if(ids == ""){// 假如这个字符串刚开始设置,
                        ids = "'"+tableData[index].maintainItemId+"'";// 则直接赋上0索引上的id属性
                    }else {
                        ids += ",'" + tableData[index].maintainItemId+"'"// 否则就加上逗号把rows里所有的id都赋给ids
                    }
                });
                $.post("/maintainDetail/userConfirm/"+recordId+"/"+ids+"/"+tableDataLength,function (data) {
                    if (data.result == "success") {
                        $("#ifConfirm").css("display","block");
                        $("#detailToolbar").css("display","none");
                        $('#table').bootstrapTable('refresh');
                        swal({
                            title: "",
                            text: "确定成功，正在指派技师为您的爱车进行施工， 请等待...",
                            type: "success",
                            confirmButtonText: "确认",
                        })
                    }else if (data.result == "fail") {
                        swal({title:"",
                            text:data.message,
                            confirmButtonText:"确认",
                            type:"error"})
                    }
                })
            }else if(data.result == 'notLogin'){
                swal({title:"",
                        text:data.message,
                        confirmButtonText:"确认",
                        type:"error"}
                    ,function(isConfirm){
                        if(isConfirm){
                            top.location = "/user/loginPage";
                        }else{
                            top.location = "/user/loginPage";
                        }
                    })
            }else if(data.result == 'notRole'){
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"})
            }
        });
    }

    function initTable1(tableId, url) {
        //先销毁表格
        $('#' + tableId).bootstrapTable('destroy');
        //初始化表格,动态从服务器加载数据
        $("#" + tableId).bootstrapTable({
            method: "get",  //使用get请求到服务器获取数据
            url: url, //获取数据的Servlet地址
            striped: false,  //表格显示条纹
            pagination: true, //启动分页
            pageSize: 10,  //每页显示的记录数
            pageNumber:1, //当前第几页
            pageList: [10, 15, 20, 25, 30],  //记录数可选列表
            showColumns: true,  //显示下拉框勾选要显示的列
            showRefresh: true,  //显示刷新按钮
            showToggle: true, // 显示详情
            strictSearch: true,
            clickToSelect: true,  //是否启用点击选中行
            uniqueId: "checkinId",                     //每一行的唯一标识，一般为主键列
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            toolbar : "#detailToolbar",// 指定工具栏
            sidePagination: "server", //表示服务端请求

            //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
            //设置为limit可以获取limit, offset, search, sort, order
            queryParamsType : "undefined",
            queryParams: function queryParams(params) {   //设置查询参数
                var param = {
                    pageNumber: params.pageNumber,
                    pageSize: params.pageSize,
                    orderNum : $("#orderNum").val()
                };
                return param;
            },
        });
    }
</script>
</body>
</html>
