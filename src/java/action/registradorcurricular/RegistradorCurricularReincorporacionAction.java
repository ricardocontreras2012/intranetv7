/*
 * @(#)RegistradorCurricularReincorporacionAction.java
 *
 * Creado por: Ricardo Contreras S.
 * Fecha Actualizacion: 17/07/2014
 *
 * License agreement: Uso exclusivo por FAE
 * Copyright (c) 2025 FAE-USACH
 */
package action.registradorcurricular;

import static service.registradorcurricular.RegistradorCurricularReincorporacionService.service;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularReincorporacionAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
        service(getGenericSession(), tipo, getKey());
        return SUCCESS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
