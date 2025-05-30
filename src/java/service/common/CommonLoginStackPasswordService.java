
package service.common;

import session.GenericSession;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;
import infrastructure.util.PasswordUtil;

/**
 *
 * @author Ricardo
 */
public class CommonLoginStackPasswordService {

    public static String service(GenericSession genericSession) {
        String retValue;
        if (PRIVILEGED_USERS.containsKey(genericSession.getUserType())) {
            if (PasswordUtil.isStrong(genericSession.getPassword(),10)) {
                retValue = "stack";
            } else {
                retValue = "askPlus";
            }

        } else {
            if (PasswordUtil.isStrong(genericSession.getPassword(),8)) {
                retValue = "stack";
            } else {
                retValue = "askNormal";
            }
        }

        return retValue;
    }

}
