/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convenio.secretariaproyectos;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.convenio.secretariaproyectos.SecretariaProyectosPrintService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Ricardo
 */
public class SecretariaProyectosPrintAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = new SecretariaProyectosPrintService().service(getGenericSession(), Manager.getProyectoSession(sesion), getKey());
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
