var map;
var point;
var mapLeave = 12;
var outPanelBaseOpts = {
    width : 250,     // 信息窗口宽度
    enableMessage:true//设置允许信息窗发送短息
}
var iconSize ;
var companyIcon ;
$(function () {
    if(window.BMap) {
        showMap();
    } else {
        hasErrorRemoveSomething();
    }
})


function getCurrLngAndLog() {
    var geolocation = new BMap.Geolocation();
    geolocation.getCurrentPosition(function(r){
        if(this.getStatus() == BMAP_STATUS_SUCCESS){
            point = new  BMap.Point(r.point.lng,r.point.lat );
            map.centerAndZoom(point, mapLeave);
            map.panTo(r.point);
            resetMapBound(point);
        } else {
            hasErrorRemoveSomething();
        }
    },{enableHighAccuracy: true});
}


function hasErrorRemoveSomething() {
    $("#mapModal").remove();
    $(".show-map").remove();
}

function initMap(mapId) {
    map = new BMap.Map(mapId);
    map.enableScrollWheelZoom(true);
}
function resetMapBound(point) {
    setSelfOverlays(point);
    setMapCompanyMarkers(map);
}

function setMapCompanyMarkers(map) {
    var bs = map.getBounds();   //获取可视区域
    var bssw = bs.getSouthWest();   //可视区域左下角
    var bsne = bs.getNorthEast();   //可视区域右上角
    var maxLng =  bsne.lng;
    var maxLat = bsne.lat;
    var minLng = bssw.lng;
    var minLat = bssw.lat;
    $.get("/materials/nearCompanys?maxLng="+maxLng+"&maxLat="+maxLat+"&minLng="+minLng+"&minLat="+minLat, function (data) {
        var companys = data;
        setCompanyMakes2Map(map, companys);
    })
}

function setCompanyMakes2Map(map, companys) {
    if(companys){
        for(var i = 0, len = companys.length;  i<len; i++) {
            var company = companys[i];
            var point = getCompanyPoint(company);
            setCompanyMake2Map(point, company);
        }
    }
}

function setCompanyMake2Map(point, company) {
    var marker =  new BMap.Marker(point);
    var winContent = getCompanyMsgContent(company);
    addClickHandler(winContent, marker);
    map.addOverlay(marker);
}

function getCompanyMsgContent(company){
    var companyHtml = [];
    companyHtml.push("<div>");
    companyHtml.push("<input name='companyId' value="+ company.companyId +" style='display:none'/>")
    companyHtml.push("<div class='head' style='font-weight: bold';>");
    companyHtml.push(company.companyName);
    companyHtml.push("</div>");

    companyHtml.push("<div>");
    companyHtml.push("<span class='infoTitle'>简介: </span><span class='msg'>" + company.companyDes + "</span>");
    companyHtml.push("</div>");

    companyHtml.push("<div>");
    companyHtml.push("<span class='infoTitle'>规模: </span><span class='msg'>" + company.companySize + " /人</span>");
    companyHtml.push("</div>");

    companyHtml.push("<div>");
    companyHtml.push("<span class='infoTitle'>联系方式: </span><span class='msg'>" + company.companyTel+ "</span>");
    companyHtml.push("</div>");

    // companyHtml.push("<div>");
    // companyHtml.push("<a style='float:right;'><span class='glyphicon glyphicon-edit'></span><span style='margin-left:10px'>预约</span></a>");
    // companyHtml.push("</div>");

    companyHtml.push("</div>");
    return companyHtml.join("");
}

function getCompanyPoint(company) {
    return new BMap.Point( company.companyLongitude ,  company.companyLatitude );
}


function clearOverlays(){
    map.clearOverlays();
}

function setSelfOverlays(point) {
    var marker = new BMap.Marker(point, {icon:companyIcon});  // 创建标注
    map.addOverlay(marker);
    return marker;
}

function addClickHandler(content,marker){
    marker.addEventListener("click",function(e){
        openInfo(content,e)}
    );
}

function openInfo(content,e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var infoWindow = new BMap.InfoWindow(content,outPanelBaseOpts);  // 创建信息窗口对象
    map.openInfoWindow(infoWindow,point); //开启信息窗口
}

function addMapEvent() {
    map.addEventListener("dragend", function (e) {
        clearOverlays();
        resetMapBound(point);
    });

    map.addEventListener("zoomend", function (e) {
        clearOverlays();
        resetMapBound(point);
    });
}

function showMap() {
    console.log("shoMap");
    $(".show-map").on("click",clicFun);
}
function clicFun(){
    $("#nearCompMap").css("display", "block");
    $("#mapModal").modal("show");
    if(!map) {
        iconSize = new BMap.Size(20, 28);
        companyIcon = new BMap.Icon("/static/img/marker_green_sprite.png", iconSize);
        initMap("nearCompMap");
        getCurrLngAndLog();
        addMapEvent();
    }
}

