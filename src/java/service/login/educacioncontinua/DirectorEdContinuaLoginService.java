/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login.educacioncontinua;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author rcontreras
 */
public class DirectorEdContinuaLoginService {

    public String service(GenericSession genericSession, String key) throws Exception {
        CommonUtil.setAgnoSemAct(genericSession.getWorkSession(key));
        return SUCCESS;
    }

}
