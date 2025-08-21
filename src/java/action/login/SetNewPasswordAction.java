/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.login;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.PasswordTicket;
import domain.repository.PasswordTicketRepository;
import infrastructure.support.action.common.ActionCommonSupportDirect;
import infrastructure.util.ActionUtil;
import static infrastructure.util.ActionUtil.retError;
import infrastructure.util.ContextUtil;
import infrastructure.util.LogUtil;
import static infrastructure.util.MailUtil.sendNewPassword;
import infrastructure.util.common.CommonRandomUtil;
import static infrastructure.util.common.CommonRandomUtil.getKeySession;

/**
 *
 * @author Ricardo
 */
public class SetNewPasswordAction extends ActionCommonSupportDirect {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String userType;
    private String passwd;
    private String actionCall;

    /**
     * Method description
     *
     * @return
     * @throws java.lang.Exception
     */
    @Override
    public String action() throws Exception {

        String user = ActionUtil.getDBUser();
        String retValue = retError();
        PasswordTicket passwordTicket;
        String email;

        PasswordTicketRepository passwordTicketPersistence
                = ContextUtil.getDAO().getPasswordTicketRepository(user);
        passwordTicket = passwordTicketPersistence.find(rut, getKey());

        if (passwordTicket != null) {
            userType = passwordTicket.getPtUserType();
            passwd = CommonRandomUtil.getRandomPassword(passwordTicket.getPtUserType());
            actionCall = ContextUtil.getDAO().getLogActionRepository(user).getActionLogin(userType);

            setKey(getKeySession());

            if (actionCall != null) {
                ContextUtil.getDAO().getDummyRepository(user).setPasswordUser(rut, passwordTicket.getPtUserType(), passwd);
                email = ContextUtil.getDAO().getDummyRepository(user).getEmail(rut);

                if (email != null) {
                    sendNewPassword(email, passwd);
                    LogUtil.setLog(rut);
                }
                retValue = SUCCESS;
            }

        } else {
            this.addActionError(this.getText("error.parameter"));
        }

        return retValue;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getActionCall() {
        return actionCall;
    }

    public Integer getRut() {
        return rut;
    }

    public String getUserType() {
        return userType;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    } 
}