
package Teste;

import br.com.laboratorio.dao.ClienteDao;
import br.com.laboratorio.modelo.Cliente;
import br.com.laboratorio.util.HibernateUtil;
import java.sql.Connection;
import java.util.List;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class ClienteTeste {
    @Test
    @Ignore
    public void imprimir(){
        String src = "C:/Users/Tardeli/OneDrive/ProjetoWeb_Inicio/CasadasMarmitasMavem/src/main/webapp/relatorio/cliente.jasper";
                      
        Connection conexao = HibernateUtil.getConnection();

        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(src, null, conexao);
            //JasperViewer viewer = new JasperViewer(jasperPrint, true);
            JasperPrintManager.printReport(jasperPrint, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    //@Ignore
    public void listar() {
        ClienteDao cliente = new ClienteDao();
        List<Cliente> objetos = cliente.listarObjetos();
        System.out.println("-------------------------------------");
        for (Cliente objeto : objetos) {
            System.out.println(objeto.toString());
            System.out.println("-------------------------------------");
        }
        
    }
    
    @Test
    @Ignore
    public void pesquisarCpf() {
        ClienteDao c = new ClienteDao();
        Boolean b = c.pesquisarCpf("111.111.111-12");
        if(b==null){
            System.out.println("Cpf não escontrado");
        }else{
            System.out.println("Cpf já está cadastrado");
        }
    }
    
    @Test
    @Ignore
    public void cadastrar(){
        Cliente objeto = new Cliente();
        ClienteDao clienteDao = new ClienteDao();
        objeto.setNome("Tardeli da Rocha");
        objeto.setCpf("046.323.323-43");
        objeto.setEmail("tardeli@gmail.com");
        objeto.setTelefone("49-1233-1212");
        objeto.setLogradouro("Rua a");
        objeto.setBairro("Vila Real");
        objeto.setPontoReferencia("123e");
        clienteDao.salvarOuAtualizarObjeto(objeto);    
   }
    

}
