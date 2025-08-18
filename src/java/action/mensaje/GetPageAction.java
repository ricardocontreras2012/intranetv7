/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.mensaje;

import java.io.InputStream;
import service.mensaje.GetPageService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author ricardo
 */
public class GetPageAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private String url;
    private InputStream inputStream;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        inputStream = new GetPageService().service(getGenericSession(), url, getKey());

        return "success";
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
