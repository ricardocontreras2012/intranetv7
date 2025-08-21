/*
 * @(#)PrintGroupAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.encuesta;

import java.io.InputStream;
import service.encuesta.PrintGroupService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL CommonEncuestaPrintGroup
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class PrintGroupAction extends ActionParameterAwareSupport {

    private InputStream inputStream;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        inputStream = new PrintGroupService().service(this, getGenericSession(), getMapParameters(),
                getKey());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }
}
