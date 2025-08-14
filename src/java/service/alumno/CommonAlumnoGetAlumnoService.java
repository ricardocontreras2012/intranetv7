/*
 * @(#)CommonAlumnoGetAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Alumno;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonAlumnoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetAlumnoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        if (!ws.getAlumnoList().isEmpty()) {
            Alumno alumno = ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).findFull(
                            ws.getAlumnoList().get(pos).getAluRut());

            ws.setAlumno(alumno);
            ws.setNombre(CommonAlumnoUtil.getNombreSocial(alumno));
        }

        return SUCCESS;
    }
}
