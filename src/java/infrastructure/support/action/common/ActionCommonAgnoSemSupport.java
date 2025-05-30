/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support.action.common;

/**
 *
 * @author Ricardo Contreras S.
 */
public class ActionCommonAgnoSemSupport extends ActionCommonSupport{
    private static final long serialVersionUID = 1L;
    private Integer agno;
    private Integer sem;

    /**
     *
     * @return
     */
    public Integer getAgno() {
        return agno;
    }

    /**
     *
     * @param agno
     */
    public void setAgno(Integer agno) {
        this.agno = agno;
    }

    /**
     *
     * @return
     */
    public Integer getSem() {
        return sem;
    }

    /**
     *
     * @param sem
     */
    public void setSem(Integer sem) {
        this.sem = sem;
    }

}
