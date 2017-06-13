
$(function () {
    var roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success'){
            initSelect2("companyName", "请选择公司", "/company/queryAllCompany"); // 初始化select2, 第一个参数是class的名字, 第二个参数是select2的提示语, 第三个参数是select2的查询url
        }else if(data.result == 'notLogin'){
            swal({title:"",
                    text:data.message,
                    confirmButtonText:"确认",
                    type:"error"}
                ,function(isConfirm){
                    if(isConfirm){
                        top.location = "/user/loginPage";
                    }else{
                        top.location = "/user/loginPage";
                    }
                })
        }else if(data.result == 'notRole'){
            swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"})
        }
    });
});


function selectMaintainName() {
    var companyId = $("#companyId").val();
    var maintainTypeId = $("#maintainTypeId").val();
    initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    $('#maintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");

}
function selectMonthMaintainName() {
    var companyId = $("#monthCompanyId").val();
    var maintainTypeId = $("#monthMaintainTypeId").val();
    initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    $('#monthMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");

}
function selectDayMaintainName() {
    var companyId = $("#dayCompanyId").val();
    var maintainTypeId = $("#dayMaintainTypeId").val();
    initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    $('#dayMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");

}
function selectQuarterMaintainName() {
    var companyId = $("#quarterCompanyId").val();
    var maintainTypeId = $("#quarterMaintainTypeId").val();
    initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    $('#quarterMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");

}
function selectWeekMaintainName() {
    var companyId = $("#weekCompanyId").val();
    var maintainTypeId = $("#weekMaintainTypeId").val();
    initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    $('#weekMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");

}

// 这个方法别看
$("#maintainTypeId").change(function(){
    var companyId = $("#companyId").val();
    var maintainTypeId = $("#maintainTypeId").val();
    $('#maintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    if (companyId != null && companyId != '') {
        initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    } else {
        initSelect2("maintainName", "请选择项目名称", "/maintain/mySelectMaintainName/"+maintainTypeId);
    }
});

// 这个方法别看
$("#monthMaintainTypeId").change(function(){
    var companyId = $("#monthCompanyId").val();
    var maintainTypeId = $("#monthMaintainTypeId").val();
    $('#monthMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    if (companyId != null && companyId != '') {
        initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    } else {
        initSelect2("maintainName", "请选择项目名称", "/maintain/mySelectMaintainName/"+maintainTypeId);
    }
});

// 这个方法别看
$("#dayMaintainTypeId").change(function(){
    var companyId = $("#dayCompanyId").val();
    var maintainTypeId = $("#dayMaintainTypeId").val();
    $('#dayMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    if (companyId != null && companyId != '') {
        initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    } else {
        initSelect2("maintainName", "请选择项目名称", "/maintain/mySelectMaintainName/"+maintainTypeId);
    }
});

// 这个方法别看
$("#quarterMaintainTypeId").change(function(){
    var companyId = $("#quarterCompanyId").val();
    var maintainTypeId = $("#quarterMaintainTypeId").val();
    $('#quarterMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    if (companyId != null && companyId != '') {
        initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    } else {
        initSelect2("maintainName", "请选择项目名称", "/maintain/mySelectMaintainName/"+maintainTypeId);
    }
});

// 这个方法别看
$("#weekMaintainTypeId").change(function(){
    var companyId = $("#weekCompanyId").val();
    var maintainTypeId = $("#weekMaintainTypeId").val();
    $('#weekMaintainId').html('<option value="' + '' + '">' + '' + '</option>').trigger("change");
    if (companyId != null && companyId != '') {
        initSelect2("maintainName", "请选择项目名称", "/maintain/queryByMaintainName/"+companyId+"/"+maintainTypeId);
    } else {
        initSelect2("maintainName", "请选择项目名称", "/maintain/mySelectMaintainName/"+maintainTypeId);
    }
});




// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    tooltip: {
        trigger: 'axis', //坐标轴触发提示框，多用于柱状、折线图中
        /*
         控制提示框内容输出格式
         formatter: '{b0}<br/><font color=#FF3333>&nbsp;●&nbsp;</font>{a0} : {c0} ℃ ' +
         '<br/><font color=#53FF53>●&nbsp;</font>{a1} : {c1} % ' +
         '<br/><font color=#68CFE8>&nbsp;●&nbsp;</font>{a3} : {c3} mm ' +
         '<br/><font color=#FFDC35>&nbsp;●&nbsp;</font>{a4} : {c4} m/s ' +
         '<br/><font color=#B15BFF>&nbsp;&nbsp;&nbsp;&nbsp;●&nbsp;</font>{a2} : {c2} hPa '
         */
    },
    legend: {
        data: ['维修项目统计']
    },
    dataZoom: [
        {
            type: 'slider',	//支持鼠标滚轮缩放
            start: 0,			//默认数据初始缩放范围为10%到90%
            end: 100
        },
        {
            type: 'inside',	//支持单独的滑动条缩放
            start: 0,			//默认数据初始缩放范围为10%到90%
            end: 100
        }
    ],
    color:[
        '#FF3333',	//温度曲线颜色
        '#49ebff'//温度曲线颜色
    ],
    toolbox: {
        //显示策略，可选为：true（显示） | false（隐藏），默认值为false
        show: true,
        //启用功能，目前支持feature，工具箱自定义功能回调处理
        feature: {
            //辅助线标志
            mark: {show: true},
            //dataZoom，框选区域缩放，自动与存在的dataZoom控件同步，分别是启用，缩放后退
            //数据视图，打开数据视图，可设置更多属性,readOnly 默认数据视图为只读(即值为true)，可指定readOnly为false打开编辑功能
            dataView: {show: true, readOnly: true},
            //magicType，动态类型切换，支持直角系下的折线图、柱状图、堆积、平铺转换
            magicType: {show: true, type: ['line', 'bar']},
            //restore，还原，复位原始图表
            restore: {show: true},
            //saveAsImage，保存图片（IE8-不支持）,图片类型默认为'png'
            saveAsImage: {show: true}
        }
    },
    xAxis:  {	//X轴
        type : 'category',
        data : []	//先设置数据值为空，后面用Ajax获取动态数据填入
    },
    yAxis : [	//Y轴（这里我设置了两个Y轴，左右各一个）
        {
            //第一个（左边）Y轴，yAxisIndex为0
            type : 'value',
            name : '数量',
            /* max: 120,
             min: -40, */
            axisLabel : {
                formatter: '{value}'	//控制输出格式
            }
        },


    ],
    series : [	//系列（内容）列表
        {
            name:'项目维修项目统计',
            type:'line',	//折线图表示（生成温度曲线）
            symbol:'emptycircle',	//设置折线图中表示每个坐标点的符号；emptycircle：空心圆；emptyrect：空心矩形；circle：实心圆；emptydiamond：菱形
            data:[]		//数据值通过Ajax动态获取
        }
    ]
};

myChart.showLoading();	//数据加载完之前先显示一段简单的loading动画

$.ajax({	//使用JQuery内置的Ajax方法
    type : "post",		//post请求方式
    async : true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
    data: {"start": "2017-1-1", "end": "2017-12-31", "type":"day"},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
    dataType : "json",		//返回数据形式为json
    success : function(result) {
        //请求成功时执行该函数内容，result即为服务器返回的json对象


        if (result !== null && result.length > 0) {
            for (var i = 0; i < result.length; i++) {
                inMoney.push(result[i].inMoney);
                outMoney.push(result[i].outMoney);
                Datas.push(result[i].mdCreatedTime);

            }

            myChart.hideLoading();	//隐藏加载动画

            myChart.setOption({		//载入数据
                xAxis: {
                    data: Datas,	//填入X轴数据
                },
                series: [	//填入系列（内容）数据
                    {
                        // 根据名字对应到相应的系列
                        name: '维修项目统计',
                        data: inMoney
                    },

                ]
            });


        }
        else {
            //返回的数据为空时显示提示信息
            swal({
                title:"",
                text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                confirmButtonText: "确定", // 提示按钮上的文本
                type: "warning"
            })
            myChart.hideLoading();
        }

    },
    error : function(errorMsg) {
        //请求失败时执行该函数
        swal({
            title:"",
            text:"请选择条件查询图表",
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText: "确定", // 提示按钮上的文本
            type: "warning"
        })
        myChart.hideLoading();
    }
})


myChart.setOption(option, true);	//载入图表

window.addEventListener('resize', function () {
    myChart.resize();
})



$('.form_Year').datetimepicker({
    format: 'yyyy',
    weekStart: 1,
    autoclose: true,
    startView: 4,
    minView: 4,
    forceParse: false,
    language: 'zh-CN'
}),

    $('.form_Month').datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm',
        autoclose: true,
        todayBtn: true,
        startView: 'year',
        minView: 'year',
        maxView: 'decade'
    }),

    $('.form_Day').datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        format: "yyyy-mm-dd", //选择日期后，文本框显示的日期格式
        language: 'zh-CN', //汉化
        autoclose: true //选择日期后自动关闭
    })





function selectYears() {

    var count=[];		//湿度数组
    var workInfoDatas=[];		//时间数组

    var start = $("#startYearInput").val() + "-01-01";
    var end = $("#endYearInput").val() + "-12-31";
    var companyId = $("#companyId").val();
    var maintainOrFix = $("#maintainTypeId").val();
    var maintainId = $("#maintainId").val();
    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/maintain/queryByCondition",	//请求发送到ShowInfoIndexServlet处
        data: {"start": start, "end": end, "type":"year", "companyId":companyId, "maintainId":maintainId, "maintainOrFix":maintainOrFix},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result !== null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    count.push(result[i].count);
                    workInfoDatas.push(formatterYear(result[i].mdCreatedTime));
                }

                myChart.hideLoading();	//隐藏加载动画

                myChart.setOption({		//载入数据
                    xAxis: {
                        data: workInfoDatas,	//填入X轴数据
                    },
                    series: [	//填入系列（内容）数据
                        {
                            // 根据名字对应到相应的系列
                            name: '维修项目统计',
                            data: count
                        }
                    ]
                });


            }
            else {
                //返回的数据为空时显示提示信息
                swal({
                    title:"",
                    text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
                myChart.hideLoading();
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数

            myChart.hideLoading();
        }
    })

    myChart.setOption(option, true);	//载入图表
}


function selectMonth() {


    var count=[];		//湿度数组
    var workInfoDatas=[];		//时间数组

    var start = $("#startMonthInput").val() + "-01";
    var end = $("#endMonthInput").val() + "-31";
    var companyId = $("#monthCompanyId").val();
    var maintainOrFix = $("#monthMaintainTypeId").val();
    var maintainId = $("#monthMaintainId").val();
    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/maintain/queryByCondition",	//请求发送到ShowInfoIndexServlet处
        data: {"start": start, "end": end, "type":"month", "companyId":companyId, "maintainId":maintainId, "maintainOrFix":maintainOrFix},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象


            if (result != null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    count.push(result[i].count);
                    workInfoDatas.push(formatterMonth(result[i].mdCreatedTime));

                }
                myChart.hideLoading();	//隐藏加载动画


                myChart.setOption({		//载入数据
                    xAxis: {
                        data: workInfoDatas	//填入X轴数据
                    },
                    series: [	//填入系列（内容）数据

                        {
                            // 根据名字对应到相应的系列
                            name: '维修项目统计',
                            data: count
                        }
                    ]
                });

            }
            else {
                //返回的数据为空时显示提示信息
                swal({
                    title:"",
                    text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
                myChart.hideLoading();
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数

            myChart.hideLoading();
        }
    })

}

function selectDay() {

    var count=[];		//湿度数组
    var workInfoDatas=[];		//时间数组

    var start = $("#startDayInput").val();
    var end = $("#endDayInput").val();
    var companyId = $("#dayCompanyId").val();
    var maintainOrFix = $("#dayMaintainTypeId").val();
    var maintainId = $("#dayMaintainId").val();
    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/maintain/queryByCondition",	//请求发送到ShowInfoIndexServlet处
        data: {"start": start, "end": end, "type":"day", "companyId":companyId, "maintainId":maintainId, "maintainOrFix":maintainOrFix},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象


            if (result != null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    count.push(result[i].count);
                    workInfoDatas.push(formatterDay(result[i].mdCreatedTime));

                }
                myChart.hideLoading();	//隐藏加载动画


                myChart.setOption({		//载入数据
                    xAxis: {
                        data: workInfoDatas	//填入X轴数据
                    },
                    series: [	//填入系列（内容）数据
                        {
                            // 根据名字对应到相应的系列
                            name: '维修项目统计',
                            data: count
                        }
                    ]
                });

            }
            else {
                //返回的数据为空时显示提示信息
                swal({
                    title:"",
                    text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
                myChart.hideLoading();
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数

            myChart.hideLoading();
        }
    })

}

function selectQuarter() {
    var count=[];		//湿度数组
    var workInfoDatas=[];		//时间数组
    var start = $("#startQuarterInput").val();
    var end = $("#endQuarterInput").val();
    var companyId = $("#quarterCompanyId").val();
    var maintainOrFix = $("#quarterMaintainTypeId").val();
    var maintainId = $("#quarterMaintainId").val();
    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/maintain/queryByCondition",	//请求发送到ShowInfoIndexServlet处
        data: {"start": start, "end": end, "type":"quarter", "companyId":companyId, "maintainId":maintainId, "maintainOrFix":maintainOrFix},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象

            if (result != null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    count.push(result[i].count);
                    workInfoDatas.push(formatterQuarter(result[i].mdCreatedTime));

                }
                myChart.hideLoading();	//隐藏加载动画

                myChart.setOption({		//载入数据
                    xAxis: {
                        data: workInfoDatas	//填入X轴数据
                    },
                    series: [	//填入系列（内容）数据

                        {
                            // 根据名字对应到相应的系列
                            name: '维修项目统计',
                            data: count
                        }
                    ]
                });

            }
            else {
                //返回的数据为空时显示提示信息
                swal({
                    title:"",
                    text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
                myChart.hideLoading();
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数

            myChart.hideLoading();
        }
    })

}


function selectWeek() {

    var count=[];		//湿度数组
    var workInfoDatas=[];		//时间数组

    var start = $("#startWeekInput").val();
    var end = $("#endWeekInput").val();
    var companyId = $("#weekCompanyId").val();
    var maintainOrFix = $("#weekMaintainTypeId").val();
    var maintainId = $("#weekMaintainId").val();
    $.ajax({	//使用JQuery内置的Ajax方法
        type: "post",		//post请求方式
        async: true,		//异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: "/maintain/queryByCondition",	//请求发送到ShowInfoIndexServlet处
        data: {"start": start, "end": end, "type":"week", "companyId":companyId, "maintainId":maintainId, "maintainOrFix":maintainOrFix},		//请求内包含一个key为name，value为A0001的参数；服务器接收到客户端请求时通过request.getParameter方法获取该参数值
        dataType: "json",		//返回数据形式为json
        success: function (result) {
            //请求成功时执行该函数内容，result即为服务器返回的json对象


            if (result != null && result.length > 0) {
                for (var i = 0; i < result.length; i++) {
                    count.push(result[i].count);
                    workInfoDatas.push(formatterWeek(result[i].mdCreatedTime));

                }
                myChart.hideLoading();	//隐藏加载动画


                myChart.setOption({		//载入数据
                    xAxis: {
                        data: workInfoDatas	//填入X轴数据
                    },
                    series: [	//填入系列（内容）数据

                        {
                            // 根据名字对应到相应的系列
                            name: '维修项目统计',
                            data: count
                        }
                    ]
                });

            }
            else {
                //返回的数据为空时显示提示信息
                swal({
                    title:"",
                    text:"图表请求数据为空,没有当前时间段的数据,请选择一个时间段的数据，可以根据年月日季度周查询",
                    confirmButtonColor: "#DD6B55", // 提示按钮的颜色
                    confirmButtonText: "确定", // 提示按钮上的文本
                    type: "warning"
                })
                myChart.hideLoading();
            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数

            myChart.hideLoading();
        }
    })

}