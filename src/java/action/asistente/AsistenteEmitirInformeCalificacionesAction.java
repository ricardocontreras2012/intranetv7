/*
 * @(#)AsistenteEmitirInformeCalificacionesAction.java
 *
 * Copyright (c) 2021 FAE-USACH
 */
package action.asistente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.calificacion.asistente.AsistenteEmitirInformeCalificacionesService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Felipe
 */
public class AsistenteEmitirInformeCalificacionesAction extends ActionCommonSupport {

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
            ais = new AsistenteEmitirInformeCalificacionesService().service(getGenericSession(), getKey());
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
