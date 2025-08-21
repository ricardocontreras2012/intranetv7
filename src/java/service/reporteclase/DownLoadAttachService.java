/*
 * @(#)DownLoadAttachService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.reporteclase;

import action.reporteclase.DownLoadAttachAction;
import java.io.InputStream;
import session.GenericSession;
import infrastructure.util.common.CommonArchivoUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class DownLoadAttachService {

    public InputStream service(DownLoadAttachAction action, GenericSession gs, String key) throws Exception {

        String name = gs.getWorkSession(key).getReporte().getRclaAttach();
        action.setName(name);
        return CommonArchivoUtil.getFile(name, "mat");
    }
}
