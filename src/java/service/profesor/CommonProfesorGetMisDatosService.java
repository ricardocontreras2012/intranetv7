/*
 * @(#)CommonProfesorGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Profesor;
import domain.repository.ProfesorRepository;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetMisDatosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {      
        ProfesorRepository profesorRepository
                = ContextUtil.getDAO().getProfesorRepository(ActionUtil.getDBUser());
        Profesor profesorAux = profesorRepository.getMisDatos(genericSession.getRut());
        Profesor profesor = genericSession.getWorkSession(key).getProfesor();


        profesor.setComuna(profesorAux.getComuna());
        profesor.setProfDireccion(profesorAux.getProfDireccion());
        profesor.setProfFono(profesorAux.getProfFono());
        profesor.setProfFechaNac(profesorAux.getProfFechaNac());
        profesor.setProfEmail(profesorAux.getProfEmail());
        return SUCCESS;
    }
}
