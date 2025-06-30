/*
 * @(#)ProfesorEvaluacionSaveAlumnoEvaluacionService.java
 *
 * Copyright (c) 2025 FAE-USACH
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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        String dbUser = ActionUtil.getDBUser();
        EvaluacionPersistence evaluacionPersistence = ContextUtil.getDAO().getEvaluacionPersistence(dbUser);

        List<EvaluacionAlumno> alumnos = ws.getEvaluacionAlumno();
        AtomicBoolean hayNotas = new AtomicBoolean(false);

        beginTransaction(dbUser);

        alumnos.forEach(evaluacionAlumno -> {
            String[] tmpNotaRut = parameters.get("row_" + evaluacionAlumno.getAluCar().getId().getAcaRut());

            if (isNotNull(tmpNotaRut)) {
                evaluacionAlumno.setEvaluNota(new BigDecimal(tmpNotaRut[0].trim()));
                ContextUtil.getDAO().getEvaluacionAlumnoPersistence(dbUser).makePersistent(evaluacionAlumno);
                hayNotas.set(true);  // marcar que al menos una nota fue ingresada
            }
        });

        if (hayNotas.get()) {
            Evaluacion evaluacion = ws.getEvaluacion();
            evaluacionPersistence.setStatusConNota(evaluacion);

            if (!"CN".equals(evaluacion.getEvalStatus())) {
                evaluacion.setEvalStatus("CN");
                evaluacionPersistence.makePersistent(evaluacion);
            }
        }

        commitTransaction();

        LogUtil.setLogCurso(genericSession.getRut(), ws.getEvaluacion().getCursoTevaluacion().getCurso());

        return ws.getCurso().getCurModalEval();
    }

}
