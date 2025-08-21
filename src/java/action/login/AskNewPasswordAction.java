/*
 * @(#)AskNewPasswordAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login;


import service.login.AskNewPasswordService;
import infrastructure.support.action.post.ActionPostCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonPasswordAskNewPassword
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AskNewPasswordAction extends ActionPostCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String userType;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action()  throws Exception{        
        return new AskNewPasswordService().service(this, rut, userType);
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
