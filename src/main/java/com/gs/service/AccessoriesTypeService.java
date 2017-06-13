package com.gs.service;

import com.gs.bean.AccessoriesType;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des 配件分类service
 * @since 2017-04-17 15:49:18
 */
public interface AccessoriesTypeService extends BaseService<String, AccessoriesType> {
    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /**
     * 根据公司查看配件分类名称
     */
    public List<AccessoriesType> queryTypeName(String companyId);

    public int queryAccTypeNameOne(@Param("accTypeName") String accTypeName,@Param("accTypeId") String accTypeId);
}