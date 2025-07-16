/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Map;

/**
 *
 * @author Ricardo
 */
public class Manager {
    
    public static SecretariaSession getSecretariaSession(Map<String, Object> sesion)
    {
        return (SecretariaSession)sesion.get("secretariaSession");
    }
    
    public static AlumnoSession getAlumnoSession(Map<String, Object> sesion)
    {
        return (AlumnoSession)sesion.get("alumnoSession");
    } 
    
    public static RegistradorSession getRegistradorSession(Map<String, Object> sesion)
    {
        return (RegistradorSession)sesion.get("registradorSession");
    }
    
    public static ProyectoSession getProyectoSession(Map<String, Object> sesion)
    {
        return (ProyectoSession)sesion.get("proyectoSession");
    }   
    
    public static EgresadoSession getEgresadoSession(Map<String, Object> sesion)
    {
        return (EgresadoSession)sesion.get("egresadoSession");
    } 
    
    public static JefeCarreraSession getJefeCarreraSession(Map<String, Object> sesion)
    {
        return (JefeCarreraSession)sesion.get("jefeCarreraSession");
    }
    
    public static ProfesorSession getProfesorSession(Map<String, Object> sesion)
    {
        return (ProfesorSession)sesion.get("profesorSession");
    } 
}
