/*
 * @(#)GetMaterialesCursoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.material;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonCursoUtil;
import static infrastructure.util.common.CommonMaterialUtil.getListaMateriales;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetMaterialesCursoService {

    /**
     *
     * @param action
     * @param genericSession
     * @param key
     * @param tipoMaterial
     * @return
     * @throws Exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String key, String tipoMaterial)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);

        ws.setTipoMaterial(tipoMaterial);
        ws.setTmaterial(getListaMateriales(CommonCursoUtil.getParent(ws.getCurso(), ws.getCargaEspejo()), genericSession.getRut(), ActionUtil.getDBUser(), genericSession.getUserType(), tipoMaterial));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        if (ws.getTmaterial().isEmpty() && !tipoMaterial.equals(genericSession.getUserType())) {
            action.addActionMessage(action.getText("message.no.hay.materiales"));
        }

        return SUCCESS;
    }
}
