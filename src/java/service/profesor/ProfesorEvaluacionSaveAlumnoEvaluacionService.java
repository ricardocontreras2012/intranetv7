/*
 * @(#)ProfesorEvaluacionSaveAlumnoEvaluacionService.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package service.profesor;

import domain.model.Evaluacion;
import domain.model.EvaluacionAlumno;
import java.math.BigDecimal;
import java.util.Map;
import domain.repository.EvaluacionPersistence;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.isNotNull;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorEvaluacionSaveAlumnoEvaluacionService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        EvaluacionPersistence evaluacionPersistence
                = ContextUtil.getDAO().getEvaluacionPersistence(ActionUtil.getDBUser());

        beginTransaction(ActionUtil.getDBUser());

        EvaluacionAlumno evaluacionAlumno = null;
        boolean hayNotas = false;

        for (EvaluacionAlumno evaluacionAlumno1 : ws.getEvaluacionAlumno()) {
            evaluacionAlumno = evaluacionAlumno1;

            String[] tmpNotaRut = parameters.get("row_" + evaluacionAlumno.getAluCar().getId().getAcaRut());

            if (isNotNull(tmpNotaRut)) {
                evaluacionAlumno.setEvaluNota(new BigDecimal(tmpNotaRut[0].trim()));
                ContextUtil.getDAO().getEvaluacionAlumnoPersistence(ActionUtil.getDBUser()).makePersistent(evaluacionAlumno);
                hayNotas = true;
            }
        }

        if (hayNotas) {
            evaluacionPersistence.setStatusConNota(ws.getEvaluacion());

            Evaluacion evaluacion = evaluacionAlumno.getEvaluacion();

            if (!"CN".equals(evaluacion.getEvalStatus())) {
                evaluacion.setEvalStatus("CN");
                evaluacionPersistence.makePersistent(evaluacion);
            }
        }

        commitTransaction();
        
        LogUtil.setLogCurso(genericSession.getRut(), ws.getEvaluacion().getCursoTevaluacion().getCurso());
        return genericSession.getWorkSession(key).getCurso().getCurModalEval();
    }
}
