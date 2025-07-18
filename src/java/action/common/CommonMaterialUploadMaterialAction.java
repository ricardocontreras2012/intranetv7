/*
 * @(#)CommonMaterialUploadMaterialAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import java.io.File;
import service.common.CommonMaterialUploadMaterialService;
import infrastructure.support.action.post.ActionPostCommonSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMaterialUploadMaterial
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMaterialUploadMaterialAction extends ActionPostCommonSupport {
    private static final long serialVersionUID = 1L;
    private String            caption;
    private Integer           tipo;
    private String            tipoMaterial;
    private File              upload;
    private String            uploadContentType;
    private String            uploadFileName;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        tipoMaterial = getGenericSession().getWorkSession(getKey()).getTipoMaterial();
        return new CommonMaterialUploadMaterialService().service(this, getGenericSession(), tipo, upload, uploadFileName,
                caption, getKey());
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
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Method description
     *
     * @param tipo
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    /**
     * Method description
     *
     * @param tipoMaterial
     */
    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
}
