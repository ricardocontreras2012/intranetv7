/*
 * @(#)CommonGetFotoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.InputStream;
import service.common.CommonGetFotoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonGetFoto
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonGetFotoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private InputStream image;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        image = new CommonGetFotoService().service(getGenericSession());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getImage() {
        return image;
    }
}
