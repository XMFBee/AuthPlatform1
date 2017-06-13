package com.gs.controller.clearingOut;


import com.gs.bean.Checkin;
import com.gs.bean.Mail;
import com.gs.bean.MaintainRecord;
import com.gs.bean.User;
import com.gs.common.Methods;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.mail.MailConfig;
import com.gs.common.mes.IndustrySMS;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.CheckinService;
import com.gs.service.MaintainRecordService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;
import java.util.List;

/**
 * 提车提醒controller， 张文星
 */
@Controller
@RequestMapping("/carRemind")
public class CarRemindController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CarRemindController.class);

    // 登记service
    @Resource
    private CheckinService checkinService;
    // 维修保养记录service
    @Resource
    private MaintainRecordService maintainRecordService;
    // 用户service
    @Resource
    private UserService userService;

    /**
     * 测试发送短信
     */
    @RequestMapping(value="sms", method = RequestMethod.GET)
    public void sms(){
        //IndustrySMS i = new IndustrySMS("15570102341", "【创意科技】您的验证码为123456，请于30分钟内正确输入，如非本人操作，请忽略此短信。");
       // i.execute();
    }

    /**
     * 提车提醒
     * @return
     */
    @ResponseBody
    @RequestMapping(value="remind/{ids}/{phones}/{userName}", method = RequestMethod.GET)
    public ControllerResult remind(HttpSession session, HttpServletRequest req, @PathVariable("ids") String ids, @PathVariable("phones") String phones, @PathVariable("userName")String userName) throws MessagingException, MessagingException {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("提车提醒");
                if(ids != null && ids != "" && phones != null && phones != "") {
                    String[] strArray = null;
                    strArray = phones.split(",");
                    String[] strArray1 = null;
                    strArray1 = userName.split(",");
                    for(int i =0; i<strArray.length; i++) {
                        IndustrySMS is = new IndustrySMS(strArray[i], "【汽车之家】尊敬的"+strArray1[i]+"车主您好，您在本店进行的维修保养已经完工，请前来本店提取车辆");
                        is.execute();
                    }
                    List<User> users = userService.queryEmail(ids);// 根据维修保养记录id查出所有的用户, 拿到用户的email;
                    String emails = "";
                    for (User u : users) { // 设置邮箱
                        if (u.getUserEmail() != null && u.getUserEmail() != "") {
                            if (emails == "" || emails == null) {
                                emails = u.getUserEmail();
                            } else {
                                emails += "," + u.getUserEmail();
                            }
                        }
                    }
                    if (emails != null && emails != "") {
                        Mail mail = new Mail();
                        mail.setSubject("维修保养记录完工提醒"); // 设置标题
                        // 设置接收者
                        // mail.setRecipients(u.getUserEmail()); 收件人
                        // mail.setCcRecipients(); // 抄送
                        mail.setBccRecipients(emails); // 密送
                        Multipart multipart = new MimeMultipart();
                        BodyPart part1 = new MimeBodyPart(); // 邮件内容
                        // 设置邮件内容
                        part1.setContent("尊敬的车主你好, 你的车辆在本店进行的维修保养现已完工好了, 请前来开走车辆", "text/html;charset=utf-8");
                        multipart.addBodyPart(part1); // 把此邮件内容添加到实例化好的邮件中
                        mail.setMultinart(multipart); // 把此邮件对象添加进邮箱
                        File file = new File(Methods.getRootPath(req) + "WEB-INF/config/mail.properties");
                        MailConfig.sendMail(file, mail);
                        maintainRecordService.updateCurrentStatus("待结算",ids);
                        // 当提醒成功之后, 应该修改状态, 提醒完毕, 页面不再显示此记录
                    }else{
                        maintainRecordService.updateCurrentStatus("待结算",ids);
                    }
                    return ControllerResult.getSuccessResult("提醒成功");
                }else{
                    return ControllerResult.getFailResult("此维修保养记录车主不是本店用户, 无车主邮箱");
                }
            }else{
                logger.info("此用户无拥有提车提醒功能的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 维修保养记录模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainRecord> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("维修保养记录模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if(text != null && text!="") {
                    List<MaintainRecord> maintainRecords = null;
                    MaintainRecord maintainRecord = new MaintainRecord();
                    Checkin checkin = new Checkin();
                    if(text.equals("车主/电话/汽车公司/车牌号")){ // 当多种模糊搜索条件时
                        checkin.setUserPhone(value);
                        checkin.setCarPlate(value);
                        checkin.setUserName(value);
                        checkin.setCompanyId(value);
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
                    maintainRecord.setCheckin(checkin);
                    maintainRecords = maintainRecordService.blurredQueryByRemind(pager, maintainRecord);
                    pager.setTotalRecords(maintainRecordService.countByBlurredByRemind(maintainRecord, (User)session.getAttribute("user")));
                    pager.setUser((User)session.getAttribute("user"));
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }else{
                    pager.setTotalRecords(maintainRecordService.count((User)session.getAttribute("user")));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByPager(pager);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }
            }else{
                logger.info("此用户无拥有可用维修保养记录分页查询的角色");
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
