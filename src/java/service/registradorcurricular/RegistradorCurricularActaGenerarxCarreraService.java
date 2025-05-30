/*
 * @(#)RegistradorCurricularActaGenerarxCarreraService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.registradorcurricular;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.EmisionNomina;
import domain.model.NominaCarrera;
import domain.model.NominaCarreraId;
import java.util.Map;
import domain.repository.ActaCalificacionPersistence;
import domain.repository.EmisionNominaPersistence;
import domain.repository.NominaCarreraPersistence;
import persistence.scalar.ScalarPersistence;
import domain.repository.NominaActaPersistenceView;
import session.GenericSession;
import session.RegistradorSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import java.util.Optional;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class RegistradorCurricularActaGenerarxCarreraService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param registradorSession
     * @param parameters Todos los valores del formulario.
     * @param agno
     * @param sem
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */    
    public static String service(GenericSession genericSession, RegistradorSession registradorSession,
            Map<String, String[]> parameters, Integer agno, Integer sem, String key) {

        String user = ActionUtil.getDBUser();

        // Obtener persistencias
        ActaCalificacionPersistence actaPersistence = ContextUtil.getDAO().getActaCalificacionPersistence(user);
        ScalarPersistence nominaCarreraScalarPersistence = ContextUtil.getDAO().getScalarPersistence(user);
        NominaCarreraPersistence nominaCarreraPersistence = ContextUtil.getDAO().getNominaCarreraPersistence(user);
        EmisionNominaPersistence emisionPersistence = ContextUtil.getDAO().getEmisionNominaPersistence(user);
        NominaActaPersistenceView nominaActaPersistence = ContextUtil.getDAO().getNominaActaPersistenceView(user);

        Integer folio = nominaCarreraScalarPersistence.getSecuenciaNomina();
        WorkSession ws = genericSession.getWorkSession(key);

        // Crear instancia de EmisionNomina usando setters
        EmisionNomina emision = new EmisionNomina();
        emision.setEmiCorrel(folio);
        emision.setEmiAgno(agno);
        emision.setEmiSem(sem);
        emision.setEmiTipo(10);
        emision.setEmiFecha(getSysdate());
        emision.setEmiReali(user);

        beginTransaction(user);
        emisionPersistence.save(emision);

        // Obtener el flag de forma segura usando Optional
        String flag = Optional.ofNullable(parameters.get("flag"))
                .map(arr -> arr.length > 0 ? arr[0] : "")
                .orElse("");

        // Filtrar y guardar solo menciones seleccionadas usando Stream API
        ws.getMencionList().stream()
                .filter(mencion -> parameters.containsKey("ck_" + ws.getMencionList().indexOf(mencion)))
                .forEach(mencion -> {
                    NominaCarreraId nominaCarreraId = new NominaCarreraId();
                    nominaCarreraId.setNcCodCar(mencion.getId().getMenCodCar());
                    nominaCarreraId.setNcCodMen(mencion.getId().getMenCodMen());
                    nominaCarreraId.setNcCorrel(folio);

                    NominaCarrera nominaCarrera = new NominaCarrera();
                    nominaCarrera.setId(nominaCarreraId);
                    nominaCarrera.setNcAgno(agno);
                    nominaCarrera.setNcSem(sem);

                    nominaCarreraPersistence.save(nominaCarrera);
                });

        commitTransaction();
  
        actaPersistence.generaActasxCarrera(agno, sem, folio, flag);  
        registradorSession.setNominaActaViewList(nominaActaPersistence.find(agno, sem, folio));
        
        return SUCCESS;
    }
}
