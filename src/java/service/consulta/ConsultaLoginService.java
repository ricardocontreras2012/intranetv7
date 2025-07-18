/*
 * @(#)ConsultaLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.consulta;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Externo;
import java.util.HashMap;
import java.util.Map;
import static org.apache.struts2.ServletActionContext.getRequest;
import domain.repository.ExternoPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retError;
import static infrastructure.util.ActionUtil.retReLogin;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ConsultaLoginService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio. param rut
     * @param sesion
     * @param rut
     * @param passwd
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(ActionCommonSupport action, Map<String, Object> sesion, Integer rut, String passwd, String key) {
        String retValue = SUCCESS;

        if (sesion != null && key != null) {
            ExternoPersistence externoPersistence
                    = ContextUtil.getDAO().getExternoPersistence(ActionUtil.getDBUser());
            Externo externo = externoPersistence.find(rut, passwd);

            if (externo != null) {
                GenericSession genericSession = new GenericSession(ActionUtil.getDBUser(), rut, passwd, 0);
                WorkSession ws = new WorkSession(ActionUtil.getDBUser());
                genericSession.setLastLogin(externo.getExtLastLogin());
                genericSession.setSessionMap(new HashMap<String, WorkSession>());
                genericSession.getSessionMap().put(key, ws);
                externoPersistence.setLastLogin(rut);
                getComplemento(genericSession, externo);

                getRequest().getSession().setMaxInactiveInterval(1800);

                //
                action.getSesion().put("genericSession", genericSession);
                LogUtil.setLog(rut);
            } else {
                action.addActionError(action.getText("error.rut.password"));
                retValue = retError();
            }
        } else {
            retValue = retReLogin();
        }
        return retValue;
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     */
    private void getComplemento(GenericSession genericSession, Externo externo) {
        genericSession.setDv(externo.getExtDv());
        genericSession.setPaterno(externo.getExtPaterno());
        genericSession.setMaterno(externo.getExtMaterno());
        genericSession.setNombres(externo.getExtNombre());
        genericSession.setNombre(externo.getNombre());
        genericSession.setNombreMensaje(externo.getNombreMensaje());
    }
}
