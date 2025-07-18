/*
 * @(#)CommonSessionCloseService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

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
public final class CommonSessionCloseService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param sesion Contenedor de la genericSession.
     * @param key
     * @return
     */
    private static final String DEFAULT_URL ="www.google.cl";

    /**
     *
     * @param action
     * @param sesion
     * @param key
     * @return
     */
    public String service(Map<String, Object> sesion, String key) {
        GenericSession genericSession;

        String retValue = DEFAULT_URL;
        genericSession = null;

        try {
            if (sesion != null && key != null) {
                genericSession = (GenericSession) sesion.get("genericSession");
                if (genericSession != null) {
                    WorkSession ws = genericSession.getWorkSession(key);

                    switch (genericSession.getUserType()) {
                        case "AL":
                            retValue=ws.getAluCar().getUnidadFacultad().getUniUrl();
                            break;
                        case "PR":
                            retValue=ContextUtil.getDAO().getUnidadPersistence(ActionUtil.getDBUser()).find(getUnidadxProf(ws.getProfesor().getProfRut())).getUniUrl();
                            break;
                        default:
                            retValue = DEFAULT_URL;
                    }
                }
            }
            if (genericSession != null) {
                LogUtil.setLog(genericSession.getRut());
            }
        } catch (Exception e) {
        } finally {
            if (sesion != null) {
                sesion.remove("genericSession");
                ((SessionMap) sesion).invalidate();
                sesion.clear();
            }
        }

        return (retValue==null)?"0":retValue;
    }
}
