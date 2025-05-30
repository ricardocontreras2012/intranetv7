package domain.model;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class CertificacionView implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer certTipo;
    private String certDes;
    private String certDesPrint;
    private String certTlogro;
    private String certAviso;
    private Integer certCorrel;
    private Integer acaRut;
    private Integer acaCodCar;
    private Integer acaAgnoIng;
    private Integer acaSemIng;
    private Integer certValor;

    public CertificacionView() {
    }

    public Integer getCertTipo() {
        return certTipo;
    }

    public void setCertTipo(Integer certTipo) {
        this.certTipo = certTipo;
    }

    public String getCertDes() {
        return certDes;
    }

    public void setCertDes(String certDes) {
        this.certDes = certDes;
    }

    public String getCertDesPrint() {
        return certDesPrint;
    }

    public void setCertDesPrint(String certDesPrint) {
        this.certDesPrint = certDesPrint;
    }

    public Integer getAcaRut() {
        return acaRut;
    }

    public void setAcaRut(Integer acaRut) {
        this.acaRut = acaRut;
    }

    public Integer getAcaCodCar() {
        return acaCodCar;
    }

    public void setAcaCodCar(Integer acaCodCar) {
        this.acaCodCar = acaCodCar;
    }

    public Integer getAcaAgnoIng() {
        return acaAgnoIng;
    }

    public void setAcaAgnoIng(Integer acaAgnoIng) {
        this.acaAgnoIng = acaAgnoIng;
    }

    public Integer getAcaSemIng() {
        return acaSemIng;
    }

    public void setAcaSemIng(Integer acaSemIng) {
        this.acaSemIng = acaSemIng;
    }

    public String getCertTlogro() {
        return certTlogro;
    }

    public void setCertTlogro(String certTlogro) {
        this.certTlogro = certTlogro;
    }

    public Integer getCertCorrel() {
        return certCorrel;
    }

    public void setCertCorrel(Integer certCorrel) {
        this.certCorrel = certCorrel;
    }

    public Integer getCertValor() {
        return certValor;
    }

    public void setCertValor(Integer certValor) {
        this.certValor = certValor;
    }

    public String getCertAviso() {
        return certAviso;
    }

    public void setCertAviso(String certAviso) {
        this.certAviso = certAviso;
    }
}
