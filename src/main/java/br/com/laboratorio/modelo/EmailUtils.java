/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.laboratorio.modelo;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Da Rocha
 */
public class EmailUtils {
    
    private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "tardeliltda@gmail.com";
    private static final String PASSWORD = "rocha48267_11r4954388";
    private static final String EMAILORIGEM = "tardeliltda@gmail.com";

    public static Email conectaEmail() throws EmailException {
        //Email email = new SimpleEmail();//email sem arquivo
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(HOSTNAME);
        //email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(Mensagem mensagem, String arquivo) throws EmailException {
        //Email email = new SimpleEmail();//email sem arquivo
        MultiPartEmail email = new MultiPartEmail();
        email = (MultiPartEmail) conectaEmail();
        email.setSubject(mensagem.getTitulo());
        //Anexa arquivo 
        email.attach(anexo(arquivo)); // adiciona o anexo à mensagem
        email.setMsg(mensagem.getMensagem());
        email.addTo(mensagem.getDestino());
        email.setDebug(true);
        String resposta = email.send();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "E-mail enviado com sucesso para: " + mensagem.getDestino(), "Informação"));
    }

    public static EmailAttachment anexo(String caminho) {
        // cria o anexo.
        EmailAttachment anexo = new EmailAttachment();
        anexo.setPath(caminho); //caminho da imagem
        anexo.setDisposition(EmailAttachment.ATTACHMENT);
        anexo.setDescription("Arquivo");
        //anexo.setName("arquivo.xml");
        
        return anexo;
    }

}
