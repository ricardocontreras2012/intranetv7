/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.convalidacion.secretariadocente;

import java.io.File;
import service.convalidacion.secretariadocente.SecretariaDocenteGetExcelService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteGetExcelAction extends ActionCommonSupport {

    private File file;

    @Override
    public String action() throws Exception {
        return new SecretariaDocenteGetExcelService().service(getGenericSession(),Manager.getSecretariaSession(sesion), getKey(), file);
    }

    /**
     *
     * @param file
     */
    public void setUpload(File file) {
        this.file = file;
    }
}
