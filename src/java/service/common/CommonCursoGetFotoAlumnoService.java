/*
 * @(#)CommonCursoGetFotoAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.AluCar;
import java.io.InputStream;
import session.GenericSession;
import static infrastructure.util.common.CommonUsersUtil.getFoto;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonCursoGetFotoAlumnoService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public InputStream service(GenericSession genericSession, Integer pos, String key) throws Exception {
        AluCar aluCar = genericSession.getWorkSession(key).getNominaCurso().get(pos);

        return getFoto(aluCar.getId().getAcaRut(), aluCar.getAlumno().getAluDv());
    }
}
