/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import com.opensymphony.xwork2.Action;
import domain.model.Solicitud;
import domain.model.ExpedienteLogro;
import infrastructure.support.SolicitudSupport;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import org.apache.commons.lang3.StringUtils;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteAddSolicitudService {

    public String service(ActionCommonSupport action, GenericSession genericSession, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        Solicitud solicitud = ws.getSolicitud();
        solicitud.setSolMotivo(StringUtils.abbreviate("Expediente", 2000));
        solicitud.setSolSolicita(ws.getExpedienteLogro().getPlanLogro().getPlalNomLogro());
        new SolicitudSupport(solicitud).setGenerada();

        ExpedienteLogro expLogro = ws.getExpedienteLogro();

        try {
            ContextUtil.getDAO().getExpedienteLogroPersistence(ActionUtil.getDBUser()).saveExpedienteSolicitud(expLogro, solicitud.getSolFolio());
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Índice fuera de rango: ");
        } catch (NullPointerException e) {
            System.err.println("Referencia nula encontrada: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
        }

        beginTransaction(user);
        ContextUtil.getDAO().getSolicitudPersistence(user).save(solicitud);
        commitTransaction();

        //CommonSolicitudUtil.saveAttach(action, genericSession, upload, uploadFileName, key);
        return Action.SUCCESS;
    }
}
