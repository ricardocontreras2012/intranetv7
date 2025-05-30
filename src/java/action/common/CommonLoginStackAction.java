
package action.common;

import static service.common.CommonLoginStackService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Ricardo
 */
public class CommonLoginStackAction extends ActionCommonSupport {
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
        action =  service(getGenericSession(), getKey());

        return SUCCESS;
    }

    public String getAction() {
        return action;
    }
}
