/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import com.opensymphony.xwork2.Action;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.common.CommonSolicitudExpedienteUtil;
import java.io.File;
import session.GenericSession;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteUploadFileService {
    
    public static String service(ActionCommonSupport action, GenericSession genericSession, File[] upload, String[] uploadFileName, Integer tdoc, String key) {
        CommonSolicitudExpedienteUtil.saveAttach(action, genericSession, upload, uploadFileName, tdoc, key);
        
        return Action.SUCCESS;
    }   
}
