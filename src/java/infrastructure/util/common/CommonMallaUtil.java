/*
 * @(#)CommonMallaUtil.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package infrastructure.util.common;

import domain.model.Malla;
import domain.model.AluCar;
import domain.model.CalificacionLogroAdicional;
import domain.model.ProcedenciaCalificacion;
import domain.model.PlanId;
import domain.model.Asignatura;
import domain.model.CalificacionId;
import domain.model.Calificacion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import domain.repository.MallaRepository;
import session.GenericSession;
import session.WorkSession;
import infrastructure.support.MallaContainerSupport;
import infrastructure.support.MallaNodoSupport;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
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
    public static MallaContainerSupport getMallaCommonAlumno(AluCar aluCar, String user) {
        return getMallaGrafica(aluCar, user);
    }

    /**
     * Method description
     *
     *
     * @param aluCar
     *
     * @return
     */
    private static MallaContainerSupport getMallaGrafica(AluCar aluCar, String user) {
        MallaRepository mallaRepository
                = ContextUtil.getDAO().getMallaRepository(ActionUtil.getDBUser());
        List<Object[]> lCalificacion = mallaRepository.getCalificacionesMalla(aluCar);
        /*List<Object[]> lInscripcion = mallaRepository.getInscripcionesMalla(aluCar);*/
        List<Object[]> lInscripcion = ("AL".equals(user) && "SI".equals(aluCar.getParametros().getBloqueada()))
                ? new ArrayList<>()
                : mallaRepository.getInscripcionesMalla(aluCar);

        List<List<MallaNodoSupport>> malla;

        if (aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip() == 16
                && aluCar.getAcaCodMen() == 2) {
            malla = getInscripcionEconomia(getMalla(lCalificacion), lInscripcion, aluCar, mallaRepository);
        } else {
            malla = getInscripcion(getMalla(lCalificacion), lInscripcion, aluCar, mallaRepository);
        }

        MallaContainerSupport mallaContainer = new MallaContainerSupport();

        mallaContainer.setMalla(malla);

        return mallaContainer;
    }

    /**
     * Method description
     *
     *
     * @param lMalla
     *
     * @return
     */
    private static List<List<MallaNodoSupport>> getMalla(List<Object[]> lMalla) {
        return lMalla.stream()
                .map(mallaObj -> {
                    MallaNodoSupport nodo = new MallaNodoSupport("P");
                    nodo.setAsignatura(((Number) mallaObj[1]).intValue());
                    nodo.setNombre((String) mallaObj[2]);
                    nodo.setElectiva((String) mallaObj[3]);
                    nodo.setTipo("P");
                    nodo.setEstado((String) mallaObj[5]);
                    nodo.setReprobaciones(((Number) mallaObj[4]).intValue());
                    nodo.setLinea(((Number) mallaObj[7]).intValue());
                    nodo.setRequisitos((String) mallaObj[8]);
                    nodo.setCorrel(((Number) mallaObj[9]).intValue());
                    nodo.setTelSct((String) mallaObj[10]);
                    return new AbstractMap.SimpleEntry<>(((Number) mallaObj[0]).intValue(), nodo);
                })
                .collect(Collectors.groupingBy(
                        AbstractMap.SimpleEntry::getKey,
                        Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())
                ))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // Asegura que los niveles estén ordenados
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
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
     *
     * @param malla
     * @param lInscripcion
     * @param aluCar
     * @param mallaRepository
     *
     * @return
     */
    private static List<List<MallaNodoSupport>> getInscripcion(List<List<MallaNodoSupport>> malla,
            List<Object[]> lInscripcion, AluCar aluCar, MallaRepository mallaRepository) {

        if (Arrays.asList(16, 20, 33, 35).contains(aluCar.getPlan().getMencion().getCarrera().getTcarrera().getTcrCtip())) {
            final int[] nextElectivo = {mallaRepository.getNextElectivo(aluCar)};

            lInscripcion.stream()
                    .map(inscripcion -> {
                        int asign = ((Number) inscripcion[0]).intValue();
                        String elect = (String) inscripcion[1];

                        if ("S".equals(elect)) {
                            asign = mallaRepository.getAsignaturaElectiva(aluCar.getPlan().getId(), nextElectivo[0]);
                            nextElectivo[0]++;
                        }

                        return new Object[]{asign, inscripcion[2]};
                    })
                    .filter(inscripcion -> (int) inscripcion[0] > 0)
                    .forEach(inscripcion -> {
                        MallaNodoSupport nodo = getNodoMalla(malla, (int) inscripcion[0]);
                        if (nodo != null) {
                            nodo.setEstado((String) inscripcion[1]);
                        }
                    });
        } else {
            lInscripcion.stream()
                    .map(inscripcion -> new Object[]{((Number) inscripcion[0]).intValue(), inscripcion[2]})
                    .forEach(inscripcion -> {
                        MallaNodoSupport nodo = getNodoMalla(malla, (int) inscripcion[0]);
                        if (nodo != null) {
                            nodo.setEstado((String) inscripcion[1]);
                        }
                    });
        }
        return malla;
    }

    /**
     * Method description
     *
     *
     * @param malla
     * @param lInscripcion
     * @param aluCar
     * @param mallaRepository
     *
     * @return
     */
    private static List<List<MallaNodoSupport>> getInscripcionEconomia(
            List<List<MallaNodoSupport>> malla,
            List<Object[]> lInscripcion,
            AluCar aluCar,
            MallaRepository mallaRepository) {

        Object[] nextElectivoObject = mallaRepository.getNextElectivoxTipo(aluCar);
        PlanId idPlan = aluCar.getPlan().getId();

        // Inicializamos contadores de electivos
        Map<String, Integer> nextElectivos = new HashMap<>();
        nextElectivos.put("E", ((Number) nextElectivoObject[0]).intValue());
        nextElectivos.put("H", ((Number) nextElectivoObject[1]).intValue());
        nextElectivos.put("C", ((Number) nextElectivoObject[2]).intValue());

        // Procesamos las inscripciones usando streams
        lInscripcion.forEach(inscripcion -> {
            int asign = ((Number) inscripcion[0]).intValue();
            String elect = (String) inscripcion[1];

            if ("S".equals(elect)) {
                String tipo = mallaRepository.getTipoElect(idPlan, asign);
                asign = mallaRepository.getAsignaturaElectivaxTipo(idPlan, nextElectivos.get(tipo), tipo);
                nextElectivos.put(tipo, nextElectivos.get(tipo) + 1);
            }
            
            if (asign > 0) {
                getNodoMalla(malla, asign).setEstado((String) inscripcion[2]);
            }
        });

        return malla;
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
        List<CalificacionLogroAdicional> lista = genericSession.getMallaContainer().getCalificacionRequisitoAdicionalLogroList().stream()
                .filter(calificacion -> calificacion.getId().getClaReq() == adicional.intValue()) // Filtra las calificaciones que coinciden con el requisito adicional
                .collect(Collectors.toList()); // Recolecta los resultados en una lista

        genericSession.getWorkSession(key).getAluCar().setCalificacionRequisitoAdicionalLogroList(lista); // Asigna la lista filtrada
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
