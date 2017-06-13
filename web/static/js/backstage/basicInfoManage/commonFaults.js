$(function () {
    $('#table').bootstrapTable('hideColumn', 'id');

    $("#addSelect").select2({
            language: 'zh-CN'
        }
    );

    //绑定Ajax的内容
    $.getJSON("/table/queryType", function (data) {
        $("#addSelect").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#addSelect").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })
//            $("#addSelect").on("select2:select",
//                    function (e) {
//                        alert(e)
//                        alert("select2:select", e);
//            });
});

function showEdit() {
    var row = $('table').bootstrapTable('getSelections');
    if (row.length > 0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
        $("#editWindow").modal('show'); // 显示弹窗
        var ceshi = row[0];
        $("#editForm").fill(ceshi);
    } else {
        swal({
            "title": "",
            "text": "请先选择一条数据",
            "type": "warning"
        })
    }
}

function showAdd() {

    $("#addWindow").modal('show');
}

function formatRepo(repo) {
    return repo.text
}
function formatRepoSelection(repo) {
    return repo.text
}

function showDel() {
    var row = $('table').bootstrapTable('getSelections');
    if (row.length > 0) {
        $("#del").modal('show');
    } else {
        $("#tanchuang").modal('show');
    }
}

function checkAdd() {
    var id = $('#addId').val();
    var name = $('#addName').val();
    var price = $('#addPrice').val();
    var reslist = $("#addSelect").select2("data"); //获取多选的值
    alert(reslist.length)
    if (id != "" && name != "" && price != "") {
        return true;
    } else {
        var error = document.getElementById("addError");
        error.innerHTML = "请输入正确的数据";
        return false;
    }
}

function checkEdit() {
    $.post("/table/edit",
        $("#editForm").serialize(),
        function (data) {
            if (data.result == "success") {
                $("#editWindow").modal('hide'); // 关闭指定的窗口
                $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                swal({
                    title: "",
                    text: data.message,
                    type: "success"
                })// 提示窗口, 修改成功
            } else if (data.result == "fail") {
                //$.messager.alert("提示", data.result.message, "info");
            }
        }, "json"
    );
}

$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("add_commonFaultsImages", "/file/addFile");
});

$(function () {
    //0.初始化fileinput
    var oFileInput = new FileInput();
    oFileInput.Init("edit_commonFaultsImages", "/file/editFile");
});

//初始化fileinput
var FileInput = function () {
    var oFile = new Object();
    //初始化fileinput控件（第一次初始化）
    oFile.Init = function (ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        //初始化上传控件的样式
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
            showUpload: true, //是否显示上传按钮
            showCaption: false,//是否显示标题
            browseClass: "btn btn-primary", //按钮样式
            dropZoneEnabled: true,//是否显示拖拽区域
            //minImageWidth: 50, //图片的最小宽度
            //minImageHeight: 50,//图片的最小高度
            //maxImageWidth: 1000,//图片的最大宽度
            //maxImageHeight: 1000,//图片的最大高度
            //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
            //minFileCount: 0,
            maxFileCount: 10, //表示允许同时上传的最大文件个数
            enctype: 'multipart/form-data',
            validateInitialCount: true,
            previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        }).on("fileuploaded", function (event, data) {
            // data 为controller返回的json
            if (data.response.result == 'success') {
                alert('处理成功');
            }
        });
    }
    return oFile;
};


$('#addDateTimePicker').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii'
});
$('#editDateTimePicker').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii'
});


//前端验证
$(document).ready(function () {
    $("#showAddFormWar").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            faultsName: {
                required: true,
                minlength: 2
            },
            recordTime: {
                required: true,
                minlength: 2
            },
            level:{
                required: true,
                minlength: 2
            },
            faultsDes:{
                required: true,
                minlength: 2
            }
        },
        messages: {
            faultsName: "请输入故障名称",
            recordTime: "请输入发现时间",
            level:"请输入故障等级",
            faultsDes:"请输入故障描述"
        },
        errorPlacement: function (error, element) {
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success: function (label) {
            var el = label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function (form) {
            alert("submitted!");
        }
    })
    $("#showEditFormWar").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            faultsName: {
                required: true,
                minlength: 2
            },
            recordTime: {
                required: true,
                minlength: 2
            },
            level:{
                required: true,
                minlength: 2
            },
            faultsDes:{
                required: true,
                minlength: 2
            }
        },
        messages: {
            faultsName: "请输入故障名称",
            recordTime: "请输入发现时间",
            level:"请输入故障等级",
            faultsDes:"请输入故障描述"
        },
        errorPlacement: function (error, element) {
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success: function (label) {
            var el = label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function (form) {
            alert("submitted!");
        }
    })
});