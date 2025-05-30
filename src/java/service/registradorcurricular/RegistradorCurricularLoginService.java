/*
 * @(#)RegistradorCurricularLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Administrativo;
import infrastructure.util.ContextUtil;
import java.util.Map;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.LogUtil;
import java.util.ArrayList;
import session.AlumnoSession;

/**
 *
 * @author Ricardo Contreras S.
 */
public class RegistradorCurricularLoginService {

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

        RegistradorSession regisSession = new RegistradorSession();
        AlumnoSession alumnoSession = new AlumnoSession();
        GenericSession genericSession = (GenericSession)session.get("genericSession");
        Administrativo administrativo = genericSession.getAdministrativo();
        regisSession.setAdministrativo(administrativo);
        session.put("registradorSession", regisSession);
        
        session.put("alumnoSession", alumnoSession);
        alumnoSession.setCertList(new ArrayList<>());
        
        genericSession.getWorkSession(key).setTramites(ContextUtil.getTramiteList());
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
