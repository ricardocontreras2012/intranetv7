/*
 * @(#)AddAlumnoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.mensaje;


import service.mensaje.AddAlumnoService;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMensajeAddAlumno
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class AddAlumnoAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyAux = getKey();

        setKey(getGenericSession().getWorkSession(getKey()).getKeyParent());

        return new AddAlumnoService().service(getGenericSession(), getKey(), keyAux);
    }
}
