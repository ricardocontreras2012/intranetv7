/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import service.certificacion.alumno.AlumnoCertificacionGlosaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGlosaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer correl; 

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new AlumnoCertificacionGlosaService().service(correl);
    }

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }  
}