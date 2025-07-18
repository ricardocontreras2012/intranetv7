/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.areaapoyo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class AreaApoyoLoginService {

    public String service(GenericSession genericSession, String key) {
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
