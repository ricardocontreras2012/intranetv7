/*
 * @(#)CommonMensajePrintMessageAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.post.ActionPostValidationSupport;
import infrastructure.util.ActionInputStreamUtil;
import java.io.InputStream;
import service.mensaje.CommonMensajePrintMessageService;

/**
 * Procesa el action mapeado del request a la URL CommonMensajePrintMessage
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajePrintMessageAction extends ActionPostValidationSupport {

    private static final long serialVersionUID = 1L;
    private String tipo;
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
            ais = new CommonMensajePrintMessageService().service(getGenericSession(), getPos(), tipo, getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }

    @Override
    public boolean isValidParam() throws IllegalArgumentException { 
        return "R".equals(tipo)
                ? isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getReceivedMsgs())
                : isValidPos(getPos(), getGenericSession().getWorkSession(getKey()).getSentMsgs());
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
