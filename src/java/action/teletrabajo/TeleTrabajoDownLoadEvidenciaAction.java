/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.teletrabajo;

import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import static service.teletrabajo.TeleTrabajoDownLoadEvidenciaService.service;
import session.Manager;
import infrastructure.support.action.post.ActionPostValidationSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 *
 * @author Ricardo
 */
public class TeleTrabajoDownLoadEvidenciaAction extends ActionPostValidationSupport {

    ActionInputStreamUtil ais;
    private Integer posTarea;

    /**
     * Method description
     *
     * @return Action status.
     */
    @Override
    public String action() {
        String retValue = SUCCESS;

        try {
            ais = service(getGenericSession(), Manager.getTeleTrabajoSession(sesion), getPos(), posTarea);
        } catch (Exception e) {
            retValue = "exception";
            this.addActionError(this.getText("error.file.not.found"));
        }

        return retValue;
    }
    
    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        return isValidPos(posTarea, Manager.getTeleTrabajoSession(sesion).getTareaList());
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

    public Integer getPosTarea() {
        return posTarea;
    }

    public void setPosTarea(Integer posTarea) {
        this.posTarea = posTarea;
    }
    
}
