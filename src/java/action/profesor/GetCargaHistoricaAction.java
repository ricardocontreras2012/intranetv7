/*
 * @(#)GetCargaHistoricaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.profesor;

import service.profesor.GetCargaHistoricaService;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 * Procesa el action mapeado del request a la URL CommonProfesorCargaHistorica
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetCargaHistoricaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

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
}
