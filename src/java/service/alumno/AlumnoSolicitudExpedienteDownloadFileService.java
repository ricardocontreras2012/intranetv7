/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.alumno;

import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.common.CommonArchivoUtil;
import java.io.InputStream;
import session.GenericSession;
import session.WorkSession;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteDownloadFileService {

    public ActionInputStreamUtil service(GenericSession genericSession, Integer tdoc, String key) throws Exception {
        String name;
        InputStream input;
        String description;

        WorkSession ws = genericSession.getWorkSession(key);
        String user = ActionUtil.getDBUser();
        ws.setEstadoDocExpList(ContextUtil.getDAO().getEstadoDocExpRepository(user).find(ws.getExpedienteLogro().getId()));

        for (Integer i = 0; i < ws.getEstadoDocExpList().size(); i++) {
            if (ws.getEstadoDocExpList().get(i).gettDocExpediente().getTdeCod().equals(tdoc)) {
                name = ws.getEstadoDocExpList().get(i).getEdeFile();
                input = CommonArchivoUtil.getFile(name, "tit");
                description = FormatUtil.getMimeType(name);
                
                LogUtil.setLog(genericSession.getRut(), name);
                return new ActionInputStreamUtil(name, description, input);
            }
        }
        
        return null;
    }
}
