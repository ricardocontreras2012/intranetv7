/*
 * @(#)PrintResumenAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */
package action.reporteclase;

import java.util.List;
import service.reporteclase.PrintResumenService;
import infrastructure.support.CursoResumenSupport;
import infrastructure.support.action.ActionReportSupport;

/**
 * Procesa el action mapeado del request a la URL CommonReportePrintResumen
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class PrintResumenAction extends ActionReportSupport {

    private static final long serialVersionUID = 1L;
    private List<CursoResumenSupport> cursos;
    private String contentDisposition;
    private String format;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        cursos = new PrintResumenService().service(getGenericSession(), getReport(), format, getKey());

        return SUCCESS;
    }

    /**
     * Method description
     *
     * @return
     */
    public List<CursoResumenSupport> getCursos() {
        return cursos;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * Method description
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Method description
     *
     * @return
     */
    public String getContentDisposition() {
        return contentDisposition;
    }

    /**
     * Method description
     *
     * @param contentDisposition
     */
    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }
}
