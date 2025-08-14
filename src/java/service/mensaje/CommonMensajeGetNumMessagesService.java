/*
 * @(#)CommonMensajeGetNumMessagesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeGetNumMessagesService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     */
    public String service(GenericSession genericSession, String key) {
            WorkSession ws = genericSession.getWorkSession(key);

            ws.setBlinkMensajes(false);

            Long numMsgs = ContextUtil.getDAO().getMensajeDestinatarioRepository(ActionUtil.getDBUser()).countMsgs(genericSession.getRut());

            if (ws.getNuevosMensajes().compareTo(numMsgs) < 0) {
                ws.setNuevosMensajes(numMsgs);
                ws.setBlinkMensajes(true);
            }

        return SUCCESS;
    }
}
