/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convalidacion.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ConvalidacionComision;
import domain.model.ConvalidacionComisionProf;
import domain.model.ConvalidacionComisionProfId;
import session.SecretariaSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteSaveComisionService {

    public String service(SecretariaSession secreSession)
            throws Exception {
        String user = ActionUtil.getDBUser();
        Integer correl = ContextUtil.getDAO().getScalarRepository(user).getSecuenciaComision();
        ConvalidacionComision comision = new ConvalidacionComision();
        comision.setCcoCod(correl);

        beginTransaction(user);
        ContextUtil.getDAO().getConvalidacionComisionRepository(user).makePersistent(comision);

        secreSession.getComision().stream()
                .forEach(profesor -> {
                    ConvalidacionComisionProf comisionProf = new ConvalidacionComisionProf();
                    ConvalidacionComisionProfId id = new ConvalidacionComisionProfId();
                    id.setCcopCod(correl);
                    id.setCcopRut(profesor.getProfRut());

                    comisionProf.setComision(comision);
                    comisionProf.setId(id);
                    comisionProf.setProfesor(profesor);

                    ContextUtil.getDAO()
                            .getConvalidacionComisionProfRepository(user)
                            .makePersistent(comisionProf);
                });

        commitTransaction();

        return SUCCESS;
    }
}
