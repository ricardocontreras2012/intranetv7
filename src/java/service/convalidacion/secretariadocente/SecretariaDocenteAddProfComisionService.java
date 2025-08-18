/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convalidacion.secretariadocente;


import domain.model.Profesor;
import java.util.List;
import session.GenericSession;
import session.SecretariaSession;
import session.WorkSession;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteAddProfComisionService {

    public void service(GenericSession genericSession, SecretariaSession secreSession, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        List<Profesor> lprof =secreSession.getComision();
        lprof.add(ws.getProfesor());
        secreSession.setComision(lprof);

    }
}
