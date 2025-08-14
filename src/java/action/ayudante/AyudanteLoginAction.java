/*
 * @(#)AyudanteLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.ayudante;

import service.login.ayudante.AyudanteLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AyudanteLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AyudanteLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String passwd;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new AyudanteLoginService().service(this, getSesion(), rut, passwd, getKey());
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
