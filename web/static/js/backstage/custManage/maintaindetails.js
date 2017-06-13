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

//显示弹窗
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
        $("#tanchuang").modal('show');
    }
}

//显示添加
function showAdd() {
    $("#addWindow").modal('show');
}


function formatRepo(repo) {
    return repo.text
}
function formatRepoSelection(repo) {
    return repo.text
}

//显示删除
function showDel() {
    var row = $('table').bootstrapTable('getSelections');
    if (row.length > 0) {
        $("#del").modal('show');
    } else {
        $("#tanchuang").modal('show');
    }
}

//检查添加
function checkAdd() {
    var id = $('#addId').val();
    var name = $('#addName').val();
    var price = $('#addPrice').val();
    var reslist = $("#addSelect").select2("data"); //获取多选的值
    if (id != "" && name != "" && price != "") {
        return true;
    } else {
        var error = document.getElementById("addError");
        error.innerHTML = "请输入正确的数据";
        return false;
    }
}

//检查修改
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

$('#maddDateTime').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii'
});
$('#meditDateTime').datetimepicker({
    language: 'zh-CN',
    format: 'yyyy-mm-dd hh:ii'
});

//前端验证
$(document).ready(function () {
    $("#addForm").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            maintainRecordId: {
                required: true,
                minlength: 2
            },
            maintainItemId: {
                required: true,
                minlength: 2
            },
            maintainDiscount: {
                required: true,
                minlength: 2
            },
            mdCreatedTime: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            maintainRecordId: "请选择维修保养记录",
            maintainItemId: "请选择维修保养项目",
            maintainDiscount: "请输入维修保养项目折扣",
            mdCreatedTime: "请选择维修保养明细创建时间",
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
    $("#editForm").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        rules: {
            maintainRecordId: {
                required: true,
                minlength: 2
            },
            maintainItemId: {
                required: true,
                minlength: 2
            },
            maintainDiscount: {
                required: true,
                minlength: 2
            },
            mdCreatedTime: {
                required: true,
                minlength: 2
            }
        },
        messages: {
            maintainRecordId: "请选择维修保养记录",
            maintainItemId: "请选择维修保养项目",
            maintainDiscount: "请输入维修保养项目折扣",
            mdCreatedTime: "请选择维修保养明细创建时间",
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