/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.alumno.AlumnoSolicitudProrrogaConstanciaService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author rcontreras
 */
public class AlumnoSolicitudProrrogaConstanciaAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {
        String retValue = SUCCESS;
        try {
            ais = new AlumnoSolicitudProrrogaConstanciaService().service(getGenericSession(), Manager.getAlumnoSession(sesion), getKey());
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
}
