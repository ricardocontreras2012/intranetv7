/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import action.alumno.AlumnoSolicitudExpedienteLogradosAction;
import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.LogUtil;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudExpedienteLogradosService {

    public String service(GenericSession genericSession, String key, AlumnoSolicitudExpedienteLogradosAction action) {
        WorkSession ws = genericSession.getWorkSession(key);

        String paramFecha = ContextUtil.getDAO().getParametroRepository(ActionUtil.getDBUser()).find("fecha_cierre_sol_logro").getParValor();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime fechaCierre = LocalDateTime.parse(paramFecha.trim(), formatter);
        
        Date fechaDB = getSysdate();
        LocalDateTime fechaFromDB = fechaDB.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();

        // Formatear al formato deseado (igual que fechaCierre)
        String fechaFormateada = fechaFromDB.format(formatter);
        LocalDateTime fechaActual = LocalDateTime.parse(fechaFormateada, formatter);        
        
        /*Si carrera corresponde a AP deben agregar 14 días adicionales */
        Integer tipoCarrera = ws.getAluCar().getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip();
        if (tipoCarrera==33) {
            fechaCierre = fechaCierre.plusDays(14);
        }
        
        if (fechaCierre.isAfter(fechaActual)) {
            action.setFechaSolLogro(true);
        } else {
            action.setFechaSolLogro(false);
        }

        ws.setExpedienteLogroList(ContextUtil.getDAO().getExpedienteLogroRepository(ActionUtil.getDBUser()).findGeneradas(ws.getAluCar()));
        
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
