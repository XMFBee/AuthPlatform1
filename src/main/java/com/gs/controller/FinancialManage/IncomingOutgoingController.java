package com.gs.controller.FinancialManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.IncomingType;
import com.gs.bean.User;
import com.gs.bean.echarts.IncomingOutInFo;
import com.gs.bean.IncomingOutgoing;
import com.gs.bean.echarts.QuarterUtil;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Echarts;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.IncomingOutgoingService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by GZWangBin on 2017/4/20.
 */
@Controller
@RequestMapping("/incomingOutgoing")
public class IncomingOutgoingController {

    private Logger logger = (Logger) LoggerFactory.getLogger(IncomingOutgoingController.class);

    /**
     * 收入Service
     */
    @Resource
    public IncomingOutgoingService incomingOutgoingService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("收支记录分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(incomingOutgoingService.count((User) session.getAttribute("user")));
                List<IncomingOutgoing> incomingOutgoings = incomingOutgoingService.queryByPager(pager);
                return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingOutgoings);
            } else {
                logger.info("此用户无拥有收支记录分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }


    }

    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("禁用收支记录分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(incomingOutgoingService.countByDisable((User) session.getAttribute("user")));
                List<IncomingOutgoing> incomingOutgoings = incomingOutgoingService.queryByPagerDisable(pager);
                return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), incomingOutgoings);
            } else {
                logger.info("此用户无拥禁用收支记录分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<IncomingOutgoing> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("工资分类模糊查询");
                Pager pager = new Pager();
                pager.setUser((User) session.getAttribute("user"));
                pager.setPageNo(Integer.parseInt(pageNumber));
                pager.setPageSize(Integer.parseInt(pageSize));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && !text.equals("") && value != null && !value.equals("")) {
                    List<IncomingOutgoing> salaries = null;
                    IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                    if (text.equals("收入类型")) {
                        incomingOutgoing.setInTypeId(value);
                    } else if (text.equals("支出类型")) {
                        incomingOutgoing.setOutTypeId(value);
                    }
                    salaries = incomingOutgoingService.blurredQuery(pager, incomingOutgoing);
                    pager.setTotalRecords(incomingOutgoingService.countByBlurred(incomingOutgoing, (User) session.getAttribute("user")));
                    return new Pager4EasyUI<IncomingOutgoing>(pager.getTotalRecords(), salaries);
                } else {
                    return null;
                }
            } else {
                logger.info("此用户无拥有模糊查询支出类型和收入类型的角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }
    }

    /**
     * 对状态的激活和启用，只使用一个方法进行切换。
     */
    @ResponseBody
    @RequestMapping(value = "statusOperate", method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session, String id, String status) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        incomingOutgoingService.active(id);
                        logger.info("激活收支记录成功");
                        return ControllerResult.getSuccessResult("激活收支记录成功");
                    } else {
                        incomingOutgoingService.inactive(id);
                        logger.info("禁用收支记录成功");
                        return ControllerResult.getSuccessResult("禁用收支记录成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            } else {
                logger.info("此用户无拥启用和禁用收支记录的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult add(HttpSession session, IncomingOutgoing incomingOutgoing) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("添加收支记录");
                User user = (User) session.getAttribute("user");
                incomingOutgoing.setInOutCreatedUser(user.getUserId());
                incomingOutgoing.setCompanyId(user.getCompanyId());
                incomingOutgoingService.insert(incomingOutgoing);
                return ControllerResult.getSuccessResult("添加收支记录成功");
            } else {
                logger.info("此用户无拥添加收支记录的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, IncomingOutgoing incomingOutgoing) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("修改收支记录");
                User user = (User) session.getAttribute("user");
                if (incomingOutgoing.getInTypeId() == "" && incomingOutgoing.getInTypeId() == null) {
                    incomingOutgoing.setInTypeId("");
                }
                if (incomingOutgoing.getOutTypeId() == "" && incomingOutgoing.getOutTypeId() == null) {
                    incomingOutgoing.setOutTypeId("");
                }
                incomingOutgoingService.update(incomingOutgoing);
                return ControllerResult.getSuccessResult("修改收支记录成功");
            } else {
                logger.info("此用户无拥有修改收支记录的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value = "queryByCondition")
    public List<IncomingOutInFo> queryByCondition(HttpSession session, String start, String end, String type, String companyId) {
        logger.info("按年月日周季查询财务报表");
        List<IncomingOutInFo> list = null;
        List<IncomingOutgoing> timeList = null;
        List<IncomingOutgoing> outList = null;
        List<IncomingOutgoing> inList = null;
        User user = (User) session.getAttribute("user");
        list = new ArrayList<IncomingOutInFo>();
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                if (type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        if (companyId != null) {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", companyId, "year");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", companyId, "year");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", companyId, "year");
                        } else {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", user.getCompanyId(), "year");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", user.getCompanyId(), "year");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", user.getCompanyId(), "year");
                        }

                        for (int p = 0; p < timeList.size(); p++) {
                            IncomingOutInFo io = new IncomingOutInFo();
                            String ag = DateFormatUtil.YearFormater(timeList.get(p).getInOutCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < outList.size(); j++) {
                                String outTime = DateFormatUtil.YearFormater(outList.get(j).getInOutCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setOutMoney(outList.get(j).getInOutMoney());
                                    break;
                                } else {
                                    io.setOutMoney(0.d);
                                }
                            }
                            for (int k = 0; k < inList.size(); k++) {
                                String inTime = DateFormatUtil.YearFormater(inList.get(k).getInOutCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setInMoney(inList.get(k).getInOutMoney());
                                    break;
                                } else {
                                    io.setInMoney(0.d);
                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("quarter")) {
                        if (companyId != null) {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", companyId, "quarter");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", companyId, "quarter");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", companyId, "quarter");
                        } else {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", user.getCompanyId(), "quarter");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", user.getCompanyId(), "quarter");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", user.getCompanyId(), "quarter");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            IncomingOutInFo io = new IncomingOutInFo();
                            String ag = DateFormatUtil.MonthFormater(timeList.get(p).getInOutCreatedTime());
                            QuarterUtil.quarter(ag);
                            io.setDate(ag);

                            for (int j = 0; j < outList.size(); j++) {
                                String outTime = DateFormatUtil.MonthFormater(outList.get(j).getInOutCreatedTime());
                                QuarterUtil.quarter(outTime);
                                if (ag.equals(outTime)) {
                                    io.setOutMoney(outList.get(j).getInOutMoney());
                                    break;

                                } else {
                                    io.setOutMoney(0.d);
                                }
                            }
                            for (int k = 0; k < inList.size(); k++) {
                                String inTime = DateFormatUtil.MonthFormater(inList.get(k).getInOutCreatedTime());
                                QuarterUtil.quarter(inTime);
                                if (ag.equals(inTime)) {
                                    io.setInMoney(inList.get(k).getInOutMoney());
                                    break;

                                } else {
                                    io.setInMoney(0.d);
                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("month")) {
                        if (companyId != null) {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", companyId, "month");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", companyId, "month");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", companyId, "month");
                        } else {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", user.getCompanyId(), "month");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", user.getCompanyId(), "month");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", user.getCompanyId(), "month");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            IncomingOutInFo io = new IncomingOutInFo();
                            String ag = DateFormatUtil.MonthFormater(timeList.get(p).getInOutCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < outList.size(); j++) {
                                String outTime = DateFormatUtil.MonthFormater(outList.get(j).getInOutCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setOutMoney(outList.get(j).getInOutMoney());
                                    break;
                                } else {
                                    io.setOutMoney(0.d);
                                }
                            }
                            for (int k = 0; k < inList.size(); k++) {
                                String inTime = DateFormatUtil.MonthFormater(inList.get(k).getInOutCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setInMoney(inList.get(k).getInOutMoney());
                                    break;
                                } else {
                                    io.setInMoney(0.d);

                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("week"))

                    {
                        if (companyId != null) {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", companyId, "week");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", companyId, "week");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", companyId, "week");
                        } else {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", user.getCompanyId(), "week");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", user.getCompanyId(), "week");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", user.getCompanyId(), "week");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            IncomingOutInFo io = new IncomingOutInFo();
                            String ag = DateFormatUtil.WEEK(timeList.get(p).getInOutCreatedTime());
                            String year = DateFormatUtil.YearFormater(timeList.get(p).getInOutCreatedTime());
                            String time = String.valueOf(Echarts.getWeek(ag));
                            String yearTime = time + year;
                            io.setDate(year + "第" + time + "周");
                            for (int j = 0; j < outList.size(); j++) {
                                String outTime = DateFormatUtil.WEEK(outList.get(j).getInOutCreatedTime());
                                String outYear = DateFormatUtil.YearFormater(outList.get(j).getInOutCreatedTime());
                                String out = String.valueOf(Echarts.getWeek(outTime));
                                String yearOut = out + outYear;
                                if (yearTime.equals(yearOut)) {
                                    io.setOutMoney(outList.get(j).getInOutMoney());
                                    break;
                                } else {
                                    io.setOutMoney(0.d);
                                }
                            }
                            for (int k = 0; k < inList.size(); k++) {
                                String inTime = DateFormatUtil.WEEK(inList.get(k).getInOutCreatedTime());
                                String inYear = DateFormatUtil.YearFormater(inList.get(k).getInOutCreatedTime());
                                String in = String.valueOf(Echarts.getWeek(inTime));
                                String yearIn = in + inYear;
                                if (yearTime.equals(yearIn)) {
                                    io.setInMoney(inList.get(k).getInOutMoney());
                                    break;
                                } else {
                                    io.setInMoney(0.d);

                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("day"))

                    {
                        if (companyId != null) {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", companyId, "day");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", companyId, "day");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", companyId, "day");
                        } else {
                            timeList = incomingOutgoingService.queryByCondition(start, end, "0", user.getCompanyId(), "day");
                            outList = incomingOutgoingService.queryByCondition(start, end, "1", user.getCompanyId(), "day");
                            inList = incomingOutgoingService.queryByCondition(start, end, "2", user.getCompanyId(), "day");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            IncomingOutInFo io = new IncomingOutInFo();
                            String ag = DateFormatUtil.DayFormater(timeList.get(p).getInOutCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < outList.size(); j++) {
                                String outTime = DateFormatUtil.DayFormater(outList.get(j).getInOutCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setOutMoney(outList.get(j).getInOutMoney());
                                    break;

                                } else {
                                    io.setOutMoney(0.d);
                                }
                            }
                            for (int k = 0; k < inList.size(); k++) {
                                String inTime = DateFormatUtil.DayFormater(inList.get(k).getInOutCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setInMoney(inList.get(k).getInOutMoney());
                                    break;

                                } else {
                                    io.setInMoney(0.d);
                                }
                            }
                            list.add(io);
                        }
                    }

                }
            } else {
                logger.info("此用户无拥有修改收支记录的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
        return list;
    }
}
