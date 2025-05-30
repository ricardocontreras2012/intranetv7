/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action.alumno;

import static service.alumno.AlumnoCertificacionGetConfirmacionPagoService.service;
import infrastructure.support.action.common.ActionCommonSupport;

/**
 *
 * @author Administrador
 */
public class AlumnoCertificacionGetConfirmacionPagoAction extends ActionCommonSupport {

    private static final long serialVersionUID = 1L;

    private String transaction;
    private Integer amount;
    private String payment_method;
    private String isApproved;
    private Integer correl;
    
    @Override
    public String action(){
        return service(this, transaction,amount, payment_method, isApproved);
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getCorrel() {
        return correl;
    }

    public void setCorrel(Integer correl) {
        this.correl = correl;
    }    
}
