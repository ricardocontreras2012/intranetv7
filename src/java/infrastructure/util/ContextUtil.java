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

            paramArchivosWebList = safeList(dao.getParamArchivosWebPersistence("CM").findAll());
            tramiteList = safeList(dao.getTramitePersistence("CM").find());
            tramiteMap = safeMap(new Tramite().getTramiteMap(tramiteList));
            diaList = safeList(dao.getDiaPersistence("CM").findClases());
            diaMap = new HashMap<>();
            for (Dia dia : diaList) {
                diaMap.put(dia.getDiaCod(), dia.getDiaCorrel());
            }

            tevaluacionList = safeList(dao.getTevaluacionPersistence("CM").findAllOrdered(new String[]{"tevalDes"}));
            regionList = safeList(dao.getRegionPersistence("CM").findAllOrdered(new String[]{"regCod"}));
            comunaList = safeList(dao.getComunaPersistence("CM").find());
            comunaMap = safeMap(new Comuna().getComunaMap(regionList, comunaList));
            estadoCivilList = safeList(dao.getEstadoCivilPersistence("CM").find());
            estadoSolicitudList = safeList(dao.getEstadoSolicitudPersistence("CM").find());
            tipoMaterialMap = safeMap(getMapaTipoMaterial(dao));
            tmotivoList = safeList(dao.getTmotivoSolicitudInscripcionPersistence("CM").find());

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

    // Métodos seguros que devuelven copias inmutables

    public static List<Comuna> getComunaList() {
        return comunaList;
    }

    public static Map<Integer, List<Comuna>> getComunaMap() {
        return comunaMap;
    }

    public static List<Dia> getDiaList() {
        return diaList;
    }

    public static Map<String, Integer> getDiaMap() {
        return diaMap;
    }

    public static FactoryConcreteDAO getDAO() {
        return dao;
    }

    public static List<ParamArchivosWeb> getParamArchivosWebList() {
        return paramArchivosWebList;
    }

    public static List<Region> getRegionList() {
        return regionList;
    }

    public static List<Tevaluacion> getTevaluacionList() {
        return tevaluacionList;
    }

    public static Map<String, List<Tmaterial>> getTipoMaterialMap() {
        return tipoMaterialMap;
    }

    public static List<Tramite> getTramiteList() {
        return tramiteList;
    }

    public static Map<Integer, Tramite> getTramiteMap() {
        return tramiteMap;
    }

    public static Collator getCollator() {
        return collator;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static List<EstadoCivil> getEstadoCivilList() {
        return estadoCivilList;
    }

    public static List<AreaTrabajo> getAreaTrabajoList() {
        return areaTrabajoList;
    }

    public static List<EstadoSolicitud> getEstadoSolicitudList() {
        return estadoSolicitudList;
    }

    public static List<TmotivoSolicitudInscripcion> getTmotivoSolicitudInscripcion() {
        return tmotivoList;
    }

    // Métodos auxiliares para hacer defensiva la inmutabilidad
    private static <T> List<T> safeList(List<T> list) {
        return list != null ? Collections.unmodifiableList(new ArrayList<>(list)) : Collections.emptyList();
    }

    private static <K, V> Map<K, V> safeMap(Map<K, V> map) {
        return map != null ? Collections.unmodifiableMap(new HashMap<>(map)) : Collections.emptyMap();
    }
}
