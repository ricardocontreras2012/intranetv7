/*
 * @(#)GetMaterialService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.material;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonMaterialUtil.getMaterial;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetMaterialService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param tipo
     * @param material
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, Integer tipo, Integer material, String key)
            throws Exception {
        
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setMaterial(getMaterial(ws.getTmaterial(), tipo, material));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());  

        return SUCCESS;
    }
}
