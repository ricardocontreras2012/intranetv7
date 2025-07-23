/*
 * @(#)Administrativo.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDateGetterSetter;
import infrastructure.util.common.CommonUsersUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class Administrativo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String admDv;
    private String admEmail;
    private Date admLastLogin;
    private String admMaterno;
    private String admNombre;
    private String admPaterno;
    private Integer admRut;

    /**
     *
     */
    public Administrativo() {
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAdmDv() {
        return admDv;
    }

    /**
     * Method description
     *
     * @param admDv
     */
    public void setAdmDv(String admDv) {
        this.admDv = admDv;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAdmMaterno() {
        return admMaterno;
    }

    /**
     * Method description
     *
     * @param admMaterno
     */
    public void setAdmMaterno(String admMaterno) {
        this.admMaterno = admMaterno;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAdmNombre() {
        return admNombre;
    }

    /**
     * Method description
     *
     * @param admNombre
     */
    public void setAdmNombre(String admNombre) {
        this.admNombre = admNombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAdmPaterno() {
        return admPaterno;
    }

    /**
     * Method description
     *
     * @param admPaterno
     */
    public void setAdmPaterno(String admPaterno) {
        this.admPaterno = admPaterno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAdmRut() {
        return admRut;
    }

    /**
     * Method description
     *
     * @param admRut
     */
    public void setAdmRut(Integer admRut) {
        this.admRut = admRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAdmEmail() {
        return admEmail;
    }

    /**
     * Method description
     *
     * @param admEmail
     */
    public void setAdmEmail(String admEmail) {
        this.admEmail = admEmail;
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Date getAdmLastLogin() {
        return getDateGetterSetter(admLastLogin);
    }

    /**
     * Method description
     *
     * @param admLastLogin
     */
    public void setAdmLastLogin(Date admLastLogin) {
        this.admLastLogin = getDateGetterSetter(admLastLogin);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombre() {
        return this.admPaterno + ' ' + ((this.admMaterno == null)
                ? ""
                : this.admMaterno + ' ') + this.admNombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreMensaje() {
        return this.admPaterno + ' ' + ((this.admMaterno == null)
                ? ""
                : this.admMaterno.charAt(0) + ". ") + this.admNombre;
    }

    /**
     * Method description
     *
     *
     * @param trabajo
     *
     * @return
     */
    public boolean is(String trabajo) {     
        return  ContextUtil.getDAO().getAdministrativoRepository(ActionUtil.getDBUser()).trabaja(this.admRut, trabajo) != null;
    }

    public String getNombreStd() {
        return CommonUsersUtil.getNombreStd(this.admPaterno, this.admMaterno, this.admNombre);
    }

}
