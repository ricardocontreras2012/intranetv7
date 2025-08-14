/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.secretariadocente;

import java.io.File;
import service.convalidacion.secretariadocente.SecretariaDocenteConvalidacionGetExcelService;
import session.Manager;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class SecretariaDocenteConvalidacionGetExcelAction extends ActionCommonSupport {

    private File file;

    @Override
    public String action() throws Exception {
        return new SecretariaDocenteConvalidacionGetExcelService().service(getGenericSession(),Manager.getSecretariaSession(sesion), getKey(), file);
    }

    /**
     *
     * @param file
     */
    public void setUpload(File file) {
        this.file = file;
    }
}
