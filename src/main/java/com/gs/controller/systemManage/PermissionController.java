package com.gs.controller.systemManage;

/**
 * @author Administrator
 * @create 2017-04-22 10:06
 */

import ch.qos.logback.classic.Logger;
import com.gs.bean.Module;
import com.gs.bean.Permission;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.PermissionService;
import com.gs.service.RolePermissionService;
import com.gs.service.RoleService;
import com.sun.tracing.dtrace.ModuleName;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author 程燕
 * @create 2017-04-23 18:57
 * @des 权限Controller
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    private Logger logger = (Logger) LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    // baoliu
    @ResponseBody
    @RequestMapping(value = "queryAll")
    public Pager4EasyUI queryByPager(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session){
        // TODO 系统所有管理员可以查看
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)){
                final String status = "Y";
                    Pager pager = new Pager();
                    pager.setPageNo(pageNumber);
                    pager.setPageSize(pageSize);
                    List rows = permissionService.queryByPager(status,pager);
                    int total = permissionService.count(status);
                    Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                    pager4EasyUI.setRows(rows);
                    pager4EasyUI.setTotal(total);
                    return pager4EasyUI;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "blurredQuery")
    public Pager4EasyUI blurredQuery(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpServletRequest req, HttpSession session){
        // TODO 系统所有管理员可以查看
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("登记记录模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                String text = req.getParameter("text");
                String value = req.getParameter("value");
                if(text != null && text!="" && value != null && value != "") {
                    List<Permission> permissions = null;
                    Permission permission= new Permission();
                    Module module = new Module();
                    if(text.equals("名称/描述/所属模块")){ // 当多种模糊搜索条件时
                        permission.setPermissionZhname(value);
                        permission.setPermissionDes(value);
                        module.setModuleName(value);
                    }else if(text.equals("名称")){
                        permission.setPermissionZhname(value);
                    }else if(text.equals("描述")){
                        permission.setPermissionDes(value);
                    }else if(text.equals("所属模块")){
                        module.setModuleName(value);
                    }
                    permission.setModule(module);
                    permissions = permissionService.blurredQuery(pager, permission);
                    pager.setTotalRecords(permissionService.countByBlurred(permission,(User)session.getAttribute("user")));
                    return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
                }else{ // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                    pager.setTotalRecords(permissionService.count());
                    List<Permission> permissions = permissionService.queryByPager(pager);
                    return new Pager4EasyUI<Permission>(pager.getTotalRecords(), permissions);
                }
            }else{
                logger.info("此用户无拥有查看登记记录模糊查询方法的角色");
                return null;
            }

        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "noStatusQueryAll")
    public List noStatusQueryAll(HttpSession session){
        // TODO 系统所有管理员可以查看
        if(SessionUtil.isLogin(session)) {
            return permissionService.queryAll();
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryRecycle")
    public Pager4EasyUI recycleByPager(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session){
        // 无用 但是
        // TODO 系统所有管理员可以查看
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                final String status = "N";
                Pager pager = new Pager();
                pager.setPageNo(pageNumber);
                pager.setPageSize(pageSize);
                List rows = permissionService.queryByPager(status,pager);
                int total = permissionService.count(status);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setRows(rows);
                pager4EasyUI.setTotal(total);
                return pager4EasyUI;
            } else {
                logger.info("此用户无拥有修改权限的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "update")
    public ControllerResult update(Permission permission, HttpSession session){
        // TODO 系统超级管理员可以修改
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                int updCount = permissionService.update(permission);
                if(updCount == 1) {
                    return ControllerResult.getSuccessResult("更改成功");
                }
                return ControllerResult.getFailResult("更改失败");
            } else {
                logger.info("此用户无拥有修改权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "insert")
    public ControllerResult insert(Permission permission, HttpSession session){
        // 无用
        // TODO 系统超级管理员可以添加
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                int addCount = permissionService.insert(permission);
                if(addCount == 1) {
                    return ControllerResult.getSuccessResult("添加成功");
                }
                return ControllerResult.getFailResult("添加失败");
            } else {
                logger.info("此用户无拥有添加权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateStatus")
    public ControllerResult updateStatus(@RequestParam("permissionId") String permissionId , @RequestParam("permissionStatus")String permissionStatus, HttpSession
            session) {
        // 无用
        // TODO 系统超级管理员可以更改
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                permissionStatus = newStatus(permissionStatus);
                final String split = ",";
                List id = Arrays.asList(idsStr2ids(permissionId,split));
                int updCount =permissionService.updateStatus(id, permissionStatus);
                if(updCount == 1) {
                    return  ControllerResult.getSuccessResult("修改状态成功");
                }
                return ControllerResult.getFailResult("修改状态失败");
            } else {
                logger.info("此用户无拥有启用或禁用权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateStatuses")
    public ControllerResult updateStatuses(@RequestParam("permissionIdsStr") String permissionIdsStr, @RequestParam("status")String permissionStatus, HttpSession session) {
        // 无用
        // TODO 系统超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                final String split = ",";
                permissionStatus = newStatus(permissionStatus);
                List ids = Arrays.asList(idsStr2ids(permissionIdsStr,split));
                int updCount =permissionService.updateStatus(ids, permissionStatus);
                if(updCount == ids.size()) {
                    return  ControllerResult.getSuccessResult("修改状态成功");
                }
                return ControllerResult.getFailResult("修改状态失败");
            } else {
                logger.info("此用户无拥有启用或禁用权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    @ResponseBody
    @RequestMapping(value = "canUse")
    public List<Permission> canUse(HttpSession session){
        // 无用
        // TODO 所有系统管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                return permissionService.queryAll("Y");
            } else {
                logger.info("此用户无拥有启用或禁用权限的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "recycle")
    public List<Permission> recycle(HttpSession session){
        // 无用
        // TODO 所有系统管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                return permissionService.queryAll("N");
            } else {
                logger.info("此用户无拥有启用或禁用权限的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "checkPerZhName")
    public Map checkPerZhName(Permission permission,  HttpSession session) {
        // TODO 所有系统管理员
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        int countPerZhName = permissionService.countByPerName(permission.getPermissionZhname(), permission.getPermissionId());
        if(countPerZhName >0 ) {
            map.put("valid", false);
        } else {
            map.put("valid", true);
        }
        return map;
    }




    private String newStatus(String oldStatus){
        if(oldStatus.equals("Y")){
            return "N";
        }
        return "Y";
    }

    private String[] idsStr2ids(String idsStr,String split){
        return idsStr.split(split);
    }



}
