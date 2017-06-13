<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 不曾有黑夜
  Date: 2017/4/26
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商家列表</title>
</head>
<link rel="stylesheet" href="/static/css/bootstrap.css">
<link rel="stylesheet" href="/static/css/animate.css">
<link rel="stylesheet" href="/static/css/TopBtm.css">
<link rel="stylesheet" href="/static/css/paging.css">
<link rel="stylesheet" href="/static/css/FactoryPageStyle.css">
<body>
    <%--

        厂家列表页面

    --%>
    <div class="main">
        <div class="nav nav-first">
            <div class="nav-left">
                <ul class="nav-left-ul">
                    <c:choose>
                        <c:when test="${sessionScope.frontUser != null}">
                            <c:if test="${sessionScope.frontUser.userName != null}">
                                <li id="placelogin">欢迎您，${sessionScope.frontUser.userName}</li>
                                <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                                <a href="/outusers"><li>退出</li></a>
                                <div class="clearfix"></div>
                            </c:if>
                            <c:if test="${sessionScope.frontUser.userName == null}">
                                <li id="placelogin">欢迎您，${sessionScope.frontUser.userPhone}</li>
                                <a href="/userpage" class="right-ul"><li>我的中心</li></a>
                                <a href="/outusers"><li>退出</li></a>
                                <div class="clearfix"></div>
                            </c:if>
                        </c:when>

                        <c:otherwise>
                            <li id="placelogin">欢迎您，请登录</li>
                            <a href="/reg" id="loginreg"><li>登录/注册</li></a>
                            <div class="clearfix"></div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="nav nav-two" id="navbar-two">
            <ul class="nav-two-ul">
                <a href="/home"><li>首页</li></a>
                <a href="/factorypage"><li class="actives">商家</li></a>
                <a href="/resepage"><li>预约</li></a>
                <a href="/common"><li>常见问题</li></a>
                <a href="/platformIntro"><li>关于我们</li></a>
            </ul>
        </div>
        <%--主内容区--%>
        <div class="content">
            <div class="search-box">
                <form method="get" name="search">
                    <div class="form-search">
                        <input type="text" class="input-text" placeholder="请输入车型、品牌或商家关键字"/>
                        <button type="submit" class="btn">
                            <span>
                                搜索
                            </span>
                        </button>
                    </div>
                </form>
            </div>
            <%--商家列表--%>
            <div class="showfactory">
                <div class="hot-factory">
                    <div class="title">
                        <span class="hot-icon"></span>
                        <h3><a href="/factorypage" style="text-decoration: none;">商家大全</a></h3>
                        <%--<div class="sorting">
                            <a id="opens" data-toggle="dropdown" href="javaScript:;" style="text-decoration: none;">按评分排序 <span class="glyphicon glyphicon-chevron-down"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="opens">
                                <li>
                                    <a href="javaScript:;">从高到低</a>
                                </li>
                                <li>
                                    <a href="javaScript:;">从低到高</a>
                                </li>
                            </ul>
                        </div>--%>
                        <div class="clearfix"></div>
                    </div>
                <c:choose>
                    <c:when test="${requestScope.companypage == null} ">
                        <h1>暂无数据</h1>
                    </c:when>
                    <c:when test="${requestScope.companypage != null}">
                        <c:forEach items="${requestScope.companypage}" var="c">
                            <div class="factory">
                                <div class="f-img">
                                    <a href="/company/queryById?companyId=${c.companyId}">
                                        <img src="${c.companyLogo}"/>
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
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>

                </c:choose>
                </div>
            </div>
            <%--分页--%>
            <div class="container large">
                <div class="pagination">
                    <ul class="paging-ul">
                        <li class="paging-li"><a href="#">上一页</a></li>
                        <li class="active paging-li"><a href="#"></a></li>
                        <li class="paging-li"><a href="#"></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <%--底部模块--%>
        <div class="index-bottom" style="background: #fff;">
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
                    <div class="col-md-5 col-sm-12 btm-left" style="padding-left: 110px;">
                        <h4>联系我们：</h4>
                        <p>
                            <i class="icon-phone"><img src="/static/img/Frontpage/phone.png"/></i>
                            <span class="phone">15570102341</span>
                        </p>
                        <p>E-mail:8318045@qq.com</p>
                        <p><a href="tencent://message/?uin=8318045&Site=&Menu=yes"><img src="/static/img/Frontpage/btm-left.png"></a></p>
                    </div>
                    <div class="col-sm-9 col-md-5 wechat">
                        <h3 style="margin-left: 55px;">扫码关注</h3>
                        <div class="row sm ">
                            <div class="col-md-5 col-sm-5" style="text-align: center;">
                                <img src="/static/img/Frontpage/erweim.jpg" width="180px" height="140px"/>
                            </div>
                            <div class="col-md-3 col-sm-3">
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
        <a href="#top" class="go-top" id="backtop" style="display:none;"></a>
    </div>
</body>
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/jquery.cxselect.min.js"></script>
<script src="/static/js/general.js"></script>
<script>
    function test(companyId) {
        $.get("/company/queryById?companyId="+companyId);
    }
</script>
</html>
