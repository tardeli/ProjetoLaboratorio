package Teste;

import br.com.laboratorio.dao.PedidoDao;
import br.com.laboratorio.modelo.Email;
import br.com.laboratorio.modelo.EmailUtils;
import br.com.laboratorio.modelo.Mensagem;
import br.com.laboratorio.modelo.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.EmailException;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Da Rocha
 */
public class EmailTeste {

    @Test
    @Ignore
    public void enviarEmail() {
//        PedidoTeste pedidoTeste = new PedidoTeste();
//        String caminho = pedidoTeste.gerarXml();
//        System.out.println(caminho);
//        
//        Email.sendEmail(caminho, "tardeliltda@hotmail.com");
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto_DiskAgua\\xml\\20_Tardeli da Rocha.xml";

        Email.sendEmail(caminho, "tardeliltda@hotmail.com");

    }

    @Test
    @Ignore
    public void testarEmail() {
        Mensagem mensagem = new Mensagem();
        mensagem.setDestino("tardeliltda@hotmail.com");
        mensagem.setTitulo("Teste disk agua");
        mensagem.setMensagem("fsfsdfsfsfsf_->> teste");

        try {
            EmailUtils.conectaEmail();
            //EmailUtils.enviaEmail(mensagem);
        } catch (EmailException ex) {
            Logger.getLogger(EmailTeste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
