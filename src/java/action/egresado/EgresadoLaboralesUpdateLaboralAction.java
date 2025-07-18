/*
 * @(#)EgresadoLaboralesUpdateLaboralAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.egresado.EgresadoLaboralesUpdateLaboralService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class EgresadoLaboralesUpdateLaboralAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer indepActividadEconomica;
    private Integer areaTrabajo;
    private String cargo;
    private Integer comuna;
    private Integer correlAluEmp;
    private Integer correlFicha;
    private String dependiente;
    private String descripcion;
    private Integer desdeAgno;
    private Integer desdeMes;
    private Integer hastaAgno;
    private Integer hastaMes;
    private Integer desdeAgnoEmpresa;
    private Integer desdeMesEmpresa;
    private Integer hastaAgnoEmpresa;
    private Integer hastaMesEmpresa;
    private String keyDummy;
    private String lugar;
    private String otroLugar;
    private Integer region;
    private Integer rutEmpleador;
    private Integer sueldo;
    private String tipoEmpleador;
    private Integer tipoTrabajo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new EgresadoLaboralesUpdateLaboralService().service(this, getGenericSession(), this.correlAluEmp, this.correlFicha, this.dependiente,
                this.rutEmpleador, this.tipoEmpleador, this.indepActividadEconomica, this.areaTrabajo, this.lugar,
                this.region, this.comuna, this.otroLugar, this.cargo, this.tipoTrabajo, this.sueldo,
                this.desdeAgno, this.desdeMes, this.hastaAgno, this.hastaMes,
                this.desdeAgnoEmpresa, this.desdeMesEmpresa, this.hastaAgnoEmpresa, this.hastaMesEmpresa,
                this.descripcion, this.keyDummy);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getCorrelAluEmp() {
        return correlAluEmp;
    }

    /**
     * Method description
     *
     *
     * @param correlAluEmp
     */
    public void setCorrelAluEmp(Integer correlAluEmp) {
        this.correlAluEmp = correlAluEmp;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getCorrelFicha() {
        return correlFicha;
    }

    /**
     * Method description
     *
     *
     * @param correlFicha
     */
    public void setCorrelFicha(Integer correlFicha) {
        this.correlFicha = correlFicha;
    }


    /**
     * Method description
     *
     *
     * @return
     */
    public String getDependiente() {
        return dependiente;
    }

    /**
     * Method description
     *
     *
     * @param dependiente
     */
    public void setDependiente(String dependiente) {
        this.dependiente = dependiente;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRutEmpleador() {
        return rutEmpleador;
    }

    /**
     * Method description
     *
     *
     * @param rutEmpleador
     */
    public void setRutEmpleador(Integer rutEmpleador) {
        this.rutEmpleador = rutEmpleador;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getTipoEmpleador() {
        return tipoEmpleador;
    }

    /**
     * Method description
     *
     *
     * @param tipoEmpleador
     */
    public void setTipoEmpleador(String tipoEmpleador) {
        this.tipoEmpleador = tipoEmpleador;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getIndepActividadEconomica() {
        return indepActividadEconomica;
    }

    /**
     * Method description
     *
     *
     * @param indepActividadEconomica
     */
    public void setIndepActividadEconomica(Integer indepActividadEconomica) {
        this.indepActividadEconomica = indepActividadEconomica;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getAreaTrabajo() {
        return areaTrabajo;
    }

    /**
     * Method description
     *
     *
     * @param areaTrabajo
     */
    public void setAreaTrabajo(Integer areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * Method description
     *
     *
     * @param lugar
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getRegion() {
        return region;
    }

    /**
     * Method description
     *
     *
     * @param region
     */
    public void setRegion(Integer region) {
        this.region = region;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getComuna() {
        return comuna;
    }

    /**
     * Method description
     *
     *
     * @param comuna
     */
    public void setComuna(Integer comuna) {
        this.comuna = comuna;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getOtroLugar() {
        return otroLugar;
    }

    /**
     * Method description
     *
     *
     * @param otroLugar
     */
    public void setOtroLugar(String otroLugar) {
        this.otroLugar = otroLugar;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Method description
     *
     *
     * @param cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getTipoTrabajo() {
        return tipoTrabajo;
    }

    /**
     * Method description
     *
     *
     * @param tipoTrabajo
     */
    public void setTipoTrabajo(Integer tipoTrabajo) {
        this.tipoTrabajo = tipoTrabajo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getSueldo() {
        return sueldo;
    }

    /**
     * Method description
     *
     *
     * @param sueldo
     */
    public void setSueldo(Integer sueldo) {
        this.sueldo = sueldo;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDesdeAgno() {
        return desdeAgno;
    }

    /**
     * Method description
     *
     *
     * @param desdeAgno
     */
    public void setDesdeAgno(Integer desdeAgno) {
        this.desdeAgno = desdeAgno;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDesdeMes() {
        return desdeMes;
    }

    /**
     * Method description
     *
     *
     * @param desdeMes
     */
    public void setDesdeMes(Integer desdeMes) {
        this.desdeMes = desdeMes;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getHastaAgno() {
        return hastaAgno;
    }

    /**
     * Method description
     *
     *
     * @param hastaAgno
     */
    public void setHastaAgno(Integer hastaAgno) {
        this.hastaAgno = hastaAgno;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getHastaMes() {
        return hastaMes;
    }

    /**
     * Method description
     *
     *
     * @param hastaMes
     */
    public void setHastaMes(Integer hastaMes) {
        this.hastaMes = hastaMes;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDesdeAgnoEmpresa() {
        return desdeAgnoEmpresa;
    }

    /**
     * Method description
     *
     *
     * @param desdeAgnoEmpresa
     */
    public void setDesdeAgnoEmpresa(Integer desdeAgnoEmpresa) {
        this.desdeAgnoEmpresa = desdeAgnoEmpresa;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getDesdeMesEmpresa() {
        return desdeMesEmpresa;
    }

    /**
     * Method description
     *
     *
     * @param desdeMesEmpresa
     */
    public void setDesdeMesEmpresa(Integer desdeMesEmpresa) {
        this.desdeMesEmpresa = desdeMesEmpresa;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getHastaAgnoEmpresa() {
        return hastaAgnoEmpresa;
    }

    /**
     * Method description
     *
     *
     * @param hastaAgnoEmpresa
     */
    public void setHastaAgnoEmpresa(Integer hastaAgnoEmpresa) {
        this.hastaAgnoEmpresa = hastaAgnoEmpresa;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getHastaMesEmpresa() {
        return hastaMesEmpresa;
    }

    /**
     * Method description
     *
     *
     * @param hastaMesEmpresa
     */
    public void setHastaMesEmpresa(Integer hastaMesEmpresa) {
        this.hastaMesEmpresa = hastaMesEmpresa;
    }


    /**
     * Method description
     *
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Method description
     *
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getKeyDummy() {
        return keyDummy;
    }

    /**
     * Method description
     *
     *
     * @param keyDummy
     */
    public void setKeyDummy(String keyDummy) {
        this.keyDummy = keyDummy;
    }
}
