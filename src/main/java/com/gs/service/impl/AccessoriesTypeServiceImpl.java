package com.gs.service.impl;

import com.gs.bean.AccessoriesType;
import com.gs.bean.Checkin;
import com.gs.bean.User;
import com.gs.dao.AccessoriesTypeDAO;
import com.gs.service.AccessoriesTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.gs.common.bean.Pager;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:49:20
*@des 配件分类Service实现
*/
@Service
public class AccessoriesTypeServiceImpl implements AccessoriesTypeService {

    @Resource
    private AccessoriesTypeDAO accessoriesTypeDAO;

    public int insert(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.insert(accessoriesType);
    }

    public int batchInsert(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchInsert(list);
    }

    public int delete(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.delete(accessoriesType);
    }

    public int deleteById(String id) {
        return accessoriesTypeDAO.deleteById(id);
    }

    public int batchDelete(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchDelete(list);
    }

    public int update(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.update(accessoriesType);
    }

    public int batchUpdate(List<AccessoriesType> list) {
        return accessoriesTypeDAO.batchUpdate(list);
    }

    @Override
    public List<AccessoriesType> queryAll(User user) {
        return accessoriesTypeDAO.queryAll(user);
    }

    @Override
    public List<AccessoriesType> queryAll(String status) {
        return accessoriesTypeDAO.queryAll(status);
    }

    public AccessoriesType query(AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.query(accessoriesType);
    }

    public AccessoriesType queryById(String id) {
        return accessoriesTypeDAO.queryById(id);
    }

    public List<AccessoriesType> queryByPager(Pager pager) {
        return accessoriesTypeDAO.queryByPager(pager);
    }

    @Override
    public int count(User user) {
        return accessoriesTypeDAO.count(user);
    }

    public int inactive(String id) {
        return accessoriesTypeDAO.inactive(id);
    }

    public int active(String id) {
        return accessoriesTypeDAO.active(id);
    }

    @Override
    public List<AccessoriesType> queryByPagerDisable(Pager pager) {
        return accessoriesTypeDAO.queryByPagerDisable(pager);
    }

    @Override
    public int countByDisable(User user) {
        return accessoriesTypeDAO.countByDisable(user);
    }

    @Override
    public int countByDisable() {
        return accessoriesTypeDAO.countByDisable();
    }

    @Override
    public List<AccessoriesType> queryTypeName(String companyId) {
        return accessoriesTypeDAO.queryTypeName(companyId);
    }

    @Override
    public int queryAccTypeNameOne(@Param("accTypeName") String accTypeName, @Param("accTypeId") String accTypeId) {
        return accessoriesTypeDAO.queryAccTypeNameOne(accTypeName,accTypeId);
    }

    public List<AccessoriesType> blurredQuery(Pager pager, AccessoriesType accessoriesType) {
        return accessoriesTypeDAO.blurredQuery(pager, accessoriesType);
    }

    @Override
    public int countByBlurred(AccessoriesType accessoriesType, User user) {
        return accessoriesTypeDAO.countByBlurred(accessoriesType, user);
    }
}