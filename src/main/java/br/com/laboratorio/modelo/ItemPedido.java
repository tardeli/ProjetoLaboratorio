package br.com.laboratorio.modelo;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Tardeli
 */
@Entity
//@XStreamAlias(value = "ItemPedido")
public class ItemPedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAsAttribute
    private long codigo;
    @ManyToOne
    @JoinColumn(nullable = true)
    @XStreamOmitField
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(nullable = true)
    private Produto produto;
    private int quantidade;
    private Double total;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, int quantidade, Double total) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = total;
    }
    
    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public Pedido getPedido() {
        if(pedido==null){
            return pedido = new Pedido();
        }
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        if (produto == null) {
            this.produto = new Produto();
        }
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String visualizar() {
        return "ItemPedido{" + "codigo=" + codigo + ", pedido=" + pedido + ", produto=" + produto + ", quantidade=" + quantidade + ", total=" + total + '}';
    }

}

  
