/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariacoordinacion;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaCoordinacionLoginService {

    /**
     * Method description
     *
     *
     * @param session
     *
     * @return
     *
     * @throws Exception
     */
    public static String service(Map<String, Object> session) throws Exception {
        LogUtil.setLog(((GenericSession) session.get("genericSession")).getRut());

        return SUCCESS;
    }
}
