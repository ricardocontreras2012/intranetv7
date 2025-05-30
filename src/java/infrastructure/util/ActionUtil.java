/*
 * @(#)ActionUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import static java.util.concurrent.TimeUnit.SECONDS;
import static infrastructure.util.AppStaticsUtil.ACTION_RELOGIN;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActionUtil {

    /**
     * Obtiene el usuario de la base de datos asociado con el contexto de acción
     * actual.
     * <p>
     * Este método verifica el contexto de la acción actual para obtener el
     * valor de "strutsId", que se espera sea un String. Si el valor está
     * presente y es un String válido, devuelve el usuario de la base de datos
     * correspondiente desde el mapa `APP_DB_USERS`. Si el valor no es un String
     * o no está presente, devuelve null.
     * </p>
     *
     * @return El usuario de la base de datos asociado con el "strutsId" actual
     * o null si no se encuentra un usuario válido.
     *
     * @see ActionContext#getContext()
     * @see APP_DB_USERS
     */
    public static String getDBUser() {
        // Obtener el valor asociado con "strutsId" desde el contexto de la acción
        Object strutsId = ActionContext.getContext().get("strutsId");

        // Devolver el usuario de la base de datos correspondiente si el strutsId es un String válido, de lo contrario retornar null
        return (strutsId instanceof String) ? APP_DB_USERS.get((String) strutsId) : null;
    }

    /**
     *
     * @return
     */
    public static String retError() {
        sleep();
        return ERROR;
    }

    /**
     *
     * @return
     */
    public static String retReLogin() {
        sleep();
        return ACTION_RELOGIN;
    }

    private static void sleep() {
        try {
            SECONDS.sleep(5);
        } catch (Exception e) {
        }
    }

    public static String getURL() {
        HttpServletRequest request = ServletActionContext.getRequest();
        return request.getScheme() + "://" + request.getServerName().replace("testsai", "intrafae") + ":" + request.getServerPort() + request.getContextPath();
    }
}
