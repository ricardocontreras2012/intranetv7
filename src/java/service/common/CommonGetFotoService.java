/*
 * @(#)CommonGetFotoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.io.InputStream;
import session.GenericSession;
import static infrastructure.util.common.CommonUsersUtil.getFoto;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonGetFotoService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @return
     */
    public static InputStream service(GenericSession genericSession) {

        return getFoto(genericSession.getRut(), genericSession.getDv());
    }
}
