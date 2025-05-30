/*
 * @(#)CommonRequisitoAdicionalLogroGetListaxCalificarService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.TrequisitoLogroAdicional;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.common.CommonRequisitoAdicionalUtil.getDescription;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRequisitoAdicionalLogroGetListaxCalificarService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param trequisito
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Integer trequisito, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        ws.setInscripcionAdicionalLogroList(ContextUtil.getDAO().getInscripcionAdicionalLogroPersistence(ActionUtil.getDBUser()).findxCalificar(trequisito));

        TrequisitoLogroAdicional trequisitoAux = new TrequisitoLogroAdicional();
        trequisitoAux.setTrlaCod(trequisito);
        trequisitoAux.setTrlaDes(getDescription(trequisito, ws));
        ws.setTrequisitoLogroAdicional(trequisitoAux);

        return SUCCESS;
    }
}
