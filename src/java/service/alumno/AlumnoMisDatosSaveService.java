/*
 * @(#)AlumnoMisDatosSaveService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.emailNormalizado;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoMisDatosSaveService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param email
     * @param emailLaboral
     * @param direccion
     * @param comuna
     * @param fono
     * @param estadoCivil
     * @return Action status.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String email, String emailLaboral, String direccion, Integer comuna, String fono, Integer estadoCivil) {
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).setMisDatos(genericSession.getRut(), emailNormalizado(email), emailNormalizado(emailLaboral), direccion, comuna, fono, estadoCivil);
        commitTransaction();
        action.addActionMessage(action.getText("message.datos.grabados"));
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
