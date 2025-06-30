/*
 * @(#)GenericSession.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package session;

import domain.model.Administrativo;
import domain.model.Curso;
import domain.model.Dia;
import domain.model.EstadoCivil;
import domain.model.EstadoSolicitud;
import domain.model.Profesor;
import domain.model.Region;
import domain.model.UserLoginActionStack;
import java.util.Date;
import java.util.List;
import java.util.Map;
import infrastructure.support.MallaContainerSupport;
import static infrastructure.util.AppStaticsUtil.AUTORIDADES;
import static infrastructure.util.AppStaticsUtil.SECRETARIAS;
import infrastructure.util.ContextUtil;
import static infrastructure.util.DateUtil.getDateGetterSetter;
import infrastructure.util.ActionUtil;
import infrastructure.util.SystemParametersUtil;
import infrastructure.util.common.CommonUsersUtil;

/**
 * Class description
 *
 * @author Ricardo Contreras S.
 * @version 7, 24/05/2012
 */
public final class GenericSession {

    private String dv;
    private String email;
    private Date lastLogin;
    private MallaContainerSupport mallaContainer;
    private String materno;
    private String nombre;
    private String nombreMensaje;
    private String nombres;
    private String paterno;
    private ProfesorSession profesorSession;
    private final Integer rut;
    private Map<String, WorkSession> sessionMap;
    private final String userType;
    private String currentAction;
    private Administrativo administrativo;
    private Profesor profesor;
    private Integer facultad;
    private List<UserLoginActionStack> stackActionList;
    private UserLoginActionStack stackAction;
    private String password;

    /**
     *
     * @param userType
     * @param rut
     * @param password
     * @param flag
     */
    public GenericSession(String userType, int rut, String password, Integer flag) {
        this.userType = userType;
        this.rut = rut;
        this.password = password;

        if (SystemParametersUtil.INGRESO_CLAVE_UNICA.equals(flag)) {
            userType += "_CU";
        }
        setStackActionList(CommonUsersUtil.getActionStack(userType));

    }

    /**
     * Method description
     *
     * @return
     */
    public String getDv() {
        return dv;
    }

    /**
     * Method description
     *
     * @param dv
     */
    public void setDv(String dv) {
        this.dv = dv;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * Method description
     *
     * @param materno
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Method description
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombreMensaje() {
        return nombreMensaje;
    }

    /**
     * Method description
     *
     * @param nombreMensaje
     */
    public void setNombreMensaje(String nombreMensaje) {
        this.nombreMensaje = nombreMensaje;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Method description
     *
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * Method description
     *
     * @param paterno
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * Method description
     *
     * @return
     */
    public ProfesorSession getProfesorSession() {
        return profesorSession;
    }

    /**
     * Method description
     *
     * @param profesorSession
     */
    public void setProfesorSession(ProfesorSession profesorSession) {
        this.profesorSession = profesorSession;
    }

    /**
     * Method description
     *
     * @return
     */
    public Integer getRut() {
        return rut;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getUserType() {
        return userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method description
     *
     * @return
     */
    public MallaContainerSupport getMallaContainer() {
        return mallaContainer;
    }

    /**
     * Method description
     *
     * @param mallaContainer
     */
    public void setMallaContainer(MallaContainerSupport mallaContainer) {
        this.mallaContainer = mallaContainer;
    }

    /**
     * Method description
     *
     * @return
     */
    public Map<String, WorkSession> getSessionMap() {
        return sessionMap;
    }

    /**
     * Method description
     *
     * @param sessionMap
     */
    public void setSessionMap(Map<String, WorkSession> sessionMap) {
        this.sessionMap = sessionMap;
    }

    /**
     * Method description
     *
     * @param key
     * @return
     */
    public Curso getCurso(String key) {
        return this.sessionMap.get(key).getCurso();
    }

    /**
     * Method description
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method description
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method description
     *
     * @return a defensive copy of the field. The caller may change the state of
     * the returned object in any way, without affecting the internals of this
     * class.
     */
    public Date getLastLogin() {
        return getDateGetterSetter(lastLogin);
    }

    /**
     * Method description
     *
     * @param lastLogin
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = getDateGetterSetter(lastLogin);
    }

    /*
     *
     *
     */
    /**
     * Method description
     *
     * @return
     */
    public boolean isAutoridad() {
        return AUTORIDADES.get(this.userType) != null;
    }

    /**
     * Method description
     *
     * @return
     */
    public boolean isSecretaria() {
        return SECRETARIAS.get(this.userType) != null;
    }

    /**
     * Method description
     *
     *
     * @param key
     *
     * @return
     */
    public WorkSession getWorkSession(String key) {
        return getSessionMap().get(key);
    }

    public WorkSession getParentWorkSession(String key) {
        return getSessionMap().get(this.getSessionMap().get(key).getKeyParent());
    }

    /**
     *
     * @return
     */
    public String getCurrentAction() {
        return currentAction;
    }

    /**
     *
     * @param currentAction
     */
    public void setCurrentAction(String currentAction) {
        this.currentAction = currentAction;
    }

    /**
     *
     * @return
     */
    public Administrativo getAdministrativo() {
        return administrativo;
    }

    /**
     *
     * @param administrativo
     */
    public void setAdministrativo(Administrativo administrativo) {
        this.administrativo = administrativo;
    }

    /**
     *
     * @return
     */
    public Profesor getProfesor() {
        return profesor;
    }

    /**
     *
     * @param profesor
     */
    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    /**
     *
     * @return
     */
    public Integer getFacultad() {
        return facultad;
    }

    /**
     *
     * @param facultad
     */
    public void setFacultad(Integer facultad) {
        this.facultad = facultad;
    }

    public List<UserLoginActionStack> getStackActionList() {
        return stackActionList;
    }

    public void setStackActionList(List<UserLoginActionStack> stackActionList) {
        this.stackActionList = stackActionList;
    }

    public UserLoginActionStack getStackAction() {
        return stackAction;
    }

    public void setStackAction(UserLoginActionStack stackAction) {
        this.stackAction = stackAction;
    }

    public List<EstadoCivil> getListaEstadoCivil() {
        return ContextUtil.getEstadoCivilList();
    }

    public List<Region> getListaRegion() {
        return ContextUtil.getRegionList();
    }

    public List<EstadoSolicitud> getListaEstadoSolicitud() {
        return ContextUtil.getEstadoSolicitudList();
    }

    public List<Dia> getDiaList() {
        return ContextUtil.getDiaList();
    }

    public String getMessagePlus() {
        return ContextUtil.getDAO().getScalarPersistence(ActionUtil.getDBUser()).getMsgPlusFlag(userType);
    }

    public WorkSession getProfWorkSession(String keyNormal) {
        return sessionMap.keySet().stream()
                .filter(key -> !key.equals(keyNormal))
                .findFirst()
                .map(getSessionMap()::get)
                .orElse(null);
    }

}
