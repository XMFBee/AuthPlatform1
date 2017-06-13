package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.MaintainFixAccService;
import com.gs.service.MaintainFixService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 保养项目管理
 * Created by yaoyong on 2017/4/18.
 */

@Controller
@RequestMapping("/maintain")
public class MaintainController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainController.class);

    @Resource
    private MaintainFixService maintainFixService;

    @Resource
    private MaintainFixAccService maintainFixAccService;

    /**
     * 分页查询维修项目信息
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerService", method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerService(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixService.count((User) session.getAttribute("user")));
                logger.info("分页查询维修项目信息");
                List<MaintainFix> maintainFixes = maintainFixService.queryByPager(pager);
                return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixes);
            } else {
                logger.info("此用户无拥有分页查询维修项目信息的角色");
                return null;
            }
        }else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 分页查询保养项目信息
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerMaintain", method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerMaintain(HttpSession session,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixService.countMaintain((User) session.getAttribute("user")));
                logger.info("分页查询保养项目信息");
                List<MaintainFix> maintainFixes = maintainFixService.queryByPagerMaintain(pager);
                return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixes);
            }else {
                logger.info("此用户无拥有分页查询保养项目信息角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 分页查询所有维修保养项目信息
     */
    @ResponseBody
    @RequestMapping(value = "queryByPagerAll",method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerAll(HttpSession session,@Param("pageNumber") String pageNumber,@Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixService.countqueryByPagerAll((User) session.getAttribute("user")));
                logger.info("分页查询所有维修保养项目信息");
                List<MaintainFix> maintainFixes = maintainFixService.queryByPagerAll(pager);
                return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(),maintainFixes);
            }else {
                logger.info("此用户无拥有分页查询所有维修保养项目信息角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }


    }

    /**
     * 查询此维修保养名称是否已存在
     */
    @ResponseBody
    @RequestMapping(value = "querymaintainName", method = RequestMethod.POST)
    public Map querymaintainName(MaintainFix maintainFix) {
        logger.info("此维修保养名称是否已存在此维修保养名称");
        int countmaintainName = maintainFixService.querymaintainName(maintainFix.getMaintainName(),maintainFix.getMaintainId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countmaintainName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }



    /**
     * 查询此维修保养名称是否已存在
     */
    @ResponseBody
    @RequestMapping(value = "querymaintainNameMaintain", method = RequestMethod.POST)
    public Map querymaintainNameMaintain(MaintainFix maintainFix) {
        logger.info("此维修保养名称是否已存在此维修保养名称");
        int countmaintainName = maintainFixService.querymaintainName(maintainFix.getMaintainName(),maintainFix.getMaintainId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countmaintainName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "queryByDetailsByPager", method = RequestMethod.GET)
    public Pager4EasyUI queryByDetailsByPager(HttpSession session,@Param("maintainId") String maintainId, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize){
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询此记录下所有明细");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixAccService.countByDetails(maintainId,(User) session.getAttribute("user")));
                List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryByDetailsByPager(pager,maintainId);
                System.out.print(maintainFixAccs);
                return new Pager4EasyUI<MaintainFixAcc>(pager.getTotalRecords(), maintainFixAccs);
            }else {
                logger.info("此用户无拥有分页查询此记录下所有明细角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 查询所有保养项目
     */
    @ResponseBody
    @RequestMapping(value = "queryAllItem", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(HttpSession session){
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司总技师,汽车公司技师,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查询所有保养项目");
                List<MaintainFix> MaintainFixs = maintainFixService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for(MaintainFix m : MaintainFixs){
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(m.getMaintainId());
                    comboBox4EasyUI.setText(m.getMaintainName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            }else {
                logger.info("此用户无拥有查询所有保养项目角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "addService",method = RequestMethod.POST)
    public ControllerResult addService(HttpSession session,MaintainFix maintainFix){
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                if(maintainFix != null && !maintainFix.equals("")){
                    logger.info("添加维修项目");
                    maintainFix.setMaintainOrFix("维修");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加维修项目成功");
                }else {
                    return ControllerResult.getFailResult("添加维修项目失败");
                }
            }else {
                logger.info("此用户无拥有添加维修项目角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    @ResponseBody
    @RequestMapping(value = "accadd",method =  RequestMethod.POST)
    public ControllerResult accadd(HttpSession session,MaintainFixAcc maintainFixAcc){
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if(maintainFixAcc != null && !maintainFixAcc.equals("")){
                    logger.info("添加配件成功");
                    maintainFixAccService.insert(maintainFixAcc);
                    return ControllerResult.getSuccessResult("添加配件成功");
                }else {
                    return ControllerResult.getFailResult("添加配件失败");
                }
            }else {
                logger.info("此用户无拥有添加配件角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    @ResponseBody
    @RequestMapping(value = "accedit",method =  RequestMethod.POST)
    public ControllerResult accedit(HttpSession session,MaintainFixAcc maintainFixAcc){
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if(maintainFixAcc != null && !maintainFixAcc.equals("")){
                    logger.info("修改配件成功");
                    maintainFixAccService.update(maintainFixAcc);
                    return ControllerResult.getSuccessResult("修改配件成功");
                }else {
                    return ControllerResult.getFailResult("修改配件失败");
                }
            }else {
                logger.info("此用户无拥有修改配件角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    @ResponseBody
    @RequestMapping(value = "addMaintain",method = RequestMethod.POST)
    public ControllerResult InsertMaintain(HttpSession session,MaintainFix maintainFix){
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                if(maintainFix != null && !maintainFix.equals("")){
                    logger.info("添加保养项目");
                    maintainFix.setMaintainOrFix("保养");
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFixService.insert(maintainFix);
                    return ControllerResult.getSuccessResult("添加保养项目成功");
                }else {
                    return ControllerResult.getFailResult("添加保养项目失败");
                }
            }else {
                logger.info("此用户无拥有添加保养项目角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    /**
     * 修改保养项目信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult updateAccBuy(HttpSession session,MaintainFix maintainFix) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                if (maintainFix != null && !maintainFix.equals("")) {
                    maintainFix.setCompanyId(user.getCompanyId());
                    maintainFixService.update(maintainFix);
                    logger.info("修改保养项目成功");
                    return ControllerResult.getSuccessResult("修改保养项目成功");
                } else {
                    return ControllerResult.getFailResult("修改保养项目失败");
                }
            }else {
                logger.info("此用户无拥有修改保养项目角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 分页查询所有被禁用的保养项目
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByPagerDisable(HttpSession session,@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用的保养项目");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixService.countByDisable((User) session.getAttribute("user")));
                List<MaintainFix> maintainFixes = maintainFixService.queryByPagerDisable(pager);
                return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixes);
            }else {
                logger.info("此用户无拥有分页查询所有被禁用的保养项目角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 分页查询所有被禁用的维修项目
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerDisableService", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainFix> queryByPagerDisableService(HttpSession session,@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用的维修项目");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(maintainFixService.countByDisableService((User) session.getAttribute("user")));
                List<MaintainFix> maintainFixes = maintainFixService.queryByPagerDisableService(pager);
                return new Pager4EasyUI<MaintainFix>(pager.getTotalRecords(), maintainFixes);
            }else {
                logger.info("此用户无拥有分页查询所有被禁用的维修项目角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 对状态的激活和启用，只使用一个方法进行切换。
     */
    @ResponseBody
    @RequestMapping(value = "statusOperate", method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session,String id, String status) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        maintainFixService.active(id);
                        logger.info("激活维修保养项目成功");
                        return ControllerResult.getSuccessResult("激活维修保养项目成功");
                    } else {
                        maintainFixService.inactive(id);
                        logger.info("禁用维修保养项目成功");
                        return ControllerResult.getSuccessResult("禁用维修保养项目成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else {
                logger.info("此用户无拥有对状态做激活与禁用的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryByCondition")
    public List<MaintainFix> queryByCondition(HttpSession session, String start, String end, String type, String companyId, String maintainId, String maintainOrFix) {
        if(SessionUtil.isLogin(session)){
            String roles="公司超级管理员,公司普通管理员,汽车公司采购人员,汽修公司销售员,汽修公司库管人员,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)){
                List<MaintainFix> list = null;
                User user = (User) session.getAttribute("user");
                if (type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        if (companyId != null) {
                            list = maintainFixService.queryByCondition(start, end, maintainId, companyId, maintainOrFix,"year");
                        } else {
                            list = maintainFixService.queryByCondition(start, end, maintainId, user.getCompanyId(), maintainOrFix,"year");
                        }
                    } else if (type.equals("quarter")) {
                        if (companyId != null) {
                            list = maintainFixService.queryByCondition(start, end, maintainId, companyId, maintainOrFix,"quarter");
                        } else {
                            list = maintainFixService.queryByCondition(start, end, maintainId, user.getCompanyId(), maintainOrFix,"quarter");
                        }
                    } else if (type.equals("month")) {
                        if (companyId != null) {
                            list = maintainFixService.queryByCondition(start, end, maintainId, companyId, maintainOrFix,"month");
                        } else {
                            list = maintainFixService.queryByCondition(start, end, maintainId, user.getCompanyId(), maintainOrFix,"month");
                        }
                    } else if (type.equals("week")) {
                        if (companyId != null) {
                            list = maintainFixService.queryByCondition(start, end, maintainId, companyId, maintainOrFix,"week");
                        } else {
                            list = maintainFixService.queryByCondition(start, end, maintainId, user.getCompanyId(), maintainOrFix,"week");
                        }
                    } else if (type.equals("day")) {
                        if (companyId != null) {
                            list = maintainFixService.queryByCondition(start, end, maintainId, companyId, maintainOrFix,"day");
                        } else {
                            list = maintainFixService.queryByCondition(start, end, maintainId, user.getCompanyId(), maintainOrFix,"day");
                        }
                    }
                }
                return list;
            }else{
                logger.info("此用户无法拥有维修项目统计角色");
                return null;
            }
        }else{
            logger.info("请先登陆");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByMaintainName/{companyId}/{maintainOrFix}",method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(@PathVariable("companyId") String companyId, @PathVariable("maintainOrFix")String maintainOrFix){
        logger.info("根据汽修公司， 汽修项目，查询项目名字");
        List<MaintainFix> maintainDetails = maintainFixService.queryByMaintainName(companyId,maintainOrFix);
        List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
        for (MaintainFix m : maintainDetails) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(m.getMaintainId());
            comboBox4EasyUI.setText(m.getMaintainName());
            comboxs.add(comboBox4EasyUI);
        }
        return comboxs;
    }

    @ResponseBody
    @RequestMapping(value = "mySelectMaintainName/{maintainOrFix}",method = RequestMethod.GET)
    public List<ComboBox4EasyUI> mySelectMaintainName(HttpSession session, @PathVariable("maintainOrFix")String maintainOrFix){
        logger.info("根据汽修公司， 汽修项目，查询项目名字");
        User user = (User) session.getAttribute("user");
        List<MaintainFix> maintainDetails = maintainFixService.queryByMaintainName(user.getCompanyId(),maintainOrFix);
        List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
        for (MaintainFix m : maintainDetails) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(m.getMaintainId());
            comboBox4EasyUI.setText(m.getMaintainName());
            comboxs.add(comboBox4EasyUI);
        }
        return comboxs;
    }

}
