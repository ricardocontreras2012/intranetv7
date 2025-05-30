/*
 * @(#)AlumnoSession.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package session;

import domain.model.AluCar;
import domain.model.Alumno;
import domain.model.Mencion;
import java.io.Serializable;
import java.util.List;
import infrastructure.support.MencionSupport;
import domain.model.CertificacionView;
import domain.model.Curso;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class AlumnoSession implements Serializable {

    private static final long serialVersionUID = 1L;

    private Alumno alumno;
    private Integer newMencion;
    private List<CertificacionView> certList;
    private CertificacionView tipoCert;
    private Integer correlCert;
    private Integer montoCert;
    private String rank;
    private String rankMencion;
    private AluCar aluCar;
    private Mencion mencionCambio;
    private Long cantEncuestasPorContestar;
    private Boolean bienvenida;
    private List<Curso> cursosSwap;

    /**
     *
     */
    public AlumnoSession() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Alumno getAlumno() {
        return alumno;
    }

    /**
     * Method description
     *
     * @param alumno
     */
    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }


    public void setCambioMencion(Integer newMencion) {
        this.newMencion = newMencion;
        this.mencionCambio = MencionSupport.getMencion(this.aluCar.getId().getAcaCodCar(), newMencion);
    }

    public List<CertificacionView> getCertList() {
        return certList;
    }

    public void setCertList(List<CertificacionView> certList) {
        this.certList = certList;
    }

    public CertificacionView getTipoCert() {
        return tipoCert;
    }

    public void setTipoCert(CertificacionView tipoCert) {
        this.tipoCert = tipoCert;
    }

    public Integer getCorrelCert() {
        return correlCert;
    }

    public void setCorrelCert(Integer correlCert) {
        this.correlCert = correlCert;
    }

    public Integer getMontoCert() {
        return montoCert;
    }

    public void setMontoCert(Integer montoCert) {
        this.montoCert = montoCert;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankMencion() {
        return rankMencion;
    }

    public void setRankMencion(String rankMencion) {
        this.rankMencion = rankMencion;
    }

    public AluCar getAluCar() {
        return aluCar;
    }

    public void setAluCar(AluCar aluCar) {
        this.aluCar = aluCar;
    }

    public String getMencionCambio() {
        return mencionCambio.getNombreCarreraFull();
    }

    public Integer getNewMencion() {
        return newMencion;
    }

    public void setNewMencion(Integer newMencion) {
        this.newMencion = newMencion;
    }

    public Long getCantEncuestasPorContestar() {
        return cantEncuestasPorContestar;
    }

    public void setCantEncuestasPorContestar(Long cantEncuestasPorContestar) {
        this.cantEncuestasPorContestar = cantEncuestasPorContestar;
    }

    public Boolean getBienvenida() {
        return bienvenida;
    }

    public void setBienvenida(Boolean bienvenida) {
        this.bienvenida = bienvenida;
    }

    public List<Curso> getCursosSwap() {
        return cursosSwap;
    }

    public void setCursosSwap(List<Curso> cursosSwap) {
        this.cursosSwap = cursosSwap;
    }
       
}
