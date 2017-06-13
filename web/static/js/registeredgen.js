/**
 * Created by 不曾有黑夜 on 2017/5/15.
 */
function reg() {
    var login =$("#login");
    var reg = $("#reg");
    var back = $("#background-img");
    if(login.css("display") == "block"){
        login.css("display","none");
        reg.css("display","block");
        back.css("background","url(/static/img/Frontpage/1112892.jpg)");
        $(".form-content form input").each(function () {
            $(this).val('');
        });
        document.title = "注册";
    }else if(reg.css("display") == "block"){
        reg.css("display","none");
        login.css("display","block");
        back.css("background","url(/static/img/Frontpage/43162.jpg)0 -80px");
        $(".form-content form input").each(function () {
            $(this).val('');
        });
        document.title = "账号登录";
    };

};
 /*回车登录*/
function keydown(){
     if(event.keyCode == 13){
         document.getElementById("loginButton").click();
     }
 }
 function keydownres(){
     if(event.keyCode == 13){
         document.getElementById("resbtn").click();
    }
 }


$(function () {
    function bodyScroll(event) {
        event.preventDefault();
    }

    document.body.addEventListener('touchmove', bodyScroll(event), false);
    document.body.removeEventListener('touchmove', bodyScroll(event), false);
});
function doNothing() {
    window.event.returnValue = false;
    return false;
}