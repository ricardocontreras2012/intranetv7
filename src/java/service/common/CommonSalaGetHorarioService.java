/*
 * @(#)CommonSalaGetHorarioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Sala;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.common.CommonHorarioUtil;
import static infrastructure.util.common.CommonHorarioUtil.getHorario;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSalaGetHorarioService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param agno
     * @param sem
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer pos, Integer agno, Integer sem, String key) {      

        WorkSession ws = genericSession.getWorkSession(key);
        Sala sala = ws.getSalaList().get(pos);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioDocencia(agno, sem));
        ws.setSala(sala);
        ws.setHorario(getHorario(ws.getModuloHorarioList(),sala, agno, sem));
       
        return SUCCESS;
    }
}
