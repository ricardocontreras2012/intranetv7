/*
 * @(#)CommonComplementariaGetCursosService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ParametroSesionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Servicio para obtener los cursos asociados a un acta complementaria
 * para un alumno matriculado en un año y semestre específico.
 * 
 * <p>Este servicio maneja la obtención de los cursos disponibles para un
 * alumno, validando si el alumno está matriculado en el año y semestre
 * proporcionados. Si está matriculado, se consultan los cursos relacionados
 * con el acta complementaria para el alumno. Si no está matriculado, se
 * genera un error.</p>
 * 
 * <p>El servicio toma como parámetros el año académico, el semestre,
 * y el estado de la sesión del usuario para realizar las validaciones
 * necesarias y devolver los cursos correspondientes.</p>
 * 
 * @author Ricardo Contreras S.
 */
public class CommonActaComplementariaGetCursosService {

    /**
     * Obtiene los cursos asociados a un acta complementaria para el alumno
     * matriculado en el año y semestre indicados. Si el alumno no está matriculado,
     * se agrega un mensaje de error en la acción.
     * 
     * <p>Este método realiza la validación de la matrícula del alumno para
     * el año y semestre especificados, y en caso afirmativo, recupera la
     * lista de cursos asociados al acta complementaria. Si el alumno no está
     * matriculado, se agrega un error a la acción.</p>
     *
     * @param action La acción común que se utiliza para manejar el flujo de la aplicación.
     * @param genericSession La sesión general que contiene la información del usuario.
     * @param key La llave que permite acceder a la sesión del trabajo del usuario.
     * @param agnoCal El año académico (calificación) que se desea consultar.
     * @param semCal El semestre académico (calificación) que se desea consultar.
     * @return El resultado de la operación. Si todo es correcto, retorna {@link com.opensymphony.xwork2.Action.SUCCESS}.
     */
    public String service(ActionCommonSupport action, GenericSession genericSession,
            String key, Integer agnoCal, Integer semCal) {

        // Obtiene la sesión de trabajo para acceder a los datos del alumno
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();  // Obtiene los datos del alumno
        ParametroSesionSupport parametros = aluCar.getParametros();  // Obtiene los parámetros asociados al alumno

        // Si el año o semestre es nulo, se utilizan los valores predeterminados desde los parámetros del alumno
        agnoCal = (agnoCal == null) ? parametros.getAgnoCal() : agnoCal;
        semCal = (semCal == null) ? parametros.getSemCal() : semCal;

        // Se actualizan los parámetros si los valores proporcionados son diferentes
        if (agnoCal.intValue() != parametros.getAgnoCal()) {
            parametros.setAgnoCal(agnoCal);
        }
        if (semCal.intValue() != parametros.getSemCal()) {
            parametros.setSemCal(semCal);
        }

        // Verifica si el alumno está matriculado en el año y semestre proporcionados
        if (aluCar.estaMatriculado(agnoCal, semCal)) {
            aluCar.setIsAlumnoPropio(genericSession);  // Establece si el alumno es propio de la unidad

            // Si el alumno es propio, obtiene la lista de cursos asociados al acta complementaria
            if (aluCar.getIsAlumnoPropio()) {
                ws.setCursoList(ContextUtil.getDAO()
                        .getActaCalificacionRepository(ActionUtil.getDBUser())
                        .getCursosActaComplementaria(aluCar, agnoCal, semCal, genericSession.getRut(), genericSession.getUserType()));
            }
        } else {
            // Si el alumno no está matriculado, se limpia el mensaje de errores y se agrega uno nuevo
            action.clearActionErrors();
            action.addActionError(action.getText("error.alumno.no.matriculado") + " " + agnoCal + "/" + semCal);
        }

        return SUCCESS;  // Retorna el estado de éxito si todo fue correcto
    }
}
