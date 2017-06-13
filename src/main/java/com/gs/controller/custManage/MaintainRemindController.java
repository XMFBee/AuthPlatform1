package com.gs.controller.custManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.Methods;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.mail.MailConfig;
import com.gs.common.mes.IndustrySMS;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoQiao on 2017/5/4.
 */

/**
 * 维修保养提醒
 */

@Controller
@RequestMapping("/maintainRemind")
public class MaintainRemindController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainRemindController.class);

    @Autowired
    private HttpServletRequest req;

    @Resource
    private MaintainRemindService maintainRemindService;

    @Resource
    private UserService userService;

    @Resource
    private MessageSendService messageSendService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRemind> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看维修保养提醒记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                int count = maintainRemindService.count((User) session.getAttribute("user"));
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("user"));
                List<MaintainRemind> queryList = maintainRemindService.queryByPager(pager);
                return new Pager4EasyUI<MaintainRemind>(pager.getTotalRecords(), queryList);
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
    @RequestMapping(value = "queryById/{remindId}", method = RequestMethod.GET)
    public MaintainRemind queryById(HttpSession session, @PathVariable("remindId") String remindId) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                MaintainRemind mr = maintainRemindService.queryById(remindId);
                return mr;
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
    @RequestMapping(value = "queryByPagerUser", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRemind> queryByPagerUser(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页用户查看维修保养提醒记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                User user = (User) session.getAttribute("frontUser");
                int count = maintainRemindService.countUser(user.getUserId());
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("frontUser"));
                List<MaintainRemind> queryList = maintainRemindService.queryByPagerUser(pager, user.getUserId());
                return new Pager4EasyUI<MaintainRemind>(pager.getTotalRecords(), queryList);
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
    @RequestMapping(value = "queryByPagerNull", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> queryByPagerNull(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看维修保养提醒记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                int count = maintainRecordService.countSix((User)session.getAttribute("user"), getInsertDateTime());
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("user"));
                List<MaintainRecord> queryList = maintainRecordService.queryByPagerSix(pager,getInsertDateTime());
                return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), queryList);
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
    public ControllerResult insert(HttpSession session, MaintainRemind maintainRemind, MessageSend messageSend) throws MessagingException {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("维修保养提醒记录添加操作");
                String remindType = req.getParameter("remindType");
                String userName = req.getParameter("checkinuserName");
                String emails = req.getParameter("checkin.user.userEmail");
                String phone = req.getParameter("checkin.user.userPhone");
                String uuid = UUIDUtil.uuid();
                maintainRemind.setRemindId(uuid);
                User user = (User) session.getAttribute("user");
//                maintainRemind.setRemindMsg("【汽车之家】尊敬的"+userName+"车主， 你的爱车已经需要再次维修保养了， 请速速前来本店。 ");
//                maintainRemind.setCompanyId(user.getCompanyId());
//                maintainRemindService.insert(maintainRemind);
                System.out.print("车主的名称："+userName);
                System.out.print("车主的邮箱为："+emails);
                System.out.print("车主的手机号码为："+phone);
                if(remindType.equals("短信提醒")) {
                    IndustrySMS i = new IndustrySMS(phone, "【汽车之家】尊敬的"+userName+"车主， 你的爱车已经需要再次维修保养了， 请速速前来本店。 ");
                    i.execute();
                    maintainRemind.setRemindMsg("【汽车之家】尊敬的"+userName+"车主， 你的爱车已经需要再次维修保养了， 请速速前来本店。 ");
                    maintainRemind.setCompanyId(user.getCompanyId());
                    maintainRemindService.insert(maintainRemind);
                    messageSend.setMessageId(maintainRemind.getRemindId());
                    messageSend.setUserId(maintainRemind.getUserId());
                    messageSend.setSendMsg(maintainRemind.getRemindMsg());
                    messageSend.setCompanyId(user.getCompanyId());
                    messageSendService.insertRemind(messageSend);
                } else if(remindType.equals("邮箱提醒")) {
                    maintainRemind.setCompanyId(user.getCompanyId());
                    maintainRemindService.insert(maintainRemind);
                    Mail mail = new Mail();
                    mail.setSubject("维修保修提醒");
                    mail.setBccRecipients(emails); // 密送
                    logger.info(emails);
                    Multipart multipart = new MimeMultipart();
                    BodyPart part1 = new MimeBodyPart();
                    // 设置邮件内容
                    part1.setContent(maintainRemind.getRemindMsg(), "text/html;charset=utf-8");
                    multipart.addBodyPart(part1); // 把此邮件内容添加到实例化好的邮件中
                    mail.setMultinart(multipart); // 把此邮件对象添加进邮箱
                    File file = new File(Methods.getRootPath(req) + "WEB-INF/config/mail.properties");
                    MailConfig.sendMail(file, mail);
                }
                return ControllerResult.getSuccessResult("添加保养提醒成功");
            } else {
                logger.info("此用户无拥有此方法");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

//    @ResponseBody
//    @RequestMapping(value = "insertRemind", method = RequestMethod.POST)
//    public ControllerResult insertDate(HttpSession session, MaintainRemind maintainRemind, MessageSend messageSend) {
//        logger.info("维修保养提醒记录添加操作");
//        List<MaintainRecord> maintainRecords = maintainRecordService.queryByPagerSix(pager, getInsertDateTime());
//        if(maintainRecords != null) {
//            List<MaintainRemind> maintainReminds = new ArrayList<MaintainRemind>();
//            for(MaintainRecord mrs : maintainRecords) {
//                MaintainRemind mrs1 = new MaintainRemind();
//                mrs1.setUserId(mrs.getCheckin().getUserId());
//                mrs1.setLastMaintainTime(mrs.getActualEndTime());
//                mrs1.setLastMaintainMileage(mrs.getCheckin().getCarMileage());
//                maintainReminds.add(mrs1);
//            }
//            maintainRemindService.insertBatch(maintainReminds);
//            return ControllerResult.getSuccessResult("添加成功");
//        }
//        return null;
//    }

    public String getInsertDateTime() {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String actualEndTime = sdf.format(new Date());
        Date date = null;
        try{
            date = sdf.parse(actualEndTime);//初始日期
        }catch(Exception e){

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,-6);//在日历的月份上增加6个月
        System.out.println(sdf.format(c.getTime()));//的到你想要得6个月后的日期
        return sdf.format(c.getTime());
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, MaintainRemind maintainRemind, MessageSend messageSend) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("维修保养提醒记录修改操作");
                maintainRemindService.update(maintainRemind);
                messageSend.setSendTime(maintainRemind.getRemindTime());
                messageSend.setSendMsg(maintainRemind.getRemindMsg());
                messageSend.setSendCreatedTime(maintainRemind.getRemindCreatedTime());
                messageSendService.update(messageSend);
                return ControllerResult.getSuccessResult("修改保养提醒成功");
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
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRemind> blurredQuery(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, MaintainRemind maintainRemind) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("模糊查询维修保养提醒记录");
                String text = req.getParameter("text");
                String value = req.getParameter("value");
                if (text != null && text != "") {
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    if (text.equals("车主姓名")) {
                        maintainRemind.setUserId(value);
                    } else if (text.equals("上次汽车行驶里程")) {
                        maintainRemind.setLastMaintainMileage(Double.valueOf(value));
                    } else if (text.equals("维修保养提醒消息")) {
                        maintainRemind.setRemindMsg(value);
                    } else if (text.equals("维修保养提醒方式")) {
                        maintainRemind.setRemindType(value);
                    }
                    int count = maintainRemindService.countByBlurred(maintainRemind, (User) session.getAttribute("user"));
                    pager.setTotalRecords(count);
                    pager.setUser((User) session.getAttribute("user"));
                    List<MaintainRemind> queryList = maintainRemindService.blurredQuery(pager, maintainRemind);
                    return new Pager4EasyUI<MaintainRemind>(pager.getTotalRecords(), queryList);
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
