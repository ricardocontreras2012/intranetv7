/*
 * @(#)FormatUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import static java.text.Normalizer.Form.NFD;
import java.util.Base64;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tratamiento de formatos y set de caracteres.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class FormatUtil {

    // Método que limpia el string eliminando caracteres de control (0-31) y el carácter DEL (127)
    public static String limpiarCadena(String input) {
        if (input == null) {
            return null; // Retorna null si la cadena de entrada es null
        }

        // Expresión regular para excluir caracteres de control (0-31) y DEL (127)
        String regexControl = "[\\x00-\\x1F\\x7F]"; // Eliminar caracteres de control y DEL

        // Expresión regular para excluir caracteres potencialmente peligrosos de XSS, SQL, y comandos
        String regexEspeciales = "[<>\"'&%;\\(\\)\\{\\}\\[\\]`|\\^~]"; // Carácter peligroso para XSS, SQL, y comandos

        // Primero, eliminar caracteres de control y DEL
        input = input.replaceAll(regexControl, "");

        // Luego, eliminar caracteres potencialmente peligrosos
        input = input.replaceAll(regexEspeciales, "");

        return input;
    }

    /**
     * Método que limpia una cadena de texto de una contraseña eliminando
     * cualquier carácter que no sea alfanumérico o uno de los caracteres
     * especiales permitidos.
     *
     * Los caracteres permitidos son: - Letras mayúsculas y minúsculas (a-zA-Z)
     * - Números (0-9) - Caracteres especiales comunes en contraseñas: @, #, $,
     * %, &, +, =, _, -
     *
     * Cualquier otro carácter, incluyendo los potencialmente peligrosos para la
     * seguridad (como <, >, ;, '), será eliminado de la cadena de texto.
     *
     * Este método se utiliza para asegurar que las contraseñas o claves de
     * acceso no contengan caracteres que puedan generar problemas en la
     * seguridad o el procesamiento (como XSS, inyección SQL, etc.).
     *
     * @param pwd La contraseña o clave de acceso que se desea limpiar.
     * @return Una cadena de texto que contiene solo los caracteres permitidos
     * según el patrón definido, es decir, letras, números y los caracteres
     * especiales mencionados. Si la cadena de entrada es null, el resultado
     * será null.
     *
     * @see java.util.regex.Pattern#replaceAll(String)
     */
    public static String cleanPasswd(String pwd) {
        // Expresión regular que excluye caracteres no permitidos
        String regex = "[^a-zA-Z0-9@#$%&+=_-]"; // Solo se permiten letras, números y los caracteres especiales indicados

        // Reemplaza los caracteres no permitidos con una cadena vacía
        return pwd.replaceAll(regex, "");
    }

    /**
     * Normaliza el nombre de archivo eliminando caracteres no ASCII,
     * reemplazando espacios por guiones bajos y eliminando caracteres no
     * alfanuméricos.
     *
     * @param archivo El nombre del archivo a normalizar.
     * @return El nombre de archivo normalizado, o una cadena vacía si el
     * archivo es null.
     */
    public static String normalizaFileName(String archivo) {
        // Si el archivo es null, retornamos una cadena vacía para evitar NullPointerException
        if (archivo == null) {
            return "";
        }

        // Normalizamos el nombre para eliminar acentos y luego aplicamos las transformaciones en una sola pasada
        return Normalizer.normalize(archivo, NFD) // Normaliza para separar caracteres compuestos (como tildes)
                .replaceAll("[^\\p{ASCII}\\s]", "") // Elimina todos los caracteres no ASCII (excepto espacios)
                .replaceAll("\\s+", "_") // Reemplaza espacios (y otros espacios blancos) por guiones bajos
                .replaceAll("[^a-zA-Z0-9_]", "");   // Elimina caracteres no alfanuméricos ni guiones bajos
    }

    /**
     * Método para obtener el nombre formateado para la búsqueda. Este método
     * convierte el nombre a mayúsculas, y asegura que solo se mantengan letras
     * y guiones. Permite caracteres especiales de varios idiomas como español,
     * francés, alemán, portugués, etc.
     *
     * @param nombre el nombre que se desea formatear.
     * @return el nombre formateado para la búsqueda.
     */
    public static String cleanName(String nombre) {
        if (StringUtils.isEmpty(nombre)) {
            return "";
        }

        // Paso 1: Eliminar caracteres que no sean letras (A-Z, a-z, y caracteres de varios idiomas) o guiones
        String resultado = nombre.trim().replaceAll("[^\\p{L}-]", "");

        // Paso 2: Convertir todo a mayúsculas
        return resultado.toUpperCase();
    }

    /**
     * Method description
     *
     * @param inputStr
     * @return
     */
    public static String emailNormalizado(String inputStr) {
        return inputStr.replaceAll(";", ",").replaceAll(" ", "");
    }

    /**
     * Method description
     *
     * @param inputStr
     * @return
     */
    public static String msgLog(String inputStr) {
        return StringUtils.substring(inputStr, 0, 500).replaceAll("'", "-");
    }

    /**
     * Genera un string el cual solo el primer caracter está en mayúscula.
     *
     * @param inputStr String a formatear.
     * @return String capitalizado.
     */
    public static String initCapital(String inputStr) {
        return StringUtils.capitalize(StringUtils.lowerCase(inputStr));
    }

    public static String initCapAll(String input) {
        List<String> excludeWords = Arrays.asList(
                "la", "el", "los", "las", "de", "en", "a", "por", "con",
                "para", "sobre", "entre", "desde", "hasta", "sin", "durante", "antes", "bajo"
        );

        String[] words = input.trim().split("\\s+");

        return IntStream.range(0, words.length)
                .mapToObj(index -> {
                    String word = words[index].toLowerCase();

                    if (index == 0 || !excludeWords.contains(word)) {
                        return StringUtils.capitalize(word);
                    } else {
                        return word;
                    }
                })
                .collect(Collectors.joining(" "));
    }

    /**
     * Obtiene el dígito verificador de un RUT.
     *
     * @param rut RUT al cual se le calculará su dígito verificador.
     * @return Dígito verificador del RUT.
     */
    public static String getDigitoVerificador(Integer rut) {
        int m = 0;
        int s = 1;

        for (int t = rut; t != 0; t /= 10) {
            s = (s + t % 10 * (9 - m++ % 6)) % 11;
        }

        return Character.toString((char) ((s != 0)
                ? s + 47
                : 75));
    }

    public static String getRutFormateado(Integer rut) {
        return getIntegerFormateado(rut) + "-" + getDigitoVerificador(rut);
    }

    /**
     * Devuelve una representación formateada de un número entero con
     * separadores de miles. Utiliza un formato de tipo "#,###".
     *
     * @param numero El número entero a formatear.
     * @return El número formateado como una cadena de texto, o una cadena vacía
     * si el número es null.
     */
    public static String getIntegerFormateado(Integer numero) {
        // Comprobamos si el número es null y devolvemos una cadena vacía si es el caso
        if (numero == null) {
            return "";
        }

        // Utilizamos un objeto DecimalFormat estático para evitar crear uno nuevo cada vez
        DecimalFormat formatter = new DecimalFormat("#,###");

        return formatter.format(numero);
    }

    /**
     *
     * @param value
     * @return
     */
    public static boolean isNotNull(String[] value) {
        return value != null && value.length > 0 && value[0] != null && !value[0].trim().isEmpty();
    }

    /*public static Color getColor(String color) {
        return new Color(Integer.parseInt(color, 16) - 16777216);
    }*/
    public static Color getColor(String color) {
        // Eliminar el símbolo "#" si está presente
        if (color.startsWith("#")) {
            color = color.substring(1);
        }

        // Usamos el switch sobre el tamaño de la cadena de color
        switch (color.length()) {
            case 6:  // RGB: #RRGGBB
                return new Color(
                        Integer.parseInt(color.substring(0, 2), 16), // Rojo
                        Integer.parseInt(color.substring(2, 4), 16), // Verde
                        Integer.parseInt(color.substring(4, 6), 16) // Azul
                );

            case 8:  // RGBA: #AARRGGBB
                int rgba = Integer.parseInt(color, 16);
                int alpha = (rgba >> 24) & 0xFF;  // Extraemos el componente alfa
                int red = (rgba >> 16) & 0xFF;    // Extraemos el componente rojo
                int green = (rgba >> 8) & 0xFF;   // Extraemos el componente verde
                int blue = rgba & 0xFF;           // Extraemos el componente azul

                return new Color(red, green, blue, alpha);

            default:
                // Si el valor no tiene el formato esperado, devolvemos un color por defecto (negro)
                return Color.BLACK;
        }
    }

    /**
     * Obtiene el tipo MIME de un archivo dado su nombre. Utiliza el método
     * `probeContentType` de `Files` para determinar el tipo MIME basado en la
     * extensión del archivo. Si ocurre un error o el archivo es inválido,
     * devuelve `null`.
     *
     * @param name El nombre del archivo cuya MIME type se quiere obtener.
     * @return El tipo MIME del archivo, o `null` si ocurre un error o el
     * archivo no es válido.
     */
    public static String getMimeType(String name) {
        // Comprobar si el nombre del archivo es null o vacío antes de continuar
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        try {
            // Obtener el Path directamente usando Paths.get()
            Path path = Paths.get(name);

            // Usar probeContentType para obtener el tipo MIME del archivo
            return Files.probeContentType(path);

        } catch (IOException e) {
            // Registrar el error
            System.err.println("Error al obtener el tipo MIME del archivo: " + e.getMessage());
            return null;  // Retorna null en caso de error
        }
    }

    public static String clean(String str) {
        return str.replaceAll("[\\r\\n\\t]+", " ") // Reemplazar saltos de línea, retorno de carro y tabuladores por un espacio
                .replaceAll("[\\p{C}]+", "") // Eliminar caracteres de control
                .replaceAll(" +", " ");             // Reemplazar espacios múltiples por un solo espacio
    }

    public static Integer Base64toInt(String valor) {
        try {
            byte[] tmp = Base64.getDecoder().decode(valor);
            // Asegúrate de que la longitud del array sea la esperada para un entero (4 bytes)
            if (tmp.length != 4) {
                throw new IllegalArgumentException("La longitud del valor decodificado no es válida para un entero.");
            }

            // Convierte los 4 bytes en un entero
            return ((tmp[0] & 0xFF) << 24) | ((tmp[1] & 0xFF) << 16) | ((tmp[2] & 0xFF) << 8) | (tmp[3] & 0xFF);
        } catch (IllegalArgumentException | NullPointerException e) {
            // Manejo de errores en caso de una entrada incorrecta
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte un String a mayúsculas, teniendo en cuenta la configuración
     * regional. Si el valor de entrada es null, devuelve una cadena vacía.
     *
     * Este método utiliza `Optional` para manejar de manera segura el caso en
     * que la entrada sea null. Si la entrada es null, no se lanza una
     * excepción, sino que se devuelve una cadena vacía. Si la entrada no es
     * null, se convierte a mayúsculas utilizando la configuración regional
     * proporcionada por `ContextUtil.getLocale()`.
     *
     * @param input El String a convertir a mayúsculas. Puede ser null.
     * @return El String convertido a mayúsculas, o una cadena vacía si el input
     * es null.
     */
    public static String toUpper(String input) {
        return Optional.ofNullable(input)
                .map(s -> s.toUpperCase(ContextUtil.getLocale())) // Convierte el String a mayúsculas usando la configuración regional
                .orElse("");  // Retorna una cadena vacía si el input es null
    }

    /* Convierte un string a un formato optimo para ser usado como nombre de archivo 
    *  
     */
    public static String sanitizeFileName(String input) {
        // 1. Normalizar acentos
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", ""); // eliminar diacríticos

        // 2. Reemplazar ñ, Ñ manualmente
        normalized = normalized.replace("ñ", "n").replace("Ñ", "N");

        // 3. Reemplazar espacios por guiones bajos
        normalized = normalized.replaceAll("\\s+", "-");

        // 4. Eliminar cualquier carácter no seguro (solo letras, números, _, -, .)
        normalized = normalized.replaceAll("[^a-zA-Z0-9_.-]", "");

        // 5. Convertir a minúsculas
        return normalized.toLowerCase();
    }
    
     public static String sanitizeMgsJson(String message) {
        if (message == null) {
            return "Error desconocido";
        }
        return message.replace("\"", "'"); // Prevenir problemas con comillas en JSON
    }
}
