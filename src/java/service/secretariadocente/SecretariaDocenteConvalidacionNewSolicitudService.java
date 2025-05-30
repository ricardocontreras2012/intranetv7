/*
 * @(#)SecretariaDocenteConvalidacionNewSolicitudService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.ConvalidacionSolicitud;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonUtil;


/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionNewSolicitudService {

    /**
     * Method description
     *
     *
     * @param genericSession
     * @param secreSession
     * @param key
     *
     * @return
     */
    public static String service(GenericSession genericSession, SecretariaSession secreSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        secreSession.setPorAprobar(ContextUtil.getDAO().getConvalidacionSolicitudAsignPersistence(ActionUtil.getDBUser()).getPorConvalidar(aluCar));

        CommonUtil.setAgnoSemAct(ws);
        ConvalidacionSolicitud solicitud = new ConvalidacionSolicitud();
        solicitud.setCosEstado("N");
        secreSession.setConvalidacion(solicitud);
        
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
