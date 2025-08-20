/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.inscripcion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.List;
import java.util.Map;
import session.GenericSession;
import session.JefeCarreraSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonCursoUtil;
import infrastructure.util.common.CommonInscripcionUtil;

/**
 *
 * @author Felipe
 */
public class RemoveNominaIzqService {

    /*
        * Method Servicio
        *
        * @param action
        * @param genericSession Sesion de trabajo.
        * @param parameters Todos los valores del formulario.
        * @param key LLave para acceder a los datos de la sesion.
        * @return Action status
     */
    public String service(GenericSession genericSession, JefeCarreraSession jcSession,
            Map<String, String[]> parameters, String key) {

        String[] listaIzqArray = parameters.get("listaIzq");
        int retValue = 0;   
        
        if (listaIzqArray != null && listaIzqArray.length > 0) {
            String listaIzqJson = listaIzqArray[0];

             Gson gson = new Gson();
            List<CommonInscripcionUtil.RowData> selectedRows = gson.fromJson(listaIzqJson, new TypeToken<List<CommonInscripcionUtil.RowData>>(){}.getType());

            for (CommonInscripcionUtil.RowData row : selectedRows) {
                int i = Integer.parseInt(row.getIdRow()) -1;
                retValue = ContextUtil.getDAO().getInscripcionRepository(ActionUtil.getDBUser()).deleteInscripcion(
                        jcSession.getNominaCursoAdmIzq().get(i), jcSession.getCursoAdmIzq().getId(), "DEL_COORD", genericSession.getRut(), genericSession.getUserType());
            }
        }

        commitTransaction();
        CommonCursoUtil.getCursos(genericSession, "*", key); //Cerrados, Transversales y Espejos
        WorkSession ws = genericSession.getWorkSession(key);
        jcSession.setCursoAdmIzq(ws.getCursoList().get(jcSession.getPosIzq()));

        if (retValue == 0) {
            return SUCCESS;
        } else {
            return "no_elimina";
        }
    }

}
