/*
 * @(#)EgresadoEstudiosGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.misdatos.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.repository.FichaEstudioRepository;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;


/**
 *
 * @author Alvaro Romero C.
 */
public final class EgresadoEstudiosGetMisDatosService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, String key) {
        WorkSession             ws             = genericSession.getWorkSession(key);
        FichaEstudioRepository fichaEstudioRepository =
            ContextUtil.getDAO().getFichaEstudioRepository(ActionUtil.getDBUser());

        es.setFichaEstudioList(fichaEstudioRepository.find(ws.getAluCar().getAlumno().getAluRut()));

        return SUCCESS;
    }
}
