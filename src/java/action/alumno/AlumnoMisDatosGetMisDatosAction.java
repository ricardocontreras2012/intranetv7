/*
 * @(#)AlumnoMisDatosGetMisDatosAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.alumno;

import service.alumno.AlumnoMisDatosGetMisDatosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL AlumnoMisDatosGetMisDatos
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AlumnoMisDatosGetMisDatosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        return new AlumnoMisDatosGetMisDatosService().service(getGenericSession(), getKey());
    }
}
