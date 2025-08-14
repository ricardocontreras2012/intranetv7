/*
 * @(#)GetSolicitudesService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.solicitud;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.Calendar;
import java.util.Date;
import session.GenericSession;
import session.WorkSession;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.DateUtil;
import static infrastructure.util.DateUtil.getSysdate;
import infrastructure.util.LogUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GetSolicitudesService {

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param estado
     * @param key LLave para acceder a los datos de la sesion.
     * @param inicio
     * @param termino
     * @return Action status.
     * @throws Exception Si el servico genera una exception.
     */
    public String service(GenericSession genericSession, String key, Integer estado, String inicio, String termino) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        Date fechaInicio;
        Date fechaFinal;

        if (estado == null) {
            estado = ContextUtil.getEstadoSolicitudList().get(0).getEsolCod();
        }

        if (inicio != null && !"".equals(inicio) && termino != null && !"".equals(termino)) {
            ws.setFechaInicio(inicio);
            ws.setFechaTermino(termino);

            fechaInicio = DateUtil.stringToDate(inicio);
            fechaFinal = DateUtil.stringToDate(termino);
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getSysdate());

            ws.setFechaInicio("01-01-" + calendar.get(Calendar.YEAR));
            ws.setFechaTermino("31-12-" + calendar.get(Calendar.YEAR));

            fechaInicio = DateUtil.stringToDate(ws.getFechaInicio());
            fechaFinal = DateUtil.stringToDate(ws.getFechaTermino());
        }

        ws.setSolicitudList(ContextUtil.getDAO().getSolicitudRepository(ActionUtil.getDBUser()).findSolicitudBetweenDates(genericSession.getRut(), genericSession.getUserType(), estado, fechaInicio, fechaFinal));
        LogUtil.setLog(genericSession.getRut());

        return SUCCESS;
    }
}
