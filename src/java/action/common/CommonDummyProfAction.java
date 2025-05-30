/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonDummyProfAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String keyProf;

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

    public String getKeyProf() {
        return keyProf;
    }

    public void setKeyProf(String keyProf) {
        this.keyProf = keyProf;
    }
}
