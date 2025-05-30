/*
 * @(#)CommonAlumnoGetFotoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Alumno;
import java.io.InputStream;
import session.GenericSession;
import static infrastructure.util.common.CommonUsersUtil.getFoto;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAlumnoGetFotoService {

    public static InputStream service(GenericSession genericSession, String key) {
        Alumno alumno = genericSession.getWorkSession(key).getAluCar().getAlumno();
        return getFoto(alumno.getAluRut(), alumno.getAluDv());
    }
}
