/*
 * @(#)DownloadAttachFileService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import java.io.InputStream;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DownloadAttachFileService {        
    public ActionInputStreamUtil service(Integer correl, String file, String key) throws Exception
    {
        InputStream input;
        String contentType;
        ActionInputStreamUtil ais= null;
        
        if ( ContextUtil.getDAO().getMensajeAttachRepository(ActionUtil.getDBUser()).find(
                correl, file, key) != null) {                        
            input = CommonArchivoUtil.getFile(file, "msg");
            contentType = FormatUtil.getMimeType(file);
            ais = new ActionInputStreamUtil(file, contentType, input);
        }
        
        return ais;        
    }
}
