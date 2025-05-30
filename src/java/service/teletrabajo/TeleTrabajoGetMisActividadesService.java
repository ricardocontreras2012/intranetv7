/*
 * @(#)TeleTrabajoGetMisActividadesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Felipe
 * @version 1.0, 13/09/2023
 */
public final class TeleTrabajoGetMisActividadesService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     * @throws java.lang.Exception
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String key) throws Exception{
        teleSession.setActividadList(ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").findByFun(genericSession.getRut()));
        
        return SUCCESS;
    }
}
