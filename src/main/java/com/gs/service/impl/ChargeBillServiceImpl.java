package com.gs.service.impl;

import com.gs.bean.ChargeBill;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.ChargeBillDAO;
import com.gs.service.ChargeBillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des 收费单据Service实现
 * @since 2017-04-17 15:56:02
 */
@Service
public class ChargeBillServiceImpl implements ChargeBillService {

    @Resource
    private ChargeBillDAO chargeBillDAO;

    public int insert(ChargeBill chargeBill) {
        return chargeBillDAO.insert(chargeBill);
    }

    public int batchInsert(List<ChargeBill> list) {
        return chargeBillDAO.batchInsert(list);
    }

    public int delete(ChargeBill chargeBill) {
        return chargeBillDAO.delete(chargeBill);
    }

    public int deleteById(String id) {
        return chargeBillDAO.deleteById(id);
    }

    public int batchDelete(List<ChargeBill> list) {
        return chargeBillDAO.batchDelete(list);
    }

    public int update(ChargeBill chargeBill) {
        return chargeBillDAO.update(chargeBill);
    }

    public int batchUpdate(List<ChargeBill> list) {
        return chargeBillDAO.batchUpdate(list);
    }

    public List<ChargeBill> queryAll(User user) {
        return chargeBillDAO.queryAll(user);
    }

    public List<ChargeBill> queryAll(String status) {
        return chargeBillDAO.queryAll(status);
    }

    public List<ChargeBill> queryByStatus(String status) {
        return chargeBillDAO.queryAll(status);
    }

    public ChargeBill query(ChargeBill chargeBill) {
        return chargeBillDAO.query(chargeBill);
    }

    public ChargeBill queryById(String id) {
        return chargeBillDAO.queryById(id);
    }

    public List<ChargeBill> queryByPager(Pager pager) {
        return chargeBillDAO.queryByPager(pager);
    }

    public int count(User user) {
        return chargeBillDAO.count(user);
    }

    public int inactive(String id) {
        return chargeBillDAO.inactive(id);
    }

    public int active(String id) {
        return chargeBillDAO.active(id);
    }

    public List<ChargeBill> queryByPagerDisable(Pager pager) {
        return chargeBillDAO.queryByPagerDisable(pager);
    }

    public int countByDisable(User user) {
        return chargeBillDAO.countByDisable(user);
    }

    public List<ChargeBill> blurredQuery(Pager pager, ChargeBill chargeBill) {
        return chargeBillDAO.blurredQuery(pager,chargeBill);
    }

    public int countByBlurred(ChargeBill chargeBill,User user) {
        return chargeBillDAO.countByBlurred(chargeBill, user);
    }

    @Override
    public List<ChargeBill> queryByCondition(String start, String end, String userId, String maintainOrFix, String type) {
        return chargeBillDAO.queryByCondition(start, end, userId, maintainOrFix, type);
    }

    /**
     * 确认收费
     */
    @Override
    public void updateDate(String chargeBillId) {
        chargeBillDAO.updateDate(chargeBillId);
    }

    /**
     * 用户确认收费单据
     */
    @Override
    public void updateCurrent(String id) {
        chargeBillDAO.updateCurrent(id);
    }

    @Override
    public int countByOwner(User frontUser) {
        return chargeBillDAO.countByOwner(frontUser);
    }

    @Override
    public List<ChargeBill> queryByOwner(Pager pager) {
        return chargeBillDAO.queryByOwner(pager);
    }
}