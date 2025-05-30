package infrastructure.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utilidad para validar contraseñas.
 * <p>
 * Esta clase proporciona métodos para verificar si una contraseña cumple con los criterios de seguridad,
 * como tener una longitud mínima, contener números, letras mayúsculas y minúsculas, y caracteres especiales.
 * </p>
 * 
 * @author Ricardo
 * @version 1.0, 16/12/2024
 */
public class PasswordUtil {

    /**
     * Valida si la contraseña cumple con los criterios de seguridad establecidos.
     * <p>
     * Una contraseña es considerada segura si:
     * <ul>
     *   <li>Contiene al menos un número.</li>
     *   <li>Contiene al menos una letra mayúscula.</li>
     *   <li>Contiene al menos una letra minúscula.</li>
     *   <li>Contiene al menos un carácter especial (ejemplo: @, #, $, %, etc.).</li>
     *   <li>No contiene espacios en blanco.</li>
     *   <li>Su longitud es mayor o igual al valor de <code>minLength</code>.</li>
     * </ul>
     * </p>
     * 
     * @param pwd La contraseña a validar.
     * @param minLength La longitud mínima requerida para la contraseña.
     * @return <code>true</code> si la contraseña es segura, <code>false</code> en caso contrario.
     */
    public static boolean isStrong(String pwd, int minLength) {
        if (pwd == null || pwd.length() < minLength) {
            return false;  // Validación temprana si la contraseña es nula o no tiene la longitud mínima
        }

        // Construir dinámicamente la expresión regular con la longitud mínima proporcionada
        String regex = "^(?=.*[0-9])"                // Debe contener al menos un número
                     + "(?=.*[a-z])"                 // Debe contener al menos una letra minúscula
                     + "(?=.*[A-Z])"                 // Debe contener al menos una letra mayúscula
                     + "(?=.*[@#$%&+=-_])"           // Debe contener al menos un carácter especial
                     + "(?=\\S+$)"                   // No debe contener espacios en blanco
                     + ".{" + minLength + ",}$";     // Longitud mínima dinámica

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pwd);
        return matcher.matches();
    }
}
