/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.profesor;

import service.profesor.ProfesorSolicitudGetJustificativosCursoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class ProfesorSolicitudGetJustificativosCursoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception { 
        return new ProfesorSolicitudGetJustificativosCursoService().service(getGenericSession(), getKey());
    }
}

