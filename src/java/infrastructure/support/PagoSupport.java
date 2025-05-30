/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.support;

/**
 *
 * @author Administrador
 */
public class PagoSupport {

    private String transaction_number;
    private String url_payment;

    public PagoSupport() {
    }

    public String getTransaction_number() {
        return transaction_number;
    }

    public void setTransaction_number(String transaction_number) {
        this.transaction_number = transaction_number;
    }

    public String getUrl_payment() {
        return url_payment;
    }

    public void setUrl_payment(String url_payment) {
        this.url_payment = url_payment;
    }
}
