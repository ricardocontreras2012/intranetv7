/*
 * @(#)GetMaterialesAlumnoService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.material;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMaterialUtil.getListaMateriales;

/**
 *
 * @author Ricardo Contreras S.
 */
public class GetMaterialesAlumnoService {

    /**
     * Method description
     *
     *
     * @param action
     * @param genericSession
     * @param key
     * @param pos
     *
     * @return
     *
     * @throws Exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, String key, Integer pos)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = ws.getCurso();

        ws.setTipoMaterial("AL");
        ws.setTmaterial(getListaMateriales(curso.getId(),
                ws.getNominaCurso().get(pos).getId().getAcaRut(), ActionUtil.getDBUser(), "AL",
                "AL"));        
        if (ws.getTmaterial().isEmpty()) {
            action.addActionMessage(action.getText("message.no.hay.materiales"));
        }

        LogUtil.setLogCurso(genericSession.getRut(), curso);
        return SUCCESS;
    }
}
