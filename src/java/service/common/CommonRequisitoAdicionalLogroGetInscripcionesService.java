/*
 * @(#)CommonRequisitoAdicionalLogroGetInscripcionesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.InscripcionAdicionalLogro;
import domain.model.TrequisitoLogroAdicional;
import java.util.List;
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
public final class CommonRequisitoAdicionalLogroGetInscripcionesService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param trequisito
     * @param key LLave para aceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer trequisito, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        if ((ws.getTrequisitoLogroAdicional()== null)
                || (ws.getTrequisitoLogroAdicional().getTrlaCod().intValue()
                != trequisito.intValue())) {
            TrequisitoLogroAdicional trequisitoAux = new TrequisitoLogroAdicional();

            trequisitoAux.setTrlaCod(trequisito);
            trequisitoAux.setTrlaDes(getDescription(trequisito, ws));
            ws.setTrequisitoLogroAdicional(trequisitoAux);
        }

        List<InscripcionAdicionalLogro> inscpcionAdicionalList
                = ContextUtil.getDAO().getInscripcionAdicionalLogroPersistence(ActionUtil.getDBUser()).find(
                        ws.getAgnoAct(), ws.getSemAct(), trequisito);

        ws.setInscripcionAdicionalLogroList(inscpcionAdicionalList);

        return SUCCESS;
    }
}
