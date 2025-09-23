/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.dto;


import java.util.List;

/**
 *
 * @author Ricardo
 */
public class InscripcionJsonDTO {
    
    private String status;
    private String message;
    private List<domain.model.Inscripcion> inscripciones;
    private Totales totales;
    private Flags flags;

    // Getters y Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<domain.model.Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<domain.model.Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public Totales getTotales() {
        return totales;
    }

    public void setTotales(Totales totales) {
        this.totales = totales;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }    

    // Clase interna para representar cada inscripción
    public static class Inscripcion {
        private Integer RUT;
        private Integer COD_CAR;
        private Integer AGNO_ING;
        private Integer SEM_ING;
        private Integer ASIGN;
        private String ELECT;
        private String COORD;
        private Integer SECC;
        private Integer AGNO;
        private Integer SEM;
        private String COMP;
        private Integer RUT_REALI;
        private String NOMBRE;
        private String NOMBRE_PROFESORES;
        private String HORARIO;

        // Getters y Setters

        public Integer getASIGN() {
            return ASIGN;
        }

        public void setASIGN(Integer ASIGN) {
            this.ASIGN = ASIGN;
        }
       

        public String getELECT() {
            return ELECT;
        }

        public void setELECT(String ELECT) {
            this.ELECT = ELECT;
        }

        public String getCOORD() {
            return COORD;
        }

        public void setCOORD(String COORD) {
            this.COORD = COORD;
        }

        public Integer getSECC() {
            return SECC;
        }

        public void setSECC(Integer SECC) {
            this.SECC = SECC;
        }

        public Integer getAGNO() {
            return AGNO;
        }

        public void setAGNO(Integer AGNO) {
            this.AGNO = AGNO;
        }

        public Integer getSEM() {
            return SEM;
        }

        public void setSEM(Integer SEM) {
            this.SEM = SEM;
        }

        public String getCOMP() {
            return COMP;
        }

        public void setCOMP(String COMP) {
            this.COMP = COMP;
        }

        public Integer getRUT_REALI() {
            return RUT_REALI;
        }

        public void setRUT_REALI(Integer RUT_REALI) {
            this.RUT_REALI = RUT_REALI;
        }

        public String getNOMBRE() {
            return NOMBRE;
        }

        public void setNOMBRE(String NOMBRE) {
            this.NOMBRE = NOMBRE;
        }

        public String getNOMBRE_PROFESORES() {
            return NOMBRE_PROFESORES;
        }

        public void setNOMBRE_PROFESORES(String NOMBRE_PROFESORES) {
            this.NOMBRE_PROFESORES = NOMBRE_PROFESORES;
        }

        public String getHORARIO() {
            return HORARIO;
        }

        public void setHORARIO(String HORARIO) {
            this.HORARIO = HORARIO;
        }

        public Integer getRUT() {
            return RUT;
        }

        public void setRUT(Integer RUT) {
            this.RUT = RUT;
        }

        public Integer getCOD_CAR() {
            return COD_CAR;
        }

        public void setCOD_CAR(Integer COD_CAR) {
            this.COD_CAR = COD_CAR;
        }

        public Integer getAGNO_ING() {
            return AGNO_ING;
        }

        public void setAGNO_ING(Integer AGNO_ING) {
            this.AGNO_ING = AGNO_ING;
        }

        public Integer getSEM_ING() {
            return SEM_ING;
        }

        public void setSEM_ING(Integer SEM_ING) {
            this.SEM_ING = SEM_ING;
        }
        
        
    }

    // Clase interna para los totales
    public static class Totales {
        private int inscritas;
        private int creditos;
        private int sct;

        // Getters y Setters
        public int getInscritas() {
            return inscritas;
        }

        public void setInscritas(int inscritas) {
            this.inscritas = inscritas;
        }

        public int getCreditos() {
            return creditos;
        }

        public void setCreditos(int creditos) {
            this.creditos = creditos;
        }

        public int getSct() {
            return sct;
        }

        public void setSct(int sct) {
            this.sct = sct;
        }
    }
    
    public static class Flags{
        private String puedeInscribir;
        private String puedeModificar;
        private String puedeEliminar;

        public String getPuedeInscribir() {
            return puedeInscribir;
        }

        public void setPuedeInscribir(String puedeInscribir) {
            this.puedeInscribir = puedeInscribir;
        }

        public String getPuedeModificar() {
            return puedeModificar;
        }

        public void setPuedeModificar(String puedeModificar) {
            this.puedeModificar = puedeModificar;
        }

        public String getPuedeEliminar() {
            return puedeEliminar;
        }

        public void setPuedeEliminar(String puedeEliminar) {
            this.puedeEliminar = puedeEliminar;
        }
    }
}

