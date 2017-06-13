package com.gs.controller.custManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.TrackList;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.TrackListService;
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
 * Created by XiaoQiao on 2017/4/27.
 */

@Controller
@RequestMapping("/tracklist")
public class TracklistController {

    private Logger logger = (Logger) LoggerFactory.getLogger(TracklistController.class);

    @Autowired
    private HttpServletRequest req;

    @Resource
    private TrackListService trackListService;

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<TrackList> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查看跟踪回访记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                int count = trackListService.count((User) session.getAttribute("user"));
                pager.setTotalRecords(count);
                pager.setUser((User) session.getAttribute("user"));
                List<TrackList> queryList = trackListService.queryByPager(pager);
                return new Pager4EasyUI<TrackList>(pager.getTotalRecords(), queryList);
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
    public Pager4EasyUI<TrackList> queryName(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize, TrackList trackList) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("模糊查询跟踪回访记录");
                String text = req.getParameter("text");
                String value = req.getParameter("value");
                if (text != null && text != "") {
                    Pager pager = new Pager();
                    pager.setPageNo(Integer.valueOf(pageNumber));
                    pager.setPageSize(Integer.valueOf(pageSize));
                    if (text.equals("回访人")) {
                        trackList.setUserId(value);
                    } else if (text.equals("服务评价")) {
                        trackList.setServiceEvaluate(Integer.valueOf(value));
                    } else if (text.equals("跟踪回访车主")) {
                        trackList.setTrackUser(value);
                    } else if (text.equals("回访问题")) {
                        trackList.setTrackContent(value);
                    } else if (text.equals("跟踪回访车主/回访问题/服务评价")) {
                        trackList.setTrackUser(value);
                        trackList.setTrackContent(value);
                        trackList.setServiceEvaluate(Integer.valueOf(value));
                    }
                    int count = trackListService.countName(trackList, (User) session.getAttribute("user"));
                    pager.setTotalRecords(count);
                    pager.setUser((User) session.getAttribute("user"));
                    List<TrackList> queryList = trackListService.queryByPagerName(pager, trackList);
                    return new Pager4EasyUI<TrackList>(pager.getTotalRecords(), queryList);
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
    @RequestMapping(value = "queryUser", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryUser(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查看用户");
                List<User> users = userService.queryByRoleName("车主");
                List<ComboBox4EasyUI> combo = new ArrayList<ComboBox4EasyUI>();
                for (User u : users) {
                    ComboBox4EasyUI co = new ComboBox4EasyUI();
                    co.setId(u.getUserId());
                    co.setText(u.getUserName());
                    String userId = req.getParameter("userId");
                    if (u.getUserId().equals(userId)) {
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
    @RequestMapping(value = "queryAdmin", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAdmin(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查看用户");
                List<User> users = userService.queryByRoleName("汽车公司接待员");
                List<ComboBox4EasyUI> combo = new ArrayList<ComboBox4EasyUI>();
                for (User u : users) {
                    ComboBox4EasyUI co = new ComboBox4EasyUI();
                    co.setId(u.getUserId());
                    co.setText(u.getUserName());
                    u.getCompanyId();
                    String userId = req.getParameter("userId");
                    if (u.getUserId().equals(userId)) {
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
    public ControllerResult insert(HttpSession session, TrackList trackList) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("跟踪回访记录添加操作");
                User user = (User) session.getAttribute("user");
                trackList.setUserId(user.getUserId());
                trackList.setCompanyId(user.getCompanyId());
                trackListService.insert(trackList);
                return ControllerResult.getSuccessResult("添加回访记录成功");
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
    public ControllerResult update(HttpSession session, TrackList trackList) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("跟踪回访记录修改操作");
                trackListService.update(trackList);
                return ControllerResult.getSuccessResult("修改回访记录成功");
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
