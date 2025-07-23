/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util.common;

import domain.model.ActaCalificacionId;
import infrastructure.support.ActaConsultaSupport;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonActaUtil {

    /**
     *
     * @param user
     * @return
     */
    public static Integer getFolio(String user) {
        return ContextUtil.getDAO().getScalarRepository(user).getSecuenciaActa();
    }

    public static ActaConsultaSupport newActaView(ActaCalificacionId id,
            Integer asign,
            String coord,
            String elect,
            Integer secc,
            String tipo,
            String nombreCurso,
            String profesores,
            String estado
    ) {
        ActaConsultaSupport acta = new ActaConsultaSupport();

        acta.setId(id);
        acta.setNombreCurso(nombreCurso);
        acta.setProfesores(profesores);
        acta.setEstado(estado);
        acta.setAcalAsign(asign);
        acta.setAcalElect(elect);
        acta.setAcalCoord(coord);
        acta.setAcalSecc(secc);
        acta.setAcalTipo(tipo);

        return acta;
    }
}
