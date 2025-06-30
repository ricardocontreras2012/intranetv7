/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class CommonLoginService {



    public static String service(String userType) {
        return ContextUtil.getDAO().getLogActionPersistence(userType).getActionLogin(userType);
    }
}
