<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/5/24
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>常见问题</title>
</head>
<link rel="stylesheet" href="/static/css/jquery.fullPage.css">
<link rel="stylesheet" href="/static/css/animate.css">
<link rel="stylesheet" href="/static/css/FrontHome.css">
<style>
    *{
        padding:0;
        margin:0;
        font-family: 'microsoft Yahei','Open Sans', sans-serif;
    }
    #one{
        background: url(/static/img/Frontpage/sg-universe.jpg)no-repeat;
    }
    #two{
        background: url(/static/img/Frontpage/sg-sky.jpg)no-repeat;
    }
    #three{
        background: url(/static/img/Frontpage/sg-sea.jpg)no-repeat;
    }
    #four{
        background: url(/static/img/Frontpage/sg-seabtm.jpg)no-repeat;
    }

    .company{
        position: relative;
        top: 50px;
        left: 100px;
        color: white;

    }
    h2{
        margin:15px 0;
    }
    p{
        font-size:22px;
    }
    .users,.admin{
        margin-top: 10%;
        margin-left: 5%;
        color: #fff;
    }
</style>
<body>
    <div id="fullpage">
        <div class="fp-section" id="one">
            <div class="nav nav-two" style="background:rgba(0,0,0,0.3);" id="navbar-two">
                <ul class="nav-two-ul wow fadeInLeft animated">
                    <a href="/home"><li>首页</li></a>
                </ul>
            </div>
            <div class="company wow fadeInRight animated">
                <div style="margin-left: 200px">
                    <h2>我是汽修公司，如何入驻?</h2>
                    <p>答：您可以在主页中的导航栏中的“汽修公司入驻”中选择入驻。填入您的信息和您公司的信息即可完成入驻。</p>
                    <h2>入驻之后可以做什么？有什么好处</h2>
                    <p>答：入驻本平台之后本平台的用户有维修保养会在本平台入住的公司选择。让您的公司做到了网上推广的效果。</p>
                    <h2>入驻之后会收费吗？</h2>
                    <p>答：本平台还在测试阶段，短时间内完全免费。</p>
                    <h2>完成用户的维修保养之后的分成是多少</h2>
                    <p>答：您一我九，本平台只收取9成的介绍费，不会多收的。</p>
                </div>
            </div>
        </div>
        <div class="fp-section" id="two">
            <div class="users">
                <div style="margin-left: 200px">
                    <h2>我是车主用户，如何注册、登录?</h2>
                    <p>答：您可以在主页或首页中的顶部导航栏中的“登录/注册”中选择登录或注册。填入基本信息完成登录或注册。</p>
                    <h2>注册之后可以做什么？有什么好处</h2>
                    <p>答：注册之后您就可以登录本平台。</p>
                    <h2>登录之后可以做什么，有什么好处？</h2>
                    <p>答：登录本平台，您可以去个人中心或微信登录查看预约，添加预约等等。。</p>
                    <h2>你们不会和汽修公司合伙坑用户吧，完成维修保养之后你们不会有分成吧？</h2>
                    <p>答：放心的亲，我们是以客户为上帝，客户就是衣食父母，我们怎么会坑爹呢。</p>
                </div>
            </div>
        </div>
        <div class="fp-section" id="three">
            <div class="admin">
                <div style="margin-left: 200px">
                    <h2>我是公司管理员，入驻之后该怎么办?</h2>
                    <p>答：入驻之后您可以在用户来您汽修店做维修保养时全程都是现代化计算机管理，让您高大上起来（虽然这破系统有很多BUG）</p>
                    <h2>管理后台我不会用怎么办？</h2>
                    <p>答：有什么不懂得地方您可以打我们的咨询热线，虽然有可能会打不通，如果万一打通了。我们会很热心的帮助您的。</p>
                    <h2>这系统为什么有好多BUG，还有好多逻辑走不通？</h2>
                    <p>答：本平台还在测试阶段，有BUG是很正常的。</p>
                    <h2>这个系统的售价是多少呢？</h2>
                    <p>答：本平台还在测试阶段，永久超级VIP ￥1999元</p>
                </div>
            </div>
        </div>
        <div class="fp-section" id="four">
            <div style="margin-left: 200px">
            </div>
        </div>
    </div>
</body>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/jquery.fullPage.js"></script>
<script src="/static/js/jquery-ui.js"></script>
<script src="/static/js/wow.js"></script>
<script>
    if (!(/msie [5|6|7|8|9]/i.test(navigator.userAgent))){
        new WOW().init();
    };
    $(function () {
        $("#fullpage").fullpage({
            navigation:'true',
            loopBottom:'true'
        })
    })
</script>
</html>
