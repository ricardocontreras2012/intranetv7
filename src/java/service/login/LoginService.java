/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login;

import action.login.LoginAction;
import infrastructure.util.ContextUtil;
import java.util.Map;

/**
 *
 * @author Ricardo
 */
public class LoginService {
    public void service(LoginAction action, String userType, Map<String, Object> sesion) {
        action.setActionCall(ContextUtil.getDAO().getLogActionRepository(userType).getActionLogin(userType));
        
        if (sesion.get("genericSession") != null)
        {
            sesion.clear();
            System.out.println("Se elimino sesion anterior......");
        }
    }
}
