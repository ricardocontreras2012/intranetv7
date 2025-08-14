/*
 * @(#)CommonMensajeDownLoadFileService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import domain.model.Mensaje;
import domain.model.MensajeAttach;
import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeDownLoadFileService {

    public ActionInputStreamUtil service(GenericSession genericSession, Integer file, String key)
            throws Exception {
        String name = "";
        InputStream input;
        String description;
        ActionInputStreamUtil ais = null;

        Mensaje mensaje = genericSession.getWorkSession(key).getCurrentMsg();
        MensajeAttach mensajeAttach = ContextUtil.getDAO().getMensajeAttachRepository(ActionUtil.getDBUser()).find(mensaje.getMsgCorrel(), file);

        if (mensajeAttach != null) {
            name = mensajeAttach.getMenaAttachFile();
            input = CommonArchivoUtil.getFile(name,"msg");
            description = FormatUtil.getMimeType(name);
            ais = new ActionInputStreamUtil(name, description, input);
        }

        LogUtil.setLog(genericSession.getRut(), name);
        return ais;
    }
}
