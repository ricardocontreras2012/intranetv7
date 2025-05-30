/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import java.io.InputStream;
import infrastructure.support.action.common.ActionCommonSupport;
import service.common.CommonPAFDownloadPAFService;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Javier Bastián Frez Valdenegro
 */
public class CommonPAFDownloadPAFAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private CommonPAFDownloadPAFService serviceCert = new CommonPAFDownloadPAFService();
    private ActionInputStreamUtil ais;
    private Integer rut;
    private String keyProf;
    
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = serviceCert.service(rut, this.getKey(), this.keyProf);
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }

    public String getDescription() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }
    
}
