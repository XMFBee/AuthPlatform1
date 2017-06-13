package com.gs.dao;

import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
*@author 小蜜蜂
*@since 2017-04-17 16:12:01
*@des 
*/
@Repository
public interface UserDAO extends BaseDAO<String, User>{

//  分页查询全部，不分状态
    public List<User> queryByPagerAll(Pager pager);

    //  分页查询全部，不分状态
    public List<User> queryAllByPager(Pager pager);

    //  分页查询被禁用的记录
    public List<User> queryByPagerDisable(Pager pager);

//  根据状态查询该状态下的所有系统管理员
    public List<User> querySystemAdminByPager(Pager pager, String status);

    /**
     * 根据维修保养记录查询到用户的email发送邮件提醒车主进行提车
     */
    public List<User> queryEmail(@Param("ids")String ids);

    /**
     *根据邮箱查询用户对应的id
     * @param email
     * @return
     */
    public User queryByEmail(String email);


    public User queryByPhone(String userPhone);

    public int updIcon(@Param("userId")String userId,@Param("userIcon")String userIcon);

    //根据用户的email查询用户所拥有的权限。
    public Set<String> queryPermissions(String email);

    //根据用户email查询用户所拥有的角色
    public Set<String> queryRoles(String email);
    /**
     * 根据用户输入的邮箱或者手机号判断成功时, 查询到此用户所有信息
     */
    public User queryUser(String email);

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
     * 查询此手机号是否已存在此手机 用于注册验证
     */
    public int queryPhoneByOne(String phone);

    /**
     * 查询此手机号是否已存在此手机   后台增，改验证
     */
    public int queryIsPhoneByOne(@Param("userPhone") String phone, @Param("userId") String userId);

    /**
     * 查询此email是否已存在此email  后台增，改验证
     */
    public int queryIsEmailByOne(@Param("userEmail") String userEmail, @Param("userId") String userId);

    /**
     * 查询此identity是否已存在此identity
     */
    public int queryIsIdentityByOne(@Param("userIdentity") String userIdentity, @Param("userId") String userId);

    /**
     * 模糊查询
     */
    public List<User> queryByPagerLike(Pager pager);

    /**
     * 根据companyId查询员工的展示信息
     */
    public List<User> queryByCompanyId(String companyId);

    // 查询手机和邮箱绑定账号是否存在
    public int queryPhoneIsNull(String phone);

    public int queryEmailIsNull(String email);

    // 根据邮箱和手机号修改密码
    public void updatePwdByEmail(@Param("pwd") String pwd, @Param("email") String email);

    public void updatePwdByPhone(@Param("pwd")String pwd, @Param("phone")String phone);

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