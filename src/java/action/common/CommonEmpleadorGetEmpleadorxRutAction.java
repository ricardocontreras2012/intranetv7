/*
 * @(#)CommonEmpleadorGetEmpleadorxRutAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import domain.model.Empleador;
import service.common.CommonEmpleadorGetEmpleadorxRutService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonEmpleadorGetEmpleadorxRut
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonEmpleadorGetEmpleadorxRutAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private Empleador empleador;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        empleador =  new CommonEmpleadorGetEmpleadorxRutService().service(getGenericSession(), getKey(), rut);
        
        return SUCCESS;
    }

    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public Empleador getEmpleador() {
        return empleador;
    }    
}
