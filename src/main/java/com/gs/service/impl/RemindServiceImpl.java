package com.gs.service.impl;

import com.gs.bean.Remind;
import com.gs.dao.RemindDAO;
import com.gs.service.RemindService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */
@Service
public class RemindServiceImpl implements RemindService{

    @Resource
    private RemindDAO remindDAO;
    @Override
    public int addRemind(Remind remind) {
        return remindDAO.addRemind(remind);
    }

    @Override
    public void deleteRemind(String remindId) {
        remindDAO.deleteRemind(remindId);
    }

    @Override
    public int updateRemind(Remind remind) {
        return remindDAO.updateRemind(remind);
    }

    @Override
    public List<Remind> selectRemind() {
        return remindDAO.selectRemind();
    }
}
