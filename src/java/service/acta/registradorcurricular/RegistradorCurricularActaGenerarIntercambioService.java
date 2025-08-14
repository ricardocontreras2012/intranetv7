/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.acta.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.EmisionNomina;
import domain.model.NominaCarrera;
import domain.model.NominaCarreraId;
import domain.repository.EmisionNominaRepository;
import domain.repository.NominaCarreraRepository;

import session.GenericSession;
import session.Manager;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import domain.repository.ActaCalificacionRepository;
import domain.repository.NominaActaViewRepository;
import persistence.scalar.ScalarRespository;

/**
 *
 * @author Ricardo
 */
public class RegistradorCurricularActaGenerarIntercambioService {

    /**
     *
     * @param action
     * @param genericSession
     * @param sem
     * @param agno
     * @param key
     * @return
     * @throws Exception
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Integer sem, Integer agno, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();

        ws.setMencionList(ContextUtil.getDAO().getMencionRepository(user).find(genericSession.getUserType(), genericSession.getRut()));

        ActaCalificacionRepository actaRepository
                = ContextUtil.getDAO().getActaCalificacionRepository(user);
        ScalarRespository nominaCarreraScalarRepository
                = ContextUtil.getDAO().getScalarRepository(user);
        NominaCarreraRepository nominaCarreraRepository
                = ContextUtil.getDAO().getNominaCarreraRepository(user);
        EmisionNominaRepository emisionRepository
                = ContextUtil.getDAO().getEmisionNominaRepository(user);
        NominaActaViewRepository nominaActaRepository
                = ContextUtil.getDAO().getNominaActaViewRepository(user);
        Integer folio = nominaCarreraScalarRepository.getSecuenciaNomina();

        EmisionNomina emision = new EmisionNomina();

        emision.setEmiCorrel(folio);
        emision.setEmiAgno(agno);
        emision.setEmiSem(sem);
        emision.setEmiTipo(10);
        emision.setEmiFecha(getSysdate());
        emision.setEmiReali(user);
        beginTransaction(user);
        emisionRepository.save(emision);

        ws.getMencionList().forEach(mencion -> {
            NominaCarrera nominaCarrera = new NominaCarrera();
            NominaCarreraId nominaCarreraId = new NominaCarreraId();

            nominaCarreraId.setNcCodCar(mencion.getId().getMenCodCar());
            nominaCarreraId.setNcCodMen(mencion.getId().getMenCodMen());
            nominaCarreraId.setNcCorrel(folio);

            nominaCarrera.setId(nominaCarreraId);
            nominaCarrera.setNcAgno(agno);
            nominaCarrera.setNcSem(sem);

            nominaCarreraRepository.save(nominaCarrera);
        });

        commitTransaction();
        actaRepository.generaActasxCarrera(agno, sem, folio, "I");
        Manager.getRegistradorSession(action.getSesion()).setNominaActaViewList(nominaActaRepository.find(agno, sem, folio));

        return SUCCESS;
    }
}
