/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.convenio.secretariaproyectos;

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
public class SecretariaProyectosPrintService {
    
    public ActionInputStreamUtil service(GenericSession genericSession, ProyectoSession ps, String key) throws Exception {     

        String name;
        InputStream input;
        ActionInputStreamUtil ais;
        
        Integer folio=ps.getConvenio().getConvNro();        
        name = "Contrato_" + folio + ".pdf";
        input = getInput(folio, name);
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
    private  InputStream getInput(Integer convenio, String name) {
        ConvenioSupport convenioSupport = new ConvenioSupport();
        return convenioSupport.print(convenio, name);
    }
}
