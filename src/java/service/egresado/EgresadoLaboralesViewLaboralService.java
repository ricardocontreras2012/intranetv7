/*
 * @(#)EgresadoLaboralesViewLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.FichaLaboralPersistence;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesViewLaboralService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param pos
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        FichaLaboralPersistence fichaLaboralPersistence
                = ContextUtil.getDAO().getFichaLaboralPersistence(ActionUtil.getDBUser());

        es.setFichaLaboral(fichaLaboralPersistence.find(ws.getAluCar().getAlumno().getAluRut(),
                es.getFichaLaboralList().get(pos).getFlabCorrelFicha()));

        return SUCCESS;
    }
}
