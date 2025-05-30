/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.verificacioncertificado;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.verificacioncertificado.VerificacionCertificadoDownLoadDocumentoService.service;
import infrastructure.support.action.post.ActionPostCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author rcontreras
 */
public class VerificacionCertificadoDownLoadDocumentoAction extends ActionPostCommonSupport {

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
            ais = service(getSesion());
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
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
