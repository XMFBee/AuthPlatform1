package com.gs.dao;

import com.gs.bean.Company;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 15:57:26
*@des 公司表dao
*/
@Repository
public interface CompanyDAO extends BaseDAO<String, Company>{

    /**
     * 模糊查询
     */
    public List<Company> blurredQuery(@Param("pager")Pager pager, @Param("company")Company company);

    /**
     * 模糊查询的记录数
     */
    public int countByBlurred(@Param("company")Company company, @Param("user")User user);

    public int updLogo(@Param("companyId")String userId,@Param("companyLogo")String companyLogo);

    /**
     * 查询此公司名称是否已存在
     */
    public int querycompanyName(@Param("companyName")String companyName,@Param("companyId")String companyId);

    /**
     * 查询此公司联系方式是否已存在
     */
    public int querycompanyPricipalphone(@Param("companyPricipalphone")String companyPricipalphone,@Param("companyId")String companyId);

    /**
     * 查询此公司联系方式是否已存在
     */
    public int querycompanyWebsite(@Param("companyWebsite")String companyWebsite,@Param("companyId")String companyId);

    /**
     * 查询此公司联系方式是否已存在
     */
    public Company queryPhone(@Param("companyPricipalphone")String companyPricipalphone);

    /**
     *查询公司需要显示的信息
     */
    public List<Company> queryByCompanyInfo();

}