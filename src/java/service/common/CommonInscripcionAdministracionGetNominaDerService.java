/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.JefeCarreraSession;

/**
 *
 * @author Ricardo
 */
public class CommonInscripcionAdministracionGetNominaDerService {

    public String service(JefeCarreraSession jcSession) {

        jcSession.setNominaCursoAdmDer(jcSession.getCursoAdmDer().getNominaAlumnosRanking());
        return SUCCESS;
    }
}
