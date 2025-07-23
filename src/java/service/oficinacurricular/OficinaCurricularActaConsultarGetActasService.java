/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.oficinacurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import domain.repository.ActaCalificacionRepository;

/**
 *
 * @author Ricardo
 */
public class OficinaCurricularActaConsultarGetActasService {

    public String service(GenericSession genericSession, String flag, String key)
            throws Exception {

        WorkSession ws = genericSession.getWorkSession(key);
        ActaCalificacionRepository actaRepository
                = ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser());

        ws.setActas(actaRepository.findActasxEstado(ws.getAgnoAct(), ws.getSemAct(), flag));

        return SUCCESS;
    }
}
