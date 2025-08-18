/*
 * @(#)AlumnoGetMisDatosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.misdatos.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Alumno;

import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoGetMisDatosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, String key) {
        Alumno alumnoAux = ContextUtil.getDAO().getAlumnoRepository(ActionUtil.getDBUser()).getMisDatos(genericSession.getRut());
        Alumno alumno = genericSession.getWorkSession(key).getAluCar().getAlumno();

        alumno.setAluEstCiv(alumnoAux.getAluEstCiv());
        alumno.setComunaAlu(alumnoAux.getComunaAlu());
        alumno.setAluDirecAlu(alumnoAux.getAluDirecAlu());
        alumno.setAluFonoAlu(alumnoAux.getAluFonoAlu());
        alumno.setAluFechaNac(alumnoAux.getAluFechaNac());
        alumno.setAluEmail(alumnoAux.getAluEmail());
        alumno.setAluEmailLaboral(alumnoAux.getAluEmailLaboral());

        return SUCCESS;
    }
}
