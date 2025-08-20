/*
 * @(#)SaveNewReporteAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.reporteclase.profesor;

import java.io.File;
import service.reporteclase.profesor.ProfesorSaveNewReporteService;
import infrastructure.support.action.ActionValidationPosSupport;
import static infrastructure.util.LogUtil.logExceptionMessage;
import java.util.List;

/**
 * Procesa el action mapeado del request a la URL ProfesorReporteSaveNewReporte
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class SaveNewReporteAction extends ActionValidationPosSupport {

    private static final long serialVersionUID = 1L;
    private String contenido;
    private String fecha;
    private String metodo;
    private String moduloHorario;
    private String objetivos;
    private String observaciones;
    private String recuperacion;

    /* Attach */
    private File upload;
    private String uploadFileName;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new ProfesorSaveNewReporteService().service(this, getGenericSession(), fecha, moduloHorario, objetivos,
                contenido, observaciones, metodo, upload, uploadFileName, recuperacion, getKey());
    }

    /**
     * Method description
     *
     * @return
     */
    @Override
    public boolean isValidParam() throws IllegalArgumentException {
        try {
            // Extraemos la lista de módulos una sola vez para evitar múltiples llamadas
            List<String> modulos = getGenericSession().getWorkSession(getKey()).getModulos();

            // Usamos Stream para verificar si alguno coincide con moduloHorario
            return modulos.stream().anyMatch(modulo -> modulo.equals(moduloHorario));
        } catch (Exception e) {
            logExceptionMessage(e);
            throw new IllegalArgumentException(e);
        }
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public void setModuloHorario(String moduloHorario) {
        this.moduloHorario = moduloHorario;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
