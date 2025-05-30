/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.common.CommonReporteExportService.service;
import infrastructure.support.action.ActionParameterAwareSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Usach
 */
public class CommonReporteExportAction extends ActionParameterAwareSupport {
    private static final long serialVersionUID = 1L;
    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {        
         String retValue = SUCCESS;
        try {
            ais = service(getGenericSession(), getMapParameters(),getKey());
        
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }

     public String getContentType() {
        return ais.getContentType();
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    /**
     * Method description
     *
     * @return
     */
    public String getName() {
        return ais.getName();
    }
}
