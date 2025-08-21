/*
 * @(#)GetHorarioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.horario;

import service.horario.GetHorarioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 * Procesa el action mapeado del request a la URL CommonHorarioGetHorario
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetHorarioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String id;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new GetHorarioService().service(getGenericSession(), id, getKey());
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
