/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.alumno.AlumnoCertificacionEmitirRankRegularService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Ricardo
 */
public class AlumnoCertificacionEmitirRankRegularAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    AlumnoCertificacionEmitirRankRegularService serviceCert = new AlumnoCertificacionEmitirRankRegularService();
    ActionInputStreamUtil ais;
    private Integer correl;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = serviceCert.service(correl);
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

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }
}
