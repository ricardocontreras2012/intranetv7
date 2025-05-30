/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;

/**
 *
 * @author rcontreras
 */
public class PlanLogro implements Serializable {

    private static final long serialVersionUID = 1L;

    private PlanLogroId id;

    private Logro logro;
    private Plan plan;
    private String plalNomLogro;
    private String plalLin1M;
    private String plalLin2M;
    private String plalLin1F;
    private String plalLin2F;
    private String plalEnDe;

    public PlanLogro() {
    }

    public PlanLogroId getId() {
        return id;
    }

    public void setId(PlanLogroId id) {
        this.id = id;
    }

    public Logro getLogro() {
        return logro;
    }

    public void setLogro(Logro logro) {
        this.logro = logro;
    }

    public String getPlalNomLogro() {
        return plalNomLogro;
    }

    public void setPlalNomLogro(String plalNomLogro) {
        this.plalNomLogro = plalNomLogro;
    }

    public String getPlalLin1M() {
        return plalLin1M;
    }

    public void setPlalLin1M(String plalLin1M) {
        this.plalLin1M = plalLin1M;
    }

    public String getPlalLin2M() {
        return plalLin2M;
    }

    public void setPlalLin2M(String plalLin2M) {
        this.plalLin2M = plalLin2M;
    }

    public String getPlalLin1F() {
        return plalLin1F;
    }

    public void setPlalLin1F(String plalLin1F) {
        this.plalLin1F = plalLin1F;
    }

    public String getPlalLin2F() {
        return plalLin2F;
    }

    public void setPlalLin2F(String plalLin2F) {
        this.plalLin2F = plalLin2F;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getPlalEnDe() {
        return plalEnDe;
    }

    public void setPlalEnDe(String plalEnDe) {
        this.plalEnDe = plalEnDe;
    }    
}
