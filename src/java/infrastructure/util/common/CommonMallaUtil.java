/*
 * @(#)CommonMallaUtil.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.util.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import domain.model.Malla;
import domain.model.AluCar;
import domain.model.CalificacionLogroAdicional;
import domain.model.ProcedenciaCalificacion;
import domain.model.Asignatura;
import domain.model.CalificacionId;
import domain.model.Calificacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import domain.repository.MallaRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MallaJsonSupport;
import infrastructure.support.MallaNodoSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMallaUtil {

    private CommonMallaUtil() {
    }

    /**
     * Method description
     *
     * @param aluCar
     * @param user
     * @return
     */
    public static List<MallaJsonSupport> getMallaCommonAlumnoJson(AluCar aluCar, String user) {
        return getMallaGraficaJson(aluCar, user);
    }

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    private static List<MallaJsonSupport> getMallaGraficaJson(AluCar aluCar, String user) {

        MallaRepository mallaRepository = ContextUtil.getDAO().getMallaRepository(ActionUtil.getDBUser());

        String mallaRaw = mallaRepository.getMallaJson(aluCar);

        List<MallaJsonSupport> result = new ArrayList<>();

        // Usamos Gson para deserializar el JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Deserializamos el JSON en una lista de objetos MallaJsonSupport
        // Asumimos que el JSON tiene la estructura adecuada para deserializar
        try {
            Type listType = new TypeToken<List<MallaJsonSupport>>() {
            }.getType();
            result = gson.fromJson(mallaRaw, listType);

        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        return result;
    }
    
    /**
     * Method description
     *
     *
     * @param malla
     * @param asign
     *
     * @return
     */
    private static MallaNodoSupport getNodoMalla(List<List<MallaNodoSupport>> malla, int asign) {
        return malla.stream()
                .flatMap(List::stream)
                .filter(nodo -> nodo.getAsignatura() == asign)
                .findFirst()
                .orElse(null);
    }

    /**
     * Method description
     *
     *
     * @param malla
     *
     * @return
     */
    public static int getMaxLinea(List<List<MallaNodoSupport>> malla) {
        return malla.stream()
                .flatMap(List::stream)
                .mapToInt(MallaNodoSupport::getLinea)
                .max()
                .orElse(0);
    }

    /**
     * Method description
     *
     *
     * @param malla
     * @param nivel
     *
     * @return
     */
    public static int getMaxLineaNivel(List<List<MallaNodoSupport>> malla, int nivel) {
        return malla.get(nivel).stream()
                .mapToInt(MallaNodoSupport::getLinea)
                .max()
                .orElse(0);
    }

    /**
     * Method description
     *
     *
     * @param malla
     * @param nivel
     * @param linea
     *
     * @return
     */
    public static MallaNodoSupport getNodo(List<List<MallaNodoSupport>> malla, int nivel, int linea) {
        return malla.get(nivel).stream()
                .filter(nodo -> nodo.getLinea() == linea)
                .findFirst()
                .orElse(null);
    }

    
    /**
     * Method description
     *
     * @param genericSession
     * @param asignatura
     * @param key
     */
    public static void setCalificacionesAsignatura(GenericSession genericSession, Integer asignatura, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        AluCar aluCar = ws.getAluCar();
        List<Object[]> lCalificacionesObject = ContextUtil.getDAO()
                .getMallaRepository("CM")
                .getCalificacionesAsignatura(aluCar, asignatura);

        // RESET
        ws.getAluCar().setCalificacionesAsignatura(new ArrayList<>());

        List<Calificacion> lCalificacion = lCalificacionesObject.stream()
                .map(obj -> {
                    Calificacion calificacion = new Calificacion();
                    CalificacionId id = new CalificacionId();
                    ProcedenciaCalificacion procedencia = new ProcedenciaCalificacion();
                    calificacion.setId(id);
                    calificacion.setAluCar(aluCar);
                    id.setCalRut(aluCar.getId().getAcaRut());
                    id.setCalCodCar(aluCar.getId().getAcaCodCar());
                    id.setCalAgnoIng(aluCar.getId().getAcaAgnoIng());
                    id.setCalSemIng(aluCar.getId().getAcaSemIng());
                    id.setCalAsign(((Number) obj[0]).intValue());
                    id.setCalElect((String) obj[1]);
                    id.setCalAgno(((Number) obj[4]).intValue());
                    id.setCalSem(((Number) obj[5]).intValue());
                    calificacion.setCalCoord((String) obj[2]);
                    calificacion.setCalSecc(getInteger(obj[3]));
                    calificacion.setCalNotaFin(getBigDecimal(obj[6]));
                    calificacion.setCalConcep((String) obj[7]);
                    calificacion.setCalSitAlu((String) obj[8]);
                    procedencia.setProcCod((String) obj[9]);
                    calificacion.setProcedenciaCalificacion(procedencia);
                    calificacion.setNombreFull((String) obj[10]);

                    return calificacion;
                })
                .collect(Collectors.toList());

        // Asignar la lista de calificaciones al objeto AluCar
        ws.getAluCar().setCalificacionesAsignatura(lCalificacion);
    }

    /**
     * Method description
     *
     * @param genericSession
     * @param adicional
     * @param key
     */
    public static void getCalificacionRequisitoAdicionalLogroionales(GenericSession genericSession, Integer adicional, String key) {
        List<CalificacionLogroAdicional> lista = genericSession.getMallaContainer().getcalificacionLogroAdicionalList().stream()
                .filter(calificacion -> calificacion.getId().getClaReq() == adicional.intValue()) // Filtra las calificaciones que coinciden con el requisito adicional
                .collect(Collectors.toList()); // Recolecta los resultados en una lista

        genericSession.getWorkSession(key).getAluCar().setcalificacionLogroAdicionalList(lista); // Asigna la lista filtrada
    }

    /**
     * Method description
     *
     *
     * @param numero
     *
     * @return
     */
    private static Integer getInteger(Object numero) {
        if (numero != null) {
            return ((Number) numero).intValue();
        }

        return null;
    }

    /**
     * Method description
     *
     *
     * @param numero
     *
     * @return
     */
    private static BigDecimal getBigDecimal(Object numero) {
        if (numero != null) {
            return (BigDecimal) numero;
        }

        return null;
    }

    public static List<Asignatura> getAsignaturasMalla(List<Malla> mallaList) {
        final int[] asignAnt = {0};  // Usamos un array para poder modificarlo dentro de la lambda
        List<Asignatura> newList = new ArrayList<>();

        mallaList.stream()
                .map(Malla::getAsignatura)
                .filter(asiMalla -> Integer.compare(asiMalla.getAsiCod(), asignAnt[0]) != 0)
                .forEach(asiMalla -> {
                    Asignatura asign = new Asignatura();
                    asign.setAsiCod(asiMalla.getAsiCod());
                    asign.setAsiNom(asiMalla.getAsiNom());
                    asign.setAsiElect(asiMalla.getAsiElect());
                    newList.add(asign);
                    asignAnt[0] = asiMalla.getAsiCod(); // Actualizamos el valor del array
                });

        return newList;
    }
}
