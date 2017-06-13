package com.gs.controller.accessoriesManage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.AccessoriesType;
import com.gs.bean.MaintainFix;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.AccessoriesTypeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.activiti.engine.impl.Page;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ch.qos.logback.classic.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 王怡 配件分类管理
 * Created by Administrator on 2017/4/18.
 */
@Controller
@RequestMapping("/accType")
public class AccessoriesTypeController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesTypeController.class);

    @Resource
    private AccessoriesTypeService accessoriesTypeService;

    /**
     * 查询全部的配件分类
     */
    @ResponseBody
    @RequestMapping(value = "queryAllAccType", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllAccType(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查询所有配件分类信息");
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (AccessoriesType c : accessoriesTypes) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(c.getAccTypeId());
                    comboBox4EasyUI.setText(c.getAccTypeName());
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

    /**
     * 分页查询配件分类信息
     */
    @ResponseBody
    @RequestMapping(value = "queryByPage", method = RequestMethod.GET)
    public Pager4EasyUI queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(accessoriesTypeService.count((User) session.getAttribute("user")));
                logger.info("分页查询配件分类信息成功");
                List<AccessoriesType> accTypes = accessoriesTypeService.queryByPager(pager);
                return new Pager4EasyUI<AccessoriesType>(pager.getTotalRecords(), accTypes);
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }

    }


    /**
     * 添加配件分类
     *
     * @param accessoriesType
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAccType", method = RequestMethod.POST)
    public ControllerResult addAccType(HttpSession session, AccessoriesType accessoriesType) {
        if (SessionUtil.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (accessoriesType != null && !accessoriesType.equals("")) {
                    accessoriesType.setCompanyId(user.getCompanyId());
                    accessoriesTypeService.insert(accessoriesType);
                    logger.info("添加成功");
                    return ControllerResult.getSuccessResult("添加配件分类成功");
                } else {
                    return ControllerResult.getFailResult("添加配件分类失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }

    }

    /**
     * 更新配件分类信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateAccType", method = RequestMethod.POST)
    public ControllerResult updateAccType(HttpSession session, AccessoriesType accessoriesType) {
        if (SessionUtil.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (accessoriesType != null && !accessoriesType.equals("")) {
                    accessoriesType.setCompanyId(user.getCompanyId());
                    accessoriesTypeService.update(accessoriesType);
                    logger.info("修改配件分类成功");
                    return ControllerResult.getSuccessResult("修改配件分类成功");
                } else {
                    logger.info("修改配件分类失败");
                    return ControllerResult.getSuccessResult("修改配件分类失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
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
    public Pager4EasyUI<AccessoriesType> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用登记记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(accessoriesTypeService.countByDisable());
                List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryByPagerDisable(pager);
                return new Pager4EasyUI<AccessoriesType>(pager.getTotalRecords(), accessoriesTypes);
            } else {
                logger.info("此用户无拥有此方法角色");
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
    public ControllerResult inactive(HttpSession session, String accTypeId, String accTypeStatus) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (accTypeId != null && !accTypeId.equals("") && accTypeStatus != null && !accTypeStatus.equals("")) {
                    if (accTypeStatus.equals("N")) {
                        accessoriesTypeService.active(accTypeId);
                        logger.info("激活配件分类成功");
                        return ControllerResult.getSuccessResult("激活配件分类成功");
                    } else {
                        accessoriesTypeService.inactive(accTypeId);
                        logger.info("禁用配件分类成功");
                        return ControllerResult.getSuccessResult("禁用配件分类成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesType> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("配件分类模糊查询");
                Pager pager = new Pager();
                pager.setUser((User) session.getAttribute("user"));
                pager.setPageNo(Integer.parseInt(pageNumber));
                pager.setPageSize(Integer.parseInt(pageSize));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && !text.equals("") && value != null && !value.equals("")) {
                    List<AccessoriesType> accessoriesTypes = null;
                    AccessoriesType accType = new AccessoriesType();
                    if (text.equals("配件分类名称/汽车公司")) {
                        accType.setCompanyId(value);
                        accType.setAccTypeName(value);
                    } else if (text.equals("汽车公司")) {
                        accType.setCompanyId(value);
                    } else if (text.equals("配件分类名称")) {
                        accType.setAccTypeName(value);
                    }
                    accessoriesTypes = accessoriesTypeService.blurredQuery(pager, accType);
                    pager.setTotalRecords(accessoriesTypeService.countByBlurred(accType, (User) session.getAttribute("user")));
                    return new Pager4EasyUI<AccessoriesType>(pager.getTotalRecords(), accessoriesTypes);
                } else {
                    return null;
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登陆");
            return null;
        }
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @ResponseBody
    @RequestMapping(value = "queryTypeName/{companyId}", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(@PathVariable("companyId") String companyId) {
        logger.info("根据汽修公司， 汽修项目，查询项目名字");
        List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryTypeName(companyId);
        List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
        for (AccessoriesType a : accessoriesTypes) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(a.getAccTypeId());
            comboBox4EasyUI.setText(a.getAccTypeName());
            comboxs.add(comboBox4EasyUI);
        }
        return comboxs;
    }

    @ResponseBody
    @RequestMapping(value = "queryMyTypeName", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> mySelectMaintainName(HttpSession session) {
        logger.info("根据汽修公司， 汽修项目，查询项目名字");
        User user = (User) session.getAttribute("user");
        List<AccessoriesType> accessoriesTypes = accessoriesTypeService.queryTypeName(user.getCompanyId());
        List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
        for (AccessoriesType a : accessoriesTypes) {
            ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
            comboBox4EasyUI.setId(a.getAccTypeId());
            comboBox4EasyUI.setText(a.getAccTypeName());
            comboxs.add(comboBox4EasyUI);
        }
        return comboxs;
    }

    /**
     * 验证手机号是否存在
     */
    @ResponseBody
    @RequestMapping(value = "queryNameByOne", method = RequestMethod.POST)
    public Map queryNameByOne(AccessoriesType accessoriesType) {
        logger.info("此品牌名称是否已存在此品牌名称");
        int countbrandName = accessoriesTypeService.queryAccTypeNameOne(accessoriesType.getAccTypeName(),accessoriesType.getAccTypeId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countbrandName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }


}
