package com.gs.service.impl;

import com.gs.bean.MaterialList;
import com.gs.bean.User;
import com.gs.bean.view.MaterialURTemp;
import com.gs.bean.view.MaterialView;
import com.gs.common.bean.Pager;
import com.gs.dao.MaterialListDAO;
import com.gs.service.MaterialListService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.history.HistoricVariableInstanceQuery;
import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:06:49
*@des 物料清单Service实现
*/
@Service
public class MaterialListServiceImpl implements MaterialListService {

	@Resource
	private MaterialListDAO materialListDAO;
	@Resource
	private HistoryService historyService;

	public int insert(MaterialList materialList) { return materialListDAO.insert(materialList); }
	public int batchInsert(List<MaterialList> list) { return materialListDAO.batchInsert(list); }
	public int delete(MaterialList materialList) { return materialListDAO.delete(materialList); }
	public int deleteById(String id) {
        return materialListDAO.deleteById(id);
    }
	public int batchDelete(List<MaterialList> list) { return materialListDAO.batchDelete(list); }
	public int update(MaterialList materialList) { return materialListDAO.update(materialList); }
	public int batchUpdate(List<MaterialList> list) { return materialListDAO.batchUpdate(list); }
	public List<MaterialList> queryAll(User user) { return materialListDAO.queryAll(user); }

	@Override
	public List<MaterialList> queryAll(String status) {
		return materialListDAO.queryAll(status);
	}

	public List<MaterialList> queryByStatus(String status) { return materialListDAO.queryAll(status); }
	public MaterialList query(MaterialList materialList) { return materialListDAO.query(materialList); }
	public MaterialList queryById(String id) { return materialListDAO.queryById(id); }
	public List<MaterialList> queryByPager(Pager pager) { return materialListDAO.queryByPager(pager); }
	public int count(User user) { return materialListDAO.count(user); }
	public int inactive(String id) { return materialListDAO.inactive(id); }
	public int active(String id) { return materialListDAO.active(id); }

	public List<MaterialList> queryByPagerDisable(Pager pager) {
		return materialListDAO.queryByPagerDisable(pager);
	}

	public int countByDisable(User user) {
		return materialListDAO.countByDisable(user);
	}

	public List<MaterialList> blurredQuery(Pager pager, MaterialList materialList) {
		return null;
	}

	@Override
	public int countByBlurred(MaterialList materialList, User user) {
		return 0;
	}

	@Override
	public List<MaterialView> queryByPager(String userId, Pager pager) {
		return materialListDAO.queryByPager(userId,pager);
	}

	@Override
	public int count(String userId) {
		return materialListDAO.count(userId);
	}

	@Override
	public List<MaterialList> recordAccsByPager(String recordId, User user, Pager pager) {
		Map paramMap = new HashMap();
		paramMap.put("recordId", recordId);
		paramMap.put("user", user);
		paramMap.put("pager", pager);
		return materialListDAO.recordAccsByPager(paramMap);
	}

	@Override
	public int countRecordAccs(String recordId, User user) {
		Map paramMap = new HashMap();
		paramMap.put("recordId", recordId);
		paramMap.put("user", user);
		return materialListDAO.countRecordAccs(paramMap);
	}

	@Override
	public void insertList(List<MaterialList> materialLists) {
		materialListDAO.insertList(materialLists);
	}

	@Override
	public MaterialURTemp queryFlowVarsByRecordId(String recordId, String accId) {
		final String countKey = "accCount";
		Map map = new HashMap();
		map.put("flowKey", "material");
		map.put("outVarName", "recordId");
		map.put("outVarValue", recordId);
		map.put("inVarName", "accId");
		map.put("inVarValue", accId);
		List<MaterialURTemp> mats = materialListDAO.queryFlowVarsByRecordId(map);
		MaterialURTemp mater  = null;
		if( mats!=null && mats.size() >0 ){
			mater = mats.get(0);
			HistoricVariableInstanceQuery query = historyService.createHistoricVariableInstanceQuery();
			mater.setVarsMap(list2Map(query.processInstanceId(mater.getProcessInstanceId()).list()));
			Map materVarMap = mater.getVarsMap();
			List accCounts = new ArrayList();
			accCounts.add(materVarMap.get(countKey));
			materVarMap.put(countKey, accCounts);
			for(int i = 1,len = mats.size(); i<len; i++) {
				MaterialURTemp mat = mats.get(i);
				List<HistoricVariableInstance> vars = query.processInstanceId(mat.getProcessInstanceId()).list();
				Map tempMap = list2Map(vars);
				accCounts.add(tempMap.get(countKey));
			}
		}
		return mater;
	}

	private Map list2Map(List<HistoricVariableInstance> vars) {
		Map map = new HashMap();
		for(HistoricVariableInstance var : vars) {
			map.put(var.getVariableName(), ((HistoricVariableInstanceEntity) var).getTextValue());
		}
		return map;
	}
}