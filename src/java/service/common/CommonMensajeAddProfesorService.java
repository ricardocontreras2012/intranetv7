/*
 * @(#)CommonMensajeAddProfesorService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Profesor;
import static java.lang.String.valueOf;
import java.util.ArrayList;
import session.GenericSession;
import infrastructure.support.MensajeNodeSupport;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeAddProfesorService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param keyParent
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, String keyParent, String key) {
        Profesor profesor = genericSession.getWorkSession(key).getProfesor();
        MensajeNodeSupport newNode = new MensajeNodeSupport();

        newNode.setId(valueOf(profesor.getProfRut()));
        newNode.setValue(profesor.getNombreMensaje());
        newNode.setTerminal(true);
        newNode.setNodeList(null);
        newNode.setNodeListBar(null);
        newNode.setState("PR");

        MensajeNodeSupport messageNodeSupport
                = genericSession.getWorkSession(keyParent).getMensajeSupport().getCurrentNode();

        if (messageNodeSupport.getNodeList() == null) {
            messageNodeSupport.setNodeList(new ArrayList<>());
        }

        messageNodeSupport.getNodeList().add(newNode);

        return SUCCESS;
    }
}
