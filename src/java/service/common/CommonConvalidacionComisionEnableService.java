/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;


import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import session.SecretariaSession;

/**
 *
 * @author rcontreras
 */
public class CommonConvalidacionComisionEnableService {

    public static String service(SecretariaSession secreSession)
            throws Exception {
        secreSession.setComision(new ArrayList<>());

        return SUCCESS;

    }
}
