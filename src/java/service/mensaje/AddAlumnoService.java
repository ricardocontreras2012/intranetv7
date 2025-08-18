/*
 * @(#)AddAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Alumno;
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
public final class AddAlumnoService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param keyParent
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String keyParent, String key) {
        Alumno alumno = genericSession.getWorkSession(key).getAlumno();
        MensajeNodeSupport newNode = new MensajeNodeSupport();

        newNode.setId(valueOf(alumno.getAluRut()));
        newNode.setValue(alumno.getNombreMensaje());
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
