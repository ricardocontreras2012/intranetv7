/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;
import infrastructure.util.ContextUtil;
import static infrastructure.util.MailUtil.sendNewPassword;

/**
 *
 * @author Ricardo
 */
public class CommonPasswordSavePasswordService {

    public String service(ActionCommonSupport action, GenericSession genericSession, String passwdActual, String passwdNueva) {
       
        String retValue = SUCCESS;
        String email;
         
        if (passwdActual.equals(genericSession.getPassword())) {
 
            ContextUtil.getDAO().getDummyRepository(genericSession.getUserType()).setPasswordUser(genericSession.getRut(), genericSession.getUserType(), passwdNueva);
            genericSession.setPassword(passwdNueva);

            email = ContextUtil.getDAO().getDummyRepository(genericSession.getUserType()).getEmail(genericSession.getRut());
            if (email != null && !"".equals(email)) {
                sendNewPassword(email, passwdNueva);
            }
        } else {
            action.addActionError(action.getText("error.password.actual"));
            if (PRIVILEGED_USERS.containsKey(genericSession.getUserType())) {
                retValue = "askPlus";
            } else {
                retValue = "askNormal";
            }
        }     
        
        return retValue;     
    }
}
