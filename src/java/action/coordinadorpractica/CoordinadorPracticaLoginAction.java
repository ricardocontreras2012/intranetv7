/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.coordinadorpractica;

import static service.coordinadorpractica.CoordinadorPracticaLoginService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Usach
 */
public class CoordinadorPracticaLoginAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return service(getGenericSession(), getKey());
    }
}