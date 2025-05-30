/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import java.io.InputStream;
import service.common.CommonEncuestaPrintGroupCursosService;
import infrastructure.support.action.ActionParameterAwareSupport;

/**
 *
 * @author Ricardo Contreras S.
 */
public class CommonEncuestaPrintGroupCursosAction extends ActionParameterAwareSupport {

    // private static final long     serialVersionUID = 1L;
    private InputStream inputStream;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        inputStream = new CommonEncuestaPrintGroupCursosService().service(this, getGenericSession(), getMapParameters(),
                getKey());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public InputStream getInputStream() {
        return inputStream;
    }
}
