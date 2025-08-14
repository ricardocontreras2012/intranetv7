/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import infrastructure.support.action.common.ActionCommonSupport;
import service.solicitud.expediente.alumno.AlumnoUpdatePersonalesService;

/**
 *
 * @author Alvaro
 */
public class AlumnoSolicitudExpedienteUpdatePersonalesAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer comuna;
    private String direccion;
    private String email;
    private String emailLaboral;
    private String fono;
    private Integer estadoCivil;
    private boolean success;
    private String message;

    @Override
    public String action() {
        try {
            new AlumnoUpdatePersonalesService().service(
                    this, getGenericSession(), this.email, this.emailLaboral,
                    this.direccion, this.comuna, this.fono, this.estadoCivil
            );

            this.success = true;

            // Extrae mensaje si fue agregado por el service
            if (!getActionMessages().isEmpty()) {
                this.message = getActionMessages().iterator().next();
            } else {
                this.message = "Datos guardados correctamente.";
            }

            return SUCCESS;

        } catch (Exception e) {
            this.success = false;
            this.message = "Error al guardar los datos.";
            return SUCCESS; // No devolvemos ERROR, porque igual queremos enviar JSON 200
        }
    }

    public void setComuna(Integer comuna) {
        this.comuna = comuna;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailLaboral(String emailLaboral) {
        this.emailLaboral = emailLaboral;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
