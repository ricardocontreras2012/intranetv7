/*
 * @(#)CloseSessionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.login;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonFacultadUtil.getUnidadxProf;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CloseSessionService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param sesion Contenedor de la genericSession.
     * @param key
     * @return
     */
    private static final String DEFAULT_URL = "www.google.cl";

    /**
     *
     * @param sesion
     * @param key
     * @return
     */
    public String service(Map<String, Object> sesion, String key) {
        String retValue = DEFAULT_URL;

        try {
            if (sesion != null && key != null) {
                GenericSession genericSession = (GenericSession) sesion.get("genericSession");
                if (genericSession != null) {
                    WorkSession ws = genericSession.getWorkSession(key);

                    switch (genericSession.getUserType()) {
                        case "AL":
                            retValue = ws.getAluCar().getUnidadFacultad().getUniUrl();
                            break;
                        case "PR":
                            retValue = ContextUtil.getDAO()
                                    .getUnidadRepository(ActionUtil.getDBUser())
                                    .find(getUnidadxProf(ws.getProfesor().getProfRut()))
                                    .getUniUrl();
                            break;
                        default:
                            retValue = DEFAULT_URL;
                    }

                    LogUtil.setLog(genericSession.getRut());
                }
            }
        } catch (Exception e) {
            LogUtil.logExceptionMessage(e); // ← importante loguear
        } finally {
            if (sesion != null) {
                sesion.remove("genericSession");
                if (sesion instanceof SessionMap) {
                    ((SessionMap) sesion).invalidate();
                }

                sesion.clear();
            }
        }

        return retValue;
    }
}
