/*
 * @(#)CommonMensajeDownLoadFileAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.mensaje;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import infrastructure.support.action.common.ActionCommonSupport;
import service.mensaje.DownLoadImgService;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeDownLoadFile
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class DownLoadImgAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private InputStream inputStream;
    private String contentType;
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
            inputStream = new DownLoadImgService().service(this, getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }

    public String getContentType() {
        return contentType;
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

    public void setContentType(String contentType) {
        this.contentType = contentType;
    } 
}
