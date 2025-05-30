/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarService {

    public static String service(ActionCommonSupport action, GenericSession genericSession, String flag, String key)
            throws Exception {
        genericSession.getWorkSession(key).setFlag(flag);
        return SUCCESS;
    }
}
