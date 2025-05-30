/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.SecretariaSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionGetSolicitudesService {

    public static String service(GenericSession genericSession, SecretariaSession secreSession, String key) {
        secreSession.setConvalidaciones(ContextUtil.getDAO().getConvalidacionSolicitudPersistence(ActionUtil.getDBUser()).find(genericSession.getWorkSession(key).getAluCar().getId()));

        return SUCCESS;
    }
}
