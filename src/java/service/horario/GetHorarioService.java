/*
 * @(#)GetHorarioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.horario;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonHorarioUtil;
import static infrastructure.util.common.CommonHorarioUtil.getHorario;


/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetHorarioService {

    public String service(GenericSession genericSession, String id, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        ws.setModuloHorarioList(CommonHorarioUtil.getModuloHorarioList(ws.getCursoList()));
        ws.setHorario(getHorario(id, ws.getModuloHorarioList(),ws.getCursoList(), genericSession.getUserType()));
        ws.setIdHorario(id);

        LogUtil.setLog(genericSession.getRut());
        
        return SUCCESS;
    }
}
