/*
 * @(#)CommonSolicitudViewSolicitudService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSolicitudViewSolicitudService {

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

        Solicitud sol = ws.getSolicitudList().get(pos);
        ws.setSolicitud(sol);
        String retValue = null;
        Integer folio = sol.getSolFolio();
        String user = ActionUtil.getDBUser();

        sol.setSolicitudAttachList(ContextUtil.getDAO().getSolicitudAttachRepository(user).find(sol));
        
        switch (sol.getTsolicitud().getTsolTipo()) {
            case "SIT":
                retValue = "situacion";
                break;
            case "INS":
                retValue = "inscripcion";
                ws.setSolicitudInscripcionList(ContextUtil.getDAO().getSolicitudInscripcionRepository(user).getSolicitud(folio));
                break;
            case "PRA":
                ws.setPractica(ContextUtil.getDAO().getPracticaRepository(user).find(folio));
                retValue = "practica";
                break;
            case "JUS":
                ws.setJustificativoList(ContextUtil.getDAO().getSolicitudJustificativoRepository(user).getSolicitud(folio));
                retValue = "justificativo";
                break;
            case "MAT":                
                retValue = "matricula";
                break;
            case "EXP":
                ws.setExpedienteLogro(ContextUtil.getDAO().getExpedienteLogroRepository(user).findBySolicitud(ws.getAluCar().getAlumno().getAluRut(), folio));
                ws.setEstadoDocExpList(ContextUtil.getDAO().getEstadoDocExpRepository(user).find(ws.getExpedienteLogro().getId()));
                retValue = "expediente";
                break;
        }

        ws.setLogSolicitudList(ContextUtil.getDAO().getLogSolicitudRepository(user).find(folio));
        LogUtil.setLog(genericSession.getRut(), folio);

        return retValue;
    }
}
