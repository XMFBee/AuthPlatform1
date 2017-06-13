package com.gs.controller.pickingManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.User;
import com.gs.bean.WorkInfo;
import com.gs.bean.view.DetailTemp;
import com.gs.bean.view.RecordBaseView;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.controller.SystemManageController;
import com.gs.service.MaterialReturnService;
import com.gs.service.MaterialUseService;
import com.gs.service.PermissionService;
import com.gs.service.WorkInfoService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * 派工领料
 *
 * @author 程燕
 * @create 2017-04-23 18:57
 * @des 派工Controller
 */
@Controller
@RequestMapping("/dispatching")
public class WorkingDispatchingController {
    private Logger logger = (Logger) LoggerFactory.getLogger(SystemManageController.class);

    @Resource
    private MaterialUseService materialUseService;

    @Resource
    private MaterialReturnService materialReturnService;

    @Resource
    private WorkInfoService workInfoService;

    // 保留
    @ResponseBody
    @RequestMapping("noDispRecordByPager")
    public Pager4EasyUI records(@RequestParam("pageNumber")String pageNo, @RequestParam("pageSize")String pageSize, HttpSession session){
        if(SessionUtil.isLogin(session)) {
            // TODO 需要公司人员登录才能指派员工
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User) session.getAttribute("user");
                Pager pager = new Pager();
                pager.setPageNo(str2int(pageNo));
                pager.setPageSize(str2int(pageSize));
                int total = materialUseService.countNoUseRecord(user.getCompanyId());
                List<RecordBaseView> rbvs = materialUseService.queryNoUseRecord(user.getCompanyId(), pager);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI(total, rbvs);
                return pager4EasyUI;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("dispRecordByPager")
    public Pager4EasyUI curRecords(@RequestParam("pageNumber")String pageNo, @RequestParam("pageSize")String pageSize, HttpSession session){
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                Pager pager = new Pager();
                pager.setPageNo(str2int(pageNo));
                pager.setPageSize(str2int(pageSize));
                int total = materialUseService.countHasUseRecord(user.getCompanyId());
                List<RecordBaseView> rbvs = materialUseService.queryHasUseRecord(user.getCompanyId(), pager);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI(total, rbvs);
                return pager4EasyUI;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping("queryRecrodAccByPager") // 可能不会使用到
    public Pager4EasyUI accInfos(@RequestParam("pageNumber")String pageNo, @RequestParam("pageSize")String pageSize, @RequestParam("recordId")String recordId, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(str2int(pageNo));
                pager.setPageSize(str2int(pageSize));
                return null;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("userWorksByPager") // 用于页面显示员工工单,员工通过这个页面会点击查询该工单所需要的零件
    public Pager4EasyUI userWorks(@RequestParam("pageNumber")String pageNo, @RequestParam("pageSize")String pageSize, HttpSession session) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                Pager pager = new Pager();
                pager.setPageNo(str2int(pageNo));
                pager.setPageSize(str2int(pageSize));
                List workInfos = materialUseService.userWorksStatusByPager(user, "N",pager);
                int total = materialUseService.countUserWorksStatus(user,"N");
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setRows(workInfos);
                pager4EasyUI.setTotal(total);
                return pager4EasyUI;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }


    // baoliu
    @ResponseBody
    @RequestMapping("recordDetails")
    public Pager4EasyUI recordDetails(@RequestParam("pageNumber")int pageNo, @RequestParam("pageSize")int pageSize,@RequestParam("recordId")String recordId, HttpSession session){
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,系统超级管理员,系统普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                String companyId = user.getCompanyId();
                Pager pager = new Pager();
                pager.setPageNo(pageNo);
                pager.setPageSize(pageSize);
                List<DetailTemp> details = materialUseService.queryDetailsByRecordId(recordId, companyId, pager);
                int total = materialUseService.countDetailsByRecordId(recordId, companyId);
                Pager4EasyUI pager4EasyUI = new Pager4EasyUI();
                pager4EasyUI.setTotal(total);
                pager4EasyUI.setRows(details);
                return  pager4EasyUI;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    // baoliu
    @ResponseBody
    @RequestMapping("emps") // 用到了
    public List<ComboBox4EasyUI> emps(HttpSession session) {
        // TODO 这里需要公司登录,管理员不行
        if(SessionUtil.isLogin(session)) {
            User user = (User)session.getAttribute("user");
            List<User> users = materialUseService.companyEmps(user.getCompanyId());
            List<ComboBox4EasyUI> comboBoxs = new ArrayList<ComboBox4EasyUI>();
            for(User userTemp: users) {
                ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                comboBox4EasyUI.setId(userTemp.getUserId());
                comboBox4EasyUI.setText(userTemp.getUserName());
                comboBoxs.add(comboBox4EasyUI);
            }
            return comboBoxs;
        } else {
            logger.info("请先登录");
            return null;
        }
    }



    //baoliu
    @ResponseBody
    @RequestMapping("insert")
    public ControllerResult insert(@RequestParam("recordId")String recordId, @RequestParam("userId")String userId, HttpSession session) {
        // TODO 公司管理员可以
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司总技师,汽车公司技师";
            if(RoleUtil.checkRoles(roles)) {
                int result = 0;
                String msg = "";
                if (materialUseService.recordIsDisp(recordId)) {
                    final String flowName = "material";
                    String oldUserId = materialUseService.queryUserIdbyRecordId4workInfo(recordId);
                    String newUserId = userId;
                    msg = "修改";
                    try {
                        materialUseService.updRunProInstStartUser(newUserId, recordId, flowName);
                        result = materialUseService.updWorkInfoUser(recordId, newUserId);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    WorkInfo workInfo = new WorkInfo();
                    workInfo.setRecordId(recordId);
                    workInfo.setUserId(userId);
                    workInfo.setWorkCreatedTime(new Date());
                    workInfo.setWorkAssignTime(new Date());
                    result = materialUseService.insertWorkInfo(workInfo);
                    msg = "添加";
                }
                if (result < 1) {
                    return ControllerResult.getFailResult(msg + "失败");
                }
                return ControllerResult.getSuccessResult(msg + "成功");
            } else {
                logger.info("此用户无拥有指派员工的角色");
                return ControllerResult.getNotRoleResult("权限不足");
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }



    private int str2int(String str){
        try{
            int num = Integer.valueOf(str);
            return num;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
