/*
 * @(#)OficinaCurricularActaRecepcionarActasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ActaCalificacion;
import java.util.Map;
import domain.repository.ActaCalificacionPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularActaRecepcionarActasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ActaCalificacion acta;
        ActaCalificacionPersistence actaPersistence
                = ContextUtil.getDAO().getActaCalificacionPersistence(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());

        for (int i = 0; i < ws.getActas().size(); i++) {
            if (parameters.get("ck_" + i) != null) {
                acta = ws.getActas().get(i);
                actaPersistence.recepcionarActa(acta.getId().getAcalAgno(), acta.getId().getAcalSem(), acta.getId().getAcalFolio());
            }
        }

        commitTransaction();
        return SUCCESS;
    }
}
