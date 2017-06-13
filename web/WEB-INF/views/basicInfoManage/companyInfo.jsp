<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>公司信息管理</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-table.css">
    <link rel="stylesheet" href="/static/css/select2.min.css">
    <link rel="stylesheet" href="/static/css/table/table.css">
    <link rel="stylesheet" href="/static/css/fileinput.css">
    <link rel="stylesheet" href="/static/css/sweetalert.css">
    <link rel="stylesheet" href="/static/css/city-picker/city-picker.css">
    <link rel="stylesheet" href="/static/css/bootstrap-validate/bootstrapValidator.min.css">
    <link rel="stylesheet" href="/static/css/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet/less" href="/static/css/bootstrap-dateTimePicker/datetimepicker.less">
    <link rel="stylesheet" href="/static/css/fileinput.css">
</head>
<body>
<%@include file="../backstage/contextmenu.jsp" %>

<div class="container">
    <div class="panel-body" style="padding-bottom:0px;">
        <table id="table" style="table-layout: fixed">
            <thead>
                <tr>
                    <th data-radio="true"></th>
                    <th data-width="120" data-field="companyName">公司名称</th>
                    <th data-width="180" data-field="companyAddress">公司地址</th>
                    <th data-width="120" data-field="companyTel">公司联系电话</th>
                    <th data-width="100" data-field="companyPricipal">负责人</th>
                    <th data-width="130" data-field="companyPricipalphone">负责人联系电话</th>
                    <th data-width="170" data-field="companyWebsite">公司官网网址</th>
                    <th data-width="120" data-field="companyLogo" data-formatter="formatterImg">公司LOGO</th>
                    <th data-width="150" data-field="companyOpendate" data-formatter="formatterDate">公司成立时间</th>
                    <th data-width="120" data-field="companySize">公司规模</th>
                    <th data-width="120" data-field="companyLongitude">公司经度</th>
                    <th data-width="120" data-field="companyLatitude">公司纬度</th>
                    <th data-width="150" data-field="companyDes">公司描述</th>
                    <th data-width="100" data-field="companyStatus" data-formatter="showStatusFormatter">公司状态</th>
                    <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                        <th data-width="100" data-field="companyStatus" data-formatter="statusFormatter">
                            操作
                        </th>
                    </shiro:hasAnyRoles>
                </tr>
            </thead>
        </table>
        <div id="toolbar" class="btn-group">
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="btn_available" type="button" class="btn btn-success" onclick="showAvailable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>可用公司记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="btn_disable" type="button" class="btn btn-danger" onclick="showDisable();">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>禁用公司记录
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员">
                <button id="btn_add" type="button" class="btn btn-default" onclick="showAdd();">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                <button id="btn_edit" type="button" class="btn btn-default" onclick="showEdit();">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
            </shiro:hasAnyRoles>
            <shiro:hasAnyRoles name="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员">
                <div class="input-group" style="width:300px;float:left;padding:0;margin:0 0 0 -1px;">
                    <div class="input-group-btn">
                        <button type="button" id="ulButton" class="btn btn-default" style="border-radius:0px;"
                                data-toggle="dropdown">公司名称/公司负责人</button>
                        <ul class="dropdown-menu pull-right">
                            <li><a onclick="onclikLi(this)">公司名称/公司负责人</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">公司名称</a></li>
                            <li class="divider"></li>
                            <li><a onclick="onclikLi(this)">公司负责人</a></li>
                        </ul>
                    </div><!-- /btn-group -->
                    <input id="ulInput" class="form-control" onkeypress="if(event.keyCode==13) {blurredQuery();}">
                    <a href="javaScript:;" onclick="blurredQuery()"><span class="glyphicon glyphicon-search search-style"></span></a>
                    </input>
                </div><!-- /input-group -->
            </shiro:hasAnyRoles>
        </div>
    </div>
</div>

<!-- 添加弹窗 -->
<div class="modal fade"  id="addWindow" style="overflow:auto;" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width:90%;">
        <div class="modal-content" data-options="resizable:true,modal:true">
              <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('addWindow', 'addForm')"></span>
            <form class="form-horizontal" role="form" id="addForm">
                <div class="modal-header" style="overflow:auto;">
                    <h4>添加公司的信息</h4>
                </div>
                <br/>
                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司名称：</label>
                        <div class="col-md-8">
                            <input type="text" id="addcompanyName" name="companyName" placeholder="请输入公司名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司官网网址：</label>
                        <div class="col-md-8">
                            <input type="text" name="companyWebsite" placeholder="请输入公司官网网址" class="form-control">
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司联系电话：</label>
                        <div class="col-md-8">
                            <input type="number" name="companyTel" placeholder="请输入公司联系电话" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司成立时间：</label>
                        <div class="col-md-8">     <!-- 当设置不可编辑后, 会修改颜色, 在min.css里搜索.form-control{background-color:#eee;opacity:1} -->
                            <input id="addDateTimePicker" placeholder="请选择公司成立时间" onclick="getDate('addDateTimePicker')" readonly="true" type="text" name="companyOpendate"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司负责人：</label>
                        <div class="col-md-8">
                            <input type="text" name="companyPricipal" placeholder="请输入公司负责人" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">负责人联系电话：</label>
                        <div class="col-md-8">
                            <input type="number" id="addcompanyPricipalphone" name="companyPricipalphone" placeholder="请输入负责人联系电话" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司Logo：</label>
                        <div class="col-md-8">
                            <div class="ibox-title">
                                <div class="input-group">
                                    <input id="file" name="companyLogo" type="file" class="form-control" multiple
                                           class="file-loading"/>
                                </div>
                            </div>
                        </div>
                    </div>
                        <%--<div class="form-group col-md-6">--%>
                            <%--<label class="col-md-4 control-label">公司Logo：</label>--%>
                            <%--<div class="col-md-8" style="margin-left: 30px;">--%>
                                <%--<div class="container kv-main">--%>
                                    <%--<div class="ibox-title">--%>
                                        <%--<div class="input-group">   &lt;%&ndash; FileInput边框和组件组在一起 &ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<div class="input-group-btn"></div> &lt;%&ndash; 用来显示选中的图片 &ndash;%&gt;&ndash;%&gt;--%>
                                            <%--<input id="file" name="companyLogo" type="file" class="form-control" multiple class="file-loading"/>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司规模：</label>
                        <div class="col-md-8">
                            <select class="form-control" name="companySize" >
                                <option value="5~10">5~10</option>
                                <option value="10~50">10~50</option>
                                <option value="50~100">50~100</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司经度：</label>
                        <div class="col-md-8">
                            <input id="addCompanyLongitudeId" name="companyLongitude" readonly="true"  type="text" placeholder="请选择公司经度" class="form-control">
                        </div>
                    </div>

                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司纬度：</label>
                        <div class="col-md-8">
                            <input id="addCompanyLatitudeId" name="companyLatitude" readonly="true" placeholder="请选择公司纬度" class="form-control">
                                <button type="button" style="margin-top: 10px;" class="btn btn-primary" onclick="showMap('addWindow')">地图选择</button>
                            </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司描述：</label>
                        <div class="col-md-8">
                                <textarea class="form-control" placeholder="请输入公司描述" name="companyDes"
                                          rows="5" maxlength="500"></textarea>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>
                <%--<div>--%>
                    <%--<div class="form-group col-md-12">--%>
                        <%--<label class="col-md-2 control-label" style="top:3px;right:5px">公司地址：</label>--%>
                        <%--<div class="col-md-10">--%>
                            <%--<fieldset id="city_china">--%>
                                <%--<div class="form-group col-md-4">--%>
                                    <%--<select class="province js-example-tags form-control" disabled="disabled" name="province"></select>--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-4">--%>
                                    <%--<select class="city js-example-tags form-control" disabled="disabled" name="city"></select>--%>
                                <%--</div>--%>
                                <%--<div class="form-group col-md-4">--%>
                                    <%--<select class="area js-example-tags form-control" disabled="disabled" name="area"></select>--%>
                                <%--</div>--%>
                            <%--</fieldset>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--<div class="form-group">--%>
                    <%--<label class="control-label">公司地址：</label>--%>
                    <%--<div style="position: relative;">--%>
                        <%--<input data-toggle="city-picker" class="col-sm-6" name="companyAddress"/>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-md-2 control-label">公司地址：</label>
                    <div class="col-md-10">
                        <div style="position: relative;">
                            <input data-toggle="city-picker" class="col-md-4" name="companyAddress"/>
                        </div>
                    </div>
                </div>
                    <%--<p class="clearfix"></p>--%>
                <%--</div>--%>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeModals('addWindow', 'addForm')">关闭</button>
                    <button id="addButton" type="button" onclick="addSubmit()" class="btn btn-sm btn-success">添加
                    </button>
                    <input type="reset" name="reset" style="display: none;"/>
                </div>
            </form>
        </div><!-- /.modal-content -->
        </div>
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- 修改弹窗 -->
<div class="modal fade" id="editWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width:90%;">
        <div class="modal-content" data-options="resizable:true,modal:true">
            <div class="modal-body">
            <span class="glyphicon glyphicon-remove closeModal" onclick="closeModals('editWindow', 'editForm')"></span>
            <form class="form-horizontal" role="form" id="editForm"  enctype="multipart/form-data">
                <input type="hidden"name="companyId" define="company.companyId">
                <input type="hidden"name="companyStatus" define="company.companyStatus">
                <input id="editLogo" type="hidden" name="logo" define="companyInfo.companyLogo"/>
                <div class="modal-header" style="overflow:auto;">
                    <h4>修改公司的信息</h4>
                </div>
                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司名称：</label>
                        <div class="col-md-8">
                            <input type="text" id="editcompanyName" name="companyName" define="companyInfo.companyName" placeholder="请输入公司名称" class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司官网网址：</label>
                        <div class="col-md-8">
                            <input type="text" name="companyWebsite" define="companyInfo.companyWebsite" placeholder="请输入公司官网网址"
                                   class="form-control">
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司联系电话：</label>
                        <div class="col-md-8">
                            <input type="number" name="companyTel" placeholder="请输入公司联系电话电话" define="companyInfo.companyTel" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司成立时间：</label>
                        <div class="col-md-8">     <!-- 当设置不可编辑后, 会修改颜色, 在min.css里搜索.form-control{background-color:#eee;opacity:1} -->
                            <input id="editDatetimepicker" placeholder="请选择公司成立时间" readonly="true" type="text" name="companyOpendate"
                                   class="form-control datetimepicker"/>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司负责人：</label>
                        <div class="col-md-8">
                            <input type="text" name="companyPricipal" define="companyInfo.companyPricipal" placeholder="请输入公司负责人"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">负责人联系电话：</label>
                        <div class="col-md-8">
                            <input type="number" id="editcompanyPricipalphone" name="companyPricipalphone" placeholder="请输入负责人联系电话" define="companyInfo.companyPricipalphone" class="form-control" style="width:100%"/>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>
                <div>
                    <%--<div class="form-group col-md-6">--%>
                        <%--<label class="col-md-4 control-label">公司logo：</label>--%>
                        <%--<div class="col-md-8">--%>
                            <%--<div class="ibox-title">--%>
                                <%--<div class="container kv-main">--%>
                                    <%--<div class="ibox-title">--%>
                                            <%--<div class="input-group-btn"></div> &lt;%&ndash; 用来显示选中的图片 &ndash;%&gt;--%>
                                            <%--<input id="file1" define="companyInfo.companyLogo" name="companyLogo" type="file" class="form-control" multiple--%>
                                                   <%--class="file-loading"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司Logo：</label>
                        <div class="col-md-8">
                            <div class="ibox-title">
                                <div class="input-group">
                                    <input id="file1" name="companyLogo" value="" type="file" class="form-control" multiple
                                           class="file-loading"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司规模：</label>
                        <div class="col-md-8">
                            <select class="form-control" define="companyInfo.companySize" id="companys" name="companySize">
                                <option value="5~10">5~10</option>
                                <option value="10~50">10~50</option>
                                <option value="50~100">50~100</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司经度：</label>
                        <div class="col-md-8">
                            <input type="text" id="editCompanyLongitudeId" name="companyLongitude" define="companyInfo.companyLongitude" readonly="true" placeholder="请输入公司经度"
                                   class="form-control">
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司纬度：</label>
                        <div class="col-md-8">
                            <input type="text" id="editCompanyLatitudeId" name="companyLatitude" define="companyInfo.companyLatitude" readonly="true" placeholder="请输入公司经度"
                                   class="form-control">
                            <button type="button" style="margin-top: 10px;" class="btn btn-primary" onclick="showMap('editWindow')">地图选择</button>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label class="col-md-4 control-label">公司描述：</label>
                        <div class="col-md-8">
                                    <textarea type="textarea" class="form-control" placeholder="请输入公司描述" define="companyInfo.companyDes" name="companyDes"
                                              rows="5" maxlength="500"></textarea>
                        </div>
                    </div>
                    <p class="clearfix"></p>
                </div>

                <div class="form-group">
                    <label class="col-md-2 control-label">公司地址：</label>
                    <div class="col-md-10">
                        <div style="position: relative;">
                            <input id="address" type="text" class="col-md-4" define="companyInfo.companyAddress" name="companyAddress"/>
                        </div>
                    </div>
                </div>
                <%--<div>--%>
                    <%--<div class="form-group col-md-12 pull-right">--%>
                        <%--<label class="col-md-2 control-label" style="bottom: 6px;right:5px">公司地址：</label>--%>
                        <%--<div class="col-md-10" id="address" style="bottom:3px;display: block;">--%>
                            <%--<input type="text" define="companyInfo.companyAddress" class="form-control">--%>
                        <%--</div>--%>
                        <%--<div class="col-md-9" id="companyAddress" style="display: none;">--%>
                            <%--<fieldset id="editCity_china">--%>
                                <%--<div class="pull-left">--%>
                                    <%--省份：<select class="province" disabled="disabled" id="editProvince" name="editProvince"></select>--%>
                                <%--</div>--%>
                                <%--<div class="pull-left">--%>
                                    <%--&nbsp;&nbsp;&nbsp;城市：<select class="city" disabled="disabled" id="editCity" name="editCity"></select>--%>
                                <%--</div>--%>
                                <%--<div class="pull-left">--%>
                                    <%--&nbsp;&nbsp;&nbsp;地区：<select class="area" disabled="disabled" id="editArea" name="editArea"></select>--%>
                                <%--</div>--%>
                            <%--</fieldset>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<p class="clearfix"></p>--%>
                <%--</div>--%>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal" onclick="closeCompanyModals('editWindow', 'editForm')">关闭
                    </button>
                    <button id="editButton" type="button" onclick="editSubmit()" class="btn btn-sm btn-success">保存
                    </button>
                </div>
            </form>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<!-- 地图弹窗 -->
<div class="modal fade" id="mapWindow" style="overflow-y:scroll" aria-hidden="true" data-backdrop="static" keyboard:false>
    <div class="modal-dialog" style="width: 100%;">
        <div class="modal-content">
            <div class="modal-body">
                <span class="glyphicon glyphicon-remove closeModal"data-dismiss="modal"></span>
                <div class="form-group row">
                    <label class="col-sm-1 control-label" style="padding: 5px 0 0 0;margin: 0;">快速定位：</label>
                    <div class="col-sm-3" style="padding-left: 0;">
                        <input id="text_" type="text" class="form-control" placeholder="请输入你要查询的地址" onkeypress="if(event.keyCode==13) {searchByStationName();}"/>
                    </div>
                    <button type="button" class="btn btn-default"  style="height:35px;" onclick="searchByStationName();">
                        <span class="glyphicon glyphicon-search" style="padding-bottom: 4px;">查询</span>
                    </button>
                </div>
                    <div id="allmap" style="wdith:100%;height: 100%"></div>
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
    <script src="/static/js/backstage/main.js"></script>
    <script src="/static/js/fileInput/fileinput.js"></script>
    <script src="/static/js/fileInput/zh.js"></script>
    <script src="/static/js/city-picker/city-picker.data.js"></script>
    <script src="/static/js/city-picker/city-picker.js"></script>
    <script src="/static/js/bootstrap-validate/bootstrapValidator.js"></script>
    <script src="/static/js/bootstrap-dateTimePicker/bootstrap-datetimepicker.min.js"></script>
    <script src="/static/js/bootstrap-dateTimePicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
    <script src="/static/js/backstage/basicInfoManage/companyInfo.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=8vZTXUNkRraY09RFkx0EupdHALhgSzyX"></script>
    <%-- 文件上传 --%>
    <script src="/static/js/fileInput/fileinput.js"></script>
    <script src="/static/js/fileInput/zh.js"></script>
    <%-- 地址选择 --%>
    <script src="/static/js/jquery.cxselect.min.js"></script>

</body>
<%--<script>--%>


    <%--$.cxSelect.defaults.url = '/static/js/cityData.json';--%>
    <%--$('#city_china').cxSelect({--%>
        <%--selects: ['province', 'city', 'area']--%>
    <%--});--%>
    <%--$('#editCity_china').cxSelect({--%>
        <%--selects: ['province', 'city', 'area']--%>
    <%--});--%>
    <%--$('#city_china_val').cxSelect({--%>
        <%--selects: ['province', 'city', 'area'],--%>
        <%--nodata: 'none'--%>
    <%--});--%>

<%--</script>--%>
</html>
