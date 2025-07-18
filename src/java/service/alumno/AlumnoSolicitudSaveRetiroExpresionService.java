/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import com.opensymphony.xwork2.Action;
import domain.model.Solicitud;
import infrastructure.support.SolicitudSupport;
import java.io.File;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import infrastructure.util.common.CommonSolicitudUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Ricardo
 */
public class AlumnoSolicitudSaveRetiroExpresionService {

    public String service(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, String causa, String key) {

        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();        
        Solicitud solicitud = ws.getSolicitud();
        solicitud.setSolMotivo(StringUtils.abbreviate(causa, 2000));
        new SolicitudSupport(solicitud).setGenerada();
        
        beginTransaction(user);        
        ContextUtil.getDAO().getSolicitudPersistence(user).save(solicitud);
        commitTransaction();

        CommonSolicitudUtil.saveAttach(action, genericSession, upload, uploadFileName, key);

        return Action.SUCCESS;
    }
}
