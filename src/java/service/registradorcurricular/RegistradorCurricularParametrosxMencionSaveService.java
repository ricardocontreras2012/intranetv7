/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.registradorcurricular;

import com.opensymphony.xwork2.Action;
import java.util.Date;
import java.util.Map;
import session.GenericSession;
import session.RegistradorSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.LogUtil;

/**
 *
 * @author Usach
 */
public class RegistradorCurricularParametrosxMencionSaveService {

    public static String service(GenericSession genericSession, RegistradorSession rs, Map<String, String[]> parameters, String key) {

        // Obtener parámetros de manera optimizada
        Integer pmenCodCar = parseInteger(parameters.get("pmenCodCar"));
        Integer pmenCodMen = parseInteger(parameters.get("pmenCodMen"));
        Integer pmenAgnoAct = parseInteger(parameters.get("pmenAgnoAct"));
        Integer pmenSemAct = parseInteger(parameters.get("pmenSemAct"));
        Integer pmenAgnoCal = parseInteger(parameters.get("pmenAgnoCal"));
        Integer pmenSemCal = parseInteger(parameters.get("pmenSemCal"));
        Integer pmenAgnoEnc = parseInteger(parameters.get("pmenAgnoEnc"));
        Integer pmenSemEnc = parseInteger(parameters.get("pmenSemEnc"));
        Integer pmenAgnoIns = parseInteger(parameters.get("pmenAgnoIns"));
        Integer pmenSemIns = parseInteger(parameters.get("pmenSemIns"));

        // Obtener fechas de manera optimizada
        Date pmenInsPostInicio = parseDate(parameters.get("pmenInsPostInicio"));
        Date pmenInsPostTermino = parseDate(parameters.get("pmenInsPostTermino"));
        Date pmenInsAdmTermino = parseDate(parameters.get("pmenInsAdmTermino"));
        Date pmenInsModTermino = parseDate(parameters.get("pmenInsModTermino"));
        Date pmenInsEliminTermino = parseDate(parameters.get("pmenInsEliminTermino"));
        String pmenInsLock = parameters.get("pmenInsLock")[0];
  
        beginTransaction(ActionUtil.getDBUser());
        // Ahora ejecutamos el SQL para actualizar la base de datos directamente
        ContextUtil.getDAO().getParametroMencionPersistence(ActionUtil.getDBUser()).updateParametroMencion(pmenCodCar, pmenCodMen, pmenAgnoAct, pmenSemAct,
                pmenAgnoCal, pmenSemCal, pmenAgnoEnc, pmenSemEnc,
                pmenAgnoIns, pmenSemIns, pmenInsPostInicio,
                pmenInsPostTermino, pmenInsAdmTermino,
                pmenInsModTermino, pmenInsEliminTermino, pmenInsLock);
        commitTransaction();

        LogUtil.setLog(genericSession.getRut(), rs.getMencion().getNombreCarreraFull()); 
            
        return Action.SUCCESS;
    }
    
    private static Integer parseInteger(String[] param) {
        try {
            return Integer.parseInt(param[0]);
        } catch (NumberFormatException e) {
            return null; // o manejar el error como sea necesario
        }
    }

    // Método para convertir un String a Date con manejo de excepciones
    private static Date parseDate(String[] param) {
        try {
            return DateUtil.parseDate(param[0]);
        } catch (Exception e) {
            return null; // o manejar el error como sea necesario
        }
    }
}
