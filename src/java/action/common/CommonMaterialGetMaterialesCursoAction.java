/*
 * @(#)CommonMaterialGetMaterialesCursoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.material.GetMaterialesCursoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonMaterialGetMaterialesCurso
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonMaterialGetMaterialesCursoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String tipoMaterial;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new GetMaterialesCursoService().service(this, getGenericSession(), getKey(), tipoMaterial);
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
