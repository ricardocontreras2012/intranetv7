/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.certificacion.alumno;

import service.certificacion.alumno.AlumnoSetGlosaService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoSetGlosaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer correl; 

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action(){
        return new AlumnoSetGlosaService().service(correl);
    }

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }  
}