package infrastructure.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Clase utilitaria para descifrar cadenas de texto cifradas utilizando Jasypt.
 * Utiliza un algoritmo de cifrado con contraseña para realizar la desencriptación de cadenas.
 * 
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class JasyptDecrypterUtil {

    // Constantes para el algoritmo y la contraseña utilizados por Jasypt
    private static final String JASYPT_ALGORITHM = "PBEWithMD5AndDES";
    private static final String JASYPT_PASSWD = "piramidal2012";

    // Instancia del cifrador de Jasypt para la desencriptación
    private final StandardPBEStringEncryptor spe;

    /**
     * Constructor que inicializa el cifrador con el algoritmo y la contraseña configurados.
     * Este constructor configura el cifrador para ser usado en la desencriptación de textos.
     */
    public JasyptDecrypterUtil() {
        // Inicialización del cifrador Jasypt
        spe = new StandardPBEStringEncryptor();
        spe.setAlgorithm(JASYPT_ALGORITHM);  // Establece el algoritmo de cifrado
        spe.setPassword(JASYPT_PASSWD);      // Establece la contraseña para el cifrado
    }

    /**
     * Desencripta una cadena de texto previamente cifrada.
     * La cadena de texto debe tener el formato "ENC(cadena_encriptada)".
     * 
     * @param value La cadena cifrada que se desea desencriptar.
     * @return La cadena desencriptada.
     */
    public String decrypt(String value) {
        // Elimina el prefijo "ENC(" y el sufijo ")" si están presentes, luego desencripta el valor
        return spe.decrypt(removeEnc(value));
    }

    /**
     * Elimina el prefijo "ENC(" y el sufijo ")" de una cadena de texto cifrada.
     * Este método es utilizado para preparar la cadena para la desencriptación.
     * 
     * @param value La cadena que puede tener el formato "ENC(cadena_encriptada)".
     * @return La cadena sin el prefijo y el sufijo, lista para ser desencriptada.
     */
    private static String removeEnc(String value) {
        // Verifica si la cadena comienza con "ENC("
        if (value != null && value.startsWith("ENC(")) {
            // Elimina el prefijo "ENC(" y el sufijo ")"
            return value.substring(4, value.length() - 1);
        }
        // Si no tiene el formato esperado, devuelve la cadena tal como está
        return value;
    }
}
