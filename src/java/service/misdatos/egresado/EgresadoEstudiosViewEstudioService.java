/*
 * @(#)EgresadoEstudiosViewEstudioService.java
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
public class EgresadoEstudiosViewEstudioService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param es
     * @param correl
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, EgresadoSession es, Integer correl, String key) {
        WorkSession             ws             = genericSession.getWorkSession(key);
        FichaEstudioRepository fichaEstudioRepository =
            ContextUtil.getDAO().getFichaEstudioRepository(ActionUtil.getDBUser());

            es.setFichaEstudio(fichaEstudioRepository.find(ws.getAluCar().getAlumno().getAluRut(),
                    correl));

            return SUCCESS;
    }
}
