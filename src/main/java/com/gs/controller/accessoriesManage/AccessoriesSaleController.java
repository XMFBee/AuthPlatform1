package com.gs.controller.accessoriesManage;

import ch.qos.logback.classic.Logger;
import com.gs.bean.*;
import com.gs.common.bean.ComboBox4EasyUI;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.RoleUtil;
import com.gs.common.util.SessionUtil;
import com.gs.service.AccessoriesSaleService;
import com.gs.service.AccessoriesService;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.RemindService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 王怡 配件销售
 * Created by Administrator on 2017/4/18.
 */
@Controller
@RequestMapping("/accSale")
public class AccessoriesSaleController {

    private Logger logger = (Logger) LoggerFactory.getLogger(AccessoriesSaleController.class);

    @Resource
    private AccessoriesSaleService accessoriesSaleService;

    @Resource
    private AccessoriesService accessoriesService;

    @Resource
    private IncomingOutgoingService incomingOutgoingService;

    @Resource
    private RemindService remindService;

    @ResponseBody
    @RequestMapping(value = "queryAllAccSale", method = RequestMethod.GET)
    public List<ComboBox4EasyUI> queryAllAccSale(HttpSession session) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("所有配件销售信息");
                List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryAll((User) session.getAttribute("user"));
                List<ComboBox4EasyUI> comboxs = new ArrayList<ComboBox4EasyUI>();
                for (AccessoriesSale c : accessoriesSales) {
                    ComboBox4EasyUI comboBox4EasyUI = new ComboBox4EasyUI();
                    comboBox4EasyUI.setId(c.getAccSaleId());
                    comboBox4EasyUI.setText(c.getAccSaleId());
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
     * 分页查询配件销售信息
     */
    @ResponseBody
    @RequestMapping(value = "queryByPage", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesSale> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(accessoriesSaleService.count((User) session.getAttribute("user")));
                logger.info("分页查询配件销售信息成功");
                List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryByPager(pager);
                return new Pager4EasyUI<AccessoriesSale>(pager.getTotalRecords(), accessoriesSales);
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
     * 添加配件销售记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addAccSale", method = RequestMethod.POST)
    public ControllerResult addAccSale(HttpSession session, AccessoriesSale accessoriesSale, @Param("inTypeId") String inTypeId) {
        if (SessionUtil.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员";
            if (RoleUtil.checkRoles(roles)) {
                if (accessoriesSale != null && !accessoriesSale.equals("")) {
                    Accessories accessories = accessoriesService.queryById(accessoriesSale.getAccId());
                    if (accessoriesSale.getAccSaleCount() > accessories.getAccIdle()) {
                        return ControllerResult.getFailResult("配件销售数量需要在1至" + accessories.getAccIdle() + "之间!");
                    }
                    accessoriesSale.setCompanyId(user.getCompanyId());
                    accessoriesSaleService.insert(accessoriesSale);
                    accessoriesService.reduceCount(accessoriesSale.getAccSaleCount(), accessoriesSale.getAccId());
                    Accessories a=accessoriesService.queryById(accessoriesSale.getAccId());
                    if(a.getAccIdle()==0&& a.getAccTotal()==0 || a.getAccIdle()<0 || a.getAccTotal()<0){
                        Remind remind=new Remind();
                        remind.setRemindDes("名称为："+a.getAccName()+"的配件可用数量已为0，请尽快添加");
                        remind.setRemindUser(user.getUserId());
                        remindService.addRemind(remind);
                        accessoriesService.inactive(accessoriesSale.getAccId());
                    }
                    incomingOutgoingService.insert(inconSet(accessoriesSale, inTypeId,user));
                    logger.info("添加配件销售成功");
                    return ControllerResult.getSuccessResult("添加配件销售成功");
                } else {
                    logger.info("添加配件销售失败");
                    return ControllerResult.getSuccessResult("添加配件销售失败");
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

    public IncomingOutgoing inconSet(AccessoriesSale accessoriesSale, String inTypeId,User user) {
        if (accessoriesSale != null && !accessoriesSale.equals("")) {
            IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
            incomingOutgoing.setInOutMoney(accessoriesSale.getAccSaleMoney());
            incomingOutgoing.setInOutCreatedUser(user.getUserId());
            incomingOutgoing.setInTypeId(inTypeId);
            incomingOutgoing.setCompanyId(user.getCompanyId());
            return incomingOutgoing;
        }
        return null;
    }

    /**
     * 修改配件销售记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updateAccSale", method = RequestMethod.POST)
    public ControllerResult updateAccSale(HttpSession session, AccessoriesSale accessoriesSale) {
        if (SessionUtil.isLogin(session)) {
            User user = (User) session.getAttribute("user");
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员";
            if (RoleUtil.checkRoles(roles)) {
                if (accessoriesSale != null && !accessoriesSale.equals("")) {
                    accessoriesSale.setCompanyId(user.getCompanyId());
                    accessoriesSaleService.update(accessoriesSale);
                    logger.info("修改配件销售成功");
                    return ControllerResult.getSuccessResult("修改配件销售成功");
                } else {
                    logger.info("修改配件销售失败");
                    return ControllerResult.getFailResult("修改配件销售失败");
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
    public Pager4EasyUI<AccessoriesSale> queryByPagerDisable(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("分页查询所有被禁用登记记录");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(accessoriesSaleService.countByDisable((User) session.getAttribute("user")));
                List<AccessoriesSale> accessoriesSales = accessoriesSaleService.queryByPagerDisable(pager);
                return new Pager4EasyUI<AccessoriesSale>(pager.getTotalRecords(), accessoriesSales);
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
    public ControllerResult inactive(HttpSession session, String accSaleId, String accSaleStatus) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员";
            if (RoleUtil.checkRoles(roles)) {
                if (accSaleId != null && !accSaleId.equals("") && accSaleStatus != null && !accSaleStatus.equals("")) {
                    if (accSaleStatus.equals("N")) {
                        accessoriesSaleService.active(accSaleId);
                        logger.info("激活配件销售成功");
                        return ControllerResult.getSuccessResult("激活配件销售成功");
                    } else {
                        accessoriesSaleService.inactive(accSaleId);
                        logger.info("禁用配件销售成功");
                        return ControllerResult.getSuccessResult("禁用配件销售成功");
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

    /**
     * 销售记录的模糊查询
     *
     * @param request
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<AccessoriesSale> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司销售人员,系统超级管理员,系统普通管理员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("配件销售记录模糊查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && !text.equals("") && value != null && !value.equals("")) {
                    List<AccessoriesSale> accessoriesSales = null;
                    AccessoriesSale accessoriesSale = new AccessoriesSale();
                    if (text.equals("配件名称/汽车公司")) {
                        accessoriesSale.setCompanyId(value);
                        accessoriesSale.setAccId(value);
                    } else if (text.equals("汽车公司")) {
                        accessoriesSale.setCompanyId(value);
                    } else if (text.equals("配件名称")) {
                        accessoriesSale.setAccId(value);
                    }
                    accessoriesSales = accessoriesSaleService.blurredQuery(pager, accessoriesSale);
                    pager.setTotalRecords(accessoriesSaleService.countByBlurred(accessoriesSale, (User) session.getAttribute("user")));
                    return new Pager4EasyUI<AccessoriesSale>(pager.getTotalRecords(), accessoriesSales);
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
}
