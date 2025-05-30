/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.common;

import session.GenericSession;

/**
 *
 * @author Ricardo
 */
public class CommonLoginStackService {

    public static String service(GenericSession genericSession, String key) {
        String action = null;

        if (!genericSession.getStackActionList().isEmpty()) {
            action = genericSession.getStackActionList().get(0).getUlasAction();
            genericSession.getStackActionList().remove(0);
        }

        return action;
    }
}
