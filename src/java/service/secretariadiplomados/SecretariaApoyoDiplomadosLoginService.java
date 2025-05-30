/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariadiplomados;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Administrativo;
import java.util.ArrayList;
import java.util.Map;
import session.AlumnoSession;
import session.GenericSession;
import session.SecretariaSession;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Administrador
 */
public class SecretariaApoyoDiplomadosLoginService {

    /**
     * Method description
     *
     *
     * @param session
     * @param key
     *
     * @return
     *
     * @throws Exception
     */
    public static String service(Map<String, Object> session, String key) throws Exception {
        SecretariaSession secreSession = new SecretariaSession();
        AlumnoSession alumnoSession = new AlumnoSession();
        GenericSession genericSession = (GenericSession) session.get("genericSession");
        Administrativo administrativo = genericSession.getAdministrativo();
        secreSession.setAdministrativo(administrativo);
        session.put("secretariaSession", secreSession);
        session.put("alumnoSession", alumnoSession);
        alumnoSession.setCertList(new ArrayList<>());
        genericSession.getWorkSession(key).setTramites(ContextUtil.getTramiteList());
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
