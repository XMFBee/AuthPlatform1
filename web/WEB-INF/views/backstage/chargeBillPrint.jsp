<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>收费单据打印</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <style>
        .clear{
            clear:both;
        }
    </style>
</head>
<body>
<div class="row">
        <div class="col-sm-12" style="text-align: center">
            <h3><strong>收&nbsp;费&nbsp;单&nbsp;据</strong></h3>
        </div>

        <div class="col-sm-12" style="border: 1px solid black;height:350px;font-size: 20px; padding-top: 20px;">
            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">汽车公司 : </div>
                <div id="companyName" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                    <c:choose>
                        <c:when test="${chargeBill.maintainRecord.checkin.company.companyName != null}">
                            ${chargeBill.maintainRecord.checkin.company.companyName}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-1" style="width: 130px;margin-left:120px;display:inline;float:left;">收款方式 : </div>
                <div id="paymentMethod" class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                    <c:choose>
                        <c:when test="${chargeBill.chargeBillDes != null}">
                            ${chargeBill.chargeBillDes}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">总金额：</div>
                <div class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                    <c:choose>
                        <c:when test="${chargeBill.chargeBillMoney != null}">
                            ${chargeBill.chargeBillMoney}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-4" style="width: 130px;margin-left:120px;display:inline;float:left;">实际付款：</div>
                <div class="col-sm-4" style="border-bottom: 1px solid black;width:300px;display:inline;float:left;">
                    <c:choose>
                        <c:when test="${chargeBill.actualPayment != null}">
                            ${chargeBill.actualPayment}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="margin-bottom: 10px;padding-top:10px;">
                <div class="col-sm-1" style="width: 130px;display:inline;float:left;">收款事由 : </div>
                <div id="chargeBillDes" class="col-sm-10" style="border-bottom: 1px solid black;display:inline;float:left;width:850px;">
                    <c:choose>
                        <c:when test="${chargeBill.chargeBillDes!= null}">
                            ${chargeBill.chargeBillDes}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="float:right;">
                <div class="col-sm-7"></div>
                <div class="col-sm-2" style="display:inline;float:left;">公司电话 : </div>
                <div class="col-sm-1" style="border-bottom: 1px solid black;width:190px;display:inline;float:left;">
                    <c:choose>
                        <c:when test="${chargeBill.maintainRecord.checkin.company.companyTel != null}">
                            ${chargeBill.maintainRecord.checkin.company.companyTel}
                        </c:when>
                        <c:otherwise>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="float:right;">
                <div class="col-sm-7"></div>
                <div class="col-sm-2" style="display:inline;float:left;">公司地址 : </div>
                <div class="col-sm-1" style="border-bottom: 1px solid black;width:190px;display:inline;float:left;">
                <c:choose>
                    <c:when test="${chargeBill.maintainRecord.checkin.company.companyAddress != null}">
                        ${chargeBill.maintainRecord.checkin.company.companyAddress}
                    </c:when>
                    <c:otherwise>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </c:otherwise>
                </c:choose>
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="float:right;">
                <div class="col-sm-7"></div>
                <div class="col-sm-2" style="display:inline;float:left;">车主签字 : </div>
                <div class="col-sm-1" style="border-bottom: 1px solid black;width:100px;display:inline;float:left;">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                </div>
            </div>
            <p class="clear"></p>
            <div class="col-sm-12" style="float:right;">
                <div class="col-sm-7"></div>
                <div class="col-sm-2" style="display:inline;float:left;">单据日期 : </div>
                <div class="col-sm-1" style="width:230px;display:inline;float:left;">
                        <fmt:formatDate value="${chargeBill.chargeCreatedTime}" pattern="yyyy/MM/dd  HH:mm:ss"></fmt:formatDate>
                </div>
            </div>
        </div>
</div>
<script src="/static/js/jquery.min.js"></script>
<script>
    $(function () {
        window.print();
    });
</script>
</body>
</html>
