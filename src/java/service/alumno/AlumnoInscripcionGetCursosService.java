/*
 * @(#)AlumnoInscripcionGetCursosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Derecho;
import session.AlumnoSession;
import session.GenericSession;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoInscripcionGetCursosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param alumnoSession
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public static String service(GenericSession genericSession, AlumnoSession alumnoSession, Integer pos, String key) {
        String retValue = SUCCESS;
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        Derecho derecho = aluCar.getDerechos().get(pos);

        ws.setDerecho(derecho);

        if (aluCar.getAcaCodMen().equals(derecho.getDerMencion()) || derecho.getDerMencion()==0) {
            aluCar.setCursosInscripcion(genericSession, derecho);
        } else {          
            alumnoSession.setCambioMencion(derecho.getDerMencion());
            retValue = "cambioMencion";
        }

        return retValue;        
    }
}
