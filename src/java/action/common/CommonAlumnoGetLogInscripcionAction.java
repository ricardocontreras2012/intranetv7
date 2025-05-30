/*
 * @(#)CommonAlumnoGetLogInscripcionAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static service.common.CommonAlumnoGetLogInscripcionService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetLogInscripcion
 *
 * @author Ricardo Contreras S and Javier Frez V.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetLogInscripcionAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    
    private Integer sem;
    private Integer agno;
    

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {              
        return service(getGenericSession(), getKey(), this.sem, this.agno);
    }

    public void setSem(Integer sem) {
        this.sem = sem;
    }

    public void setAgno(Integer agno) {
        this.agno = agno;
    }
    
}
