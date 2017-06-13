package com.gs.controller.supplyManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.Supply;
import com.gs.bean.SupplyType;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.SupplyService;
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
 * 钟燕玲 供应商管理
 *
 * Created by Administrator on 2017/4/18.
 */
@Controller
@RequestMapping("/supply")
public class SupplyController {


    @Resource
    private SupplyService supplyService;

    private Logger logger = (Logger) LoggerFactory.getLogger(SupplyController.class);

   /*
    查询全部的供应商信息
    */
    @ResponseBody
    @RequestMapping(value = "queryAllSupply",method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllSupply(HttpSession session){
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("查询所有的供应商信息");
                List<Supply> supplys = supplyService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (Supply s : supplys) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(s.getSupplyId());
                    comboBox4EasyUI.setText(s.getSupplyName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            } else {
                logger.info("此用户无拥有查询所有供应商的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }


    /**
     * 分页查询所有的供应商
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPager",method = RequestMethod.GET)
    public Pager4EasyUI<Supply> queryByPager(HttpSession session, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("供应商分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(supplyService.count((User)session.getAttribute("user")));
                List<Supply> supplyList = supplyService.queryByPager(pager);
                return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplyList);
            } else {
                logger.info("此用户无拥有供应商分页查询角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 分页查询禁用的供应商
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable",method = RequestMethod.GET)
    public Pager4EasyUI<Supply> queryByPagerDisable(HttpSession session,@Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("供应商禁用分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(supplyService.countByDisable((User)session.getAttribute("user")));
                List<Supply> supplyList = supplyService.queryByPagerDisable(pager);
                return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplyList);
            }else {
                logger.info("此用户无拥有供应商禁用分页查询角色");
                return null;
            }

        }else{
            logger.info("请先登录");
            return null;
        }
    }

    /**
     * 添加供应商
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addSupply", method = RequestMethod.POST)
    public ControllerResult addSupply(HttpSession session,HttpServletRequest request,Supply supply) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                User user = (User)session.getAttribute("user");
                logger.info("添加供应商记录");
                if (supply != null && !supply.equals("")) {
                    supply.setCompanyId(user.getCompanyId());
                    supplyService.insert(supply);
                    logger.info("添加供应商成功");
                    return ControllerResult.getSuccessResult("添加供应商成功");
                } else {
                    return ControllerResult.getFailResult("添加供应商失败");
                }
            }else {
                logger.info("此用户无拥有添加供应商记录角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    /**
     * 验证供应商名称是否存在
     */
    @ResponseBody
    @RequestMapping(value = "queryNameByOne")
    public Map queryNameByOne(Supply supply, HttpSession session) {
        int countName = supplyService.queryNameByOne(supply.getSupplyName(), supply.getSupplyId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }


    /**
     * 修改供应商信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateSupply", method = RequestMethod.POST)
    public ControllerResult updateSupply(HttpSession session,Supply supply,HttpServletRequest request) {
        if(SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("修改供应商记录");
                if (supply != null && !supply.equals("")) {
                    User user = (User)session.getAttribute("user");
                    supply.setCompanyId(user.getCompanyId());
                    supplyService.update(supply);
                    logger.info("修改供应商成功");
                    return ControllerResult.getSuccessResult("修改供应商成功");
                } else {
                    return ControllerResult.getFailResult("修改供应商失败");
                }
            }else {
                logger.info("此用户无拥有修改供应商角色");
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
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        supplyService.active(id);
                        logger.info("激活供应商成功");
                        return ControllerResult.getSuccessResult("激活供应商成功");
                    } else {
                        supplyService.inactive(id);
                        logger.info("禁用供应商成功");
                        return ControllerResult.getSuccessResult("禁用供应商成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            }else {
                logger.info("此用户无拥有对供应商状态做激活与禁用的角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }

    }

    /**
     * 供应商记录模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<Supply> blurredQuery(HttpSession session,HttpServletRequest request, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("供应商模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if(text != null && text!="" && value != null && value != "") {
                    System.out.print(text);
                    List<Supply> supplys = null;
                    Supply supply = new Supply();
                    if(text.equals("供应商/供应商类型/所属公司")){
                        supply.setSupplyName(value);
                        supply.setSupplyTypeId(value);
                        supply.setCompanyId(value);
                    }if(text.equals("供应商/供应商类型")){
                        supply.setSupplyName(value);
                        supply.setSupplyTypeId(value);
                    }else if(text.equals("供应商")){
                        supply.setSupplyName(value);
                    }else if(text.equals("供应商类型")) {
                        supply.setSupplyTypeId(value);
                    }else if(text.equals("所属公司")){
                        supply.setCompanyId(value);
                    }
                    supplys = supplyService.blurredQuery(pager,supply);
                    pager.setTotalRecords(supplyService.countByBlurred(supply,(User)session.getAttribute("user")));
                    System.out.print(supplys);
                    return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
                }else{ // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                    pager.setTotalRecords(supplyService.count((User)session.getAttribute("user")));
                    List<Supply> supplys = supplyService.queryByPager(pager);
                    return new Pager4EasyUI<Supply>(pager.getTotalRecords(), supplys);
                }
            }else {
                logger.info("此用户无拥有供应商模糊查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

}
