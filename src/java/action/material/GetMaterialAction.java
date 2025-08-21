/*
 * @(#)GetMaterialAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.material;


import service.material.GetMaterialService;
import infrastructure.support.action.post.ActionPostValidationSupport;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.common.CommonMaterialUtil.existeMaterial;


/**
 * Procesa el action mapeado del request a la URL CommonMaterialGetMaterial
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetMaterialAction extends ActionPostValidationSupport {
    private static final long serialVersionUID = 1L;
    private Integer           material;
    private Integer           tipo;
    private String            tipoMaterial;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetMaterialService().service(getGenericSession(), tipo, material, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        boolean retValue;
        try {
            retValue =
                existeMaterial(getGenericSession().getWorkSession(getKey()).getTmaterial(),
                    tipo, material);
        } catch (Exception e) {
            retValue = false;
            logExceptionMessage(e);
        }

        return retValue;
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
