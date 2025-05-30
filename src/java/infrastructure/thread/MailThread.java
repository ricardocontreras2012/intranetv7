/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure.thread;

import domain.model.MensajeAttach;
import java.util.List;
import infrastructure.util.HibernateUtil;
import infrastructure.util.MailUtil;

/**
 *
 * @author Ricardo
 */
public class MailThread extends Thread {

    private final String dir;
    private final String subject;
    private final String body;
    private final List<MensajeAttach> attachList;
    private final String attach;
    private final String imagen;
    private final String tipo;

    public MailThread(String dir, String subject, String body, List<MensajeAttach> attachList, String attach, String imagen, String tipo) {
        this.dir = dir;
        this.subject = subject;
        this.body = body;
        this.attachList = attachList;
        this.attach = attach;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    @Override
    public void run() {                
        new MailUtil().sendMensajeEmail(
                dir,subject, body, attachList, attach, imagen, "TM_STD", tipo, 1);
        HibernateUtil.closeSession();
    }
}
