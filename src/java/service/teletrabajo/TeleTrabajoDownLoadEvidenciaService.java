/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.teletrabajo;

import domain.model.EvidenciaTareaActTeletrabajo;
import domain.model.TareaActividadTeletrabajo;
import java.io.InputStream;
import session.GenericSession;
import session.TeleTrabajoSession;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.ContextUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.LogUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 *
 * @author Ricardo
 */
public class TeleTrabajoDownLoadEvidenciaService {
public static ActionInputStreamUtil service(GenericSession genericSession, TeleTrabajoSession sesion, Integer pos, Integer posTarea) throws Exception
    {
        String name;
        InputStream input;
        String description;
        
        TareaActividadTeletrabajo tareaActividadTeletrabajo = sesion.getTareaList().get(posTarea);
        EvidenciaTareaActTeletrabajo evidenciaTareaActTeletrabajo = ContextUtil.getDAO().getEvidenciaTarActTeletrabajoPersistence("TT").find(tareaActividadTeletrabajo.getId().getTatelRut(), tareaActividadTeletrabajo.getId().getTatelFecha(), tareaActividadTeletrabajo.getId().getTatelTarea(), pos);
        
        name = evidenciaTareaActTeletrabajo.getEtatelFile();
        input = getInput(name);                
        description = FormatUtil.getMimeType(name);
        
        LogUtil.setLog(genericSession.getRut());

        return new ActionInputStreamUtil(name, description, input);
    }

  

    /**
     * Method description
     *
     * @param name
     * @return
     * @throws java.lang.Exception
     */
    private static InputStream getInput(String name) throws Exception {
        InputStream retValue = null;
 
        if (name != null) {
            if ( CommonArchivoUtil.exists (SystemParametersUtil.PATH_TELE_TRABAJO+name) )
            {
                retValue = CommonArchivoUtil.getFile(name,"tele");
            }
        }
        
        return retValue;
    }
}
