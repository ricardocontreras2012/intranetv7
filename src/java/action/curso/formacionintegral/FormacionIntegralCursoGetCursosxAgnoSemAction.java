/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso.formacionintegral;

import service.curso.formacionintegral.FormacionIntegralCursoGetCursosxAgnoSemService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Administrador
 */
public class FormacionIntegralCursoGetCursosxAgnoSemAction extends ActionCommonAgnoSemSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new FormacionIntegralCursoGetCursosxAgnoSemService().service(getGenericSession(), getKey(), getAgno(), getSem());
    }   

    public String getActionCall() {
        return getGenericSession().getWorkSession(getKey()).getActionCall();
    }
}
