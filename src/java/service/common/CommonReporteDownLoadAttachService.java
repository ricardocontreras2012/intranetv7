/*
 * @(#)CommonReporteDownLoadAttachService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import action.common.CommonReporteDownLoadAttachAction;
import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonReporteDownLoadAttachService {

    public InputStream service(CommonReporteDownLoadAttachAction action, GenericSession gs, String key) throws Exception {

        String name = gs.getWorkSession(key).getReporte().getRclaAttach();
        action.setName(name);
        return CommonArchivoUtil.getFile(name, "mat");
    }
}
