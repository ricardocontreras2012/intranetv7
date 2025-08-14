/*
 * @(#)CommonAlumnoGetLogInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import domain.model.LogInscripcion;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonUtil;
import java.util.List;

/**
 * Class description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetLogInscripcionService {

    /**
     * Método de servicio que retorna la lista de LogInscripcion.
     *
     * @param genericSession Sesión de trabajo.
     * @param key Llave para acceder a los datos de la sesión.
     * @param sem Semestre (puede ser null).
     * @param agno Año (puede ser null).
     * @return Lista de LogInscripcion.
     */
    public List<LogInscripcion> service(GenericSession genericSession, String key, Integer sem, Integer agno) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<LogInscripcion> lista;

        if (sem != null && agno != null) {
            ws.setSemAct(sem);
            ws.setAgnoAct(agno);
            lista = ContextUtil.getDAO()
                    .getLogInscripcionRepository(ActionUtil.getDBUser())
                    .findAgnoSem(ws.getAluCar(), sem, agno);            
            
        } else {
            CommonUtil.setAgnoSemAct(ws);
            lista = ContextUtil.getDAO()
                    .getLogInscripcionRepository(ActionUtil.getDBUser())
                    .find(ws.getAluCar());
        }

        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());
        return lista;
    }
}
