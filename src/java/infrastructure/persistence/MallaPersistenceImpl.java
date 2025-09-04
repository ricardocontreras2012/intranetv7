/*
 * @(#)MallaPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.MallaRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Asignatura;
import domain.model.Malla;
import domain.model.MallaId;
import domain.model.PlanId;
import java.sql.Clob;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import static org.hibernate.type.StandardBasicTypes.INTEGER;
import static org.hibernate.type.StandardBasicTypes.STRING;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class MallaPersistenceImpl extends CrudAbstractDAO<Malla, Long> implements MallaRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getCalificacionesMalla(AluCar aluCar) {
        Query query = getSession().getNamedQuery("CalificacionesMallaFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public String getMallaJson(AluCar aluCar) {
        System.out.println("Mall JSON");
        
        String sql = "select malla_grafica_pkg.get_malla_json(:id,:codCar,:agnoIng,:semIng,:codMen,:codPlan) from dual";
        Query query = getSession().createSQLQuery(sql);
        
        AluCarId id = aluCar.getId();
        query.setParameter("id", id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter("codCar", id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter("agnoIng", id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter("semIng", id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter("codMen", aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter("codPlan", aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        try {
            Clob resultClob = (Clob) query.uniqueResult();
            return resultClob.getSubString(1, (int) resultClob.length());

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    
    

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getInscripcionesMalla(AluCar aluCar) {
        Query query = getSession().getNamedQuery("InscripcionesMallaFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return query.list();
    }

    @Override
    public int getNextElectivo(AluCar aluCar) {
        AluCarId id = aluCar.getId();
        String strQuery = "SELECT " + "get_next_correl_elect(" + id.getAcaRut() + ","
                + id.getAcaCodCar() + "," + id.getAcaAgnoIng() + ","
                + id.getAcaSemIng() + "," + aluCar.getAcaCodMen() + ","
                + aluCar.getAcaCodPlan() + ") nextElectivo FROM dual";

        return (Integer) getSession().createSQLQuery(strQuery).addScalar("nextElectivo", INTEGER).uniqueResult();
    }

    @Override
    public Object[] getNextElectivoxTipo(AluCar aluCar) {
        AluCarId id = aluCar.getId();

        String strQuery = "SELECT " + "get_next_correl_elect_x_tipo(" + id.getAcaRut() + ","
                + id.getAcaCodCar() + "," + id.getAcaAgnoIng() + ","
                + id.getAcaSemIng() + "," + aluCar.getAcaCodMen() + ","
                + aluCar.getAcaCodPlan() + ",'E') esp," + "get_next_correl_elect_x_tipo("
                + id.getAcaRut() + "," + id.getAcaCodCar() + ","
                + id.getAcaAgnoIng() + "," + id.getAcaSemIng() + ","
                + aluCar.getAcaCodMen() + "," + aluCar.getAcaCodPlan()
                + ",'H') hab," + "get_next_correl_elect_x_tipo(" + id.getAcaRut() + ","
                + id.getAcaCodCar() + "," + id.getAcaAgnoIng() + ","
                + id.getAcaSemIng() + "," + aluCar.getAcaCodMen() + ","
                + aluCar.getAcaCodPlan() + ",'C') cs FROM dual";
        SQLQuery query = getSession().createSQLQuery(strQuery);

        return (Object[]) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getCalificacionesAsignatura(AluCar aluCar, int asignatura) {
        Query query = getSession().getNamedQuery("NotasAsinaturaMallaFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter(6, asignatura, StandardBasicTypes.INTEGER);

        return query.list();
    }

    /**
     * Method description
     *
     *
     * @param planId
     * @param correl
     *
     * @return
     */
    @Override
    public int getAsignaturaElectiva(PlanId planId, int correl) {
        String sqlQuery = "SELECT malla_grafica_pkg.get_malla_cod_asig_electiva(" + planId.getPlaCodCar() + ","
                + planId.getPlaCodMen() + "," + planId.getPlaCod() + "," + correl + ") asignatura FROM dual";

        return (Integer) getSession().createSQLQuery(sqlQuery).addScalar("asignatura", INTEGER).uniqueResult();
    }

    /**
     * Method description
     *
     *
     * @param planId
     * @param correl
     * @param tipo
     *
     * @return
     */
    @Override
    public int getAsignaturaElectivaxTipo(PlanId planId, int correl, String tipo) {
        String sqlQuery = "SELECT get_electivo_x_tipo_x_posicion(" + planId.getPlaCodCar() + ","
                + planId.getPlaCodMen() + "," + planId.getPlaCod() + ",'" + tipo + "'," + correl
                + ") asignatura FROM dual";

        return (Integer) getSession().createSQLQuery(sqlQuery).addScalar("asignatura", INTEGER).uniqueResult();
    }

    /**
     * Method description
     *
     *
     * @param planId
     * @param asignatura
     *
     * @return
     */
    @Override
    public String getTipoElect(PlanId planId, int asignatura) {
        String sqlQuery = "SELECT ma_tipo_elect tipo FROM malla WHERE ma_cod_car=" + planId.getPlaCodCar()
                + " AND ma_cod_men=" + planId.getPlaCodMen() + " AND ma_cod_plan=" + planId.getPlaCod()
                + " AND ma_asign=" + asignatura;

        return (String) getSession().createSQLQuery(sqlQuery).addScalar("tipo", STRING).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer getCreditosNivel(AluCar aluCar) {
        Query query = getSession().getNamedQuery("CreditosNivelFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return (Integer) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Integer getSctNivel(AluCar aluCar) {
        Query query = getSession().getNamedQuery("SctNivelFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return (Integer) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> porConvalidar(AluCar aluCar) {
        Query query = getSession().getNamedQuery("PorConvalidarMallaFunction");
        AluCarId id = aluCar.getId();

        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);

        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> porConvalidarSolicitud(AluCar aluCar, Integer solicitud) {
        Query query = getSession().getNamedQuery("PorConvalidarSolicitudMallaFunction");
        AluCarId id = aluCar.getId();
        query.setParameter(0, id.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, id.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, id.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, aluCar.getAcaCodMen(), StandardBasicTypes.INTEGER);
        query.setParameter(5, aluCar.getAcaCodPlan(), StandardBasicTypes.INTEGER);
        query.setParameter(6, solicitud, StandardBasicTypes.INTEGER);

        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Malla> getMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo) {
        try {
            Query query = getSession().getNamedQuery("GetMallaFunction");

            query.setParameter(0, tcarrera, StandardBasicTypes.INTEGER);
            query.setParameter(1, especialidad, StandardBasicTypes.INTEGER);
            query.setParameter(2, jornada, StandardBasicTypes.STRING);
            query.setParameter(3, rut, StandardBasicTypes.INTEGER);
            query.setParameter(4, cargo, StandardBasicTypes.STRING);

            List<Object[]> results = query.list();

            // Usar Streams para mapear resultados
            return results.stream().map(obj -> {
                MallaId id = new MallaId();
                id.setMaCodCar(((Number) obj[0]).intValue());
                id.setMaCodMen(((Number) obj[1]).intValue());
                id.setMaCodPlan(((Number) obj[2]).intValue());
                id.setMaAsign(((Number) obj[4]).intValue());

                Asignatura asignatura = new Asignatura();
                asignatura.setAsiCod(id.getMaAsign());
                asignatura.setAsiNom((String) obj[5]);
                asignatura.setAsiElect((String) obj[6]);

                Malla malla = new Malla();
                malla.setId(id);
                malla.setMaNivel(((Number) obj[3]).intValue());
                malla.setAsignatura(asignatura);

                return malla;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            // Devuelve lista vacía en lugar de null para evitar problemas en código consumidor
            return Collections.emptyList();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Malla> getElectivosMalla(Integer tcarrera, Integer especialidad, String jornada, Integer rut, String cargo) {
        try {
            Query query = getSession().getNamedQuery("GetMallaFunction");

            query.setParameter(0, tcarrera, StandardBasicTypes.INTEGER);
            query.setParameter(1, especialidad, StandardBasicTypes.INTEGER);
            query.setParameter(2, jornada, StandardBasicTypes.STRING);
            query.setParameter(3, rut, StandardBasicTypes.INTEGER);
            query.setParameter(4, cargo, StandardBasicTypes.STRING);

            List<Object[]> results = query.list();

            // Usar Streams para mapear y filtrar electivos
            return results.stream()
                    .map(obj -> {
                        MallaId id = new MallaId();
                        id.setMaCodCar(((Number) obj[0]).intValue());
                        id.setMaCodMen(((Number) obj[1]).intValue());
                        id.setMaCodPlan(((Number) obj[2]).intValue());
                        id.setMaAsign(((Number) obj[4]).intValue());

                        Asignatura asignatura = new Asignatura();
                        asignatura.setAsiCod(id.getMaAsign());
                        asignatura.setAsiNom((String) obj[5]);
                        asignatura.setAsiElect((String) obj[6]);

                        Malla malla = new Malla();
                        malla.setId(id);
                        malla.setMaNivel(((Number) obj[3]).intValue());
                        malla.setAsignatura(asignatura);

                        return malla;
                    })
                    .filter(malla -> "S".equals(malla.getAsignatura().getAsiElect())) // Filtrar solo electivos
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // Devuelve lista vacía en lugar de null para evitar problemas en código consumidor
            return Collections.emptyList();
        }
    }
}
