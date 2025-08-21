/*
 * @(#)EgresadoLoginAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.login.egresado;

import service.login.egresado.EgresadoLoginService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL EgresadoLogin
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class EgresadoLoginAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
            private Integer rut;
private String passwd;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        
        return new EgresadoLoginService().service(this, getSesion(), rut,
                passwd, getKey());
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
