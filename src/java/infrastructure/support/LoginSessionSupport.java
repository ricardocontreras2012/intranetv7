/*
 * @(#)LoginSessionSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import java.io.Serializable;
import java.util.Map;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class LoginSessionSupport implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;
    private Integer rut;
    private String userType;
    private Map<String, String> userTypeMap;

    /**
     *
     */
    public LoginSessionSupport() {
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Method description
     *
     * @param userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method description
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     * @param rut
     */
    public void setRut(Integer rut) {
        this.rut = rut;
    }

    /**
     * Method description
     *
     * @return
     */
    public Map<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    /**
     * Method description
     *
     * @param userTypeMap
     */
    public void setUserTypeMap(Map<String, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }
}
