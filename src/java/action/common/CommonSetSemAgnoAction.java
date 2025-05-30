/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static service.common.CommonSetSemAgnoService.service;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 * Procesa el action mapeado del request a la URL CommonGetSemAgno
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class CommonSetSemAgnoAction extends ActionCommonAgnoSemSupport {

    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getSem(), getAgno(), getKey());
    }
}
