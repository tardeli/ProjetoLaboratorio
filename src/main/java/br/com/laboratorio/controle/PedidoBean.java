package br.com.laboratorio.controle;

import br.com.laboratorio.dao.ClienteDao;
import br.com.laboratorio.dao.FuncionarioDao;
import br.com.laboratorio.dao.PedidoDao;
import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.modelo.Cliente;
import br.com.laboratorio.modelo.Email;
import br.com.laboratorio.modelo.Funcionario;
import br.com.laboratorio.modelo.ItemPedido;
import br.com.laboratorio.modelo.Pedido;
import static br.com.laboratorio.modelo.Pessoa_.email;
import br.com.laboratorio.modelo.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class PedidoBean implements Serializable {
    private Pedido pedido;
    private List<Pedido> pedidos = new ArrayList<>();
    private PedidoDao pedidoDao = new PedidoDao();

    private ItemPedido itemPedido;
   
    private List<Cliente> clientes = new ArrayList<>();
    private ClienteDao clienteDao = new ClienteDao();

    private Produto produto;
    private List<Produto> produtos = new ArrayList<>();
    private ProdutoDao produtoDao = new ProdutoDao();

    public PedidoBean() {
      this.pedidos = pedidoDao.listaPedido();
    }

    @PostConstruct
    public void init() {
    
    }
        
    public void atualizar(){
        ///Messages.addGlobalInfo("Oi");
        this.pedidos = pedidoDao.listaPedido();
        //this.pedido.getItens();
        // "pedidolista.xhtml";
    }
    
    public void limpar(){
          pedido = new Pedido();
          produto = new Produto();
          itemPedido = new ItemPedido();
          pedidos = new ArrayList<>();
                      
    }
    
    public void salvar(){
        pedidoDao.salvar(pedido);
        Messages.addGlobalInfo("Salva com sucesso!");
        pedido = new Pedido();
        this.pedidos = pedidoDao.listaPedido();    
    }
    
    public void excluir(Pedido pedido){
        this.pedido = pedido;
        pedidoDao.deletarObjeto(pedido); 
        this.limpar();
    }
    
    public void novo(){
        this.limpar();
        
        //atualizar();
    }
    
    
    public void iniciarQuantidade(){
        Messages.addGlobalInfo(produto.getNome());
        itemPedido.setQuantidade(1);
    }
    
    public void adicionarProduto(){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(this.produto);
        itemPedido.setPedido(pedido);
        itemPedido.setQuantidade(this.itemPedido.getQuantidade());
        itemPedido.setTotal(itemPedido.getQuantidade()*itemPedido.getProduto().getPrecoCusto());
        int aux=0;
        for(ItemPedido itemAtual: this.pedido.getItens()){
            if(itemAtual.getProduto().equals(this.produto)){
                itemPedido = itemAtual;
                aux++;
            }
        }
        
        if(aux>0){
            Messages.addGlobalInfo("Produto já cadastrado -->foi atualizado!!");
            itemPedido.setQuantidade(this.itemPedido.getQuantidade());
            itemPedido.setTotal(itemPedido.getQuantidade()*itemPedido.getProduto().getPrecoCusto());
        }else{
            pedido.getItens().add(itemPedido);
        }
        
        totalPedido();
        this.produto = new Produto();
        this.itemPedido = null;
        
    }
    
    public void totalPedido(){
        double total = 0;
        for(ItemPedido item: this.pedido.getItens()){
            total+=item.getTotal();
        }
        pedido.setTotal(total);
    }
    
    public void excluirProduto(ItemPedido itemPedido){
        Messages.addGlobalInfo("Produto: "+itemPedido.getProduto().getNome());
        pedido.getItens().remove(itemPedido);
        totalPedido();
    
    }
    
    public void carregarProdutoEdicao(ItemPedido itemPedido){
        this.itemPedido = itemPedido;
        this.produto = itemPedido.getProduto();
        
    }
    

    public void carregarPedidoEdicao(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.getItens();

    } 
    
    public Pedido getPedido() {
        if (pedido == null) {
            return pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;// = pedidoDao.listaPedido();
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public PedidoDao getPedidoDao() {
        return pedidoDao;
    }

    public void setPedidoDao(PedidoDao pedidoDao) {
        this.pedidoDao = pedidoDao;
    }

    public ItemPedido getItemPedido() {
        if (itemPedido == null) {
            return itemPedido = new ItemPedido();
        }
        return itemPedido;
    }

    public void setItemPedido(ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }
    
    public List<Cliente> getClientes() {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        return clientes = clienteDao.listarObjetos();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Produto> getProdutos() {
        if (produtos == null) {
            produtos = new ArrayList<>();
        }
        return produtos = produtoDao.listarObjetos();

    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public ProdutoDao getProdutoDao() {
        return produtoDao;
    }

    public void setProdutoDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public Produto getProduto() {
        if(produto ==null){
            produto = new Produto();
        }
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
