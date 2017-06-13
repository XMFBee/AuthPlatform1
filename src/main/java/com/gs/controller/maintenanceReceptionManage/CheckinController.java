package com.gs.controller.maintenanceReceptionManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Checkin;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AppointmentService;
import com.gs.service.CheckinService;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 接待登记管理controller， 张文星
 */
@Controller
@RequestMapping("/checkin")
public class CheckinController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CheckinController.class);

    // 登记service
    @Resource
    private CheckinService checkinService;
    // 维修保养记录service
    @Resource
    private MaintainRecordService maintainRecordService;
    // 预约记录service
    @Resource
    private AppointmentService appointmentService;

    /**
     * 查询所有登记记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> queryByPager(HttpSession session, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有登记记录");
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    pager.setTotalRecords(checkinService.count((User)session.getAttribute("user")));
                    pager.setUser((User)session.getAttribute("user"));
                    List<Checkin> checkins = checkinService.queryByPager(pager);
                    return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
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
     * 查询所有被禁用的登记记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> queryByPagerDisable(HttpSession session, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用登记记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(checkinService.countByDisable((User)session.getAttribute("user")));
                List<Checkin> checkins = checkinService.queryByPagerDisable(pager);
                return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
            }else{
                logger.info("此用户无拥有禁用登记记录分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addCheckin(HttpSession session, Checkin checkin) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                logger.info("添加登记记录");
                if(checkin != null) {
                    if(checkin.getAppointmentId()!= null && checkin.getAppointmentId() != ""){
                        appointmentService.updateCurrentById("登记完",checkin.getAppointmentId());
                    }
                    checkin.setCheckinId(UUIDUtil.uuid());
                    checkin.setCompanyId(user.getCompanyId());
                    checkinService.insert(checkin);
                    MaintainRecord maintainRecord = new MaintainRecord();
                    maintainRecord.setCheckinId(checkin.getCheckinId());
                    maintainRecord.setCurrentStatus("已登记");
                    maintainRecord.setRecordDes(checkin.getUserRequests());
                    maintainRecord.setIfConfirm("N");
                    maintainRecordService.insert(maintainRecord);
                    return ControllerResult.getSuccessResult("添加登记记录成功");
                }else{
                    return ControllerResult.getFailResult("添加登记记录失败");
                }
            }else{
                logger.info("此用户无拥有添加登记记录的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editCheckin(HttpSession session, Checkin checkin) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("修改登记记录");
                User user = (User)session.getAttribute("user");
                if(checkin.getCheckinStatus().equals("N")){
                    checkin.setCheckinStatus("Y");
                }
                checkinService.update(checkin);
                return ControllerResult.getSuccessResult("修改登记记录成功");
            }else{
                logger.info("此用户无拥有修改登记记录的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
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
                        checkinService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活登记记录成功");
                    } else {
                        checkinService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用登记记录成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else{
                logger.info("此用户无拥有更改登记记录状态的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 登记记录模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<Checkin> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("登记记录模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if(text != null && text!="" && value != null && value != "") {
                    List<Checkin> checkins = null;
                    Checkin checkin = new Checkin();
                    if(text.equals("车主/电话/汽车公司/车牌号")){ // 当多种模糊搜索条件时
                        checkin.setUserName(value);
                        checkin.setCompanyId(value);
                        checkin.setCarPlate(value);
                        checkin.setUserPhone(value);
                    }else if(text.equals("车主/电话/车牌号")){
                        checkin.setUserName(value);
                        checkin.setCarPlate(value);
                        checkin.setUserPhone(value);
                    }else if(text.equals("车主")){
                        checkin.setUserName(value);
                    }else if(text.equals("汽车公司")){
                        checkin.setCompanyId(value);
                    }else if(text.equals("车牌号")){
                        checkin.setCarPlate(value);
                    }else if(text.equals("电话")){
                        checkin.setUserPhone(value);
                    }
                    checkins = checkinService.blurredQuery(pager, checkin);
                    pager.setTotalRecords(checkinService.countByBlurred(checkin,(User)session.getAttribute("user")));
                    return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
                }else{ // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                    pager.setTotalRecords(checkinService.count((User)session.getAttribute("user")));
                    List<Checkin> checkins = checkinService.queryByPager(pager);
                    return new Pager4EasyUI<Checkin>(pager.getTotalRecords(), checkins);
                }
            }else{
                logger.info("此用户无拥有查看登记记录模糊查询方法的角色");
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }



}
