package infrastructure.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Gestiona el tratamiento de las fechas en la aplicación.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class DateUtil {

    // Usamos ThreadLocal para crear instancias de SimpleDateFormat de forma segura para hilos
    private static final ThreadLocal<SimpleDateFormat> DATE_FORMATTER = 
        ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_FORMAT, ContextUtil.getLocale()));

    /**
     * Obtiene la fecha desde la base de datos del sistema.
     *
     * @return Fecha del sistema.
     */
    public static Date getSysdate() {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSysdate();
    }

    /**
     * Obtiene la fecha formateada del sistema.
     *
     * @param format Formato de la fecha.
     * @return Fecha formateada.
     */
    public static String getDate(String format) {
        return new SimpleDateFormat(format, ContextUtil.getLocale()).format(getSysdate());
    }

    /**
     * Convierte una fecha formateada (DATE_FORMAT) a un objeto Date.
     *
     * @param fecha Fecha formateada.
     * @return Fecha convertida a Date.
     */
    public static Date stringToDate(String fecha) {
        if (fecha == null || fecha.isEmpty()) {
            return null;  // Retorna null si el valor es nulo o vacío.
        }
        try {
            return DATE_FORMATTER.get().parse(fecha.replace("-", "/"));
        } catch (ParseException e) {
            // Aquí puedes lanzar una excepción personalizada si es necesario
            return null;
        }
    }

    /**
     * Convierte una fecha en formato DATE_FORMAT a una cadena de texto en palabras.
     *
     * @param fecha Fecha formateada.
     * @return Fecha en palabras.
     */
    private static String getFechaEnPalabras(String fecha) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getFechaEnPalabras(fecha);
    }

    /**
     * Convierte una fecha de tipo Date a una cadena en palabras.
     *
     * @param fecha Fecha a convertir.
     * @return Fecha en palabras.
     */
    public static String getFechaEnPalabras(Date fecha) {
        return getFechaEnPalabras(getFormatedDate(fecha, DATE_FORMAT));
    }

    /**
     * Convierte una fecha de tipo Date a una cadena formateada.
     *
     * @param fecha Fecha a convertir.
     * @param format Formato de la fecha.
     * @return Fecha formateada.
     */
    public static String getFormatedDate(Date fecha, String format) {
        if (fecha == null) {
            return "";  // Si la fecha es nula, se retorna una cadena vacía.
        }
        return new SimpleDateFormat(format, ContextUtil.getLocale()).format(fecha);
    }

    /**
     * Genera una copia defensiva de la fecha para los getters/setters de la aplicación.
     *
     * @param fecha Fecha a copiar.
     * @return Copia defensiva de la fecha.
     */
    public static Date getDateGetterSetter(Date fecha) {
        return (fecha != null) ? new Date(fecha.getTime()) : null;
    }

    /**
     * Retorna la fecha en formato Ciudad, Ejemplo: "Santiago, <fecha en palabras>"
     *
     * @param fecha Fecha a convertir.
     * @return Fecha en formato ciudad.
     */
    public static String getFechaCiudad(Date fecha) {
        return "Santiago, " + getFechaEnPalabras(fecha);
    }

    /**
     * Convierte una fecha de un formato a otro.
     *
     * @param fecha Fecha a convertir.
     * @param formatOri Formato original.
     * @param formatDest Formato destino.
     * @return Fecha transformada.
     * @throws ParseException Si la conversión falla.
     */
    public static String transform(String fecha, String formatOri, String formatDest) throws ParseException {
        if (fecha == null || fecha.isEmpty()) {
            return "";  // Si la fecha es nula o vacía, se retorna una cadena vacía.
        }
        SimpleDateFormat originalFormat = new SimpleDateFormat(formatOri);
        SimpleDateFormat destinationFormat = new SimpleDateFormat(formatDest);
        Date parsedDate = originalFormat.parse(fecha);
        return destinationFormat.format(parsedDate);
    }

    /**
     * Parsea una cadena de texto a un objeto Date con formato "yyyy-MM-dd".
     *
     * @param dateStr Cadena con la fecha a parsear.
     * @return Fecha convertida.
     */
    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            return null;  // Retorna null si no se puede parsear la fecha.
        }
    }
    
    public static Date stringToDate(String fechaStr, String formato) {
        // Crear un DateTimeFormatter con el formato proporcionado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);

        // Convertir el String en un LocalDate utilizando el DateTimeFormatter
        TemporalAccessor temporal = formatter.parse(fechaStr);

        // Convertir LocalDate a Date
        return java.sql.Date.valueOf(LocalDate.from(temporal)); // Si solo necesitas la fecha sin hora
    }
}
