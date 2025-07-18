/*
 * @(#)JefeAreaLoginService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.jefearea;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonUtil;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeAreaLoginService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param key
     *
     * @return
     */
    public String service(GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        CommonUtil.setAgnoSemAct(ws);
        ws.setCursoList(ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser()).findxUser(genericSession.getRut(), genericSession.getUserType()));
        LogUtil.setLog(genericSession.getRut());
        return SUCCESS;
    }
}
