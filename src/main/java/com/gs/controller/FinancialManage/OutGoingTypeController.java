package com.gs.controller.FinancialManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.PagerUtil;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.controller.FinancialViewController;
import com.gs.service.OutgoingTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by GZWangBin on 2017/4/18.
 */
@Controller
@RequestMapping("/outGoingType")
public class OutGoingTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(OutGoingTypeController.class);

    /**
     * 支出Service
     */
    @Resource
    public OutgoingTypeService outgoingTypeService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<OutgoingType> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("支出类型分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(outgoingTypeService.count((User) session.getAttribute("user")));
                List<OutgoingType> outgoingTypes = outgoingTypeService.queryByPager(pager);
                return new Pager4EasyUI<OutgoingType>(pager.getTotalRecords(), outgoingTypes);
            } else {
                logger.info("此用户无拥有支出类型分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<OutgoingType> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("禁用支出类型分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(outgoingTypeService.countByDisable((User) session.getAttribute("user")));
                List<OutgoingType> outgoingTypes = outgoingTypeService.queryByPagerDisable(pager);
                return new Pager4EasyUI<OutgoingType>(pager.getTotalRecords(), outgoingTypes);
            } else {
                logger.info("此用户无拥有禁用支出类型分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "statusOperate", method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session, String id, String status) {

        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        outgoingTypeService.active(id);
                        logger.info("激活成功");
                        return ControllerResult.getSuccessResult("激活支出类型成功");
                    } else {
                        outgoingTypeService.inactive(id);
                        logger.info("禁用成功");
                        return ControllerResult.getSuccessResult("禁用支出类型成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            } else {
                logger.info("此用户无拥有操作支出类型禁用启用的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult add(HttpSession session, OutgoingType outgoingType) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("添加支出类型");
                User user = (User) session.getAttribute("user");
                outgoingType.setCompanyId(user.getCompanyId());
                outgoingTypeService.insert(outgoingType);
                return ControllerResult.getSuccessResult("添加支出类型成功");
            } else {
                logger.info("此用户无拥有添加支出类型的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }




    /**
     * 验证支出类型名称
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "checkOutTypeName",method = RequestMethod.POST)
    public Map checkOutTypeName(OutgoingType outgoingType) {
        OutgoingType o = outgoingTypeService.queryById(outgoingType.getOutTypeName(), outgoingType.getOutTypeId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(o != null)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, OutgoingType outgoingType) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                System.out.printf(outgoingType.getOutTypeId() + "," + outgoingType.getOutTypeName());
                logger.info("修改支出类型");
                User user = (User) session.getAttribute("user");
                outgoingType.setCompanyId(user.getCompanyId());
                outgoingTypeService.update(outgoingType);
                return ControllerResult.getSuccessResult("修改支出类型成功");
            } else {
                logger.info("此用户无拥有修改支出类型的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 查询全部的配件分类
     */
    @ResponseBody
    @RequestMapping(value = "queryAllOutGoing", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllOutGoing(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查询所有支出类型");
                List<OutgoingType> outgoingTypeList = outgoingTypeService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (OutgoingType o : outgoingTypeList) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(o.getOutTypeId());
                    comboBox4EasyUI.setText(o.getOutTypeName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }
    }
}
