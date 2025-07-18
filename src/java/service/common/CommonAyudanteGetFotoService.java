/*
 * @(#)CommonAyudanteGetFotoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.io.InputStream;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import static infrastructure.util.common.CommonUsersUtil.getFoto;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAyudanteGetFotoService {

    public ActionInputStreamUtil service(GenericSession genericSession, String key) {
        InputStream input;
        WorkSession ws = genericSession.getWorkSession(key);
        input = getFoto(ws.getAyudante().getAyuRut(), ws.getAyudante().getAyuDv());

        return new ActionInputStreamUtil("1.jpg", "image/jpeg", input);
    }
}
