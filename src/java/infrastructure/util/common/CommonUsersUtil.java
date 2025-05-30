/*
 * @(#)CommonUsersUtil.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.UserLoginActionStack;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import static org.apache.commons.io.FileUtils.openInputStream;
import org.apache.commons.lang3.StringUtils;
import static org.apache.struts2.ServletActionContext.getServletContext;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.EXTENSION_FOTOS;
import static infrastructure.util.SystemParametersUtil.FOTO_DUMMY;
import static infrastructure.util.SystemParametersUtil.LARGO_NOMBRE_ARCHIVO_FOTO;
import static infrastructure.util.SystemParametersUtil.PAD_NOMBRE_ARCHIVO_FOTOS;
import static infrastructure.util.SystemParametersUtil.PATH_FOTOS;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Clase comun de los usuarios de la Intranet.
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonUsersUtil {

    private CommonUsersUtil() {
    }

    public static String getNombreSimple(String paterno, String materno, String nombre) {
        return nombre + ' ' + paterno + getMaterno(materno);
    }

    /**
     * Obtiene nombre completo(formal).
     *
     * @param paterno Apellido paterno.
     * @param materno Apellido materno.
     * @param nombre Nombres.
     * @return Nombre completo normalizado.
     */
    public static String getNombre(String paterno, String materno, String nombre) {
        return paterno + getMaterno(materno) + ' ' + nombre;
    }

    /**
     * Method description
     *
     *
     * @param paterno
     * @param materno
     * @param nombre
     *
     * @return
     */
    public static String getNombreConSeparador(String paterno, String materno, String nombre) {
        return paterno + getMaterno(materno) + ", " + nombre;
    }

    /**
     * Obtiene nombre para los mensajes.
     *
     * @param paterno Apellido paterno.
     * @param materno Apellido materno.
     * @param nombre Nombres.
     * @return Nombre abreviado normalizado.
     */
    public static String getNombreMensaje(String paterno, String materno, String nombre) {
        return paterno + ((materno == null)
                ? ""
                : ' ' + materno.substring(0, 1) + ".") + " " + nombre;
    }

    /**
     * Obtiene nombre completo(natural).
     *
     * @param paterno Apellido paterno.
     * @param materno Apellido materno.
     * @param nombre Nombres.
     * @return Nombre completo en formato natural.
     */
    public static String getNombreStd(String paterno, String materno, String nombre) {
        return nombre + ' ' + paterno + getMaterno(materno);
    }

    public static String getNombreTyG(String paterno, String materno, String nombre) {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getNombreNormalizado(getNombreStd(paterno, materno, nombre).replace("'", "''"));
    }

    /**
     * Method description
     *
     *
     * @param paterno
     * @param materno
     * @param nombre
     *
     * @return
     */
    public static String getNombreStdConSeparador(String paterno, String materno, String nombre) {
        return nombre + ", " + paterno + getMaterno(materno);
    }

    /**
     * Method description
     *
     *
     * @param materno
     *
     * @return
     */
    private static String getMaterno(String materno) {
        return ((materno == null)
                ? ""
                : ' ' + materno);
    }

    /**
     *
     * @param rut
     * @param dv
     * @return
     */
    public static String getPathFoto(Integer rut, String dv) {
        return getPathFoto(getRutFoto(rut, dv));
    }

    /**
     *
     * @param rut
     * @return
     */
    public static String getPathFoto(String rut) {
        final String fotoPath = PATH_FOTOS + rut + EXTENSION_FOTOS;

        if (Files.exists(Paths.get(fotoPath))) {
            return fotoPath;
        } else {
            return getServletContext().getRealPath(FOTO_DUMMY);
        }
    }

    /**
     *
     * @param rut
     * @param dv
     * @return
     */
    public static String getRutFoto(Integer rut, String dv) {
        return StringUtils.leftPad(rut
                + dv.toUpperCase(ContextUtil.getLocale()), LARGO_NOMBRE_ARCHIVO_FOTO, PAD_NOMBRE_ARCHIVO_FOTOS);
    }

    /**
     *
     * @param rut
     * @param dv
     * @return
     */
    public static String getRutFotoProfesor(Integer rut, String dv) {
        return getRutFoto(rut, dv) + "-P";
    }

    /**
     *
     * @param rut
     * @param dv
     * @return
     */
    public static InputStream getFoto(Integer rut, String dv) {
        return getFileFoto(getRutFoto(rut, dv));
    }

    /**
     *
     * @param rut
     * @param dv
     * @return
     */
    public static InputStream getFotoProfesor(Integer rut, String dv) {
        return getFileFoto(getRutFotoProfesor(rut, dv));
    }

    private static InputStream getFileFoto(String rut) {       
        try {
            return openInputStream(new File(getPathFoto(rut)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<UserLoginActionStack> getActionStack(String user) {
        return ContextUtil.getDAO().getUserLoginActionStackPersistence(ActionUtil.getDBUser()).find(user);
    }
}
