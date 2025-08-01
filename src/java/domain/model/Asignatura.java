/*
 * @(#)Asignatura.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Asignatura generated by hbm2java
 */
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    private Set<Curso> cursos = new HashSet<>(0);
    private Area area;
    private Integer asiCod;
    private String asiElect;
    private String asiFlagPlanComun;
    private Integer asiHcredTeo;
    private Integer asiHcredEje;
    private Integer asiHcredLab;
    private String asiTipoControlTel;
    private String asiNom;
    private String asiNomCorto;
    private Integer asiSct;
    private String asiTipo;
    private String asiTipoCal;
    private String asiBgColor;
    private String asiFlagPractica;

    /**
     *
     */
    public Asignatura() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsiCod() {
        return this.asiCod;
    }

    /**
     * Method description
     *
     * @param asiCod
     */
    public void setAsiCod(Integer asiCod) {
        this.asiCod = asiCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public Area getArea() {
        return this.area;
    }

    /**
     * Method description
     *
     * @param area
     */
    public void setArea(Area area) {
        this.area = area;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAsiNom() {
        return this.asiNom;
    }

    /**
     * Method description
     *
     * @param asiNom
     */
    public void setAsiNom(String asiNom) {
        this.asiNom = asiNom;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAsiTipo() {
        return this.asiTipo;
    }

    /**
     * Method description
     *
     * @param asiTipo
     */
    public void setAsiTipo(String asiTipo) {
        this.asiTipo = asiTipo;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAsiElect() {
        return this.asiElect;
    }

    /**
     * Method description
     *
     * @param asiElect
     */
    public void setAsiElect(String asiElect) {
        this.asiElect = asiElect;
    }

    public Integer getAsiHcredTeo() {
        return asiHcredTeo;
    }

    public void setAsiHcredTeo(Integer asiHcredTeo) {
        this.asiHcredTeo = asiHcredTeo;
    }   

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsiHcredLab() {
        return this.asiHcredLab;
    }

    /**
     * Method description
     *
     * @param asiHcredLab
     */
    public void setAsiHcredLab(Integer asiHcredLab) {
        this.asiHcredLab = asiHcredLab;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsiHcredEje() {
        return this.asiHcredEje;
    }

    /**
     * Method description
     *
     * @param asiHcredEje
     */
    public void setAsiHcredEje(Integer asiHcredEje) {
        this.asiHcredEje = asiHcredEje;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAsiTipoCal() {
        return this.asiTipoCal;
    }

    /**
     * Method description
     *
     * @param asiTipoCal
     */
    public void setAsiTipoCal(String asiTipoCal) {
        this.asiTipoCal = asiTipoCal;
    }

    /**
     * Method description
     *
     * @return
     */
    public Set<Curso> getCursos() {
        return this.cursos;
    }

    /**
     * Method description
     *
     * @param cursos
     */
    public void setCursos(Set<Curso> cursos) {
        this.cursos = cursos;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAsiFlagPlanComun() {
        return asiFlagPlanComun;
    }

    /**
     * Method description
     *
     * @param asiFlagPlanComun
     */
    public void setAsiFlagPlanComun(String asiFlagPlanComun) {
        this.asiFlagPlanComun = asiFlagPlanComun;
    }

    /**
     * Method description
     *
     * @return
     */
    public boolean isElectivo() {
        return !(asiElect == null || asiElect.isEmpty()) && "S".equals(asiElect);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getAsiNomCorto() {
        return asiNomCorto;
    }

    /**
     * Method description
     *
     *
     * @param asiNomCorto
     */
    public void setAsiNomCorto(String asiNomCorto) {
        this.asiNomCorto = asiNomCorto;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAsiSct() {
        return asiSct;
    }

    /**
     * Method description
     *
     *
     * @param asiSct
     */
    public void setAsiSct(Integer asiSct) {
        this.asiSct = asiSct;
    }

    public String getAsiBgColor() {
        return asiBgColor;
    }

    public void setAsiBgColor(String asiBgColor) {
        this.asiBgColor = asiBgColor;
    }

    public String getAsiTipoControlTel() {
        return asiTipoControlTel;
    }

    public void setAsiTipoControlTel(String asiTipoControlTel) {
        this.asiTipoControlTel = asiTipoControlTel;
    }

    public String getAsiFlagPractica() {
        return asiFlagPractica;
    }

    public void setAsiFlagPractica(String asiFlagPractica) {
        this.asiFlagPractica = asiFlagPractica;
    }
    
    
}
