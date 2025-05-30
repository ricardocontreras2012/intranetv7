/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import infrastructure.support.action.common.ActionCommonSupport;
import static service.common.CommonSalaReservaGetCursoService.service;

/**
 *
 * @author rcontreras
 */
public class CommonSalaReservaGetCursoAction extends ActionCommonSupport {

    private String curso;

    @Override
    public String action() throws Exception {
        return service(getGenericSession(), curso, getKey());
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
