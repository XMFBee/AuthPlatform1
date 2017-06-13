/*
$(function () {
    $('#table').bootstrapTable('hideColumn', 'id');

    $("#userNameSelect").select2({
            language: 'zh-CN'
        }
    );
    $("#roleNameSelect").select2({
            language: 'zh-CN'
        }
    );
    $("#userNameSelect1").select2({
            language: 'zh-CN'
        }
    );
    $("#roleNameSelect1").select2({
            language: 'zh-CN'
        }
    );

    //绑定Ajax的内容
    $.getJSON("/table/queryType", function (data) {
        $("#userNameSelect").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#userNameSelect").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })
//            $("#addSelect").on("select2:select",
//                    function (e) {
//                        alert(e)
//                        alert("select2:select", e);
//            });

    $.getJSON("/table/queryType", function (data) {
        $("#roleNameSelect").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#roleNameSelect").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })

    $.getJSON("/table/queryType", function (data) {
        $("#userNameSelect1").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#userNameSelect1").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })

    $.getJSON("/table/queryType", function (data) {
        $("#roleNameSelect1").empty();//清空下拉框
        $.each(data, function (i, item) {
            $("#roleNameSelect1").append("<option value='" + data[i].id + "'>&nbsp;" + data[i].name + "</option>");
        });
    })
});

$("#editForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

$("#addForm").submit(function(){
    $(":submit",this).attr("disabled","disabled");
});

function showAvailable(){
    alert("可用");
}

function showDisable(){
    alert("禁用");
}


function showDel(){
    var row =  $('table').bootstrapTable('getSelections');
    if(row.length >0) {
        swal(
            {title:"",
                text:"您确定要禁用此角色吗",
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
                    swal({title:"",
                        text:"禁用成功",
                        type:"success",
                        confirmButtonText:"确认",
                    },function(){
                    })
                }
                else{
                    swal({title:"",
                        text:"已取消",
                        confirmButtonText:"确认",
                        type:"error"})
                }
            })
    }else{
        swal({
            title:"",
            text: "请先选择要禁用的角色", // 主要文本
            confirmButtonColor: "#DD6B55", // 提示按钮的颜色
            confirmButtonText:"确定", // 提示按钮上的文本
            type:"warning"}) // 提示类型
    }
}

function showAdd(){
    $("input[type=reset]").trigger("click");
    $("#addButton").removeAttr("disabled");
    $("#add").modal('show');
}

$(document).ready(function() {
    $("#addForm").validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules : {
            roleDes : {
                required : true
            },
            roleName : {
                required : true
            },
        },
        messages : {
            roleName : "请输入角色名称",
            roleDes : "请输入角色描述"
        },
        errorPlacement : function(error, element) {
            $("#addButton").removeAttr("disabled");
                element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
                element.closest('.form-group').append(error);
        },
        highlight : function(element) {
            $("#addButton").removeAttr("disabled");
                $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success : function(label) {
            $("#addButton").removeAttr("disabled");
                var el=label.closest('.form-group').find("input");
                el.next().remove();
                el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
                label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
                label.remove();
        },
        submitHandler: function(form) {
            $.post("/table/add",
                $("#editForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#add").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title:"",
                            text: data.message, // 主要文本
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type:"success"}) // 提示类型
                    } else if (data.result == "fail") {
                        swal({title:"",
                            text:"添加失败",
                            confirmButtonText:"确认",
                            type:"error"})
                    }
                }, "json"
            );
        }
    })

    $("#editForm").validate({
        errorElement : 'span',
        errorClass : 'help-block',
        rules : {
            roleDes : {
                required : true
            },
            roleName : {
                required : true
            },
        },
        messages : {
            roleName : "请输入角色名称",
            roleDes : "请输入角色描述"
        },
        errorPlacement : function(error, element) {
            $("#editButton").removeAttr("disabled");
            element.next().remove();
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);
        },
        highlight : function(element) {
            $("#editButton").removeAttr("disabled");
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        success : function(label) {
            $("#editButton").removeAttr("disabled");
            var el=label.closest('.form-group').find("input");
            el.next().remove();
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },
        submitHandler: function(form) {
            $.post("/table/edit",
                $("#editForm").serialize(),
                function (data) {
                    if (data.result == "success") {
                        $("#edit").modal('hide'); // 关闭指定的窗口
                        $('#table').bootstrapTable("refresh"); // 重新加载指定数据网格数据
                        swal({
                            title:"",
                            text: data.message, // 主要文本
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type:"success"}) // 提示类型
                    } else if (data.result == "fail") {
                        swal({title:"",
                            text:"修改失败",
                            confirmButtonText:"确认",
                            type:"error"})
                    }
                }, "json"
            );
        }
    })
});*/



// 自己的



var initObj = {};
var trees = {};
$(function(){
    var roles = "系统超级管理员,系统普通管理员";
    $.post("/user/isLogin/"+roles, function (data) {
        if(data.result == 'success') {
            var roleId;
            initAll();
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
function resetModules() {
    var modulesAjaxVar =  $.ajax({url:"/module/queryAll",async:false});
    initObj.modules = modulesAjaxVar.responseJSON;
}
function resetRole(){
    var rolesAjaxVar = $.ajax({url:"/role/queryAll",async:false});
    initObj.roles  = rolesAjaxVar.responseJSON;
}
function resetPermissions(){
    var permissionsAjaxVar =   $.ajax({url:"/permission/noStatusQueryAll",async:false});
    initObj.permissions = permissionsAjaxVar.responseJSON;
}
function resetRolePermission(roleId){
    var rolePermissoinsAjaxVar =$.ajax({url:"/role/permissions/"+roleId ,async:false});
    initObj.rolePermissions= rolePermissoinsAjaxVar.responseJSON;
}


function initAll(){
    resetModules();
    resetPermissions();
    resetRole();

    setTimeout(function(){
        initRoleTabs();  //初始左侧菜单栏
    },300);
    setTimeout(function(){
        initObj.role = initObj.roles[0];
        initObj.rolePermissions = null;
        var rolePermissionsAjaxVar = $.ajax({url:"/role/permissions/"+initObj.role.roleId ,async:false});
        initObj.rolePermissions =  rolePermissionsAjaxVar.responseJSON;
        setPageTitle();
        initDnyTree("#dnyTree",initObj.modules, initObj.rolePermissions);//初始了树
        trees.staTree = initStaticTree("#staTree", initObj.permissions, initObj.modules, initObj.rolePermissions)
    },500);
}

function initRoleTabs() {
    var tabMsg = {data: initObj.roles, barId:"#bar", panelId: "#home",idCol: "roleId"};
    initTabs(tabMsg);
}
function initTabs(tabMsg) {
    $(tabMsg.barId).empty();
    var data = tabMsg.data;
    var bar = $(tabMsg.barId);
    var panelId = tabMsg.panelId;
    var idCol = tabMsg.idCol;
    for(var i = 0,len = data.length; i<len; i++ ){
        var oneBar = [];
        var role = data[i];
        if(i===0){
            oneBar.push("<li class='active'>");
            // setId(role.roleId);
        } else {
            oneBar.push("<li>");
        }
        oneBar.push("<a href='"+panelId + "' data-toggle='tab' data-id =" +role.roleId +"  onclick='resetRightPanel(this)'>");
        oneBar.push(role.roleName);
        oneBar.push("</a></li>");
        bar.append(oneBar.join(""));
    }
    /*var recyclebin = '<li><a href="#recyclebin" data-toggle="tab"  onclick="recyclebin()"><span class="glyphicon glyphicon-trash" style="margin-right:6px"></span>回收站</a></li>'*/
    /*bar.append(recyclebin);*/
}

function setPageTitle(){
    var role = initObj.role;
    var title = $("#home .title:eq(0)")
    var roleNameNode = "<span>" + role.roleName + "</span>";
    var roleDesNode = "<br /><small style='font-size:9px;color:#999; position:relative; margin-top: 5px;margin-left:10px;'>简介: "+ role.roleDes +"</small>"
    title.find("h3").html(roleNameNode+ roleDesNode);
    title.find("input:eq(0)").val(role.roleId);
}
/*
function setNavName () {
    var role = initObj.role;
    var navul = $("#bar");
    var navlia = navul.find("a[data-id=" + role.roleId + "]");
    $(navlia).text(role.roleName);
}
*/

function resetRightPanel(thisEl){ // 用于点击更换右侧数值
    var roleId = $(thisEl).attr("data-id");
    var curObj,
        modules,
        rolePermissions;

    curObj  = initObj;
    modules = curObj.modules;
    resetRolePermission(roleId);  // 设置当前的rolePermissions
    rolePermissions = curObj.rolePermissions;
    setRole(roleId);        // 设置当前的role
    setPageTitle();
    reloadDny(modules, rolePermissions);
    $("#roleId").val(roleId);
}

function setRole(roleId){
    var roles = initObj.roles;
    var len = roles.length;
    for(var i = 0; i<len; i++) {
        var role = roles[i];
        if(role.roleId ===roleId){
            initObj.role = role;
            return;
        }
    }
}

// 右边面板的树
function initDnyTree(treeid,modules, rolePermissions){
    $(treeid).tree({
        primaryKey: 'id',
        uiLibrary: 'materialdesign',
        childrenField: 'permissions'
    });
    reloadDny(modules, rolePermissions);
    return dnyTree;
}
function initStaticTree(treeid, permissions, modules, rolePermissions) {		//添加了方法
    var treenodes = bean4StaTreeData(permissions,modules, rolePermissions);
    if(treenodes.length) {
        var staTree = $(treeid).tree({
            primaryKey: 'id',
            uiLibrary: 'materialdesign',
            childrenField: 'permissions',
            checkedField: 'checked',
            dataSource: treenodes,
            checkboxes: true
        })
        return staTree;
    }
    appendNotHasElMsg(treeid, "暂无权限");
    // TODO
    $("#subRolePer").remove();
    return null;
}

function reloadStatic(staTree,permissions, modules, rolePermissions){
    var nodeData = bean4StaTreeData(permissions,modules, rolePermissions);
    if(nodeData && nodeData.length) {
        reloadTree("#staTree", nodeData)
        return true;
    }
    return false;
}
function reloadDny(modules, rolePermissions) {
    var nodeData =  bean4DnyTreeData(modules,rolePermissions);
    reloadTree("#dnyTree", nodeData)
    if(nodeData.length) {
        removeNotHasElMsg("#dnyTree");
    } else {
        appendNotHasElMsg("#dnyTree", "暂无权限")
    }
}
function reloadTree(treeEl, data){
    $(treeEl).tree().render(data);
    $(treeEl).tree().expandAll();
}

function appendNotHasElMsg(el, msg) {
    removeNotHasElMsg(el);
    var appElHtml = "<h2 class='msg' style='color:#aaa; font-family:\"微软雅黑\"'>"+ msg +"</h2>"
    console.log($(el).append(appElHtml));
}
function removeNotHasElMsg(el) {
    $(el).children(".msg").remove();
}

function bean4DnyTreeData(modules,rolePermissions){
    var molnodes= [];
    modules = setModulePermissions(rolePermissions, modules);
    for(var i = 0,len = modules.length; i<len; i++ ){
        var module = modules[i];
        var molnode = {};
        var pernodes = [];
        molnode.id = "module-"+module.moduleId;
        molnode.text = module.moduleName;
        for(var j=0,jlen = rolePermissions.length; j<jlen; j++) {
            var permission = rolePermissions[j];
            if(permission.moduleId === module.moduleId) {
                var pernode ={};
                pernode.id = "permission-"+permission.permissionId;
                pernode.text = permission.permissionZhname;
                pernodes.push(pernode);
            }
        }
        if(pernodes.length > 0) {
            molnode.permissions = pernodes;
            molnodes.push(molnode);
        }
    }
    var otherMolnode = {};
    var otherPernodes = [];
    otherMolnode.id = "module-";
    otherMolnode.text ="其它";
    for(var i =0,len =rolePermissions.length; i<len; i++ ){
        var permission = rolePermissions[i];
        if(permission.moduleId === "" || permission.moduleId === null) {
            var pernode ={};
            pernode.id = "permission-"+permission.permissionId;
            pernode.text = permission.permissionZhname;
            otherPernodes.push(pernode);
        }
    }
    if(otherPernodes && otherPernodes.length >0) {
        otherMolnode.permissions = otherPernodes;
        molnodes.push(otherMolnode);
    }
    return molnodes;
}
function bean4StaTreeData(permissions, modules, rolePermissions){
    var molnodes = [];
    modules = setModulePermissions(permissions, modules);
    for(var i = 0,len = modules.length; i<len; i++){
        var pernodes = [];
        var module = modules[i];
        var molnode = {};
        molnode.id = "module-" + module.moduleId;
        molnode.text = module.moduleName;
        for(var j = 0,jlen=permissions.length; j<jlen; j++){
            var pernode = {};
            var permission = permissions[j];
            if(permission.permissionStatus==='Y'){
                pernode.id = "permission-"+permission.permissionId;
                pernode.text=permission.permissionZhname;
                if(permission.moduleId === module.moduleId){
                    for(var k = 0,klen=rolePermissions.length; k<klen; k++) {
                        if(rolePermissions[k].permissionId === permission.permissionId){
                            pernode.checked = true;
                            break;
                        }else {
                            pernode.checked = false;
                        }
                    }
                    pernodes.push(pernode);
                }
            }
        }
        if(pernodes.length >0){
            molnode.permissions =pernodes;
            molnodes.push(molnode);
        }
    }
    var otherMolnode = getModuleByAlonePers(permissions, rolePermissions);
    if(otherMolnode.permissions && otherMolnode.permissions.length >0 ) {
        molnodes.push(otherMolnode);
    }
    return molnodes;
}
    // 没有在模块中的权限,在树中创建另一个伪模块
function getModuleByAlonePers(permissions, rolePermissions){
    var molnode = {};
    var pernodes = [];
    molnode.id = "module-";
    molnode.text = "其它";
    for(var i =0,len = permissions.length; i<len; i++) {
        var permission = permissions[i];
        if(permission.permissionStatus==='Y' && (permission.moduleId === "" || permission.moduleId === null)) {
            var pernode = {};
            pernode.id = "permission-"+permission.permissionId;
            pernode.text=permission.permissionZhname;
            for(var k = 0,klen=rolePermissions.length; k<klen; k++) {
                if(rolePermissions[k].permissionId === permission.permissionId){
                    pernode.checked = true;
                    break;
                }else {
                    pernode.checked = false;
                }
            }
            pernodes.push(pernode);
        }
    }
    if(pernodes && pernodes.length >0) {
            molnode.permissions =pernodes;
    }
    return molnode;
}

function setModulePermissions(permissions, modules){
    for(var m = 0,mLen = modules.length; m<mLen; m++) {
        var module = modules[m]
        var mPers = [];
        for(var p=0,pLen = permissions.length; p<pLen; p++){
            var permission = permissions[p]
            var pmId = permission.moduleId;
            if(pmId === module.moduleId){
                mPers.push(permission);
            }
        }
        module.permission = mPers;
    }
    return modules;
}
function showEditPermission(){
    var curObj = initObj;
    var bool = reloadStatic($("#staTree").tree(),curObj.permissions, curObj.modules, curObj.rolePermissions);
    $("#editPermission").modal("show");
    if(!bool) {
        appendNotHasElMsg("#staTree","暂无权限可分配");
    }
}

/**
 *  添加 相关
 * */
/*    function showAdd(){
 $("#addButton").removeAttr("disabled");
 $("#addModal").modal('show');
 var roleNameInput = $("#addForm").find("input[name=roleName]");
 $(roleNameInput).removeAttr("readonly");
 validator('addForm');
 $("#addModal .modal-header> h3").text("添加角色");
 $("#addModal .modal-header> input").val("addForm");
 }*/

// 修改角色相关信息用到的
function showEdit(){
    $("#addButton").removeAttr("disabled");
    $("#addModal").modal('show');
    validator('addForm');
    $("#addModal .modal-header> h4").text("修改角色信息");
    $("#addModal .modal-header input[data-flag=flag]").val("editForm");
    var role = initObj.role;
    var roleNameInput = $("#addForm").find("input[name=roleName]");
    $(roleNameInput).attr("readonly");
    $("#addForm").fill(role);
}
function editSave() {
    $("#addForm").data('bootstrapValidator').validate();
    if ($("#addForm").data('bootstrapValidator').isValid()) {
        $("#editButton").attr("disabled","disabled");
    } else {
        $("#editButton").removeAttr("disabled");
    }
}
function validator(formId) {
    $('#' + formId).bootstrapValidator({
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            roleDes: {
                message: '角色描述验证失败',
                validators: {
                    stringLength: {
                        max: 100,
                        message: "角色描述长度不能超过100字符"
                    }
                }
            }
        }
    }) .on('success.form.bv', function (e) {
        var title =$("#addModal .modal-header input[data-flag=flag]").val();
        if(title === "addForm") {
            formSubmit("/role/insert", "addModal",formId, title);
        }else if (title === "editForm"){
            formSubmit("/role/edit", "addModal",formId, title);
        }
    })
}
function formSubmit(url, modalId ,formId, flag) {
    $.post(url,
        $("#" + formId).serialize(),
        function(data) {
            if (data.result == "success") {
                swal({
                    title:"",
                    text: data.message,
                    confirmButtonText:"确定", // 提示按钮上的文本
                    type:"success"});// 提示窗口, 修改成功
                var roleNameEl = $("#"+formId).find("input[name=roleName]")[0];
                var roleIdEl = $("#"+formId).find("input[name=roleId]")[0];
                var roleDesEl = $("#" + formId).find("textarea")[0];
                var role = {}
                role.roleId = $(roleIdEl).val();
                role.roleName = $(roleNameEl).val();
                role.roleDes = $(roleDesEl).val();
                updateRole(role);
                setPageTitle();
            } else if (data.result == "fail") {
                swal({title:"",
                    text:data.message,
                    confirmButtonText:"确定",
                    type:"error"});
            }
            formModalclose(modalId,formId, "editButton");
        }, "json"
    )

}
function formModalclose(modalId, formId, btnId) {
    $("#"+modalId).modal('hide');
    $("#"+ btnId).removeAttr("disabled");
    $("#" + formId).data('bootstrapValidator').destroy(); // 销毁此form表单
    $('#' + formId).data('bootstrapValidator', null);// 此form表单设置为空
    $("input[type=reset]").trigger("click");
}

// 修改角色权限用到的
function savePermission(){
    var addAndRemoveIds = getAddedAndRemovePermissionIds();
    if(addAndRemoveIds) {
        var roleId = initObj.role.roleId;
        var seachText = "roleId=" + roleId + "&added=" + addAndRemoveIds.added + "&removed=" + addAndRemoveIds.removed;
        $.post("/role/updatePermission",
            seachText,
            function (data) {
                if (data.result == "success") {
                    swal({
                            title: "",
                            text: data.message,
                            confirmButtonText:"确定", // 提示按钮上的文本
                            type: "success"
                        },
                        function (isConfirm) {
                            $("#editPermission").modal("hide");
                            resetRolePermission(roleId);
                            var rolePermissions = initObj.rolePermissions;
                            reloadDny(initObj.modules, rolePermissions);
                        });// 提示窗口, 修改成功
                } else if (data.result == "fail") {
                    swal({
                        title: "",
                        text: data.message,
                        confirmButtonText:"确定", // 提示按钮上的文本
                        type: "error"
                    }, function (isConfirm) {
                        $("#editPermission").modal("hide");
                    });// 提示窗口, 修改成功
                    //$.messager.alert("提示", data.result.message, "info");
                }

            })
    }
}
function getAddedAndRemovePermissionIds(){
    if(trees.staTree) {
        var nodes = trees.staTree.getCheckedNodes();
        var oldRolePermission= initObj.rolePermissions;
        var currPermissionIds = filterPermissionIds(nodes);
        var oldPermissionIds = rolePermissionsObj2permissionIds(oldRolePermission);
        var addedPermissionIds = contrast(currPermissionIds,oldPermissionIds);
        var removedPermissionIds = contrast(oldPermissionIds, currPermissionIds);
        var resultObj = {added: addedPermissionIds.join("|"), removed: removedPermissionIds.join("|")};
        return resultObj;
    } else {
        return null;
    }

}
function  filterPermissionIds(nodes){
    var permissionNodes = removeModuleId(nodes);
    var permissionIds = filterPermission(permissionNodes);
    return permissionIds;
}
function removeModuleId(nodes) {
    var len = nodes.length;
    var moduleReg = /^module-/;
    var permissionReg = /^permission-/;
    var permissionNodes = [];
    for(var i = 0; i<len; i++) {
        var node = nodes[i];
        if(moduleReg.exec(node)) {
            nodes[i] = undefined;
        } else if(permissionReg.exec(node)){
            permissionNodes.push(node);
        }
    }
    return permissionNodes;
}
function filterPermission(permissionNodes){
    var permissionReg = /^permission-/;
    var len = permissionNodes.length;
    var permissionIds = [];
    for(var i = 0; i<len; i++ ){
        var permissionNode = permissionNodes[i];
        if(permissionNode.replace(permissionReg,"","")){
            permissionIds.push(permissionNode.replace(permissionReg,"",""));
        }
    }
    return permissionIds;
}
function rolePermissionsObj2permissionIds(permissions){
    var len = permissions.length;
    var permissionIds = [];
    for(var i = 0; i<len; i++) {
        permissionIds.push(permissions[i].permissionId);
    }
    return permissionIds;
}
function updateRole(role){
    var roles = initObj.roles;
    for(var i = 0, len = roles.length; i<len; i++) {
        var roleTemp = roles[i];
        if(roleTemp.roleId == role.roleId) {
            initObj.role.roleName =  role.roleName;
            initObj.role.roleDes =  role.roleDes;
            return ;
        }
    }
}
function contrast(cur, sou){
    var added = [];
    var ilen = sou.length;
    var jlen = cur.length;
    var tempCurrPermission = cur.slice(0,jlen);
    for(var j = 0; j<jlen; j++){
        for(var i = 0; i<ilen; i++){
            if(tempCurrPermission[j] === sou[i]){
                tempCurrPermission[j] = undefined;
            }
        }
        if(tempCurrPermission[j]){
            added.push(tempCurrPermission[j])
        }
    }
    return added;
}

// 回收站
/* function showDel(){ //删除角色
 swal(
 {title:"",
 text:"您确定要删除此角色吗",
 type:"warning",
 showCancelButton:true,
 confirmButtonColor:"#DD6B55",
 confirmButtonText:"我确定",
 cancelButtonText:"再考虑一下",
 closeOnConfirm:false,
 closeOnCancel:false
 },function(isConfirm){
 if(isConfirm) {
 var role = initObj.role;
 $.get("/role/updateStatus?roleStatus=Y&roleId=" + role.roleId,function (data) {
 swal({title:"",
 text:"删除成功",
 type:"success",
 confirmButtonText:"确认",
 },function(){
 resetRole();
 initRoleTabs();
 })
 })
 } else {
 swal({title:"",
 text:"已取消",
 confirmButtonText:"确认",
 type:"error"})
 }
 })

 }

 function recyclebin(){
 initTable('recycleTable', '/role/recycle'); // 初始化表格

 }

 function todoCell(ele, row, index) {
 var btnhtml = '<button type="button" class="btn btn-info" style="margin-right:5px;" onclick="restore(\''+ row.roleId +'\')">还原</button>'
 return btnhtml;
 }

 function restore(roleId){
 $.get("/role/updateStatus?roleStatus=N&roleId=" + roleId,function (data) {
 swal({title:"",
 text:"还原成功",
 confirmButtonText:"确认",
 type:"success"},function(isConfirm){
 initTable('recycleTable', '/role/recycle');
 resetRole();
 initRoleTabs();
 });
 });
 }*/



