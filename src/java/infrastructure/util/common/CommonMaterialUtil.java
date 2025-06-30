/*
 * @(#)CommonMaterialUtil.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package infrastructure.util.common;

import infrastructure.persistence.dao.FactoryConcreteDAO;
import domain.model.Curso;
import domain.model.CursoId;
import domain.model.MaterialApoyo;
import domain.model.Tmaterial;
import domain.model.TmaterialPerfil;
import java.io.File;
import java.util.*;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.action.common.ActionCommonSupport;
import static infrastructure.util.AppStaticsUtil.APP_DB_USERS;
import static infrastructure.util.AppStaticsUtil.NORMAL_USERS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getSysdate;
import static infrastructure.util.HibernateUtil.beginTransaction;
import static infrastructure.util.HibernateUtil.commitTransaction;
import static infrastructure.util.common.CommonArchivoUtil.doUpload;
import static infrastructure.util.common.CommonArchivoUtil.getMaterialFileName;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
@SuppressWarnings("ALL")
public final class CommonMaterialUtil {

    private CommonMaterialUtil() {
    }

    /**
     * Method description
     *
     * @param id
     * @param rut
     * @param userType
     * @param userTypeShort
     * @param tipoMaterial
     * @return
     * @throws java.lang.Exception
     */
    public static List<Tmaterial> getListaMateriales(CursoId id, Integer rut, String userType, String userTypeShort,
            String tipoMaterial)
            throws Exception {

        return getTmaterialFromMaterial(ContextUtil.getDAO().getMaterialApoyoPersistence(userType).find(
                id, rut, userTypeShort, tipoMaterial));
    }

    /**
     * Method description
     *
     * @param id
     * @return
     * @throws java.lang.Exception
     */
    public static List<Tmaterial> getListaOtrosMateriales(CursoId id) throws Exception {
        return getTmaterialFromMaterial(ContextUtil.getDAO().getMaterialApoyoPersistence(ActionUtil.getDBUser()).findOtros(id));
    }

    /**
     * Method description
     *
     * @param materialApoyoList
     * @return
     */
    private static List<Tmaterial> getTmaterialFromMaterial(List<MaterialApoyo> materialApoyoList) {
        // Filtrar y agrupar MaterialApoyo por Tmaterial
        Map<Tmaterial, List<MaterialApoyo>> groupedByTmaterial = materialApoyoList.stream()
                .collect(Collectors.groupingBy(MaterialApoyo::getTmaterial)); // Agrupa por Tmaterial

        // Ordenar los materiales dentro de cada grupo por MatCorrel
        groupedByTmaterial.forEach((tmaterial, materialApoyoListAux) -> {
            materialApoyoListAux.sort(Comparator.comparing(MaterialApoyo::getMatCorrel)); // Ordena por MatCorrel
            tmaterial.setMaterials(materialApoyoListAux); // Establece los materiales ordenados
        });

        // Crear lista de Tmaterial, ordenada por TmaDes
        List<Tmaterial> tmat = new ArrayList<>(groupedByTmaterial.keySet());
        tmat.sort(Comparator.comparing(Tmaterial::getTmaDes)); // Ordena por TmaDes

        return tmat;
    }

    /**
     * Method description
     *
     * @param dao
     * @return
     */
    public static Map<String, List<Tmaterial>> getMapaTipoMaterial(FactoryConcreteDAO dao) {
        // Obtener la lista de TmaterialPerfil desde el DAO
        List<TmaterialPerfil> tmaterialPerfilList = dao.getTmaterialPerfilPersistence("CM").find();

        // Crear el mapa usando Streams para agrupar y filtrar
        return NORMAL_USERS.entrySet().stream() // Iterar sobre las entradas de NORMAL_USERS
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // La clave será el tipo de usuario (userType)
                        entry -> tmaterialPerfilList.stream() // Para cada userType, filtrar la lista de TmaterialPerfil
                                .filter(tmaterialPerfil -> tmaterialPerfil.getId().getTmapDes().equals(entry.getKey())) // Filtrar por userType
                                .map(TmaterialPerfil::getTmaterial) // Mapear a Tmaterial
                                .collect(Collectors.toList()) // Recoger los resultados en una lista
                ));
    }

    /**
     * Method description
     *
     * @param action
     * @param genericSession
     * @param tipo
     * @param upload
     * @param uploadFileName
     * @param caption
     * @param key
     * @throws Exception
     */
    public static void doNewFile(ActionCommonSupport action, GenericSession genericSession, Integer tipo, File upload,
            String uploadFileName, String caption, final String key)
            throws Exception {
        Integer folio;
        String nombre;

        folio = CommonSequenceUtil.getDocumentSeq();
        nombre = getMaterialFileName(uploadFileName, folio);
        doUpload(action, upload, nombre, "mat");

        MaterialApoyo materialApoyo = new MaterialApoyo();
        WorkSession ws = genericSession.getWorkSession(key);
        Curso curso = new Curso();
        CursoId id = CommonCursoUtil.getParent(genericSession.getCurso(key), ws.getCargaEspejo());
        curso.setId(id);

        materialApoyo.setMatCorrel(folio);
        materialApoyo.setCurso(curso);
        materialApoyo.setMatArchivo(nombre);
        materialApoyo.setMatDescripcion(caption);
        materialApoyo.setMatRutAutor(genericSession.getRut());
        materialApoyo.setMatTipo(tipo);
        materialApoyo.setMatPerfil(APP_DB_USERS.get(genericSession.getUserType()));
        materialApoyo.setMatFechaArchivado(getSysdate());
        materialApoyo.setMatFechaHabilitacion(getSysdate());
        beginTransaction(ActionUtil.getDBUser());
        ContextUtil.getDAO().getMaterialApoyoPersistence(ActionUtil.getDBUser()).makePersistent(
                materialApoyo);
        commitTransaction();
    }

    /**
     * Method description
     *
     * @param lTmaterial
     * @param tipo
     * @param material
     * @return
     * @throws java.lang.Exception
     */
    public static boolean existeMaterial(List<Tmaterial> lTmaterial, Integer tipo, Integer material) throws Exception {
        boolean retValue = false;

        if (tipo < lTmaterial.size()) {
            if (material < lTmaterial.get(tipo).getMateriales().size()) {
                retValue = true;
            }
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @param lTmaterial
     * @param tipo
     * @param material
     * @return
     * @throws java.lang.Exception
     */
    public static MaterialApoyo getMaterial(List<Tmaterial> lTmaterial, Integer tipo, Integer material) throws Exception {
        MaterialApoyo retValue = null;

        if (existeMaterial(lTmaterial, tipo, material)) {
            retValue = lTmaterial.get(tipo).getMateriales().get(material);
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @param tMaterial
     * @return
     */
    public static List<MaterialApoyo> getMateriales(Tmaterial tMaterial) {
        return tMaterial.getMaterials().stream() // Crear un Stream de los materiales
                .sorted(Comparator.comparing(MaterialApoyo::getMatCorrel)) // Ordenar por MatCorrel
                .collect(Collectors.toList()); // Recoger los materiales ordenados en una lista
    }

    /**
     * Method description
     *
     * @param genericSession
     * @param key
     * @param prefijo
     * @return
     * @throws java.lang.Exception
     */
    public static String getContentDispositionCurso(final GenericSession genericSession, final String key, final String prefijo)
            throws Exception {
        final WorkSession ws = genericSession.getWorkSession(key);

        return "attachment;filename=\"" + prefijo + "_" + ws.getCurso().getNombreNormalizado() + "_"
                + CommonSequenceUtil.getDocumentSeq() + ".xlsx\"";
    }

    public static String getContentDispositionSemAgno(final GenericSession genericSession, final String key, final String prefijo) throws Exception {
        final WorkSession ws = genericSession.getWorkSession(key);

        return "attachment;filename=\"" + prefijo + "_" + ws.getSemAct() + "_" + ws.getAgnoAct() + "_"
                + CommonSequenceUtil.getDocumentSeq() + ".xlsx\"";
    }

    public static String getContentDispositionProfesor(final GenericSession genericSession, final String key, final String prefijo)
            throws Exception {
        final WorkSession ws = genericSession.getWorkSession(key);

        return "attachment;filename=\"" + prefijo + "_" + ws.getProfesor().getProfRut() + "_"
                + CommonSequenceUtil.getDocumentSeq() + ".xlsx\"";
    }
}
