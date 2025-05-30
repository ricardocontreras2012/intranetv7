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
public class UserLoginActionStackId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer ulasCorrel;
    private String ulasUser;

    public UserLoginActionStackId() {
    }

    public Integer getUlasCorrel() {
        return ulasCorrel;
    }

    public void setUlasCorrel(Integer ulasCorrel) {
        this.ulasCorrel = ulasCorrel;
    }

    public String getUlasUser() {
        return ulasUser;
    }

    public void setUlasUser(String ulasUser) {
        this.ulasUser = ulasUser;
    }

}
