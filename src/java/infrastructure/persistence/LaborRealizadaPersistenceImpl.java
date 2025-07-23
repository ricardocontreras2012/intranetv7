package infrastructure.persistence;

import domain.repository.LaborRealizadaRepository;
import infrastructure.persistence.dao.CrudAbstractDAO;
import domain.model.LaborRealizada;
import java.util.List;
import org.hibernate.Criteria;
import static org.hibernate.criterion.Order.asc;
import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.sqlRestriction;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 * Implementación de la persistencia de LaborRealizada.
 */
public class LaborRealizadaPersistenceImpl extends CrudAbstractDAO<LaborRealizada, Long> implements LaborRealizadaRepository {

    /**
     * Buscar LaborRealizada por Mencion.
     *
     * @param carrera Código de carrera
     * @param mencion Código de mención
     * @param cargo Cargo del usuario
     * @return LaborRealizada
     */
    @SuppressWarnings("unchecked")
    @Override
    public LaborRealizada findLaborRealizadaMencion(Integer carrera, Integer mencion, String cargo) {
        Criteria criteria = getSession().createCriteria(LaborRealizada.class);

        // Usamos SQLRestriction con parámetros para evitar inyecciones
        String filter = "exists (select * from mencion, unidad u1, unidad u2 where "
                + "men_cod_car = ? and men_cod_men = ? and u1.uni_cod = lrea_unidad "
                + "and u2.uni_cod = u1.uni_acad_mayor and men_unidad = u2.uni_cod )";

        criteria.createAlias("labor", "labor");
        criteria.createAlias("funcionario", "funcionario");
        criteria.add(eq("labor.labUser", cargo));
        criteria.add(sqlRestriction(filter, new Object[]{carrera, mencion}, new Type[]{StandardBasicTypes.INTEGER, StandardBasicTypes.INTEGER}));

        return (LaborRealizada) criteria.uniqueResult();
    }

    /**
     * Método genérico para buscar LaborRealizada con un filtro SQL
     * personalizado.
     *
     * @param cargo Cargo del usuario
     * @param filter Filtro SQL
     * @param params Parámetros para el filtro
     * @param types Tipos de los parámetros
     * @return Lista de LaborRealizada
     */
    @SuppressWarnings("unchecked")
    public List<LaborRealizada> findLaborRealizada(String cargo, String filter, Object[] params, Type[] types) {
        Criteria criteria = getSession().createCriteria(LaborRealizada.class);

        criteria.createAlias("labor", "labor");
        criteria.createAlias("unidad", "unidad");
        criteria.createAlias("funcionario", "funcionario");
        criteria.add(eq("labor.labUser", cargo));

        // Agregamos la restricción SQL con parámetros
        criteria.add(sqlRestriction(filter, params, types));

        criteria.addOrder(asc("funcionario.traPaterno"));
        criteria.addOrder(asc("funcionario.traMaterno"));
        criteria.addOrder(asc("funcionario.traNombre"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return criteria.list();
    }

    /**
     * Buscar LaborRealizada por Cargo.
     *
     * @param cargo Cargo del usuario
     * @return LaborRealizada
     */
    @Override
    public LaborRealizada findAutoridad(String cargo) {
        Criteria criteria = getSession().createCriteria(LaborRealizada.class);

        criteria.createAlias("labor", "labor");
        criteria.createAlias("unidad", "unidad");
        criteria.add(eq("labor.labUser", cargo));

        return (LaborRealizada) criteria.uniqueResult();
    }

    /**
     * Buscar LaborRealizada por Cargo y Unidad.
     *
     * @param cargo Cargo del usuario
     * @param unidad Código de unidad
     * @return Lista de LaborRealizada
     */
    @Override
    public List<LaborRealizada> findAutoridad(String cargo, Integer unidad) {
        String strFilter = "lrea_unidad = ?";

        return findLaborRealizada(cargo, strFilter, new Object[]{unidad}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada para un Alumno con un Cargo específico.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del alumno
     * @return Lista de LaborRealizada
     */
    @Override
    public List<LaborRealizada> findAutoridadAlumno(String cargo, Integer rut) {
        String strFilter = "EXISTS (SELECT * FROM alu_car WHERE "
                + "facultad_pkg.get_unidad_x_unidad(lrea_unidad) = facultad_pkg.get_unidad_x_carrera_mencion(aca_cod_car, aca_cod_men) "
                + " and aca_rut = ?)";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada para un Profesor con un Cargo específico.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del profesor
     * @return Lista de LaborRealizada
     */
    @Override
    public List<LaborRealizada> findAutoridadProfesor(String cargo, Integer rut) {
        String strFilter = "EXISTS (SELECT * FROM curso_profesor, curso_car WHERE "
                + "cpro_asign = ccar_asign and cpro_elect = ccar_elect and "
                + "cpro_coord = ccar_coord and cpro_secc = ccar_secc and "
                + "cpro_agno = ccar_agno and cpro_sem = ccar_sem and cpro_prof_rut = ? and "
                + "facultad_pkg.get_unidad_x_unidad(lrea_unidad) = facultad_pkg.get_unidad_x_carrera_mencion(ccar_cod_car, ccar_cod_men))";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada por Unidad y Carrera de un Profesor en un
     * Departamento.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del profesor
     * @return Lista de LaborRealizada
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LaborRealizada> findAutoridadAcademicaDeptoProfesor(String cargo, Integer rut) {
        String strFilter = "EXISTS ("
                + "    SELECT 1 FROM ("
                + "        SELECT DISTINCT ccar_cod_car, ccar_cod_men "
                + "        FROM curso_car cc "
                + "        JOIN curso_profesor cp "
                + "          ON cp.cpro_asign = cc.ccar_asign "
                + "         AND cp.cpro_elect = cc.ccar_elect "
                + "         AND cp.cpro_coord = cc.ccar_coord "
                + "         AND cp.cpro_secc = cc.ccar_secc "
                + "         AND cp.cpro_agno = cc.ccar_agno "
                + "         AND cp.cpro_sem = cc.ccar_sem "
                + "        WHERE cp.cpro_prof_rut = ?"
                + "    ) unidad"
                + "    WHERE lrea_unidad = departamento_pkg.get_unidad_x_carrera_mencion(unidad.ccar_cod_car, unidad.ccar_cod_men)"
                + ")";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada para un Alumno en un Departamento Académico.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del alumno
     * @return Lista de LaborRealizada
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LaborRealizada> findAutoridadAcademicaAlumnoDepto(String cargo, Integer rut) {
        String strFilter = "EXISTS (SELECT * FROM alu_car WHERE "
                + "lrea_unidad = departamento_pkg.get_unidad_x_carrera_mencion(aca_cod_car, aca_cod_men) "
                + "and aca_ccal <= 2 and aca_rut = ?)";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada por Mencion y Alumno en un Departamento Académico.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del alumno
     * @return Lista de LaborRealizada
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LaborRealizada> findAutoridadAcademicaMencionAlumno(String cargo, Integer rut) {
        String strFilter = "EXISTS (SELECT * FROM alu_car, mencion WHERE "
                + "men_cod_car = aca_cod_car and men_cod_men = aca_cod_men and "
                + "lrea_unidad = men_unidad and aca_ccal <= 2 and aca_rut = ?)";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }

    /**
     * Buscar LaborRealizada por Profesor y Mencion en un Departamento
     * Académico.
     *
     * @param cargo Cargo del usuario
     * @param rut Rut del profesor
     * @return Lista de LaborRealizada
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<LaborRealizada> findAutoridadAcademicaMencionProfesor(String cargo, Integer rut) {    
        String strFilter = "EXISTS (SELECT * FROM curso_profesor, curso_car, mencion WHERE "
                + "lrea_unidad = men_unidad and cpro_asign = ccar_asign and "
                + "cpro_elect = ccar_elect and cpro_coord = ccar_coord and "
                + "cpro_secc = ccar_secc and cpro_agno = ccar_agno and cpro_sem = ccar_sem "
                + "and cpro_prof_rut = ? and men_cod_car = ccar_cod_car "
                + "and men_cod_men = ccar_cod_men)";

        return findLaborRealizada(cargo, strFilter, new Object[]{rut}, new Type[]{StandardBasicTypes.INTEGER});
    }
}
