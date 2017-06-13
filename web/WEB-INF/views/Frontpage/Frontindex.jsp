<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>主页</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/animate.css">
<link rel="stylesheet" href="/static/css/FrontHome.css">
<style>
    /* 地图 开始 */
    #nearCompMap {width:100%; height:70%; overflow:hidden; margin:0;font-family:"微软雅黑";}
    /* 地图 结束 */
</style>
<body>
<%--

主页面

--%>
<div class="main" name="top">
    <div class="nav nav-first">
        <div class="nav-left">
            <ul class="nav-left-ul">
                <c:choose>
                    <c:when test="${sessionScope.frontUser != null}">
                        <c:if test="${sessionScope.frontUser.userName != null}">
                            <li id="placelogin">欢迎您，${sessionScope.frontUser.userName}</li>
                            <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                            <a href="/index" class="right-ul"><li>主页</li></a>
                            <a href="/outusers"><li>退出</li></a>
                            <div class="clearfix"></div>
                        </c:if>
                        <c:if test="${sessionScope.frontUser.userName == null}">
                            <li id="placelogin">欢迎您，${sessionScope.frontUser.userPhone}</li>
                            <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                            <a href="/index" class="right-ul"><li>主页</li></a>
                            <a href="/outusers"><li>退出</li></a>
                            <div class="clearfix"></div>
                        </c:if>
                    </c:when>

                    <c:otherwise>
                        <li id="placelogin">欢迎您，请登录</li>
                        <a href="/reg" id="loginreg"><li>登录/注册</li></a>
                        <a href="/index" class="right-ul"><li>主页</li></a>
                        <div class="clearfix"></div>
                    </c:otherwise>

                </c:choose>
            </ul>
            <div class="clearfix"></div>
        </div>

    </div>
    <div class="nav nav-two" id="navbar-two">
        <ul class="nav-two-ul">
            <a href="/home"><li class="actives">首页</li></a>
            <a href="/factorypage"><li>商家</li></a>
            <a href="/resepage"><li>预约</li></a>
            <a href="/common"><li>常见问题</li></a>
            <a href="/platformIntro"><li>关于我们</li></a>
        </ul>
    </div>
    <%--轮播图--%>
    <div class="car-carousel">
        <div class="carousel">
            <div class="car-choose">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播项目-->
                    <div class="carousel-inner">
                        <div class="item active">
                            <img src="/static/img/Frontpage/banner1.png">
                            <%--<div class="carousel-caption">标题 1</div>--%>
                        </div>
                        <div class="item ">
                            <img src="/static/img/Frontpage/582.jpg"/>
                        </div>
                        <div class="item">
                            <img src="/static/img/Frontpage/homeback1.jpg"/>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <button class="carousel-control left" href="#myCarousel" style="border: none;"
                            data-slide="prev"><span class="glyphicon glyphicon-chevron-left" style="left:0;width:200px;height: 200px;"></span></button>
                    <button class="carousel-control right" href="#myCarousel" style="border: none;"
                            data-slide="next"><span class="glyphicon glyphicon-chevron-right" style="left:0;width:200px;height: 200px;"></span></button>
                </div>
            </div>
        </div>

    </div>
    <%--主内容开始--%>
    <div class="content">
        <%--保养项目开始--%>
        <div class="product">
            <%--保养项目头部--%>
            <div class="preferences">
                <div class="pro-title">
                    <i class="title-bg"></i>
                    <h3>
                        <a href="javaScript:;" style="text-decoration: none;">保养项目</a>
                    </h3>
                </div>
                <div class="pro-content">
                    <div class="index_baoyang_wrap">
                        <div>
                            <a class="index-green" href="/resepage">
                                <span class="baoy-title">小保养</span>
                                <i class="jiage">￥ <span class="price-span">158</span> 起</i>
                            </a>
                        </div>
                        <div>
                            <a class="index-red" href="/resepage">
                                <span class="baoy-title">大保养</span>
                                <i class="jiage">￥ <span class="price-span">346</span> 起</i>
                            </a>
                        </div>
                        <div>
                            <a class="index-blue" href="/resepage">
                                <span class="baoy-title">更换空调滤清器</span>
                                <i class="jiage">￥ <span class="price-span">580</span> 起</i>
                            </a>
                        </div>
                        <div>
                            <a class="index-pink" href="/resepage">
                                <span class="baoy-title">更换刹车片</span>
                                <i class="jiage">￥ <span class="price-span">98</span> 起</i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <%--全部保养项目模块--%>
            <div class="index-main-wrap">
                <ul class="index-ul">
                    <li class="one-li">
                        <a href="/resepage">
                            <span>更换轮胎</span>
                        </a>
                    </li>
                    <li class="two-li">
                        <a href="/resepage">
                            <span>更换轮毂</span>
                        </a>
                    </li>
                    <li class="three-li">
                        <a href="/resepage">
                            <span>更换刹车油</span>
                        </a>
                    </li>
                    <li class="four-li">
                        <a href="/resepage">
                            <span>更换火花塞</span>
                        </a>
                    </li>
                    <li class="five-li">
                        <a href="/resepage">
                            <span>更换电瓶</span>
                        </a>
                    </li>
                    <li class="six-li">
                        <a href="/resepage">
                            <span>更换大灯/灯泡</span>
                        </a>
                    </li>
                    <li class="seven-li">
                        <a href="/resepage">
                            <span>更换变速箱油</span>
                        </a>
                    </li>
                    <li class="eight-li">
                        <a href="/resepage">
                            <span>更换空调制冷剂</span>
                        </a>
                    </li>
                    <li class="nine-li">
                        <a href="/resepage">
                            <span>更换刹车盘</span>
                        </a>
                    </li>
                    <li class="ten-li">
                        <a href="/resepage">
                            <span>更换防冻冷却液</span>
                        </a>
                    </li>
                    <li class="eleven-li">
                        <a href="/resepage">
                            <span>进排气系统养护</span>
                        </a>
                    </li>
                    <li class="twelve-li">
                        <a href="/resepage">
                            <span>更换助力转向油</span>
                        </a>
                    </li>
                    <li class="thirteen-li">
                        <a href="/resepage">
                            <span>更换正时皮带</span>
                        </a>
                    </li>
                    <li class="fourteen-li">
                        <a href="/resepage">
                            <span>更换外部皮带</span>
                        </a>
                    </li>
                    <li class="fifteen-li">
                        <a href="/resepage">
                            <span>更换减震器</span>
                        </a>
                    </li>
                    <li class="sixteen-li">
                        <a href="/resepage">
                            <span>燃油系统养护</span>
                        </a>
                    </li>
                    <li class="seventeen-li">
                        <a href="/resepage">
                            <span>发动机内部养护</span>
                        </a>
                    </li>
                    <li class="eighteen-li">
                        <a href="/resepage">
                            <span>空调系统养护</span>
                        </a>
                    </li>
                    <div class="clearfix"></div>
                </ul>
            </div>
        </div>
        <%--入驻商家--%>
        <div class="showfactory">
            <div class="hot-factory">
                <div class="title">
                    <span class="hot-icon"></span>
                    <h3><a href="/factorypage" style="text-decoration: none;">热门商家</a></h3>
                    <div class="clearfix"></div>
                </div>
                <c:if test="${requestScope.company != null}">
                    <c:forEach items="${requestScope.company}" var="c">
                        <div class="factory">
                            <div class="f-img">
                                <a href="/factorypage">
                                    <c:if test="${c.companyLogo != null}">
                                        <img src="/${c.companyLogo}" alt="公司图片"/>
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
                                        <i class="glyphicon glyphicon-bookmark"></i> <strong>公司名称：</strong> ${c.companyName}
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
                                    <i class="glyphicon glyphicon-edit"></i> <strong>公司描述： </strong>${c.companyDes}
                                </span>
                                </div>
                                <div class="company-address">
                                <span class="com-address" title="${c.companyAddress}">
                                    <i class="glyphicon glyphicon-map-marker"></i> <strong>公司地址： </strong>${c.companyAddress}
                                </span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.company == null}">
                    <h2 style="text-align: center;">暂无商家</h2>
                </c:if>
            </div>
        </div>
        <%--最近成交--%>
        <div class="content-first">
            <div class="recent-title">
                <i class="title-bg"></i>
                <h3><a href="javaScript:;" style="text-decoration: none;">最近维修保养</a></h3>
            </div>
            <%--<div class="search-box">--%>
                <%--<form method="" name="search">--%>
                    <%--<div class="form-search" style="text-align: right;">--%>
                        <%--<input id="phone" type="text" class="input-text" placeholder="输入手机号，查看维修保养记录"/>--%>
                        <%--<button type="button" class="btn" id="sendbutton">--%>
                            <%--<span>--%>
                                <%--发送短信验证码--%>
                            <%--</span>--%>
                        <%--</button>--%>
                        <%--<span id="codeprompt" style="font-size: 20px;color: red;display: none;margin-left: 15px;">手机号错误</span>--%>
                        <%--<input id="phonecodeinput" type="text" class="input-text" onchange="" placeholder="输入短信验证码" style="margin-left: 15px;width: 200px;display:none;">--%>
                    <%--</div>--%>
                <%--</form>--%>
            <%--</div>--%>
            <div class="con">
                <div class="acc-head">
                    <ul>
                        <li>
                            <span style="width:20%;margin-left:15px;">保养项目</span>
                            <span style="width:33%;">车型</span>
                            <span style="width: 20%;">费用</span>
                            <span style="width: 16%;">保养时间</span>
                        </li>
                    </ul>
                </div>
                <div class="acc-content">
                    <ul style="margin: 0;padding: 0;">
                        <c:choose>
                            <c:when test="${queryList != null}">
                                <c:forEach items="${queryList}" var = "m">
                                    <li>
                                        <span style="width:20%;margin-left:19px;padding-right: 15px;">${m.maintainFix.maintainName}</span>
                                        <span style="width:33%;padding-right: 15px;">${m.maintainRecord.checkin.model.modelName}</span>
                                        <span style="width: 20%;">${m.maintainFix.maintainMoney}</span>
                                        <span style="width: 16%;"><fmt:formatDate value="${m.mdcreatedTime}" pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate></span>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                暂无最新维修保养记录
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <%--底部模块--%>
    <div class="index-bottom" style="background: white;border-top:solid 1px #9d9d9d;">
        <div class="bottom-main">
            <div class="bottom-bs">
                <div class="bg"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">原厂品质正品配件</div>
                    <div class="btm-des">
                        <span>深厚的产业背景和厂商资源，严格控制进货渠道，杜绝一切假冒伪劣配件</span>
                    </div>
                </a>
            </div>
            <div class="bottom-bs">
                <div class="bg-two"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">保养省钱，安心便捷</div>
                    <div class="btm-des">
                        <span>优惠的配件价格，合理的安装人工费，汽车保养省钱之道</span>
                    </div>
                </a>
            </div>
            <div class="bottom-bs">
                <div class="bg-three"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">汽车保养专家系统</div>
                    <div class="btm-des">
                        <span>要保养什么，用什么配件，用多少？智能汽车保养专家系统，让您轻松养车</span>
                    </div>
                </a>
            </div>
            <div class="bottom-bs">
                <div class="bg-four"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">全里程保养保障</div>
                    <div class="btm-des">
                        <span> 针对汽车从上路到报废整个期间所有需要保养的项目都可以提供全面服务</span>
                    </div>
                </a>
            </div>
            <div class="bottom-bs">
                <div class="bg-five"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">线下安装服务承诺</div>
                    <div class="btm-des">
                        <span>到店安装、特约安装店全部经过严格筛选，定期对技术资质和服务能力评估</span>
                    </div>
                </a>
            </div>
            <div class="bottom-bs">
                <div class="bg-six"></div>
                <a href="javaScript:;" style="text-decoration: none;">
                    <div class="btm-title">汽车保养服务保障</div>
                    <div class="btm-des">
                        <span>从养车无忧网购买配件到特约安装店更换，出现任何问题，统一协调解决</span>
                    </div>
                </a>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="bottom-nav">
            <div class="row concart-warp">
                <div class="col-md-5 col-sm-12 btm-left">
                    <h4>联系我们：</h4>
                    <p>
                        <i class="icon-phone"><img src="/static/img/Frontpage/phone.png"/></i>
                        <span class="phone">15570102341</span>
                    </p>
                    <p>E-mail:8318045@qq.com</p>
                    <p><a href="tencent://message/?uin=8318045&Site=&Menu=yes"><img src="/static/img/Frontpage/btm-left.png"></a></p>
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
                <span style="font-size: 19px;color: #000;">© 2017-3017 神的坐骑 版权所有 ｜ 赣ICP备11018683-3</span>
            </div>
        </div>
    </div>
    <%-- 地图开始 --%>
    <div class="show-map" title="附近汽车公司"><div></div></div>
    <%-- 地图结束--%>
    <a href="#top" class="go-top" id="backtop" style="display:none;"></a>
</div>


<!-- 地图 开始-->
<div class="modal fade" id="mapModal" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog" style="heigth:70%">
        <div class="modal-content" style="heigth:100%">
            <div class="modal-header" style="overflow:auto;">
                <span  class="close" data-dismiss="modal">&times;</span>
                <h4 data-tit = "title">附近的汽车公司</h4>
            </div>
            <div class="modal-content" style="overflow:auto;" style="heigth:80%">
                <div id="nearCompMap" style="display:none"></div>
            </div>
        </div>
    </div>
</div>
<!--   地图 结束 -->

<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.cxselect.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=51SKZww5ttdlF6C387IPRh2tmHllmwm7"></script>
<script type="text/javascript" src="/static/js/backstage/systemManage/nearCompMap.js"></script>
<script>

    $(document).ready(function(){
        $(".nav-two-ul a li").each(function(){
            $this = $(this);
            if($this[0].href==String(window.location)){
                $this.addClass("actives");
            }
        });

        $(function () {
            var navbar = $("#navbar-two");
            $(window).scroll(function () {
                if($(window).scrollTop() > 21){
                    if(navbar.css("position") !="fixed"){
                        navbar.css({ 'position': 'fixed',top:0,width:1349,zIndex:9999})
                    };
                }else if(navbar.css("position") != "static"){
                    navbar.css("position","static");
                }
            }) ;
        });

        $(function(){
            var backtop = document.getElementById("backtop");
            $(window).scroll(function () {
                if($(window).scrollTop() >= 500){
                    backtop.style.display = "block";
                }else if($(window).scrollTop() <500){
                    backtop.style.display = "none";
                }
            });
        });
        $(function(){
            $('a[href*=#],area[href*=#]').click(function() {
                if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
                    var $target = $(this.hash);
                    $target = $target.length && $target || $('[name=' + this.hash.slice(1) + ']');
                    if ($target.length) {
                        var targetOffset = $target.offset().top;
                        $('html,body').animate({
                                scrollTop: targetOffset
                            },
                            1000);
                        return false;
                    }
                }
            });
        });
        $("#sendbutton").click(function () {
            var phone = document.getElementById("phone");
            var prompt = document.getElementById("codeprompt");
            var phonecodeinput = document.getElementById("phonecodeinput");
            if(phone.value == null || phone.value == ""){
                prompt.innerHTML = "手机号为空";
                prompt.style.display ="inline-block";
            }else if(!(/^1[34578]\d{9}$/).test(phone.value)){
                prompt.innerHTML = "手机号错误";

            }else{
                prompt.style.display = "none";
                phonecodeinput.style.display = "inline-block";
            }
        })

        $(document).keydown(function () {
            if(event.keyCode == 13){
                $("#sendbutton").click();
            }
        });
    });

</script>


</body>
</html>

