/*
 * @(#)AlumnoLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login.alumno;

import service.login.alumno.AlumnoLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String passwd;

    /**
     * Method description
     *
     * @return
     */
    @Override
    public String action() {                
        return new AlumnoLoginService().service(this, getSesion(), rut, passwd, getKey());
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
