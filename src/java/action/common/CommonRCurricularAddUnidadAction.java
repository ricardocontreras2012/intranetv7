/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.common.CommonRCurricularAddUnidadService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier Frez V.
 */
public class CommonRCurricularAddUnidadAction extends ActionCommonSupport {
    private static final long serialVersionUID = 1L;
    private String retValue;
    private Integer uniCod;
    private String uniNom;
    private Integer uniCC;
    private String uniUrl;
    private Integer uniTipo;
    private String uniColorSala;
    private Integer uniAcadMayor;
    private Integer uniAdmMayor;
    private Integer uniMayor;
    private Integer uniSuperior;
    
    private Integer menCodCar;
    private Integer menCodMen;
    
    private String duty;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        retValue = new CommonRCurricularAddUnidadService().service(getGenericSession(), getKey(), duty, menCodCar, menCodMen, uniCod, uniNom, uniCC, uniUrl, uniTipo, uniColorSala, uniAcadMayor, uniAdmMayor, uniMayor, uniSuperior);
        return retValue;
    }

    public String getRetValue() {
        return retValue;
    }

    public void setRetValue(String retValue) {
        this.retValue = retValue;
    }

    public Integer getUniCod() {
        return uniCod;
    }

    public void setUniCod(Integer uniCod) {
        this.uniCod = uniCod;
    }

    public Integer getUniCC() {
        return uniCC;
    }

    public void setUniCC(Integer uniCC) {
        this.uniCC = uniCC;
    }

    public Integer getUniTipo() {
        return uniTipo;
    }

    public void setUniTipo(Integer uniTipo) {
        this.uniTipo = uniTipo;
    }
    
    public Integer getUniAcadMayor() {
        return uniAcadMayor;
    }

    public void setUniAcadMayor(Integer uniAcadMayor) {
        this.uniAcadMayor = uniAcadMayor;
    }

    public Integer getUniAdmMayor() {
        return uniAdmMayor;
    }

    public void setUniAdmMayor(Integer uniAdmMayor) {
        this.uniAdmMayor = uniAdmMayor;
    }

    public Integer getUniMayor() {
        return uniMayor;
    }

    public void setUniMayor(Integer uniMayor) {
        this.uniMayor = uniMayor;
    }

    public Integer getUniSuperior() {
        return uniSuperior;
    }

    public void setUniSuperior(Integer uniSuperior) {
        this.uniSuperior = uniSuperior;
    }

    public String getUniNom() {
        return uniNom;
    }

    public void setUniNom(String uniNom) {
        this.uniNom = uniNom;
    }

    public String getUniUrl() {
        return uniUrl;
    }

    public void setUniUrl(String uniUrl) {
        this.uniUrl = uniUrl;
    }

    public String getUniColorSala() {
        return uniColorSala;
    }

    public void setUniColorSala(String uniColorSala) {
        this.uniColorSala = uniColorSala;
    }

    public Integer getMenCodCar() {
        return menCodCar;
    }

    public void setMenCodCar(Integer menCodCar) {
        this.menCodCar = menCodCar;
    }

    public Integer getMenCodMen() {
        return menCodMen;
    }

    public void setMenCodMen(Integer menCodMen) {
        this.menCodMen = menCodMen;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }
    
}
