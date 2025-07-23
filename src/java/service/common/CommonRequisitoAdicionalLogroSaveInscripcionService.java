/*
 * @(#)CommonRequisitoAdicionalLogroSaveInscripcionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.InscripcionAdicionalLogro;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.DateUtil.stringToDate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRequisitoAdicionalLogroSaveInscripcionService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param tema
     * @param fecha
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, String tema, String fecha, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        InscripcionAdicionalLogro inscripcionRequisitoAdicionalLogro = ws.getInscripcionAdicionalLogro();

        inscripcionRequisitoAdicionalLogro.setIalGlosa(tema);

        if ((fecha != null) && !fecha.isEmpty()) {
            inscripcionRequisitoAdicionalLogro.setIalFecha(stringToDate(fecha));
        } else {
            inscripcionRequisitoAdicionalLogro.setIalFecha(getSysdate());
        }

        inscripcionRequisitoAdicionalLogro.setIalFechaSys(getSysdate());
        inscripcionRequisitoAdicionalLogro.setIalReali("C");
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getInscripcionAdicionalLogroRepository(ActionUtil.getDBUser()).update(inscripcionRequisitoAdicionalLogro);
        commitTransaction();

        return SUCCESS;
    }
}
