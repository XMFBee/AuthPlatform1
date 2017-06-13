package com.gs.service;

import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Wang Genshen on 2017-03-22.
 */
public interface BaseService<PK extends Serializable, T> {

    /**
     * 插入数据
     *
     * @param t
     * @return
     */
    public int insert(T t);

    /**
     * 批量插入数据
     *
     * @param list
     * @return
     */
    public int batchInsert(List<T> list);

    /**
     * 删除数据
     *
     * @param t
     * @return
     */
    public int delete(T t);

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public int deleteById(PK id);

    /**
     * 批量删除数据
     *
     * @param list
     * @return
     */
    public int batchDelete(List<T> list);

    /**
     * 更新数据
     *
     * @param t
     * @return
     */
    public int update(T t);

    /**
     * 批量更新数据
     *
     * @param list
     * @return
     */
    public int batchUpdate(List<T> list);

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<T> queryAll(User user);

    /**
     * 查询指定状态的所有记录
     *
     * @param status
     * @return
     */
    public List<T> queryAll(String status);

    /**
     * 查询单个记录
     *
     * @param t
     * @return
     */
    public T query(T t);

    /**
     * 根据id查询记录
     *
     * @param id
     * @return
     */
    public T queryById(PK id);

    /**
     * 分页查询记录
     *
     * @param pager
     * @return
     */
    public List<T> queryByPager(Pager pager);

    /**
     * 计数
     *
     * @return
     */
    public int count(User user);

    /**
     * 冻结数据
     *
     * @param id
     * @return
     */
    public int inactive(String id);

    /**
     * 激活数据
     *
     * @param id
     * @return
     */
    public int active(String id);

    /**
     * 分页查询被禁用的记录
     */
    public List<T> queryByPagerDisable(Pager pager);

    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable(User user);
    /**
     * 模糊查询
     */
    public List<T> blurredQuery(Pager pager,T t);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(T t, User user);

}
