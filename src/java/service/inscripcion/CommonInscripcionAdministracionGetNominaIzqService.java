/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.inscripcion;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.JefeCarreraSession;

/**
 *
 * @author Ricardo
 */
public class CommonInscripcionAdministracionGetNominaIzqService {

    public String service(JefeCarreraSession jcSession) {
        jcSession.setNominaCursoAdmIzq(jcSession.getCursoAdmIzq().getNominaAlumnosRanking());
        return SUCCESS;
    }
}
