package com.gs.service.impl;

import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.CompanyDAO;
import com.gs.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:57:28
*@des 公司表Service实现
*/
@Service
public class CompanyServiceImpl implements CompanyService {

	@Resource
	private CompanyDAO companyDAO;

	public int insert(Company company) { return companyDAO.insert(company); }
	public int batchInsert(List<Company> list) { return companyDAO.batchInsert(list); }
	public int delete(Company company) { return companyDAO.delete(company); }
	public int deleteById(String id) {
        return companyDAO.deleteById(id);
    }
	public int batchDelete(List<Company> list) { return companyDAO.batchDelete(list); }
	public int update(Company company) { return companyDAO.update(company); }
	public int batchUpdate(List<Company> list) { return companyDAO.batchUpdate(list); }
	public List<Company> queryAll(User user) { return companyDAO.queryAll(user); }

	@Override
	public List<Company> queryAll(String status) {
		return companyDAO.queryAll(status);
	}

	public List<Company> queryByStatus(String status) { return companyDAO.queryAll(status); }
	public Company query(Company company) { return companyDAO.query(company); }
	public Company queryById(String id) { return companyDAO.queryById(id); }
	public List<Company> queryByPager(Pager pager) { return companyDAO.queryByPager(pager); }
	public int count(User user) { return companyDAO.count(user); }
	public int inactive(String id) { return companyDAO.inactive(id); }
	public int active(String id) { return companyDAO.active(id); }

	public List<Company> queryByPagerDisable(Pager pager) {
		return companyDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return companyDAO.countByDisable(user);
	}

	@Override
	public List<Company> blurredQuery(Pager pager, Company company) {
		return companyDAO.blurredQuery(pager,company);
	}

	@Override
	public int countByBlurred(Company company, User user) {
		return companyDAO.countByBlurred(company,user);
	}

	@Override
	public int updLogo(String userId, String companyLogo) {
		return companyDAO.updLogo(userId,companyLogo);
	}

	@Override
	public int querycompanyName(String companyName,String companyId) {
		return companyDAO.querycompanyName(companyName,companyId);
	}

	@Override
	public int querycompanyPricipalphone(String companyPricipalphone,String companyId) {
		return companyDAO.querycompanyPricipalphone(companyPricipalphone,companyId);
	}

	@Override
	public int querycompanyWebsite(String companyWebsite, String companyId) {
		return companyDAO.querycompanyWebsite(companyWebsite,companyId);
	}

	@Override
	public Company queryPhone(String companyPricipalphone) {
		return companyDAO.queryPhone(companyPricipalphone);
	}

	@Override
	public List<Company> queryByCompanyInfo() {
		return companyDAO.queryByCompanyInfo();
	}
}