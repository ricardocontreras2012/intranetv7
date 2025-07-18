/*
 * @(#)CommonMensajeDownLoadFileAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.common.CommonMensajeDownLoadFileService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL CommonMensajeDownLoadFile
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMensajeDownLoadFileAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer file;
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
        ais = new CommonMensajeDownLoadFileService().service(getGenericSession(), file, getKey());

       } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
        
    }

    public String getContentType() {
        return ais.getContentType();
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    /**
     * Method description
     *
     * @return
     */
    public String getName() {
        return ais.getName();
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getFile() {
        return file;
    }

    /**
     * Method description
     *
     * @param file
     */
    public void setFile(Integer file) {
        this.file = file;
    }
}
