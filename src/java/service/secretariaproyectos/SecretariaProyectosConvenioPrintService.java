/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.secretariaproyectos;

import java.io.InputStream;
import session.GenericSession;
import session.ProyectoSession;
import infrastructure.support.ConvenioSupport;
import infrastructure.util.ActionInputStreamUtil;
import infrastructure.util.AppStaticsUtil;
import infrastructure.util.LogUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosConvenioPrintService {
    
    public static ActionInputStreamUtil service(GenericSession genericSession, ProyectoSession ps, String key) throws Exception {     

        String name;
        InputStream input;
        ActionInputStreamUtil ais;
        ps.setConvenio(ps.getConvenio());

        Integer folio=ps.getConvenio().getConvNro();
        
        name = "Contrato_" + folio + ".pdf";
        input = getInput(ps, name, key);
        ais = new ActionInputStreamUtil(name, AppStaticsUtil.PDF_MIME, input);
        LogUtil.setLog(genericSession.getRut(),folio);

        return ais;         
    }            
            
    
    /**
     * Method description
     *
     *
     * @param genericSession
     * @param name
     * @param key
     *
     * @return
     */
    private static InputStream getInput(ProyectoSession ps, String name, String key) {
        ConvenioSupport convenioSupport = new ConvenioSupport();
        return convenioSupport.print(ps.getConvenio().getConvNro(), name);

    }
}
