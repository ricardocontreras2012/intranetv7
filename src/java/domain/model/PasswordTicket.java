/*
 * @(#)PasswordTicket.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.Date;
import static infrastructure.util.DateUtil.getDateGetterSetter;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class PasswordTicket implements Serializable {

    private static final long serialVersionUID = 1L;
    private Date ptDate;
    private String ptKey;
    private Integer ptRut;
    private String ptUserType;

    /**
     *
     */
    public PasswordTicket() {
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Date getPtDate() {
        return getDateGetterSetter(ptDate);
    }

    /**
     * Method description
     *
     * @param ptDate
     */
    public void setPtDate(Date ptDate) {
        this.ptDate = getDateGetterSetter(ptDate);
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPtKey() {
        return ptKey;
    }

    /**
     * Method description
     *
     * @param ptKey
     */
    public void setPtKey(String ptKey) {
        this.ptKey = ptKey;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getPtRut() {
        return ptRut;
    }

    /**
     * Method description
     *
     * @param ptRut
     */
    public void setPtRut(Integer ptRut) {
        this.ptRut = ptRut;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPtUserType() {
        return ptUserType;
    }

    /**
     * Method description
     *
     * @param ptUserType
     */
    public void setPtUserType(String ptUserType) {
        this.ptUserType = ptUserType;
    }
}
