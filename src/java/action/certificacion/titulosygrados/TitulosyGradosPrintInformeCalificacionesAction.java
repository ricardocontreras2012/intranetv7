/*
 * @(#)AlumnoCertificacionEmitirInformeCalificacionesAction.java
 *
 * Copyright (c) 2021 FAE-USACH
 */
package action.certificacion.titulosygrados;

import java.io.InputStream;
import service.certificacion.titulosygrados.TitulosyGradosPrintInformeCalificacionesService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Ricardo Contreras S.
 */

public class TitulosyGradosPrintInformeCalificacionesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    TitulosyGradosPrintInformeCalificacionesService cert = new TitulosyGradosPrintInformeCalificacionesService();
    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = cert.service(getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }    

    public String getDescription() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }
}