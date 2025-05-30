/*
 * @(#)CommonAsistenciaUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.AsistenciaAlumnoNomina;
import java.util.List;
import session.WorkSession;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonAsistenciaUtil {

    private CommonAsistenciaUtil() {
    }

    /**
     * Method description
     *
     * @param ws
     * @param correl
     * @param rut
     * @return
     */
    public static boolean asiste(WorkSession ws, Integer correl, Integer rut) {
        return (findAsistencia(ws.getAsistenciaAlumnoNominaList(), correl, rut) == 1);
    }

    /**
     * Method description
     *
     * @param asistenciaList
     * @param correl
     * @param rut
     * @return
     */
    public static int findAsistencia(List<AsistenciaAlumnoNomina> asistenciaList, Integer correl, Integer rut) {
        return asistenciaList.stream()
                .filter(asistencia -> asistencia.getId().getAanCorrel().equals(correl)
                && asistencia.getId().getAanRut().equals(rut))
                .map(AsistenciaAlumnoNomina::getAanAsistio)
                .findFirst()
                .orElse(0);
    }
}
