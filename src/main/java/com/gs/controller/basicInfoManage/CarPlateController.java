package com.gs.controller.basicInfoManage;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gs.bean.CarPlate;
import com.gs.bean.User;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.common.util.UUIDUtil;
import com.gs.service.CarPlateService;
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
 * 汽车车牌管理
 * Created by yaoyong on 2017/4/18.
 */
@Controller
@RequestMapping("/carPlate")
public class CarPlateController {

    private Logger logger = (Logger) LoggerFactory.getLogger(CarPlateController.class);

    @Resource
    private CarPlateService carPlateService;

    @ResponseBody
    @RequestMapping(value = "queryByPagerCarPlate", method = RequestMethod.GET)
    public Pager4EasyUI<CarPlate> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有车牌");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                pager.setTotalRecords(carPlateService.count((User) session.getAttribute("user")));
                List<CarPlate> carPlates = carPlateService.queryByPager(pager);
                return new Pager4EasyUI<CarPlate>(pager.getTotalRecords(), carPlates);
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
    @RequestMapping(value = "queryAllCarPlate", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAll(HttpSession session) {
       /* if (SessionUtil.isLogin(session) || SessionUtil.isOwnerLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部,车主";
            if (RoleUtil.checkRoles(roles)) {*/
                logger.info("查询所有车牌");
                List<CarPlate> carPlates = carPlateService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (CarPlate c : carPlates) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(c.getPlateId());
                    comboBox4EasyUI.setText(c.getPlateName());
                    comboxs.add(comboBox4EasyUI);
                }
                return comboxs;
           /* } else {
                logger.info("此用户无拥有此方法角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }*/
    }

    @ResponseBody
    @RequestMapping(value = "addCarPlate", method = RequestMethod.POST)
    public ControllerResult add(CarPlate carPlate, HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (carPlate != null && !carPlate.equals("")) {
                    logger.info("添加汽车车牌信息");
                    carPlateService.insert(carPlate);
                    return ControllerResult.getSuccessResult("添加汽车车牌信息成功");
                } else {
                    return ControllerResult.getFailResult("添加汽车车牌信息失败");
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
    @RequestMapping(value = "updateCarPlate", method = RequestMethod.POST)
    public ControllerResult updateCarPlate(CarPlate carPlate, HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                if (carPlate != null && !carPlate.equals("")) {
                    logger.info("修改汽车车牌信息");
                    carPlateService.update(carPlate);
                    return ControllerResult.getSuccessResult("修改汽车车牌信息成功");
                } else {
                    return ControllerResult.getFailResult("修改汽车车牌信息失败");
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
     * 查询此车牌名称是否已存在
     */
    @ResponseBody
    @RequestMapping(value = "queryplateName", method = RequestMethod.POST)
    public Map queryplateName(CarPlate carPlate) {
        logger.info("此车牌名称是否已存在此车牌名称");
        int countplateName = carPlateService.queryplateName(carPlate.getPlateName(),carPlate.getPlateId());
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if(countplateName > 0)
            map.put("valid", false);
        else
            map.put("valid", true);

        return map;
    }

    /**
     * 查询所有被禁用的汽车车牌
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryByPagerDisable", method = RequestMethod.GET)
    public Pager4EasyUI<CarPlate> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用汽车车牌");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(carPlateService.countByDisable((User) session.getAttribute("user")));
                List<CarPlate> carPlates = carPlateService.queryByPagerDisable(pager);
                return new Pager4EasyUI<CarPlate>(pager.getTotalRecords(), carPlates);
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
                        carPlateService.active(id);
                        logger.info("激活汽车车牌成功");
                        return ControllerResult.getSuccessResult("激活汽车车牌成功");
                    } else {
                        carPlateService.inactive(id);
                        logger.info("禁用汽车车牌成功");
                        return ControllerResult.getSuccessResult("禁用汽车车牌成功");
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


    /**
     * 汽车车牌记录模糊查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value="blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<CarPlate> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber")String pageNumber, @Param("pageSize")String pageSize) {
        if(SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司接待员,汽车公司总技师,汽车公司技师,汽车公司学徒,汽车公司销售人员,汽车公司财务人员,汽车公司采购人员,汽车公司库管人员,汽车公司人力资源管理部";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("汽车车牌记录模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User)session.getAttribute("user"));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if(text != null && text!="" && value != null && value != "") {
                    List<CarPlate> carPlates = null;
                    CarPlate carPlate = new CarPlate();
                    if(text.equals("车牌名称/车牌描述")){
                        carPlate.setPlateName(value);
                        carPlate.setPlateDes(value);
                    }else if(text.equals("车牌名称")){
                        carPlate.setPlateName(value);
                    }else if(text.equals("车牌描述")) {
                        carPlate.setPlateDes(value);
                    }
                    carPlates = carPlateService.blurredQuery(pager,carPlate);
                    pager.setTotalRecords(carPlateService.countByBlurred(carPlate,(User)session.getAttribute("user")));
                    System.out.print(carPlates);
                    return new Pager4EasyUI<CarPlate>(pager.getTotalRecords(), carPlates);
                }else{ // 当在模糊查询输入框中输入的值为空时, 使它查询全部
                    pager.setTotalRecords(carPlateService.count((User)session.getAttribute("user")));
                    List<CarPlate> carPlates = carPlateService.queryByPager(pager);
                    return new Pager4EasyUI<CarPlate>(pager.getTotalRecords(), carPlates);
                }
            }else {
                logger.info("此用户无拥有供应商记录模糊查询角色");
                return null;
            }
        }else{
            logger.info("请先登录");
            return null;
        }
    }

}
