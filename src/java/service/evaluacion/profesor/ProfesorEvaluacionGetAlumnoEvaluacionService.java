/*
 * @(#)ProfesorEvaluacionGetAlumnoEvaluacionService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.evaluacion.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Evaluacion;
import domain.repository.EvaluacionAlumnoRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.HibernateUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorEvaluacionGetAlumnoEvaluacionService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param pos Numero del registro seleccionado en el formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Integer pos, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        EvaluacionAlumnoRepository evaluacionAlumnoRepo
                = ContextUtil.getDAO().getEvaluacionAlumnoRepository(ActionUtil.getDBUser());
        Evaluacion evaluacion = ws.getEvaluacionList().get(pos);       
        
        HibernateUtil.beginTransaction(ActionUtil.getDBUser());
        evaluacionAlumnoRepo.sincronizaCurso(ws.getCurso().getId());
        HibernateUtil.commitTransaction();

        ws.setEvaluacion(evaluacion);
        ws.setEvaluacionAlumno(evaluacionAlumnoRepo.find(evaluacion));

        return SUCCESS;
    }
}
