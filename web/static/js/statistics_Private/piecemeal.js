
// 随窗口变化,echart大小也变化
function pageChangeEchartsResize() {
    var propertys = arguments;
    $(window).resize(function(){
        var len = propertys.length;
        for(var i = 0; i< len; i++) {
            var curEchart = propertys[i];
            curEchart.resize();
        }
    });
}

// 模态显示后 框中的 echart 大小变化
function detailEchartResize(detailsEchart){
    setTimeout(function(){
        detailsEchart.resize()},200);
}


// 后台传入数据转所需要的数组, 第一个为数据源, 后面两个为字段名
function arrayObjs2array(arrayObj){
    var arrs = [];
    var argument = arguments;
    arrayObj.forEach(function(value,index,array){
        var arr = [value[argument[1]], value[argument[2]] ];
        arrs.push(arr);
    })
    return arrs;
}

function arrayObjs2Objs(arrayObj){
    var arrs = [];
    var argument = arguments;
    arrayObj.forEach(function(value,index,array){
        var obj={name: value[argument[1]], value:  value[argument[2]]}
        arrs.push(obj);
    })
    return arrs;
}


// 后台传入数据转所需要的数据, 第一个为数据源, 后面一个为字段名,用于得到类目
function arrayObjs2arrayAttr(arrayObj){
    var arr=[];
    var argument = arguments;
    arrayObj.forEach(function(value,index,array){
        arr.push( value[argument[1]] );
    })
    return arr;
}

function initData(el) {
    $(el).text(moment(new Date).format('YYYY-MM-DD'));  // 初始页眉日期
}

function setDaterangeReturnFun(elid,daterangeReturnFun){
    $(elid).daterangepicker({
        locale:{
            format: 'YYYY-MM-DD',
            applyLabel: '确认',
            cancelLabel: '取消',
            fromLabel: '从',
            toLabel: '到',
            weekLabel: 'W',
            customRangeLabel: 'Custom Range',
            daysOfWeek:["日","一","二","三","四","五","六"],
            monthNames: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
        },
        ranges: {
            '今日': [moment(), moment()],
            '昨日': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
            '最近一周': [moment().day(1), moment().day(7)],
            '最近一月': [moment().subtract(29, 'days'), moment()],
            '本季度': [moment().quarter(1), moment().quarter(1)],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            'Custom Range':[]
        },
        showCustomRangeLabel: false,
        alwaysShowCalendars: false
    },function(start, end, label) {

        var tempdatetext = ''
        if(label === '今日' || label === '明日')
            tempdatetext = label + " " + start.format('YYYY-MM-DD');
        else
            tempdatetext = label + "(" + start.format('YYYY-MM-DD') + " ~ " +end.format('YYYY-MM-DD') + ")";
        $(elid+">span").text(tempdatetext);

        daterangeReturnFun(start, end, label);
    });
}


function setTopData(top, data){
    top.reLoadData(data); // data is array
}

function setTableData(table, data){
    table.bootstrapTable("load",data);
}