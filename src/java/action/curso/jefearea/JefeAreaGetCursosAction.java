/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso.jefearea;

import service.curso.jefearea.JefeAreaGetCursosService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class JefeAreaGetCursosAction extends ActionCommonAgnoSemSupport {
    @Override
    public String action() throws Exception {
        return new JefeAreaGetCursosService().service(getGenericSession(), getKey(), getAgno(), getSem());
    }

}
