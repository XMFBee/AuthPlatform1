/**
 *
 * Created by 不曾有黑夜 on 2017/4/27.
 */
/*导航栏*/
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

/*$(".nav-two-ul a li").each(function(){
    $this = $(this);
    if($this[0].href==String(window.location)){
        $this.addClass("actives");
    }
});*/
$(".paging-ul .paging-li ").each(function () {
    $(this).click(function () {
        $(".paging-ul .paging-li ").removeClass("active");
        $(".paging-ul .paging-li ").removeClass("active-two");
        $(this).addClass("active-two");
        return false;
    });
});
/*返回顶部*/
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
/*锚点*/
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