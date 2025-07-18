/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Ricardo
 */
public class AlumnoPracticaDeleteFileService {

    public String service(GenericSession genericSession, Integer attach, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        Solicitud solicitud = ws.getSolicitud();
        String user = ActionUtil.getDBUser();
        
        Integer doc = solicitud.getSolicitudAttachList().get(attach).getId().getSolaCorrelAttach();
        beginTransaction(user);
        ContextUtil.getDAO().getSolicitudAttachPersistence(user).deleteAttach(solicitud.getSolFolio(), doc);
        commitTransaction();
        solicitud.setSolicitudAttachList(ContextUtil.getDAO().getSolicitudAttachPersistence(user).find(solicitud));

        return SUCCESS;
    }
}
