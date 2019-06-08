package Teste;

import br.com.laboratorio.dao.ItemPedidoDao;
import br.com.laboratorio.dao.PedidoDao;
import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.modelo.ItemPedido;
import br.com.laboratorio.modelo.Pedido;
import br.com.laboratorio.modelo.Produto;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.junit.Ignore;
import org.junit.Test;
import org.omnifaces.util.Faces;

/**
 *
 * @author Tardeli
 */
public class ItemPedidoTeste {
    @Test
    @Ignore
    public void cadastrar(){
        ItemPedido itemPedido = new ItemPedido();
        ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
        
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.buscarObjeto(1L);
        
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = pedidoDao.buscarObjeto(1L);
        
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
               
        itemPedidoDao.salvarOuAtualizarObjeto(itemPedido);            
    }
    
    @Test
    //@Ignore
    public void imprimir() throws JRException{
         //String jrxml = "itensPedido.jrxml";
         String src = Faces.getRealPath("/relatorio/itenspedido.jasper");
         //String jasper = JasperCompileManager.compileReportToFile(src);

         System.out.println(src);            
    }
}
