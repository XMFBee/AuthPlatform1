<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/5/11
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账号信息</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/sweetalert.css">
<link rel="stylesheet" href="/static/css/city-picker/city-picker.css">
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
    .content{
        padding:0 50px;
    }
    .select{
        margin: 10px 0;
        display: block;
    }
</style>
<body>
    <div class="main">
        <div class="form-box well" id="form-box2">
            <form class=" info-form" id="form2" method="get">
                <input type="hidden" name="userId" value="${sessionScope.frontUser.userId}">
                <div class="content">
                    <div class="info">
                        <label>名称：</label>
                        <input class="form-control" name="userName" type="text" value="${sessionScope.frontUser.userName}">
                    </div>

                    <div class="info">
                        <label>电话：</label>
                        <input class="form-control" name="userPhone" type="text" value="${sessionScope.frontUser.userPhone}">
                    </div>
                    <div class="info">
                        <label>邮箱：</label>
                        <input class="form-control" name="userEmail" type="text" value="${sessionScope.frontUser.userEmail}">
                    </div>
                    <div class="info">
                        <label>地址：</label>
                        <div style="position: relative;">
                            <input data-toggle="city-picker" id="address" type="text" name="userAddress" value="${sessionScope.frontUser.userAddress}"/>
                        </div>
                    </div>
                    <div class="info">
                        <label>性别：</label>
                        <select style="width: 30%" name="userGender">
                            <c:if test="${sessionScope.frontUser.userGender == '男'}">
                                <option value="男">
                                    男
                                </option>
                            </c:if>
                            <c:if test="${sessionScope.frontUser.userGender == '女'}">
                                <option value="女">
                                    女
                                </option>
                            </c:if>
                        </select>
                    </div>
                    <div class="info">
                        <a class="btn btn-danger btn-block" onclick="edit()">确认</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script src="/static/js/jquery.min.js"></script>
    <script src="/static/js/jquery.cxselect.js"></script>
    <script src="/static/js/sweetalert/sweetalert.min.js"></script>
    <script src="/static/js/city-picker/city-picker.data.js"></script>
    <script src="/static/js/city-picker/city-picker.js"></script>
</body>
<script>
    initCityPicker("address");//初始化三级地区联动

    function edit() {

        $.post("/editinfomation",$("#form2").serialize(),function (data) {
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
