/*
 * @(#)CursoResumenSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CursoResumenSupport {

    private Integer agno;
    private Integer asign;
    private String coord;
    private String elect;
    private String profesor;
    private Integer nobjs1;
    private Integer nobjs2;
    private String nombre;
    private Integer secc;
    private Integer sem;

    /**
     *
     */
    public CursoResumenSupport() {
    }

    public CursoResumenSupport(Object[] obj) {
        setAsign(((Number) obj[0]).intValue());
        setElect((String) obj[1]);
        setCoord((String) obj[2]);
        setSecc(((Number) obj[3]).intValue());
        setAgno(((Number) obj[4]).intValue());
        setSem(((Number) obj[5]).intValue());
        setNombre((String) obj[6]);
        setProfesor((String) obj[7]);
        setNobjs1(((Number) obj[8]).intValue());
        if (obj.length ==10) {
            setNobjs2(((Number) obj[9]).intValue());
        }
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     * Method description
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getAsign() {
        return asign;
    }

    /**
     * Method description
     *
     * @param asign
     */
    public void setAsign(Integer asign) {
        this.asign = asign;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getCoord() {
        return coord;
    }

    /**
     * Method description
     *
     * @param coord
     */
    public void setCoord(String coord) {
        this.coord = coord;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getElect() {
        return elect;
    }

    /**
     * Method description
     *
     * @param elect
     */
    public void setElect(String elect) {
        this.elect = elect;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method description
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSecc() {
        return secc;
    }

    /**
     * Method description
     *
     * @param secc
     */
    public void setSecc(Integer secc) {
        this.secc = secc;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getSem() {
        return sem;
    }

    /**
     * Method description
     *
     * @param sem
     */
    public void setSem(Integer sem) {
        this.sem = sem;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getNobjs1() {
        return nobjs1;
    }

    /**
     * Method description
     *
     * @param nobjs1
     */
    public void setNobjs1(Integer nobjs1) {
        this.nobjs1 = nobjs1;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getNobjs2() {
        return nobjs2;
    }

    /**
     * Method description
     *
     * @param nobjs2
     */
    public void setNobjs2(Integer nobjs2) {
        this.nobjs2 = nobjs2;
    }
}
