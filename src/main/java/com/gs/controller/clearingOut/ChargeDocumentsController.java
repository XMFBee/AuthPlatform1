package com.gs.controller.clearingOut;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.bean.echarts.ChargeBillBean;
import com.gs.bean.echarts.QuarterUtil;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Echarts;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.ExcelExport;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.ChargeBillService;
import com.gs.service.CheckinService;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.MaintainRecordService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收费单据管理controller， 张文星
 */
@Controller
@RequestMapping("/charge")
public class ChargeDocumentsController {

    private Logger logger = (Logger) LoggerFactory.getLogger(ChargeDocumentsController.class);

    // 登记service
    @Resource
    private CheckinService checkinService;
    // 维修保养记录service
    @Resource
    private MaintainRecordService maintainRecordService;
    // 收费单据service
    @Resource
    private ChargeBillService chargeBillService;
    // 收支记录service
    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    /**
     * 分页查询所有收费单据
     */
    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有收费单据");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(chargeBillService.count((User) session.getAttribute("user")));
                List<ChargeBill> chargeBills = chargeBillService.queryByPager(pager);
                return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
            } else {
                logger.info("此用户无拥有可用收费单据分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询所有收费单据
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByOwner", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryByOwner(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("车主分页查询所有收费单据");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("frontUser"));
                pager.setTotalRecords(chargeBillService.countByOwner((User) session.getAttribute("frontUser")));
                List<ChargeBill> chargeBills = chargeBillService.queryByOwner(pager);
                return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
            } else {
                logger.info("此用户无拥有收费单据分页查询的车主角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 查询所有被禁用的登记记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用收费单据");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(chargeBillService.countByDisable((User) session.getAttribute("user")));
                List<ChargeBill> chargeBills = chargeBillService.queryByPagerDisable(pager);
                return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
            } else {
                logger.info("此用户无拥有禁用收费单据分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // 添加收费单据, 此方法完成结算出厂
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult addCheckin(HttpSession session, ChargeBill chargeBill) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("添加收费单据");
                maintainRecordService.updateCurrentStatus("待收费", "'" + chargeBill.getMaintainRecordId() + "'");
                chargeBillService.insert(chargeBill);
                return ControllerResult.getSuccessResult("结算出厂成功");
            } else {
                logger.info("此用户无拥有添加收费单据的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult editCheckin(HttpSession session, ChargeBill chargeBill) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("修改收费单据");
                if (chargeBill.getChargeBillStatus().equals("N")) {
                    chargeBill.setChargeBillStatus("Y");
                }
                chargeBillService.update(chargeBill);
                return ControllerResult.getSuccessResult("修改成功");
            } else {
                logger.info("此用户无拥有修改收费单据的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 确认收费
     */
    @ResponseBody
    @RequestMapping(value = "updateDate/{chargeBillId}/{maintainRecordId}", method = RequestMethod.POST)
    public ControllerResult updateDate(HttpSession session, @PathVariable("chargeBillId") String chargeBillId, @PathVariable("maintainRecordId") String maintainRecordId) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                User user = (User) session.getAttribute("user");
                chargeBillService.updateDate(chargeBillId);
                maintainRecordService.updatePickupTime(maintainRecordId);
                maintainRecordService.updateCurrentStatus("已收费", "'" + maintainRecordId + "'");
                return ControllerResult.getSuccessResult("确认收费成功");
            } else {
                logger.info("此用户无拥有此方法角色");
                return ControllerResult.getFailResult("权限不足");
            }
        } else {
            logger.info("请先登陆");
            return ControllerResult.getFailResult("请先登录");
        }

    }

    /**
     * 用户对收费单据进行确认
     */
    @ResponseBody
    @RequestMapping(value = "updateCurrent", method = RequestMethod.POST)
    public ControllerResult updateCurrent(HttpSession session, String id){
        if (SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("")) {
                    chargeBillService.updateCurrent(id);
                    return ControllerResult.getSuccessResult("确认成功");
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            } else {
                logger.info("此用户无拥有可用收费单据更改状态的角色");
                return null;
            }
        } else {
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
                        chargeBillService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活成功");
                    } else {
                        chargeBillService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else{
                logger.info("此用户无拥有可用收费单据更改状态的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 收费单据模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<ChargeBill> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
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
                if(text != null && text!="") {
                    List<ChargeBill> chargeBills = null;
                    ChargeBill chargeBill = new ChargeBill();
                    MaintainRecord maintainRecord = new MaintainRecord();
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
                    maintainRecord.setCheckin(checkin);
                    chargeBill.setMaintainRecord(maintainRecord);
                    chargeBills = chargeBillService.blurredQuery(pager, chargeBill);
                    pager.setTotalRecords(chargeBillService.countByBlurred(chargeBill, (User)session.getAttribute("user")));
                    return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
                }else{
                    pager.setTotalRecords(chargeBillService.count((User)session.getAttribute("user")));
                    List<ChargeBill> chargeBills = chargeBillService.queryByPager(pager);
                    return new Pager4EasyUI<ChargeBill>(pager.getTotalRecords(), chargeBills);
                }
            }else{
                logger.info("此用户无拥有收费单据模糊查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    @RequestMapping(value="exportExcel", method = RequestMethod.GET)
    public void exportExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("收费单据导出");
                try {
                    ChargeBill chargeBill = new ChargeBill();
                    // 查询所有收费单据
                    List<ChargeBill> chargeBills = chargeBillService.queryAll((User)session.getAttribute("user"));
                    String title = "收费单据";
                    String[] rowsName = new String[]{"收费单据编号", "车主姓名", "车主手机", "汽车品牌",
                            "汽车车型", "汽车颜色", "汽车车牌", "车牌号码", "维修保养记录提车时间",
                            "维修保养记录描述", "付款方式", "总金额", "实际付款", "收费时间", "收费单据创建时间",
                            "收费单据描述", "收费单据状态"};
                    List<Object[]> dataList = new ArrayList<Object[]>();
                    Object[] objs = null;
                    for (ChargeBill c : chargeBills) {
                        objs = new Object[rowsName.length];
                        objs[0] = c.getChargeBillId();
                        objs[1] = c.getMaintainRecord().getCheckin().getUserName();
                        objs[2] = c.getMaintainRecord().getCheckin().getUserPhone();
                        objs[3] = c.getMaintainRecord().getCheckin().getBrand().getBrandName();
                        objs[4] = c.getMaintainRecord().getCheckin().getModel().getModelName();
                        objs[5] = c.getMaintainRecord().getCheckin().getColor().getColorName();
                        objs[6] = c.getMaintainRecord().getCheckin().getPlate().getPlateName();
                        objs[7] = c.getMaintainRecord().getCheckin().getCarPlate();
                        objs[8] = c.getMaintainRecord().getPickupTime();
                        objs[9] = c.getMaintainRecord().getRecordDes();
                        objs[10] = c.getPaymentMethod();
                        objs[11] = c.getChargeBillMoney();
                        objs[12] = c.getActualPayment();
                        objs[13] = c.getChargeTime();
                        if(c.getChargeCreatedTime() != null) {
                            objs[14] = java.sql.Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getChargeCreatedTime()));
                        }else{
                            objs[14] = null;
                        }
                        objs[15] = c.getChargeBillDes();
                        objs[16] = "可用";
                        dataList.add(objs);
                    }
                    ExcelExport ex = new ExcelExport(title, rowsName, dataList, response);
                    ex.exportData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                logger.info("此用户无拥有收费单据导出的角色");
            }
        }else{
            logger.info("请先登录");
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

    @ResponseBody
    @RequestMapping(value = "queryByCondition")
    public List<ChargeBillBean> queryByCondition(HttpSession session, String start, String end, String type){
        logger.info("根据年月日周季去查询所有的消费统计");
        List<ChargeBillBean> list = null;
        List<ChargeBill> timeList = null;
        List<ChargeBill> maintainList = null;
        List<ChargeBill> preserveList = null;
        list = new ArrayList<ChargeBillBean>();
        User user = (User)session.getAttribute("frontUser");
        if (type != null && !type.equals("")) {
            if (type.equals("year")) {
                timeList = chargeBillService.queryByCondition(start, end,user.getUserId(),"0","year");
                maintainList=chargeBillService.queryByCondition(start, end,user.getUserId(),"1","year");
                preserveList=chargeBillService.queryByCondition(start, end,user.getUserId(),"2","year");
                for (int p = 0; p < timeList.size(); p++) {
                    ChargeBillBean io = new ChargeBillBean();
                    String ag = DateFormatUtil.YearFormater(timeList.get(p).getChargeCreatedTime());
                    io.setDate(ag);
                    for (int j = 0; j < maintainList.size(); j++) {
                        String outTime = DateFormatUtil.YearFormater(maintainList.get(j).getChargeCreatedTime());
                        if (ag.equals(outTime)) {
                            io.setMaintainMoney(maintainList.get(j).getActualPayment());
                            break;
                        } else {
                            io.setMaintainMoney(0.d);
                        }
                    }
                    for (int k = 0; k < preserveList.size(); k++) {
                        String inTime = DateFormatUtil.YearFormater(preserveList.get(k).getChargeCreatedTime());
                        if (ag.equals(inTime)) {
                            io.setPreserveMoney(preserveList.get(k).getActualPayment());
                            break;
                        } else {
                            io.setPreserveMoney(0.d);
                        }
                    }
                    list.add(io);
                }

            } else if (type.equals("quarter")) {
                timeList = chargeBillService.queryByCondition(start, end,user.getUserId(),"0","quarter");
                maintainList=chargeBillService.queryByCondition(start, end,user.getUserId(),"1","quarter");
                preserveList=chargeBillService.queryByCondition(start, end,user.getUserId(),"2","quarter");
                for (int p = 0; p < timeList.size(); p++) {
                    ChargeBillBean io = new ChargeBillBean();
                    String ag = DateFormatUtil.MonthFormater(timeList.get(p).getChargeCreatedTime());
                    QuarterUtil.quarter(ag);
                    io.setDate(ag);
                    for (int j = 0; j < maintainList.size(); j++) {
                        String outTime = DateFormatUtil.MonthFormater(maintainList.get(j).getChargeCreatedTime());
                        QuarterUtil.quarter(outTime);
                        if (ag.equals(outTime)) {
                            io.setMaintainMoney(maintainList.get(j).getActualPayment());
                            break;
                        } else {
                            io.setMaintainMoney(0.d);
                        }
                    }
                    for (int k = 0; k < preserveList.size(); k++) {
                        String inTime = DateFormatUtil.MonthFormater(preserveList.get(k).getChargeCreatedTime());
                        QuarterUtil.quarter(inTime);
                        if (ag.equals(inTime)) {
                            io.setPreserveMoney(preserveList.get(k).getActualPayment());
                            break;
                        } else {
                            io.setPreserveMoney(0.d);
                        }
                    }
                    list.add(io);
                }
            } else if (type.equals("month")) {
                timeList = chargeBillService.queryByCondition(start, end,user.getUserId(),"0","month");
                maintainList=chargeBillService.queryByCondition(start, end,user.getUserId(),"1","month");
                preserveList=chargeBillService.queryByCondition(start, end,user.getUserId(),"2","month");
                for (int p = 0; p < timeList.size(); p++) {
                    ChargeBillBean io = new ChargeBillBean();
                    String ag = DateFormatUtil.MonthFormater(timeList.get(p).getChargeCreatedTime());
                    io.setDate(ag);
                    for (int j = 0; j < maintainList.size(); j++) {
                        String outTime = DateFormatUtil.MonthFormater(maintainList.get(j).getChargeCreatedTime());
                        if (ag.equals(outTime)) {
                            io.setMaintainMoney(maintainList.get(j).getActualPayment());
                            break;
                        } else {
                            io.setMaintainMoney(0.d);
                        }
                    }
                    for (int k = 0; k < preserveList.size(); k++) {
                        String inTime = DateFormatUtil.MonthFormater(preserveList.get(k).getChargeCreatedTime());
                        if (ag.equals(inTime)) {
                            io.setPreserveMoney(preserveList.get(k).getActualPayment());
                            break;
                        } else {
                            io.setPreserveMoney(0.d);
                        }
                    }
                    list.add(io);
                }
            } else if (type.equals("week")) {
                timeList = chargeBillService.queryByCondition(start, end,user.getUserId(),"0","week");
                maintainList=chargeBillService.queryByCondition(start, end,user.getUserId(),"1","week");
                preserveList=chargeBillService.queryByCondition(start, end,user.getUserId(),"2","week");
                for (int p = 0; p < timeList.size(); p++) {
                    ChargeBillBean io = new ChargeBillBean();
                    String ag = DateFormatUtil.WEEK(timeList.get(p).getChargeCreatedTime());
                    String year = DateFormatUtil.YearFormater(timeList.get(p).getChargeCreatedTime());
                    String time = String.valueOf(Echarts.getWeek(ag));
                    String yearTime = time + year;
                    io.setDate(year + "第" + time + "周");
                    for (int j = 0; j < maintainList.size(); j++) {
                        String outTime = DateFormatUtil.WEEK(maintainList.get(j).getChargeCreatedTime());
                        String outYear = DateFormatUtil.YearFormater(maintainList.get(j).getChargeCreatedTime());
                        String out = String.valueOf(Echarts.getWeek(outTime));
                        String yearOut = out + outYear;
                        if (yearTime.equals(yearOut)) {
                            io.setMaintainMoney(maintainList.get(j).getActualPayment());
                            break;
                        } else {
                            io.setMaintainMoney(0.d);
                        }
                    }
                    for (int k = 0; k < preserveList.size(); k++) {
                        String inTime = DateFormatUtil.WEEK(preserveList.get(k).getChargeCreatedTime());
                        String inYear = DateFormatUtil.YearFormater(preserveList.get(k).getChargeCreatedTime());
                        String in = String.valueOf(Echarts.getWeek(inTime));
                        String yearIn = in + inYear;
                        if (yearTime.equals(yearIn)) {
                            io.setPreserveMoney(preserveList.get(k).getActualPayment());
                            break;
                        } else {
                            io.setPreserveMoney(0.d);
                        }
                    }
                    list.add(io);
                }
            } else if (type.equals("day")) {
                timeList = chargeBillService.queryByCondition(start, end,user.getUserId(),"0","day");
                maintainList=chargeBillService.queryByCondition(start, end,user.getUserId(),"1","day");
                preserveList=chargeBillService.queryByCondition(start, end,user.getUserId(),"2","day");
                for (int p = 0; p < timeList.size(); p++) {
                    ChargeBillBean io = new ChargeBillBean();
                    String ag = DateFormatUtil.DayFormater(timeList.get(p).getChargeCreatedTime());
                    io.setDate(ag);
                    for (int j = 0; j < maintainList.size(); j++) {
                        String outTime = DateFormatUtil.DayFormater(maintainList.get(j).getChargeCreatedTime());
                        if (ag.equals(outTime)) {
                            io.setMaintainMoney(maintainList.get(j).getActualPayment());
                            break;
                        } else {
                            io.setMaintainMoney(0.d);
                        }
                    }
                    for (int k = 0; k < preserveList.size(); k++) {
                        String inTime = DateFormatUtil.DayFormater(preserveList.get(k).getChargeCreatedTime());
                        if (ag.equals(inTime)) {
                            io.setPreserveMoney(preserveList.get(k).getActualPayment());
                            break;
                        } else {
                            io.setPreserveMoney(0.d);
                        }
                    }
                    list.add(io);
                }
            }
        }
        return list;
    }
}
