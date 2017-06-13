(function($){
    'use strict';


    var cachedWidth = null;
    var  Cyitem  = function (el, options){
        this.options = options;
        this.$el = el;
        this.$el__ = $(this.$el).clone();
        this.timeoutId_ = 0;
        this.timeoutFooter_ = 0;
        this.init();
    };

    Cyitem.DEFAULTS = {
        icon: undefined,
        title: undefined
    };

    Cyitem.prototype.init = function(){
        this.initContainer();
        this.initItem();
        this.initHead();
    }

    Cyitem.prototype.initContainer = function () {
        this.$container = $(["<ul></ul>"
        ].join(' '));
        this.$container.appendTo(this.$el);
    }


    Cyitem.prototype.initItem = function () {
        var that = this,
            style= '',
            html = [];

        $.each(that.options.data, function (index, value){
            var li = "<li>";
            var iconNum =Number(index)+1;

            if(Number(index) !== (that.options.data.length-1)) {
                li = "<li style='float:left'>";
            }
            html.push(li);
            var style = "";
            var cla = "normIcon ";
            if(that.options.icon != undefined && that.options.icon  != ""){
                style = that.options.icon_style;
            }
            var htmlTest = "<i style='" + style + "' " ;

            if(Number(index) === 0) {
                cla += "n1Icon" ;
            }
            htmlTest += "class='" + cla + "' >";


            html.push(htmlTest);
            html.push(iconNum);
            html.push("</i>");
            html.push(value);
            html.push("</li>");
        })
        var items = $(html.join(""));
        items.appendTo(this.$container);

    };

    Cyitem.prototype.initHead = function () {
        this.$head = $([
            this.options.title === undefined ? '':
                this.options.title.text === '' || this.options.title.text === undefined ?
                    '' : this.options.title.style === '' ||this.options.title.style === undefined ?
                '<h4>' + this.options.title.text + '</h4>' :
                '<h4 '+ 'style="' + this.options.title.style + '">' + this.options.title.text + '</h4>',
        ].join(""));
        this.$head.prependTo(this.$el);

    };


    Cyitem.prototype.reLoadData = function (list) {
        var $that = $(this)[0];
        var el = $that.$el;
        var ul = $(el).find(">ul");
        $(ul).empty();
        $that.options.data = list;
        $that.initItem();

    }

    $.fn.cyItem = function (option) {
        var value,
            args = Array.prototype.slice.call(arguments, 1);


        this.each(function () {

            var $this = $(this),
                data = $this.data('cyitem.item'),
                options = $.extend({}, Cyitem.DEFAULTS, $this.data(),
                    typeof option === 'object' && option);


            if (typeof option === 'string') {
                if ($.inArray(option, allowedMethods) < 0) {
                    throw new Error("Unknown method: " + option);
                }

                if (!data) {
                    return;
                }
                value = data[option].apply(data, args);

                if (option === 'destroy') {
                    $this.removeData('cyitem.item');
                }
            }


            if (!data) {
                $this.data('cyitem.item', (data = new Cyitem(this, options)));
            }
        });

        return $(this).data('cyitem.item');
    };

    $.fn.cyItem.Constructor = Cyitem;
    $.fn.cyItem.defaults = Cyitem.DEFAULTS;


    Cyitem.prototype.testFun = function () {
        console.log("use testFun");
    }


})($)