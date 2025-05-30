/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import static com.opensymphony.xwork2.Action.SUCCESS;
import domain.model.PasswordTicket;
import domain.repository.PasswordTicketPersistence;
import infrastructure.support.LoginSessionSupport;
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
public class CommonPasswordSetNewPasswordAction extends ActionCommonSupportDirect {

    private static final long serialVersionUID = 1L;
    private Integer rut;
    private String userType;
    private String password;
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

        PasswordTicketPersistence passwordTicketPersistence
                = ContextUtil.getDAO().getPasswordTicketPersistence(user);
        passwordTicket = passwordTicketPersistence.find(rut, getKey());

        if (passwordTicket != null) {
            userType = passwordTicket.getPtUserType();
            password = CommonRandomUtil.getRandomPassword(passwordTicket.getPtUserType());
            actionCall = ContextUtil.getDAO().getLogActionPersistence(user).getActionLogin(userType);

            LoginSessionSupport loginSessionSupport
                    = (LoginSessionSupport) getSession().get("loginSessionSupport");

            if (getSession().get("loginSessionSupport") == null) {
                loginSessionSupport = new LoginSessionSupport();
                getSession().put("loginSessionSupport", loginSessionSupport);
            }

            setKey(getKeySession());

            if (actionCall != null) {
                ContextUtil.getDAO().getDummyPersistence(user).setPasswordUser(rut, passwordTicket.getPtUserType(), password);
                email = ContextUtil.getDAO().getDummyPersistence(user).getEmail(rut);

                if (email != null) {
                    sendNewPassword(email, password);
                    LogUtil.setLog(rut);
                }
                loginSessionSupport.setRut(rut);
                loginSessionSupport.setPassword(password);
                loginSessionSupport.setUserType(userType);
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
    }
}
