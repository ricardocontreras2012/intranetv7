/*
 * @(#)TeletrabajoAsignarGetHorarioService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import infrastructure.util.DateUtil;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.support.TeleTrabajoSupport;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Felipe
 * @version 1.0, 25/09/2023
 */
public final class TeleTrabajoAsignarGetHorarioService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param teleSession
     * @param inicio
     * @param termino
     * @param inicioSemana
     * @param terminoSemana
     * @param key LLave para acceder a los datos de la sesion.
     * @param inicioPos
     * @param terminoPos
     * @return Action status.
     * @throws java.text.ParseException
     */
    public static String service(GenericSession genericSession, TeleTrabajoSession teleSession, String inicio, String termino, String inicioSemana, String terminoSemana, String inicioPos, String terminoPos, String key) throws ParseException {
        
        Date fechaI = DateUtil.stringToDate(inicioSemana);
        Date fechaT = DateUtil.stringToDate(terminoSemana);
        
        int dias = (int) ((fechaT.getTime() - fechaI.getTime())/(1000*60*60*24));
        
        Calendar c = Calendar.getInstance();
        c.setTime(fechaI);
        
        teleSession.setActividadList(ContextUtil.getDAO().getActividadTeletrabajoPersistence("TT").findActividadesBetweenDates(teleSession.getFuncionarioTeletrabajo().getFtelRut(), fechaI, fechaT));

        TeleTrabajoSupport[] horario = new TeleTrabajoSupport[dias+1];
        
        for (int i = 0; i <= dias; i++) {            
            int estado = 0;
            Date fecha = c.getTime();
            TeleTrabajoSupport horarioAux = new TeleTrabajoSupport();
            
            for (int j = 0; j < teleSession.getActividadList().size(); j++) {
                if (fecha.equals(teleSession.getActividadList().get(j).getId().getAtelFecha())) {
                    estado = 1;
                    horarioAux.setEstado(teleSession.getActividadList().get(j).getEstado().getEatDes());
                }
            }
            horarioAux.setFecha(fecha);
            if (estado == 0) {
                horarioAux.setEstado(null);
            }
            
            horario[i] = horarioAux;

            c.add(Calendar.DATE, 1);
        }
        
        teleSession.setHorarioFuncionario(horario);
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
