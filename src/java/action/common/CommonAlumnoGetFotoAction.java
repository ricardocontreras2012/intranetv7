/*
 * @(#)CommonAlumnoGetFotoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.common.CommonAlumnoGetFotoService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoGetFoto
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoGetFotoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private InputStream image;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        image = service(getGenericSession(), getKey());
        return SUCCESS;
    }

    public InputStream getImage() {
        return image;
    }
}
