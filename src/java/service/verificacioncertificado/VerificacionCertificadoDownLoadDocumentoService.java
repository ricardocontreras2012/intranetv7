/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.verificacioncertificado;

import java.io.InputStream;
import session.VerificacionCertificadoSession;
import static infrastructure.util.SystemParametersUtil.PATH_CERT;
import java.util.Map;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.FormatUtil;
import infrastructure.util.common.CommonArchivoUtil;

/**
 *
 * @author rcontreras
 */
public class VerificacionCertificadoDownLoadDocumentoService {
    
    public static ActionInputStreamUtil service(Map<String, Object> sesionMap) throws Exception
    {
        String name;
        InputStream input;
        String description;

        VerificacionCertificadoSession session
                = (VerificacionCertificadoSession) sesionMap.get("verificacionCertificadoSession");

        name = session.getArchivo();
    
        input = getInput(name);
        description = FormatUtil.getMimeType(name);
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
            if ( CommonArchivoUtil.exists (PATH_CERT+name) )
            {
                retValue = CommonArchivoUtil.getFile(name,"cert");
            }
        }

        return retValue;
    }
}
