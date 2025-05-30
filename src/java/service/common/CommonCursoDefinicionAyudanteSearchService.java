/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Ayudante;
import java.util.List;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.util.common.CommonAyudanteUtil.getAyudantePersona;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionAyudanteSearchService {

    public static String service(GenericSession genericSession, Integer rut, String paterno, String materno,
            String nombre, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        List<Ayudante> nomina = getAyudantePersona(rut, paterno, materno, nombre);

        ws.setAyudanteList(nomina);

        return SUCCESS;
    }
}
