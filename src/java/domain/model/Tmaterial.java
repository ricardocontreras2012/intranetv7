/*
 * @(#)Tmaterial.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package domain.model;

import java.io.Serializable;
import java.util.List;
import infrastructure.util.common.CommonMaterialUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class Tmaterial implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<MaterialApoyo> materials;
    private Integer tmaCod;
    private String tmaDes;

    /**
     *
     */
    public Tmaterial() {
    }

    /**
     * Method description
     *
     * @return
     */
    public List<MaterialApoyo> getMaterials() {
        return materials;
    }

    /**
     * Method description
     *
     * @param materials
     */
    public void setMaterials(List<MaterialApoyo> materials) {
        this.materials = materials;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getTmaCod() {
        return tmaCod;
    }

    /**
     * Method description
     *
     * @param tmaCod
     */
    public void setTmaCod(Integer tmaCod) {
        this.tmaCod = tmaCod;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getTmaDes() {
        return tmaDes;
    }

    /**
     * Method description
     *
     * @param tmaDes
     */
    public void setTmaDes(String tmaDes) {
        this.tmaDes = tmaDes;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<MaterialApoyo> getMateriales() {
        return CommonMaterialUtil.getMateriales(this);
    }
}
