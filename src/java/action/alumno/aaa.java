/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import infrastructure.support.action.common.ActionCommonSupport;
import static service.alumno.aaaS.service;

/**
 *
 * @author Ricardo
 */
public class aaa extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public String action() {
        return service( getGenericSession(), getKey());
    }    
}
