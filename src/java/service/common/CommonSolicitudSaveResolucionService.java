/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonSolicitudSaveResolucionService {

    /**
     *
     * @param genericSession
     * @param key
     * @param resolucion
     * @param respuesta
     * @return
     * @throws Exception
     */
    public String service(GenericSession genericSession, String key, String resolucion, String respuesta) throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);

        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getSolicitudPersistence(ActionUtil.getDBUser()). saveResolucion(ws.getSolicitud().getSolFolio(), resolucion, respuesta, 40);
        commitTransaction();
        return SUCCESS;
    }
}
