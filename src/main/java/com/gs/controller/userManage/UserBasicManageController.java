package com.gs.controller.userManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.bean.UserRole;
import com.gs.common.Constants;
import com.gs.common.Methods;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.EncryptUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.UserRoleService;
import com.gs.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 小蜜蜂 on 2017-04-19.
 * 控制 员工基本信息管理的 增、改、查 操作
 */
@Controller
@RequestMapping("/userBasicManage")
public class UserBasicManageController {

    private Logger logger = (Logger) LoggerFactory.getLogger(UserBasicManageController.class);

    @Resource
    private UserService userService;

    @Resource
    protected UserRoleService userRoleService;

    /**
     *
     * 在提交表单后，续图片提交，这里接收用户ID
     */
    @ResponseBody
    @RequestMapping(value = "afterUpdIcon", method = RequestMethod.POST)
    public Map afterSubForm(@RequestParam("userIcon") MultipartFile file, @RequestParam("userId") String userId, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        HttpSession session = request.getSession();

        String savePath = Constants.UPLOAD_HEAD + Methods.createNewFolder() + "/";
        Map map= new HashMap();
        if(fileSave(file, savePath,userId,session)) {
            userService.updIcon(userId,savePath+userId+".jpg");   // 设置头像
            map.put("controllerResult", ControllerResult.getSuccessResult("添加成功,默认密码为123456"));
            map.put("imgPath", savePath);
        } else {
            userService.updIcon(userId,"/static/uploads/head/default.jpg");   // 设置头像
            map.put("controllerResult", ControllerResult.getFailResult("提交失败"));
        }
        return map;
    }

    /**
     * 添加人员基本信息 返回json
     */
    @ResponseBody
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public Map addUser(User user, HttpServletRequest request, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("添加人员");
                Map map = new HashMap();
                user.setUserPwd(EncryptUtil.md5Encrypt("123456"));
                String nickName = request.getParameter("userNickname");
                if(nickName != null) {
                    user.setUserNickname(nickName);
                } else {
                    user.setUserNickname(request.getParameter("userEmail"));
                }
                User u = (User) session.getAttribute("user");
                user.setCompanyId(u.getCompanyId());    // 设置为session获取的user对象的CompanyId
                user.setUserId(UUIDUtil.uuid());
                userService.insert(user);
                User userTemp = userService.queryByPhone(user.getUserPhone());
                map.put("user", userTemp);
                UserRole userRole = new UserRole();
                userRole.setUserId(userTemp.getUserId());
                userRole.setRoleId(user.getRoleId());
                userRoleService.insert(userRole);
                map.put("controllerResult", ControllerResult.getSuccessResult("添加成功,默认密码为123456"));
                return map;
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "addFile", method = RequestMethod.POST)
    public Map addFile(@RequestParam("userIconTemp") MultipartFile file, HttpServletRequest request){
        String fileName = file.getOriginalFilename();
        String savePath = "E://abc.jpg";
        Map map= new HashMap();
        System.out.println(fileName);
        if(fileSave(file, savePath,"",null)) {
            map.put("controllerResult", ControllerResult.getSuccessResult("上传成功"));
            map.put("imgPath", savePath);
        } else {
            map.put("controllerResult", ControllerResult.getFailResult("上传失败"));
        }
        return map;
    }

    private boolean fileSave(MultipartFile sourceFile, String savePath,String userId, HttpSession session) {
        byte[] temp = new byte[1024];
        int len = -1;
        String rootPath = session.getServletContext().getRealPath("/");
        savePath =rootPath + "/"+ savePath ;
        try {
            File saveDir = new File(savePath);
            if(!saveDir.isDirectory()) {
                saveDir.mkdirs();
            }

            File saveFile = new File(savePath + userId + ".jpg");
            InputStream fis = sourceFile.getInputStream();
            OutputStream fos = new FileOutputStream(saveFile);
            while((len = fis.read(temp)) != -1) {
                fos.write(temp, 0 ,len);
            }
            System.out.println(saveFile.getAbsolutePath());
            fis.close();
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 修改人员基本信息
     */
    @ResponseBody
    @RequestMapping(value = "updateUser", method =RequestMethod.POST)
    public Map updateUser(User user,HttpServletRequest request, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                Map map = new HashMap();
                userService.update(user);
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(user.getRoleId());
                userRoleService.update(userRole);
                logger.info("修改成功");
                map.put("controllerResult", ControllerResult.getSuccessResult("修改成功"));
                return map;
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    public ControllerResult updateStatus(String id, String status, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (status.equals("Y")) {
                    userService.inactive(id);
                    logger.info("修改状态成功，已禁用");
                    return ControllerResult.getSuccessResult("禁用人员基本信息成功");
                } else if (status.equals("N")) {
                    userService.active(id);
                    logger.info("修改状态成功，已激活");
                    return ControllerResult.getSuccessResult("激活人员基本信息成功");
                }
                return ControllerResult.getFailResult("修改状态失败");
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询人员基本信息,不分状态
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerAll", method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerAll(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize,HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒," +
                    "汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询人员基本信息成功");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                if(roleName == "系统超级管理员" || roleName == "系统普通管理员") {
                    pager.setTotalRecords(userService.countAll((User) session.getAttribute("user")));
                    users = userService.queryAllByPager(pager);
                } else {
                    pager.setTotalRecords(userService.count((User) session.getAttribute("user")));
                    users = userService.queryByPagerAll(pager);
                }
                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询状态为可用的记录
     */
    @ResponseBody
    @RequestMapping(value="queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI queryByPager(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒," +
                    "汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询状态为可用的人员基本信息成功");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                if(roleName == "系统超级管理员" || roleName == "系统普通管理员") {
                    pager.setTotalRecords(userService.countStatus((User) session.getAttribute("user"), "Y"));
                    users = userService.querySystemAdminByPager(pager, "Y");
                } else {
                    pager.setTotalRecords(userService.countOK((User) session.getAttribute("user")));
                    users = userService.queryByPager(pager);
                }
                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询状态为不可用的记录
     */
    @ResponseBody
    @RequestMapping(value="queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerDisable(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒," +
                    "汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询状态为不可用的人员基本信息成功");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                if(roleName == "系统超级管理员" || roleName == "系统普通管理员") {
                    pager.setTotalRecords(userService.countStatus((User) session.getAttribute("user"), "N"));
                    users = userService.querySystemAdminByPager(pager, "N");
                } else {
                    pager.setTotalRecords(userService.countNO((User) session.getAttribute("user")));
                    users = userService.queryByPagerDisable(pager);
                }
                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 查询全部人员基本信息  下拉列表
     */
    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(HttpSession session) {
        List<User> users = userService.queryAll((User)session.getAttribute("user"));
        List<ComboBox4EasyUI> combo = new ArrayList<ComboBox4EasyUI>();
        for(User u: users) {
            ComboBox4EasyUI c = new ComboBox4EasyUI();
            c.setId(u.getUserId());
            c.setText(u.getUserName());
            combo.add(c);
        }
        return combo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

//    @ResponseBody
//    @RequestMapping(value = "queryById", method = RequestMethod.POST)
//    public User queryById(@Param("userId") String userId) {
//        logger.info("根据id查询该id的详细信息");
//        return userService.queryById(userId);
//    }

    @ResponseBody
    @RequestMapping(value = "queryByRoleName", method = RequestMethod.POST)
    public List<User> queryByRoleName(@Param("roleName") String roleName) {
        logger.info("根据roleName查询该roleName的详细信息");
        return userService.queryByRoleName(roleName);
    }

    @ResponseBody
    @RequestMapping(value = "queryInfoById", method = RequestMethod.POST)
    public User queryInfoById(@Param("roleName") String id) {
        logger.info("根据userId查询");
        return userService.queryInfoById(id);
    }

    @ResponseBody
    @RequestMapping(value = "updatePwd", method = RequestMethod.POST)
    public ControllerResult updatePwd(User user) {
        userService.updatePwd(user);
        logger.info("修改密码成功");
        return ControllerResult.getSuccessResult("修改密码成功");
    }

    @ResponseBody
    @RequestMapping(value = "queryIsPhoneByOne")
    public Map queryIsPhoneByOne(User user, HttpSession session) {
        int countPhone = userService.queryIsPhoneByOne(user.getUserPhone(), user.getUserId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countPhone > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "queryIsEmailByOne")
    public Map queryIsEmailByOne(User user, HttpSession session) {
        int countEmail = userService.queryIsEmailByOne(user.getUserEmail(), user.getUserId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countEmail > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "queryIsIdentityByOne")
    public Map queryIsIdentityByOne(User user, HttpSession session) {
        int countIdentity = userService.queryIsIdentityByOne(user.getUserIdentity(), user.getUserId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countIdentity > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    /**
     * 模糊查询
    */
    @ResponseBody
    @RequestMapping(value="queryByPagerLike", method = RequestMethod.GET)
    public Pager4EasyUI queryByPagerLike(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session, HttpServletRequest request) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && text != "" && value != null && value != "") {
                    List<User> users = null;
                    User user = new User();
                    if (text.equals("姓名/手机号/角色名称/所属公司")) {
                        user.setUserName(value);
                        user.setUserPhone(value);
                        user.setRoleId(value);
                        user.setCompanyId(value);
                    } else if (text.equals("姓名/手机号/角色名称")) {
                        user.setUserName(value);
                        user.setUserPhone(value);
                        user.setRoleId(value);
                    } else if (text.equals("姓名")) {
                        user.setUserName(value);
                    } else if (text.equals("手机号")) {
                        user.setUserPhone(value);
                    } else if (text.equals("角色名称")) {
                        user.setRoleId(value);
                    } else if (text.equals("所属公司")) {
                        user.setCompanyId(value);
                    }
                    pager.setUser(user);
                    users = userService.queryByPagerLike(pager);
                    pager.setTotalRecords(userService.countOK((User) session.getAttribute("user")));
                    System.out.print(users);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                } else {
                    pager.setTotalRecords(userService.countOK((User) session.getAttribute("user")));
                    List<User> users = userService.queryByPagerAll(pager);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
            }else {
                logger.info("此用户无人员基本信息模糊查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

//  个人资料修改
    @ResponseBody
    @RequestMapping(value = "updateSelfManage", method = RequestMethod.POST)
    public ControllerResult updateSelfManage(User user, HttpServletRequest request, HttpSession session) {
//        User user = (User)session.getAttribute("user");
        int count = userService.updateSelfManage(user);
        if(count > 0) {
            User u = userService.queryById(user);
            session.setAttribute("user", u);
        }
        return ControllerResult.getSuccessResult("修改个人资料成功");
    }

    /**
     * 分页查询车主信息
     */
    @ResponseBody
    @RequestMapping(value="queryCarByRoleName", method = RequestMethod.GET)
    public Pager4EasyUI queryCarByRoleName(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询车主用户基本信息成功");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                pager.setTotalRecords(userService.countCar());
                users = userService.queryCarByRoleName(pager);

                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 查询角色为车主的信息   可用
     */
    @ResponseBody
    @RequestMapping(value="queryCarByOk", method = RequestMethod.GET)
    public Pager4EasyUI queryCarByOk(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("根据状态分页查询角色为车主的信息 可用的记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                pager.setTotalRecords(userService.countCarByOk((User) session.getAttribute("user")));
                users = userService.queryCarByOk(pager);
                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 查询角色为车主的信息   不可用
     */
    @ResponseBody
    @RequestMapping(value="queryCarByNo", method = RequestMethod.GET)
    public Pager4EasyUI queryCarByNo(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("根据状态分页查询角色为车主的信息 可用的记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));

                String roleName = ((User) session.getAttribute("user")).getRole().getRoleName();
                List<User> users = null;
                pager.setTotalRecords(userService.countCarByNo((User) session.getAttribute("user")));
                users = userService.queryCarByNo(pager);
                return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
            } else {
                logger.info("此用户没有该操作所属的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 模糊查询 车主管理得模糊查询
     */
    @ResponseBody
    @RequestMapping(value="queryCarByPagerLike", method = RequestMethod.GET)
    public Pager4EasyUI queryCarByPagerLike(@Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, HttpSession session, HttpServletRequest request) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && text != "" && value != null && value != "") {
                    List<User> users = null;
                    User user = new User();
                    if (text.equals("姓名/手机号/角色名称/所属公司")) {
                        user.setUserName(value);
                        user.setUserPhone(value);
                        user.setRoleId(value);
                        user.setCompanyId(value);
                    } else if (text.equals("姓名/手机号/角色名称")) {
                        user.setUserName(value);
                        user.setUserPhone(value);
                        user.setRoleId(value);
                    } else if (text.equals("姓名")) {
                        user.setUserName(value);
                    } else if (text.equals("手机号")) {
                        user.setUserPhone(value);
                    } else if (text.equals("角色名称")) {
                        user.setRoleId(value);
                    } else if (text.equals("所属公司")) {
                        user.setCompanyId(value);
                    }
                    pager.setUser(user);
                    users = userService.queryCarByPagerLike(pager);
                    pager.setTotalRecords(userService.countAllCar((User) session.getAttribute("user")));
                    System.out.print(users);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                } else {
                    pager.setTotalRecords(userService.countOK((User) session.getAttribute("user")));
                    List<User> users = userService.queryCarByRoleName(pager);
                    return new Pager4EasyUI<User>(pager.getTotalRecords(), users);
                }
            }else {
                logger.info("此用户无人员基本信息模糊查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }
}