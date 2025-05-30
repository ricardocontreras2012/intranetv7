/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.ElectivoId;
import java.util.Map;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MiCarreraSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionRemoveElectivosService {

    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        // Eliminar los electivos seleccionados
        ws.getElectivoList().stream()
                .filter(electivo -> parameters.containsKey("ck_" + ws.getElectivoList().indexOf(electivo))) // Verificar si el parámetro existe
                .forEach(electivo -> {
                    ElectivoId id = electivo.getId();
                    // Realizar la eliminación del electivo
                    ContextUtil.getDAO().getElectivoPersistence(ActionUtil.getDBUser())
                            .delete(id.getEleAsign(), id.getEleElect(), id.getEleAgno(), id.getEleSem());
                });

        // Actualizar la lista de electivos
        MiCarreraSupport carrera = ws.getMiCarreraSupport();
        ws.setElectivoList(ContextUtil.getDAO().getElectivoPersistence(ActionUtil.getDBUser())
                .find(carrera.getTcrCtip(), carrera.getEspCod(), ws.getAgnoAct(), ws.getSemAct(),
                        genericSession.getRut(), genericSession.getUserType()));

        return SUCCESS;
    }

}
