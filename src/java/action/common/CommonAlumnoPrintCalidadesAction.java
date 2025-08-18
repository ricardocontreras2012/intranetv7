/*
 * @(#)CommonAlumnoPrintCalidadesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;
import java.io.InputStream;
import service.alumno.PrintCalidadesService;

/**
 * Procesa el action mapeado del request a la URL CommonAlumnoPrintCalidades
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonAlumnoPrintCalidadesAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = new PrintCalidadesService().service(getGenericSession(), getKey());
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
