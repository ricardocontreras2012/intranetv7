package domain.model;
// Generated 14-08-2014 03:40:22 PM by Hibernate Tools 3.2.1.GA

/**
 * InstitucionEducacional generated by hbm2java
 */
public class InstitucionEducacional implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer ineId;
    private String ineNombre;

    /**
     *
     */
    public InstitucionEducacional() {
    }

    /**
     *
     * @param ineId
     */
    public InstitucionEducacional(Integer ineId) {
        this.ineId = ineId;
    }

    /**
     *
     * @return
     */
    public Integer getIneId() {
        return this.ineId;
    }

    /**
     *
     * @param ineId
     */
    public void setIneId(Integer ineId) {
        this.ineId = ineId;
    }

    /**
     *
     * @return
     */
    public String getIneNombre() {
        return this.ineNombre;
    }

    /**
     *
     * @param ineNombre
     */
    public void setIneNombre(String ineNombre) {
        this.ineNombre = ineNombre;
    }
}
