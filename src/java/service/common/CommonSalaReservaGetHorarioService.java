/*
 * @(#)CommonSalaReservaGetHorarioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Sala;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonHorarioUtil;
import infrastructure.util.common.CommonSalaUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSalaReservaGetHorarioService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param inicio
     * @param termino
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer pos, String inicio, String termino, String key) { 

        WorkSession ws = genericSession.getWorkSession(key);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioAll(inicio, termino));
        Sala sala = ws.getSalaList().get(pos);
        ws.setSala(sala);        
        ws.setHorarioSala(CommonSalaUtil.getHorario(sala, inicio, termino, ws.getModuloHorarioList()));

        return SUCCESS;
    }
}
