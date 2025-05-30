/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.AlumnoSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionCheckLogroEnTramiteService {
     public static String service(AlumnoSession alumnoSession, Integer pos) {
        alumnoSession.setTipoCert(alumnoSession.getCertList().get(pos));        
       
        return SUCCESS;
    } 
}
