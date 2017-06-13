// 点击刷新按钮
function itemOnclik1(){
    window.parent.refresh(window.name);
}

// 点击关闭当前页
function itemOnclik2(){
    window.parent.closeThis();
}

// 点击关闭其他页面
function itemOnclik3(){
    window.parent.closeOther();
}

// 点击关闭全部页面
function itemOnclik4(){
    window.parent.closeAll();
}

var menu = document.getElementById("itemMenu");
document.oncontextmenu = function(e) {
    var e = e || window.event;
    //鼠标点的坐标
    var oX = e.clientX;
    var oY = e.clientY;
    //菜单出现后的位置
    menu.style.display = "block";
    menu.style.position = 'absolute'; // 为新创建的DIV指定绝对定位
    if(oX > 980){
        oX = 980;
    }
    if(oY > 350){
        oY = 350;
    }
    menu.style.left = oX + "px";
    menu.style.right = "";
    menu.style.top = oY + "px";
    //阻止浏览器默认事件
    return false;//一般点击右键会出现浏览器默认的右键菜单，写了这句代码就可以阻止该默认事件。
}
document.onclick = function(e) {
    var e = e || window.event;
    menu.style.display = "none"
}
menu.onclick = function(e) {
    var e = e || window.event;
    e.cancelBubble = true;
}