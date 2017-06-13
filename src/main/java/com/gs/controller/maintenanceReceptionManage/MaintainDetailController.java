package com.gs.controller.maintenanceReceptionManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.bean.echarts.MaintainRecordBean;
import com.gs.bean.echarts.QuarterUtil;
import com.gs.common.bean.*;
import com.gs.common.util.DateFormatUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 维修保养明细管理controller， 张文星
 */
@Controller
@RequestMapping("/maintainDetail")
public class MaintainDetailController {

    private Logger logger = (Logger) LoggerFactory.getLogger(MaintainDetailController.class);

    // 明细service
    @Resource
    private MaintainDetailService maintainDetailService;
    // 物料清单service
    @Resource
    private MaterialListService materialListService;
    // 项目配件service
    @Resource
    private MaintainFixAccService maintainFixAccService;
    // 工单service
    @Resource
    private WorkInfoService workInfoService;
    // 维修保养记录service
    @Resource
    private MaintainRecordService maintainRecordService;

    /**
     * 查询所有明细
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryByPager(HttpSession session, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有明细");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(maintainDetailService.count((User)session.getAttribute("user")));
                List<MaintainDetail> maintainDetails = maintainDetailService.queryByPager(pager);
                return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
            }else{
                logger.info("此用户无拥有可用维修保养明细分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 前台查询最新的十条维修保养记录
     */
    @ResponseBody
    @RequestMapping("queryByFrontpage")
    public Pager4EasyUI<MaintainDetail> queryByFrontpage(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        logger.info("前台分布查看最新的十条维修保养记录");
        Pager pager = new Pager();
        pager.setPageNo(Integer.valueOf(pageNumber));
        pager.setPageSize(Integer.valueOf(pageSize));
        int count = 10;
        pager.setTotalRecords(count);
        List<MaintainDetail> queryList = maintainDetailService.queryByFrontpage(pager);
        return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), queryList);
    }

    /**
     * 根据维修保养记录查询此记录下所有明细
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByDetailPager/{recordId}", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryByRecordIdPager(HttpSession session, @PathVariable("recordId") String recordId, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分页查询此记录下所有明细");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(maintainDetailService.countByDetail(recordId));
                List<MaintainDetail> maintainDetails = maintainDetailService.queryByDetailByPager(pager, recordId);
                System.out.print(maintainDetails);
                return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
            }else{
                logger.info("此用户无拥有根据维修保养记录查询此记录下的维修保养明细分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 添加维修保养明细
     */
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult add(HttpSession session, MaintainDetail maintainDetail) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("添加维修保养明细");
                maintainDetailService.insert(maintainDetail);
                return ControllerResult.getSuccessResult("生成维修保养明细成功");
            }else{
                logger.info("此用户无拥有添加维修保养明细的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 修改维修保养明细
     */
    @ResponseBody
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public ControllerResult edit(HttpSession session, MaintainDetail maintainDetail) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("修改维修保养明细");
                maintainDetailService.update(maintainDetail);
                return ControllerResult.getSuccessResult("修改维修保养明细成功");
            }else{
                logger.info("此用户无拥有修改维修保养明细的角色");
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
                        maintainDetailService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活维修保养明细成功");
                    } else {
                        maintainDetailService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用维修保养明细成功");
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
     * 根据维修保养记录id查询此记录所有明细
     */
    @ResponseBody
    @RequestMapping(value = "queryByRecordId/{maintainRecordId}", method = RequestMethod.POST)
    public List<Double> queryByRecordId(HttpSession session, @PathVariable("maintainRecordId")String maintainRecordId) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("根据维修保养记录id查询此记录所有明细");
                // 拿到此维修保养记录下所有明细
                List<MaintainDetail> maintainDetails = maintainDetailService.queryByRecordId(maintainRecordId);
                Double disCountMoney = 0.d; // 折扣后总价
                Double money = 0.d; // 总价
                for(MaintainDetail md : maintainDetails){
                    money += md.getMaintainFix().getMaintainMoney();
                    disCountMoney +=md.getMaintainDiscount() * md.getMaintainFix().getMaintainMoney();
                }
                List<Double> doubles = new ArrayList<Double>();
                doubles.add(money);
                doubles.add(disCountMoney);
                return doubles;
            }else{
                logger.info("此用户无拥查询所有明细的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 车主根据维修保养记录查询此记录下所有明细
     * @return
     */
    @ResponseBody
    @RequestMapping(value="queryByOwner/{recordId}", method = RequestMethod.GET)
    public Pager4EasyUI<MaintainDetail> queryByOwner(HttpSession session, @PathVariable("recordId") String recordId, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isOwnerLogin(session)) {
            String roles = "车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("分页查询此记录下所有明细");
                User user = (User)session.getAttribute("frontUser");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setTotalRecords(maintainDetailService.countByOwner(recordId,user.getUserId()));
                List<MaintainDetail> maintainDetails = maintainDetailService.queryByOwner(pager, recordId, user.getUserId());
                System.out.print(maintainDetails);
                return new Pager4EasyUI<MaintainDetail>(pager.getTotalRecords(), maintainDetails);
            }else{
                logger.info("此用户无拥有根据维修保养记录查询此记录下的维修保养明细分页查询的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 查询维修保养明细中是否已经存在此维修保养项目, 不可同时存在
     */
    @ResponseBody
    @RequestMapping(value = "queryItem/{maintainId}/{recordId}", method = RequestMethod.POST)
    public ControllerResult queryItem(HttpSession session, @PathVariable("maintainId") String maintainId, @PathVariable("recordId") String recordId) {
        logger.info("查询维修保养明细中是否已经存在此维修保养项目, 不可同时存在");
        if (recordId != null && recordId != "" && maintainId != null && maintainId != "") {
            List<MaintainDetail> maintainDetails = maintainDetailService.queryByRecordId(recordId);
            for(MaintainDetail m : maintainDetails){
                if(m.getMaintainItemId().equals(maintainId)){
                    return ControllerResult.getFailResult("此维修保养项目以存在此维修保养记录中, 请重新选择");
                }
            }
            return ControllerResult.getSuccessResult("生成成功");
        } else {
            return ControllerResult.getFailResult("生成失败");
        }
    }

    /**
     * 用户确认, 此时生成所有物料清单, 两个参数一个为维修保养记录id, 一个为所有项目ids
     */
    @ResponseBody
    @RequestMapping(value = "userConfirm/{recordId}/{ids}/{tableDataLength}", method = RequestMethod.POST)
    public ControllerResult userConfirm(HttpSession session, @PathVariable("recordId") String recordId,@PathVariable("ids") String ids,@PathVariable("tableDataLength") String tableDataLength) {
        if(SessionUtil.isLogin(session) || SessionUtil.isOwnerLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("用户确认明细清单, 这时生成所有物料清单和工单");
                if(recordId != null && recordId != "" && ids!= null && ids != "" && tableDataLength!=null && tableDataLength!="") {
                    List<MaintainFixAcc> maintainFixAccs = maintainFixAccService.queryByRecord(ids);
                    int length = 0;// 明细中有多少行数据
                    try {
                        length = Integer.valueOf(tableDataLength);
                    }catch (NumberFormatException e){
                        logger.info("明细个数转换异常");
                    }
                    int maintainHour=0;// 工时
                    if(maintainFixAccs.size() >= length && maintainFixAccs.size() >0) {
                            List<MaterialList> materialLists = new ArrayList<MaterialList>();
                            for (MaintainFixAcc m : maintainFixAccs) {
                                MaterialList materialList = new MaterialList();
                                materialList.setMaintainRecordId(recordId);
                                materialList.setAccId(m.getAccId());
                                materialList.setMaterialCount(m.getAccCount());
                                materialLists.add(materialList);
                                maintainHour+=m.getMaintainFix().getMaintainHour();
                            }
                            materialListService.insertList(materialLists); // 生成物料清单
                            // 用户确认之后, 生成工单, 指派员工进行施工
                            WorkInfo w = new WorkInfo();
                            w.setRecordId(recordId);
                            workInfoService.insert(w);
                            // 修改维修保养记录
                            // 修改维修保养记录中的开始时间
                            MaintainRecord maintainRecord = maintainRecordService.queryById(recordId);
                            maintainRecord.setStartTime(new Date());// 增加维修保养开始时间
                            Date date = new Date();
                            Calendar now=Calendar.getInstance();
                            now.setTime(date);
                            now.add(Calendar.MINUTE,maintainHour*60);
                            maintainRecord.setEndTime(now.getTime());// 增加维修保养预估结束时间
                            maintainRecord.setCurrentStatus("维修保养中");
                            maintainRecord.setIfConfirm("Y");
                            maintainRecordService.update(maintainRecord);
                            return ControllerResult.getSuccessResult("确定成功, 请在派工领料为工单指派员工开始进行维修保养...");
                    }else{
                        return ControllerResult.getFailResult("确定失败, 此维修保养记录所属明细的维修项目并没有配件");
                    }
                }else{
                    return ControllerResult.getFailResult("确定失败");
                }
            }else{
                logger.info("此用户无拥有确认维修保养明细的角色");
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
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师";
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
                    maintainRecords = maintainRecordService.blurredQuery(pager, maintainRecord);
                    pager.setTotalRecords(maintainRecordService.countByBlurred(maintainRecord,(User)session.getAttribute("user")));
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }else{
                    pager.setTotalRecords(maintainRecordService.count((User)session.getAttribute("user")));
                    List<MaintainRecord> maintainRecords = maintainRecordService.queryByPager(pager);
                    return new Pager4EasyUI<MaintainRecord>(pager.getTotalRecords(), maintainRecords);
                }
            }else{
                logger.info("此用户无拥有维修保养明细模糊查询的角色");
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

    @ResponseBody
    @RequestMapping(value = "queryByCondition")
    public List<MaintainRecordBean> queryByCondition(HttpSession session, String start, String end, String type, String companyId){
        logger.info("维修记录报表");
        List<MaintainRecordBean> list = null;
        List<MaintainRecord> timeList = null;
        List<MaintainRecord> maintainList = null;
        List<MaintainRecord> preserveList = null;
        list = new ArrayList<MaintainRecordBean>();
        User user = (User) session.getAttribute("user");
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if(RoleUtil.checkRoles(roles)) {
                if (type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        if (companyId != null) {
                            timeList = maintainRecordService.queryByCondition(start, end,companyId,"0","year");
                            maintainList=maintainRecordService.queryByCondition(start, end,companyId,"1","year");
                            preserveList=maintainRecordService.queryByCondition(start, end,companyId,"2","year");
                        } else {
                            timeList = maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"0","year");
                            maintainList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"1","year");
                            preserveList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"2","year");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            MaintainRecordBean io = new MaintainRecordBean();
                            String ag = DateFormatUtil.YearFormater(timeList.get(p).getRecordCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < maintainList.size(); j++) {
                                String outTime = DateFormatUtil.YearFormater(maintainList.get(j).getRecordCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setMaintainCount(maintainList.get(j).getCount());
                                    break;
                                } else {
                                    io.setMaintainCount(0);
                                }
                            }
                            for (int k = 0; k < preserveList.size(); k++) {
                                String inTime = DateFormatUtil.YearFormater(preserveList.get(k).getRecordCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setPreserveCount(preserveList.get(k).getCount());
                                    break;
                                } else {
                                    io.setPreserveCount(0);
                                }
                            }
                            list.add(io);
                        }

                    } else if (type.equals("quarter")) {
                        if (companyId != null) {
                            timeList = maintainRecordService.queryByCondition(start, end,companyId,"0","quarter");
                            maintainList=maintainRecordService.queryByCondition(start, end,companyId,"1","quarter");
                            preserveList=maintainRecordService.queryByCondition(start, end,companyId,"2","quarter");
                        } else {
                            timeList = maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"0","quarter");
                            maintainList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"1","quarter");
                            preserveList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"2","quarter");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            MaintainRecordBean io = new MaintainRecordBean();
                            String ag = DateFormatUtil.MonthFormater(timeList.get(p).getRecordCreatedTime());
                            QuarterUtil.quarter(ag);
                            io.setDate(ag);
                            for (int j = 0; j < maintainList.size(); j++) {
                                String outTime = DateFormatUtil.MonthFormater(maintainList.get(j).getRecordCreatedTime());
                                QuarterUtil.quarter(outTime);
                                if (ag.equals(outTime)) {
                                    io.setMaintainCount(maintainList.get(j).getCount());
                                    break;
                                } else {
                                    io.setMaintainCount(0);
                                }
                            }
                            for (int k = 0; k < preserveList.size(); k++) {
                                String inTime = DateFormatUtil.MonthFormater(preserveList.get(k).getRecordCreatedTime());
                                QuarterUtil.quarter(inTime);
                                if (ag.equals(inTime)) {
                                    io.setPreserveCount(preserveList.get(k).getCount());
                                    break;
                                } else {
                                    io.setPreserveCount(0);
                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("month")) {
                        if (companyId != null) {
                            timeList = maintainRecordService.queryByCondition(start, end,companyId,"0","month");
                            maintainList=maintainRecordService.queryByCondition(start, end,companyId,"1","month");
                            preserveList=maintainRecordService.queryByCondition(start, end,companyId,"2","month");
                        } else {
                            timeList = maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"0","month");
                            maintainList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"1","month");
                            preserveList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"2","month");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            MaintainRecordBean io = new MaintainRecordBean();
                            String ag = DateFormatUtil.MonthFormater(timeList.get(p).getRecordCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < maintainList.size(); j++) {
                                String outTime = DateFormatUtil.MonthFormater(maintainList.get(j).getRecordCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setMaintainCount(maintainList.get(j).getCount());
                                    break;
                                } else {
                                    io.setMaintainCount(0);
                                }
                            }
                            for (int k = 0; k < preserveList.size(); k++) {
                                String inTime = DateFormatUtil.MonthFormater(preserveList.get(k).getRecordCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setPreserveCount(preserveList.get(k).getCount());
                                    break;
                                } else {
                                    io.setPreserveCount(0);
                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("week")) {
                        if (companyId != null) {
                            timeList = maintainRecordService.queryByCondition(start, end,companyId,"0","week");
                            maintainList=maintainRecordService.queryByCondition(start, end,companyId,"1","week");
                            preserveList=maintainRecordService.queryByCondition(start, end,companyId,"2","week");
                        } else {
                            timeList = maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"0","week");
                            maintainList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"1","week");
                            preserveList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"2","week");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            MaintainRecordBean io = new MaintainRecordBean();
                            String ag = DateFormatUtil.WEEK(timeList.get(p).getRecordCreatedTime());
                            String year = DateFormatUtil.YearFormater(timeList.get(p).getRecordCreatedTime());
                            String time = String.valueOf(Echarts.getWeek(ag));
                            String yearTime = time + year;
                            io.setDate(year + "第" + time + "周");
                            for (int j = 0; j < maintainList.size(); j++) {
                                String outTime = DateFormatUtil.WEEK(maintainList.get(j).getRecordCreatedTime());
                                String outYear = DateFormatUtil.YearFormater(maintainList.get(j).getRecordCreatedTime());
                                String out = String.valueOf(Echarts.getWeek(outTime));
                                String yearOut = out + outYear;
                                if (yearTime.equals(yearOut)) {
                                    io.setMaintainCount(maintainList.get(j).getCount());
                                    break;
                                } else {
                                    io.setMaintainCount(0);
                                }
                            }
                            for (int k = 0; k < preserveList.size(); k++) {
                                String inTime = DateFormatUtil.WEEK(preserveList.get(k).getRecordCreatedTime());
                                String inYear = DateFormatUtil.YearFormater(preserveList.get(k).getRecordCreatedTime());
                                String in = String.valueOf(Echarts.getWeek(inTime));
                                String yearIn = in + inYear;
                                if (yearTime.equals(yearIn)) {
                                    io.setPreserveCount(preserveList.get(k).getCount());
                                    break;
                                } else {
                                    io.setPreserveCount(0);
                                }
                            }
                            list.add(io);
                        }
                    } else if (type.equals("day")) {
                        if (companyId != null) {
                            timeList = maintainRecordService.queryByCondition(start, end,companyId,"0","day");
                            maintainList=maintainRecordService.queryByCondition(start, end,companyId,"1","day");
                            preserveList=maintainRecordService.queryByCondition(start, end,companyId,"2","day");
                        } else {
                            timeList = maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"0","day");
                            maintainList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"1","day");
                            preserveList=maintainRecordService.queryByCondition(start, end,user.getCompanyId(),"2","day");
                        }
                        for (int p = 0; p < timeList.size(); p++) {
                            MaintainRecordBean io = new MaintainRecordBean();
                            String ag = DateFormatUtil.DayFormater(timeList.get(p).getRecordCreatedTime());
                            io.setDate(ag);
                            for (int j = 0; j < maintainList.size(); j++) {
                                String outTime = DateFormatUtil.DayFormater(maintainList.get(j).getRecordCreatedTime());
                                if (ag.equals(outTime)) {
                                    io.setMaintainCount(maintainList.get(j).getCount());
                                    break;
                                } else {
                                    io.setMaintainCount(0);
                                }
                            }
                            for (int k = 0; k < preserveList.size(); k++) {
                                String inTime = DateFormatUtil.DayFormater(preserveList.get(k).getRecordCreatedTime());
                                if (ag.equals(inTime)) {
                                    io.setPreserveCount(preserveList.get(k).getCount());
                                    break;
                                } else {
                                    io.setPreserveCount(0);
                                }
                            }
                            list.add(io);
                        }
                    }
                }
                return list;
            }else{
                logger.info("此用户无拥有查询维修记录的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }



}
