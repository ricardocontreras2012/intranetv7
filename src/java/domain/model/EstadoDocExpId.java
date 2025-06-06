/*
 * @(#)Dia.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;

/**
 * Class description
 *
 * @author Alvaro Romero C.
 * @version 7, 06/05/2025
 */
public class EstadoDocExpId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer edeRut;
    private Integer edeCodCar;
    private Integer edeAgnoIng;
    private Integer edeSemIng;
    private Integer edeCorrelLogro;
    private Integer edeTdoc;

    /**
     *
     */
    public EstadoDocExpId() {
    }

    public EstadoDocExpId(Integer edeRut, Integer edeCodCar, Integer edeAgnoIng, Integer edeSemIng,
                          Integer edeCorrelLogro, Integer edeTdoc) {
        this.edeRut = edeRut;
        this.edeCodCar = edeCodCar;
        this.edeAgnoIng = edeAgnoIng;
        this.edeSemIng = edeSemIng;
        this.edeCorrelLogro = edeCorrelLogro;
        this.edeTdoc = edeTdoc;
    }

    // Getters and Setters
    public Integer getEdeRut() { return edeRut; }
    public void setEdeRut(Integer edeRut) { this.edeRut = edeRut; }

    public Integer getEdeCodCar() { return edeCodCar; }
    public void setEdeCodCar(Integer edeCodCar) { this.edeCodCar = edeCodCar; }

    public Integer getEdeAgnoIng() { return edeAgnoIng; }
    public void setEdeAgnoIng(Integer edeAgnoIng) { this.edeAgnoIng = edeAgnoIng; }

    public Integer getEdeSemIng() { return edeSemIng; }
    public void setEdeSemIng(Integer edeSemIng) { this.edeSemIng = edeSemIng; }

    public Integer getEdeCorrelLogro() { return edeCorrelLogro; }
    public void setEdeCorrelLogro(Integer edeCorrelLogro) { this.edeCorrelLogro = edeCorrelLogro; }

    public Integer getEdeTdoc() { return edeTdoc; }
    public void setEdeTdoc(Integer edeTdoc) { this.edeTdoc = edeTdoc; }
}
