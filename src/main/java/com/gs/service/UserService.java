package com.gs.service;

import com.gs.bean.User;
import com.gs.common.bean.Pager;

import java.util.List;
import java.util.Set;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des
 * @since 2017-04-17 16:12:02
 */
public interface UserService extends BaseService<String, User> {

    //  分页查询全部，不分状态
    public List<User> queryByPagerAll(Pager pr);

    //  分页查询被禁用的记录
    public List<User> queryByPagerDisable(Pager pager);

    public List<User> queryEmail(String ids);

    //根据用户的email查询用户所拥有的权限。
    public Set<String> queryPermissions(String email);

    //根据用户email查询用户所拥有的角色
    public Set<String> queryRoles(String email);

    /**
     * 根据用户输入的邮箱或者手机号判断成功时, 查询到此用户所有信息
     */
    public User queryUser(String email);

    /**
     * 根据邮箱查询用户对应的id
     *
     * @param email
     * @return
     */
    public User queryByEmail(String email);


    public User queryByPhone(String userPhone);

    public int updIcon(String userId,String userIcon);

    /**
     * 根据roleName查询人员基本信息
     * @param roleName
     * @return
     */
    public List<User> queryByRoleName(String roleName);

    /**
     * 根据id查询 只查询t_user表
     * @param id
     * @return
     */
    public User queryInfoById(String id);

    public void updatePwd(User user);

    /**
     * 计数可用的
     * @return
     */
    public int countOK(User user);

    /**
     * 计数不可用的
     * @return
     */
    public int countNO(User user);

    /**
     * 根据状态计数
     * @return
     */
    public int countStatus(User user, String status);

    /**
     * 根据状态计数 系统管理员
     * @return
     */
    public int countSystemAdminStatus(User user, String status);

    /**
     * 状态计数全部
     * @return
     */
    public int countAll(User user);

    /**
     * 查询此手机号是否已存在此手机
     */
    public int queryPhoneByOne(String phone);

    /**
     * 查询此手机号是否已存在此手机
     */
    public int queryIsPhoneByOne(String phone, String userId);

    /**
     * 查询此email是否已存在此email
     */
    public int queryIsEmailByOne(String userEmail, String userId);

    /**
     * 查询此identity是否已存在此identity
     */
    public int queryIsIdentityByOne(String userIdentity, String userId);

    /**
     * 模糊查询
     */
    public List<User> queryByPagerLike(Pager pager);

    /**
     * 根据companyId查询员工的展示信息
     * @param companyId
     * @return
     */
    public List<User> queryByCompanyId(String companyId);

    //  根据状态查询该状态下的所有系统管理员
    public List<User> querySystemAdminByPager(Pager pager, String status);

    //  分页查询全部，不分状态
    public List<User> queryAllByPager(Pager pager);

    // 查询手机和邮箱绑定账号是否存在
    public int queryPhoneIsNull(String rtphone);

    public int queryEmailIsNull(String eamil);

    // 根据邮箱和手机号修改密码
    public void updatePwdByEmail(String pwd, String email);

    public void updatePwdByPhone(String pwd, String phone);

    //   修改个人资料
    public int updateSelfManage(User user);

    public User queryById(User user);

    //  查询车主用户信息
    public List<User> queryCarByRoleName(Pager pager);

    //  车主记录数
    public int countCar();

    //  查询状态为可用的车主用户信息
    public List<User> queryCarByOk(Pager pager);

    //  查询状态为不可用的车主用户信息
    public List<User> queryCarByNo(Pager pager);

    //  车主记录数 可用
    public int countCarByOk(User user);

    //  车主记录数 不可用
    public int countCarByNo(User user);

    //  模糊分页查询，不分状态     只是角色为车主的
    public List<User> queryCarByPagerLike(Pager pager);

    //  车主记录数 可用    模糊查询的分页记录数
    public int countAllCar(User user);
}