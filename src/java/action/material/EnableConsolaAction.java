/*
 * @(#)EnableConsolaAction.java
 *
 * Copyright (c) 2025 FAE-USACH
 */


package action.material;



/**
 * Procesa el action mapeado del request a la URL CommonMaterialEnableConsole
 *
 * @author Ricardo Contreras S.
 * @version 7, 28/05/2012
 */
public final class EnableConsolaAction extends infrastructure.support.action.post.ActionPostCommonSupport {
    private static final long serialVersionUID = 1L;
    private String tipo;

    /**
     * Method description
     *
     * @return Action status.
     * @throws Exception Si recibe una exception del service.
     */
    @Override
    public String action() throws Exception {
        return SUCCESS;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
