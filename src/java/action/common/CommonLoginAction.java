/*
 * @(#)CommonLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static java.util.concurrent.TimeUnit.SECONDS;
import infrastructure.support.LoginSessionSupport;
import infrastructure.support.action.post.ActionPostCommonSupport;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL CommonLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonLoginAction extends ActionPostCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;
    private String password;
    private Integer rut;
    private String userType;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        String retValue = "relogin";
 
        actionCall = ContextUtil.getDAO().getLogActionPersistence(userType).getActionLogin(userType);

        LoginSessionSupport loginSessionSupport
                = (LoginSessionSupport) getSesion().get("loginSessionSupport");

        if (loginSessionSupport != null) {
            setKey(getKeySession());

            if (actionCall != null) {
                loginSessionSupport.setUserType(userType);
                loginSessionSupport.setRut(rut);
                loginSessionSupport.setPassword(password);
                retValue = SUCCESS;
            }
        } else {
            SECONDS.sleep(5);
            retValue = "denied";
        }    
        return retValue;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method description
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = FormatUtil.cleanPasswd(password);
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

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
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Method description
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
}
