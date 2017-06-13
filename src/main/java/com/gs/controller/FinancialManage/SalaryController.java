package com.gs.controller.FinancialManage;

import com.gs.bean.*;
import com.gs.common.Constants;
import com.gs.common.Methods;
import com.gs.common.bean.ControllerResult;
import com.gs.common.bean.Pager;
import com.gs.common.bean.Pager4EasyUI;
import com.gs.common.util.*;
import com.gs.service.IncomingOutgoingService;
import com.gs.service.SalaryService;
import com.gs.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by GZWangBin on 2017/4/20.
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {

    private Logger logger = (Logger) LoggerFactory.getLogger(SalaryController.class);

    /**
     * 工资Service
     */
    @Resource
    public SalaryService salaryService;

    @Resource
    public IncomingOutgoingService incomingOutgoingService;

    @Resource
    public UserService userService;

    @ResponseBody
    @RequestMapping(value = "queryByPager", method = RequestMethod.GET)
    public Pager4EasyUI<Salary> queryByPager(HttpSession session, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("工资信息分页查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(salaryService.count((User) session.getAttribute("user")));
                List<Salary> salaries = salaryService.queryByPager(pager);
                return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salaries);
            } else {
                logger.info("此用户无拥有工资信息分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
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
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public ControllerResult add(HttpSession session, Salary salary, String inId, String outId) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("添加工资信息");
                User user = (User) session.getAttribute("user");
                double prize = 0.d;
                double minus = 0.d;
                if (salary.getPrizeSalary() != null) {
                    prize = salary.getPrizeSalary();
                }
                if (salary.getMinusSalay() != null) {
                    minus = salary.getMinusSalay();
                }
                if (salary.getPrizeSalary() == null) {
                    prize = 0d;
                }
                if (salary.getMinusSalay() == null) {
                    minus = 0d;
                }
                salary.setTotalSalary(user.getUserSalary() + prize - minus);
                salary.setUser(user);
                IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                if (inId != null) {
                    incomingOutgoing.setInTypeId(inId);
                    incomingOutgoing.setInOutMoney(minus - prize - user.getUserSalary());
                } else if (outId != null) {
                    incomingOutgoing.setOutTypeId(outId);
                    incomingOutgoing.setInOutMoney(salary.getTotalSalary());
                } else {
                    incomingOutgoing.setInTypeId(null);
                    incomingOutgoing.setInTypeId(null);
                }
                incomingOutgoing.setInOutCreatedUser(user.getUserId());
                incomingOutgoing.setCompanyId(user.getCompanyId());
                incomingOutgoingService.insert(incomingOutgoing);
                salaryService.insert(salary);
                return ControllerResult.getSuccessResult("添加工资信息成功，并自动生成一条收支记录");
            } else {
                logger.info("此用户无拥有添加工资信息的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ControllerResult update(HttpSession session, Salary salary) {
        if (SessionUtil.isLogin(session)) {
            String roles = "公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("修改工资信息");
                User user = (User) session.getAttribute("user");
                salary.setUser(user);
                salary.setTotalSalary(user.getUserSalary() + salary.getPrizeSalary() - salary.getMinusSalay());
                salaryService.update(salary);
                return ControllerResult.getSuccessResult("修改成功");
            } else {
                logger.info("此用户无拥有修改工资信息的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @RequestMapping(value = "exportExcel")
    public ModelAndView exportExcel(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                try {
                    Salary salary = new Salary();
                    // 查询工资信息
                    List<Salary> userlist = salaryService.queryAll((User) session.getAttribute("user"));
                    String title = "员工工资信息";
                    String[] rowsName = new String[]{"工资发放编号", "用户编号", "用户名称", "奖金", "罚款", "总工资", "工资发放描述", "工资发放时间", "工资发放创建时间"};
                    List<Object[]> dataList = new ArrayList<Object[]>();
                    Object[] objs = null;
                    for (int i = 0; i < userlist.size(); i++) {
                        Salary salary1 = userlist.get(i);
                        objs = new Object[rowsName.length];
                        objs[0] = salary1.getSalaryId();
                        objs[1] = salary1.getUserId();
                        objs[2] = salary1.getUser().getUserName();
                        if (salary1.getPrizeSalary() != null) {
                            objs[3] = salary1.getPrizeSalary();
                        } else {
                            objs[3] = 0;
                        }
                        if (salary1.getMinusSalay() != null) {
                            objs[4] = salary1.getMinusSalay();
                        } else {
                            objs[4] = 0;
                        }

                        objs[5] = salary1.getTotalSalary();
                        objs[6] = salary1.getSalaryDes();
                        if (salary1.getSalaryTime() != null) {
                            objs[7] = java.sql.Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(salary1.getSalaryTime()));
                        } else {
                            objs[7] = null;
                        }

                        if (salary1.getSalaryCreatedTime() != null) {
                            objs[8] = java.sql.Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(salary1.getSalaryCreatedTime()));
                        } else {
                            objs[8] = null;
                        }
                        dataList.add(objs);
                    }
                    ExcelExport ex = new ExcelExport(title, rowsName, dataList, response);
                    ex.exportData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            } else {
                logger.info("此用户无拥有导出工资信息的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }

    }

    @ResponseBody
    @RequestMapping(value = "blurredQuery", method = RequestMethod.GET)
    public Pager4EasyUI<Salary> blurredQuery(HttpSession session, HttpServletRequest request, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("工资分类模糊查询");
                Pager pager = new Pager();
                pager.setUser((User) session.getAttribute("user"));
                pager.setPageNo(Integer.parseInt(pageNumber));
                pager.setPageSize(Integer.parseInt(pageSize));
                String text = request.getParameter("text");
                String value = request.getParameter("value");
                if (text != null && !text.equals("") && value != null && !value.equals("")) {
                    List<Salary> salaries = null;
                    Salary salary = new Salary();
                    if (text.equals("姓名")) {
                        salary.setUserId(value);
                    } else if (text.equals("工资")) {
                        salary.setTotalSalary(Double.valueOf(value));
                    }
                    salaries = salaryService.blurredQuery(pager, salary);
                    pager.setTotalRecords(salaryService.countByBlurred(salary, (User) session.getAttribute("user")));
                    return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salaries);
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

    @ResponseBody
    @RequestMapping(value = "selectSalary", method = RequestMethod.GET)
    public Pager4EasyUI<Salary> querySalary(HttpSession session, HttpServletRequest request, String start, String end, @Param("pageNumber") String pageNumber, @Param("pageSize") String pageSize) {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("根据工资段查询");
                Pager pager = new Pager();
                pager.setPageNo(Integer.valueOf(pageNumber));
                pager.setPageSize(Integer.valueOf(pageSize));
                pager.setUser((User) session.getAttribute("user"));
                pager.setTotalRecords(salaryService.countSalary((User)session.getAttribute("user"), start,end));
                List<Salary> salaries = salaryService.querySalary(pager,start,end);
                return new Pager4EasyUI<Salary>(pager.getTotalRecords(), salaries);
            } else {
                logger.info("此用户无拥有工资信息分页查询的角色");
                return null;
            }
        } else {
            logger.info("请先登录");
            return null;
        }
    }



    @ResponseBody
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public ControllerResult readExcel(HttpSession session, MultipartFile txt_file, String outId) throws IOException {
        if (SessionUtil.isLogin(session)) {
            String roles = "系统超级管理员,系统普通管理员,公司超级管理员,公司普通管理员,汽车公司财务人员";
            if (RoleUtil.checkRoles(roles)) {
                logger.info("导入工资");
                String name = txt_file.getOriginalFilename();
                long size = txt_file.getSize();
                if (name == null || ExcelReader.EMPTY.equals(name) && size == 0) {
                    return ControllerResult.getFailResult("导入工资失败");
                }
                //读取Excel数据到List中
                List<ArrayList<String>> list = null;
                try {
                    list = new ExcelRead().readExcel(txt_file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Salary salary = null;
                User sessionUser = (User) session.getAttribute("user");
                //list中存的就是excel中的数据，可以根据excel中每一列的值转换成你所需要的值（从0开始），如：
                List<Salary> salaries = new ArrayList<Salary>();
                List<IncomingOutgoing> incomingOutgoings = new ArrayList<IncomingOutgoing>();

                for (ArrayList<String> arr : list) {
                    salary = new Salary();
                    try {
                        salary.setUserId(arr.get(1));

                        if (Double.valueOf(arr.get(3)) == null) {
                            salary.setPrizeSalary(0.d);
                        } else {
                            salary.setPrizeSalary(Double.valueOf(arr.get(3)));
                        }
                        if (Double.valueOf(arr.get(4)) == null) {
                            salary.setMinusSalay(0.d);
                        } else {
                            salary.setMinusSalay(Double.valueOf(arr.get(4)));
                        }
                        salary.setTotalSalary(Double.valueOf(arr.get(5)));
                        salary.setSalaryDes(arr.get(6));
                        salary.setSalaryTime(dateFormat(arr.get(7)));
                    } catch (NullPointerException e) {
                        return ControllerResult.getFailResult("导入工资失败");
                    }
                    User user = (User)session.getAttribute("user");
                    IncomingOutgoing incomingOutgoing = new IncomingOutgoing();
                    incomingOutgoing.setOutTypeId(outId);
                    incomingOutgoing.setInTypeId(null);
                    incomingOutgoing.setCompanyId(user.getCompanyId());
                    incomingOutgoing.setInOutCreatedUser(user.getUserId());
                    incomingOutgoing.setInOutMoney(salary.getTotalSalary());
                    incomingOutgoings.add(incomingOutgoing);
                    salaries.add(salary);
                }
                if (salaryService.addInsert(salaries)) {
                    incomingOutgoingService.addInsert(incomingOutgoings);
                    return ControllerResult.getSuccessResult("导入工资成功");
                } else {
                    return ControllerResult.getFailResult("导入工资失败,没有该权限");
                }
            }
            return ControllerResult.getFailResult("导入失败");
        } else {
            logger.info("Session已失效，请重新登入");
            return ControllerResult.getNotLoginResult("登入信息已失效，请重新登入");
        }
    }

    public Date dateFormat(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
