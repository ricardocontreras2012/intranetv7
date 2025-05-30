/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.util;

import domain.model.EstadoSolicitud;
import domain.model.TmotivoSolicitudInscripcion;
import domain.model.Tevaluacion;
import domain.model.AreaTrabajo;
import domain.model.Dia;
import domain.model.Region;
import domain.model.Comuna;
import domain.model.Tramite;
import domain.model.EstadoCivil;
import domain.model.Tmaterial;
import domain.model.ParamArchivosWeb;
import infrastructure.persistence.dao.FactoryConcreteDAO;
import java.text.Collator;
import java.util.*;
import static infrastructure.util.HibernateUtil.closeSession;
import static infrastructure.util.HibernateUtil.configure;
import static infrastructure.util.LogUtil.logExceptionMessage;
import static infrastructure.util.common.CommonMaterialUtil.getMapaTipoMaterial;

/**
 * Contexto de la Intranet. Implementado segun Patron de Diseño Singleton
 * (BillPughSingleton)
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public class ContextUtil {

    private static Collator collator;
    private static List<Comuna> comunaList;
    private static Map<Integer, List<Comuna>> comunaMap;
    private static List<Dia> diaList;
    private static Map<String, Integer> diaMap;
    private static FactoryConcreteDAO dao;
    private static Locale locale;
    private static List<ParamArchivosWeb> paramArchivosWebList;
    private static List<Region> regionList;
    private static List<Tevaluacion> tevaluacionList;
    private static Map<String, List<Tmaterial>> tipoMaterialMap;
    private static List<Tramite> tramiteList;
    private static Map<Integer, Tramite> tramiteMap;
    private static List<EstadoSolicitud> estadoSolicitudList;
    private static Map<String, String> noLogActionMap;
    private static Map<String, String> helpActionMap;
    private static List<EstadoCivil> estadoCivilList;
    private static List<AreaTrabajo> areaTrabajoList;
    private static List<TmotivoSolicitudInscripcion> tmotivoList;

    private ContextUtil() {
        try {

            System.out.println("Inicializando Contexto...");

            /*
                 * Acceso a la Base de Datos mediante Hibernate(4).
             */
            dao = new FactoryConcreteDAO();
            configure(true);
            System.out.println("Hibernate Configurado.");

            paramArchivosWebList
                    = dao.getParamArchivosWebPersistence("CM").findAll();

            System.out.println("Parametros de archivos web configurado.");

            tramiteList = dao.getTramitePersistence("CM").find();
            tramiteMap = new Tramite().getTramiteMap(dao.getTramitePersistence("CM").find());
            diaList = dao.getDiaPersistence("CM").findClases();
            diaMap = new HashMap<>();
            diaList.forEach(dia -> diaMap.put(dia.getDiaCod(), dia.getDiaCorrel()));
            tevaluacionList = dao.getTevaluacionPersistence("CM").findAllOrdered(new String[]{"tevalDes"});
            regionList = dao.getRegionPersistence("CM").findAllOrdered(
                    new String[]{"regCod"});
            comunaList = dao.getComunaPersistence("CM").find();
            comunaMap = new Comuna().getComunaMap(regionList, comunaList);
            estadoCivilList = dao.getEstadoCivilPersistence("CM").find();
            estadoSolicitudList = dao.getEstadoSolicitudPersistence("CM").find();
            tipoMaterialMap = getMapaTipoMaterial(dao);
            tmotivoList = dao.getTmotivoSolicitudInscripcionPersistence("CM").find();
            
            locale = new Locale("es", "CL");
            collator = Collator.getInstance(locale);    

            closeSession();

            System.out.println("FIN inicializacion de contexto");
        } catch (InfrastructureExceptionUtil e) {
            logExceptionMessage(e);
        } catch (Error e) {
            logExceptionMessage(e);
        }
    }

    private static class InstanceHolder {

        private static final ContextUtil INSTANCE = new ContextUtil();
    }

    public static ContextUtil getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Comuna> getComunaList() {
        return comunaList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Dia> getDiaList() {
        return diaList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static FactoryConcreteDAO getDAO() {
        return dao;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<ParamArchivosWeb> getParamArchivosWebList() {
        return paramArchivosWebList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Region> getRegionList() {
        return regionList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Tevaluacion> getTevaluacionList() {
        return tevaluacionList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Map<String, List<Tmaterial>> getTipoMaterialMap() {
        return tipoMaterialMap;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static List<Tramite> getTramiteList() {
        return tramiteList;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Map<Integer, List<Comuna>> getComunaMap() {
        return comunaMap;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Map<String, Integer> getDiaMap() {
        return diaMap;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Map<Integer, Tramite> getTramiteMap() {
        return tramiteMap;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Collator getCollator() {
        return collator;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public static Locale getLocale() {
        return locale;
    }

    /**
     *
     * @return
     */
    public static Map<String, String> getNoLogActionMap() {
        return noLogActionMap;
    }

    /**
     *
     * @return
     */
    public static Map<String, String> getHelpActionMap() {
        return helpActionMap;
    }

    /**
     *
     * @return
     */
    public static List<EstadoCivil> getEstadoCivilList() {
        return estadoCivilList;
    }

    /**
     *
     * @return
     */
    public static List<AreaTrabajo> getAreaTrabajoList() {
        return areaTrabajoList;
    }

    /**
     *
     * @return
     */
    public static List<EstadoSolicitud> getEstadoSolicitudList() {
        return estadoSolicitudList;
    }

    public static List<TmotivoSolicitudInscripcion> getTmotivoSolicitudInscripcion() {
        return tmotivoList;
    }
}
