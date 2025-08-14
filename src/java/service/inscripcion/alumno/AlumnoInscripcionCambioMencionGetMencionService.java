/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.inscripcion.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.AlumnoSession;
import session.GenericSession;

/**
 *
 * @author Usach
 */
public class AlumnoInscripcionCambioMencionGetMencionService {
 public String service(GenericSession genericSession, AlumnoSession alumnoSession,  String key) { 
        /*AluCar aluCar = genericSession.getWorkSession(key).getAluCar();
        Integer mencion = alumnoSession.getCambioMencion();
        
        String men = MencionSupport.getMencion(aluCar.getId().getAcaCodCar(), mencion);
  
        ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).cambioMencion(
                aluCar, alumnoSession.getCambioMencion(), aluCar.getParametros().getAgnoIns(), aluCar.getParametros().getSemIns());*/
        return SUCCESS;
    }
}
