/*
 * @(#)CommonAlumnoSearchService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonAlumnoUtil.getNomina;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoSearchService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param rut
     * @param paterno
     * @param materno
     * @param nombre
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer rut, String paterno, String materno,
            String nombre, String key){               
        
        getNomina(genericSession, rut, paterno,materno,nombre,key);

        LogUtil.setLog(genericSession.getRut(),"RUT=" + rut + " paterno=" + paterno + " materno=" + materno);
        return SUCCESS;
    }
}
