package com.gs.controller.maintainScheduleManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainSchedule;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.MaintainScheduleService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jyy on 2017/5/4.
 */

@Controller
@RequestMapping("/maintainSchedule")
public class  MaintainScheduleController {
    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainScheduleController.class);

    @Resource
    private MaintainScheduleService maintainScheduleService;
    @Resource
    private MaintainRecordService maintainRecordService;

    /**
     * 根据维修保养记录id查询此记录下所有维修保养进度
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryScheduleByRecord/{recordId}", method = RequestMethod.POST)
    public List<MaintainSchedule> queryScheduleByRecord(HttpSession session, @PathVariable("recordId") String recordId){
        if(SessionUtil.isLogin(session) || SessionUtil.isOwnerLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("根据维修保养记录id查询此记录下所有维修保养进度");
                List<MaintainSchedule> maintainSchedules = maintainScheduleService.queryScheduleByRecord(recordId);
                return maintainSchedules;
            }else{
                logger.info("此用户没有根据维修保养记录id查询此记录下所有维修保养进度的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }


    /**
     * 查询全部维修保养进度管理
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryAllschedule", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllSchedule(HttpSession session){
        if(SessionUtil.isLogin(session)) {
            String roles="系统超级管理员,系统普通管理员公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("查询全部维修保养进度管理");
                List<MaintainSchedule> maintainSchedules = maintainScheduleService.queryAll((User)session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for(MaintainSchedule m : maintainSchedules){
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(m.getMaintainScheduleId());
                    comboBox4EasyUI.setText(m.getMaintainScheduleId());
                }
                return comboxs;
            }else{
                logger.info("此用户没有查询进度方法的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询进度管理
     * @param pageNumber
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPage", method = RequestMethod.GET)
    public Pager4EasyUI queryByPager(HttpSession session,@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize){
        if(SessionUtil.isLogin(session)) {
            String roles="系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主,汽车公司总技师";
            if(RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(maintainScheduleService.count((User)session.getAttribute("user")));
                logger.info("分页查询维修保养进度管理成功");
                List<MaintainSchedule> maintainSchedules = maintainScheduleService.queryByPager(pager);
                return new Pager4EasyUI<MaintainSchedule>(pager.getTotalRecords(), maintainSchedules);
            }else{
                logger.info("此用户无拥有查询进度方法的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 添加维修保养进度管理
     * @param maintainSchedule
     * @return
     */
    @ResponseBody
    @RequestMapping(value="addSchedule", method = RequestMethod.POST)
    public ControllerResult addSchedule(HttpSession session,MaintainSchedule maintainSchedule){
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if(RoleUtil.checkRoles(roles)) {
                if(maintainSchedule !=null && !maintainSchedule.equals("")){
                    maintainScheduleService.insert(maintainSchedule);
                    logger.info("添加成功");
                    return ControllerResult.getSuccessResult("添加成功");
                }else{
                    return ControllerResult.getFailResult("添加失败，请检查输入的信息是否有误");
                }
            }else{
                logger.info("此用户无拥有添加进度方法的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 分页查看已禁用的维修保养记录
     * @param session
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value= "queryByPagerDisable", method = RequestMethod.POST)
    public Pager4EasyUI<MaintainSchedule> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分布查看已禁用的维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainScheduleService.countByDisable((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainSchedule> queryList = maintainScheduleService.queryByPagerDisable(pager);
                return new Pager4EasyUI<MaintainSchedule>(pager.getTotalRecords(), queryList);
            }else{
                logger.info("此用户无拥有可用维修保养分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 对状态的激活和启用，只使用一个方法进行切换。
     */
    @ResponseBody
    @RequestMapping(value = "statusOperate", method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session, String id, String status) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        maintainScheduleService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活成功");
                    } else {
                        maintainScheduleService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else{
                logger.info("此用户无拥有更改维修保养的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    //修改进度
    @ResponseBody
    @RequestMapping(value = "updateSchedule", method = RequestMethod.POST)
    public ControllerResult updateSchedule(HttpSession session, MaintainSchedule maintainSchedule){
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                if(maintainSchedule !=null && !maintainSchedule.equals("")){
                    maintainScheduleService.update(maintainSchedule);
                    logger.info("修改成功");
                    return ControllerResult.getSuccessResult("修改成功");
                }else{
                    return ControllerResult.getFailResult("修改失败");
                }
            }else{
                logger.info("此用户无拥有更新方法的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }


    /**
     * 日期格式
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
