/*
 * @(#)EgresadoLaboralesViewLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.misdatos.egresado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.FichaLaboralRepository;
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
        FichaLaboralRepository fichaLaboralRepository
                = ContextUtil.getDAO().getFichaLaboralRepository(ActionUtil.getDBUser());

        es.setFichaLaboral(fichaLaboralRepository.find(ws.getAluCar().getAlumno().getAluRut(),
                es.getFichaLaboralList().get(pos).getFlabCorrelFicha()));

        return SUCCESS;
    }
}
