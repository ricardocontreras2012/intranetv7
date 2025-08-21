/*
 * @(#)LoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login;

import infrastructure.support.action.post.ActionPostCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;
import service.common.CommonLoginService;

/**
 * Procesa el action mapeado del request a la URL CommonLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class LoginAction extends ActionPostCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;
    private String passwd;
    private Integer rut;
    private String userType;
    private String key;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        this.key = getKeySession();
        actionCall = new CommonLoginService().service(userType);
        
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param password
     */
    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }

    /**
     * Method description
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getKey() {
        return key;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getRut() {
        return rut;
    }

    public String getUserType() {
        return userType;
    }
}
