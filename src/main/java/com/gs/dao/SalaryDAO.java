package com.gs.dao;

import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:15
*@des 
*/
@Repository
public interface SalaryDAO extends BaseDAO<String, Salary>{

    // 批量添加，导入时要用
    public void addInsert(List<Salary> salarys);

    public List<Salary> blurredQuery(@Param("pager") Pager pager, @Param("salary") Salary salary);

    public int countByBlurred(@Param("salary") Salary salary, @Param("user") User user);

    public List<Salary> querySalary(@Param("pager") Pager pager, @Param("start")String start, @Param("end")String end);

    public int countSalary(@Param("user")User user, @Param("start") String start, @Param("end") String end);

    public User queryBySalary(String userId);
}