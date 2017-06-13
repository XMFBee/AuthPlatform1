/**
 * Created by 不曾有黑夜 on 2017/5/4.
 */
$(".paging-ul .paging-li ").each(function () {
    $(this).click(function () {
        $(".paging-ul .paging-li ").removeClass("active");
        $(".paging-ul .paging-li ").removeClass("active-two");
        $(this).addClass("active-two");
        return false;
    });
});
$(function () {
    var none = document.getElementById("wechat-right");
    var style = document.createElement('style');
    if (window.screen.width <= 456){
        none.style.display="none";
    }else{
        none.style.display="block";
    }

});


function verification(){
    var nameValue = document.getElementById("name").value;
    var phoneValue = document.getElementById("phone").value;
    var dateValue = document.getElementById("date").value;
    var timeValue = document.getElementById("time").value;
    var name = document.getElementById("name");
    var phone = document.getElementById("phone");
    var date = document.getElementById("date");
    var time = document.getElementById("time");
    var namepro = $("#name-pro");
    var namepro2 = $("#name-pro2");
    var namepro3 = $("#name-pro3");
    var phonepro = $("#phone-pro");
    var phonepro2 = $("#phone-pro2");
    var datepro = $("#time-pro");
    /*验证姓名框*/
    if(nameValue == null || nameValue == ""){
        namepro.addClass("show")
        name.style.borderColor = "red";
        return false;
    }else if(nameValue.length < 2 || nameValue.length >= 10){
        namepro.removeClass("show");
        namepro2.addClass("show");
        name.style.borderColor = "red";
        return false;

    }else if(/\w+/.test(nameValue)){
        namepro.removeClass("show")
        name.style.borderColor = "red";
        namepro3.addClass("show");
        return false;
        /*验证电话框*/
    }else if(phoneValue == null || phoneValue == ""){
        namepro.removeClass("show");
        namepro2.removeClass("show");
        namepro3.removeClass("show");
        name.style.borderColor = "#ccc";
        phonepro.addClass("show");
        phone.style.borderColor = "red";
        return false;
        /*判断格式*/

    }else if(!(/^1[3|5|8]\d{9}/.test(phoneValue))){
        phonepro.removeClass("show")
        phonepro2.addClass("show");
        phone.style.borderColor = "red";
        return false;
    }else if(dateValue == null || dateValue == "" || timeValue == null || timeValue == ""){
        namepro.removeClass("show");
        namepro2.removeClass("show");
        name.style.borderColor = "#ccc";
        phonepro.removeClass("show")
        phonepro2.removeClass("show");
        phone.style.borderColor = "#ccc";
        datepro.addClass("show")
        date.style.borderColor="red";
        time.style.borderColor="red";
        return false;
    }
    datepro.removeClass("show")
    date.style.borderColor="#ccc";
    time.style.borderColor="#ccc";
    return true;
};

function Conversion(){
    var by = $("#baoy");
    var wx = $("#weix");
    by.click(function () {
        wx.removeClass("choose");
        by.addClass("choose");
    });
    wx.click(function () {
        by.removeClass("choose");
        wx.addClass("choose");
    });

};

