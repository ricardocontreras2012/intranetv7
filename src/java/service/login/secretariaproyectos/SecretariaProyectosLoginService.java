/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.login.secretariaproyectos;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.ProyectoSession;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosLoginService {

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
    public String service(Map<String, Object> session) throws Exception {
        session.put("proyectoSession", new ProyectoSession());
        LogUtil.setLog(((GenericSession) session.get("genericSession")).getRut());

        return SUCCESS;
    }
}
