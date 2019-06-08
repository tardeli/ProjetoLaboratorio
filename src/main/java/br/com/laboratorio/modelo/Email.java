package br.com.laboratorio.modelo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Da Rocha
 */
public class Email {

    public static void sendEmail(String caminho, String destino) {

        try {
            // cria o anexo.
            EmailAttachment anexo = new EmailAttachment();
            anexo.setPath(caminho); //caminho da imagem
            anexo.setDisposition(EmailAttachment.ATTACHMENT);
            anexo.setDescription("Anexo arquivo xml pedido");
            anexo.setName("arquivo.xml");
            
            //SimpleEmail email = new SimpleEmail();//sem anexo
            MultiPartEmail email = new MultiPartEmail();
            email.setDebug(true);
            //Utilize o hostname do seu provedor de email
            System.out.println("alterando hostname...");
            email.setHostName("smtp.gmail.com");
            //Quando a porta utilizada não é a padrão (gmail = 465)
            //email.setSmtpPort(465);
            //Configure o seu email do qual enviará
            email.setFrom("tardeliltda@gmail.com");
            //Adicione os destinatários
            email.addTo(destino);
            //Adicione um assunto
            email.setSubject("Test message - programa disk agua");   
            //Adicione a mensagem do email
            email.setMsg("acabou de receber, email sistema disk agua");
            //Anexa arquivo
            email.attach(anexo); // adiciona o anexo à mensagem
            //Para autenticar no servidor é necessário chamar os dois métodos abaixo
            System.out.println("autenticando...");
            email.setSSL(true);
            email.setAuthentication("tardeliltda@gmail.com", "rocha48267_11r4954388");
            System.out.println("enviando...");
            email.send();
            System.out.println("Email enviado!");
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
