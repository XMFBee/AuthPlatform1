package com.gs.controller.supplyManage;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.SupplyTypeService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 钟燕玲 供应商类型管理
 * Created by Administrator on 2017/4/18.
 */
@Controller
@RequestMapping("/supplyType")
public class SupplyTypeController {


    @Resource
    private SupplyTypeService supplyTypeService;
    private Logger logger = (Logger) LoggerFactory.getLogger(SupplyTypeController.class);


    /*
        查询全部供应商类型
     */
    @ResponseBody
    @RequestMapping(value = "queryAllSupplyType",method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(HttpSession session){
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("查询全部供应商类型");
                List<SupplyType> supplyTypes = supplyTypeService.queryAll((User)session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for(SupplyType st : supplyTypes){
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(st.getSupplyTypeId());
                    comboBox4EasyUI.setText(st.getSupplyTypeName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            }else{
                logger.info("此用户无拥有查询全部供应商类型记录角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }


    /**
     * 分页查询所有的供应商类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPager",method = RequestMethod.GET)
    public Pager4EasyUI<SupplyType> queryByPager(HttpSession session,@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("供应商类型分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(supplyTypeService.count((User)session.getAttribute("user")));
                List<SupplyType> supplyTypeList = supplyTypeService.queryByPager(pager);
                return new Pager4EasyUI<SupplyType>(pager.getTotalRecords(), supplyTypeList);
            }else{
                logger.info("此用户无拥有供应商类型记录分页查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 验证供应商类型名称是否存在
     */
     @ResponseBody
     @RequestMapping(value = "queryNameByOne")
     public Map queryNameByOne(SupplyType supplyType, HttpSession session) {
     int countName = supplyTypeService.queryNameByOne(supplyType.getSupplyTypeName(), supplyType.getSupplyTypeId());
     Map<String, Boolean> map = new HashMap<String, Boolean>();
     if(countName > 0)
     map.put("valid", false);
     else
     map.put("valid", true);

     return map;
     }

   /* @ResponseBody
    @RequestMapping(value = "queryNameByOne", method = RequestMethod.GET)
    public String queryNameByOne(HttpServletRequest req) {
        logger.info("验证供应商类型名称是否已经存在");
        String supplyTypeName = (String)req.getParameter("supplyTypeName");
        boolean result = true;
        String resultString = "";
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        ObjectMapper mapper = new ObjectMapper();
        if (supplyTypeName != null && supplyTypeName !="") {
            int count = supplyTypeService.queryNameByOne(supplyTypeName);
            if (count > 1 || count == 1) {
                result = false;
            }
        }
        map.put("valid", result);
        try {
            resultString = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultString;
    }*/


    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable",method = RequestMethod.GET)
    public Pager4EasyUI<SupplyType> queryByPagerDisable(HttpSession session,@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                logger.info("供应商类型禁用分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(supplyTypeService.countByDisable((User)session.getAttribute("user")));
                List<SupplyType> supplyTypeList = supplyTypeService.queryByPagerDisable(pager);
                return new Pager4EasyUI<SupplyType>(pager.getTotalRecords(), supplyTypeList);
            }else{
                logger.info("此用户无拥有供应商类型记录禁用分页查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }

    }

    /**
     * 添加供应商类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addSupplyType", method = RequestMethod.POST)
    public ControllerResult addSupplyType(HttpSession session,SupplyType supplyType) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                logger.info("添加供应商类型");
                if (supplyType != null && !supplyType.equals("")) {
                    System.out.println(supplyType.toString());
                    supplyType.setCompanyId(user.getCompanyId());
                    supplyTypeService.insert(supplyType);
                    logger.info("添加供应商类型成功");
                    return ControllerResult.getSuccessResult("添加供应商类型成功");
                } else {
                    return ControllerResult.getFailResult("添加供应商类型失败");
                }
            }else{
                logger.info("此用户无拥有添加供应商类型角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }


    /**
     * 修改供应商类型信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateSupplyType", method = RequestMethod.POST)
    public ControllerResult updateSupplyType(HttpSession session,SupplyType supplyType) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                if (supplyType != null && !supplyType.equals("")) {
                    User user = (User)session.getAttribute("user");
                    supplyType.setCompanyId(user.getCompanyId());
                    supplyTypeService.update(supplyType);
                    logger.info("修改供应商类型成功");
                    return ControllerResult.getSuccessResult("修改供应商类型成功");
                } else {
                    return ControllerResult.getFailResult("修改供应商类型失败");
                }
            }else{
                logger.info("此用户无拥有修改供应商类型角色");
                return null;
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
    @RequestMapping(value = "statusOperate",method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session,String id,String status){
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if(RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        supplyTypeService.active(id);
                        logger.info("激活供应商类型成功");
                        return ControllerResult.getSuccessResult("激活供应商类型成功");
                    } else {
                        supplyTypeService.inactive(id);
                        logger.info("禁用供应商类型成功");
                        return ControllerResult.getSuccessResult("禁用供应商类型成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else{
                logger.info("此用户无拥有对供应商类型状态做激活与禁用的角色");
                return null;
            }

        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

}
