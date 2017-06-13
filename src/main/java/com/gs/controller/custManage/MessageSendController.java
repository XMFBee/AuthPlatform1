package com.gs.controller.custManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Mail;
import com.gs.bean.MaintainRecord;
import com.gs.bean.MessageSend;
import com.gs.bean.User;
import com.gs.common.Methods;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.mail.MailConfig;
import com.gs.common.mes.IndustrySMS;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.MaintainRecordService;
import com.gs.service.MessageSendService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoQiao on 2017/5/6.
 */

@Controller
@RequestMapping("/messageSend")
public class MessageSendController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MessageSendController.class);

    @Autowired
    private HttpServletRequest req;

    @Resource
    private MessageSendService messageSendService;

    @Resource
    private UserService userService;

    @Resource
    private MaintainRecordService maintainRecordService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MessageSend> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看短信提醒记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                int count = messageSendService.count((User) session.getAttribute("user"));
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("user"));
                List<MessageSend> queryList = messageSendService.queryByPager(pager);
                return new Pager4EasyUI<MessageSend>(pager.getTotalRecords(), queryList);
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
    @RequestMapping(value = "queryById/{messageId}", method = RequestMethod.GET)
    public MessageSend queryById(HttpSession session, @PathVariable("messageId") String messageId) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                MessageSend ms = messageSendService.queryById(messageId);
                return ms;
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
    @RequestMapping(value = "insert/{userIds}/{userPhone}", method = RequestMethod.POST)
    public ControllerResult batchInsert(HttpSession session, @PathVariable("userIds") String userIds,@PathVariable("userPhone")String userPhone) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("短信提醒记录添加操作");
                User user = (User) session.getAttribute("user");
                String sendMsg = req.getParameter("sendMsg");
                String messageSendText = req.getParameter("messageSendText");
                String[] strArray = null;
                strArray = userIds.split(",");
                String[] strArray1 = null;
                strArray1 = userPhone.split(",");
                List<MessageSend> list = new ArrayList<MessageSend>();
                for(String s: strArray){
                    MessageSend m = new MessageSend();
                    m.setUserId(s);
                    m.setSendMsg(sendMsg);
                    m.setCompanyId(user.getCompanyId());
                    list.add(m);
                }
                if(messageSendText.equals("模板1")) {
                    for(String phone : strArray1){
                        IndustrySMS i = new IndustrySMS(phone, "【汽车之家】亲，本店开启了促销活动， 快来给你的爱车进行维修保养吧~");
                        i.execute();
                    }
                } else if(messageSendText.equals("模板2")) {
                    for(String phone2 : strArray1){
                        IndustrySMS i = new IndustrySMS(phone2, "【汽车之家】尊敬的车主您好， 您已经很久没有进行维修保养了， 快来预约吧。");
                        i.execute();
                    }
                }
                messageSendService.batchInsert(list);
                return ControllerResult.getSuccessResult("添加短信提醒成功");
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
//    @RequestMapping(value = "insert", method = RequestMethod.POST)
//    public ControllerResult insert(HttpSession session, MessageSend messageSend) {
//        if (SessionUtil.isLogin(session)) {
//            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
//            if (RoleUtil.checkRoles(roles)) {
//                logger.info("短信提醒记录添加操作");
//                messageSendService.insert(messageSend);
//                return ControllerResult.getSuccessResult("添加短信提醒成功");
//            } else {
//                logger.info("此用户无拥有此方法");
//                return null;
//            }
//        } else {
//            logger.info("请先登录");
//            return null;
//        }
//
//    }



    @ResponseBody
    @RequestMapping(value = "queryCombox", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryCombox(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查看用户");
                List<User> users = userService.queryByRoleName("车主");
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
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<MessageSend> blurredQuery(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, MessageSend messageSend, User user) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("模糊查询短信提醒记录");
                String text = req.getParameter("text");
                String value = req.getParameter("value");
                if (text != null && text != "") {
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    if (text.equals("车主姓名")) {
                        messageSend.setUserId(value);
                    } else if (text.equals("发送内容")) {
                        messageSend.setSendMsg(value);
                    } else if (text.equals("车主手机号码")) {
                        messageSend.setUser(user);
                    }
                    int count = messageSendService.countByBlurred(messageSend, (User) session.getAttribute("user"));
                    pager.setTotalRecords(count);
                    pager.setUser((User) session.getAttribute("user"));
                    List<MessageSend> queryList = messageSendService.blurredQuery(pager, messageSend);
                    return new Pager4EasyUI<MessageSend>(pager.getTotalRecords(), queryList);
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
