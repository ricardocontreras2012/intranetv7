/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.Curso;
import domain.model.CursoId;
import static java.lang.Integer.parseInt;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.ActionResultSupport;
import infrastructure.support.HorarioSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class CommonCursoDefinicionHorarioSaveService {

    public static String service(GenericSession genericSession, Map<String, String[]> parameters, String key) {
        Integer position = parseInt(parameters.get("pos")[0]);
        String user = ActionUtil.getDBUser();

        // Crear lista de horarios usando streams
        List<HorarioSupport> horList = parameters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith("dia_"))
                .map(entry -> {
                    int id = parseInt(entry.getKey().substring(entry.getKey().lastIndexOf('_') + 1));
                    String dia = parameters.get("dia_" + id)[0];
                    String modulo = parameters.get("mod_" + id)[0];
                    String sala = parameters.get("sala_" + id)[0].toUpperCase(ContextUtil.getLocale());
                    String tipo = parameters.get("tipo_" + id)[0];
                    return new HorarioSupport(dia, Integer.parseInt(modulo), sala, tipo);
                })
                .collect(Collectors.toList());

        // Serializar la lista de horarios a JSON
        Gson gson = new GsonBuilder().create();
        String json = gson.toJsonTree(horList).getAsJsonArray().toString();

        // Guardar en la base de datos
        WorkSession ws = genericSession.getWorkSession(key);
        CursoId cursoId = ws.getCurso().getId();
        String result = ContextUtil.getDAO().getScalarPersistence(user)
                .saveHorarioSala(cursoId.getCurAsign(), cursoId.getCurElect(), cursoId.getCurCoord(), cursoId.getCurSecc(),
                        cursoId.getCurAgno(), cursoId.getCurSem(), json);

        // Obtener el curso y configurar el resultado de la acción
        Curso curAux = ContextUtil.getDAO().getCursoPersistence(user).find(ws.getCurso());
        String actionResult = Stream.of(
                curAux.getCurHorario() == null ? "" : curAux.getCurHorario(),
                curAux.getCurSalas() == null ? "" : curAux.getCurSalas(),
                curAux.getCurProfesores() == null ? "" : curAux.getCurProfesores(),
                curAux.getCurAyudantes() == null ? "" : curAux.getCurAyudantes(),
                String.valueOf(curAux.getCurEnableProf()),
                String.valueOf(curAux.getCurEnableAyu())
        )
                .collect(Collectors.joining(":"));

        // Configuración del resultado de la acción
        ActionResultSupport actionSupport = new ActionResultSupport();
        actionSupport.setActionResult(actionResult);
        if ("".equals(result)) {
            actionSupport.setActionStatus("Success");
        } else {
            actionSupport.setActionStatus("Error");
            actionSupport.setActionErrorMsg(result);
        }

        // Asignar el resultado y la posición en la sesión de trabajo
        ws.setActionSupport(actionSupport);
        ws.setPos(position);

        // Log de la operación
        LogUtil.setLogCurso(genericSession.getRut(), ws.getCurso());

        return SUCCESS;
    }
}
