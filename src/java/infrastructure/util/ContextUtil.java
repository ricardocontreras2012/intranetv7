package infrastructure.util;

import domain.model.*;
import infrastructure.persistence.dao.FactoryConcreteDAO;

import java.text.Collator;
import java.util.*;

import static infrastructure.util.HibernateUtil.closeSession;
import static infrastructure.util.HibernateUtil.configure;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.common.CommonMaterialUtil.getMapaTipoMaterial;

/**
 * Contexto de la Intranet. Singleton (Bill Pugh)
 */
public final class ContextUtil {

    private static Collator collator;
    private static List<Comuna> comunaList = Collections.emptyList();
    private static Map<Integer, List<Comuna>> comunaMap = Collections.emptyMap();
    private static List<Dia> diaList = Collections.emptyList();
    private static Map<String, Integer> diaMap = Collections.emptyMap();
    private static FactoryConcreteDAO dao;
    private static Locale locale;
    private static List<ParamArchivosWeb> paramArchivosWebList = Collections.emptyList();
    private static List<Region> regionList = Collections.emptyList();
    private static List<Tevaluacion> tevaluacionList = Collections.emptyList();
    private static Map<String, List<Tmaterial>> tipoMaterialMap = Collections.emptyMap();
    private static List<Tramite> tramiteList = Collections.emptyList();
    private static Map<Integer, Tramite> tramiteMap = Collections.emptyMap();
    private static List<EstadoSolicitud> estadoSolicitudList = Collections.emptyList();
    private static List<EstadoCivil> estadoCivilList = Collections.emptyList();
    private static final List<AreaTrabajo> areaTrabajoList = Collections.emptyList();
    private static List<TmotivoSolicitudInscripcion> tmotivoList = Collections.emptyList();

    private ContextUtil() {
        try {
            System.out.println("Inicializando Contexto...");

            dao = new FactoryConcreteDAO();
            configure(true);
            System.out.println("Hibernate Configurado.");

            paramArchivosWebList = safeList(dao.getParamArchivosWebRepository("CM").findAll());
            tramiteList = safeList(dao.getTramiteRepository("CM").find());
            tramiteMap = safeMap(new Tramite().getTramiteMap(tramiteList));
            diaList = safeList(dao.getDiaRepository("CM").findClases());
            diaMap = new HashMap<>();
            for (Dia dia : diaList) {
                diaMap.put(dia.getDiaCod(), dia.getDiaCorrel());
            }

            tevaluacionList = safeList(dao.getTevaluacionRepository("CM").findAllOrdered(new String[]{"tevalDes"}));
            regionList = safeList(dao.getRegionRepository("CM").findAllOrdered(new String[]{"regCod"}));
            comunaList = safeList(dao.getComunaRepository("CM").find());
            comunaMap = safeMap(new Comuna().getComunaMap(regionList, comunaList));
            estadoCivilList = safeList(dao.getEstadoCivilRepository("CM").find());
            estadoSolicitudList = safeList(dao.getEstadoSolicitudRepository("CM").find());
            tipoMaterialMap = safeMap(getMapaTipoMaterial(dao));
            tmotivoList = safeList(dao.getTmotivoSolicitudInscripcionRepository("CM").find());

            locale = new Locale("es", "CL");
            collator = Collator.getInstance(locale);

            closeSession();
            System.out.println("FIN inicializacion de contexto");

        } catch (InfrastructureExceptionUtil | Error e) {
            logExceptionMessage(e);
        }
    }

    private static class InstanceHolder {
        private static final ContextUtil INSTANCE = new ContextUtil();
    }

    public static ContextUtil getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // Getters seguros con copia defensiva

    public static List<Comuna> getComunaList() {
        return new ArrayList<>(comunaList);
    }

    public static Map<Integer, List<Comuna>> getComunaMap() {
        return new HashMap<>(comunaMap);
    }

    public static List<Dia> getDiaList() {
        return new ArrayList<>(diaList);
    }

    public static Map<String, Integer> getDiaMap() {
        return new HashMap<>(diaMap);
    }

    /**
     * Devuelve instancia de uso interno. No debe ser modificada desde fuera.
     */
    public static FactoryConcreteDAO getDAO() {
        return dao; // ⚠️ se asume uso de solo lectura/controlado internamente
    }

    public static List<ParamArchivosWeb> getParamArchivosWebList() {
        return new ArrayList<>(paramArchivosWebList);
    }

    public static List<Region> getRegionList() {
        return new ArrayList<>(regionList);
    }

    public static List<Tevaluacion> getTevaluacionList() {
        return new ArrayList<>(tevaluacionList);
    }

    public static Map<String, List<Tmaterial>> getTipoMaterialMap() {
        return new HashMap<>(tipoMaterialMap);
    }

    public static List<Tramite> getTramiteList() {
        return new ArrayList<>(tramiteList);
    }

    public static Map<Integer, Tramite> getTramiteMap() {
        return new HashMap<>(tramiteMap);
    }

    /**
     * Devuelve un clon para evitar modificar el collator interno.
     */
    public static Collator getCollator() {
        return (Collator) collator.clone();
    }

    /**
     * Devuelve una copia defensiva del locale.
     */
    public static Locale getLocale() {
        return new Locale(locale.getLanguage(), locale.getCountry());
    }

    public static List<EstadoCivil> getEstadoCivilList() {
        return new ArrayList<>(estadoCivilList);
    }

    public static List<AreaTrabajo> getAreaTrabajoList() {
        return new ArrayList<>(areaTrabajoList);
    }

    public static List<EstadoSolicitud> getEstadoSolicitudList() {
        return new ArrayList<>(estadoSolicitudList);
    }

    public static List<TmotivoSolicitudInscripcion> getTmotivoSolicitudInscripcion() {
        return new ArrayList<>(tmotivoList);
    }

    // Métodos auxiliares para protección defensiva

    private static <T> List<T> safeList(List<T> list) {
        return list != null ? Collections.unmodifiableList(new ArrayList<>(list)) : Collections.emptyList();
    }

    private static <K, V> Map<K, V> safeMap(Map<K, V> map) {
        return map != null ? Collections.unmodifiableMap(new HashMap<>(map)) : Collections.emptyMap();
    }
}
