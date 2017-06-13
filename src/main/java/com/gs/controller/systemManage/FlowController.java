package com.gs.controller.systemManage;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.bean.*;
import com.gs.bean.view.MaterialURTemp;
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
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

/**
 * Created by Administrator on 2017-05-05.
 */
@Controller
@RequestMapping("/flow")
public class FlowController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SystemManageController.class);

    @Resource
    private MaterialListService materialListService;

    @Resource
    private MaterialUseService materialUseService;

    @Resource
    private MaterialReturnService materialReturnService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private RoleService roleService;

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

    @ResponseBody
    @RequestMapping("queryAllFile")
    public Pager4EasyUI queryAllProcessFile(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize,HttpSession session) {
        if(SessionUtil.isLogin(session)) {
        Pager pager = new Pager();
        pager.setPageNo(pageNo);
        pager.setPageSize(pageSize);
        return getAllBpmnFileMsg(pager,session);
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    private Pager4EasyUI getAllBpmnFileMsg( Pager pager, HttpSession session) {
        String rootPath = session.getServletContext().getRealPath("/");
        rootPath += "/WEB-INF/classes/com/gs/bpmn";
        File dir = new File(rootPath);
        File[] files = null;
        System.out.println(dir.getAbsolutePath());
        if(dir.isDirectory()){
            files = dir.listFiles();
        } else {
            System.out.println("dir is not a directory");
        }
        SAXReader reader = new SAXReader();
        Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
        List<Map> rows = new ArrayList<Map>();
        for (int i = pager.getBeginIndex(),len = files.length; i<len; i++) {
            File file = files[i];
            String fileName = file.getName();
            String endTag = fileName.substring(fileName.indexOf(".")+1);
            if(endTag.equals("bpmn") || endTag.equals("xml")) {
                try {
                    Document doc = reader.read(file);
                    Element proEl= doc.getRootElement().element("process");
                    String flowKey = proEl.attribute("id").getValue();
                    String flowName = proEl.attribute("name").getValue();
                    Map row = new HashMap();
                    row.put("fileName", fileName);
                    row.put("flowKey", flowKey);
                    row.put("flowName", flowName);
                    row.put("lastModified", file.lastModified());
                    appendProDef(flowKey, row);
                    rows.add(row);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        pager4EasyUI.setRows(rows);
        pager4EasyUI.setTotal(files.length);
        return pager4EasyUI;
    }
    private void appendProDef(String flowKey, Map row ) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().latestVersion().processDefinitionKey(flowKey).singleResult();
        if(processDefinition != null ) {
            Deployment deploy = repositoryService.createDeploymentQuery().deploymentId(processDefinition .getDeploymentId()).singleResult();
            row.put("deployDatetime", deploy.getDeploymentTime());
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("deployFile")
    public ControllerResult deployFile(@RequestParam("fileName")String fileName, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                try {
                    DeploymentBuilder depBuil = repositoryService.createDeployment();
                    depBuil.addClasspathResource("com/gs/bpmn/"+ fileName);
                    depBuil.deploy();
                } catch(Exception e) {
                    e.printStackTrace();
                    return ControllerResult.getFailResult("部署失败,请刷新页面");
                }
                return ControllerResult.getSuccessResult("部署成功");

            } else {
                logger.info("此用户无拥有布署流程文件的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

// baoliu
    @ResponseBody
    @RequestMapping("removeProcessDeploy")
    public ControllerResult removeProcessDeploy(@RequestParam("flowKey")String flowKey, HttpSession session) {
        /**
         *  1. 得到该模型所有正在运行的流程数量,如果有,则不能禁用
         *  2. 如果没有,则禁用
         */
        // 第一步
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                int runProInstdefTaskLen = runtimeService.createExecutionQuery().processDefinitionKey(flowKey).list().size();
                if (runProInstdefTaskLen <= 0) {
                    List<Deployment> deps = repositoryService.createDeploymentQuery().processDefinitionKey(flowKey).list();
                    for (Deployment dep : deps) {
                        try {
                            repositoryService.deleteDeployment(dep.getId());
                        } catch (Exception e) {
                            e.printStackTrace();
                            return ControllerResult.getFailResult("禁用失败");
                        }
                    }
                } else {
                    return ControllerResult.getFailResult("禁用失败,目前还有" + runProInstdefTaskLen + "个流程实例正在运行中");
                }
                return ControllerResult.getSuccessResult("禁用成功");
            } else {
                logger.info("此用户无拥有禁用流程模版的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    /* 用于其它的,但与流程有关的 */
    @ResponseBody
    @RequestMapping("queryAcquisition")
    public Pager4EasyUI queryAcquisitionByPager(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize, HttpSession session) {
        // TODO 库管的页面, 应该是查询申请领取物料的页面
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员,汽车公司库管人员";
            if(RoleUtil.checkRoles(roles)){
                    Pager pager = new Pager();
                    pager.setPageNo(pageNo);
                    pager.setPageSize(pageSize);
                    User user = (User) session.getAttribute("user");
                    final String companyId = user.getCompanyId(); //假设当前登录角色为公司11的库管
                    final String curActId = "usertask2";
                    final String userId = user.getUserId();      //当前用户为2;
                    final String materialFlowKey = "material";

                    int total = materialUseService.countUseFlowing(materialFlowKey, user, curActId);
                    List<MaterialURTemp> prox = materialUseService.queryUseFlowingbyPager(materialFlowKey, user, curActId,pager);
                    setMaterProVars2Map(prox);
                    Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                    pager4EasyUI.setTotal(total);
                    pager4EasyUI.setRows(prox);
                    return pager4EasyUI;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("queryReturned")
    public Pager4EasyUI queryReturnedByPager(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize, HttpSession session) {
        // TODO 库管的页面, 应该是查询申请退料的页面
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员,汽车公司库管人员";
            if(RoleUtil.checkRoles(roles)){
                Pager pager = new Pager();
                User user = (User) session.getAttribute("user");
                pager.setPageNo(pageNo);
                pager.setPageSize(pageSize);
                final String userId = user.getUserId();      //当前用户为2;
                final String companyId = user.getCompanyId(); //假设当前登录角色为公司11的库管
                final String curActId = "usertask2";
                final String materialFlowKey = "material";
                int total = materialUseService.countReturnFlowing(materialFlowKey, user, curActId);
                List<MaterialURTemp> prox = materialUseService.queryReturnFlowingbyPager(materialFlowKey, user, curActId,pager);
                setMaterProVars2Map(prox);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setTotal(total);
                pager4EasyUI.setRows(prox);
                return pager4EasyUI;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("queryAccManagerHistory")
    public Pager4EasyUI queryAccManagerHistoryByPager(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize, HttpSession session) {
        // TODO 库管的页面,查询完成流程的物料申请页面
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员,汽车公司库管人员";
            if(RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                User user = (User) session.getAttribute("user");
                pager.setPageNo(pageNo);
                pager.setPageSize(pageSize);
                final String userId = user.getUserId();      //当前用户为2;
                final String companyId = user.getCompanyId(); //假设当前登录角色为公司11的库管
                final String materialFlowKey = "material";
                final String taskKey = "usertask2";
                int total = materialUseService.countHistoryFlowing(user, materialFlowKey);
                List<MaterialURTemp> prox = materialUseService.queryHistoryFlowingbyPager(user, materialFlowKey, taskKey, pager);
                setHisMaterProVars2Map(prox);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setTotal(total);
                pager4EasyUI.setRows(prox);
                return pager4EasyUI;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("queryUserFlowing")
    public Pager4EasyUI queryUserFlowingHistoryByPager(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize, HttpSession session) {
        // TODO 车修工自身的物料流程, 应该是进行中的
        if(SessionUtil.isLogin(session)) {
            String roles="公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User) session.getAttribute("user");
                Pager pager = new Pager();
                pager.setPageNo(pageNo);
                pager.setPageSize(pageSize);
                final String userId = user.getUserId();      //当前用户为1;
                final String materialFlowKey = "material";
                final String reveiwTaskKey = "usertask2";
                int total = materialUseService.countUserFlowing(materialFlowKey,user);
                List<MaterialURTemp> prox = materialUseService.queryUserFlowingByPager(materialFlowKey, user, reveiwTaskKey, pager);
                setHisMaterProVars2Map(prox);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setTotal(total);
                pager4EasyUI.setRows(prox);
                return pager4EasyUI;
            }else{
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    private void setHisMaterProVars2Map(List<MaterialURTemp> prox) {
        HistoricVariableInstanceQuery query = historyService.createHistoricVariableInstanceQuery();
        for(MaterialURTemp temp : prox) {
            List<HistoricVariableInstance> vars = query.processInstanceId(temp.getProcessInstanceId()).list();
            Map map = new HashMap();
            /*Map map1 = new HashMap();
            HistoricProcessInstanceEntity processInstance = temp.getProcessInstance();
            List<HistoricVariableInstanceEntity> vars = processInstance.getQueryVariables();
            */
            for(HistoricVariableInstance var : vars) {
                HistoricVariableInstanceEntity vartemp = (HistoricVariableInstanceEntity)var;
                map.put(var.getVariableName(), ((HistoricVariableInstanceEntity) var).getTextValue());
            }
            temp.setVarsMap(map);
            putMapAcc(map);
            // processInstance.setQueryVariables(null);
        }
    }

    private void setMaterProVars2Map(List<MaterialURTemp> prox) {
        for(MaterialURTemp temp : prox) {
            String taskId = temp.getTaskId();
            Map map = taskService.getVariables(taskId);
            /*Map map1 = new HashMap();
            HistoricProcessInstanceEntity processInstance = temp.getProcessInstance();
            List<HistoricVariableInstanceEntity> vars = processInstance.getQueryVariables();
            for(HistoricVariableInstanceEntity var : vars) {
                VariableInstanceTemp vt = (VariableInstanceTemp)var;
                map.put(var.getName(), var.getValue());
            }*/
            temp.setVarsMap(map);
            putMapAcc(map);
            // processInstance.setQueryVariables(null);
        }
    }
    private void putMapAcc(Map varMap) {
        String accId  = (String)varMap.get("accId");
        Accessories acc = materialUseService.accQueryById(accId);
        varMap.put("acc", acc);
    }

    @ResponseBody
    @RequestMapping("subForm")
    public ControllerResult subForm(MaterialURTemp materialUse, HttpSession session){
        /**
         * 流程示意: begin --> form --formSub? Y -->  审核 -isOK? Y-> END
         *                             |                    |
         *                             N                    N
         *                             ˇ                    ˇ
         *                             END                  form
         *
         *  伪代码:
         *      1. 点击提交后, 流程发起人在start里设置
         *           得到流程实例,再得到流程实例ID,由ID得到当前的流程分支. 由于流程分支只有一条,所以之后无论是用流程ID或分支ID都可以
         *      2. 由流程ID得到最下一个任务,也就是表单任务, 这里把用户从前台传入的参数变为这里的参数, 还要附加上formSub参数,下面判断要用到
         *              后面有个判断是否提交表单的, 这里设为true, 只要是这种判断都使用布尔
         *      3. 设置后面可做审核任务的候选组,默认角色id"1",这里假设角色1是库管
         *    以上把用户第一次提交表单所要做的操作完成了
         *
         *    有默认值, 默认这个流程的键是 "material",
         *    要完成审核的候选组ID为 "1" 这里假设1为库管人员的角色ID
         *
         */
        // TODO 员工们领料的页面,有公司的可以登录
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User) session.getAttribute("user");
                Role managerRole = roleService.queryByName("汽车公司库管人员");
                final String startUserId = user.getUserId();
                final String startUserName = user.getUserName();
                final String materialFlowKey = "material";
                final String canGroupId = managerRole.getRoleId(); // 审核的员工的id
                int accCount = materialUse.getAccCount();
                Map msgs = new HashMap();
                msgs.put("startName", startUserName);
                msgs.put("accId", materialUse.getAccId());
                msgs.put("accName", materialUse.getAccessories().getAccName());
                msgs.put("accCount", materialUse.getAccCount());
                msgs.put("recordId", materialUse.getMatainRecordId());
                msgs.put("reqMsg", materialUse.getReqMsg());
                msgs.put("formSub", true);
                try {
                    // 第一步
                    Authentication.setAuthenticatedUserId(startUserId);
                    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(materialFlowKey);
                    // 第二步
                    Task formTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
                    taskService.complete(formTask.getId(), msgs);
                    // 第三步
                    Task nextTask = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
                    taskService.addCandidateGroup(nextTask.getId(),canGroupId);
                } catch (Exception e) {
                    e.printStackTrace();
                    return ControllerResult.getFailResult("申请失败,可能是因为领取/退回物料流程未部署,请联系管理员");
                }
                return ControllerResult.getSuccessResult("申请成功");
            } else {
                logger.info("此用户无拥有领料的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("reSubForm")
    public ControllerResult reSubForm(MaterialURTemp materialUse, HttpSession session){
        /**
         * 1. 修改reqMsg变量    v
         * 2. respMsg变量删除   v
         * 3. 设置下一个任务的候选组
         */
        // TODO 重新提交申请表单, 公司员工可以登录
        User user = (User) session.getAttribute("user");
        Role managerRole = roleService.queryByName("汽车公司库管人员");
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if(RoleUtil.checkRoles(roles)) {
                final String startUserId = user.getUserId();
                final String materialFlowKey = "material";
                final String subFormTaskKey = "usertask1";
                final String canGroupId = managerRole.getRoleId(); // 库管的ID

                int accCount = materialUse.getAccCount();
                Map msgs = new HashMap();
                // 第一步
                msgs.put("reqMsg", materialUse.getReqMsg());
                msgs.put("formSub", true);
                TaskQuery taskQuery = taskService.createTaskQuery();
                try {
                    String processInstanceId = materialUse.getProcessInstanceId();
                    // 第二步
                    Task task = taskQuery.processInstanceId(processInstanceId).singleResult();
                    if(task.getTaskDefinitionKey().equals(subFormTaskKey)) {
                        taskService.removeVariable(task.getId(), "respMsg");
                        taskService.complete(task.getId(), msgs);
                        // 第三步
                        Task nextTask = taskQuery.processInstanceId(processInstanceId).singleResult();
                        taskService.addCandidateGroup(nextTask.getId(), canGroupId);
                    } else {
                        return ControllerResult.getFailResult("慢了一步,该申请已经被审核完了");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ControllerResult.getFailResult("申请失败,可能是因为领取/退回物料流程未部署,请联系管理员");
                }
                return ControllerResult.getSuccessResult("申请成功");
            } else {
                logger.info("此用户无拥有重新提交表单的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("removeMaterialProInst")
    public ControllerResult removeMaterialProInst(@RequestParam("processInstanceId") String proInstId, HttpSession session) {
        // TODO 员工自己删除流程实例
        if(SessionUtil.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            final String curUserId = user.getUserId();
            final String subFormTaskName = "usertask1";
            final String reviewTaskName = "usertask2";
            HistoricProcessInstance hisproIns = historyService.createHistoricProcessInstanceQuery().processInstanceId(proInstId).singleResult();
            if(curUserId.equals(hisproIns.getStartUserId())) {
                try {
                    Task task = taskService.createTaskQuery().processInstanceId(proInstId).singleResult();
                    if(task != null) {
                        String curTaskName = task.getTaskDefinitionKey();
                        Map dontSubForm = new HashMap();
                        if(curTaskName.equals(subFormTaskName)){ dontSubForm.put("formSub", false); }
                        else if(curTaskName.equals(reviewTaskName)){ dontSubForm.put("isOK",true ); }
                        taskService.complete(task.getId(), dontSubForm);
                        return ControllerResult.getSuccessResult("删除申请成功");
                    } else {
                        return ControllerResult.getFailResult("删除失败,该申请已经被审核了");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ControllerResult.getFailResult("删除申请失败");
                }
            } else {
                return ControllerResult.getFailResult("您不是流程发起人,删除申请失败");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    private ControllerResult junpei(MaterialUse materialUse) {
        int accCount = materialUse.getAccCount();
        int resultCount = 0;
        materialUse.setMaterialUseId(UUIDUtil.uuid());
        if(accCount>0) {
            materialUse.setMuCreatedTime(new Date());
            materialUse.setMuUseDate(new Date());
            resultCount  = materialUseService.insert(materialUse);
        }else {
            materialUse.setAccCount(-accCount);
            MaterialReturn materialReturn = new MaterialReturn();
            materialReturn.setMaterialReturnId(materialUse.getMaterialUseId());
            materialReturn.setAccCount(materialUse.getAccCount());
            materialReturn.setAccId(materialUse.getAccId());
            materialReturn.setMatainRecordId(materialUse.getMatainRecordId());
            materialReturn.setMrCreatedDate(materialUse.getMuCreatedTime());
            materialReturn.setMrReturnDate(materialUse.getMuUseDate());
            resultCount  = materialReturnService.insert(materialReturn);
        }
        return isSuc(resultCount,"申请成功","申请失败");
    }

    private ControllerResult isSuc(int resultCount,String sucmsg,String ermsg) {
        if(resultCount>0) {
            return ControllerResult.getSuccessResult(sucmsg);
        }
        return ControllerResult.getFailResult(ermsg);
    }





}
