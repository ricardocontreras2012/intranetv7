/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier
 */
public final class CommonDummyPosAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private int pos;

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

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
    
}