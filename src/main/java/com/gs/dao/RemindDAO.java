package com.gs.dao;

import com.gs.bean.Remind;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
@Repository
public interface RemindDAO {
    /**
     * 添加提醒
     */
    public int addRemind(Remind remind);

    /**
     * 删除提醒
     */
    public void deleteRemind(String remindId);

    /**
     * 更新提醒
     */
    public int updateRemind(Remind remind);

    /**
     * 查询提醒
     */
    public List<Remind> selectRemind();
}
