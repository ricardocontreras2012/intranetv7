/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class WebUser implements Serializable {

    private static final long serialVersionUID = 1L;

    public WebUser() {
    }

    private String wuUser;
    private String wuDes;
    private String wuNormalPlus;
    private String wuJsp;
    private String wuBD;
    private String wuLogin;
    private String wuEmail;
    private String wuAutoridad;
    private String wuSecretaria;

    public String getWuUser() {
        return wuUser;
    }

    public void setWuUser(String wuUser) {
        this.wuUser = wuUser;
    }

    public String getWuDes() {
        return wuDes;
    }

    public void setWuDes(String wuDes) {
        this.wuDes = wuDes;
    }

    public String getWuNormalPlus() {
        return wuNormalPlus;
    }

    public void setWuNormalPlus(String wuNormalPlus) {
        this.wuNormalPlus = wuNormalPlus;
    }

    public String getWuJsp() {
        return wuJsp;
    }

    public void setWuJsp(String wuJsp) {
        this.wuJsp = wuJsp;
    }

    public String getWuBD() {
        return wuBD;
    }

    public void setWuBD(String wuBD) {
        this.wuBD = wuBD;
    }

    public String getWuLogin() {
        return wuLogin;
    }

    public void setWuLogin(String wuLogin) {
        this.wuLogin = wuLogin;
    }

    public String getWuEmail() {
        return wuEmail;
    }

    public void setWuEmail(String wuEmail) {
        this.wuEmail = wuEmail;
    }

    public String getWuAutoridad() {
        return wuAutoridad;
    }

    public void setWuAutoridad(String wuAutoridad) {
        this.wuAutoridad = wuAutoridad;
    }

    public String getWuSecretaria() {
        return wuSecretaria;
    }

    public void setWuSecretaria(String wuSecretaria) {
        this.wuSecretaria = wuSecretaria;
    }


}
