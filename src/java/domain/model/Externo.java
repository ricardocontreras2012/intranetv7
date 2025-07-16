/*
 * @(#)Externo.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;
import infrastructure.util.common.CommonUsersUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class Externo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String extDv;
    private String extEmail;
    private Date extLastLogin;
    private String extMaterno;
    private String extNombre;
    private String extPaterno;
    private Integer extRut;

    /**
     *
     */
    public Externo() {
    }

    /**
     * Method description
     *
     * @return
     */
    public String getExtDv() {
        return extDv;
    }

    /**
     * Method description
     *
     * @param extDv
     */
    public void setExtDv(String extDv) {
        this.extDv = extDv;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getExtMaterno() {
        return extMaterno;
    }

    /**
     * Method description
     *
     * @param extMaterno
     */
    public void setExtMaterno(String extMaterno) {
        this.extMaterno = extMaterno;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getExtNombre() {
        return extNombre;
    }

    /**
     * Method description
     *
     * @param extNombre
     */
    public void setExtNombre(String extNombre) {
        this.extNombre = extNombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getExtPaterno() {
        return extPaterno;
    }

    /**
     * Method description
     *
     * @param extPaterno
     */
    public void setExtPaterno(String extPaterno) {
        this.extPaterno = extPaterno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getExtRut() {
        return extRut;
    }

    /**
     * Method description
     *
     * @param extRut
     */
    public void setExtRut(Integer extRut) {
        this.extRut = extRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getExtEmail() {
        return extEmail;
    }

    /**
     * Method description
     *
     * @param extEmail
     */
    public void setExtEmail(String extEmail) {
        this.extEmail = extEmail;
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Date getExtLastLogin() {
        return this.extLastLogin != null
                ? new Date(this.extLastLogin.getTime())
                : null;
    }

    /**
     * Method description
     *
     * @param extLastLogin
     */
    public void setExtLastLogin(Date extLastLogin) {
        if (extLastLogin != null) {
            this.extLastLogin = new Date(extLastLogin.getTime());
        }
    }

    /**
     * BUSINESS LOGIC
     *
     * @return
     */
    public String getNombre() {
        return CommonUsersUtil.getNombre(this.extPaterno, this.extMaterno, this.extNombre);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreMensaje() {
        return CommonUsersUtil.getNombreMensaje(this.extPaterno, this.extMaterno, this.extNombre);
    }
}
