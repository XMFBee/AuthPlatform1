package com.gs.service.impl;

import com.gs.bean.Flow;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.FlowDAO;
import com.gs.service.FlowService;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Administrator on 2017-05-06.
 */
public class FlowServiceImpl implements FlowService {
    @Resource
    private FlowDAO flowDAO;


    @Override
    public int insert(Flow flow) {
        return flowDAO.insert(flow);
    }

    @Override
    public int batchInsert(List<Flow> list) {
        return flowDAO.batchInsert(list);
    }

    @Override
    public int delete(Flow flow) {
        return flowDAO.delete(flow);
    }

    @Override
    public int deleteById(String id) {
        return flowDAO.deleteById(id);
    }

    @Override
    public int batchDelete(List<Flow> list) {
        return flowDAO.batchDelete(list);
    }

    @Override
    public int update(Flow flow) {
        return flowDAO.update(flow);
    }

    @Override
    public int batchUpdate(List<Flow> list) {
        return flowDAO.batchUpdate(list);
    }

    @Override
    public List<Flow> queryAll(User user) {
        return flowDAO.queryAll(user);
    }

    @Override
    public List<Flow> queryAll(String status) {
        return flowDAO.queryAll(status);
    }

    @Override
    public Flow query(Flow flow) {
        return flowDAO.query(flow);
    }

    @Override
    public Flow queryById(String id) {
        return flowDAO.queryById(id);
    }

    @Override
    public List<Flow> queryByPager(Pager pager) {
        return flowDAO.queryByPager(pager);
    }

    @Override
    public int count(User user) {
        return flowDAO.count(user);
    }

    @Override
    public int inactive(String id) {
        return flowDAO.inactive(id);
    }

    @Override
    public int active(String id) {
        return flowDAO.active(id);
    }

    @Override
    public List<Flow> queryByPagerDisable(Pager pager) {
        return flowDAO.queryByPagerDisable(pager);
    }

    @Override
    public int countByDisable(User user) {
        return flowDAO.countByDisable(user);
    }

    @Override
    public List<Flow> blurredQuery(Pager pager, Flow flow) {
        return null;
    }

    @Override
    public int countByBlurred(Flow flow, User user) {
        return 0;
    }
}
