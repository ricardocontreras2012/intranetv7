/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.common;

import domain.model.MensajeDestinatario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import service.common.CommonMensajeGetDataTableService;
import infrastructure.support.action.common.ActionCommonSupport;


/**
 * Clase que recive las peticiones del ajax dentro de DataTable dentro de 
 * commonMensajeReceivedMessages.js (La ultima version revizada fue la 3.0.5).
 * @author Javier Frez Valdenegro
 */
public final class CommonMensajeGetDataTableAction extends ActionCommonSupport{
    private static final long serialVersionUID = 1L;
    //Variables recibidas por Ajax
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
    private List<MensajeDestinatario> data;
    
    /**
     * Se ejecuta el servicio de CommonMensajeGetDataTableService, el cual 
     * consigue todos los mensajes del usuario.
     * @return 
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
        
        String retValue = new CommonMensajeGetDataTableService().service(getGenericSession(), getKey(), start, length, searchValue, tipoOrder, nombreDataColumnaActual);
        this.data = getGenericSession().getWorkSession(getKey()).getReceivedMsgs();
        this.recordsTotal = getGenericSession().getWorkSession(getKey()).getCantMsgsReceived();
        this.recordsFiltered = getGenericSession().getWorkSession(getKey()).getCantMsgsReceivedFiltered();
        
        return retValue;
    }
    
    
    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    
    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<MensajeDestinatario> getData() {
        return data;
    }

    public void setData(List<MensajeDestinatario> data) {
        this.data = data;
    }
    
    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getTipoOrder() {
        return tipoOrder;
    }

    public void setTipoOrder(String tipoOrder) {
        this.tipoOrder = tipoOrder;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public String getNombreDataColumnaActual() {
        return nombreDataColumnaActual;
    }

    public void setNombreDataColumnaActual(String nombreDataColumnaActual) {
        this.nombreDataColumnaActual = nombreDataColumnaActual;
    }
}