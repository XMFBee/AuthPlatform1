<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>收费单据管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<body>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;"  >
        <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
        <!-- table里的所有属性在bootstrap-table.js的240行-->
        <table id="table" style="table-layout: fixed">
            <thead>
            <tr>
                <th data-radio="true" data-field="status"></th>
                <th data-width="90" data-field="maintainRecord.checkin.userName">车主姓名</th>
                <th data-width="130" data-field="maintainRecord.checkin.userPhone">车主手机</th>
                <th data-width="90" data-field="maintainRecord.checkin.brand.brandName">汽车品牌</th>
                <th data-width="90" data-field="maintainRecord.checkin.model.modelName">汽车车型</th>
                <th data-width="90" data-field="maintainRecord.checkin.color.colorName">汽车颜色</th>
                <th data-width="90" data-field="maintainRecord.checkin.plate.plateName">汽车车牌</th>
                <th data-width="90" data-field="maintainRecord.checkin.carPlate">车牌号码</th>
                <th data-width="180" data-field="maintainRecord.pickupTime" data-formatter="formatterDate">维修保养记录提车时间</th>
                <th data-width="150" data-field="maintainRecord.recordDes">维修保养记录描述</th>
                <th data-width="90" data-field="paymentMethod">付款方式</th>
                <th data-width="110" data-field="chargeBillMoney">总金额(元)</th>
                <th data-width="120" data-field="actualPayment">实际付款(元)</th>
                <th data-width="180" data-field="chargeTime" data-formatter="formatterDate">收费时间</th>
                <th data-width="180" data-field="chargeCreatedTime" data-formatter="formatterDate">收费单据创建时间</th>
                <th data-width="130" data-field="chargeBillDes">收费单据描述</th>
                    <th data-width="110" data-field="maintainRecord.checkin.company.companyName">
                        汽车公司
                    </th>
                <th data-width="100" data-hide="all" data-field="maintainRecord.currentStatus">
                    记录状态
                </th>
                <th data-width="130" data-field="chargeBillStatus" data-formatter="showStatusFormatter">收费单据状态</th>
                <th data-width="130" data-field="cdStatus" data-formatter="currentStatusFormatter">单据当前状态</th>
            </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showMoney();">
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>确认收费
                </button>
        </div>
    </div>
</div>

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/backstage/main.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
</body>
<script>
    $(function () {
        var roles = "车主";
        $.post("/isOwnerLogin/"+roles, function (data) {
            if(data.result == 'success'){
                initTable('table', '/charge/queryByOwner'); // 初始化表格
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
    });

    function currentStatusFormatter(value, row, index){
        if(value != 'N'){
            return "用户已确认";
        }else{
            return "用户未确认";
        }
    }

    function showMoney(){
        var roles = "车主";
        $.post("/isOwnerLogin/"+roles, function (data) {
            if(data.result == 'success'){
                var row =  $('#table').bootstrapTable('getSelections');
                if(row.length ==1) {
                    if(row[0].actualPayment != null && row[0].actualPayment != ""){
                        if(row[0].maintainRecord.currentStatus !='已收费'){
                            if(row[0].cdStatus !="N") {
                                swal({title:"",
                                    text:"您已确认过此收费单据， 请等待公司人员确认已经收到费用",
                                    confirmButtonText:"确认",
                                    type:"error"})
                            }else{
                                // 未用户确认
                                swal({
                                    title: "",
                                    text: "是否确认已付维修保养费用?",
                                    type: "warning",
                                    showCancelButton: true,
                                    confirmButtonColor: "#DD6B55",
                                    confirmButtonText: "我确定",
                                    cancelButtonText: "再考虑一下",
                                    closeOnConfirm: true,
                                    closeOnCancel: false
                                }, function (ifCor) {
                                    if (ifCor) {
                                        $.post("/charge/updateCurrent?id="+row[0].chargeBillId, function (data) {
                                            if(data.result == "success"){
                                                $('#table').bootstrapTable('refresh');
                                                swal({
                                                    title: "",
                                                    text: data.message,
                                                    confirmButtonText: "确认",
                                                    type: "success"
                                                });
                                            }else{
                                                swal({
                                                    title: "",
                                                    text: data.message,
                                                    confirmButtonText: "确认",
                                                    type: "error"
                                                });
                                            }
                                        });
                                    } else {
                                        swal({
                                            title: "",
                                            text: "已取消",
                                            confirmButtonText: "确认",
                                            type: "error"
                                        });
                                    }
                                })
                            }
                        }else{
                            swal({
                                title:"",
                                text: "此记录已完成确认收费!", // 主要文本
                                confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                                confirmButtonText:"确定", // 提示按钮上的文本
                                type:"warning"}) // 提示类型
                        }
                    }else{
                        swal({
                            title:"",
                            text: "此记录还未收到维修保养费用, 不能确认收费", // 主要文本
                            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type:"warning"}) // 提示类型
                    }
                }else{
                    swal({
                        title:"",
                        text: "请先选择要确认收费的记录且只能选择一条", // 主要文本
                        confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type:"warning"}) // 提示类型
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

</script>
</html>
