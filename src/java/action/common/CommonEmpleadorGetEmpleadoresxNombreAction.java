/*
 * @(#)CommonEmpleadorGetEmpleadoresxNombreAction
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonEmpleadorGetEmpleadoresxNombreService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Virtual
 */
public class CommonEmpleadorGetEmpleadoresxNombreAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String nombre;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey(), nombre);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method description
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
