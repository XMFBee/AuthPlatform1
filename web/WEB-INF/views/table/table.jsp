<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
<div class="panel-body" style="padding-bottom:0px;"  >
    <!--show-refresh, show-toggle的样式可以在bootstrap-table.js的948行修改-->
    <!-- table里的所有属性在bootstrap-table.js的240行-->
    <table id="table"
           data-toggle="table"
           data-toolbar="#toolbar"
           data-url="/table/query"
           data-method="post"
           data-query-params="queryParams"
           data-pagination="true"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-show-columns="true"
           data-page-size="10"
           data-height="543"
           data-id-field="id"
           data-page-list="[5, 10, 20]"
           data-cach="false"
           data-click-to-select="true"
           data-single-select="true">
        <thead>
        <tr>
            <th data-radio="true" data-field="status"></th>
            <th  data-field="name">用户账号</th>
            <th data-field="price">用户密码</th>
        </tr>
        </thead>
    </table>
    <div id="toolbar" class="btn-group">
        <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
        </button>
        <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
        </button>
        <button id="btn_delete" type="button" class="btn btn-default" onclick="showDel();">
            <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
        </button>
    </div>
 </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade" id="add" aria-hidden="true" style="overflow:hidden;">
    <div class="modal-dialog" style="overflow:hidden;">
        <div class="modal-content" style="overflow:hidden;">
            <form action="/table/edit" onsubmit="return checkAdd()" id="addForm" method="post">
                <div class="modal-header" style="overflow:hidden;">
                    <input type="text" id="addId" placeholder="请输入标题" style="width:300px;" maxlength="15" name="top-search"/>
                    <input type="text" id="addPrice" placeholder="请输入标题" style="width:300px;" maxlength="15" name="top-search"/>
                    <br />
                    <select id="addSelect"  class="js-example-basic-multiple" multiple="multiple" style="width:300px;">
                    </select>
                </div>
                <div class="modal-body" style="overflow:hidden;">
                    <textarea id="addName" placeholder="请输入描述"  style="width:530px;height:100px;" maxlength="142"></textarea>
                </div>
                <div class="modal-footer" style="overflow:hidden;">
                    <span id="addError" style="color: red;"></span>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form id="editForm" class="data1" id="editForm" method="post">
                <div class="modal-header" style="overflow:hidden;">
                    <input type="text"  define="ceshi.id" name="id" placeholder="请输入标题" style="width:300px;margin-left:70px;" maxlength="15"/>
                    <input type="text"  define="ceshi.price" name="price"  placeholder="请输入标题" style="width:300px;margin-left:70px;" maxlength="15"/>
                </div>
                <div class="modal-body">
                    <textarea type="text"  define="ceshi.name" name="name" placeholder="请输入描述"  style="width:530px;height:100px;" maxlength="142"></textarea>
                </div>
                <div class="modal-footer">
                    <span id="editError" style="color: red;"></span>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" onclick="checkEdit()" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 删除弹窗 -->
<div class="modal fade" id="del" aria-hidden="true">
    <div class="modal-dialog" style="overflow:hidden;">
        <form action="/table/edit" method="post">
            <div class="modal-content">
                <input type="hidden" id="delNoticeId"/>
                <div class="modal-footer" style="text-align: center;">
                    <h2>确认删除吗?</h2>
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="sumbit" class="btn btn-primary" onclick="del()">
                        确认
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </form>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- 提示弹窗 -->
<div class="modal fade" id="tanchuang" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
                <div class="modal-header">
                    提示
                </div>
            <div class="modal-body">
                请先选择某一行
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/select2/select2.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script>
    $(function () {
        $('#table').bootstrapTable('hideColumn', 'id');

        $("#addSelect").select2({
                language: 'zh-CN'
            });

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

    function showEdit(){
        var row =  $('table').bootstrapTable('getSelections');
        if(row.length >0) {
//                $('#editId').val(row[0].id);
//                $('#editName').val(row[0].name);
//                $('#editPrice').val(row[0].price);
            $("#edit").modal('show'); // 显示弹窗
            var ceshi = row[0];
            $("#editForm").fill(ceshi);
        }else{
            //layer.msg("请先选择某一行", {time : 1500, icon : 2});
            swal({
                title:"",
                text:"请先选择一行数据",
                type:"warning"})// 提示窗口, 修改成功
        }
    }

    function showAdd(){

        $("#add").modal('show');
    }

    function formatRepo(repo){return repo.text}
    function formatRepoSelection(repo){return repo.text}

    function showDel(){
        var row =  $('table').bootstrapTable('getSelections');
        if(row.length >0) {
            $("#del").modal('show');
        }else{
            $("#tanchuang").modal('show');
        }
    }

    function checkAdd(){
        var id = $('#addId').val();
        var name = $('#addName').val();
        var price = $('#addPrice').val();
        var reslist=$("#addSelect").select2("data"); //获取多选的值
        alert(reslist.length)
        if(id != "" && name != "" && price != ""){
            return true;
        }else{
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
                    $("#edit").modal('hide'); // 关闭指定的窗口
                    $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                    swal({
                        title:"",
                        text: data.message,
                        type:"success"})// 提示窗口, 修改成功
                } else if (data.result == "fail") {
                    //$.messager.alert("提示", data.result.message, "info");
                }
            }, "json"
        );
    }

</script>
</body>
</html>
