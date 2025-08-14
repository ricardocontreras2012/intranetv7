/*
 * @(#)OficinaCurricularSolicitudRemoveDocumentoAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.oficinacurricular;

import service.solicitud.oficinacurricular.OficinaCurricularRemoveDocumentoService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 * Procesa el action mapeado del request a la URL
 * OficinaCurricularSolicitudRemoveDocumento
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class OficinaCurricularSolicitudRemoveDocumentoAction extends ActionParameterAwareSupport {

    private static final long serialVersionUID = 1L;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new OficinaCurricularRemoveDocumentoService().service(getGenericSession(), getMapParameters(), getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPos() {
        return pos;
    }

    /**
     * Method description
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
