/*
 * @(#)CommonAlumnoPrintSituacionesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;
import java.io.InputStream;
import service.common.CommonAlumnoPrintSituacionesService;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoPrintSituaciones
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoPrintSituacionesAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    CommonAlumnoPrintSituacionesService serviceCert = new CommonAlumnoPrintSituacionesService();
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
            ais = serviceCert.service(getGenericSession(), getKey());
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
