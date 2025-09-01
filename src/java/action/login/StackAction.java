
package action.login;

import service.login.StackService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class StackAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;

    private String action;


    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {                                
        action =  new StackService().service(getGenericSession(), getKey());
        
        return SUCCESS;
    }

    public String getAction() {
        return action;
    }
}
