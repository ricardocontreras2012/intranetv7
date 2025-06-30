/*
 * @(#)EmailListValidator.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model.validator;

import com.opensymphony.xwork2.validator.validators.RegexFieldValidator;
import static java.lang.Boolean.TRUE;

/**
 * Validador de formato de lista de correos electrónicos.
 * 
 * Esta clase extiende {@link RegexFieldValidator} y proporciona una validación para una lista de direcciones de correo electrónico.
 * Utiliza una expresión regular para verificar que el formato de cada correo electrónico en la lista sea válido.
 * La expresión regular también permite múltiples direcciones de correo electrónico separadas por comas o punto y coma.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class EmailListValidator extends RegexFieldValidator {

    /**
     * Expresión regular utilizada para validar el formato de las direcciones de correo electrónico.
     * La expresión regular verifica un correo electrónico válido (nombre de usuario + dominio) y
     * permite múltiples correos electrónicos separados por comas o punto y coma.
     * 
     * El patrón sigue el siguiente formato:
     * - El primer correo electrónico debe tener el formato estándar de "nombre@dominio.com".
     * - Los correos electrónicos adicionales deben ser separados por comas o punto y coma y seguir el mismo formato.
     * 
     * Ejemplo válido: "correo1@dominio.com, correo2@dominio.com"
     */
    private static final String EMAIL_ADDRESS_PATTERN =
    "^([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})(\\s*[,;]\\s*[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,})*$";

    /**
     * Constructor del validador.
     * 
     * Este constructor configura el validador de la siguiente manera:
     * - No distingue entre mayúsculas y minúsculas (`setCaseSensitive(false)`).
     * - Elimina los espacios en blanco antes y después del valor ingresado (`setTrim(TRUE)`).
     * - Establece la expresión regular para validar los correos electrónicos.
     */
    public EmailListValidator() {
        setCaseSensitive(false);  // No diferencia entre mayúsculas y minúsculas.
        setTrim(TRUE);  // Elimina los espacios en blanco antes y después de la cadena.
        setRegexExpression(EMAIL_ADDRESS_PATTERN);  // Establece la expresión regular de validación.
    }
}
