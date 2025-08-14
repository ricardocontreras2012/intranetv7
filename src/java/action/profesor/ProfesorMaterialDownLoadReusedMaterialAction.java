/*
 * @(#)ProfesorMaterialDownLoadReusedMaterialAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import java.io.InputStream;
import service.material.DownLoadMaterialService;
import infrastructure.support.action.ActionValidationPosSupport;
import infrastructure.util.ActionInputStreamUtil;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.common.CommonMaterialUtil.existeMaterial;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorMaterialDownLoadReusedMaterial
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorMaterialDownLoadReusedMaterialAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private Integer material;
    private Integer tipo;
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
            ais = new DownLoadMaterialService().service(getGenericSession(),
                    getGenericSession().getWorkSession(getKey()).getOtrosTmaterial(), tipo, material, getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        try {
            return existeMaterial(
                    getGenericSession().getWorkSession(getKey()).getOtrosTmaterial(), tipo, material);
        } catch (Exception e) {
            logExceptionMessage(e);

            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Method description
     *
     * @return
     */
    public String getDescription() {
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
    public Integer getMaterial() {
        return material;
    }

    /**
     * Method description
     *
     * @param material
     */
    public void setMaterial(Integer material) {
        this.material = material;
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
}
