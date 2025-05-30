package domain.model;

import java.io.Serializable;
import java.util.Date;
import static infrastructure.util.DateUtil.getFormatedDate;
import static infrastructure.util.SystemParametersUtil.DATE_FORMAT;

public class ActividadTeletrabajoId implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer atelRut;
    private Date atelFecha;

    public Integer getAtelRut() {
        return atelRut;
    }

    public void setAtelRut(Integer atelRut) {
        this.atelRut = atelRut;
    }
    
    public Date getAtelFecha() {
        return atelFecha;
    }

    public void setAtelFecha(Date atelFecha) {
        this.atelFecha = atelFecha;
    }
    
    public String getFormatedStandardDate() {
        return getFormatedDate(this.atelFecha, DATE_FORMAT);
    }
    
}

