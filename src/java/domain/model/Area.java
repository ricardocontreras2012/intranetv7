/*
 * @(#)Area.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

//Generated 24-mar-2009 15:19:39 by Hibernate Tools 3.2.1.GA
import java.io.Serializable;

/**
 * Area generated by hbm2java
 */
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer areCod;
    private String areDes;

    /**
     *
     */
    public Area() {
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAreCod() {
        return this.areCod;
    }

    /**
     * Method description
     *
     * @param areCod
     */
    public void setAreCod(Integer areCod) {
        this.areCod = areCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getAreDes() {
        return this.areDes;
    }

    /**
     * Method description
     *
     * @param areDes
     */
    public void setAreDes(String areDes) {
        this.areDes = areDes;
    }
}
