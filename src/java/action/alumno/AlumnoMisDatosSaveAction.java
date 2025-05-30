/*
 * @(#)AlumnoMisDatosSaveAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import static service.alumno.AlumnoMisDatosSaveService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoMisDatosSaveMisDatos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoMisDatosSaveAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer comuna;
    private String direccion;
    private String email;
    private String emailLaboral;
    private String fono;
    private Integer estadoCivil;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return service(this, getGenericSession(), this.email,  this.emailLaboral, this.direccion,
                this.comuna, this.fono, this.estadoCivil);
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

    public void setEmailLaboral(String emailLaboral) {
        this.emailLaboral = emailLaboral;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
