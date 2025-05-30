/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoPracticaDeleteFileService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class AlumnoPracticaDeleteFileAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private Integer doc;

    @Override
    public String action() {
        return service( getGenericSession(), doc, getKey());
    }    

    public void setDoc(Integer doc) {
        this.doc = doc;
    }
}
