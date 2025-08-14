/*
 * @(#)CommonSolicitudDownLoadFileAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.solicitud.DownLoadFileService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL
 * CommonSolicitudDownLoadDocumento
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSolicitudDownLoadFileAction extends ActionCommonSupport {

    private Integer file;
    private ActionInputStreamUtil ais;

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
            ais = new DownLoadFileService().service(getGenericSession(), file, getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;

    }

    public String getContentType() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    /**
     *
     * @return
     */
    public Integer getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(Integer file) {
        this.file = file;
    }
}
