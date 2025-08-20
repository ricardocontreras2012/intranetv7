/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.curso;

import service.curso.AddElectivoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AddElectivoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asign;
    private String elect;
    private String electivo;
    private Integer minor;
    private Integer area;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new AddElectivoService().service(getGenericSession(), asign, elect, electivo, minor, area, getKey());
    }

    public void setAsign(Integer asign) {
        this.asign = asign;
    }

    public void setElect(String elect) {
        this.elect = elect;
    }

    public void setElectivo(String electivo) {
        this.electivo = electivo;
    }

    public void setMinor(Integer minor) {
        this.minor = minor;
    }

    public void setArea(Integer area) {
        this.area = area;
    }
}
