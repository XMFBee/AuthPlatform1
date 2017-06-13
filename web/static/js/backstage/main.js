$(function () {
});

// 激活或禁用
function showStatusFormatter(value) {
    if(value == 'Y') {
        return "可用";
    } else {
        return "禁用";
    }
}

function showYesOrNoFormatter(value) {
    if(value == 'Y') {
        return "是";
    } else {
        return "否";
    }
}


// 初始化带有分秒的时间框
function initDateTimePicker(formId, fieldName, fieldId){
    $("#"+fieldId).datetimepicker({
        language: 'zh-CN',
        format: 'yyyy-mm-dd hh:ii',
        initialDate: new Date(),
        autoclose: true,
        todayHighligh:true,
        todayBtn :true, // 显示今日按钮
        autoclose: 1
    }).on('hide',function(e) {
        $('#'+formId).data('bootstrapValidator')
            .updateStatus(fieldName, 'NOT_VALIDATED',null)
            .validateField(fieldName);
    });
}

// 初始化没有分秒的时间框
function initDatePicker(formId, fieldName, fieldId){
    $("#"+fieldId).datetimepicker({
        minView: "month", //选择日期后，不会再跳转去选择时分秒
        language: 'zh-CN',
        format: 'yyyy-mm-dd',
        initialDate: new Date(),
        autoclose: true,
        todayHighligh:true,
        todayBtn :true, // 显示今日按钮
        autoclose: 1
    }).on('hide',function(e) {
        $('#'+formId).data('bootstrapValidator')
            .updateStatus(fieldName, 'NOT_VALIDATED',null)
            .validateField(fieldName);
    });
}

function initTable(tableId, url) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url: url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        showToggle: true, // 显示详情
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "checkinId",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        toolbar : "#toolbar",// 指定工具栏
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

function initTableNotTollbar(tableId, url) {
    //先销毁表格
    $('#' + tableId).bootstrapTable('destroy');
    //初始化表格,动态从服务器加载数据
    $("#" + tableId).bootstrapTable({
        method: "get",  //使用get请求到服务器获取数据
        url:  url, //获取数据的Servlet地址
        striped: false,  //表格显示条纹
        pagination: true, //启动分页
        pageSize: 10,  //每页显示的记录数
        pageNumber:1, //当前第几页
        pageList: [10, 15, 20, 25, 30],  //记录数可选列表
        showColumns: true,  //显示下拉框勾选要显示的列
        showRefresh: true,  //显示刷新按钮
        showToggle: true, // 显示详情
        strictSearch: true,
        clickToSelect: true,  //是否启用点击选中行
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server", //表示服务端请求

        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                orderNum : $("#orderNum").val()
            };
            return param;
        },
    });
}

// 获取当前时间
function getDate(id){
    $('#'+id).val(new Date());
}

/**
 * 时间格式化，传递进来的时间
 */
function formatterDate(value) {
    if (value == undefined || value == null || value == '') {
        return "";
    }
    else {
        var date = new Date(value);
        var year = date.getFullYear().toString();
        var month = (date.getMonth() + 1);
        var day = date.getDate().toString();
        var hour = date.getHours().toString();
        var minutes = date.getMinutes().toString();
        var seconds = date.getSeconds().toString();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        if (hour < 10) {
            hour = "0" + hour;
        }
        if (minutes < 10) {
            minutes = "0" + minutes;
        }
        if (seconds < 10) {
            seconds = "0" + seconds;
        }
        return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }
}
// 当修改表单提交时, 按钮不可点击
$("#editForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});
// 当添加表单提交时, 按钮不可点击
$("#addForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

//模糊查询li点击事件
function onclikLi(lis) {
    var button = $("#ulButton");
    button.text($(lis).text());
    button.append("<span class='caret'></span>");
}

function initSelect2(clazz, title, url) {
    $("." + clazz).select2({
        // enable tagging
        tags: true,
        language: 'zh-CN',
        minimumResultsForSearch: -1,
        placeholder: title,
        ajax: {
            url: url,
            processResults: function (data, page) {
                var parsed = data;
                var arr = [];
                for(var x in parsed){
                    arr.push(parsed[x]);
                }
                return {
                    results: arr
                };
            }
        },

    });
}

// 禁用
function inactive(url) {
    $.post(url,
        function(data){
            if(data.result == 'success'){
                $('#table').bootstrapTable("refresh");
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"})// 提示窗口, 修改成功
            }else{
                swal({title:"",
                    text:"禁用失败",
                    confirmButtonText:"确认",
                    type:"error"})
            }
        },"json");
}
// 激活
function active(url) {
    $.post(url,
        function(data){
            if(data.result == 'success'){
                $('#table').bootstrapTable("refresh");
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"})// 提示窗口, 修改成功
            }else{
                swal({title:"",
                    text:"激活失败",
                    confirmButtonText:"确认",
                    type:"error"})
            }
        },"json");
}

function closeModals(winId, formId){
    $("#"+winId).modal('hide');
    $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
}

/*回车登录*/
function keydown(buttonId){
    if(event.keyCode == 13){
        document.getElementById(buttonId).click();
    }
}


/** 初始化三级地区联动 */
function initCityPicker(id) {
    $('#' + id).citypicker('destroy');
    $('#' + id).citypicker();
}