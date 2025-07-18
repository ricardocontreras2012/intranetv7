/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariadocente;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.secretariadocente.SecretariaDocenteConvalidacionImprimirInformeService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author rcontreras
 */
public class SecretariaDocenteConvalidacionImprimirInformeAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private ActionInputStreamUtil ais;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;
        try {
            ais = new SecretariaDocenteConvalidacionImprimirInformeService().service(getGenericSession(), Manager.getSecretariaSession(sesion), getKey());
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
