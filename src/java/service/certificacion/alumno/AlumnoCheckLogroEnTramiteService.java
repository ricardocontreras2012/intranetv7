/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.AlumnoSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoCheckLogroEnTramiteService {
     public String service(AlumnoSession alumnoSession, Integer pos) {
        alumnoSession.setTipoCert(alumnoSession.getCertList().get(pos));        
       
        return SUCCESS;
    } 
}
