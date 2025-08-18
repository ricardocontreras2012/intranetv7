/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.expediente.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;
import java.io.InputStream;
import service.expediente.alumno.AlumnoGeneraPagoAranceService;

/**
 *
 * @author Ricardo
 */
public class AlumnoGeneraPagoArancelAction extends  ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private ActionInputStreamUtil ais;

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
            ais = new AlumnoGeneraPagoAranceService().service(getGenericSession(), getKey());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.generated"));
        }

        return retValue;
    }

    public String getContentType() {
        return ais.getContentType();
    }
    
    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }
}

