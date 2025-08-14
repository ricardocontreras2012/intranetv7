/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.jefearea;

import service.encuesta.jefearea.JefeAreaGetCursosEncuestaService;
import infrastructure.support.action.common.ActionCommonAgnoSemSupport;

/**
 *
 * @author Administrador
 */
public class JefeAreaGetCursosEncuestaAction extends ActionCommonAgnoSemSupport {
    
    private static final long serialVersionUID = 1L;
    private String tipo;
    
    @Override
    public String action() throws Exception {
        return new JefeAreaGetCursosEncuestaService().service(getGenericSession(), getKey(), getAgno(), getSem());
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}