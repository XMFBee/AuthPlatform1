/**
 *
 * Created by 不曾有黑夜 on 2017/4/19.
 */
$(document).ready(function(){
    var isIE=!!window.ActiveXObject;
    var isIE6=isIE&&!window.XMLHttpRequest;
    $window = $(window);
    var clientHeight = $window.height();
    var firstHeight = 934;
    var topHeight = 81;
    var secondHeight = 82;
    var scrollTop = $window.scrollTop();

    var secondTop = (clientHeight - secondHeight - scrollTop)<0?0:(clientHeight - secondHeight - scrollTop);
    var contentHeight = 770;
    var barHeight = 600;
    var thirdScroll = clientHeight < (topHeight + firstHeight)? contentHeight : (contentHeight - clientHeight + topHeight + firstHeight);
    var fourthScroll = thirdScroll + contentHeight + barHeight;
    var fifthScroll = fourthScroll + contentHeight + barHeight;
    var sixthScroll = fifthScroll + contentHeight + barHeight;
    var changeH = (firstHeight + topHeight - clientHeight) / 2;
    var welTop = (clientHeight / 2) - 185 + changeH;
    var weiboTop = clientHeight - 195 + changeH;
    welTop = welTop < 100?100:welTop;

    $(".bwelcome").css("top",welTop + "px").fadeIn();


    $("#second").css("top",secondTop+"px").fadeIn();
    $(".weibo1,.weibo2").css("top",weiboTop+"px").fadeIn();

    $(".weibo1,.weibo2").css("top",(secondTop+scrollTop)+"px");

    if(isIE6)
        return;
    //TODO
    $("#pinyin").animate({"height":"100px"},500);

    $(".pro1").click(function(){
        $("#third").ScrollTo(800);
    });
    $(".pro2").click(function(){
        $("#fourth").ScrollTo(800);
    });
    $(".pro3").click(function(){
        $("#fifth").ScrollTo(800);
    });
    $(".pro4").click(function(){
        $("#sixth").ScrollTo(800);
    });
    $(".pro5").click(function(){
        $("#seventh").ScrollTo(800);
    });
    $window.scroll(function(){
        scrollTop = $window.scrollTop();
        secondTop = (clientHeight - secondHeight - scrollTop)<0?0:(clientHeight - secondHeight - scrollTop);
        $("#second").css("top",secondTop+"px");
        if(scrollTop > thirdScroll && (scrollTop < thirdScroll+clientHeight+barHeight)){
            var yPos = (scrollTop - thirdScroll)/4 * 3 - 800;
            $("#thirdBar").css("background-position","50% "+yPos+"px");
        }
        if(scrollTop > fourthScroll && (scrollTop < fourthScroll+clientHeight+barHeight)){
            var yPos = (scrollTop - fourthScroll)/4 * 3 - 800;
            $("#fourthBar").css("background-position","50% "+yPos+"px");
        }
        if(scrollTop > fifthScroll && (scrollTop < fifthScroll+clientHeight+barHeight)){
            var yPos = (scrollTop - fifthScroll)/4 * 3 - 750;
            $("#fifthBar").css("background-position","50% "+yPos+"px");
        }
        if(scrollTop > sixthScroll && (scrollTop < sixthScroll+clientHeight+barHeight)){
            var yPos = (scrollTop - sixthScroll)/4 * 4 - 850;
            $("#sixthBar").css("background-position","50% "+yPos+"px");
        }

    });

}); // document ready