package com.gs.controller.custManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Complaint;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.ComplaintService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoQiao on 2017/4/25.
 */

/**
 * 投诉管理
 */
@Controller
@RequestMapping("/complaint")
public class ComplaintController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ComplaintController.class);

    @Autowired
    private HttpServletRequest req;

    @Resource
    private ComplaintService complaintService;

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Complaint> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看投诉记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                User user = (User) session.getAttribute("user");
                int count = complaintService.count(user);
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("user"));
                List<Complaint> queryList = complaintService.queryByPager(pager);
                return new Pager4EasyUI<Complaint>(pager.getTotalRecords(), queryList);
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryByPagerComplaintUser", method = RequestMethod.GET)
    public Pager4EasyUI<Complaint> queryByPagerComplaintUser(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看投诉记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                User user = (User) session.getAttribute("frontUser");
                int count = complaintService.countComplaintUser(user.getUserId());
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("frontUser"));
                List<Complaint> queryList = complaintService.queryByPagerComplaintUser(pager,user.getUserId());
                return new Pager4EasyUI<Complaint>(pager.getTotalRecords(), queryList);
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryName", method = RequestMethod.GET)
    public Pager4EasyUI<Complaint> queryName(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, Complaint complaint) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("模糊查询投诉记录");
                String text = req.getParameter("text");
                String value = req.getParameter("value");
                if (text != null && text != "") {
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    if (text.equals("投诉人")) {
                        complaint.setUserId(value);
                    } else if (text.equals("投诉内容")) {
                        complaint.setComplaintContent(value);
                    } else if (text.equals("投诉回复人")) {
                        complaint.setComplaintReplyUser(value);
                    } else if (text.equals("投诉回复内容")) {
                        complaint.setComplaintReply(value);
                    }
                    int count = complaintService.countName(complaint, (User) session.getAttribute("user"));
                    pager.setTotalRecords(count);
                    pager.setUser((User) session.getAttribute("user"));
                    List<Complaint> queryList = complaintService.queryByPagerName(pager, complaint);
                    return new Pager4EasyUI<Complaint>(pager.getTotalRecords(), queryList);
                }
                return null;
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "queryCombox", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCombox(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查看用户");
                List<User> users = userService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> combo = new ArrayList<ComboBox4EasyUI>();
                for (User user : users) {
                    ComboBox4EasyUI co = new ComboBox4EasyUI();
                    co.setId(user.getUserId());
                    co.setText(user.getUserName());
                    String userId = req.getParameter("userId");
                    if (user.getUserId().equals(userId)) {
                        co.setSelected(true);
                    }
                    combo.add(co);
                }
                return combo;
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ControllerResult insert(HttpSession session, Complaint complaint) {
        if (SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("投诉记录添加操作");
                complaintService.insert(complaint);
                return ControllerResult.getSuccessResult("添加投诉信息成功,请留意该公司的回复");
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateReply", method = RequestMethod.POST)
    public ControllerResult updateReply(HttpSession session, Complaint complaint) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("投诉记录回复操作");
                User user = (User) session.getAttribute("user");
                complaint.setComplaintReplyUser(user.getUserId());
                complaintService.update(complaint);
                return ControllerResult.getSuccessResult("回复车主成功");
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, Complaint complaint) {
        if (SessionUtil.isLogin(session)) {
            String roles = "汽修公司管理员,汽修公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("投诉记录修改操作");
                complaintService.update(complaint);
                return ControllerResult.getSuccessResult("修改投诉成功");
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
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
