/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

/**
 *
 * @author Ricardo
 */
public class HorarioSupport {
    String horDia;
    Integer horModulo;
    String horSala;
    String horTipoClase;

    public HorarioSupport(String horDia, Integer horModulo, String horSala, String horTipoClase) {
        this.horDia = horDia;
        this.horModulo = horModulo;
        this.horSala = horSala;
        this.horTipoClase = horTipoClase;
    }

    public String getHorDia() {
        return horDia;
    }

    public void setHorDia(String horDia) {
        this.horDia = horDia;
    }

    public Integer getHorModulo() {
        return horModulo;
    }

    public void setHorModulo(Integer horModulo) {
        this.horModulo = horModulo;
    }

    public String getHorSala() {
        return horSala;
    }

    public void setHorSala(String horSala) {
        this.horSala = horSala;
    }

    public String getHorTipoClase() {
        return horTipoClase;
    }

    public void setHorTipoClase(String horTipoClase) {
        this.horTipoClase = horTipoClase;
    }
}
