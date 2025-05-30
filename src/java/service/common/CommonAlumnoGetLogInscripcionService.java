/*
 * @(#)CommonAlumnoGetLogInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonUtil;
import java.util.Optional;

/**
 * Class description
 *
 * @author Ricardo Contreras S and Javier Frez V.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetLogInscripcionService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @param sem
     * @param agno
     * @return Action status.
     */
    /*public static String service(GenericSession genericSession, String key, Integer sem, Integer agno) {
        WorkSession ws = genericSession.getWorkSession(key);
        if(sem != null && agno != null)
        {
            ws.setSemAct(sem);
            ws.setAgnoAct(agno);
            ws.setLogInscripcionList(ContextUtil.getDAO().getLogInscripcionPersistence(ActionUtil.getDBUser()).findAgnoSem(ws.getAluCar(), sem, agno));
        }
        else
        {
            CommonUtil.setAgnoSemAct(ws);
            ws.setLogInscripcionList(ContextUtil.getDAO().getLogInscripcionPersistence(ActionUtil.getDBUser()).find(ws.getAluCar()));
        }
        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());       
        return SUCCESS;
    }*/
    public static String service(GenericSession genericSession, String key, Integer sem, Integer agno) {
        WorkSession ws = genericSession.getWorkSession(key);

        Optional.ofNullable(sem).ifPresent(s
                -> Optional.ofNullable(agno).ifPresent(a -> { 
                    ws.setSemAct(s);
                    ws.setAgnoAct(a);
                    ws.setLogInscripcionList(
                            ContextUtil.getDAO()
                                    .getLogInscripcionPersistence(ActionUtil.getDBUser())
                                    .findAgnoSem(ws.getAluCar(), s, a)
                    );
                })
        );

        if (ws.getLogInscripcionList() == null) {          
            CommonUtil.setAgnoSemAct(ws);
            ws.setLogInscripcionList(
                    ContextUtil.getDAO()
                            .getLogInscripcionPersistence(ActionUtil.getDBUser())
                            .find(ws.getAluCar())
            );
        }

        LogUtil.setLog(genericSession.getRut(), ws.getAluCar().getId().getAcaRut());
        return SUCCESS;
    }

}
