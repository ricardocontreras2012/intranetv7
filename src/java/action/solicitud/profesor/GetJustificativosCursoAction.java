/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.solicitud.profesor;

import service.solicitud.justificativo.profesor.ProfesorGetJustificativosService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class GetJustificativosCursoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception { 
        return new ProfesorGetJustificativosService().service(getGenericSession(), getKey());
    }
}

