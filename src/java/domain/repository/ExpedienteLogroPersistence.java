/*
 * @(#)ExpedienteLogroPersistence.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package domain.repository;

import domain.model.AluCar;
import infrastructure.persistence.dao.CrudGenericDAO;
import domain.model.ExpedienteLogro;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ricardo Contreras S.
 */
public interface ExpedienteLogroPersistence extends CrudGenericDAO<ExpedienteLogro, Long> {

    /**
     * Method description
     *
     *
     * @param nomina
     * @param agno
     * @param logro
     *
     * @return
     */
    List<ExpedienteLogro> find(String nomina, Integer agno, Integer logro);
    List<ExpedienteLogro> find(AluCar aca);
    List<ExpedienteLogro> findGeneradas(AluCar aca);
    ExpedienteLogro find(ExpedienteLogro exp);


    /**
     * Method description
     *
     *
     * @param rut
     * @param logro
     * @param nomina
     * @param agno
     */
    void removeNomina(Integer rut, Integer logro, String nomina, Integer agno);



    /**
     * Method description
     *
     *
     * @param expediente
     * @param ne
     * @param fe
     * @param rol
     */
    void saveExpediente(ExpedienteLogro expediente, Integer ne, String fe, String rol);
    void saveExpediente(ExpedienteLogro expediente, String rol, Integer resol, Date fecha);
    Date getFechaTramite(Integer rut, Integer carrera, Integer agnoIng, Integer semIng, Integer correl );

}
