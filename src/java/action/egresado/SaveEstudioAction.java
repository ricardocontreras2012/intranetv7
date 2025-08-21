/*
 * @(#)SaveEstudioAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.egresado;

import service.egresado.SaveEstudioService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Alvaro Romero C.
 */
public class SaveEstudioAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer areaEstudio;
    private Integer desdeAgno;
    private Integer desdeMes;
    private Integer estadoEstudio;
    private Integer hastaAgno;
    private Integer hastaMes;
    private Integer institucionEducacional;
    private String keyDummy;
    private String nombreEstudio;
    private String otraInstitucion;
    private Integer pais;
    private Integer tipoEstudio;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new SaveEstudioService().service(this, getGenericSession(), this.pais,
                this.institucionEducacional, this.otraInstitucion, this.tipoEstudio, this.nombreEstudio,
                this.desdeAgno, this.desdeMes, this.hastaAgno, this.hastaMes, this.estadoEstudio, this.areaEstudio,
                this.keyDummy);
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAreaEstudio() {
        return areaEstudio;
    }

    /**
     * Method description
     *
     * @param areaEstudio
     */
    public void setAreaEstudio(Integer areaEstudio) {
        this.areaEstudio = areaEstudio;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getDesdeAgno() {
        return desdeAgno;
    }

    /**
     * Method description
     *
     * @param desdeAgno
     */
    public void setDesdeAgno(Integer desdeAgno) {
        this.desdeAgno = desdeAgno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getDesdeMes() {
        return desdeMes;
    }

    /**
     * Method description
     *
     * @param desdeMes
     */
    public void setDesdeMes(Integer desdeMes) {
        this.desdeMes = desdeMes;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getEstadoEstudio() {
        return estadoEstudio;
    }

    /**
     * Method description
     *
     * @param estadoEstudio
     */
    public void setEstadoEstudio(Integer estadoEstudio) {
        this.estadoEstudio = estadoEstudio;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getTipoEstudio() {
        return tipoEstudio;
    }

    /**
     * Method description
     *
     *
     * @param tipoEstudio
     */
    public void setTipoEstudio(Integer tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
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
    public Integer getInstitucionEducacional() {
        return institucionEducacional;
    }

    /**
     * Method description
     *
     *
     * @param institucionEducacional
     */
    public void setInstitucionEducacional(Integer institucionEducacional) {
        this.institucionEducacional = institucionEducacional;
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

    /**
     * Method description
     *
     *
     * @return
     */
    public String getNombreEstudio() {
        return nombreEstudio;
    }

    /**
     * Method description
     *
     *
     * @param nombreEstudio
     */
    public void setNombreEstudio(String nombreEstudio) {
        this.nombreEstudio = nombreEstudio;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public String getOtraInstitucion() {
        return otraInstitucion;
    }

    /**
     * Method description
     *
     *
     * @param nombreInstitucion
     */
    public void setOtraInstitucion(String nombreInstitucion) {
        this.otraInstitucion = nombreInstitucion;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Integer getPais() {
        return pais;
    }

    /**
     * Method description
     *
     *
     * @param pais
     */
    public void setPais(Integer pais) {
        this.pais = pais;
    }
}
