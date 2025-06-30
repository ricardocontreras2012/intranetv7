/*
 * @(#)SystemParametersUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util;

import domain.model.ParamArchivosWeb;
import static java.lang.Integer.valueOf;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import infrastructure.support.UserEmailSupport;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.PropertyLoaderUtil.loadProperties;
import static infrastructure.util.ContextUtil.getParamArchivosWebList;

/**
 * Utilidad para manejar parámetros de configuración y constantes utilizadas en
 * la intranet.
 *
 * Esta clase proporciona un acceso centralizado a diversos parámetros de
 * configuración almacenados en archivos de propiedades y objetos relacionados.
 * Los parámetros incluyen rutas de acceso, configuraciones de correo
 * electrónico, longitudes máximas permitidas para campos, y otros valores
 * relevantes para la lógica de la aplicación.
 *
 * <p>
 * La configuración se carga principalmente desde archivos XML y se utiliza en
 * varias partes del sistema. Esta clase también incluye métodos utilitarios
 * para acceder a parámetros dinámicos y para generar objetos relacionados con
 * la configuración de correo electrónico.</p>
 *
 * <b>Notas:</b>
 * - Los valores de configuración se obtienen mediante métodos estáticos para
 * facilitar el acceso.
 *
 * @author Ricardo Contreras S.
 * @version 7.0, 24/05/2012
 */
public class SystemParametersUtil {

    /**
     * Archivo de configuración de parámetros generales.
     */
    private static final Properties PROPERTIES = loadProperties("config/parameters.xml");

    /**
     * Constantes que definen tipos de ingreso de usuarios.
     */
    public static final Integer INGRESO_REGULAR = 0;
    public static final Integer INGRESO_CLAVE_UNICA = 1;

    /*
     * Logos USACH
     */
    /**
     * Rutas a los logos de la universidad.
     */
    public static final String UNIVERSITY_LOGO_PATH1 = PROPERTIES.getProperty("universityLogoPath1");
    public static final String UNIVERSITY_LOGO_PATH2 = PROPERTIES.getProperty("universityLogoPath2");
    public static final String UNIVERSITY_LOGO_PATH3 = PROPERTIES.getProperty("universityLogoPath3");
    public static final String UNIVERSITY_SOMOS_PATH = PROPERTIES.getProperty("somosUsachPath");
    public static final String UNIVERSITY_LOGO_PATH = PROPERTIES.getProperty("universityLogoUsach");

    /**
     * Ruta al directorio de respaldo de contratos.
     */
    public static final String CONTRATO_BCK_DIR = PROPERTIES.getProperty("contratoBckDirectory");

    /**
     * Rutas a archivos temporales y documentos relacionados con diversos
     * procesos en el sistema.
     */
    public static final String PATH_URL_ATTACH = getParam("pathUrlAttach");
    public static final String PATH_TEMP_FILES = getParam("pathTempFiles");
    public static final String PATH_ACTAS = getParam("pathActas");
    public static final String PATH_MATERIALES = getParam("pathMateriales");
    public static final String PATH_FOTOS = getParam("pathFotos");
    public static final String PATH_ATTACH_SOLICITUDES = getParam("pathDocsSolicitudes");
    public static final String PATH_ATTACH_MESSAGES = getParam("pathMensajes");
    public static final String PATH_CERT = getParam("pathCertificados");
    public static final String PATH_SITUACIONES = getParam("pathSituaciones");
    public static final String PATH_CONV = getParam("pathConvenios");
    public static final String PATH_PROG = getParam("pathProgramas");
    public static final String PATH_TELE_TRABAJO = getParam("pathTeleTrabajo");
    public static final String PATH_TITULACION = getParam("pathTitulacion");

    /*
     * Configuración de contraseñas
     */
    /**
     * Longitud mínima de contraseñas para ciertos usuarios.
     */
    public static final Integer PASSWORD_USERS_PLUS_MIN_LENGTH = valueOf(PROPERTIES.getProperty("passwordUserPlusMinLength"));
    public static final Integer PASSWORD_USERS_MIN_LENGTH = valueOf(PROPERTIES.getProperty("passwordUserMinLength"));

    /**
     * Nombre del archivo base para las fotos de los usuarios.
     */
    public static final String PAD_NOMBRE_ARCHIVO_FOTOS = PROPERTIES.getProperty("PadNombreArchivoFotos");

    /**
     * Longitud máxima de los mensajes.
     */
    public static final Integer MESSAGE_TO_MAX_LENGTH = valueOf(PROPERTIES.getProperty("messageToMaxLength"));
    public static final Integer MESSAGE_SUBJECT_MAX_LENGTH = valueOf(PROPERTIES.getProperty("messageSubjectMaxLength"));

    /*
     * Configuración de correos electrónicos
     */
    /**
     * Propiedades para la configuración del correo electrónico.
     */
    public static final Properties MAIL_PROPERTIES = loadProperties("config/mail/mail.xml");
    public static final Integer NUM_EMAIL_USERS = valueOf(MAIL_PROPERTIES.getProperty("users"));
    public static final Map<Integer, UserEmailSupport> EMAIL_USERS = getEmailUsers();

    /*
     * Configuración general
     */
    /**
     * Duración de la sesión de usuario en minutos.
     */
    public static final Integer KEY_SESSION_LENGTH = valueOf(PROPERTIES.getProperty("keySessionLength"));

    /**
     * Foto por defecto de los usuarios.
     */
    public static final String FOTO_DUMMY = PROPERTIES.getProperty("FotoDummy");

    /**
     * Extensión de archivos permitidos para fotos de usuario.
     */
    public static final String EXTENSION_FOTOS = PROPERTIES.getProperty("ExtencionFotos");

    /**
     * Longitud de nombre archivos permitidos para fotos de usuario para efectos de formateo.
     */
    public static final Integer LARGO_NOMBRE_ARCHIVO_FOTO
            = valueOf(PROPERTIES.getProperty("LargoNombreArchivoFoto"));

    /**
     * Formato corto para fechas.
     */
    public static final String DATE_SHORT_FORMAT = PROPERTIES.getProperty("dateShortFormat");

    /**
     * Formato completo para fechas.
     */
    public static final String DATE_FULL_FORMAT = PROPERTIES.getProperty("dateFullFormat");

    /**
     * Formato de fecha estándar en el sistema.
     */
    public static final String DATE_FORMAT = PROPERTIES.getProperty("dateFormat");

    /*
     * Certificación
     */
    /**
     * Códigos de certificación para diversos tipos de documentos.
     */
    public static final Integer C1 = 1;
    public static final Integer C2 = 2;
    public static final Integer C3 = 3;
    public static final Integer C4 = 4;
    public static final Integer C5 = 5;
    public static final Integer C6 = 11;
    public static final Integer C7 = 14;
    public static final Integer C8 = 30;
    public static final Integer C9 = 32;
    public static final Integer I1 = 6;
    public static final Integer I2 = 8;
    public static final Integer I3 = 7;
    public static final Integer I4 = 9;
    public static final Integer I5 = 10;
    public static final Integer I6 = 12;
    public static final Integer I7 = 21;
    public static final Integer I8 = 13;

    /**
     * Código ISO para certificados.
     */
    public static final Integer CERTIFICATE_ISO_CODE = valueOf(PROPERTIES.getProperty("certificateISOCode"));

    /**
     * Rutas de imágenes relacionadas con los certificados.
     */
    public static final String CERTIFICATE_BARCODE_BLANK_IMAGE_PATH = PROPERTIES.getProperty("certificateBarcodeBlankImagePath");
    public static final String CERTIFICATE_BACKGROUND_CLEAN_IMAGE_PATH = PROPERTIES.getProperty("certificateBackgroundCleanImagePath");
    public static final String CERTIFICATE_BACKGROUND_IMAGE_PATH = PROPERTIES.getProperty("certificateBackgroundImagePath");
    public static final String CONTANCIA_BACKGROUND_IMAGE_PATH = PROPERTIES.getProperty("constanciaBackgroundImagePath");
    public static final String CERTIFICATE_ISO_27001_BACKGROUND_IMAGE_PATH = PROPERTIES.getProperty("certificateBackgroundImageISO27001Path");
    public static final String CERTIFICATE_TIMBRE_PROGRAMAS_IMAGE_PATH = PROPERTIES.getProperty("certificateTimbreProgramasPath");
    public static final String CERTIFICATE_CLEAN_BACKGROUND_IMAGE_PATH = PROPERTIES.getProperty("certificateCleanBackgroundImagePath");
    public static final String CERTIFICATE_REGISTRADOR_IMAGE_PATH = PROPERTIES.getProperty("certificateRegistradorImagePath");

    /**
     * Ruta de acceso al sistema web.
     */
    public static final String ACCESO_WEB = PROPERTIES.getProperty("AccesoWEB");

    /**
     * Ruta de imagen para el fondo del acta de grado y título.
     */
    public static final String ACTA_GRADO_TITULO_BACKGROUND_IMAGE_PATH = PROPERTIES.getProperty("actaGradoTituloBackgroundImagePath");

    /**
     * Obtiene el valor de un parámetro desde la base de datos.
     *
     * @param parametro El nombre del parámetro a obtener.
     * @return El valor del parámetro.
     */
    private static String getParam(String parametro) {
        try {
            return getParamArchivosWebList().stream()
                    .filter(param -> param.getParNomVar().equals(parametro))
                    .map(ParamArchivosWeb::getParValor)
                    .findFirst()
                    .orElse("");
        } catch (Exception e) {
            logExceptionMessage(e);
            return ""; // Retorna una cadena vacía en caso de excepción
        }
    }

    /**
     * Obtiene una lista de usuarios de correo electrónico configurados.
     *
     * @return Un mapa con los usuarios y sus respectivas configuraciones de
     * correo.
     */
    private static Map<Integer, UserEmailSupport> getEmailUsers() {
        return IntStream.range(0, NUM_EMAIL_USERS)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        i -> new UserEmailSupport(
                                MAIL_PROPERTIES.getProperty("mail.smtp.user" + i),
                                MAIL_PROPERTIES.getProperty("mail.smtp.password"+i)
                        )
                ));
    }
}
