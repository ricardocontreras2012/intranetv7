/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import infrastructure.support.action.post.ActionPostCommonSupport;
import service.alumno.AlumnoSolicitudExpedienteAddSolicitudService;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteAddSolicitudAction extends ActionPostCommonSupport{
    
    private static final long serialVersionUID = 1L;
    
    //private String causa;
   
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new AlumnoSolicitudExpedienteAddSolicitudService().service(this, getGenericSession(), getKey());
    } 

   /*public void setCausa(String causa) {
        this.causa = causa;
    }*/
}