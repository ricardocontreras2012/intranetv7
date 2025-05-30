/*
 * @(#)CommonRandomUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util.common;

import org.apache.commons.lang3.RandomStringUtils;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.KEY_SESSION_LENGTH;

/**
 * Class Obtención de números aleatorios.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonRandomUtil {

    private CommonRandomUtil() {
    }

    public static String getRandomPassword(String user)
    {
        return ContextUtil.getDAO().getDummyPersistence(ActionUtil.getDBUser()).getRandomPassword(user);
    }

    /**
     * Method Obtiene string identificador de sesion
     *
     * @return
     */
    public static String getKeySession() {
        return RandomStringUtils.randomAlphanumeric(KEY_SESSION_LENGTH);
    }
}
