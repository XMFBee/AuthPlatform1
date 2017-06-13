<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/5/8
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号信息</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<style>
    body,html{
        font-family:Microsoft YaHei,Arial,simsun, Helvetica, sans-serif;
    }
    *{
        padding:0;
        margin:0;
    }
    .form-box{
        width: 400px;
        height:auto;
        margin: 0px auto;
    }
    @media (max-width: 767px) {
        .form-box{
            width:100%;
        }
    }
    .info{
        margin-bottom: 15px;
    }
    .info p{
        display: inline-block;
        font-size: 18px;
    }

</style>
<body>
    <div class="main">
        <div class="form-box well" id="form-box1" >
            <form class="form-inline info-form" id="form" method="post">
                <div class="info">
                    <label>名称：</label>
                    <p>${sessionScope.frontUser.userName}</p>
                </div>

                <div class="info">
                    <label>电话：</label>
                    <p>${sessionScope.frontUser.userPhone}</p>
                </div>
                <div class="info">
                    <label>邮箱：</label>
                    <p>${sessionScope.frontUser.userEmail}</p>
                </div>
                <div class="info">
                    <label>地址：</label>
                    <p>${sessionScope.frontUser.userAddress}</p>
                </div>
                <div class="info">
                    <label>性别：</label>
                    <p>${sessionScope.frontUser.userGender}</p>
                </div>
                <div class="info">
                    <a class="btn btn-info btn-block" href="/showeditpage">修改</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
