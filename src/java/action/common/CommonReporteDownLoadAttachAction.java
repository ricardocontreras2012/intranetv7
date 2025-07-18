/*
 * @(#)CommonReporteDownLoadAttachAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import java.io.InputStream;
import service.common.CommonReporteDownLoadAttachService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonReporteDownLoadAttach
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonReporteDownLoadAttachAction extends ActionCommonSupport {

    private String description = "application/binary";
    private InputStream inputStream;
    private String name;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String retValue = SUCCESS;
        try {
            inputStream = new CommonReporteDownLoadAttachService().service(this, getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
