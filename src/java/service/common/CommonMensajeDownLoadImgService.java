/*
 * @(#)CommonMensajeDownLoadFileService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeDownLoadImgService {

    /**
     * Method description
     *
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    public static InputStream getFileServiceAction(String name) throws Exception {
        return CommonArchivoUtil.getFile(name, "msg");
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     */
    public static String getNameServiceAction(GenericSession genericSession, String key) {
        return genericSession.getWorkSession(key).getCurrentMsg().getMsgImagen();
    }
}
