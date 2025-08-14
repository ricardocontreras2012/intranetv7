/*
 * @(#)EgresadoLaboralesNewLaboralService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */


package service.misdatos.egresado;


import static com.opensymphony.xwork2.Action.SUCCESS;
import session.EgresadoSession;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import domain.repository.AlumnoEmpleadorRepository;


/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesNewLaboralService {

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
        WorkSession ws = genericSession.getWorkSession(key);
        es.setAreaTrabajoList(ContextUtil.getAreaTrabajoList());
        AlumnoEmpleadorRepository alumnoEmpleadorRepository
                = ContextUtil.getDAO().getAlumnoEmpleadorRepository(ActionUtil.getDBUser());

        es.setAlumnoEmpleadorList(
                alumnoEmpleadorRepository.find(ws.getAluCar().getAlumno().getAluRut()));
        return SUCCESS;
    }
}
