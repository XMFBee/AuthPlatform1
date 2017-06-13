package com.gs.controller.pickingManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.bean.view.MaterialURTemp;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.controller.SystemManageController;
import com.gs.service.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 领料与退料
 *
 * @author 程燕
 * @create 2017-04-24 14:53
 */
@Controller
@RequestMapping("/materials")
public class MaterialsController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SystemManageController.class);


    @Resource
    private MaterialListService materialListService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private MaterialUseService materialUseService;

    @Resource
    private MaterialReturnService materialReturnService;

    @Resource
    private WorkInfoService workInfoService;

    @Resource
    private RepositoryService repositoryService;
    @Resource
    private RuntimeService runtimeService;
    @Resource
    private TaskService taskService;
    @Resource
    private FormService formService;
    @Resource
    private HistoryService historyService;
    @Resource
    private ManagementService managementService;
    @Resource
    private IdentityService identityService;



    @ResponseBody       //可能用不到了
    @RequestMapping("queryUserMaterialsByPager")
    public Pager4EasyUI materialsByPager(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session){
        // TODO 公司员工
        if(SessionUtil.isLogin(session)) {
                User user = (User) session.getAttribute("user");
                Pager pager = new Pager();
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager.setPageNo(pageNumber);
                pager.setPageSize(pageSize);
                int total = materialListService.count(user.getUserId());
                pager4EasyUI.setTotal(total);
                List list = materialListService.queryByPager(user.getUserId(), pager);
                pager4EasyUI.setRows(list);
                return pager4EasyUI;
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("history") // 查询一个员工的领料历史记录
    public Pager4EasyUI historyByPager(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session){
        // TODO 公司员工
        if(SessionUtil.isLogin(session)) {
            User user = (User)session.getAttribute("user");
            Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
            Pager pager = new Pager();
            //当前以用户1号查询,后期需要判断是否拥有查询所有领用记录才可以使用
            int total = materialUseService.countUserHist(user.getUserId());
            pager.setPageNo(pageNumber);
            pager.setPageSize(pageSize);
            pager4EasyUI.setTotal(total);
            List rows = materialUseService.userHistByPager(pager,user.getUserId());
            pager4EasyUI.setRows(rows);
            return pager4EasyUI;
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("finishWorkByUser") // 查询一个员工的领料历史记录
    public Pager4EasyUI queryFinishWorkByUser(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(SessionUtil.isLogin(session)) {
            Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
            Pager pager = new Pager();
            //当前以用户1号查询,后期需要判断是否拥有查询所有领用记录才可以使用
            int total = materialUseService.countUserWorksStatus(user,"Y");
            pager.setPageNo(pageNumber);
            pager.setPageSize(pageSize);
            List workInfos = materialUseService.userWorksStatusByPager(user, "Y",pager);
            pager4EasyUI.setTotal(total);
            pager4EasyUI.setRows(workInfos);
            return pager4EasyUI;
        }  else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("recordAccsByPager")
    public Pager4EasyUI recordAccsByPager(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize,@RequestParam("recordId")String recordId, HttpSession session){
        // TODO 所有登录公司员工
        if(SessionUtil.isLogin(session)) {
            User user = (User)session.getAttribute("user");
            Pager pager = new Pager();
            pager.setPageNo(pageNumber);
            pager.setPageSize(pageSize);
            int total = materialListService.countRecordAccs(recordId, user);
            Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
            pager4EasyUI.setTotal(total);
            List list = materialListService.recordAccsByPager(recordId, user, pager);
            setFlowingVars(list);
            pager4EasyUI.setRows(list);
            return pager4EasyUI;
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    private void setFlowingVars(List<MaterialList> list) {
        for(MaterialList mater: list){
            String recordId = mater.getRecord().getRecordId();
            String accId = mater.getAccId();
            MaterialURTemp temp = materialListService.queryFlowVarsByRecordId(recordId, accId);
            Map map = new HashMap();
            map.put("materialURTemp", temp);
            mater.setOther(map);
        }
    }


// baoliu
    @ResponseBody
    @RequestMapping("doReview")
    public ControllerResult doReview(MaterialURTemp materialUse, HttpServletRequest req, HttpSession session){ // 审核退料与领料申请
        // TODO 库管
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司库管人员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User) session.getAttribute("user");
                final String reviewTaskKey = "usertask2";
                TaskQuery taskQuery = taskService.createTaskQuery();

                String proInsId = materialUse.getProcessInstanceId();
                Task task = taskQuery.processInstanceId(proInsId).singleResult();

                if(task!= null && task.getTaskDefinitionKey().equals(reviewTaskKey)) {
                    boolean isOK = Boolean.parseBoolean(req.getParameter("isOK"));
                    String respMsg = materialUse.getRespMsg();

                    Map map = new HashMap();
                    map.put("isOK", isOK);
                    map.put("respMsg", respMsg);
                    String resultPre = "拒绝";
                    if (isOK) {
                        resultPre = "同意";
                    }
                    taskService.setAssignee(task.getId(), user.getUserId());
                    return nextTask(proInsId, task.getId(), map, isOK);
                } else {
                    return ControllerResult.getFailResult("审批失败,该申请已被用户删除");
                }

            } else {
                logger.info("此用户无拥有审核领/退料的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    private ControllerResult nextTask(String proInsId, String taskId, Map map, Boolean isOK) {
        HistoricProcessInstance hisProInst = historyService.createHistoricProcessInstanceQuery().processInstanceId(proInsId).singleResult();
        Map varMap = taskService.getVariables(taskId);
        String recordId = varMap.get("recordId").toString();
        String accId = varMap.get("accId").toString();
        int accCount = Integer.parseInt(varMap.get("accCount").toString());
        String startUserId = hisProInst.getStartUserId();
        try {
            taskService.complete(taskId, map);
            if(isOK) {
                if(addMaterialUseAReturnTable(varMap,startUserId)) {
                    return ControllerResult.getSuccessResult("审核完成");
                }
                return ControllerResult.getFailResult("审核失败");
            } else {
                return ControllerResult.getSuccessResult("审核完成");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return ControllerResult.getFailResult("审核失败");
    }


    /**
     * 原本用于上方,需要用到,这是领料申请成功后所做的事
     * @return
     */
    private boolean addMaterialUseAReturnTable(Map varMap, String startUserId ) {
        String recordId = varMap.get("recordId").toString();
        String accId = varMap.get("accId").toString();
        int accCount = Integer.parseInt(varMap.get("accCount").toString());
        int resultCount = 0;
        MaterialUse materialUse = new MaterialUse();
        materialUse.setMaterialUseId(UUIDUtil.uuid());
        // TODO  零件库存需要更新
        if(accCount>0) {
            materialUse.setMatainRecordId(recordId);
            materialUse.setAccId(accId);
            materialUse.setAccCount(Math.abs(accCount));
            materialUse.setMuCreatedTime(new Date());
            materialUse.setMuUseDate(new Date());
            resultCount  = materialUseService.insert(materialUse);
            if(resultCount > 0) {
                accessoriesService.reduceCount(Math.abs(accCount), accId);
            }
        }else {
            MaterialReturn materialReturn = new MaterialReturn();
            materialReturn.setMaterialReturnId(materialUse.getMaterialUseId());
            materialReturn.setAccCount(Math.abs(accCount));
            materialReturn.setAccId(accId);
            materialReturn.setMatainRecordId(recordId);
            materialReturn.setMrCreatedDate(new Date());
            materialReturn.setMrReturnDate(new Date());
            resultCount  = materialReturnService.insert(materialReturn);
            if(resultCount > 0) {
                accessoriesService.reduceCount(-Math.abs(accCount), accId);
            }
        }
        if(resultCount>0) {
            return true;
        }
        return  false;
    }


// baoliu
    @ResponseBody
    @RequestMapping("insert")
    public ControllerResult insertMaterials(MaterialList materialList, HttpSession session){
        // TODO 未知, 可能学徒都可以
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                int resultCount = materialListService.insert(materialList);
                return isSuc(resultCount, "添加成功", "添加失败");
            }  else {
                logger.info("此用户无拥有追加物料的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    private ControllerResult isSuc(int resultCount,String sucmsg,String ermsg) {
        if(resultCount>0) {
            return ControllerResult.getSuccessResult(sucmsg);
        }
        return ControllerResult.getFailResult(ermsg);
    }


    /**
     * 查询全部的配件信息
     */
    @ResponseBody
    @RequestMapping(value = "queryAllAccInv",method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllAccInv(HttpSession session){
        if(SessionUtil.isLogin(session)){
            User user = (User) session.getAttribute("user");
            String roles="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司技师,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)){
                logger.info("查询所有配件信息");
                List<Accessories> accessories = materialUseService.accQueryAll(user);
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for(Accessories c : accessories){
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(c.getAccId());
                    comboBox4EasyUI.setText(c.getAccName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        }else{
            logger.info("请先登陆");
            return null;
        }
    }

    /**
     * 库存领料统计
     * @param session
     * @param start
     * @param end
     * @param type
     * @param companyId
     * @param accTypeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByCondition")
    public List<MaterialUse> queryByCondition(HttpSession session,String start, String end, String type, String companyId, String accTypeId) {
        if(SessionUtil.isLogin(session)){
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)){
                List<MaterialUse> list = null;
                User user = (User) session.getAttribute("user");
                if (type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        if (companyId != null) {
                            list = materialUseService.queryByCondition(start, end, companyId, accTypeId,"year");
                        } else {
                            list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"year");
                        }
                    } else if (type.equals("quarter")) {
                        if (companyId != null) {
                            list = materialUseService.queryByCondition(start, end, companyId, accTypeId,"quarter");
                        } else {
                            list = materialUseService.queryByCondition(start, end, user.getCompanyId(),accTypeId, "quarter");
                        }
                    } else if (type.equals("month")) {
                        if (companyId != null) {
                            list = materialUseService.queryByCondition(start, end, companyId, accTypeId,"month");
                        } else {
                            list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"month");
                        }
                    } else if (type.equals("week")) {
                        if (companyId != null) {
                            list = materialUseService.queryByCondition(start, end, companyId, accTypeId,"week");
                        } else {
                            list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"week");
                        }
                    } else if (type.equals("day")) {
                        if (companyId != null) {
                            list = materialUseService.queryByCondition(start, end, companyId, accTypeId,"day");
                        } else {
                            list = materialUseService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"day");
                        }
                    }
                }
                return list;
            }else{
                logger.info("此用户无法拥有库存统计查询角色");
                return null;
            }
        }else{
            logger.info("请先登陆");
            return null;
        }
    }

    /**
     * 库存退料统计
     * @param session
     * @param start
     * @param end
     * @param type
     * @param companyId
     * @param accTypeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByResultCondition")
    public List<MaterialReturn> queryByResultCondition(HttpSession session,String start, String end, String type, String companyId, String accTypeId) {
        if(SessionUtil.isLogin(session)){
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)){
                List<MaterialReturn> list = null;
                User user = (User) session.getAttribute("user");
                if (type != null && !type.equals("")) {
                    if (type.equals("year")) {
                        if (companyId != null) {
                            list = materialReturnService.queryByCondition(start, end, companyId, accTypeId,"year");
                        } else {
                            list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"year");
                        }
                    } else if (type.equals("quarter")) {
                        if (companyId != null) {
                            list = materialReturnService.queryByCondition(start, end, companyId, accTypeId,"quarter");
                        } else {
                            list = materialReturnService.queryByCondition(start, end, user.getCompanyId(),accTypeId, "quarter");
                        }
                    } else if (type.equals("month")) {
                        if (companyId != null) {
                            list = materialReturnService.queryByCondition(start, end, companyId, accTypeId,"month");
                        } else {
                            list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"month");
                        }
                    } else if (type.equals("week")) {
                        if (companyId != null) {
                            list = materialReturnService.queryByCondition(start, end, companyId, accTypeId,"week");
                        } else {
                            list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"week");
                        }
                    } else if (type.equals("day")) {
                        if (companyId != null) {
                            list = materialReturnService.queryByCondition(start, end, companyId, accTypeId,"day");
                        } else {
                            list = materialReturnService.queryByCondition(start, end, user.getCompanyId(), accTypeId,"day");
                        }
                    }
                }
                return list;
            }else{
                logger.info("此用户无法拥有库存统计查询角色");
                return null;
            }
        }else{
            logger.info("请先登陆");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "nearCompanys")
    public List<Company> nearCompanys( HttpServletRequest req ) {
        Map pointsMap = new HashMap();
        double maxLng = str2dou(req.getParameter("maxLng"));
        double maxLat = str2dou(req.getParameter("maxLat"));
        double minLng = str2dou(req.getParameter("minLng"));
        double minLat = str2dou(req.getParameter("minLat"));
        pointsMap.put("maxLng", maxLng);
        pointsMap.put("maxLat", maxLat);
        pointsMap.put("minLng", minLng);
        pointsMap.put("minLat", minLat);
        return materialUseService.queryNearCompanys(pointsMap);
    }
    private double str2dou(String str) {
        try {
            return Double.parseDouble(str);
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


    /**
     * Workinfo 部分
     */


}
