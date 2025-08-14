/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.certificacion.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionCheckRegularService {

    public String service(GenericSession genericSession,
            String key) {        
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        ws.setMatriculaList(ContextUtil.getDAO().getMatriculaHistoricoRepository(ActionUtil.getDBUser()).findMatCert(aluCar,genericSession.getUserType()));        
        
        return SUCCESS;
    }
}
