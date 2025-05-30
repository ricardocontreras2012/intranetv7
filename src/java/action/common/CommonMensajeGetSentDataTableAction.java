/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import domain.model.Mensaje;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import static service.common.CommonMensajeGetSentDataTableService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Javier
 */
public class CommonMensajeGetSentDataTableAction extends ActionCommonSupport  {
    private static final long serialVersionUID = 1L;
    
    private int draw;
    private int start;
    private int length;
    private String searchValue;
    private String tipoOrder;
    private int direccion;
    private String nombreDataColumnaActual;
    
    //Variables a retornar al Ajax
    private int recordsTotal;
    private int recordsFiltered;
    private List<Mensaje> data;
    
    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        //Obtener los datos datos por DataTable
        HttpServletRequest request = ServletActionContext.getRequest();
        this.searchValue = request.getParameter("search[value]");
        this.tipoOrder = request.getParameter("order[0][dir]");
        this.direccion = Integer.parseInt(request.getParameter("order[0][column]"));
        this.nombreDataColumnaActual = request.getParameter("columns[" + this.direccion + "][data]");
        
        String retValue = service(getGenericSession(), getKey(), start, length, searchValue, tipoOrder, nombreDataColumnaActual);
        
        this.data = getGenericSession().getWorkSession(getKey()).getSentMsgs();
        this.recordsTotal = getGenericSession().getWorkSession(getKey()).getCantMsgsSended();
        this.recordsFiltered = getGenericSession().getWorkSession(getKey()).getCantMsgsSendedFiltered();
        
        return retValue;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public void setData(List<Mensaje> data) {
        this.data = data;
    }

    public void setTipoOrder(String tipoOrder) {
        this.tipoOrder = tipoOrder;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public void setNombreDataColumnaActual(String nombreDataColumnaActual) {
        this.nombreDataColumnaActual = nombreDataColumnaActual;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getDraw() {
        return draw;
    }

    public int getStart() {
        return start;
    }

    public int getLength() {
        return length;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public String getTipoOrder() {
        return tipoOrder;
    }

    public int getDireccion() {
        return direccion;
    }

    public String getNombreDataColumnaActual() {
        return nombreDataColumnaActual;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public List<Mensaje> getData() {
        return data;
    } 
}
