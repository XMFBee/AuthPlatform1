<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>修改密码</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
</head>
<style>
    body,html{
        font-family:Microsoft YaHei,Arial,simsun, Helvetica, sans-serif;
    }
    *{
        padding:0;
        margin:0;
    }
    .edit-box{
        margin:15px auto;
        width: 400px;
    }
    .gotoleft{
        margin-left: 13px;
    }
    @media(max-width: 767px) {
        .edit-box{
            width: 100%;
        }
        .gotoleft{
            margin-left: 0px;
        }
    }
    .editpwd{
        margin-bottom: 15px;
    }
    .form-box{
        padding:0 50px;
    }

</style>
<body>
<%@include file="../backstage/contextmenu.jsp" %>
<div class="main">
    <div class="edit-box">
        <form class="form-inline well" id="pwdForm" method="post">
            <div class="form-box">
                <div class="editpwd">
                    <label>旧密码：</label>
                    <input class="form-control gotoleft" name="oldPwd" type="password" placeholder="输入您的旧密码">
                </div>
                <div class="editpwd">
                    <label>新密码：</label>
                    <input class="form-control gotoleft" name="newPwd" type="password" placeholder="输入您的新密码">
                </div>
                <div class="editpwd">
                    <label>再次输入：</label>
                    <input class="form-control" name="conPwd" type="password" placeholder="再次输入您的新密码">
                </div>
                <div class="editpwd">
                    <button class="btn btn-success btn-block" type="button" onclick="editPwd()">确认</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/backstage/financialControlJS/incomeType.js"></script>
<script src="/static/js/bootstrap-select/bootstrap-select.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
<script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script src="/static/js/backstage/main.js"></script>
<script>
    function editPwd() {
        $.post("/user/updatePwd",$("#pwdForm").serialize(),function (data) {
            if(data.result=="success"){
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"}
                    ,function(isConfirm){
                        if(isConfirm){
                            top.location = "/user/loginPage";
                        }else{
                            top.location = "/user/loginPage";
                        }
                    })// 提示窗口, 修改成功
            }else if(data.result=="fail"){
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"error"})// 提示窗口, 修改成功
            }
        })
    }
</script>
</html>
