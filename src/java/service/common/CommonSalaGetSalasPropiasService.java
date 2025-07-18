/*
 * @(#)CommonSalaGetSalasPropiasService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Sala;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSalaGetSalasPropiasService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public String service(GenericSession genericSession, String key) throws Exception {  
        List<Sala> salaList = ContextUtil.getDAO().getSalaPersistence(ActionUtil.getDBUser()).findPropias(genericSession.getRut());
        WorkSession ws = genericSession.getWorkSession(key);

        CommonUtil.setAgnoSemAct(ws);
        ws.setSalaList(salaList);
  
        return SUCCESS;
    }
}
