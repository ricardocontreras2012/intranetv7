/*
 * @(#)CommonMensajeReceiveListaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MensajeNodeSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeReceiveListaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        MensajeNodeSupport mensajeNodeSupport = null;
        if (ws.getMensajeSupport() != null && ws.getMensajeSupport().getCurrentNode() != null) {
            mensajeNodeSupport = ws.getMensajeSupport().getCurrentNode();
        }

        if (mensajeNodeSupport != null) {
            if (mensajeNodeSupport.getNodeListBar() != null) {
                int n = mensajeNodeSupport.getNodeListBar().size();

                for (int i = 0; i < n; i++) {
                    String[] tmpId = parameters.get("barraCk_" +i);

                    if (tmpId != null) {
                        MensajeNodeSupport messageNodeSupport = mensajeNodeSupport.getNodeListBar().get(i);

                        messageNodeSupport.setState("SP");
                        messageNodeSupport.setId(tmpId[0]);
                        messageNodeSupport.setValue(mensajeNodeSupport.getNodeListBar().get(i).getValue());
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (!"SP".equals(mensajeNodeSupport.getNodeListBar().get(i).getState())) {
                        mensajeNodeSupport.getNodeListBar().remove(i);
                        i--;
                        n--;
                    }
                }
            }

            if (mensajeNodeSupport.getNodeList() != null) {
                int n = mensajeNodeSupport.getNodeList().size();
                for (int i = 0; i < n; i++) {
                    String[] tmpId = parameters.get("ck_" + i);

                    if (tmpId != null) {
                        MensajeNodeSupport messageNodeSupport = mensajeNodeSupport.getNodeList().get(i);

                        messageNodeSupport.setState("SP");
                        messageNodeSupport.setId(tmpId[0]);
                        messageNodeSupport.setValue(mensajeNodeSupport.getNodeList().get(i).getValue());
                    }
                }

                for (int i = 0; i < n; i++) {
                    if (!"SP".equals(mensajeNodeSupport.getNodeList().get(i).getState())) {
                        mensajeNodeSupport.getNodeList().remove(i);
                        i--;
                        n--;
                    }
                }
            }
        }

        return SUCCESS;
    }
}
