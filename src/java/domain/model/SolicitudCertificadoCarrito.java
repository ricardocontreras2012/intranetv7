
package domain.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.Serializable;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;

/**
 *
 * @author Ricardo
 */
public class SolicitudCertificadoCarrito implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private SolicitudCertificadoCarritoId id;
    private SolicitudCertificado solicitud;
    private Tcertificado tcertificado;
    private Integer sccMonto;
    private String sccParams;
    private String sccEstado;

    public SolicitudCertificadoCarrito() {
    }

    public SolicitudCertificadoCarritoId getId() {
        return id;
    }

    public void setId(SolicitudCertificadoCarritoId id) {
        this.id = id;
    }

    public Tcertificado getTcertificado() {
        return tcertificado;
    }

    public void setTcertificado(Tcertificado tcertificado) {
        this.tcertificado = tcertificado;
    }    

    public Integer getSccMonto() {
        return sccMonto;
    }

    public void setSccMonto(Integer sccMonto) {
        this.sccMonto = sccMonto;
    }

    public String getSccParams() {
        return sccParams;
    }

    public void setSccParams(String sccParams) {
        this.sccParams = sccParams;
    }

    public String getSccEstado() {
        return sccEstado;
    }

    public void setSccEstado(String sccEstado) {
        this.sccEstado = sccEstado;
    }

    public SolicitudCertificado getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudCertificado solicitud) {
        this.solicitud = solicitud;
    }
    
    public String getObs()
    {
        JsonObject jobj = new Gson().fromJson(sccParams.substring(1, sccParams.length() - 1), JsonObject.class);
        return jobj.get("obs").toString().replace("\"","");
    }
    
    public String getTramite()
    {
        JsonObject jobj = new Gson().fromJson(sccParams.substring(1, sccParams.length() - 1), JsonObject.class);
        Integer codTram =  Integer.valueOf(jobj.get("tramite").toString().replace("\"",""));
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getTramite(codTram);
    }
    
}
