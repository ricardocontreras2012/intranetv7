/*
 * @(#)OficinaCurricularSolicitudRemoveDocumentoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.SolicitudAttach;
import java.util.List;
import java.util.Map;
import domain.repository.SolicitudAttachPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularSolicitudRemoveDocumentoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();

        if ("OC".equals(genericSession.getUserType()) || "RC".equals(genericSession.getUserType())) {
            SolicitudAttachPersistence solicitudDocumentoPersistence
                    = ContextUtil.getDAO().getSolicitudAttachPersistence(user);
            List<SolicitudAttach> solicitudDocumentoList = ws.getSolicitud().getSolicitudAttachList();

            beginTransaction(user);

            for (int i = 0; i < solicitudDocumentoList.size(); i++) {
                if (parameters.get("ck_" + i) != null) {
                    SolicitudAttach attach = solicitudDocumentoList.get(i);                    
                    LogUtil.setLog(genericSession.getRut(), attach.getSolaAttachFile());
                    solicitudDocumentoPersistence.makeTransient(attach);
                }
            }

            commitTransaction();
        }

        return SUCCESS;
    }
}
