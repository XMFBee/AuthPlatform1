package com.gs.controller.custManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.MaintainRecordService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoQiao on 2017/4/24.
 */
@Controller
@RequestMapping("/maintainRecord")
public class MaintainRecordController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainRecordController.class);

    @Resource
    private MaintainRecordService maintainRecordService;

    @ResponseBody
    @RequestMapping("queryByPager")
    public Pager4EasyUI<MaintainRecord> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分布查看维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainRecordService.count((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByPager(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
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
     * 后台首页查询最新的维修保养记录
     */
    @ResponseBody
    @RequestMapping("queryByBackstage")
    public Pager4EasyUI<MaintainRecord> queryByBackstage(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("后台首页查看最新的十条维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                int count = 10;
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByBackstage(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
            }else{
                logger.info("此用户无拥有后台首页查询最新的维修保养记录的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 提车提醒分页查询未提醒维修保养记录
     */
    @ResponseBody
    @RequestMapping("queryByPagerRemindNo")
    public Pager4EasyUI<MaintainRecord> queryByPagerRemindNo(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("提车提醒分布查看维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainRecordService.countByRemindNo((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByPagerRemindNo(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
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
     * 提车提醒分页查询已提醒维修保养记录
     */
    @ResponseBody
    @RequestMapping("queryByPagerRemindYes")
    public Pager4EasyUI<MaintainRecord> queryByPagerRemindYes(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("提车提醒分布查看维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainRecordService.countByRemindYes((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByPagerRemindYes(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
            }else{
                logger.info("此用户无拥有可用维修保养分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("queryByPagerDisable")
    public Pager4EasyUI<MaintainRecord> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分布查看已禁用的维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainRecordService.countByDisable((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByPagerDisable(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
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
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        maintainRecordService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活维修保养记录成功");
                    } else {
                        maintainRecordService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用维修保养记录成功");
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

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ControllerResult insert(HttpSession session, MaintainRecord maintainRecord) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("维修保养记录添加操作");
                maintainRecord.setStartTime(new Date());
                maintainRecord.setEndTime(new Date());
                maintainRecord.setRecordCreatedTime(new Date());
                maintainRecord.setPickupTime(new Date());
                maintainRecord.setActualEndTime(new Date());
                maintainRecordService.insert(maintainRecord);
                return ControllerResult.getSuccessResult("添加维修保养记录成功");
            }else{
                logger.info("此用户无拥有更改维修保养的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session,MaintainRecord maintainRecord) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("维修保养记录修改操作");
                if(maintainRecord.getRecordStatus().equals("N")){
                    maintainRecord.setRecordStatus("Y");
                }
                maintainRecordService.update(maintainRecord);
                return ControllerResult.getSuccessResult("修改维修保养记录成功");
            }else{
                logger.info("此用户无拥有更改维修保养的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPagerSuccess", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryByPagerSuccess(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分布查看已完成的维修保养记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                int count = maintainRecordService.countSuccess((User)session.getAttribute("user"));
                pager.setTotalRecords(count);
                List<MaintainRecord> queryList = maintainRecordService.queryByPagerSuccess(pager);
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
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
     * 根据用户id查询此车主所有预约记录
     */
    @ResponseBody
    @RequestMapping(value="queryByOwner", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryByOwner(HttpSession session, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("根据用户id查询此车主所有预约记录");
                User user = (User)session.getAttribute("frontUser");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(maintainRecordService.countByOwner(user.getUserId()));
                List<MaintainRecord> appointments = maintainRecordService.queryByOwner(pager, user.getUserId());
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), appointments);
            }else{
                logger.info("此用户无拥有可用登记记录分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 时间格式化
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
