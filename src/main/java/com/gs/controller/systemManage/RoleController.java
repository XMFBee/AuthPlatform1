package com.gs.controller.systemManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Permission;
import com.gs.bean.Role;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.PermissionService;
import com.gs.service.RolePermissionService;
import com.gs.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 *
 * @author 程燕
 * @create 2017-04-23 18:57
 * @des 角色Controller
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = (Logger) LoggerFactory.getLogger(RoleController.class);
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;

    @RequiresAuthentication
    @RequestMapping(value = "roleIndex" ,method = RequestMethod.GET)
    public ModelAndView tableIndex(HttpSession session){
       ModelAndView mav = new ModelAndView("role/role");
       return mav;
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "insert" )
    public ControllerResult insert(Role role, HttpSession session){
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                int resultCount = roleService.insert(role);
                if(resultCount>0){
                    return  ControllerResult.getSuccessResult("添加成功");
                }
                return ControllerResult.getFailResult("添加失败");
            } else {
                logger.info("此用户无拥有添加角色的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "edit" )
    public ControllerResult edit(Role role, HttpSession session){
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                int resultCount = roleService.update(role);
                if (resultCount > 0) {
                    return ControllerResult.getSuccessResult("修改成功");
                }
                return ControllerResult.getFailResult("修改失败");
            } else {
                logger.info("此用户无拥有添加角色的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("updateStatus")
    public ControllerResult updateStatus(Role role, HttpSession session) {
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                String status = role.getRoleStatus();
                String newStatus = getNewStatus(status);
                role.setRoleStatus(newStatus);
                int countResult = roleService.updateStatus(role);
                if(countResult == 1) {
                    return ControllerResult.getSuccessResult("修改状态成功");
                }
                return ControllerResult.getFailResult("修改状态失败");
            } else {
                logger.info("此用户无拥有更改角色状态的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "queryAll")
    public List<Role> queryAll(HttpSession session){
        // TODO 所有人
        if(SessionUtil.isLogin(session)) {
            return roleService.queryAll("Y");
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "recycle")
    public Pager4EasyUI queryRecycle(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session){
        // TODO 所有管理员
        if(SessionUtil.isLogin(session)) {
            final String status = "N";
            int total = roleService.count(status);
            Pager pager = new Pager();
            pager.setPageNo(pageNumber);
            pager.setPageSize(pageSize);

            Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
            pager4EasyUI.setRows(roleService.queryByPager(status, pager));
            pager4EasyUI.setTotal(total);
            return pager4EasyUI;
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "role2CheckBox")
    public  List<ComboBox4EasyUI> queryAll2CheckBox(HttpSession session){
        // TODO 所有人
        if(SessionUtil.isLogin(session)) {
            User user = (User)session.getAttribute("user");
            List<ComboBox4EasyUI> comboBoxes = new ArrayList<ComboBox4EasyUI>();
            String roles1 = "汽车公司人力资源管理部";
            String roles2 = "系统超级管理员,系统普通管理员";
            String roles3 = "公司超级管理员,公司普通管理员";
            ComboBox4EasyUI comboBox4EasyUI1 = new ComboBox4EasyUI();
            comboBox4EasyUI1.setId("7ff4f1c5-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI1.setText("系统超级管理员");
            ComboBox4EasyUI comboBox4EasyUI2 = new ComboBox4EasyUI();
            comboBox4EasyUI2.setId("80095901-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI2.setText("系统普通管理员");
            ComboBox4EasyUI comboBox4EasyUI3 = new ComboBox4EasyUI();
            comboBox4EasyUI3.setId("8010cecf-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI3.setText("公司超级管理员");
            ComboBox4EasyUI comboBox4EasyUI4 = new ComboBox4EasyUI();
            comboBox4EasyUI4.setId("80195a53-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI4.setText("公司普通管理员");
            ComboBox4EasyUI comboBox4EasyUI5 = new ComboBox4EasyUI();
            comboBox4EasyUI5.setId("8020abdc-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI5.setText("汽车公司接待员");
            ComboBox4EasyUI comboBox4EasyUI6 = new ComboBox4EasyUI();
            comboBox4EasyUI6.setId("802895f4-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI6.setText("汽车公司总技师");
            ComboBox4EasyUI comboBox4EasyUI7 = new ComboBox4EasyUI();
            comboBox4EasyUI7.setId("802fd749-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI7.setText("汽车公司技师");
            ComboBox4EasyUI comboBox4EasyUI8 = new ComboBox4EasyUI();
            comboBox4EasyUI8.setId("80360abc-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI8.setText("汽车公司学徒");
            ComboBox4EasyUI comboBox4EasyUI9 = new ComboBox4EasyUI();
            comboBox4EasyUI9.setId("803f0384-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI9.setText("汽车公司销售人员");
            ComboBox4EasyUI comboBox4EasyUI10 = new ComboBox4EasyUI();
            comboBox4EasyUI10.setId("804532c4-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI10.setText("汽车公司财务人员");
            ComboBox4EasyUI comboBox4EasyUI11 = new ComboBox4EasyUI();
            comboBox4EasyUI11.setId("804cc69c-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI11.setText("汽车公司采购人员");
            ComboBox4EasyUI comboBox4EasyUI12 = new ComboBox4EasyUI();
            comboBox4EasyUI12.setId("8052af73-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI12.setText("汽车公司库管人员");
            ComboBox4EasyUI comboBox4EasyUI13 = new ComboBox4EasyUI();
            comboBox4EasyUI13.setId("805a2613-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI13.setText("汽车公司人力资源管理部");
            ComboBox4EasyUI comboBox4EasyUI14 = new ComboBox4EasyUI();
            comboBox4EasyUI14.setId("8067fa42-3205-11e7-bc72-507b9d765567");
            comboBox4EasyUI14.setText("车主");
            if(RoleUtil.checkRoles(roles1)) {
                    comboBoxes.add(comboBox4EasyUI5);
                    comboBoxes.add(comboBox4EasyUI6);
                    comboBoxes.add(comboBox4EasyUI7);
                    comboBoxes.add(comboBox4EasyUI8);
                    comboBoxes.add(comboBox4EasyUI9);
                    comboBoxes.add(comboBox4EasyUI10);
                    comboBoxes.add(comboBox4EasyUI11);
                    comboBoxes.add(comboBox4EasyUI12);
                    comboBoxes.add(comboBox4EasyUI13);
                    comboBoxes.add(comboBox4EasyUI14);
                    return comboBoxes;
            }else if (RoleUtil.checkRoles(roles2)){
                comboBoxes.add(comboBox4EasyUI1);
                comboBoxes.add(comboBox4EasyUI2);
                return comboBoxes;
            }else if (RoleUtil.checkRoles(roles3)){
                comboBoxes.add(comboBox4EasyUI3);
                comboBoxes.add(comboBox4EasyUI4);
                comboBoxes.add(comboBox4EasyUI5);
                comboBoxes.add(comboBox4EasyUI6);
                comboBoxes.add(comboBox4EasyUI7);
                comboBoxes.add(comboBox4EasyUI8);
                comboBoxes.add(comboBox4EasyUI9);
                comboBoxes.add(comboBox4EasyUI10);
                comboBoxes.add(comboBox4EasyUI11);
                comboBoxes.add(comboBox4EasyUI12);
                comboBoxes.add(comboBox4EasyUI13);
                comboBoxes.add(comboBox4EasyUI14);
                return comboBoxes;
            }else {
               return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    // baoliu
    @ResponseBody
    @RequestMapping(value = "/permissions/{id}")
    public List<Permission> queryPermissions(@PathVariable("id") String id, HttpSession session){
        // TODO 所有管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                return rolePermissionService.queryPermissions(id, "Y");
            }  else {
                logger.info("此用户无拥有更改角色状态的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping(value = "/updatePermission" )
    public ControllerResult updatePermission(@RequestParam("roleId") String roleId, @RequestParam("added") String addedPerIdsStr, @RequestParam("removed")String removedPerIdsStr, HttpSession session){
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("删除角色权限");
                List<String> removedPerIds = Arrays.asList(perIdsStr2perIds(removedPerIdsStr, "\\|"));
                List<String> addedPerIds = Arrays.asList(perIdsStr2perIds(addedPerIdsStr, "\\|"));
                removedPerIds = removeEmpStr(removedPerIds);
                addedPerIds = removeEmpStr(addedPerIds);
                int addedResultCount = 0;
                if (addedPerIds.size() > 0) {
                    addedResultCount = rolePermissionService.insertList(roleId, addedPerIds);
                }
                int removeResultCount = 0;

                for (String perId : removedPerIds) {
                    removeResultCount += rolePermissionService.removePermission(roleId, perId);
                }
                int removeCount = removedPerIds.size();
                int addCount = addedPerIds.size();
                if (removedPerIds.size() == removeResultCount && addedPerIds.size() == addedResultCount) {
                    return ControllerResult.getSuccessResult("修改权限成功");
                }
                return ControllerResult.getFailResult("修改权限失败");
            } else {
                logger.info("此用户无拥有修改角色权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    private String[] perIdsStr2perIds(String perIdsStr,String split){
        return perIdsStr.split(split);
    }
    private List<String> removeEmpStr(List addedPerIds){
        ArrayList newaddedPerIds = new ArrayList(addedPerIds);
        for(int i = 0,len = newaddedPerIds.size(); i<len; i++) {
            String curStr = (String) newaddedPerIds.get(i);
            if(newaddedPerIds.get(i).equals("")){
                newaddedPerIds.remove(i);
            }
        }
        return newaddedPerIds;
    }


//    @ResponseBody
//    @RequestMapping(value = "/permission/{id}" ,method = RequestMethod.GET)
//    public List<Permission> insert(@PathVariable("id") int id){
//        logger.info("展示角色权限");
//        List<Permission> ps = permissionService.queryAll();
//        System.out.print(ps+"__________");
//        List<Permission> ps1 = permissionService.queryPermissionById(id);
//        System.out.print(ps1+"__________");
//        for(Permission p : ps){
//            for (Permission p1 : ps1){
//                if(p.getPermissionId() == p1.getPermissionId()){
//                    p.setStatus("true");
//                }
//            }
//        }
//        System.out.print(ps+"__________");
//        return ps;
//    }

    @ResponseBody
    @RequestMapping(value = "/addPermission/{roleId}/{permissionId}" ,method = RequestMethod.GET)
    public ControllerResult addPermission(@PathVariable("roleId") String roleId, @PathVariable("permissionId") String permissionId, HttpSession session){
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("添加角色权限");
                rolePermissionService.addPermission(roleId, permissionId);
                return ControllerResult.getSuccessResult("添加权限成功");
            } else {
                logger.info("此用户无拥有添加角色权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/removePermission/{roleId}/{permissionId}" ,method = RequestMethod.GET)
    public ControllerResult removePermission(@PathVariable("roleId") String roleId, @PathVariable("permissionId") String permissionId, HttpSession session){
        // TODO 超级管理员
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("删除角色权限");
                rolePermissionService.removePermission(roleId, permissionId);
                return ControllerResult.getSuccessResult("删除权限成功");
            } else {
                logger.info("此用户无拥有删除角色权限的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    public String getNewStatus(String status){
        if(status.equals("N")){
            return "Y";
        } else {
            return "N";
        }
    }


}
