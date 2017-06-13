<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>模块管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/systemManage/distribution.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <style>
       div ul {
            border: none;
        }
        .connectedSortable{
            border: 1px solid #eee;
            width: 142px;
            min-height: 20px;
            list-style-type: none;
            margin: 0;
            padding: 5px 0 0 0;
            float: left;
            margin-right: 10px;
        }
       .connectedSortable li:before {
           content: "";
           background-color: rgba(152, 152, 152, 0.7);
           width: 30px;
           height: 35px;
           position: absolute;
           left: -20px;
           top: -1px;
           cursor: move;
       }
       .connectedSortable li{
            margin: 0 5px 5px 10%;
            padding-left: 20px;
            font-size: 1em;
            width : 80%;
            line-height:35px;
            height:35px;
            border-radius: 3px;
            border: 1px solid #e4e4e4;
           border-left: none;
           background-color: rgba(255,255,255,0.7);
        }
       #otherModule .li {
           width:95% ;
           float:left;
       }
        div ::-webkit-scrollbar {
            width: 5px;
            background: #333;
        }
        div ::-webkit-scrollbar-button{

        }
        div ::-webkit-scrollbar-track {
            background: #504ad6;
        }
        div ::-webkit-scrollbar-track-piece{
            background: #efefef;
        }
        div ::-webkit-scrollbar-thumb {
            width:10px;
            background: #337ab7;
        }
        div ::-webkit-scrollbar-corner {
            background: green;
        }
        div ::-webkit-resizer {
        }
       .modulePanel {
           width:100%;
           margin-right:2%;
           margin-left:1%;
           border: 1px solid #CDCDCD;
           border-radius: 8px;
           float:left;
       }
       #otherModule {
           height:100%;
           position:fixed;
           margin-bottom:0px;
           right:0px;
           bottom:0px;
           -webkit-box-shadow: 2px 0px 10px #909090;
           -moz-box-shadow: 2px 0px 10px #909090;
           box-shadow: 2px 0px 10px #909090;
           background:#fff
       }

        .modulePanel > .panel-heading {
            width:100%;
            height:50px;
            margin-bottom: 5px;
            background:#8BACC7;
            color:#fff
        }
       #otherModule> .panel-heading {
           width:100%;
           height:50px;
           margin-bottom: 5px;
           background: #c79f85;
           color:#fff
       }
       #otherModule.col-md-3{
           padding:0;
       }

        .modulePanel > .panel-heading div {
            display: inline;
            font-weight: bold;
        }
        .modulePanel ul{
            overflow:auto;
            width:100%;
            border:none;
        }
        .glyphicon {
            margin-right:10px;
        }

        #navbar {
            width: 200px;
            max-height:70%;
            position: fixed;
            top:108px;
            left:10px;
            color: #fff;
            overflow: auto;
        }

       #navbar li{
           background:#8BACC7;
           height:35px;
           text-align: center;
           line-height: 30px;
           list-style-type: none;
           cursor: hand;
       }
       #navbar li:after{
           content: "";
           width:90px;
           left: 38%;
           position:absolute;
           border-bottom: 1px solid #7F9FB9;
       }
       span {
           cursor: default;
       }
        span.glyphicon {
            cursor: hand;
        }
        .modal-header h3{
            font-family: '微软雅黑';
        }
       .close {
           right:20px;
           top:10px;
           position: relative;
       }
    </style>
</head>

<body>
<%@include file="../backstage/contextmenu.jsp"%>

<div class="container">
    <div class="panel-body">
        <div class="col-md-9">
            <div class="panel-heading" style="height:90px; border-bottom: 1px solid #ddd">
               <h3 style="display: inline;line-height:70px;" >模块管理</h3>
                <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                    <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();" style="margin-left:15px;">
                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                    </button>
                </shiro:hasAnyRoles>
            </div>

            <div id="navbar" class="col-md-2">
                <ul>
                </ul>
            </div>
            <div class="panel-body col-md-9" id="modulePanels" style="padding-bottom:0px;float:right"  >
            </div>
         </div>
        <div class = "col-md-3" id="otherModule"   >
            <div class = "panel-heading" style="width:100%;border-radius: 0" data-id = "module-">
                <div style="display: inline"><span style="font-weight: bold;">其它</span><small style="margin-left:10px;"><span style="margin-right:3px;">简介</span><span>未被分配到模块里的权限</span></small></div>
                <p class="clearfix"></p>
            </div>
            <ul  class="panel-body connectedSortable" style="overflow:auto;height:85%;width:100%;border:none" >
            </ul>
        </div>
    </div>
</div>

<!-- 添加/修改弹窗 -->
<div class="modal fade" id="addModal" aria-hidden="true" data-backdrop="static" keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="overflow:auto;width:95%" >
                <span class="glyphicon glyphicon-remove closeModal" onclick = "closeModal()"></span>
                <h4 data-tit = "title"></h4><input style="display:none" data-flag="flag"/>
            </div>
            <hr/>
            <form id="addForm" role="form" class="form-horizontal">
                <input type="text" name="moduleId" define="module.moduleId" style="display: none;"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">模块名称：</label>
                    <div class="col-sm-7">
                        <input type="text" name="moduleName" define="module.moduleName"  class="form-control" data-field="moduleName"   placeholder="请输入模块名称"  maxlength="10">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">模块描述：</label>
                    <div class="col-sm-7">
                        <textarea type="textarea"  name="moduleDes" define="module.moduleDes"   class="form-control" data-field="moduleDes"  placeholder="请输入模块描述"
                                  rows="3" maxlength="100"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            onclick = "closeModal()">关闭
                    </button>
                    <button id="addButton" class="btn btn-success btn-sm" onclick="addSubmit()">保存</button>
                    <input type="reset" name="reset" style="display: none;"/>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script src="/static/js/jquery.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table.js"></script>
<script src="/static/js/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/static/js/jquery.formFill.js"></script>
<script src="/static/js/sweetalert/sweetalert.min.js"></script>
<script src="/static/js/contextmenu.js"></script>
<script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
<script src="/static/js/backstage/systemManage/jquery.nestable.js"></script>
<script src="/static/js/backstage/systemManage/jquery-ui.custom.js"></script>
<script src="/static/js/backstage/systemManage/moduleManage.js"></script>
</body>
<script>

</script>
</html>

