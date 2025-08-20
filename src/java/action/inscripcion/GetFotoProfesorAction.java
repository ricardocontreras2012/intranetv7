/*
 * @(#)GetFotoProfesorAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.inscripcion;


import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.InputStream;
import service.inscripcion.GetFotoProfesorService;
import infrastructure.support.action.common.ActionCommonSupport;
import infrastructure.util.ActionInputStreamUtil;

/**
 * Procesa el action mapeado del request a la URL
 * CommonInscripcionGetFotoProfesor
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class GetFotoProfesorAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;
    private ActionInputStreamUtil ais;
    private Integer pos;
    private String source;

    /**
     * Method description
     *
     * @return Action status.
     */
   @Override
    public String action() {
        ais = new GetFotoProfesorService().service(getGenericSession(), pos, source, getKey());
        return SUCCESS;
    }
    
    
    public String getContentType() {
        return ais.getContentType();
    }

    public String getName() {
        return ais.getName();
    }

    public InputStream getInputStream() {
        return ais.getInputStream();
    }    

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public void setSource(String source) {
        this.source = source;
    }    
}
