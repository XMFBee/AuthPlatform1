package com.gs.controller;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.Methods;
import com.gs.common.bean.ControllerResult;
import com.gs.common.mail.MailConfig;
import com.gs.common.mes.IndustrySMS;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.AppointmentService;
import com.gs.service.CheckinService;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private AppointmentService appointmentService;
    @Resource
    private CheckinService checkinService;

    private Subject subject;

    @RequestMapping(value = "loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "Frontpage/registered";
    }

    @RequestMapping(value = "redirectLogin", method = RequestMethod.GET)
    public void redirectLoginPage(HttpServletResponse response) throws IOException {
        response.getWriter().print("<html><script type='text/JavaScript'>top.location = \"/user/loginPage\";</script></html>");
        response.setContentType("text/html");
    }

    /**
     * 登陆方法。
     *
     * @param user1
     * @param session
     * @param checkCode
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ControllerResult userLogin(User user1, HttpSession session, HttpServletRequest req, @Param("checkCode") String checkCode) {
        String codeSession = (String) session.getAttribute("checkCode");
        if (checkCode != null && checkCode.equals(codeSession)) {
            subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(user1.getUserEmail(), EncryptUtil.md5Encrypt(user1.getUserPwd())));
                if (subject.hasRole(Constants.role_systemSuperAdmin) || subject.hasRole(Constants.role_systemOrdinaryAdmin)
                        || subject.hasRole(Constants.role_companySuperAdmin) || subject.hasRole(Constants.role_companyOrdinaryAdmin)
                        || subject.hasRole(Constants.role_companyReceptionist) || subject.hasRole(Constants.role_companyTotalTC)
                        || subject.hasRole(Constants.role_companyTechnician) || subject.hasRole(Constants.role_companyApprentice)
                        || subject.hasRole(Constants.role_companySales) || subject.hasRole(Constants.role_companyFinancial)
                        || subject.hasRole(Constants.role_companyProcurement) || subject.hasRole(Constants.role_companyLibraryTube)
                        || subject.hasRole(Constants.role_companyHR)) {
                    logger.info("登录成功");
                    User user = userService.queryUser(user1.getUserEmail());
                    session.setAttribute("user", user);
                    session.setAttribute("userLoginedTime", new Date());
                    return ControllerResult.getSuccessResult("登录成功");
                }else if(subject.hasRole(Constants.role_owner)){
                    logger.info("登录成功");
                    User user = userService.queryUser(user1.getUserEmail());
                    session.setAttribute("frontUser", user);
                    return ControllerResult.getIsOwnerResult("登录成功");
                }else {
                    logger.info("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
                    return ControllerResult.getFailResult("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
                }
            } catch (UnknownAccountException e) {//未知的账号异常
                logger.info("登陆失败，请检查你的账号是否存在或是否可用！");
                return ControllerResult.getFailResult("登陆失败，请检查你的账号是否存在或是否可用！");
            } catch (IncorrectCredentialsException e) {//未知的凭证异常
                logger.info("登陆失败，请检查你的账号密码是否正确！");
                return ControllerResult.getFailResult("登陆失败，请检查你的账号密码是否正确！");
            } catch (LockedAccountException e) {//锁定的账号异常
                logger.info("登陆失败，你的账号已被冻结，暂时无法使用！");
                return ControllerResult.getFailResult("登陆失败，你的账号已被冻结，暂时无法使用！");
            }
        } else {
            logger.info("验证码输入错误，请输入正确的验证码！");
            return ControllerResult.getFailResult("验证码输入错误，请输入正确的验证码！");
        }
    }

    /**
     * 验证手机号是否存在
     */
    @ResponseBody
    @RequestMapping(value = "queryPhoneByOne", method = RequestMethod.GET)
    public String queryPhoneByOne(HttpServletRequest req) {
        logger.info("验证手机号是否已经存在");
            String userPhone = (String)req.getParameter("userPhone");
            boolean result = true;
            String resultString = "";
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            ObjectMapper mapper = new ObjectMapper();
            if (userPhone != null && userPhone !="") {
                int count = userService.queryPhoneByOne(userPhone);
                if (count > 1 || count == 1) {
                    result = false;
                }
            }
            map.put("valid", result);
            try {
                resultString = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return resultString;
    }

    /**
     * 验证邮箱绑定账号是否存在
     */
    @ResponseBody
    @RequestMapping(value = "queryEmailIsNull", method = RequestMethod.GET)
    public String queryEmailIsNull(HttpServletRequest req) {
        logger.info("验证邮箱绑定账号是否存在");
        String eamil = (String)req.getParameter("rtemail");
        boolean result = true;
        String resultString = "";
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ObjectMapper mapper = new ObjectMapper();
        if (eamil != null && eamil !="") {
            int count = userService.queryEmailIsNull(eamil);
            if (count == 0) {
                result = false;
            }
        }
        map.put("valid", result);
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 验证手机号码绑定账号是否存在
     */
    @ResponseBody
    @RequestMapping(value = "queryPhoneIsNull", method = RequestMethod.GET)
    public String queryPhoneIsNull(HttpServletRequest req) {
        logger.info("验证手机号码绑定账号是否存在");
        String rtphone = (String)req.getParameter("rtphone");
        boolean result = true;
        String resultString = "";
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ObjectMapper mapper = new ObjectMapper();
        if (rtphone != null && rtphone !="") {
            int count = userService.queryPhoneIsNull(rtphone);
            if (count == 0) {
                result = false;
            }
        }
        map.put("valid", result);
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ControllerResult register(User user, HttpSession session){
        if(user!=null) {
            int code = (Integer) session.getAttribute("phonecode");
            if(user.getPhonecode() == code) {
                user.setUserId(UUIDUtil.uuid());
                user.setUserPwd(EncryptUtil.md5Encrypt(user.getUserPwd()));
                userService.insert(user);
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId("8067fa42-3205-11e7-bc72-507b9d765567");// 设置为车主角色
                userRoleService.insert(userRole);
                // 根据此注册手机号查找预约表和登记表， 假如表中的userPhone和注册的userPhone一致， 则把记录的userId改为此注册用户id
                List<Appointment> appointments = appointmentService.queryByPhone(user.getUserPhone());
                List<Checkin> checkins = checkinService.queryByPhone(user.getUserPhone());
                String appIds = null;
                String chIds = null;
                for (Appointment appointment : appointments) {
                    if (appIds == null) {
                        appIds = "'" + appointment.getAppointmentId() + "'";
                    } else {
                        appIds += ",'" + appointment.getAppointmentId() + "'";
                    }
                }
                for (Checkin checkin : checkins) {
                    if (chIds == null) {
                        chIds = "'" + checkin.getCheckinId() + "'";
                    } else {
                        chIds += ",'" + checkin.getCheckinId() + "'";
                    }
                }
                if(chIds != null && chIds != "") {
                    checkinService.updateUserIds(user.getUserId(), chIds);
                }
                if(appIds != null && appIds != "") {
                    appointmentService.updateUserIds(user.getUserId(), appIds);
                }
                return ControllerResult.getSuccessResult("注册成功, 确认将直接跳转到我的中心...");
            }else{
                return ControllerResult.getNotPhoneCodeResult("短信验证码输入错误");
            }
        }else{
            return ControllerResult.getFailResult("注册失败");
        }
    }

    /**
     * 登陆方法1。
     *
     * @param user1
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login1", method = RequestMethod.POST)
    public ControllerResult userLogin1(User user1, HttpSession session, HttpServletRequest req) {
            subject = SecurityUtils.getSubject();
            try {
                subject.login(new UsernamePasswordToken(user1.getUserPhone(), EncryptUtil.md5Encrypt(user1.getUserPwd())));
               if(subject.hasRole(Constants.role_owner)){
                    logger.info("登录成功");
                    User user = userService.queryUser(user1.getUserPhone());
                    session.setAttribute("frontUser", user);
                    return ControllerResult.getIsOwnerResult("登录成功");
                }else {
                    logger.info("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
                    return ControllerResult.getFailResult("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
                }
            } catch (UnknownAccountException e) {//未知的账号异常
                logger.info("登陆失败，请检查你的账号是否存在或是否可用！");
                return ControllerResult.getFailResult("登陆失败，请检查你的账号是否存在或是否可用！");
            } catch (IncorrectCredentialsException e) {//未知的凭证异常
                logger.info("登陆失败，请检查你的账号密码是否正确！");
                return ControllerResult.getFailResult("登陆失败，请检查你的账号密码是否正确！");
            } catch (LockedAccountException e) {//锁定的账号异常
                logger.info("登陆失败，你的账号已被冻结，暂时无法使用！");
                return ControllerResult.getFailResult("登陆失败，你的账号已被冻结，暂时无法使用！");
            }
        }

    /**
     * 登陆方法2。
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login2", method = RequestMethod.POST)
    public ControllerResult userLogin2(Company company, HttpSession session, HttpServletRequest req) {
        subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(company.getCompanyPricipalphone(), EncryptUtil.md5Encrypt("123456")));
            if(subject.hasRole(Constants.role_companySuperAdmin)){
                logger.info("登录成功");
                User user = userService.queryUser(company.getCompanyPricipalphone());
                session.setAttribute("user", user);
                return ControllerResult.getIsOwnerResult("登录成功");
            }else {
                logger.info("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
                return ControllerResult.getFailResult("抱歉，你的账号角色并不授权。请联系管理员激活账号！");
            }
        } catch (UnknownAccountException e) {//未知的账号异常
            logger.info("登陆失败，请检查你的账号是否存在或是否可用！");
            return ControllerResult.getFailResult("登陆失败，请检查你的账号是否存在或是否可用！");
        } catch (IncorrectCredentialsException e) {//未知的凭证异常
            logger.info("登陆失败，请检查你的账号密码是否正确！");
            return ControllerResult.getFailResult("登陆失败，请检查你的账号密码是否正确！");
        } catch (LockedAccountException e) {//锁定的账号异常
            logger.info("登陆失败，你的账号已被冻结，暂时无法使用！");
            return ControllerResult.getFailResult("登陆失败，你的账号已被冻结，暂时无法使用！");
        }
    }

    /**
     * 验证是否登录
     */
    @ResponseBody
    @RequestMapping(value="isLogin/{roles}",method=RequestMethod.POST)
    public ControllerResult isLogin(@PathVariable("roles") String roles, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            if(RoleUtil.checkRoles(roles)){
                return ControllerResult.getSuccessResult("拥有角色");
            }else{
                logger.info("无进入方法角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 注册发送短信
     */
    @ResponseBody
    @RequestMapping(value="sendSms",method=RequestMethod.GET)
    public ControllerResult sendSms(HttpServletRequest req, HttpSession session) {
        String phone = req.getParameter("phone");
        if(phone!= null && phone!= "") {
            logger.info("发送短信验证码");
            int code = (int)((Math.random()*9+1)*100000);
            session.setAttribute("phonecode", code);
            IndustrySMS i = new IndustrySMS(phone, "【汽车之家】您的验证码为" +code+"，请于30分钟内正确输入，如非本人操作，请忽略此短信。");
            i.execute();
            return ControllerResult.getSuccessResult("发送短信验证码成功");
        }
       return ControllerResult.getFailResult("发送短信验证码失败");
    }

    /**
     * 找回密码发送短信
     */
    @ResponseBody
    @RequestMapping(value="rtPhoneCode",method=RequestMethod.GET)
    public ControllerResult rtPhoneCode(HttpServletRequest req, HttpSession session) {
        String phone = req.getParameter("phone");
        if(phone!= null && phone!= "") {
            logger.info("发送短信验证码");
            int code = (int)((Math.random()*9+1)*100000);
            session.setAttribute("rtPhoneCode", code);
            IndustrySMS i = new IndustrySMS(phone, "【汽车之家】您的验证码为" +code+"，请于30分钟内正确输入，如非本人操作，请忽略此短信。");
            i.execute();
            return ControllerResult.getSuccessResult("发送短信验证码成功");
        }
        return ControllerResult.getFailResult("发送短信验证码失败");
    }

    /**
     * 找回密码发送邮箱
     */
    @ResponseBody
    @RequestMapping(value="rtEmailCode",method=RequestMethod.GET)
    public ControllerResult rtEmailCode(HttpServletRequest req, HttpSession session) throws MessagingException {
        String email = req.getParameter("email");
        if(email!= null && email!= "") {
            logger.info("发送邮箱验证码");
            int code = (int)((Math.random()*9+1)*100000);
            session.setAttribute("rtEmailCode", code);
            Mail mail = new Mail();
            mail.setSubject("找回密码"); // 设置标题
            // 设置接收者
            // mail.setRecipients(u.getUserEmail()); 收件人
            // mail.setCcRecipients(); // 抄送
            mail.setBccRecipients(email); // 密送
            Multipart multipart = new MimeMultipart();
            BodyPart part1 = new MimeBodyPart(); // 邮件内容
            // 设置邮件内容
            part1.setContent("您的验证码为"+code+"，请于30分钟内正确输入，如非本人操作，请忽略此邮件。", "text/html;charset=utf-8");
            multipart.addBodyPart(part1); // 把此邮件内容添加到实例化好的邮件中
            mail.setMultinart(multipart); // 把此邮件对象添加进邮箱
            File file = new File(Methods.getRootPath(req) + "WEB-INF/config/mail.properties");
            MailConfig.sendMail(file, mail);
            return ControllerResult.getSuccessResult("发送邮箱验证码成功");
        }
        return ControllerResult.getFailResult("发送邮箱验证码失败");
    }

    /**
     * 邮箱验证确定按钮
     */
    @ResponseBody
    @RequestMapping(value="emailConfirm/{email}/{code}/{pwd}",method=RequestMethod.GET)
    public ControllerResult emailConfirm(HttpServletRequest req, HttpSession session, @PathVariable("email")String email, @PathVariable("code")int code, @PathVariable("pwd")String pwd) {
        Integer sessionCode = (Integer)session.getAttribute("rtEmailCode");
        if(code == sessionCode){
            userService.updatePwdByEmail(EncryptUtil.md5Encrypt(pwd), email);
            return ControllerResult.getSuccessResult("修改密码成功");
        }else{
            return ControllerResult.getFailResult("邮箱验证码输入错误");
        }
    }

    /**
     * 手机验证确定按钮
     */
    @ResponseBody
    @RequestMapping(value="phoneConfirm/{phone}/{code}/{pwd}",method=RequestMethod.GET)
    public ControllerResult phoneConfirm(HttpServletRequest req, HttpSession session, @PathVariable("phone")String phone, @PathVariable("code")int code, @PathVariable("pwd")String pwd) {
        Integer sessionCode = (Integer)session.getAttribute("rtPhoneCode");
        if(code == sessionCode){
            userService.updatePwdByPhone(EncryptUtil.md5Encrypt(pwd), phone);
            return ControllerResult.getSuccessResult("修改密码成功");
        }else{
            return ControllerResult.getFailResult("短信验证码输入错误");
        }
    }

    /**
     * 跳转修改密码页面
     */
    @RequestMapping(value="updatePwdPage",method=RequestMethod.GET)
    public String updatePwdPage() {
        return "backstage/updatePwd";
    }


    /**
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param conPwd 确认密码
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    public ControllerResult updatePwd(@Param("oldPwd") String oldPwd, @Param("newPwd")
            String newPwd, @Param("conPwd") String conPwd, HttpSession session, HttpServletRequest request) {
        User user = (User) session.getAttribute("user");
        if (user != null && !user.equals("")) {
          if (oldPwd != null && !oldPwd.equals("")) {
              if (newPwd != null && !newPwd.equals("")) {
                  if (conPwd != null && !conPwd.equals("")) {
                      if (newPwd.equals(conPwd)) {
                          if (user.getUserPwd().equals(EncryptUtil.md5Encrypt(oldPwd))) {
                              if (newPwd != null && newPwd.equals(conPwd)) {
                                  user.setUserPwd(EncryptUtil.md5Encrypt(conPwd));
                                  userService.updatePwd(user);
                                  Subject currentUser = SecurityUtils.getSubject();
                                  if(SessionUtil.isLogin(session)) {
                                      user.setUserLoginedTime((Date) session.getAttribute("userLoginedTime"));
                                      userService.update(user);
                                  }
                                  currentUser.logout();
                                  logger.info("用户更新密码成功");
                                  return ControllerResult.getSuccessResult("修改密码成功");
                              } else {
                                  logger.info("两次密码输入不一致");
                                  return ControllerResult.getFailResult("两次密码输入不一致，请重新输入！");
                              }
                          } else {
                              logger.info("旧密码输入有误！");
                              return ControllerResult.getFailResult("更新密码失败，您的旧密码输入有误");
                          }
                      } else {
                          return ControllerResult.getFailResult("两次密码输入错误");
                      }
                  } else {
                      return ControllerResult.getFailResult("重复密码不能为空");
                  }
              } else {
                  return ControllerResult.getFailResult("新密码不能为空");
              }
          } else {
              return ControllerResult.getFailResult("旧密码不能为空");
          }
        } else {
            logger.info("用户还未登陆");
            return ControllerResult.getFailResult("请登录");
        }
    }

    /**
     * 时间格式化
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}


