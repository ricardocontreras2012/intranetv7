/*
 * @(#)ActaCalificacionPersistenceImpl.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.persistence;

import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.ActaCalificacion;
import domain.model.AluCar;
import domain.model.AluCarId;
import domain.model.Curso;
import domain.model.CursoId;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.or;
import org.hibernate.type.StandardBasicTypes;
import domain.repository.ActaCalificacionRepository;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class ActaCalificacionPersistenceImpl extends CrudAbstractDAO<ActaCalificacion, Long> implements ActaCalificacionRepository {

    /**
     * Method description
     *
     * @param curso
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaCalificacion> puedeEmitir(Curso curso) {
        Criteria criteria = getSession().createCriteria(ActaCalificacion.class);

        criteria.setFetchMode("curso", JOIN);
        criteria.setFetchMode("estadoActa", JOIN);
        criteria.add(eq("estadoActa.eacCod", "G"));
        criteria.add(eq("acalTipo", "N"));
        criteria.add(eq("curso", curso));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param correl
     * @param flag
     */
    @Override
    public void generaActasxCarrera(Integer agno, Integer sem, Integer correl, String flag) {
        Query query = getSession().createSQLQuery("{ call actas_pkg.genera_actas_x_correl(?,?,?,?) }");

        query.setParameter(0, agno, StandardBasicTypes.INTEGER);
        query.setParameter(1, sem, StandardBasicTypes.INTEGER);
        query.setParameter(2, correl, StandardBasicTypes.INTEGER);
        query.setParameter(3, flag, StandardBasicTypes.STRING);
        
        query.executeUpdate();
    }

    @Override
    public void recepcionarActa(Integer agno, Integer sem, Integer correl) {
        Query query = getSession().createSQLQuery("{ call actas_pkg.recepcionar(?,?,?) }");

        query.setParameter(0, correl, StandardBasicTypes.INTEGER);
        query.setParameter(1, agno, StandardBasicTypes.INTEGER);
        query.setParameter(2, sem, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param curso
     */
    @Override
    public void emiteActas(Curso curso) {
        CursoId id = curso.getId();
        Query query = getSession().createSQLQuery("{ call poner_notas_en_acta(?,?,?,?,?,?) }");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(1, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(2, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.executeUpdate();
    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<ActaCalificacion> findActasxImprimir(Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(ActaCalificacion.class);

        criteria.setFetchMode("curso.electivo", JOIN);
        criteria.setFetchMode("curso.asignatura", JOIN);
        criteria.createAlias("curso", "curso");
        criteria.createAlias("estadoActa", "estadoActa");
        criteria.add(eq("id.acalAgno", agno));
        criteria.add(eq("id.acalSem", sem));
        criteria.add(or(eq("estadoActa.eacCod", "E"), eq("estadoActa.eacCod", "I")));
        criteria.addOrder(asc("curso.id.curAsign"));
        criteria.addOrder(asc("curso.id.curElect"));
        criteria.addOrder(asc("curso.id.curCoord"));
        criteria.addOrder(asc("curso.id.curSecc"));

        return criteria.list();
    }

    /**
     * Method description
     *
     *
     * @param folio
     * @param agno
     * @param sem
     * @param estado
     */
    @Override
    public void putActaEstado(Integer folio, Integer agno, Integer sem, String estado) {
        String hql = "update ActaCalificacion set acal_est = :estado where acal_folio = :folio AND acal_agno = :agno AND "
                + "acal_sem = :sem";
        Query query = getSession().createQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);
        query.setParameter("estado", estado, StandardBasicTypes.STRING);
        query.executeUpdate();
    }

    /**
     *
     * @param folio
     * @param agno
     * @param sem
     * @param asign
     * @param elect
     * @param coord
     * @param secc
     * @param comp
     * @param tipo
     * @param estado
     * @param tipoAlumno
     */
    @Override
    public void crearActa(Integer folio, Integer agno, Integer sem, Integer asign, String elect, String coord, Integer secc, String comp, String tipo, String estado, String tipoAlumno) {
        String hql = "insert into Acta_Calificacion (acal_folio, acal_agno, acal_sem, acal_asign, acal_elect, acal_coord, acal_secc, acal_tipo, acal_est, acal_tipo_alu, acal_comp) VALUES"
                + "(:folio, :agno, :sem, :asign, :elect, :coord, :secc, :tipo, :estado, :tipoAlumno, 'T')";

        Query query = getSession().createSQLQuery(hql);

        query.setParameter("folio", folio, StandardBasicTypes.INTEGER);
        query.setParameter("agno", agno, StandardBasicTypes.INTEGER);
        query.setParameter("sem", sem, StandardBasicTypes.INTEGER);
        query.setParameter("asign", asign, StandardBasicTypes.INTEGER);
        query.setParameter("elect", elect, StandardBasicTypes.STRING);
        query.setParameter("coord", coord, StandardBasicTypes.STRING);
        query.setParameter("secc", secc, StandardBasicTypes.INTEGER);
        query.setParameter("tipo", tipo, StandardBasicTypes.STRING);
        query.setParameter("estado", estado, StandardBasicTypes.STRING);
        query.setParameter("tipoAlumno", tipoAlumno, StandardBasicTypes.STRING);

        query.executeUpdate();
    }

    @Override
    public void fix(Integer folio) {
        Query query = getSession().getNamedQuery("FixActaFunction");
        query.setParameter(0, folio, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ActaCalificacion> findActasxEstado(Integer agno, Integer sem, String estado) {
        Criteria criteria = getSession().createCriteria(ActaCalificacion.class);

        criteria.setFetchMode("curso.electivo", JOIN);
        criteria.setFetchMode("curso.asignatura", JOIN);
        criteria.createAlias("curso", "curso");
        criteria.createAlias("estadoActa", "estadoActa");
        criteria.add(eq("id.acalAgno", agno));
        criteria.add(eq("id.acalSem", sem));
        if (!"*".equals(estado)) {
            criteria.add(eq("estadoActa.eacCod", estado));
        }
        criteria.addOrder(asc("curso.id.curAsign"));
        criteria.addOrder(asc("curso.id.curElect"));
        criteria.addOrder(asc("curso.id.curCoord"));
        criteria.addOrder(asc("curso.id.curSecc"));
        criteria.addOrder(asc("id.acalFolio")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    /**
     *
     * @param acaId
     * @param id
     * @param nota
     * @param concepto
     * @param rutReali
     */
    @Override
    public void generarActaComplementariaCoordinador(AluCarId acaId, CursoId id, BigDecimal nota, String concepto, Integer rutReali) {
        Query query = getSession().getNamedQuery("GenerarActaComplemetariaFunction");

        query.setParameter(0, acaId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, acaId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, acaId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, acaId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(6, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(7, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(8, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(9, id.getCurSem(), StandardBasicTypes.INTEGER);
        query.setParameter(10, "T", StandardBasicTypes.STRING);
        query.setParameter(11, nota, StandardBasicTypes.BIG_DECIMAL);
        query.setParameter(12, concepto, StandardBasicTypes.STRING);
        query.setParameter(13, rutReali, StandardBasicTypes.INTEGER);

        query.executeUpdate();
    }

    /**
     * Method description
     *
     *
     * @param aluCar
     * @param agnoCal
     * @param semCal
     * @param rut
     * @param user
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Curso> getCursosActaComplementaria(AluCar aluCar, Integer agnoCal,
            Integer semCal, Integer rut, String user) {

        Query query = getSession().getNamedQuery("CursosActaComplementariaFunction");

        List<Object> params = Arrays.asList(
                aluCar.getId().getAcaRut(),
                aluCar.getId().getAcaCodCar(),
                aluCar.getId().getAcaAgnoIng(),
                aluCar.getId().getAcaSemIng(),
                aluCar.getAcaCodMen(),
                aluCar.getAcaCodPlan(),
                aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip(),
                agnoCal, semCal, rut, user
        );

        // Asignar parámetros de manera dinámica
        IntStream.range(0, params.size())
                .forEach(i -> query.setParameter(i, params.get(i),
                params.get(i) instanceof String ? StandardBasicTypes.STRING : StandardBasicTypes.INTEGER));

        // Casting explícito para evitar errores de tipo
        List<Object[]> results = (List<Object[]>) query.list();

        return results.stream()
                .map(row -> {
                    Curso curso = new Curso();
                    CursoId id = new CursoId();

                    id.setCurAsign(((Number) row[0]).intValue());
                    id.setCurElect((String) row[1]);
                    id.setCurCoord((String) row[3]);
                    id.setCurSecc(((Number) row[4]).intValue());
                    id.setCurAgno(agnoCal);
                    id.setCurSem(semCal);
                    id.setCurComp("T");

                    curso.setCurNombre((String) row[2]);
                    curso.setCurProfesores((String) row[5]);
                    curso.setId(id);

                    return curso;
                })
                .collect(Collectors.toList());
    }

    /**
     * Method description
     *
     *
     * @param acaId
     * @param id
     *
     * @return
     */
    @Override
    public Integer isExisteActaCoordinador(AluCarId acaId, CursoId id) {
        Query query = getSession().getNamedQuery("ExisteActaFunction");

        query.setParameter(0, acaId.getAcaRut(), StandardBasicTypes.INTEGER);
        query.setParameter(1, acaId.getAcaCodCar(), StandardBasicTypes.INTEGER);
        query.setParameter(2, acaId.getAcaAgnoIng(), StandardBasicTypes.INTEGER);
        query.setParameter(3, acaId.getAcaSemIng(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter(6, id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter(7, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(8, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(9, id.getCurSem(), StandardBasicTypes.INTEGER);

        return (Integer) query.uniqueResult();
    }
}
