/*
 * @(#)CommonGetKeyProfesorAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonGetKeyProfesorService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * @author Ricardo Contreras S.
 */
public final class CommonGetKeyProfesorAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String keyProf;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        keyProf = new CommonGetKeyProfesorService().service(getGenericSession());
              
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getKeyProf() {
        return keyProf;
    }

    /**
     * Method description
     *
     * @param keyProf
     */
    public void setKeyProf(String keyProf) {
        this.keyProf = keyProf;
    }
}
