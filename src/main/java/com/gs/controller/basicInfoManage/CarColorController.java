package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.CarColor;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.CarColorService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
 * 汽车颜色管理
 * Created by yaoyong on 2017/4/18.
 */

@Controller
@RequestMapping("/carColor")
public class CarColorController {

    @Resource
    private CarColorService carColorService;

    private Logger logger = (Logger) LoggerFactory.getLogger(CarColorController.class);

    @ResponseBody
    @RequestMapping(value = "queryAllCarColor", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(HttpSession session) {
        /*if (SessionUtil.isLogin(session) || SessionUtil.isOwnerLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司接待员,车主";
            if (RoleUtil.checkRoles(roles)) {*/
                logger.info("查询所有汽车颜色");
                List<CarColor> carColors = carColorService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (CarColor c : carColors) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(c.getColorId());
                    comboBox4EasyUI.setText(c.getColorName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
            /*} else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }*/
    }

    @ResponseBody
    @RequestMapping(value = "queryByPagerCarColor", method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有汽车颜色");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(carColorService.count((User) session.getAttribute("user")));
                List<CarColor> carColors = carColorService.queryByPager(pager);
                return new Pager4EasyUI<CarColor>(pager.getTotalRecords(), carColors);
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
    @RequestMapping(value = "addCarColor")
    public ControllerResult add(HttpSession session, CarColor carColor) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (carColor != null && !carColor.equals("")) {
                    logger.info("添加汽车颜色");
                    carColorService.insert(carColor);
                    return ControllerResult.getSuccessResult("添加汽车颜色成功");
                } else {
                    return ControllerResult.getFailResult("添加汽车颜色失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }


    @ResponseBody
    @RequestMapping(value = "updateCarColor", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, CarColor carColor) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (carColor != null && !carColor.equals("")) {
                    logger.info("修改汽车颜色");
                    carColorService.update(carColor);
                    return ControllerResult.getSuccessResult("修改汽车颜色成功");
                } else {
                    return ControllerResult.getFailResult("修改汽车颜色失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }

    /**
     * 查询所有被禁用的汽车颜色
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<CarColor> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用汽车颜色");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(carColorService.countByDisable((User) session.getAttribute("user")));
                List<CarColor> carColors = carColorService.queryByPagerDisable(pager);
                return new Pager4EasyUI<CarColor>(pager.getTotalRecords(), carColors);
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }

        } else {
            logger.info("请先登录");
            return null;
        }

    }


    /**
     * 验证查询此品牌名称是否已存在此品牌名称
     */
    @ResponseBody
    @RequestMapping(value = "querycolorName", method = RequestMethod.POST)
    public Map querycolorName(CarColor carColor) {
        logger.info("此品牌名称是否已存在此品牌名称");
        int countcolorName = carColorService.querycolorName(carColor.getColorName(),carColor.getColorId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countcolorName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    /**
     * 对状态的激活和启用，只使用一个方法进行切换。
     */
    @ResponseBody
    @RequestMapping(value = "statusOperate", method = RequestMethod.POST)
    public ControllerResult inactive(HttpSession session, String id, String status) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (id != null && !id.equals("") && status != null && !status.equals("")) {
                    if (status.equals("N")) {
                        carColorService.active(id);
                        logger.info("激活汽车颜色成功");
                        return ControllerResult.getSuccessResult("激活汽车颜色成功");
                    } else {
                        carColorService.inactive(id);
                        logger.info("禁用汽车颜色成功");
                        return ControllerResult.getSuccessResult("禁用汽车颜色成功");
                    }
                } else {
                    return ControllerResult.getFailResult("操作失败");
                }
            } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return ControllerResult.getNotLoginResult("登录信息无效，请重新登录");
        }
    }
}
