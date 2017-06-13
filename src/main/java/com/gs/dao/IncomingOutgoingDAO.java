package com.gs.dao;

import com.gs.bean.*;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 由CSWangBin技术支持
 *
 * @author CSWangBin
 * @des 收支表dao
 * @since 2017-04-17 15:59:05
 */
@Repository
public interface IncomingOutgoingDAO extends BaseDAO<String, IncomingOutgoing> {

    /**
     * 分页查询被禁用的记录
     */
    public List<IncomingOutgoing> queryByPagerDisable(Pager pager);

    /**
     * 分页查询被禁用的记录
     */
    public int countByDisable();

    /*
  * 批量添加
  * */
    public void addInsert(List<IncomingOutgoing> incomingOutgoings);

    /**
     * 根据时间段去查找
     */
    public List<IncomingOutgoing> queryByDate(@Param("inOutType") String inOutType, @Param("companyId") String companyId);


    /**
     * 模糊查询收入支出
     *
     * @param pager
     * @return
     */
    public List<IncomingOutgoing> blurredQuery(@Param("pager") Pager pager, @Param("incomingOutgoing") IncomingOutgoing incomingOutgoing);


    /**
     * 模糊查询收入支出个数
     *
     * @return
     */
    public int countByBlurred(@Param("user") User user, @Param("incomingOutgoing") IncomingOutgoing incomingOutgoing);


    /*根据年，月，季度，周，日查询所有收支记录*/
    public List<IncomingOutgoing> queryByCondition(@Param("start") String start, @Param("end") String end, @Param("inOutType") String inOutType, @Param("companyId") String companyId, @Param("type") String type);

    public List<IncomingOutgoing> queryByCompanyIdForInType(String companyId);

    public List<IncomingOutgoing> queryByCompanyIdForOutType(String companyId);
}