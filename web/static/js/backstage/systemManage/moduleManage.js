var initObj = {};
$(function() {
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            initModules();
            init();
            windowScrollListener();
            canDragSetting();
        } else if(data.result == 'notLogin'){
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
        } else if(data.result == 'notRole'){
            swal({title:"",
                text:data.message,
                confirmButtonText:"确认",
                type:"error"})
        }
    })
});

function init(){
    var moduleData = initObj.modules.responseJSON;
    $.get("/permission/noStatusQueryAll",function(permissionData){
        var mlen = moduleData.length;
        var tempModule = [];
        for(var m = 0; m<mlen; m++) {
            var mdl = moduleData[m];
            if(mdl.moduleStatus == 'Y') {
                tempModule.push(mdl);
                mdl.permissions = setPermissions(permissionData, mdl.moduleId); // 模块与权限匹配到了
            }
        }
        var notInModulePers =  getNotInModulePers(permissionData, "moduleId");  // 得到没有分配到模块的权限

        initLeftNav(moduleData);
        setPageModulePanel(moduleData);
        setNoInModulePanelModulePanel(notInModulePers);
        canDragSetting();
    });
}

function setPageModulePanel(modules){
    var len = modules.length;
    var modulePanels = $("#modulePanels");
    for(var i = 0; i<len; i++) {
        modulePanels.append(getModulePanel(modules[i]));
    }
}
function getLeftNaveOneHtml(module) {
    var liHtmlTest = [];
    liHtmlTest.push('<li hrett="'+ module.moduleId +'">')
    liHtmlTest.push(module.moduleName);
    liHtmlTest.push("</li>");
    return liHtmlTest.join("");
}

// 右边面板的
function setNoInModulePanelModulePanel(permissions) {
    var ul = $("#otherModule").children("ul")[0];
    $(ul).append(getNoModulePermissionsHtmlTest(permissions));
}
function getModulePanel(module){
    var outtest = [];
    outtest.push('<div class="panel modulePanel" ></div>');
    var headingtest = [];
    headingtest.push('<div class="panel-heading" data-id="'+ module.moduleId+ '" >');
    headingtest.push('<div><span>'+ module.moduleName + '</span>');
    headingtest.push('<small><span style="margin-right:3px; margin-left:10px">简介:</span>');
    if(module.moduleDes)        headingtest.push('<span>' + module.moduleDes +'</span></small>');
    else        headingtest.push('<span>暂无简介</span></small>');
    headingtest.push('</div>');
    headingtest.push('<div style="float:right">');
    headingtest.push('<span  class="glyphicon glyphicon-edit" onclick="showEdit(\''+ module.moduleId +'\')"></span>');
    headingtest.push('<span  class="glyphicon glyphicon-remove" onclick="delModule(this)"></span>');
    headingtest.push('</div>');
    headingtest.push('<p class="clearfix"></p>');
    headingtest.push('</div>');
    var bodytest = [];
    bodytest.push('<div class="panel-body" style="padding-top:0">');
    bodytest.push('<ul class="connectedSortable">');
    bodytest.push(getHasModulePermissionsHtmlTest(module.permissions).join(""));
    bodytest.push('</ul>');
    var outEl = $(outtest.join(""));
    var headingEl = $(headingtest.join(""));
    var bodyEl = $(bodytest.join(""));
    outEl.append(headingEl);
    outEl.append(bodyEl);
    return outEl;
}

function getHasModulePermissionsHtmlTest(permissions){
    return getPermissionsHtmlTest(permissions, "col-md-12","");
}
function getNoModulePermissionsHtmlTest(permissions){
    return getPermissionsHtmlTest(permissions,"col-md-12","" );
}
function getPermissionsHtmlTest(permissions, clazz, style){
    var permissionsHtmlTest = [];
    if(permissions!=undefined && permissions!=null && permissions!="") {
        var len = permissions.length;
        for(var i =0;i<len; i++ ) {
            var permission = permissions[i];
            permissionsHtmlTest .push('<li class="'+ clazz +'" style="'+style+'"   data-id = "'+permission.permissionId+'">'+ permission.permissionZhname +'</li>');
        }
    }
    return permissionsHtmlTest;
}


// 右边面板相关的
function appendModulePanel(module) {
    var modulePanels = $("#modulePanels");
    var panel = getModulePanel(module);
    modulePanels.append(panel);
}

function updateModule(moduleId, module) {
    var oldModule = getModule(moduleId);
    oldModule.moduleName = moduleName;
    oldModule.moduleDes = moduleDes;
}
function getModulePanelByModuleId(moduelId) {
    var modulePanels = $("#modulePanels").children(".modulePanel");

    for(var i =0, len = modulePanels.length; i<len; i++ ){
        var modulePanel = modulePanels[i];
        var panelHeading = $(modulePanel).children(".panel-heading");
        if($(panelHeading).attr("data-id")==moduelId) {
            return modulePanel;
        }
    }
    return null;
}
function updBodyModulePanel(module) {
    var modulePanel = getModulePanelByModuleId(module.moduleId);
    updateModuelPanelTitle(modulePanel,module.moduleName);
    updateModulePanelDes(modulePanel, module.moduleDes);
}
function updateModuelPanelTitle(modulePanel, newTitle) {
    var heading = $(modulePanel).children(".panel-heading")
    var titleDiv = $(heading[0]).children("div")
    var span = $(titleDiv[0]).children("span")
    $(span).text(newTitle);
}
function updateModulePanelDes(modulePanel, newDes) {
    var heading = $(modulePanel).children(".panel-heading")
    var titleDiv = $(heading[0]).children("div");
    var span= $(titleDiv[0]).find("small span")[1];
    $(span).text(newDes);
}
// 事件相关
function canDragSetting() {
    $( ".connectedSortable" ).sortable({
        connectWith: ".connectedSortable",
        stop:stopFun
    }).disableSelection();
}
function stopFun( event, ui ) {
    var dragEl = ui.item[0];
    var moduleEl = $(dragEl).parents()[2];
    var moduleTitle = $(moduleEl).children(".panel-heading")[0];
    var moduleId = $(moduleTitle).attr("data-Id");
    var permissionId = $(dragEl).attr("data-Id");
    updModulePer(moduleId, permissionId);
}



// 左边条相关
function initLeftNav(modules) {
    var ulEl = $($("#navbar").children("ul"));
    var lisHtmlTest = [];
    for(var i = 0,len = modules.length; i<len; i++ ){
        lisHtmlTest.push(getLeftNaveOneHtml(modules[i]));
    }
    ulEl.append(lisHtmlTest.join(""));
    navLiLietener();
}
function deleteLeftNav(moduleId) {
    var ulEl = $($("#navbar").children("ul"));
    var lis = ulEl.find("li");
    $.each(lis,function (index, el) {
        if($(el).attr("hrett") == moduleId) {
            el.remove();
            return ;
        }
    })
}
function appendLeftNav(module) {
    var ulEl = $($("#navbar").children("ul"));
    ulEl.append(getLeftNaveOneHtml(module));
    navLiLietener();
}
function updateLeftNavTitle(module) {
    var ulEl = $($("#navbar").children("ul"));
    var lis = ulEl.find("li");
    $.each(lis,function (index, el) {
        if($(el).attr("hrett") == module.moduleId) {
            $(el).text(module.moduleName);
            return ;
        }
    })
}

// 左边事件
function navLiLietener() {
    var lis = $("#navbar").find("li");
    lis.on("click",function (e) {
        var moduleId = $(this).attr("hrett");
        var modulePanel = getModulePanelByModuleId(moduleId);
        window.scrollTo(0,$(modulePanel)[0].offsetTop+108);
    })
}
function windowScrollListener() {
    var $windowtemp = $(window)
    $windowtemp.on("scroll",function (){
        scrollNavListener($windowtemp);
    })
}
function scrollNavListener($window) {
    var nav = $("#navbar");
    if($window[0].scrollY >= 108) {
        nav.css("top","10px");
    } else {
        var souHeight = 108;
        var height = souHeight- $window[0].scrollY+8;
        nav.css("top",height+ "px");
    }
}


function initModules() {
    initObj.modules =  $.ajax({url:"/module/queryAll",async:false});
}
function getModules() {
    return initObj.modules.responseJSON;
}
function addModule(module){
    initObj.modules.responseJSON.push(module);
}
function getModule(moduleId) {
    var modules = getModules();
    var len = modules.length;
    for(var i =0; i<len; i++) {
        var module = modules[i];
        if(module.moduleId == moduleId) {
            return module;
        }
    }
    return null;
}
function setPermissions(permissionData,checkId){
    var permissions = [];
    var plen = permissionData.length;
    for(var p = 0; p<plen; p++){
        var per = permissionData[p];
        if(per!=null){
            if(per.permissionStatus === 'Y'){
                if(per.moduleId === checkId) {
                    permissions.push(per);
                    per = null;
                }
            }
        }
    }
    return permissions;
}
function getNotInModulePers(pers, molCol){
    var len = pers.length;
    var notInpers = [];
    for(var i = 0; i< len; i++) {
        if(!pers[i][molCol]){
            notInpers.push(pers[i]);
        }
    }
    return notInpers;
}
function getlis2PermissionIds(lis) {
    var len = lis.length;
    var permissionIds = [];
    for(var i=0; i<len ; i++ ) {
        var li = lis[i];
        permissionIds.push($(li).attr("data-id"))
    }
    return permissionIds.join(",");
}


function showAdd(){
    validator('addForm');
    $("#addButton").removeAttr("disabled");
    $("#addModal .modal-header input[data-flag=flag]").val("addForm");
    $("#addModal .modal-header h4[data-tit=title]").text("添加模块管理信息");
    $("#addButton").text("添加");
    $("#addModal").modal('show');
}
function delModule(el) {
    swal(
        {title:"",
            text:"您确定要删除此模块吗",
            type:"warning",
            showCancelButton:true,
            confirmButtonColor:"#DD6B55",
            confirmButtonText:"我确定",
            cancelButtonText:"再考虑一下",
            closeOnConfirm:false,
            closeOnCancel:false
        },function(isConfirm){
            if(isConfirm)
            {
                delSubmit("/module/delete", el);
            } else {
                swal({title:"",
                    text:"已取消",
                    confirmButtonText:"确认",
                    type:"success"})
            }
        })
}
function showEdit(moduleId) {
    $("#addModal .modal-header input[data-flag=flag]").val("editForm");
    $("#addModal .modal-header h4[data-tit=title]").text("修改模块管理信息");
    $("#addButton").text("保存");
    $("#addButton").removeAttr("disabled");
    var module = getModule(moduleId);
   $("#addForm").fill(module);
    validator('addForm');
    $("#addModal").modal('show');
}
function closeModal(){
    $("#addModal").modal("hide");
    $("input[type=reset]").trigger("click");
    $("#addForm").data('bootstrapValidator').destroy(); // 销毁此form表单
    $("#addForm").data('bootstrapValidator', null);// 此form表单设置为空
}

function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            moduleDes: {
                message: '模块描述验证失败',
                validators: {
                    stringLength: {
                        max: 100,
                        message: "模块描述长度不能超过100个字符"
                    }
                }
            },
            moduleName: {
                message: '模块名称验证失败',
                validators: {
                    notEmpty: {
                        message: '模块名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 6,
                        message: '模块名称必须在1至6个字符之间'
                    },
                    regexp: {
                        regexp: /^[^$&,""\|''\s]+$/,
                        message: '模块名称不允许存在符号'
                    },
                    remote: {
                        url: '/module/checkModuleName',//验证地址
                        message: '该模块名称已存在',//提示消息
                        delay :  2000,
                        data: {
                            moduleId: $("input[name=moduleId]").val(),
                            moduleName: $("input[name=moduleName]").val()
                        },
                        type: 'POST'//请求方式
                    }
                }
            }
        }
    }) .on('success.form.bv', function (e) {
        var title =$("#addModal .modal-header input[data-flag=flag]").val();
        if(title === "addForm") {
            formSubmit("/module/insert", "addModal",formId, title);
        }else if (title === "editForm"){
            formSubmit("/module/edit", "addModal",formId, title);
        }
    })
}
function formSubmit(url, modalId ,formId, flag) {
    $.post(url,
        $("#" + formId).serialize(),
        function(data) {
            var title = "修改";
            if(flag === "addForm") {
                title = "添加";
            }
            if (data.controllResult.result == "success") {
                swal({
                    title:"",
                    text:data.controllResult.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"},function (){
                    if(flag === "addForm") {
                        appendLeftNav(data.module);
                        addModule(data.module);
                        appendModulePanel(data.module);
                        canDragSetting();
                    } else {
                        var inputs = $("#" + formId).find("input");
                        var textarea = $("#" + formId).find("textarea");
                        var module = {}
                        for(var i = 0,len = inputs.length; i<len; i++) {
                            var input = inputs[i];
                            if( $(input).attr("name")==="moduleId") {
                                module.moduleId =  $(input).val();
                            }
                            if($(input).attr("name") === "moduleName") {
                                module.moduleName = $(input).val();
                            }
                        }
                        var moduleTemp = getModule(module.moduleId);
                        moduleTemp.moduleName =module.moduleName;
                        moduleTemp.moduleDes = textarea.val();
                        updBodyModulePanel(moduleTemp);
                        updateLeftNavTitle(moduleTemp);
                    }
                    closeModal();
                });// 提示窗口, 修改成功
            } else if (data.controllResult.result == "fail") {
                swal({title:"",
                    text:data.controllResult.message,
                    confirmButtonText:"确认",
                    type:"error"},function(isc) {
                            closeModal();
                });
                $("#"+formId).removeAttr("disabled");
            }

        }, "json"
    )
}
function addSubmit(){
    setTimeout(function() {
        $("#addForm").data('bootstrapValidator').validate();
        if($("#addForm").data('bootstrapValidator').isValid()) {
            $("#addButton").attr("disabled","disabled");
        } else {
            $("#addButton").removeAttr("disabled");
        }
    }, 300);
}
function delSubmit(url, el) {
    var modulePanel = $(el).parents(".panel").first();
    var moduleTitle =modulePanel.find(".panel-heading")[0];
    var lis =modulePanel.find("li");
    var otherModule = $("#otherModule");
    var otherul = otherModule.children("ul")[0];
    $(otherul).append(lis);
    var moduleId = $(moduleTitle).attr("data-id");
    var perIds = getlis2PermissionIds(lis);
    $.get(url + "?moduleId="+ moduleId,function (data) {
        if(data.result === "success") {
            swal({title:"",
                text:data.message,
                type:"success",
                confirmButtonText:"确认",
            },function(){
                modulePanel.remove();
                deleteLeftNav(moduleId);
            })
        } else {
            swal({title:"",
                text:data.message,
                type:"error",
                confirmButtonText:"确认",
            });
        }

    })
}
function updModulePer(moduleId, permissionId){
    if(!moduleId) {moduleId = "";}
    $.post("/module/updPer",
        "moduleId="+moduleId+"&permissionId="+permissionId,
        function (data) {
            if(data.result=== "fail") {
                swal({title:"",
                    text:data.message+",请刷新页面重试",
                    type:"error",
                    confirmButtonText:"点击刷新",
                },function(isConfirm){
                    if(isConfirm)
                    {
                        itemOnclik1();
                    }
                });
            }
        }
    )
}