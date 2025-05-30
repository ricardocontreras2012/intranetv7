/*
 * @(#)AlumnoSolicitudAddSolicitudInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ActividadTeletrabajo;
import static java.lang.Integer.parseInt;
import java.util.Date;
import java.util.Map;
import domain.repository.TareaActividadTeletrabajoPersistence;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class TeleTrabajoSaveTareaService {

    /**
     * Method description
     *
     * @param action
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param parameters
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, TeleTrabajoSession teleSession, Map<String, String[]> parameters, String key) {

        String field;
        String otro;

        Date fecha = teleSession.getActividadTeletrabajo().getId().getAtelFecha();
        int rut = teleSession.getActividadTeletrabajo().getId().getAtelRut();

        TareaActividadTeletrabajoPersistence itemActividadTeletrabajoPersistence = ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT");

        teleSession.setTareaList(itemActividadTeletrabajoPersistence.findByActividad(rut, fecha));

        beginTransaction("TT");
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            field = entry.getKey();

            if (field.startsWith("otro_")) {
                int pos = field.lastIndexOf('_');
                int row = parseInt(field.substring(pos + 1));
                otro = parameters.get("otro_" + row)[0];

                ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").insertTarea(rut, fecha,otro);
            }
        }
        commitTransaction();
        ActividadTeletrabajo act = teleSession.getActividadTeletrabajo();
        teleSession.setTareaList(ContextUtil.getDAO().getTareaActividadTeletrabajoPersistence("TT").find(act.getId().getAtelRut(), act.getId().getAtelFecha()));       
        
        return SUCCESS;
    }
}
