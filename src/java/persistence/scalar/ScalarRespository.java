/*
 * @(#)ScalarRespository.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package persistence.scalar;

import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.AluCarId;
import java.io.Serializable;
import java.util.Date;

/**
 * Interface description
 *
 * @author Ricardo Contreras S.
 *
 */
public interface ScalarRespository extends CrudGenericDAO<Object, Serializable> {

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaAsistencia();

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaMensaje();

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaNomina();

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaCertificado();

    /**
     * Method description
     *
     * @param folio
     * @return
     */
    String getVerificadorCertificado(Integer folio);

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaMaterial();

    /**
     *
     * @return
     */
    Integer getSecuenciaLogging();

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaActa();

    /**
     * Method description
     *
     * @return
     */
    Integer getSecuenciaEncuesta();

    /**
     * Method description
     *
     *
     * @return
     */
    Integer getSecuenciaDocumentoSolicitud();

    /**
     * Method description
     *
     *
     * @return
     */
    Integer getSecuenciaSolicitud();

    /**
     * Method description
     *
     *
     * @return
     */
    Integer getSecuenciaFichaEgresado();

    Integer getSecuenciaComision();

    Integer getSecuenciaConvalidacion();

    Integer getSecuenciaExpedienteNomina();
    
    Integer getSecuenciaConvenio();

    /**
     * Method description
     *
     * @return
     */
    Date getSysdate();

    /**
     * Method description
     *
     * @param fecha
     * @return
     */
    String getFechaEnPalabras(String fecha);
    String getNumeroEnPalabras(Integer numero);
    String getUltimaMatricula(AluCarId id);
    String getExamenAP(AluCarId id);
    /**
     *
     * @param asign
     * @return
     */
    String getNombreFacultadxAsign(Integer asign);
    String getNombreFacultadxTcarrera(Integer tcarrera, Integer especialidad);

    /**
     *
     * @param asign
     * @return
     */
    String getNombreFacultadxAyu(Integer asign);

    /**
     *
     * @param rut
     * @return
     */
    Integer getUnidadFacultadxProf(Integer rut);

    String getNombreDepartamento(Integer carrera, Integer mencion);

    Integer getComision(Integer carrera, Integer mencion);

    String getReserva(String sala, String dia, Integer modulo, String inicio, String termino);

    String getDistincion(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion, Integer plan, Integer correl);

    String getRol(Integer rut);   
    
    String saveHorarioSala(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, String horarioList);

    String addCurso(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, String electivo, Integer cupo, String inicio, String termino, Integer rut, String jornada);

    String modifyCurso(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, Integer cupo, String inicio, String termino, Integer rut, String jornada);    

    String getPuedePonerNotas(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);

    String getSiguienteSemestre(Integer agno, Integer sem, Integer carrera, Integer mencion, Integer plan);
    
    String getSemestrePrevio(Integer agno, Integer sem, Integer carrera, Integer mencion, Integer plan);

    String getMsgPlusFlag(String user);

    String getIniciales(Integer rut, String trabajo);

    String getTopeHorarioConvenioCurso(Integer rut, String incio, String termino, Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
    
    String getTopeHorarioConvenio(Integer rut, String dia, String fechaInicio, String fechaTermino, String horaInicio, String horaTermino);
    
    String getColorHexPorAsignatura(Integer asignatura);
    
    String getColorHexPorSala(String sala);
    
    String hayCupo(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
    String hayCupoCarrera(Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem, Integer carrera, Integer mencion);
       
    int tienePreReqElectivo(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer mencion, Integer plan, Integer asign, String elect,  Integer agno, Integer sem);    
    int electivoYaAprobado(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer asign, String elect,  Integer agno, Integer sem) ;
    String topeHorario(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer asign, String elect, String coord, Integer secc, Integer agno, Integer sem);
    String topeHorarioCambioCurso(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer asignOri, String electOri, String coordOri, Integer seccOri, Integer agnoOri, Integer semOri, Integer asignDest, String electDest, String coordDest, Integer seccDest, Integer agnoDest, Integer semDest);
    int isCursoPropio(Integer asig, Integer rut, String userType);
    
    int getHorasCromoMalla(Integer carrera, Integer mencion, Integer plan);
    String getNombreNormalizado(String str);
    String getCert(Integer cod);
    String getTramite(Integer cod);
    int getFlagEmail(Integer rut, Integer carrera, Integer agnoIng, Integer semIng);
    String getEmailUsach(String email);
    int getArancelLogro(int logro, int tprograma);
    
}
