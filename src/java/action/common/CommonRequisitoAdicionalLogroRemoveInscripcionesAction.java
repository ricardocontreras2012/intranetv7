/*
 * @(#)CommonRequisitoAdicionalLogroRemoveInscripcionesAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonRequisitoAdicionalLogroRemoveInscripcionesService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * CommonRequisitoAdicionalLogroRemoveInscripciones
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonRequisitoAdicionalLogroRemoveInscripcionesAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private Integer trequisitoLogroAdicional;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonRequisitoAdicionalLogroRemoveInscripcionesService().service(getGenericSession(), getMapParameters(), getKey());
    }

    /**
     *
     * @return
     */
    public Integer getTrequisitoLogroAdicional() {
        return trequisitoLogroAdicional;
    }

    /**
     *
     * @param trequisitoLogroAdicional
     */
    public void setTrequisitoLogroAdicional(Integer trequisitoLogroAdicional) {
        this.trequisitoLogroAdicional = trequisitoLogroAdicional;
    }
}
