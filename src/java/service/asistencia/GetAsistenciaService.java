/*
 * @(#)GetAsistenciaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.asistencia;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AsistenciaAlumno;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetAsistenciaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param pos Numero del registro seleccionado en el formulario.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key, Integer pos) {
        WorkSession ws = genericSession.getWorkSession(key);
        AsistenciaAlumno asistenciaAlumno = ws.getAsistenciaAlumnoList().get(pos);

        ws.setAsistenciaAlumno(asistenciaAlumno);
        ws.setAsistenciaAlumnoNominaList(ContextUtil.getDAO().getAsistenciaAlumnoNominaRepository(ActionUtil.getDBUser()).find(asistenciaAlumno.getAsaCorrel()));

        return SUCCESS;
    }
}
