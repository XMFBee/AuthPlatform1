<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/4/18
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>关于我们</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/animate.css">
<link rel="stylesheet" href="/static/css/Frontpage.css">
<link rel="stylesheet" href="/static/css/platform.css">
<body name="top">
    <%--


        下载页面


    --%>
    <div id="main">
        <%--导航栏--%>
        <nav class="nav navbar-default navbar-fixed-top" role="navigation"  style="">
            <div class="container-fluid">
                <div class="navbar-header">

                </div>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/home">主页</a></li>
                    <li class="icon-li"><a href="/reg">登录</a></li>
                    <li class="icon-li"><a href="/platformIntro">关于我们</a></li>
                    <li id="menu-bg"><a href="/factoryreg">汽车公司入驻</a></li>
                </ul>
            </div>
        </nav>
        <%--第二个导航栏--%>
        <div class="nav-two">
            <div class="container-nav">
                <ul class="nav-ul">
                    <a href="#windows-m"><li>项目简介</li></a>
                    <a href="#android-m"><li>公司简介</li></a>
                    <a href="#iphone-m"><li>常见问题</li></a>
                    <a href="#ipad-m"><li>诚聘英才</li></a>
                    <div class="clearfix"></div>
                </ul>
            </div>
        </div>
        <%--第一个内容--%>
        <div id="first" style="margin-top:100px;" name="windows-m">
            <div class="container">
                <div class="col-md-6 col-sm-6 content-left wow fadeInLeft animated" data-wow-delay="0.7s" >
                    <div class="first-left-bg">
                        <img style="width: 90%;" src="/static/img/Frontpage/pc-bg.png"/>
                    </div>
                </div>
                <div class="col-md-6 col-sm-6 content-right wow fadeInRight animated" data-wow-delay="0.7s" >
                    <div class="first-rt-txt">
                        <h1 style="font-size: 4em;"><p>简介</p></h1>
                        <ul class="des-txt" style="list-style: none;margin-left: 20px;">
                            <p>
                                "汽车维修管理平台"是一款面向汽修汽配行业的管理软件，本平台专注于车辆的信息化管理，
                                为加强企业对车辆维修的综合管理而提供全方位的解决方案，并且具有二次开发的独特特性，
                                独特的二次开发功能可以为新老用户在以后的使用过程中出现的新模块，新功能随时进行添加，
                                为用户的使用提供全面服务。
                            </p>
                            <p>
                                "汽车维修管理平台"以对车辆的维修和管理为主线， 通过对信息的收集、存储、传递、统计、分析、综合查询、报表输出和信息共享， 及时为企业领导及各部门管理人员的决策提供全面、准确的信息数据。
                                此汽车维修保养管理平台旨在提升汽修店的信息化水平及工作 效率，提供便捷有效的方式管理从维修保养预约到维修完成产生收费单据并提车的整个过程。并附带汽修店基本信息的管理，汽车配件的库存管理等功能。
                            </p>

                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <%--背景图--%>
        <div id="thirdBar">

        </div>
        <%--第二个内容--%>
        <div id="two" name="iphone-m">
            <div class="container">
                <div class="col-md-6 col-sm-6 content-left  wow fadeInLeft animated" data-wow-delay="0.7s" >
                    <div class="first-rt-txt">
                        <h1 style="font-size: 4em;">公司简介</h1>
                        <p style="font-size: 22px;"><strong>地址：</strong>江西赣州</p>


                    </div>
                </div>
                <div class="col-md-6 col-sm-6 content-right wow fadeInRight animated" data-wow-delay="0.7s" >
                    <div class="first-left-bg">
                        <img src="/static/img/Frontpage/iphone-green.png"/>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
        <%--背景图--%>
        <div id="fourthBar"></div>
        <%--第三个内容--%>
        <div id="three" name="android-m">
                <div class="container">
                    <div class="col-md-6 col-sm-6 content-left wow fadeInLeft animated" data-wow-delay="0.7s" >
                        <div class="first-left-bg">
                            <img src="/static/img/Frontpage/anzhuo-bg.png"/>
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 content-right wow fadeInRight animated" data-wow-delay="0.7s" >
                        <div class="first-rt-txt">
                            <h1 style="font-size: 4em;">常见问题</h1>
                            <p style="font-size: 22px;">如有疑问请咨询客服或前往<a href="/common">常见问题页面</a>进行了解</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        <div id="fifthBar"></div>
        <%--第四个内容--%>
        <div id="four" name="ipad-m">
                <div class="container">
                    <div class="col-md-6 col-sm-6 content-left wow fadeInLeft animated">
                        <div class="first-rt-txt">
                        </div>

                    </div>
                    <div class="col-md-6 col-sm-6 content-right wow fadeInRight animated">
                        <div class="first-left-bg">
                            <img src="/static/img/Frontpage/ipad-bg.jpg"/>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </div>
        <div id="sixthBar"></div>
        <%--底部版权--%>
            <div class="bottom-nav">
                <div class="row concart-warp">
                    <div class="col-md-5 col-sm-12 btm-left">
                        <h4>联系我们：</h4>
                        <p>
                            <i class="icon-phone"><img src="/static/img/Frontpage/phone.png"/></i>
                            <span class="phone">15570102341</span>
                        </p>
                        <p>E-mail:8318045@qq.com</p>
                        <p><a href="javaScript:;"><img src="/static/img/Frontpage/btm-left.png"></a></p>
                    </div>
                    <div class="col-sm-9 col-md-5 wechat">
                        <h3 style="margin: 0;">扫码关注</h3>
                        <div class="row sm ">
                            <div class="col-md-3 col-sm-3">
                            </div>
                            <div class="col-md-5 col-sm-5" style="text-align: center;">
                                <img src="/static/img/Frontpage/erweim.jpg" width="180px" height="140px"/>
                            </div>
                            <div class="col-md-3 col-sm-3">

                            </div>
                        </div>
                    </div>
                    <div class="col-md-2 col-sm-3 rt-img row">
                        <div class="col-md-12 col-sm-10 gz">
                            <h4 style="margin-bottom: 10px;">关注预约有好礼</h4>
                            <a href="/resepage" id="mc5-rtimg"><img src="/static/img/Frontpage/weixin.jpg" width="100px" height="100px"/></a>

                        </div>
                    </div>
                </div>
                <div class="t-bottom">
                    <span style="font-size: 19px;">© 2017-3017 神的坐骑 版权所有 ｜ 赣ICP备11018683-3</span>
                </div>
            </div>
    </div>
    <a href="#top" class="go-top" id="backtop" style="display:none;"></a>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/wow.js"></script>
<script src="/static/js/init.js"></script>
<script src="/static/js/general.js"></script>
<script>
    if (!(/msie [5|6|7|8|9]/i.test(navigator.userAgent))){
        new WOW().init();
    };

</script>
</body>

</html>
