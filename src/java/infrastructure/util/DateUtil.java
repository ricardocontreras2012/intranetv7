package infrastructure.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;
import java.time.temporal.TemporalAccessor;

/**
 * Utilidad para el tratamiento de fechas usando Java 8.
 */
public class DateUtil {

    private static final Locale LOCALE = ContextUtil.getLocale();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT, LOCALE);

    public static Date getSysdate() {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getSysdate();
    }

    public static String getDate(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, LOCALE);
        return formatter.format(toLocalDateTime(getSysdate()));
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date stringToDate(String fecha) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDate localDate = LocalDate.parse(fecha.replace("-", "/"), DATE_FORMATTER);
            return toDate(localDate.atStartOfDay());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String getFechaEnPalabras(String fecha) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getFechaEnPalabras(fecha);
    }

    public static String getFechaEnPalabras(Date fecha) {
        return getFechaEnPalabras(getFormattedDate(fecha, DATE_FORMAT));
    }

    public static String getFormattedDate(Date fecha, String format) {
        if (fecha == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, LOCALE);
        return formatter.format(toLocalDateTime(fecha));
    }

    public static Date getDateGetterSetter(Date fecha) {
        return (fecha != null) ? new Date(fecha.getTime()) : null;
    }

    public static String getFechaCiudad(Date fecha) {
        return "Santiago, " + getFechaEnPalabras(fecha);
    }

    public static String transform(String fecha, String formatOri, String formatDest) {
        if (fecha == null || fecha.trim().isEmpty()) {
            return "";
        }
        DateTimeFormatter oriFormatter = DateTimeFormatter.ofPattern(formatOri, LOCALE);
        DateTimeFormatter destFormatter = DateTimeFormatter.ofPattern(formatDest, LOCALE);
        try {
            TemporalAccessor parsed = oriFormatter.parse(fecha);
            return destFormatter.format(parsed);
        } catch (DateTimeParseException e) {
            return "";
        }
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd", LOCALE));
            return java.sql.Date.valueOf(date);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static Date stringToDate(String fechaStr, String formato) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato, LOCALE);
            LocalDate date = LocalDate.parse(fechaStr, formatter);
            return java.sql.Date.valueOf(date);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
