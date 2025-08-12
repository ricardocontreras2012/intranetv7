/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.opensymphony.xwork2.Action;
import infrastructure.support.action.post.ActionPostCommonSupport;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retError;
import infrastructure.util.ContextUtil;
import static infrastructure.util.MailUtil.sendURLNewPassword;

/**
 *
 * @author Ricardo
 */
public class CommonPasswordAskNewPasswordService {

    public String service(ActionPostCommonSupport action, int rut, String user) throws Exception {
  
        String retValue;
        String email = ContextUtil.getDAO().getDummyRepository(ActionUtil.getDBUser()).getEmail(rut);       
        
        if (email != null && !"".equals(email)) {                        
            sendURLNewPassword(rut, user, email);
            action.addActionMessage(action.getText("message.password.email.sent"));
            retValue = Action.SUCCESS;

        } else {
            action.addActionError(action.getText("error.usuario.sin.correo"));
            retValue = retError();
        }

        return retValue;                
        
    }
}