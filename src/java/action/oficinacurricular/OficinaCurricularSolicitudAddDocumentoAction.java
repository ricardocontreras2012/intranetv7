/*
 * @(#)OficinaCurricularSolicitudAddDocumentoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.oficinacurricular;

import java.io.File;
import static service.oficinacurricular.OficinaCurricularSolicitudAddDocumentoService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * OficinaCurricularSolicitudAddDocumento
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class OficinaCurricularSolicitudAddDocumentoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String caption;
    private Integer pos;
    private Integer tipoDocumento;
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(this, getGenericSession(), tipoDocumento, upload,
                uploadFileName, caption, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Method description
     *
     * @param caption
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Method description
     *
     * @param tipoDocumento
     */
    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Method description
     *
     * @return
     */
    public File getUpload() {
        return upload;
    }

    /**
     * Method description
     *
     * @param upload
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * Method description
     *
     * @param uploadContentType
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * Method description
     *
     * @param uploadFileName
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
