/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.inscripcion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.AluCar;
import domain.model.AluCarId;
import java.util.List;
import java.util.Map;
import session.GenericSession;
import session.JefeCarreraSession;
import session.WorkSession;
import infrastructure.support.InscripcionSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonInscripcionUtil;

/**
 *
 * @author Felipe
 */
public class CommonInscripcionAdmChangeNominaDerService {

    /*
        * Method Servicio
        *
        * @param action
        * @param genericSession Sesion de trabajo.
        * @param parameters Todos los valores del formulario.
        * @param key LLave para acceder a los datos de la sesion.
        * @return Action status
     */
    public String service(ActionCommonSupport action, GenericSession genericSession, JefeCarreraSession jcSession, Map<String, String[]> parameters, String key) {
        int retValue = 0;

        String result;
        WorkSession ws = genericSession.getWorkSession(key);

        String[] listaIzqArray = parameters.get("listaIzq");

        if (listaIzqArray != null && listaIzqArray.length > 0) {
            String listaIzqJson = listaIzqArray[0];

            Gson gson = new Gson();
            List<CommonInscripcionUtil.RowData> selectedRows = gson.fromJson(listaIzqJson, new TypeToken<List<CommonInscripcionUtil.RowData>>() {
            }.getType());

            for (CommonInscripcionUtil.RowData row : selectedRows) {
                int i = Integer.parseInt(row.getIdRow()) - 1;
                AluCarId aluCarId = jcSession.getNominaCursoAdmIzq().get(i).getId();

                AluCar aluCar = ContextUtil.getDAO().getAluCarRepository(ActionUtil.getDBUser()).find(aluCarId);
                aluCar.setInitValues();
                aluCar.setInsList(new InscripcionSupport(aluCar, genericSession).getInscripcion(ws.getAgnoAct(), ws.getSemAct()));

                result = aluCar.traspasoInscripcionCoordinador(action, genericSession, jcSession.getCursoAdmIzq(), jcSession.getCursoAdmDer());
                if (result.equals("error")) {
                    retValue = -1;
                }
            }
        }

        CommonCursoUtil.getCursos(genericSession, "*", key); //Cerrados, Transversales y Espejos
        ws = genericSession.getWorkSession(key);
        jcSession.setCursoAdmIzq(ws.getCursoList().get(jcSession.getPosIzq()));
        jcSession.setCursoAdmDer(ws.getCursoList().get(jcSession.getPosDer()));
        jcSession.setNominaCursoAdmIzq(jcSession.getCursoAdmIzq().getNominaAlumnosRanking());
        jcSession.setNominaCursoAdmDer(jcSession.getCursoAdmDer().getNominaAlumnosRanking());
        if (retValue == 0) {
            return SUCCESS;
        } else {
            return "no_elimina";
        }
    }
}
