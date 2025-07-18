/*
 * @(#)CommonHorarioGetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.common;

import service.common.CommonHorarioGetHorarioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonHorarioGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonHorarioGetHorarioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String id;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new CommonHorarioGetHorarioService().service(getGenericSession(), id, getKey());
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
