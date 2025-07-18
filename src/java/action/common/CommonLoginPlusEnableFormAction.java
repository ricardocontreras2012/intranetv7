/*
 * @(#)CommonLoginPlusEnableFormAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import infrastructure.support.action.common.ActionCommonSupport;
import java.util.Map;
import service.common.CommonLoginPlusEnableFormService;


/**
 * Procesa el action mapeado del request a la URL CommonLoginPlusEnableForm
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonLoginPlusEnableFormAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private Map<String, String> userTypeMap;
    private String err;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        userTypeMap = new CommonLoginPlusEnableFormService().service();

        return SUCCESS;
    }

    public Map<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }    
}
