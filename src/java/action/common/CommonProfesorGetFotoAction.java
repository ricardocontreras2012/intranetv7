/*
 * @(#)CommonProfesorGetFotoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.InputStream;
import service.common.CommonProfesorGetFotoService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL CommonProfesorGetFoto
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonProfesorGetFotoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {   
        ais = new CommonProfesorGetFotoService().service(getGenericSession(), getKey());
        return SUCCESS;
    }

    public String getContentType() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }
}
