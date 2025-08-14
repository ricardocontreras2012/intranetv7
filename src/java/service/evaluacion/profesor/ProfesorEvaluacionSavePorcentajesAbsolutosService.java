/*
 * @(#)ProfesorEvaluacionSavePorcentajesAbsolutosService.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package service.evaluacion.profesor;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import java.util.Map;
import domain.repository.EvaluacionRepository;
import session.GenericSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.common.CommonEvaluacionUtil.resoloverDiferenciasTipos;
import static infrastructure.util.common.CommonEvaluacionUtil.resolverDiferenciasEvaluaciones;
import static infrastructure.util.common.CommonEvaluacionUtil.setModoEvaluacion;
import static infrastructure.util.common.CommonEvaluacionUtil.setearPorcentajesEvaluaciones;
import domain.repository.CursoTevaluacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ProfesorEvaluacionSavePorcentajesAbsolutosService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param parameters Todos los valores del formulario.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status.
     */
    public String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        Curso curso = genericSession.getWorkSession(key).getCurso();
        CursoTevaluacionRepository cursoTevaluacionRepository
                = ContextUtil.getDAO().getCursoTevaluacionRepository(ActionUtil.getDBUser());

        EvaluacionRepository evaluacionRepository
                = ContextUtil.getDAO().getEvaluacionRepository(ActionUtil.getDBUser());

        resoloverDiferenciasTipos(parameters, cursoTevaluacionRepository.find(curso), cursoTevaluacionRepository, curso);
        resolverDiferenciasEvaluaciones(parameters, evaluacionRepository.find(curso), evaluacionRepository, curso);
        setearPorcentajesEvaluaciones(parameters, evaluacionRepository, curso);
        setModoEvaluacion(curso, "A");
        LogUtil.setLogCurso(genericSession.getRut(), curso);

        return SUCCESS;
    }
}
