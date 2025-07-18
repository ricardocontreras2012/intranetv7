/*
 * @(#)CommonSolicitudDownLoadFileService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.SolicitudAttach;
import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonSolicitudDownLoadFileService {
    
    public ActionInputStreamUtil service(GenericSession genericSession, Integer documento, String key) throws Exception {
        String name;
        InputStream input;
        String description;
        
        SolicitudAttach solicitudDocumento
                = genericSession.getWorkSession(key).getSolicitud().getSolicitudAttachList().get(documento);
        name = solicitudDocumento.getSolaAttachFile();                        
        input = CommonArchivoUtil.getFile(name,"sol");
        description = FormatUtil.getMimeType(name);
        LogUtil.setLog(genericSession.getRut(),solicitudDocumento.getSolaAttachFile());
        return new ActionInputStreamUtil(name, description, input);
    }    
}
