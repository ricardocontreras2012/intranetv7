/*
 * @(#)HorarioPersistenceImpl.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.persistence;

import domain.repository.HorarioRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.CursoId;
import domain.model.Horario;
import domain.model.Sala;
import static java.lang.String.valueOf;
import java.sql.Clob;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.FetchMode.JOIN;
import org.hibernate.Query;
import static org.hibernate.criterion.Order.*;
import org.hibernate.criterion.Restrictions;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.sql.JoinType.LEFT_OUTER_JOIN;
import org.hibernate.type.StandardBasicTypes;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class HorarioPersistenceImpl extends CrudAbstractDAO<Horario, Long> implements HorarioRepository {

    /**
     * Method description
     *
     * @param sala
     * @param agno
     * @param sem
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> findSalas(Sala sala, Integer agno, Integer sem) {
        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.createAlias("sala", "sala");
        criteria.createAlias("curso", "curso");
        criteria.add(eq("sala.salaNum", sala.getSalaNum()));
        criteria.add(eq("id.horAgno", agno));
        criteria.add(eq("id.horSem", sem));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param horario
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> hayTope(Horario horario) {
        CursoId cursoId = horario.getCurso().getId();
        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createAlias("sala", "sala");
        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.createAlias("curso", "curso");
        criteria.add(eq("sala.salaNum", horario.getSala().getSalaNum()));
        criteria.add(eq("id.horDia", horario.getId().getHorDia()));
        criteria.add(eq("id.horModulo", horario.getId().getHorModulo()));
        criteria.add(eq("id.horAgno", cursoId.getCurAgno()));
        criteria.add(eq("id.horSem", cursoId.getCurSem()));

        return criteria.list();
    }

    /**
     * Method description
     *
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> getHorario(CursoId id) {

        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createCriteria("sala", LEFT_OUTER_JOIN);
        criteria.createAlias("dia", "dia");
        criteria.setFetchMode("curso", JOIN);
        criteria.add(eq("id.horAsign", id.getCurAsign()));
        criteria.add(eq("id.horElect", id.getCurElect()));
        criteria.add(eq("id.horCoord", id.getCurCoord()));
        criteria.add(eq("id.horSecc", id.getCurSecc()));
        criteria.add(eq("id.horAgno", id.getCurAgno()));
        criteria.add(eq("id.horSem", id.getCurSem()));
        criteria.addOrder(desc("horTipoClase"));
        criteria.addOrder(asc("dia.diaCorrel"));
        criteria.addOrder(asc("id.horModulo"));

        return criteria.list();

    }

    /**
     * Method description
     *
     * @param id
     * @param tipo
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> findxTipo(CursoId id, Character tipo) {

        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createCriteria("sala", LEFT_OUTER_JOIN);
        criteria.createAlias("dia", "dia");
        criteria.setFetchMode("curso", JOIN);
        criteria.add(eq("id.horAsign", id.getCurAsign()));
        criteria.add(eq("id.horElect", id.getCurElect()));
        criteria.add(eq("id.horCoord", id.getCurCoord()));
        criteria.add(eq("id.horSecc", id.getCurSecc()));
        criteria.add(eq("id.horAgno", id.getCurAgno()));
        criteria.add(eq("id.horSem", id.getCurSem()));
        criteria.add(eq("horTipoClase", tipo));
        criteria.addOrder(asc("dia.diaCorrel"));
        criteria.addOrder(asc("id.horModulo"));

        return criteria.list();

    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param dia
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> getHorarioByAgnoSem(Integer agno, Integer sem, String dia) {
        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.createAlias("sala", "sala");
        criteria.createAlias("curso", "curso");
        criteria.createAlias("dia", "dia");
        criteria.add(eq("id.horAgno", agno));
        criteria.add(eq("id.horSem", sem));
        criteria.add(eq("dia.diaCod", dia));
        criteria.addOrder(asc("id.horModulo"));
        criteria.addOrder(asc("sala.salaNum"));

        return criteria.list();

    }

    /**
     * Method description
     *
     * @param agno
     * @param sem
     * @param dia
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Horario> getHorarioFAEByAgnoSem(Integer agno, Integer sem, String dia) {
        Criteria criteria = getSession().createCriteria(Horario.class);

        criteria.createAlias("curso.asignatura", "asignatura");
        criteria.createAlias("sala", "sala");
        criteria.createAlias("curso", "curso");
        criteria.createAlias("dia", "dia");
        criteria.add(eq("id.horAgno", agno));
        criteria.add(eq("id.horSem", sem));
        criteria.add(eq("dia.diaCod", dia));
        criteria.add(Restrictions.or(
                Restrictions.like("sala.salaNum", "EF%"),
                Restrictions.like("sala.salaNum", "FAE%")
        ));
        criteria.addOrder(asc("id.horModulo"));
        criteria.addOrder(asc("sala.salaNum"));

        return criteria.list();

    }

    /*
    @Override
    public String getHorarioCoumn(CursoId id) {
        
try{        
        Query query = getSession().getNamedQuery("GetHorarioComunFunction");

        query.setParameter(0, id.getCurAsign(), StandardBasicTypes.INTEGER);
        query.setString(1, id.getCurElect());
        query.setString(2, id.getCurCoord());
        query.setParameter(3, id.getCurSecc(), StandardBasicTypes.INTEGER);
        query.setParameter(4, id.getCurAgno(), StandardBasicTypes.INTEGER);
        query.setParameter(5, id.getCurSem(), StandardBasicTypes.INTEGER);
        
        query.setParameter(0, 1);
        
        

        //query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        /*List<?> result = query.list();
        if (!result.isEmpty()) {
            Object clobObject = ((Map<?, ?>) result.get(0)).values().iterator().next();
            if (clobObject instanceof Clob) {
                try {
                    Clob clob = (Clob) clobObject;
                    Reader reader = clob.getCharacterStream();
                    StringBuilder sb = new StringBuilder();
                    char[] buffer = new char[1024];
                    int charsRead;
                    while ((charsRead = reader.read(buffer)) != -1) {
                        sb.append(buffer, 0, charsRead);
                    }
                    return sb.toString();
                } catch (IOException | SQLException e) {
                    throw new RuntimeException("Error leyendo el CLOB", e);
                }
            }
       
        
        //Clob resultClob = (Clob) query.uniqueResult();
        

// Si lo necesitas como String:
//String resultString = resultClob.getSubString(1, (int) resultClob.length());

String resultString = (String)query.uniqueResult();

System.out.println("x==="+resultString);

return Action.SUCCESS;

}catch(Exception e){e.printStackTrace();}
        
        return null;

    }*/
    @Override
    public String getHorarioCoumn(CursoId id) {

        String sql = "select get_horario_comun(:asign,:elect,:coord,:secc,:agno,:sem) from dual";

        Query query = getSession().createSQLQuery(sql);

        query.setParameter("asign", valueOf(id.getCurAsign()), StandardBasicTypes.STRING);
        query.setParameter("elect", id.getCurElect(), StandardBasicTypes.STRING);
        query.setParameter("coord", id.getCurCoord(), StandardBasicTypes.STRING);
        query.setParameter("secc", valueOf(id.getCurSecc()), StandardBasicTypes.STRING);
        query.setParameter("agno", valueOf(id.getCurAgno()), StandardBasicTypes.STRING);
        query.setParameter("sem", valueOf(id.getCurSem()), StandardBasicTypes.STRING);

        try {
            Clob resultClob = (Clob) query.uniqueResult();
            return resultClob.getSubString(1, (int) resultClob.length());

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
