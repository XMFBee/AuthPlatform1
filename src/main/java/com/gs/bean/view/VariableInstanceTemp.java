package com.gs.bean.view;

import org.activiti.engine.impl.persistence.entity.HistoricVariableInstanceEntity;

/**
 * Created by Administrator on 2017-05-08.
 */
public class VariableInstanceTemp extends HistoricVariableInstanceEntity {
    public Object getValue() {
        return getTextValue();
    }

}
