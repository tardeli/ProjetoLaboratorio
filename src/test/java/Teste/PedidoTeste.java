package Teste;

import br.com.laboratorio.dao.ClienteDao;
import br.com.laboratorio.dao.FuncionarioDao;
import br.com.laboratorio.dao.ItemPedidoDao;
import br.com.laboratorio.dao.PedidoDao;
import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.enumeradores.Status;
import br.com.laboratorio.modelo.Cliente;
import br.com.laboratorio.modelo.Funcionario;
import br.com.laboratorio.modelo.Pedido;
import br.com.laboratorio.modelo.ItemPedido;
import br.com.laboratorio.modelo.Produto;
import br.com.laboratorio.modelo.Email;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
public class PedidoTeste {

    Pedido pedido = new Pedido();
    PedidoDao pedidoDao = new PedidoDao();
    ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
    ProdutoDao produtoDao = new ProdutoDao();
    ClienteDao clienteDao = new ClienteDao();
    FuncionarioDao funcionarioDao = new FuncionarioDao();
    private Date dtData;

    @Test
    //@Ignore
    public void converterData() throws ParseException {
        Pedido pedido = pedidoDao.buscarPedido(20L);
    
        String dataEmUmFormato = pedido.getData().toString();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
        Date data = formato.parse(dataEmUmFormato); 
        formato.applyPattern("dd/MM/yyyy"); 
        String dataFormatada = formato.format(data);
        
        
//       SimpleDateFormat formatBra;   
//       formatBra = new SimpleDateFormat("dd/MM/yyyy");
//       try {
//          java.util.Date newData = formatBra.parse(dtData.toString());
//            String format = formatBra.format(newData);
//       } catch (ParseException Ex) {
//              Ex.getMessage();
//       }
       
       System.out.println("Data formatada: "+dataFormatada);
    }
    
    @Test
    @Ignore
    public void cadastrar() {
        Cliente cliente = clienteDao.buscarObjeto(2L);
        Funcionario funcionario = funcionarioDao.buscarObjeto(1L);
        Produto produto_1 = produtoDao.buscarObjeto(1L);
        Produto produto_2 = produtoDao.buscarObjeto(2L);
        Produto produto_3 = produtoDao.buscarObjeto(3L);

        Pedido pedido = new Pedido();//Salva Pedido  

        ItemPedido itemPedido_1 = new ItemPedido(pedido, produto_1, 1, 12.00);
        ItemPedido itemPedido_2 = new ItemPedido(pedido, produto_2, 2, 24.00);
        ItemPedido itemPedido_3 = new ItemPedido(pedido, produto_3, 3, 48.00);

        List<ItemPedido> itens = new ArrayList<>();

        itens.add(itemPedido_1);
        itens.add(itemPedido_2);
        itens.add(itemPedido_3);

        pedido.setCliente(cliente);
        //pedido.setFuncionario(funcionario);
        pedido.setData(new Date());
        pedido.setTotal(90.00);
        pedido.setStatus(Status.Pendente);
        pedido.setItens(itens);
        pedidoDao.salvar(pedido);//salvar

    }

    @Test
    @Ignore
    public void atualizar() {
        Pedido pedido = pedidoDao.buscarObjeto(1L);
        ItemPedidoDao ItemPedidoDao = new ItemPedidoDao();

//        Produto produto_1 = produtoDao.buscarObjeto(5L);
//        Produto produto_2 = produtoDao.buscarObjeto(6L);
        Produto produto_4 = produtoDao.buscarObjeto(4L);
        Produto produto_7 = produtoDao.buscarObjeto(7L);

        ItemPedido itemPedido_4 = new ItemPedido(pedido, produto_4, 1, 12.00);
        ItemPedido itemPedido_7 = new ItemPedido(pedido, produto_7, 2, 24.00);
        //ItemPedido itemPedido_3 = new ItemPedido(pedido, produto_3, 3, 36.00);

//        pedido.getItens().add(itemPedido_4);
//        pedido.getItens().add(itemPedido_7);
        //pedido.getItens().add(itemPedido_3);
        ItemPedido itemPedido_1 = ItemPedidoDao.buscarObjeto(7L);
        //ItemPedido itemPedido_2 = ItemPedidoDao.buscarObjeto(2L);
        //ItemPedido itemPedido_3 = ItemPedidoDao.buscarObjeto(3L);

        //System.out.println("------------>>>"+pedido.getItens().contains(itemPedido_1));
        //pedido.getItens().add(itemPedido);
        pedidoDao.salvar(pedido);
//        System.out.println(pedido.toString());
//        System.out.println("Quantidade: "+pedido.getItens().size());
    }

    @Test
    @Ignore
    public void excluir() {
        Pedido pedido = pedidoDao.buscarObjeto(27L);
        pedidoDao.deletarObjeto(pedido);
        System.out.println("Deletado com sucesso!");
    }

    @Test
    @Ignore
    public void buscarPedido() {
        Pedido pedido = pedidoDao.buscarObjeto(1L);
        System.out.println("-------------------------------------------");
        System.out.println(pedido.visualizar());
        System.out.println("-------------------------------------------");
    }

    @Test
    @Ignore
    public void listar() {
        List<Pedido> pedidos = pedidoDao.listaPedido();
        System.out.println("-------------------------------------------");
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.visualizar());
            System.out.println("-------------------------------------------");
        }
    }

    @Test
    @Ignore
    public void gerarXml() {
        Pedido pedido = pedidoDao.buscarPedido(20L);
        
        String xml = "";
        
        XStream xs = new XStream(new DomDriver());
        xs.autodetectAnnotations(true);

        xs.addDefaultImplementation(java.sql.Date.class, Date.class);
        
        xml = xs.toXML(pedido);
        
        //System.out.println(xml);
        
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto_DiskAgua\\xml\\"+pedido.getCodigo()+"_"+pedido.getCliente().getNome()+".xml";
        File arquivo = new File(caminho);
        try {
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.write(xml);
            printWriter.flush();
            printWriter.close(); 
            //System.out.println("caminho->>> "+caminho);
            //Email.sendEmail(caminho, pedido.getCliente().getEmail());
            //return caminho;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //return "";
    }

}
