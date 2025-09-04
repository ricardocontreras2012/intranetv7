/*
 * @(#)GetMallaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import infrastructure.support.MallaJsonSupport;
import service.alumno.GetMallaJsonService;
import infrastructure.support.action.common.ActionCommonSupport;
import java.util.List;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetMalla
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetMallaJsonAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    
    private List<MallaJsonSupport> malla;  // Cambio: Ahora es una lista de objetos, no un String

    public List<MallaJsonSupport> getMalla() {
        return malla;
    }

    public void setMalla(List<MallaJsonSupport> malla) {
        this.malla = malla;
    }

    

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetMallaJsonService().service(getGenericSession(), getKey(), this);
    }
    
}
