/*
 * @(#)CommonProfesorGetFotoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.io.InputStream;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionInputStreamUtil;
import static infrastructure.util.common.CommonUsersUtil.getFotoProfesor;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonProfesorGetFotoService {
    public static ActionInputStreamUtil service(GenericSession genericSession, String key){
        InputStream input;
        WorkSession ws = genericSession.getWorkSession(key);        
        input = getFotoProfesor(ws.getProfesor().getProfRut(), ws.getProfesor().getProfDv());
        
        return new ActionInputStreamUtil("1.jpg", "image/jpeg", input);
        
        //return null;
    }
}
