/*
 * @(#)SaveModifiedAsistenciaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.asistencia;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AsistenciaAlumnoNomina;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.stringToDate;
import static infrastructure.util.FormatUtil.isNotNull;
import infrastructure.util.LogUtil;
import java.util.List;
import java.util.stream.IntStream;
import domain.repository.AsistenciaAlumnoNominaRepository;
import domain.repository.AsistenciaAlumnoRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class SaveModifiedAsistenciaService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param map
     * @param key LLave para acceder a los datos de la sesion.
     * @param fecha
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, Map<String, String[]> map, String key, String fecha) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String dbUser = ActionUtil.getDBUser();

        AsistenciaAlumnoNominaRepository asistenciaAlumnoNominaRepo
                = ContextUtil.getDAO().getAsistenciaAlumnoNominaRepository(dbUser);
        AsistenciaAlumnoRepository asistenciaAlumnoRepo
                = ContextUtil.getDAO().getAsistenciaAlumnoRepository(dbUser);

        Integer correl = ws.getAsistenciaAlumno().getAsaCorrel();

        // Actualiza la fecha de asistencia
        asistenciaAlumnoRepo.updateFecha(correl, stringToDate(fecha));

        // Recorre la lista con IntStream para evitar el uso de una variable de índice manual
        List<AsistenciaAlumnoNomina> asistenciaAlumnoNominaList = ws.getAsistenciaAlumnoNominaList();

        IntStream.range(0, asistenciaAlumnoNominaList.size()).forEach(i -> {
            AsistenciaAlumnoNomina asistenciaAlumnoNomina = asistenciaAlumnoNominaList.get(i);
            Integer asistio = isNotNull(map.get("ck_" + i)) ? 1 : 0;

            asistenciaAlumnoNominaRepo.updateNomina(asistenciaAlumnoNomina.getAluCar().getId(), correl, asistio);
        });

        // Actualiza la lista de asistencia y registra en el log
        ws.setAsistenciaAlumnoList(asistenciaAlumnoRepo.find(ws.getCurso()));
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }

}
