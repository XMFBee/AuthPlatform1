<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/5/8
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/sweetalert.css">
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
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script>
    function editPwd() {
        $.post("/updatePwd",$("#pwdForm").serialize(),function (data) {
            if(data.result=="success"){
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "success"
                })
            }else{
                swal({
                    title: "",
                    text: data.message,
                    confirmButtonText: "确认",
                    type: "error"
                })
            }
        })
    }
</script>
</html>
