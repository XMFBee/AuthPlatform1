package com.gs.service.impl;

import com.gs.bean.User;
import com.gs.common.bean.Pager;
import com.gs.dao.UserDAO;
import com.gs.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des
 * @since 2017-04-17 16:12:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    public int insert(User user) {
        return userDAO.insert(user);
    }

    public int batchInsert(List<User> list) {
        return userDAO.batchInsert(list);
    }

    public int delete(User user) {
        return userDAO.delete(user);
    }

    public int deleteById(String id) {
        return userDAO.deleteById(id);
    }

    public int batchDelete(List<User> list) {
        return userDAO.batchDelete(list);
    }

    public int update(User user) {
        return userDAO.update(user);
    }

    public int batchUpdate(List<User> list) {
        return userDAO.batchUpdate(list);
    }

    public List<User> queryAll(User user) {
        return userDAO.queryAll(user);
    }

    @Override
    public List<User> queryAll(String status) {
        return userDAO.queryAll(status);
    }

    public List<User> queryByStatus(String status) {
        return userDAO.queryAll(status);
    }

    public User query(User user) {
        return userDAO.query(user);
    }

    public User queryById(String id) {
        return userDAO.queryById(id);
    }

    public List<User> queryByPager(Pager pager) {
        return userDAO.queryByPager(pager);
    }

    public int count(User user) {
        return userDAO.count(user);
    }

    public int inactive(String id) {
        return userDAO.inactive(id);
    }

    public int active(String id) {
        return userDAO.active(id);
    }

    //  分页查询全部，不分状态
    @Override
    public List<User> queryByPagerAll(Pager pager) {
        return userDAO.queryByPagerAll(pager);
    }

    @Override
    public User queryByEmail(String email) {
        return userDAO.queryByEmail(email);
    }

    public List<User> queryByPagerDisable(Pager pager) {
        return userDAO.queryByPagerDisable(pager);
    }

    public int countByDisable(User user) {
        return userDAO.countByDisable(user);
    }

    public List<User> blurredQuery(Pager pager, User user) {
        return null;
    }

    public int countByBlurred(User user,User user1) {
        return countByBlurred(user, user1);
    }

    public List<User> queryEmail(String ids) {
        return userDAO.queryEmail(ids);
    }


    @Override
    public User queryByPhone(String userPhone) {
        return userDAO.queryByPhone(userPhone);
    }

    @Override
    public int updIcon(String userId, String userIcon) {
        return userDAO.updIcon(userId, userIcon);
    }

    @Override
    public User queryUser(String email) {
        return userDAO.queryUser(email);
    }

    @Override
    public Set<String> queryPermissions(String email) {
        return userDAO.queryPermissions(email);
    }

    @Override
    public Set<String> queryRoles(String email) {
        return userDAO.queryRoles(email);
    }

    /**
     * 根据roleName查询人员基本信息
     * @param roleName
     * @return
     */
    @Override
    public List<User> queryByRoleName(String roleName) {
        return userDAO.queryByRoleName(roleName);
    }

    /**
     * 根据id查询 只查询t_user表
     * @param id
     * @return
     */
    public User queryInfoById(String id) {
        return userDAO.queryInfoById(id);
    }

    public void updatePwd(User user) {
        userDAO.updatePwd(user);
    }

    /**
     * 计数可用的
     * @return
     */
    public int countOK(User user) {
        return userDAO.countOK(user);
    }

    /**
     * 计数不可用的
     * @return
     */
    public int countNO(User user) {
        return userDAO.countNO(user);
    }


    /**
     * 根据状态计数
     * @return
     */
    public int countStatus(User user, String status) {
        return userDAO.countStatus(user, status);
    }

    /**
     * 根据状态计数 系统管理员
     * @return
     */
    public int countSystemAdminStatus(User user, String status) {
        return userDAO.countSystemAdminStatus(user, status);
    }

    /**
     * 状态计数全部
     * @return
     */
    public int countAll(User user) {
        return userDAO.countAll(user);
    }

    @Override
    public int queryPhoneByOne(String userPhone) {
        return userDAO.queryPhoneByOne(userPhone);
    }

    /**
     * 查询此手机号是否已存在此手机
     */
    public int queryIsPhoneByOne(String phone, String userId) {
        return userDAO.queryIsPhoneByOne(phone, userId);
    }

    /**
     * 查询此email是否已存在此email
     */
    public int queryIsEmailByOne(String userEmail, String userId) {
        return userDAO.queryIsEmailByOne(userEmail, userId);
    }

    /**
     * 查询此identity是否已存在此identity
     */
    public int queryIsIdentityByOne(String userIdentity, String userId) {
        return userDAO.queryIsIdentityByOne(userIdentity, userId);
    }

    /**
     * 模糊查询
     */
    public List<User> queryByPagerLike(Pager pager) {
        return userDAO.queryByPagerLike(pager);
    }

    @Override
    public List<User> queryByCompanyId(String companyId) {
        return userDAO.queryByCompanyId(companyId);
    }

    //  根据状态查询该状态下的所有系统管理员
    public List<User> querySystemAdminByPager(Pager pager, String status) {
        return userDAO.querySystemAdminByPager(pager, status);
    }

    //  分页查询全部，不分状态
    public List<User> queryAllByPager(Pager pager) {
        return userDAO.queryAllByPager(pager);
    }

    @Override
    public int queryPhoneIsNull(String rtphone) {
        return userDAO.queryPhoneIsNull(rtphone);
    }

    @Override
    public int queryEmailIsNull(String eamil) {
        return userDAO.queryEmailIsNull(eamil);
    }

    @Override
    public void updatePwdByEmail(String pwd, String email) {
        userDAO.updatePwdByEmail(pwd, email);
    }

    @Override
    public void updatePwdByPhone(String pwd, String phone) {
        userDAO.updatePwdByPhone(pwd, phone);
    }

    //   修改个人资料
    public int updateSelfManage(User user) {
        return userDAO.updateSelfManage(user);
    }

    public User queryById(User user) {
        return userDAO.queryById(user);
    }

    //  查询车主用户信息
    public List<User> queryCarByRoleName(Pager pager) {
        return userDAO.queryCarByRoleName(pager);
    }

    //  车主记录数
    public int countCar() {
        return userDAO.countCar();
    }

    //  查询状态为可用的车主用户信息
    public List<User> queryCarByOk(Pager pager) {
        return userDAO.queryCarByOk(pager);
    }

    //  查询状态为不可用的车主用户信息
    public List<User> queryCarByNo(Pager pager) {
        return userDAO.queryCarByNo(pager);
    }

    //  车主记录数 可用
    public int countCarByOk(User user) {
        return userDAO.countCarByOk(user);
    }

    //  车主记录数 不可用
    public int countCarByNo(User user) {
        return userDAO.countNO(user);
    }

    //  模糊分页查询，不分状态     只是角色为车主的
    public List<User> queryCarByPagerLike(Pager pager) {
        return userDAO.queryCarByPagerLike(pager);
    }

    //  车主记录数 可用    模糊查询的分页记录数
    public int countAllCar(User user) {
        return userDAO.countAllCar(user);
    }
}