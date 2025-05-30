/*
 * @(#)MensajeSenderSupport.java
 *
 * Copyright (c) 2016 FAE-USACH
 */
package infrastructure.support;

import infrastructure.util.ContextUtil;
import infrastructure.util.ActionUtil;
import infrastructure.util.InfrastructureExceptionUtil;
import infrastructure.util.HibernateUtil;
import domain.model.MensajeAttach;
import domain.model.Mensaje;
import domain.model.Curso;
import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;
import static java.lang.System.out;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;
import static infrastructure.support.LaborSupport.getDirectoresDepto;
import static infrastructure.support.LaborSupport.getDirectoresPrograma;
import static infrastructure.support.LaborSupport.getJefesCarrera;
import static infrastructure.util.LogUtil.logInfo;
import static infrastructure.util.SystemParametersUtil.PATH_TEMP_FILES;
import domain.model.AlumnoActivoView;
import domain.model.AyudanteActivoView;
import domain.model.ProfesorActivoView;
import java.util.Optional;

/**
 *
 * @author Ricardo Contreras S.
 */
public class MensajeSenderSupport {

    private static final String USER_DB = "CM";
    private final Set<Integer> rutDestinatarios = new HashSet<>(0);
    private Integer correl;
    private final MensajeSupport mensajeSupport;

    /**
     *
     * @param mensajeSupport
     */
    public MensajeSenderSupport(MensajeSupport mensajeSupport) {
        this.mensajeSupport = mensajeSupport;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<String> send() {
        correl = getCorrel();
        mensajeSupport.getMensaje().setMsgEstado("S");
        mensajeSupport.getMensaje().setMsgCorrel(correl);

        return saveMensaje();
    }

    /**
     * Method description
     *
     * @return
     */
    private static int getCorrel() {
        return ContextUtil.getDAO().getScalarPersistence(USER_DB).getSecuenciaMensaje();
    }

    /**
     * Guarda un mensaje y sus asociados (adjuntos y destinatarios) en la base
     * de datos.
     *
     * Este método procesa el mensaje, guarda los datos asociados (como los
     * adjuntos y los destinatarios), y retorna una lista de los destinatarios a
     * los que se ha enviado el mensaje. Si ocurre un error, se maneja
     * adecuadamente mediante excepciones.
     *
     * @return Una lista de destinatarios a los que se ha enviado el mensaje.
     */
    private List<String> saveMensaje() {
        Mensaje mensaje = mensajeSupport.getMensaje();
        List<String> destList = null;

        try {
            // Procesar la estructura del mensaje
            processList(this.mensajeSupport.getRootNode());

            // Guardar el mensaje en la base de datos
            ContextUtil.getDAO().getMensajePersistence(USER_DB).saveMsg(mensaje);

            // Guardar los adjuntos si existen
            saveAdjuntos(mensaje);

            // Guardar los destinatarios
            saveDestinatarios();

            // Obtener la lista de destinatarios que recibieron el mensaje
            destList = getDestinatariosEnviados();

        } catch (InfrastructureExceptionUtil e) {
            // Manejo de excepciones, si ocurre un error en el proceso
            out.println("##### Error en saveMensaje(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Asegurarse de que la sesión de Hibernate se cierre correctamente
            HibernateUtil.closeSession();
        }

        return destList;
    }

    /**
     * Guarda los adjuntos del mensaje en la base de datos si existen.
     *
     * @param mensaje El mensaje que contiene los adjuntos.
     */
    private void saveAdjuntos(Mensaje mensaje) {
        if ("S".equals(mensaje.getMsgAttach())) {
            // Utilizando una expresión lambda para iterar sobre la lista de adjuntos
            IntStream.range(0, mensaje.getMensajeAttachList().size())
                    .forEach(i -> {
                        MensajeAttach mensajeAttach = mensaje.getMensajeAttachList().get(i);
                        ContextUtil.getDAO().getMensajeAttachPersistence(USER_DB)
                                .saveAttach(correl, i, mensajeAttach.getMenaAttachFile());
                    });
        }
    }

    /**
     * Guarda los destinatarios del mensaje en la base de datos usando lambda.
     */
    private void saveDestinatarios() {
        rutDestinatarios.forEach(rut
                -> ContextUtil.getDAO().getMensajeDestinatarioPersistence(USER_DB)
                        .saveDest(correl, rut)
        );
    }

    /**
     * Obtiene la lista de destinatarios a los que se ha enviado el mensaje.
     *
     * @return Una lista de destinatarios a los que se ha enviado el mensaje.
     */
    private List<String> getDestinatariosEnviados() {
        return ContextUtil.getDAO().getMensajeDestinatarioPersistence(USER_DB).findSent(correl);
    }

    /**
     * Method description
     *
     * @param rootNode
     */
    private void processList(MensajeNodeSupport rootNode) {
        while (rootNode != null) {
            if (rootNode.isTerminal()) {
                logInfo("Enviando mensaje a " + rootNode.getId());
                enviarMensaje(rootNode);
                logInfo("Mensaje enviado a " + rootNode.getId());

                break;
            } else if ((rootNode.getNodeList() != null) && !rootNode.getNodeList().isEmpty()) {
                MensajeNodeSupport messageNodeSupport = rootNode.getNodeList().remove(0);

                processList(messageNodeSupport);
            } else {
                break;
            }
        }
    }

    /**
     * Method description
     *
     * @param nodoDest
     */
    private void enviarMensaje(MensajeNodeSupport nodoDest) {
        try {
            if (nodoDest != null) {
                String userType = this.mensajeSupport.getMensaje().getMsgRolEnv();
                Integer rut = this.mensajeSupport.getMensaje().getMsgRutEnv();

                String destCod = nodoDest.getId();
                int posSufix = destCod.indexOf("::");

                if (posSufix >= 0) {
                    destCod = destCod.substring(0, posSufix);
                }

                String prefixLog = "Correo enviado a ";

                if (null != destCod) {
                    switch (destCod) {
                        case "MALF": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findFacultad(rut, userType)
                                    .forEach(fac -> sendMsgAlumnosFacultad(fac.getUniCod()));
                            break;
                        }
                        case "MAYF": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findFacultad(rut, userType)
                                    .forEach(fac -> sendMsgAyudantesFacultad(fac.getUniCod()));
                            break;
                        }
                        case "MPRF": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findFacultad(rut, userType)
                                    .forEach(fac -> sendMsgProfesoresFacultad(fac.getUniCod()));
                            break;
                        }
                        case "MALD": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findDeptos(rut, userType)
                                    .forEach(depto -> sendMsgAlumnosDepto(depto.getUniCod()));
                            break;
                        }
                        case "MAYD": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findDeptos(rut, userType)
                                    .forEach(depto -> sendMsgAyudantesDepto(depto.getUniCod()));
                            break;
                        }
                        case "MPRD": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findDeptos(rut, userType)
                                    .forEach(depto -> sendMsgProfesoresDepto(depto.getUniCod()));
                            break;
                        }
                        case "MALC": {
                            ContextUtil.getDAO()
                                    .getMencionPersistence(ActionUtil.getDBUser())
                                    .find(userType, rut)
                                    .forEach(mencion
                                            -> sendMsgAlumnosCarrera(
                                            mencion.getId().getMenCodCar(),
                                            mencion.getId().getMenCodMen()
                                    )
                                    );
                            break;
                        }
                        case "MAYC": {
                            ContextUtil.getDAO()
                                    .getMencionPersistence(ActionUtil.getDBUser())
                                    .find(userType, rut)
                                    .forEach(mencion
                                            -> sendMsgAyudantesCarrera(
                                            mencion.getId().getMenCodCar(),
                                            mencion.getId().getMenCodMen()
                                    )
                                    );
                            break;
                        }
                        case "MPRC": {
                            ContextUtil.getDAO()
                                    .getUnidadPersistence(ActionUtil.getDBUser())
                                    .findCarreras(rut, userType)
                                    .forEach(carrera -> sendMsgProfesoresCarrera(carrera.getUniCod()));
                            break;
                        }
                        // Facultad
                        case "FA": {
                            int nodeSizeFac = nodoDest.getNodeList().size();
                            for (int nc = 1; nc <= nodeSizeFac; nc++) {
                                MensajeNodeSupport nodeFac = nodoDest.getNodeList().get(nc - 1);
                                int facultad = Integer.parseInt(nodeFac.getId());
                                int nodeSize = nodoDest.getNodeListBar().size();
                                for (int i = 1; i <= nodeSize; i++) {
                                    MensajeNodeSupport ca = nodoDest.getNodeListBar().get(i - 1);
                                    if (null != ca.getId()) {
                                        switch (ca.getId()) {
                                            case "AL":
                                                sendMsgAlumnosFacultad(facultad);
                                                logInfo(prefixLog + "todos los alumnos");
                                                break;
                                            case "AY":
                                                sendMsgAyudantesFacultad(facultad);
                                                logInfo(prefixLog + "todos los ayudantes");
                                                break;
                                            case "PR":
                                                sendMsgProfesoresFacultad(facultad);
                                                logInfo(prefixLog + "todos los profesores");
                                                break;
                                            case "JC":
                                                sendMsgJefesCarreraFacultad();
                                                logInfo(prefixLog + "todos los jefe de carrera");
                                                break;
                                            case "DD":
                                                sendMsgDirDeptoFacultad();
                                                logInfo(prefixLog + "todos los directores de departamento");
                                                break;
                                            case "DP":
                                                sendMsgDirProgramaFacultad();
                                                logInfo(prefixLog + "todos los directores de programa");
                                                break;
                                            case "VDD":
                                                sendPersonList(nodoDest);
                                                logInfo(prefixLog + "vicedecano de Docencia");
                                                break;
                                            case "VDI":
                                                sendPersonList(nodoDest);
                                                logInfo(prefixLog + "vicedecano de Investigación");
                                                break;
                                            case "DE":
                                                sendPersonList(nodoDest);
                                                logInfo(prefixLog + "decano");
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        // Departamentos Academicos
                        case "DA":
                        case "MDA": {
                            int nodeBarSize = nodoDest.getNodeListBar().size();
                            for (int i = 1; i <= nodeBarSize; i++) {
                                MensajeNodeSupport ca = nodoDest.getNodeListBar().get(i - 1);
                                int nodeSize = nodoDest.getNodeList().size();

                                for (int nc = 1; nc <= nodeSize; nc++) {
                                    MensajeNodeSupport node = nodoDest.getNodeList().get(nc - 1);
                                    int depto = parseInt(node.getId());

                                    if (null != ca.getId()) {
                                        switch (ca.getId()) {
                                            case "AL":
                                                sendMsgAlumnosDepto(depto);
                                                logInfo(prefixLog + "todos los alumnos del depto " + depto);
                                                break;
                                            case "AY":
                                                sendMsgAyudantesDepto(depto);
                                                logInfo(prefixLog + "todos los ayudantes del depto " + depto);
                                                break;
                                            case "PR":
                                                sendMsgProfesoresDepto(depto);
                                                logInfo(prefixLog + "todos los profesores del depto " + depto);
                                                break;
                                            case "DD":
                                                sendMsgDirDepto(depto);
                                                logInfo(prefixLog + "director del depto " + depto);
                                                break;
                                            case "JC":
                                                // sendMsgJefeCarreraDepartamento( depto);
                                                logInfo(prefixLog + "jefe de carrera del depto " + depto);
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        // Carreras
                        case "CA":
                        case "PG":
                        case "MPG":
                        case "MCA": {
                            int nodeBarSize = nodoDest.getNodeListBar().size();
                            for (int i = 1; i <= nodeBarSize; i++) {
                                MensajeNodeSupport ca = nodoDest.getNodeListBar().get(i - 1);
                                int nodeSize = nodoDest.getNodeList().size();

                                for (int nc = 1; nc <= nodeSize; nc++) {
                                    MensajeNodeSupport node = nodoDest.getNodeList().get(nc - 1);
                                    String valor = node.getId();
                                    int pos = valor.lastIndexOf('_');
                                    int carrera = parseInt(valor.substring(0, pos));
                                    int mencion = parseInt(valor.substring(pos + 1));

                                    if (null != ca.getId()) {
                                        switch (ca.getId()) {
                                            case "AL":
                                                sendMsgAlumnosCarrera(carrera, mencion);
                                                logInfo(prefixLog + "todos los alumnos de la carrera " + carrera + ' '
                                                        + mencion);
                                                break;
                                            case "AY":
                                                sendMsgAyudantesCarrera(carrera, mencion);
                                                logInfo(prefixLog + "todos los ayudantes de la carrera " + carrera + ' '
                                                        + mencion);
                                                break;
                                            case "PR":
                                                sendMsgProfesoresCarrera(carrera, mencion);
                                                logInfo(prefixLog + "todos los profesores de la carrera " + carrera
                                                        + ' ' + mencion);
                                                break;
                                            case "DD":
                                                // sendMsgDirDeptoCarrera( carrera, mencion);
                                                logInfo(prefixLog + "director de departamento de la carrera " + carrera
                                                        + ' ' + mencion);
                                                break;
                                            case "DP":
                                                sendMsgDirPrograma(carrera, mencion);
                                                logInfo(prefixLog + "director del programa " + carrera + ' ' + mencion);
                                                break;
                                            case "JC":
                                                sendMsgJefeCarreraCarrera(carrera, mencion);
                                                logInfo(prefixLog + "jefe de la carrera " + carrera + ' '
                                                        + mencion);
                                                break;
                                            case "N1":
                                            case "N2":
                                            case "N3":
                                            case "N4":
                                            case "N5":
                                            case "N6":
                                            case "N7":
                                            case "N8":
                                            case "N9":
                                            case "N10":
                                            case "N11":
                                            case "N12":
                                                sendMsgAlumnosCarreraNivel(carrera, mencion, valueOf(ca.getId().substring(1)));
                                                logInfo(prefixLog + ca.getId() + carrera + ' '
                                                        + mencion);
                                                break;

                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        // Curso Generico o Mis Cursos.
                        case "CU":
                        case "MCU": {
                            // for (int i = 1; i <= nodoDest.getNodeListBar().size(); i++) {
                            // MensajeNodeSupport ca = nodoDest.getNodeListBar().get(i - 1);
                            int nodeSize = nodoDest.getNodeList().size();
                            for (int nc = 1; nc <= nodeSize; nc++) {
                                MensajeNodeSupport node = nodoDest.getNodeList().get(nc - 1);
                                Curso curso = new Curso(node.getId());

                                // if ("AL".equals(ca.getId())) {
                                sendMsgAlumnosCurso(curso);
                                logInfo(prefixLog + "al curso " + node.getId());

                                if ("MCU".equals(destCod)) {
                                    sendMsgProfesoresCurso(curso);
                                    sendMsgAyudantesCurso(curso);
                                }
                            }

                            break;
                        }
                        // DECANO
                        case "DE":
                        // VICEDECANO DOCENCIA
                        case "VDD":
                        // VICEDECANO INVESTIGACION
                        case "VDI":
                        // REGISTRADOR CURRICULAR
                        case "MRC":
                        // ASISTENTE SOCIAL
                        case "MAS":
                        // JEFE DE BIBLIOTECA
                        case "MJB":
                        // DIRECTORES DEPTO
                        case "DD":
                        // ALUMNOS MISCURSOS
                        case "CURSOS_MAL":
                        case "CURSOS_MCC":
                        // MIS PROFESORES
                        case "MPR":
                        // MIS AYUDANTES
                        case "MAY":
                        case "MDD":
                        case "MDP":
                        case "MJC":
                        // MI SECRETARIA DOCENTE
                        case "MSD":
                        // ALUMNOS
                        case "AL":
                        // PROFESORES
                        case "PR":
                        // AYUDANTES
                        case "AY":
                        // REPLY MESSAGE
                        case "RE":
                        /// SITUACIONES
                        case "REI":// REINCORPORACION
                        case "RC":// RENUNCUIA CARRERA
                        case "RT":// RETIRO TEMPORAL
                        case "PPL":// PRORROGA
                        case "MAT":// MATRICULAS
                        /// CERTIFICADOS
                        case "C1":// CERTIFICADO ALUMNO REGULAR
                        case "C2":// CERTIFICADO NO IMPED
                        case "C3":// CERTIFICADO ALUMNO EGRESADO
                        case "C4":// CERTIFICADO DE TITULO EN TRAMITE
                        case "C5":// CERTIFICADO DE GRADO EN TRAMITE
                        case "C6":// CERTIFICADO RANKING DE EGRESO
                        case "C7":// CERTIFICADO RANKING ALUMNO REG
                        case "C8":// CERTIFICADO POST EN TRAMITE
                        case "C9":// CERTIFICADO DIP EN TRAMITE 
                        case "I3":// CERTIFICADO DE CALIFICACIONES
                            sendPersonList(nodoDest);
                            break;

                        // Lista de RUN
                        case "LI":
                            sendRUNFile(nodoDest);
                            break;

                        default:
                            break;
                    }
                }
            }
        } catch (Exception e) {
            out.println("enviarMensaje->Exception e");
            e.printStackTrace();
        }
    }

    /**
     * Method description
     *
     * @param nodoDest
     */
    private void sendPersonList(MensajeNodeSupport nodoDest) {
        Optional.ofNullable(nodoDest)
                .map(MensajeNodeSupport::getNodeList)
                .ifPresent(nodeList
                        -> nodeList.stream()
                        .map(node -> valueOf(node.getId()))
                        .forEach(this::addDestinatario)
                );
    }

    private void sendRUNFile(MensajeNodeSupport nodoDest) {
        try {
            File file = new File(PATH_TEMP_FILES + nodoDest.getValue());

            Scanner inputStream = new Scanner(file, "UTF-8");
            // hashNext() loops line-by-line
            while (inputStream.hasNext()) {
                //read single line, put in string
                String rut = inputStream.next();
                addDestinatario(valueOf(rut));
            }
            inputStream.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    /**
     * Method description
     *
     * @throws Exception
     */
    private void sendMsgJefesCarreraFacultad() {
        getJefesCarrera(mensajeSupport.getFacultad())
                .stream()
                .map(autoridad -> autoridad.getFuncionario().getTraRut())
                .forEach(this::addDestinatario);
    }

    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     * @throws Exception
     */
    private void sendMsgJefeCarreraCarrera(int carrera, int mencion) {

        // OJO
        //addDestinatario(LaborSupport.getJefeCarrera(carrera, mencion).getAcadRut());
    }

    /**
     * Method description
     *
     * @param depto
     */
    // private void sendMsgJefeCarreraDepartamento(int depto) {
    //
    // }
    /**
     * Method description
     *
     * @param carrera
     * @param mencion
     */
    // private void sendMsgDirDeptoCarrera(int carrera, int mencion) {
    // sendMsgLista( new daoMenDirectorDeptoCarreraDAO().getDirectorDeptoCarrera(carrera, mencion));
    // }
    /**
     * Method description
     *
     * @param depto
     * @throws Exception
     */
    /////////////////////////
    /////////////////////////
    private void sendMsgDirDepto(int depto) {
        getDirectoresDepto(depto)
                .stream()
                .map(autoridad -> autoridad.getFuncionario().getTraRut())
                .forEach(this::addDestinatario);
    }

    private void sendMsgDirDeptoFacultad() {
        getDirectoresDepto(mensajeSupport.getFacultad())
                .stream()
                .map(autoridad -> autoridad.getFuncionario().getTraRut())
                .forEach(this::addDestinatario);
    }

    private void sendMsgDirProgramaFacultad() {
        getDirectoresPrograma(mensajeSupport.getFacultad())
                .stream()
                .map(autoridad -> autoridad.getFuncionario().getTraRut())
                .forEach(this::addDestinatario);
    }

    private void sendMsgDirPrograma(int programa, int mencion) {
        // Método sin implementación en el original
    }

    private void sendMsgAlumnosFacultad(int facultad) {
        ContextUtil.getDAO()
                .getAlumnoPersistence(USER_DB)
                .findAlumnosActivosFacultad(facultad)
                .stream()
                .map(AlumnoActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAlumnosDepto(int depto) {
        ContextUtil.getDAO()
                .getAlumnoPersistence(USER_DB)
                .findAlumnosActivosDepartamento(depto)
                .stream()
                .map(AlumnoActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAlumnosCarrera(int carrera, int mencion) {
        ContextUtil.getDAO()
                .getAlumnoPersistence(USER_DB)
                .findAlumnosActivosCarrera(carrera, mencion)
                .stream()
                .map(AlumnoActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAlumnosCarreraNivel(int carrera, int mencion, int nivel) {
        ContextUtil.getDAO()
                .getAlumnoPersistence(USER_DB)
                .findAlumnosActivosNivelCarrera(carrera, mencion, nivel)
                .stream()
                .map(AlumnoActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAlumnosCurso(Curso curso) {
        curso.getRutAlumnos()
                .stream()
                .forEach(this::addDestinatario);
    }

    private void sendMsgAyudantesFacultad(int facultad) {
        ContextUtil.getDAO()
                .getAyudantePersistence(USER_DB)
                .findAyudantesActivosFacultad(facultad)
                .stream()
                .map(AyudanteActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAyudantesDepto(int depto) {
        ContextUtil.getDAO()
                .getAyudantePersistence(USER_DB)
                .findAyudantesActivosDepartamento(depto)
                .stream()
                .map(AyudanteActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAyudantesCarrera(int carrera, int mencion) {
        ContextUtil.getDAO()
                .getAyudantePersistence(USER_DB)
                .findAyudantesActivosCarrera(carrera, mencion)
                .stream()
                .map(AyudanteActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgAyudantesCurso(Curso curso) throws Exception {
        ContextUtil.getDAO()
                .getAyudantePersistence(USER_DB)
                .find(curso)
                .stream()
                .map(cursoAyudante -> cursoAyudante.getId().getCayuRut())
                .forEach(this::addDestinatario);
    }

    private void sendMsgProfesoresFacultad(int facultad) {
        ContextUtil.getDAO()
                .getProfesorPersistence(USER_DB)
                .findProfesoresActivosFacultad(facultad)
                .stream()
                .map(ProfesorActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgProfesoresDepto(int depto) {
        ContextUtil.getDAO()
                .getProfesorPersistence(USER_DB)
                .findProfesoresActivosDepartamento(depto)
                .stream()
                .map(ProfesorActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgProfesoresCarrera(int carrera, int mencion) {
        ContextUtil.getDAO()
                .getProfesorPersistence(USER_DB)
                .findProfesoresActivosCarrera(carrera, mencion)
                .stream()
                .map(ProfesorActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgProfesoresCarrera(Integer unidad) {
        ContextUtil.getDAO()
                .getProfesorPersistence(USER_DB)
                .findProfesoresActivosCarrera(unidad)
                .stream()
                .map(ProfesorActivoView::getRut)
                .forEach(this::addDestinatario);
    }

    private void sendMsgProfesoresCurso(Curso curso) throws Exception {
        ContextUtil.getDAO()
                .getProfesorPersistence(USER_DB)
                .findProfesor(curso)
                .stream()
                .map(cursoProfesor -> cursoProfesor.getId().getCproRut())
                .forEach(this::addDestinatario);
    }

    /**
     * Method description
     *
     *
     * @param rut
     */
    private void addDestinatario(Integer rut) {
        if (rut != null && rut > 0) {
            rutDestinatarios.add(rut);
        }
    }
}
