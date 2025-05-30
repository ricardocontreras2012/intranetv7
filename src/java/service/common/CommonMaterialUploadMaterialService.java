/*
 * @(#)CommonMaterialUploadMaterialService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMaterialUtil.doNewFile;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMaterialUploadMaterialService {

    /**
     * Method description
     *
     * @param action Clase(action) que invoca al servicio.
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param upload
     * @param uploadFileName
     * @param caption
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public static String service(ActionCommonSupport action, GenericSession genericSession, Integer tipo, File upload,
            String uploadFileName, String caption, String key)
            throws Exception {
        doNewFile(action, genericSession, tipo, upload, uploadFileName, caption, key);
        
        LogUtil.setLog(genericSession.getRut(), genericSession.getCurso(key).getNombreCorto() + " > "
                + uploadFileName);    
        return SUCCESS;
    }
}
