package com.gs.service.impl;

import com.gs.bean.Checkin;
import com.gs.bean.Salary;
import com.gs.bean.User;
import com.gs.dao.SalaryDAO;
import com.gs.service.SalaryService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;
import org.springframework.transaction.annotation.Transactional;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:11:17
*@des 
*/
@Service
public class SalaryServiceImpl implements SalaryService {

	@Resource
	private SalaryDAO salaryDAO;

	public int insert(Salary salary) { return salaryDAO.insert(salary); }
	public int batchInsert(List<Salary> list) { return salaryDAO.batchInsert(list); }
	public int delete(Salary salary) { return salaryDAO.delete(salary); }
	public int deleteById(String id) {
        return salaryDAO.deleteById(id);
    }
	public int batchDelete(List<Salary> list) { return salaryDAO.batchDelete(list); }
	public int update(Salary salary) { return salaryDAO.update(salary); }
	public int batchUpdate(List<Salary> list) { return salaryDAO.batchUpdate(list); }
	public List<Salary> queryAll(User user) { return salaryDAO.queryAll(user); }

	@Override
	public List<Salary> queryAll(String status) {
		return salaryDAO.queryAll(status);
	}


	public List<Salary> queryByStatus(String status) { return salaryDAO.queryAll(status); }
	public Salary query(Salary salary) { return salaryDAO.query(salary); }
	public Salary queryById(String id) { return salaryDAO.queryById(id); }
	public List<Salary> queryByPager(Pager pager) { return salaryDAO.queryByPager(pager); }
	public int count(User user) { return salaryDAO.count(user); }
	public int inactive(String id) { return salaryDAO.inactive(id); }
	public int active(String id) { return salaryDAO.active(id); }

	public List<Salary> queryByPagerDisable(Pager pager) {
		return salaryDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return salaryDAO.countByDisable(user);
	}

	public List<Salary> blurredQuery(Pager pager, Salary salary) {

		return salaryDAO.blurredQuery(pager, salary);
	}

	@Override
	public int countByBlurred(Salary salary, User user) {

		return salaryDAO.countByBlurred(salary, user);
	}

	public int countByBlurred(Salary salary) {
		return 0;
	}

	@Transactional
	public boolean addInsert(List<Salary> salaries) {
		boolean flag = false;
		if (salaries != null) {
			salaryDAO.addInsert(salaries);
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Salary> querySalary(Pager pager, String start, String end) {
		return salaryDAO.querySalary(pager,start, end);
	}

	@Override
	public int countSalary(User user, String start, String end) {
		return salaryDAO.countSalary(user, start, end);
	}

	@Override
	public User queryBySalary(String userId) {
		return salaryDAO.queryBySalary(userId);
	}
}