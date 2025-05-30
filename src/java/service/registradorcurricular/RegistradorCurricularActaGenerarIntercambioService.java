/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.EmisionNomina;
import domain.model.Mencion;
import domain.model.NominaCarrera;
import domain.model.NominaCarreraId;
import domain.repository.ActaCalificacionPersistence;
import domain.repository.EmisionNominaPersistence;
import domain.repository.NominaCarreraPersistence;
import persistence.scalar.ScalarPersistence;
import domain.repository.NominaActaPersistenceView;
import session.GenericSession;
import session.Manager;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;

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
    public static String service(ActionCommonSupport action, GenericSession genericSession, Integer sem, Integer agno, String key)
            throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        
        ws.setMencionList(ContextUtil.getDAO().getMencionPersistence(user).find(genericSession.getUserType(), genericSession.getRut()));
        
        ActaCalificacionPersistence actaPersistence
                = ContextUtil.getDAO().getActaCalificacionPersistence(user);
        ScalarPersistence nominaCarreraScalarPersistence
                = ContextUtil.getDAO().getScalarPersistence(user);
        NominaCarreraPersistence nominaCarreraPersistence
                = ContextUtil.getDAO().getNominaCarreraPersistence(user);
        EmisionNominaPersistence emisionPersistence
                = ContextUtil.getDAO().getEmisionNominaPersistence(user);
        NominaActaPersistenceView nominaActaPersistence
                = ContextUtil.getDAO().getNominaActaPersistenceView(user);
        Integer folio = nominaCarreraScalarPersistence.getSecuenciaNomina();

        EmisionNomina emision = new EmisionNomina();

        emision.setEmiCorrel(folio);
        emision.setEmiAgno(agno);
        emision.setEmiSem(sem);
        emision.setEmiTipo(10);
        emision.setEmiFecha(getSysdate());
        emision.setEmiReali(user);
        beginTransaction(user);
        emisionPersistence.save(emision);

        for (int i = 0; i < ws.getMencionList().size(); i++) {
            Mencion mencion = ws.getMencionList().get(i);
            NominaCarrera nominaCarrera = new NominaCarrera();
            NominaCarreraId nominaCarreraId = new NominaCarreraId();

            nominaCarreraId.setNcCodCar(mencion.getId().getMenCodCar());
            nominaCarreraId.setNcCodMen(mencion.getId().getMenCodMen());
            nominaCarreraId.setNcCorrel(folio);
            nominaCarrera.setId(nominaCarreraId);
            nominaCarrera.setNcAgno(agno);
            nominaCarrera.setNcSem(sem);
            nominaCarreraPersistence.save(nominaCarrera);
        }

        commitTransaction();
        actaPersistence.generaActasxCarrera(agno, sem, folio, "I");
        Manager.getRegistradorSession(action.getSesion()).setNominaActaViewList(nominaActaPersistence.find(agno, sem, folio));

        return SUCCESS;
    }
}
