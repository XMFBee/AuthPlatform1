/**
 * Created by 不曾有黑夜 on 2017/5/25.
 */
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