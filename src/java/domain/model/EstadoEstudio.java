package domain.model;
// Generated 14-08-2014 03:40:22 PM by Hibernate Tools 3.2.1.GA

/**
 * EstadoEstudio generated by hbm2java
 */
public class EstadoEstudio implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer eestId;
    private String eestNombre;

    /**
     *
     */
    public EstadoEstudio() {
    }

    /**
     *
     * @param eestId
     */
    public EstadoEstudio(Integer eestId) {
        this.eestId = eestId;
    }

    /**
     *
     * @return
     */
    public Integer getEestId() {
        return this.eestId;
    }

    /**
     *
     * @param eestId
     */
    public void setEestId(Integer eestId) {
        this.eestId = eestId;
    }

    /**
     *
     * @return
     */
    public String getEestNombre() {
        return this.eestNombre;
    }

    /**
     *
     * @param eestNombre
     */
    public void setEestNombre(String eestNombre) {
        this.eestNombre = eestNombre;
    }
}
