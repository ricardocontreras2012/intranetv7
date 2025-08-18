/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import service.curso.CommonCursoDefinicionAddElectivoService;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class CommonCursoDefinicionAddElectivoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private Integer asign;
    private String elect;
    private String electivo;
    private Integer minor;
    private Integer area;
    private String tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return new CommonCursoDefinicionAddElectivoService().service(getGenericSession(), asign, elect, electivo, minor, area, tipo, getKey());
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
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
