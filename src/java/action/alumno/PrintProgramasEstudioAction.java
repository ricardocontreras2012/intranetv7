/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.alumno.PrintProgramasEstudioService;
import infrastructure.support.action.ActionParameterAwareSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Administrador
 */
public class PrintProgramasEstudioAction extends ActionParameterAwareSupport {

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
            ais = new PrintProgramasEstudioService().service(this, getGenericSession(), getMapParameters(), getKey());

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

