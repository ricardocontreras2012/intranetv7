/*
 * @(#)CommonActaGetActasCarreraService.java
 *
 * Copyright (c) 2023 FAE-USACH
 */
package service.acta;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActaConsultaSupport;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 * Servicio para obtener las actas de los cursos por carrera.
 *
 * Esta clase proporciona el método para obtener las actas asociadas a una carrera
 * en función del año, semestre, y tipo de usuario. El servicio consulta la base
 * de datos según los permisos del usuario para recuperar la información relevante.
 * Dependiendo del tipo de usuario, se realiza una consulta diferente para obtener
 * las actas correspondientes, ya sea por mencion, departamento o facultad.
 *
 * @author Ricardo Contreras S.
 * @version 1.0, 24/05/2023
 */
public final class CommonActaGetActasCarreraService {

    /**
     * Método para obtener las actas de una carrera específicas para un año y semestre dados.
     *
     * Este método consulta las actas correspondientes a una carrera específica,
     * basándose en el tipo de usuario y la carrera seleccionada en el sistema.
     * Se establecen los valores de año y semestre y luego se realiza la consulta
     * a la base de datos para recuperar las actas relacionadas con esa carrera.
     * 
     * @param genericSession La sesión del usuario que contiene la información
     *        de la sesión de trabajo.
     * @param key La llave única que identifica la sesión de trabajo.
     * @param pos El índice del registro seleccionado en el formulario que representa
     *        la carrera del estudiante.
     * @param agno El año académico para el cual se solicita la información de actas.
     * @param sem El semestre académico correspondiente a las actas solicitadas.
     * 
     * @return El estado de la acción, indicando si se ejecutó correctamente.
     *         En este caso, siempre devuelve el valor de éxito (SUCCESS).
     */
    public String service(GenericSession genericSession, String key, Integer pos, Integer agno, Integer sem) {

        // Recupera la sesión de trabajo utilizando la llave proporcionada
        WorkSession ws = genericSession.getWorkSession(key);
        
        // Obtiene la carrera seleccionada en la sesión de trabajo
        MiCarreraSupport miCarreraSupport = ws.getMiCarreraSupportList().get(pos);

        // Establece los valores de año y semestre en la sesión de trabajo
        ws.setAgnoAct(agno);
        ws.setSemAct(sem);
        ws.setNombreCarrera(miCarreraSupport.getNombreCarrera());

        // Obtiene la información relacionada con el tipo de carrera, especialidad y régimen
        Integer tipoCarrera = miCarreraSupport.getTcrCtip();
        Integer especialidad = miCarreraSupport.getEspCod();
        String regimen = miCarreraSupport.getRegimen();
        
        // Lista para almacenar las actas relacionadas con la carrera
        List<ActaConsultaSupport> actaList = new ArrayList<>();
        
        // Tipo de usuario que está realizando la consulta
        String userType = genericSession.getUserType();

        // Realiza las consultas según el tipo de usuario
        switch (userType) {
            case "SM":
            case "SP":
            case "JC":
            case "DP":
            case "JP":
                // Consulta por Mención
                ContextUtil.getDAO().getActaMencionViewRepository(ActionUtil.getDBUser())
                        .findMencion(agno, sem, tipoCarrera, especialidad, regimen, genericSession.getRut(), userType)
                        .forEach((acta) -> {
                            actaList.add(acta.getActaConsultaSupport());
                        });
                break;

            case "SD":
            case "DD":
                // Consulta por Departamento
                ContextUtil.getDAO().getActaMencionViewRepository(ActionUtil.getDBUser())
                        .findDepto(agno, sem, tipoCarrera, especialidad, regimen, genericSession.getRut(), userType)
                        .forEach((acta) -> {
                            actaList.add(acta.getActaConsultaSupport());
                        });
                break;

            case "SF":
            case "RC":
            case "OC":
            case "DE":
            case "VDD":
                // Consulta por Facultad
                ContextUtil.getDAO().getActaMencionViewRepository(ActionUtil.getDBUser())
                        .findFacultad(agno, sem, tipoCarrera, especialidad, regimen, genericSession.getRut(), userType)
                        .forEach((acta) -> {
                            actaList.add(acta.getActaConsultaSupport());
                        });
                break;
        }

        // Asigna las actas encontradas a la sesión de trabajo
        ws.setActaConsultaSupportList(actaList);

        // Retorna el estado de éxito de la acción
        return SUCCESS;
    }
}
