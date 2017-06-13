package com.gs.service.impl;

import com.gs.bean.Accessories;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.AccessoriesDAO;
import com.gs.service.AccessoriesService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des AccessoriesService
 * @since 2017-04-17 15:43:06
 */
@Service
public class AccessoriesServiceImpl implements AccessoriesService {

    @Resource
    private AccessoriesDAO accessoriesDAO;

    public int insert(Accessories accessories) {
        return accessoriesDAO.insert(accessories);
    }

    public int batchInsert(List<Accessories> list) {
        return accessoriesDAO.batchInsert(list);
    }

    public int delete(Accessories accessories) {
        return accessoriesDAO.delete(accessories);
    }

    public int deleteById(String id) {
        return accessoriesDAO.deleteById(id);
    }

    public int batchDelete(List<Accessories> list) {
        return accessoriesDAO.batchDelete(list);
    }

    public int update(Accessories accessories) {
        return accessoriesDAO.update(accessories);
    }

    public int batchUpdate(List<Accessories> list) {
        return accessoriesDAO.batchUpdate(list);
    }

    @Override
    public List<Accessories> queryAll(User user) {
        return accessoriesDAO.queryAll(user);
    }

    @Override
    public List<Accessories> queryAll(String status) {
        return accessoriesDAO.queryAll(status);
    }

    public List<Accessories> queryByStatus(String status) {
        return accessoriesDAO.queryAll(status);
    }

    public Accessories query(Accessories accessories) {
        return accessoriesDAO.query(accessories);
    }

    public Accessories queryById(String id) {
        return accessoriesDAO.queryById(id);
    }

    public List<Accessories> queryByPager(Pager pager) {
        return accessoriesDAO.queryByPager(pager);
    }

    @Override
    public int count(User user) {
        return accessoriesDAO.count(user);
    }

    public int inactive(String id) {
        return accessoriesDAO.inactive(id);
    }

    public int active(String id) {
        return accessoriesDAO.active(id);
    }

    @Override
    public List<Accessories> queryByPagerDisable(Pager pager) {
        return accessoriesDAO.queryByPagerDisable(pager);
    }

    @Override
    public int countByDisable(User user) {
        return accessoriesDAO.countByDisable(user);
    }


    @Override
    public List<Accessories> queryByIdPager(String id, Pager pager) {
        return accessoriesDAO.queryByIdPager(id, pager);
    }

    @Override
    public int updateCount(@Param("accCount") int accCount, @Param("accId") String accId) {
        return accessoriesDAO.updateCount(accCount, accId);
    }

    @Override
    public int reduceCount(@Param("accCount") int accCount, @Param("accId") String accId) {
        return accessoriesDAO.reduceCount(accCount,accId);
    }

    @Override
    public Accessories queryByName(@Param("accName") String accName) {
        return accessoriesDAO.queryByName(accName);
    }

    @Override
    public List<Accessories> queryByCondition(String start, String end, String companyId, String accTypeId, String type) {
        return accessoriesDAO.queryByCondition(start, end,companyId,accTypeId, type);
    }


    public List<Accessories> blurredQuery(Pager pager, Accessories accessories) {
        return accessoriesDAO.blurredQuery(pager, accessories);
    }

    @Override
    public int countByBlurred(Accessories accessories, User user) {
        return accessoriesDAO.countByBlurred(accessories,user);
    }

    public int countByBlurred(Accessories accessories) {
        return accessoriesDAO.countByBlurred(accessories);
    }


}