/*
 * @(#)GetCargaHistoricaMaterialAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.profesor.GetCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL
 * CommonProfesorCargaHistoricaMaterial
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetCargaHistoricaMaterialAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String actionCall = "CommonMaterialHistoricoGetMaterialesCurso";
    private String tipoMaterial;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        String keyParent = getKey();

        setKey(getKeySession());

        return new GetCargaHistoricaService().service(getGenericSession(), getKey(), keyParent);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getActionCall() {
        return actionCall+"?tipoMaterial="+tipoMaterial;
    }

    /**
     * Method description
     *
     * @param actionCall
     */
    public void setActionCall(String actionCall) {
        this.actionCall = actionCall;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }
}
