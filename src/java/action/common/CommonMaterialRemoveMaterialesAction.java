/*
 * @(#)CommonMaterialRemoveMaterialesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.common;


import service.material.RemoveMaterialesService;
import infrastructure.support.action.ActionParameterAwareSupport;


/**
 * Procesa el action mapeado del request a la URL CommonMaterialRemoveMateriales
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMaterialRemoveMaterialesAction extends ActionParameterAwareSupport {
    private static final long serialVersionUID = 1L;
    private String            tipoMaterial;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        tipoMaterial = getGenericSession().getWorkSession(getKey()).getTipoMaterial();

        return new RemoveMaterialesService().service(this.getGenericSession(), getMapParameters(), getKey());
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
