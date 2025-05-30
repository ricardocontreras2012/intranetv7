/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import java.io.InputStream;
import java.util.Map;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.common.CommonEncuestaUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonEncuestaPrintGroupCursosService {

    /**
     *
     * @param action
     * @param genericSession
     * @param parameters
     * @param key
     * @return
     * @throws Exception
     */
    public InputStream service(ActionCommonSupport action, GenericSession genericSession,
            Map<String, String[]> parameters, String key)
            throws Exception {
        CommonEncuestaUtil commonEncuestaUtil  = new CommonEncuestaUtil();

        return commonEncuestaUtil.print(action, genericSession, parameters, key);
    }
}
