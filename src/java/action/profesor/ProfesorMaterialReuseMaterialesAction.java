/*
 * @(#)ProfesorMaterialReuseMaterialesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.profesor.ProfesorMaterialReuseMaterialesService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * ProfesorMaterialReuseMateriales
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class ProfesorMaterialReuseMaterialesAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall;
    private String tipoMaterial;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {       
        String keyAux = getKey();

        setKey(getGenericSession().getWorkSession(getKey()).getKeyParent());

        return new ProfesorMaterialReuseMaterialesService().service(getGenericSession(), getMapParameters(), keyAux, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
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
