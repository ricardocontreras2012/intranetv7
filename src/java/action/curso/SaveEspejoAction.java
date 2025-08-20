/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso;

import service.curso.SaveEspejoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class SaveEspejoAction extends ActionCommonSupport {
    private Integer transversal;
    private Integer cerrado;

    private static final long serialVersionUID = 1L;

    @Override
    public String action() throws Exception {
        return new SaveEspejoService().service(getGenericSession(), transversal, cerrado, getKey());
    }

    public void setTransversal(Integer transversal) {
        this.transversal = transversal;
    }

    public void setCerrado(Integer cerrado) {
        this.cerrado = cerrado;
    }   
}
