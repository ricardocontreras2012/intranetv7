/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.solicitud.justificativo.alumno;

import com.opensymphony.xwork2.Action;
import java.io.File;
import session.GenericSession;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.common.CommonSolicitudUtil;

/**
 *
 * @author Usach
 */
public class AlumnoAddAttachService {

    public String service(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, String key) {

        CommonSolicitudUtil.saveAttach(action, genericSession, upload, uploadFileName, key);

        return Action.SUCCESS;
    }
}
