/*
 * @(#)CommonMensajeDownLoadFileService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.mensaje;

import action.common.CommonMensajeDownLoadImgAction;
import infrastructure.util.FormatUtil;
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

    public InputStream service(CommonMensajeDownLoadImgAction action, GenericSession gs, String key) throws Exception {
        String name = gs.getWorkSession(key).getCurrentMsg().getMsgImagen();
        action.setName(name);
        action.setContentType(FormatUtil.getMimeType(name));
        return CommonArchivoUtil.getFile(action.getName(), "msg");
    }
}
