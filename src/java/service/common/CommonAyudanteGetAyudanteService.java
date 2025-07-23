/*
 * @(#)CommonAyudanteGetAyudanteService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Ayudante;
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
public final class CommonAyudanteGetAyudanteService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        if (!ws.getAyudanteList().isEmpty()) {

            Ayudante ayudante = ContextUtil.getDAO().getAyudanteRepository(ActionUtil.getDBUser()).findFull(
                            ws.getAyudanteList().get(pos).getAyuRut());

            ws.setAyudante(ayudante);
            ayudante.setCarga();
            ws.setNombre(ayudante.getNombre());
        }

        return SUCCESS;
    }
}
