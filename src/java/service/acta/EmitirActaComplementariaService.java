/*
 * @(#)CommonComplementariaEmitirActaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.acta;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.Curso;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ParametroSesionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.FormatUtil.isNotNull;
import infrastructure.util.LogUtil;

/**
 * Servicio para emitir actas complementarias para un conjunto de cursos asociados
 * a un alumno en particular. Este servicio procesa las notas asociadas a los 
 * cursos y genera un acta complementaria para cada uno.
 * 
 * <p>Este servicio realiza las siguientes tareas:</p>
 * <ul>
 *   <li>Itera sobre los cursos asociados al alumno.</li>
 *   <li>Verifica si hay una calificación asociada a cada curso.</li>
 *   <li>Si la calificación es válida, genera una acta complementaria para el curso.</li>
 *   <li>Actualiza la lista de cursos en la sesión de trabajo con los cursos de la acta complementaria.</li>
 * </ul>
 * 
 * <p>Finalmente, limpia cualquier mensaje o error previo y agrega un mensaje 
 * indicando que el acta ha sido emitida correctamente.</p>
 * 
 * @author Ricardo Contreras S.
 */
public class EmitirActaComplementariaService {

    /**
     * Método principal para generar actas complementarias para los cursos de un alumno.
     * 
     * <p>Este método toma la sesión de trabajo del alumno, el mapa de parámetros y la clave
     * de la sesión para generar las actas complementarias. A medida que procesa cada curso,
     * verifica si tiene una calificación asociada. Si es así, se genera el acta complementaria
     * correspondiente.</p>
     * 
     * <p>El servicio también actualiza la lista de cursos asociados al alumno con los cursos
     * correspondientes a la acta complementaria, y limpia cualquier mensaje o error en el
     * proceso de ejecución.</p>
     * 
     * @param action Acción que se ejecuta (contiene los mensajes de error y éxito)
     * @param genericSession Sesión de trabajo del sistema que contiene información del usuario
     * @param map Mapa de parámetros que contiene las notas asociadas a cada curso
     * @param key Clave de la sesión de trabajo que se utiliza para obtener la sesión del alumno
     * @return El estado de la acción, normalmente {@link com.opensymphony.xwork2.Action#SUCCESS}
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, Map<String, String[]> map, String key) {
        // Se obtiene la sesión de trabajo y los parámetros asociados al alumno
        WorkSession ws = genericSession.getWorkSession(key);  
        AluCar aluCar = ws.getAluCar();
        ParametroSesionSupport parametros = aluCar.getParametros();
        
        // Obtención de la lista de cursos asociados al alumno
        List<Curso> cursos = ws.getCursoList();
        
        // Usamos un contador para llevar el índice del curso
        AtomicInteger nCurso = new AtomicInteger(1);

        // Iteramos sobre los cursos y generamos las actas complementarias
        cursos.forEach(curso -> {
            // Obtenemos la nota asociada al curso
            String[] tmp = map.get("nota_" + nCurso.get());

            // Si hay una nota válida, generamos el acta complementaria
            if (isNotNull(tmp)) {
                // Llamada al DAO para generar el acta complementaria para el curso
                ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser())
                    .generarActaComplementariaCoordinador(
                        aluCar.getId(),
                        curso.getId(),
                        new BigDecimal(tmp[0]),
                        "",
                        genericSession.getRut()
                    );
                
                // Registramos un log de la operación realizada
                LogUtil.setLog(genericSession.getRut(), aluCar.getId().getAcaRut() + " > " + curso.getCodigo(" "));
            }
            // Incrementamos el contador para el próximo curso
            nCurso.incrementAndGet();
        });

        // Actualizamos la lista de cursos en la sesión con los cursos de la acta complementaria
        ws.setCursoList(ContextUtil.getDAO().getActaCalificacionRepository(ActionUtil.getDBUser())
            .getCursosActaComplementaria(aluCar, parametros.getAgnoCal(), parametros.getSemCal(),
                                         genericSession.getRut(), genericSession.getUserType()));

        // Limpiamos errores previos y agregamos el mensaje de éxito
        action.clearErrorsAndMessages();
        action.addActionMessage(action.getText("message.acta.emitida"));

        // Retornamos el estado de la acción exitosa
        return SUCCESS;
    }
}
