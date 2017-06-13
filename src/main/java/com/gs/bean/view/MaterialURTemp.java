package com.gs.bean.view;

import com.gs.bean.MaterialUse;
import com.gs.bean.WorkInfo;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

/**
 * 继承自Use,但是多添加一个字段
 *
 * @author Administrator
 * @create 2017-04-25 20:14
 */
public class MaterialURTemp extends MaterialUse {
    private String flag;
    private WorkInfo workInfo;

    private String processInstanceId;


    private String reqMsg;
    private String respMsg;
    private String taskId;
    private HistoricTaskInstanceEntity taskTemp;
    private HistoricProcessInstanceEntity processInstance;
    private Map varsMap;

    public Map getVarsMap() {
        return varsMap;
    }

    public void setVarsMap(Map varsMap) {
        this.varsMap = varsMap;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public WorkInfo getWorkInfo() {
        return workInfo;
    }

    public void setWorkInfo(WorkInfo workInfo) {
        this.workInfo = workInfo;
    }

    public String getReqMsg() {
        return reqMsg;
    }

    public void setReqMsg(String reqMsg) {
        this.reqMsg = reqMsg;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }


    public HistoricProcessInstanceEntity getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(HistoricProcessInstanceEntity processInstance) {
        this.processInstance = processInstance;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public HistoricTaskInstanceEntity getTaskTemp() {
        return taskTemp;
    }


    public void setTaskTemp(HistoricTaskInstanceEntity taskTemp) {
        this.taskTemp = taskTemp;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }


}
