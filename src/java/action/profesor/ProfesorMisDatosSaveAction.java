/*
 * @(#)ProfesorMisDatosSaveAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.misdatos.profesor.ProfesorMisDatosSaveService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL ProfesorMisDatosSave
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorMisDatosSaveAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer comuna;
    private String direccion;
    private String email;
    private String emailUsach;
    private String fechaNac;
    private String fono;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorMisDatosSaveService().service(this, getGenericSession(), Manager.getProfesorSession(sesion), emailUsach, email, fechaNac, direccion, comuna, fono, getKey());
    }

    public void setComuna(Integer comuna) {
        this.comuna = comuna;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailUsach(String emailUsach) {
        this.emailUsach = emailUsach;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }
}
