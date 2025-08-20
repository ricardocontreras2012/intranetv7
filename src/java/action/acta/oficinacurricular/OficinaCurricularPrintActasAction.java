/*
 * @(#)OficinaCurricularPrintActasAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.acta.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import service.acta.oficinacurricular.OficinaCurricularPrintActasService;
import infrastructure.support.action.ActionParameterAwareSupport;


/**
 * Procesa el action mapeado del request a la URL
 * OficinaCurricularActaPrintActas
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class OficinaCurricularPrintActasAction extends ActionParameterAwareSupport {

    @Override
    public String action() throws Exception {
        new OficinaCurricularPrintActasService().service(getGenericSession(), getMapParameters(), getKey());

        return SUCCESS;
    }
}
