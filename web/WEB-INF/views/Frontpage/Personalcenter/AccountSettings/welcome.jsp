<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/5/9
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/animate.css">
<link rel="stylesheet" href="/static/css/paging.css">
<link rel="stylesheet" href="/static/css/FactoryPageStyle.css">
<style>
    /*热门商家模块*/
    .showfactory{
        padding-top: 40px;
        margin: 15px 0;
        height: auto;
    }
    .factory{
        border: solid 1px #d3d3d3;
        width: 349px;
        height: 300px;
        margin: 15px;
        display: inline-block;
    }
    .factory:hover{
        border:solid #0090ff 1px;
        box-shadow: 4px 4px 7px #eee;
    }
    .factory .f-img{
        height: 50%;
        padding: 15px 20px;
    }
    .factory .f-img img{
        width: 100%;
        height: 100%;
    }
    .f-des{
        padding:15px 10px;
    }
    .company{
        margin-top: 15px;
    }

    .com-des,.com-address,.cns-a{
        text-decoration: none;
        color: black;
        display: inline-block;
        overflow: hidden;
        text-overflow: ellipsis;
        padding:0 15px;
        width:250px;
        height:30px;
        white-space: nowrap;
    }

    .icon-t{
        padding:0 14px;
    }
    .com-icon .icon-t i{
        display: inline-block;
        height: 25px;
        width: 25px;
        cursor: pointer;
    }
    .tuij{
        float: right;
        background: red;
        color: white;
        border-radius:5px ;
        padding: 4px 5px;
    }
    .ms,.hp{
        font-size: 20px;
        color: #a7aaab;
    }
    .hot-icon{
        background: url(/static/img/Frontpage/syicon-bg.png);
        background-position: -6px -329px;
        width: 30px;
        height: 30px;
        float: left;
        margin-right:5px;
    }

    .con{
        border:1px solid #d7d7d7;
    }
</style>
<body>
<div class="content" style="padding:15px 15px;margin: 0;background:#F0F0F0;height: auto;">
    <%--商家列表--%>
    <div class="showfactory" style="padding:0;margin: 0;">
        <div class="hot-factory">
            <div class="title">
                <h3>热门商家</h3>
            </div>
            <div class="row">
                    <c:forEach items="${requestScope.companys}" var="c">
                        <div class="factory col-md-4 col-sm-12">
                            <div class="f-img">
                                <a href="javaScript:;">
                                    <c:if test="${c.companyLogo != null}">
                                        <img src="${c.companyLogo}" alt="公司图片"/>
                                    </c:if>
                                    <c:if test="${c.companyLogo == null}">
                                        <img src="/static/img/Frontpage/u29.png" alt="logo">
                                    </c:if>
                                </a>
                            </div>
                            <div class="f-des">
                                <div class="company-name">
                                        <span class="cns">
                                            <span class="cns-a" href="javaScript:;" title="${c.companyName}">
                                                <i class="glyphicon glyphicon-bookmark"></i> <strong>公司名称：</strong>  ${c.companyName}
                                            </span>
                                            <div style="float: right;margin-right: 15px">
                                                <img src="/static/img/Frontpage/xun-lv.png"/>
                                                <img src="/static/img/Frontpage/xun-lv.png"/>
                                            </div>
                                            <div class="clearfix"></div>
                                        </span>
                                </div>
                                <div class="company-des">
                                        <span class="com-des" title="${c.companyDes}">
                                            <i class="glyphicon glyphicon-edit"></i> <strong>公司描述：</strong>  ${c.companyDes}
                                        </span>
                                </div>
                                <div class="company-address">
                                        <span class="com-address" title="${c.companyAddress}">
                                            <i class="glyphicon glyphicon-map-marker"></i> <strong>公司地址：</strong>  ${c.companyAddress}
                                        </span>
                                </div>
                                <span class="tuijian" title="预约">
                                    <a href="/appointmenting" style="width: auto;" class="btn btn-info btn-block">预约</a>
                                </span>
                            </div>
                        </div>
                    </c:forEach>
                </div>
        </div>
    </div>
</body>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.cxselect.min.js"></script>

</html>
