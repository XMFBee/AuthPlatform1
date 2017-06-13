package com.gs.service.impl;

import com.gs.bean.CarPlate;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.dao.CarPlateDAO;
import com.gs.service.CarPlateService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:55:08
*@des 汽车车牌Service实现
*/
@Service
public class CarPlateServiceImpl implements CarPlateService {

	@Resource
	private CarPlateDAO carPlateDAO;

	public int insert(CarPlate carPlate) { return carPlateDAO.insert(carPlate); }
	public int batchInsert(List<CarPlate> list) { return carPlateDAO.batchInsert(list); }
	public int delete(CarPlate carPlate) { return carPlateDAO.delete(carPlate); }
	public int deleteById(String id) {
        return carPlateDAO.deleteById(id);
    }
	public int batchDelete(List<CarPlate> list) { return carPlateDAO.batchDelete(list); }
	public int update(CarPlate carPlate) { return carPlateDAO.update(carPlate); }
	public int batchUpdate(List<CarPlate> list) { return carPlateDAO.batchUpdate(list); }
	public List<CarPlate> queryAll(User user) { return carPlateDAO.queryAll(user); }

	@Override
	public List<CarPlate> queryAll(String status) {
		return carPlateDAO.queryAll(status);
	}

	public List<CarPlate> queryByStatus(String status) { return carPlateDAO.queryAll(status); }
	public CarPlate query(CarPlate carPlate) { return carPlateDAO.query(carPlate); }
	public CarPlate queryById(String id) { return carPlateDAO.queryById(id); }
	public List<CarPlate> queryByPager(Pager pager) { return carPlateDAO.queryByPager(pager); }
	public int count(User user) { return carPlateDAO.count(user); }
	public int inactive(String id) { return carPlateDAO.inactive(id); }
	public int active(String id) { return carPlateDAO.active(id); }

	public List<CarPlate> queryByPagerDisable(Pager pager) {
		return carPlateDAO.queryByPagerDisable(pager);
	}

	@Override
	public int countByDisable(User user) {
		return carPlateDAO.countByDisable(user);
	}

	public List<CarPlate> blurredQuery(Pager pager, CarPlate carPlate) {
		return carPlateDAO.blurredQuery(pager,carPlate);
	}
	@Override
	public int countByBlurred(CarPlate carPlate, User user) {
		return carPlateDAO.countByBlurred(carPlate,user);
	}

	@Override
	public int queryplateName(String plateName,String plateId) {
		return carPlateDAO.queryplateName(plateName,plateId);
	}
}