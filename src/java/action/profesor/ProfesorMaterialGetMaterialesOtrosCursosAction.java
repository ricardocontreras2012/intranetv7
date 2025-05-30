/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.profesor;

import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class ProfesorMaterialGetMaterialesOtrosCursosAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String keyNew;
    private Integer pos;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return SUCCESS;
    }

    public String getKeyNew() {
        return keyNew;
    }

    public void setKeyNew(String keyNew) {
        this.keyNew = keyNew;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}

