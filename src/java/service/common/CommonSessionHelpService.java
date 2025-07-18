/*
 * @(#)CommonSessionHelpService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static java.lang.System.out;
import session.GenericSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSessionHelpService {

    /**
     *
     * @param genericSession
     * @return
     */
    public String service(GenericSession genericSession) {
        String ret;

        out.println("En help====>>" + genericSession.getCurrentAction());

        if (genericSession.getCurrentAction() != null) {
            ret = "/jsp/help/" + genericSession.getCurrentAction() + "/index.jsp";
        } else {
            ret = "/jsp/help/UnderConstruction.jsp";
        }

        return ret;
    }
}
