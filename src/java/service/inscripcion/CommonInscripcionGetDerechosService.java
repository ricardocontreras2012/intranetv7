/*
 * @(#)CommonInscripcionGetDerechosService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.inscripcion;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonInscripcionGetDerechosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     */
    public String service(GenericSession genericSession, String key) {
        AluCar aluCar = genericSession.getWorkSession(key).getAluCar();

        aluCar.setIsAlumnoPropio(genericSession);       
        
        if (aluCar.getIsAlumnoPropio()) {            
            aluCar.setDerechosCoordinadorInscripcion(genericSession.getRut(), genericSession.getUserType());
        } else {            
            switch (genericSession.getUserType()) {
                case "JC":
                case "SP":
                    aluCar.setDerechosCoordinadorInscripcionLibre(genericSession.getRut());
                    break;
                case "CFI":
                    aluCar.setDerechosFIInscripcion(genericSession.getRut(), genericSession.getUserType());
                    break;
            }
        }             
        
        return SUCCESS;
    }
}
