/*
 * @(#)CommonMensajeProcessListaService.java
 *
 * Copyright (c) Copyright (c) 2023 FAE-USACH
 */
package service.common;

import domain.model.Profesor;
import domain.model.Curso;
import domain.model.LaborRealizada;
import domain.model.Unidad;
import domain.model.Mencion;
import domain.model.TmensajeDestino;
import static com.opensymphony.xwork2.Action.SUCCESS;
import static java.lang.String.valueOf;
import java.util.*;
import static java.util.Arrays.asList;
import domain.repository.TmensajeDestinoPersistence;
import session.GenericSession;
import session.WorkSession;
import static infrastructure.support.LaborSupport.*;
import infrastructure.support.MensajeNodeSupport;
import infrastructure.support.MensajeSupport;
import static infrastructure.util.AppStaticsUtil.PRIVILEGED_USERS;
import infrastructure.util.ActionUtil;
import infrastructure.util.ContextUtil;
import static infrastructure.util.SystemParametersUtil.MESSAGE_TO_MAX_LENGTH;
import infrastructure.util.common.CommonCursoUtil;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class CommonMensajeProcessListaService {

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @return Action status
     * @throws Exception Si el servicio genera una exception
     */
    public static String service(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);

        if (ws.getMensajeSupport() == null) {
            newRootMessage(genericSession, key);
            return SUCCESS;
        }

        MensajeNodeSupport messageNodeSupport = getNodeToProcess(ws.getMensajeSupport().getRootNode());

        if (messageNodeSupport == null) {
            setDestiny(genericSession, key);
            return ws.getMensajeFwd() == null ? "newMessage" : "forwardMessage";
        }

        return "PR".equals(messageNodeSupport.getState())
                ? "processList"
                : processNode(genericSession, messageNodeSupport, key);
    }

    /**
     * Method description
     *
     * @param rootNode
     * @return
     */
    private static MensajeNodeSupport getNodeToProcess(MensajeNodeSupport rootNode) {
        if (!"SP".equals(rootNode.getState())) {
            return null;
        }

        if (rootNode.getNodeList() == null) {
            if (rootNode.isTerminal()) {
                rootNode.setState("PR");
            }
            return rootNode;
        }

        return rootNode.getNodeList().stream()
                .map(node -> getNodeToProcess(node))
                .filter(node -> node != null)
                .peek(node -> {
                    if (node.isTerminal()) {
                        node.setState("PR");
                        if (rootNode.getNodeList().indexOf(node) == rootNode.getNodeList().size() - 1) {
                            rootNode.setState("PR");
                        }
                    }
                })
                .findFirst()
                .orElse(null);
    }

    /**
     * Method description
     *
     * @param rootNode
     * @return
     */
    private static String getDestiny(MensajeNodeSupport rootNode) {
        if (rootNode.isTerminal()) {
            String mainValue = rootNode.getValue();

            // Procesar nodeList
            String nodeListString = Optional.ofNullable(rootNode.getNodeList())
                    .map(list -> list.stream()
                    .map(MensajeNodeSupport::getValue) // Se obtiene el valor del nodo
                    .collect(Collectors.joining(", ", ": ", "")))
                    .orElse("");

            // Procesar nodeListBar
            String nodeListBarString = Optional.ofNullable(rootNode.getNodeListBar())
                    .map(list -> list.stream()
                    .map(MensajeNodeSupport::getValue) // Se obtiene el valor del nodo
                    .collect(Collectors.joining(", ", "(", ")")))
                    .orElse("");

            return mainValue + nodeListString + nodeListBarString;
        } else {
            return Optional.ofNullable(rootNode.getNodeList())
                    .map(list -> list.stream()
                    .map(node -> getDestiny(node)) // Se llama a getDestiny de forma explícita
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.joining("; ")))
                    .orElse("");
        }
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     * @throws Exception
     */
    private static void setDestiny(GenericSession genericSession, String key) throws Exception {
        WorkSession ws = genericSession.getWorkSession(key);
        String para = getDestiny(ws.getMensajeSupport().getRootNode());

        if (para.length() > MESSAGE_TO_MAX_LENGTH) {
            para = para.substring(0, MESSAGE_TO_MAX_LENGTH - 3) + "...";
        }

        ws.getMensajeSupport().setPara(para);
    }

    /**
     * Method description
     *
     * @param sesion
     * @return
     */
    private static String tipoUsuario(String sesion) {
        return asList("DE", "VDD", "VDI", "DD", "DP", "JC").contains(sesion) ? "PR" : sesion;
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     * @return
     */
    private static String processNode(GenericSession genericSession, MensajeNodeSupport node, String key)
            throws Exception {

        String id;
        String sufix = "";
        int posSufix;
        String retValue = SUCCESS;
        WorkSession ws = genericSession.getWorkSession(key);
        String userType = genericSession.getUserType();
        Integer rut = genericSession.getRut();

        id = node.getId();
        posSufix = id.indexOf("::");

        if (posSufix >= 0) {
            sufix = id.substring(posSufix + 2);
            id = id.substring(0, posSufix);
        }

        if (id != null) {
            if (asList("LI", "PR", "AL", "AY").contains(id)) {
                ws.getMensajeSupport().setCurrentNode(node);
                node.setTerminal(true);
                node.setState("PR");

                switch (id) {
                    case "LI":
                        retValue = "lista";
                        break;
                    case "PR":
                        retValue = "profesor";
                        break;
                    case "AL":
                        retValue = "alumno";
                        break;
                    case "AY":
                        retValue = "ayudante";
                }
            }
        }

        if (retValue.equals(SUCCESS)) {

            /**
             * * Procesamiento de Listas *
             */
            node.setNodeList(new ArrayList<>());

            if (null == id) {
                node.setNodeList(null);
            } else {
                switch (id) {
                    case "FA":
                        listaFacultades(genericSession, node);
                        getBarra(node, id);
                        break;
                    case "MPR":
                        node.setTerminal(true);
                        listaMisProfesores(genericSession, node, key);
                        break;
                    case "MAY":
                        node.setTerminal(true);
                        listaMisAyudantes(genericSession, node, key);
                        break;
                    case "MCC":
                        node.setTerminal(false);
                        listaCursosAlumno(genericSession, node, key);
                        break;
                    case "CURSOS_MAL":
                    case "CURSOS_MCC":
                        node.setTerminal(true);
                        listaAlumnosCurso(node, sufix);
                        break;
                    case "MCU":
                        node.setTerminal(true);
                        listaMisCursos(genericSession, node, key);
                        break;
                    case "MAL":
                        // GENERA LISTA CURSOS_MAL
                        node.setTerminal(false);
                        listaMisCursos(genericSession, node, key);
                        break;
                    case "DA":
                        // Departamentos Academicos Facultad
                        listaDeptosFacultad(genericSession, node);
                        getBarra(node, id);
                        break;
                    case "CU":
                        node.setTerminal(true);
                        listaCursos(genericSession, node);
                        break;
                    case "MJC":
                        node.setTerminal(true);
                        listaMisJefesCarrera(userType, rut, node);
                        break;
                    case "DE":
                        node.setTerminal(true);
                        listaDecanos(userType, rut, node);
                        break;
                    case "VDD":
                        node.setTerminal(true);
                        listaViceDecDocencia(userType, rut, node);
                        break;
                    case "VDI":
                        node.setTerminal(true);
                        listaViceDecInvestigacion(userType, rut, node);
                        break;
                    case "JC":
                        node.setTerminal(true);
                        listaJefesCarreraFacultad(genericSession, node);
                        break;
                    case "MDD":
                        node.setTerminal(true);
                        listaMiDirDepto(userType, rut, node);
                        break;
                    case "MSDD":
                        node.setTerminal(true);
                        listaMiSubDirDepto(genericSession, node, key);
                        break;
                    case "DD":
                        node.setTerminal(true);
                        listaDirDeptosFacultad(genericSession, node);
                        break;
                    case "MDP":
                        node.setTerminal(true);
                        listaMiDirPrograma(userType, rut, node);
                        break;
                    case "MSDP":
                        node.setTerminal(true);
                        listaMiSubDirPrograma(genericSession, node, key);
                        break;
                    case "MSD":
                        node.setTerminal(true);
                        listaMiSecretariaDocente(userType, rut, node);
                        break;
                    case "DP":
                        node.setTerminal(true);
                        listaDirProgramasFacultad(genericSession, node);
                        break;
                    case "CA":
                    case "MCA":
                        listaCarrerasProgramas(userType, rut, node, "C");
                        getBarra(node, id);
                        break;
                    case "MDA":
                        listaDeptos(userType, rut, node);
                        getBarra(node, id);
                        break;
                    case "MRC":
                        node.setTerminal(true);
                        listaRegistradorCurricular(userType, rut, node);
                        break;
                    case "MAS":
                        node.setTerminal(true);
                        listaAsistenteSocial(userType, rut, node);
                        break;
                    case "MJB":
                        node.setTerminal(true);
                        listaJefeBiblioteca(userType, rut, node);
                        break;
                    case "PG":
                    case "MPG":
                        listaCarrerasProgramas(userType, rut, node, "P");
                        getBarra(node, id);
                        break;
                    case "OTR":
                        listaOtros(genericSession, node);
                        break;
                    default:
                        node.setNodeList(null);
                        break;
                }
            }

            ws.getMensajeSupport().setCurrentNode(node);

            if (node.isTerminal()) {
                node.setState("PR");
            }
        }

        return retValue;
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     */
    private static void listaOtros(GenericSession genericSession, MensajeNodeSupport node) {
        muestraListaOtros(node,
                ContextUtil.getDAO().getTmensajeDestinoPersistence(ActionUtil.getDBUser()).findOtros(genericSession.getUserType()));
    }

    private static void listaFacultades(GenericSession genericSession, MensajeNodeSupport node) {
        node.setTerminal(true);

        List<Unidad> facultades = ContextUtil.getDAO()
                .getUnidadPersistence(ActionUtil.getDBUser())
                .findFacultad(genericSession.getRut(), genericSession.getUserType());

        facultades.forEach(fac -> addNode(node, fac.getUniCod().toString(), fac.getUniNom()));
    }

    /**
     * Method description
     *
     * @param node
     */
    private static void listaDeptosFacultad(GenericSession genericSession, MensajeNodeSupport node) {
        node.setTerminal(true);

        //OJO
        /*for (Departamento departamento
                : getDAO().getDepartamentoPersistence(ActionUtil.getDBUser()).find(genericSession.getUserType(), genericSession.getRut())) {
            addNode(node, departamento.getDeptCod().toString(), departamento.getDeptNom());
        }*/
    }

    /**
     * Method description
     *
     * @param node
     * @throws Exception
     */
    private static void listaDirDeptosFacultad(GenericSession genericSession, MensajeNodeSupport node) {
        node.setTerminal(true);

        getDirectoresDepto(genericSession.getFacultad())
                .forEach(autoridad -> addNode(
                node,
                autoridad.getFuncionario().getTraRut().toString(),
                autoridad.getFuncionario().getTraNombreSimple() + " " + autoridad.getLabelFull()
        ));
    }

    private static void listaCarrerasProgramas(String userType, Integer rut, MensajeNodeSupport node, String flag) {
        node.setTerminal(true);

        ContextUtil.getDAO()
                .getMencionPersistence(ActionUtil.getDBUser())
                .findCarrerasProgramas(userType, rut, flag)
                .stream()
                .sorted(Comparator.comparing(Mencion::getNombreCarreraFull))
                .forEach(mencion
                        -> addNode(
                        node,
                        mencion.getId().getMenCodCar() + "_" + mencion.getId().getMenCodMen(),
                        mencion.getNombreCarreraFull() + " (" + mencion.getId().getMenCodCar() + ')'
                )
                );
    }

    private static void listaDeptos(String userType, Integer rut, MensajeNodeSupport node) {
        node.setTerminal(true);

        ContextUtil.getDAO()
                .getUnidadPersistence(ActionUtil.getDBUser())
                .findDeptos(rut, userType)
                .forEach(depto
                        -> addNode(
                        node,
                        depto.getUniCod().toString(),
                        depto.getUniNom()
                )
                );
    }

    /**
     * Method description
     *
     * @param node
     * @throws Exception
     */
    private static void listaDirProgramasFacultad(GenericSession genericSession, MensajeNodeSupport node) {
        //OJO
    }

    /**
     * Method description
     *
     * @param node
     * @throws Exception
     */
    private static void listaJefesCarreraFacultad(GenericSession genericSession, MensajeNodeSupport node) {
        //OJO
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     */
    private static void listaMiSecretariaDocente(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list = Optional.ofNullable(
                "AL".equals(tipoUsuario) ? getSecretariaDocenteAlumno(rut)
                : "PR".equals(tipoUsuario) ? getSecretariaDocenteProfesor(rut) : null
        ).orElseGet(Collections::emptyList);

        addLaborRealizada(list, node);
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @throws Exception
     */
    private static void listaMiDirDepto(String tipoUsuario, Integer rut, MensajeNodeSupport node) {

        List<LaborRealizada> list = Optional.ofNullable(
                "AL".equals(tipoUsuario) ? getDirectorDepartamentoAlumno(rut)
                : "PR".equals(tipoUsuario) ? getDirectorDepartamentoProfesor(rut) : null
        ).orElseGet(Collections::emptyList);

        addLaborRealizada(list, node);
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     * @throws Exception
     */
    private static void listaMiSubDirDepto(GenericSession genericSession, MensajeNodeSupport node, String key) {
        //OJO
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @throws Exception
     */
    private static void listaMiDirPrograma(String tipoUsuario, Integer rut, MensajeNodeSupport node) {

        List<LaborRealizada> list
                = "AL".equals(tipoUsuario) ? getDirectorProgramaAlumno(rut)
                : "PR".equals(tipoUsuario) ? getDirectorProgramaProfesor(rut)
                : Collections.emptyList(); // Manejo seguro para valores inesperados

        addLaborRealizada(list, node);
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     * @throws Exception
     */
    private static void listaMiSubDirPrograma(GenericSession genericSession, MensajeNodeSupport node, String key) {
        //OJO
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @throws Exception
     */
    private static void listaMisJefesCarrera(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list
                = "AL".equals(tipoUsuario) ? getJefeCarreraAlumno(rut)
                : "PR".equals(tipoUsuario) ? getJefeCarreraProfesor(rut)
                : Collections.emptyList(); // Manejo seguro para valores inesperados

        addLaborRealizada(list, node);
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     */
    private static void listaCursosAlumno(GenericSession genericSession, MensajeNodeSupport node, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.getCursoList().stream()
                .forEach(curso -> {
                    // Usamos ternario en lugar de if-else
                    String codigo = node.isTerminal() ? curso.getCodigo("_") : "CURSOS_MCC::" + curso.getCodigo("_");
                    String nombre = node.isTerminal() ? curso.getNombreFull() : "Alumnos de:  " + curso.getNombreFull();

                    addNode(node, codigo, nombre);
                });
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     */
    private static void listaMisCursos(GenericSession genericSession, MensajeNodeSupport node, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String userType = genericSession.getUserType();

        // Dependiendo del tipo de usuario, obtenemos el Stream de cursos adecuado
        Stream<Curso> cursoStream = asList("AL", "AY", "PR").contains(userType)
                ? ws.getCursoList().stream()
                : ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser())
                        .findxUser(genericSession.getRut(), userType).stream();

        // Recorremos el Stream y procesamos los cursos
        cursoStream.forEach(curso -> {
            // Usamos ternario para decidir el formato de los datos a pasar a addNode
            String codigo = node.isTerminal() ? curso.getCodigo("_") : "CURSOS_MAL::" + curso.getCodigo("_");
            String nombre = node.isTerminal() ? curso.getNombreFull() : "Alumnos de:  " + curso.getNombreFull();

            addNode(node, codigo, nombre);
        });
    }

    /**
     * Method description
     *
     * @param node
     */
    private static void listaCursos(GenericSession genericSession, MensajeNodeSupport node) {
        // Obtenemos el Stream de los cursos asociados al usuario
        ContextUtil.getDAO().getCursoPersistence(ActionUtil.getDBUser())
                .findxUser(genericSession.getRut(), genericSession.getUserType())
                .stream() // Convertimos la lista a un Stream
                .forEach(curso -> {
                    // Añadimos el nodo para cada curso
                    addNode(node, curso.getId().getCodigo("_"), curso.getNombreFull() + " (" + curso.getCurProfesores() + ")");
                });
    }

    /**
     * Method description
     *
     * @param genericSession Sesion de trabajo.
     * @param node
     * @param key LLave para acceder a los datos de la sesion.
     */
    private static void listaMisProfesores(GenericSession genericSession, MensajeNodeSupport node, String key) {
        WorkSession ws = genericSession.getWorkSession(key);
        String userType = tipoUsuario(genericSession.getUserType());

        if ("AL".equals(userType)) {
            // Obtener la lista de cursos y procesar profesores
            List<Curso> cursoList = ws.getCursoList();
            cursoList.forEach(curso -> {
                CommonCursoUtil.getProfesores(curso).forEach(prof
                        -> addNode(node, valueOf(prof.getProfRut()),
                                String.format("%s (%s)", prof.getNombreMensaje(), curso.getNombreCorto()))
                );
            });
        } else {
            // Obtener profesores específicos para el usuario
            List<Profesor> profesores = ContextUtil.getDAO()
                    .getProfesorPersistence(ActionUtil.getDBUser())
                    .findxUser(genericSession.getRut(), genericSession.getUserType());

            profesores.forEach(prof -> addNode(node,
                    valueOf(prof.getProfRut()),
                    String.format("%s %s %s", prof.getProfPat(), prof.getProfMat(), prof.getProfNom()))
            );
        }
    }

    private static void listaMisAyudantes(GenericSession genericSession, MensajeNodeSupport node, String key) {
        WorkSession ws = genericSession.getWorkSession(key);

        ws.getCursoList().stream()
                .flatMap(curso
                        -> ContextUtil.getDAO()
                        .getAyudantePersistence(ActionUtil.getDBUser())
                        .find(curso)
                        .stream()
                        .map(cursoAyudante
                                -> new AbstractMap.SimpleEntry<>(
                                cursoAyudante.getAyudante().getAyuRut(),
                                cursoAyudante.getAyudante().getNombreMensaje()
                                + " (" + curso.getNombreCorto() + ')'
                        )
                        )
                )
                .forEach(entry
                        -> addNode(node, valueOf(entry.getKey()), entry.getValue())
                );
    }

    private static void listaAlumnosCurso(MensajeNodeSupport node, String strCurso) {
        new Curso(strCurso)
                .getNominaAlumnos()
                .stream()
                .map(aluCar
                        -> new AbstractMap.SimpleEntry<>(
                        aluCar.getId().getAcaRut(),
                        aluCar.getAlumno().getNombreMensaje()
                )
                )
                .forEach(entry
                        -> addNode(node, valueOf(entry.getKey()), entry.getValue())
                );
    }

    private static void listaDecanos(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list
                = "AL".equals(tipoUsuario) ? getDecanoAlumno(rut)
                : "PR".equals(tipoUsuario) ? getDecanoProfesor(rut)
                : Collections.emptyList(); // Manejo seguro para valores inesperados

        addLaborRealizada(list, node);
    }

    private static void listaViceDecDocencia(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list
                = "AL".equals(tipoUsuario) ? getViceDecanoDocAlumno(rut)
                : "PR".equals(tipoUsuario) ? getViceDecanoDocProfesor(rut)
                : Collections.emptyList(); // Manejo seguro para valores inesperados

        addLaborRealizada(list, node);
    }

    private static void listaViceDecInvestigacion(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list
                = "AL".equals(tipoUsuario) ? getViceDecanoInvAlumno(rut)
                : "PR".equals(tipoUsuario) ? getViceDecanoInvProfesor(rut)
                : Collections.emptyList(); // Manejo seguro para valores inesperados

        addLaborRealizada(list, node);
    }

    private static void listaRegistradorCurricular(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list = null;

        if ("AL".equals(tipoUsuario)) {
            list = getRegistradorAlumno(rut);
        }

        addLaborRealizada(list, node);
    }

    private static void listaAsistenteSocial(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list = null;

        if ("AL".equals(tipoUsuario)) {
            list = getAsitenteSocial(rut);
        }

        addLaborRealizada(list, node);
    }

    private static void listaJefeBiblioteca(String tipoUsuario, Integer rut, MensajeNodeSupport node) {
        List<LaborRealizada> list = null;

        if ("AL".equals(tipoUsuario)) {
            list = getJefeBiblioteca(rut);
        }

        addLaborRealizada(list, node);
    }

    private static void addLaborRealizada(List<LaborRealizada> list, MensajeNodeSupport node) {
        Optional.ofNullable(list)
                .ifPresent(laborList
                        -> laborList.stream()
                        .map(autoridad
                                -> new AbstractMap.SimpleEntry<>(
                                autoridad.getFuncionario().getTraRut().toString(),
                                autoridad.getFuncionario().getTraNombreSimple() + " " + autoridad.getLabelFull()
                        )
                        )
                        .forEach(entry
                                -> addNode(node, entry.getKey(), entry.getValue())
                        )
                );
    }

    /**
     * Method description
     *
     * @param node
     * @param id
     * @param value
     */
    private static void addNode(MensajeNodeSupport node, String id, String value) {
        MensajeNodeSupport newNode = new MensajeNodeSupport();

        newNode.setId(id);
        newNode.setValue(value);
        node.getNodeList().add(newNode);
    }

    /**
     * Method description
     *
     * @param node
     * @param otros
     */
    private static void muestraListaOtros(MensajeNodeSupport node, List<TmensajeDestino> otros) {
        if (otros != null) {
            otros.forEach(otro -> {
                MensajeNodeSupport newNode = new MensajeNodeSupport();

                newNode.setId(otro.getTmdCod());
                newNode.setValue(otro.getTmdDes());

                if (otro.getTmdLista() == 'N') {
                    newNode.setTerminal(true);
                }

                node.getNodeList().add(newNode);
            });
        }
    }

    /**
     * Method description
     *
     * @param node
     * @param codigo
     */
    private static void getBarra(MensajeNodeSupport node, String codigo) {
        List<MensajeNodeSupport> nodeListBar = ContextUtil.getDAO()
                .getTmensajeBarraDestinoPersistence(ActionUtil.getDBUser())
                .find(codigo)
                .stream()
                .map(tmensajeBarraDestino -> {
                    MensajeNodeSupport messageNodeSupport = new MensajeNodeSupport();
                    messageNodeSupport.setId(tmensajeBarraDestino.getTmensajeDestinoSegmento().getTmdCod());
                    messageNodeSupport.setValue(tmensajeBarraDestino.getTmensajeDestinoSegmento().getTmdDes());
                    return messageNodeSupport;
                })
                .collect(Collectors.toList()); // Uso de Collectors.toList() para versiones de Java anteriores a 16

        node.setNodeListBar(nodeListBar);
    }

    /**
     * Method Servicio
     *
     * @param genericSession Sesion de trabajo.
     * @param key LLave para acceder a los datos de la sesion.
     */
    private static void newRootMessage(GenericSession genericSession, String key) {
        List<TmensajeDestino> tmensajeDestinoList;

        MensajeNodeSupport rootNode = new MensajeNodeSupport();
        List<MensajeNodeSupport> mensajeNodeSupportList = new ArrayList<>();
        MensajeSupport mensajeSupport = new MensajeSupport(genericSession.getEmail());

        mensajeSupport.setRootNode(rootNode);
        WorkSession ws = genericSession.getWorkSession(key);
        TmensajeDestinoPersistence tmensajeDestinoPersistence
                = ContextUtil.getDAO().getTmensajeDestinoPersistence(ActionUtil.getDBUser());

        tmensajeDestinoList = (ws.getCursoList() != null && !ws.getCursoList().isEmpty()) || PRIVILEGED_USERS.get(genericSession.getUserType()) != null
                ? tmensajeDestinoPersistence.find(genericSession.getUserType())
                : tmensajeDestinoPersistence.findSinCursos(genericSession.getUserType());

        tmensajeDestinoList.stream()
                .filter(tmensajeDestino -> {
                    String cod = tmensajeDestino.getTmdCod();
                    return ("MDD".equals(cod) && ws.isCursoPregrado())
                            || ("MDP".equals(cod) && ws.isCursoPrograma())
                            || ("MSDD".equals(cod) && ws.isCursoPregrado())
                            || ("MSDP".equals(cod) && ws.isCursoPrograma())
                            || ("MJC".equals(cod) && ws.isCursoPregrado())
                            || (!"MDD".equals(cod) && !"MDP".equals(cod) && !"MJC".equals(cod) && !"MSDD".equals(cod) && !"MSDP".equals(cod));
                })
                .map(tmensajeDestino -> {
                    MensajeNodeSupport messageNodeSupport = new MensajeNodeSupport();
                    messageNodeSupport.setId(tmensajeDestino.getTmdCod());
                    messageNodeSupport.setValue(tmensajeDestino.getTmdDes());
                    messageNodeSupport.setTerminal(tmensajeDestino.getTmdLista() == 'N');
                    return messageNodeSupport;
                })
                .forEach(mensajeNodeSupportList::add);

        rootNode.setId("ROOT");
        rootNode.setValue("Destinatarios");
        rootNode.setState("SP");
        rootNode.setNodeList(mensajeNodeSupportList);
        mensajeSupport.setRootNode(rootNode);
        mensajeSupport.setCurrentNode(rootNode);
        ws.setMensajeSupport(mensajeSupport);

    }
}
