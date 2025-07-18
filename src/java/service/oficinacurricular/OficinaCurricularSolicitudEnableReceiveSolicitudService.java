/*
 * @(#)OficinaCurricularSolicitudEnableReceiveSolicitudService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Solicitud;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class OficinaCurricularSolicitudEnableReceiveSolicitudService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, Integer pos, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        Solicitud solicitud = ws.getSolicitudList().get(pos);
        ws.setSolicitud(solicitud);
        ws.getSolicitud().setSolicitudAttachList(ws.getSolicitud().getDocumentos());

        return SUCCESS;
    }
}
