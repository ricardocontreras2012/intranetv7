/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login;

import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;
import org.apache.struts2.action.ServletRequestAware;
import org.apache.struts2.action.SessionAware;
import service.common.CommonLoginClaveUnicaResultService;

/**
 *
 * @author Administrador
 */
import javax.servlet.http.HttpServletRequest;

public class ClaveUnicaResultAction extends ActionCommonSupport implements ServletRequestAware, SessionAware {

    private HttpServletRequest request;
    private String key;

    @Override
    public String action() throws Exception {
        this.key = getKeySession();
        return new CommonLoginClaveUnicaResultService().service(request, getSesion(), key);
    }

    @Override
    public void withServletRequest(HttpServletRequest request) {
        this.request = request;
    }

   @Override
    public String getKey() {
        return key;
    }
}
