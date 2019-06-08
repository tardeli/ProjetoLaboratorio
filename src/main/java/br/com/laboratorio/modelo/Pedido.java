package br.com.laboratorio.modelo;

import br.com.laboratorio.enumeradores.Status;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Pedido")
@XStreamAlias(value = "Pedido")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XStreamAlias(value = "Codigo")
    @XStreamAsAttribute
    private Long codigo;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Temporal(TemporalType.DATE)
    private Date data;

    private Status status;
    private Double total;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pedido")
    @XStreamImplicit(itemFieldName = "ItemPedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
        Date date = new Date();
        this.data = date;
    }

    public Pedido(Cliente cliente, Date data, Status status, Double total, List<ItemPedido> itens) {
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.total = total;
        this.itens = itens;
    }

    public String gerarArquivo_XML(Pedido pedido) {
        String caminho = "C:\\Users\\Da Rocha\\OneDrive\\Projeto_DiskAgua\\xml\\" + pedido.codigo + "_" + pedido.cliente.getNome() + ".xml";
        String xml = "";

        XStream xs = new XStream(new DomDriver());
        xs.autodetectAnnotations(true);

        xs.addDefaultImplementation(java.sql.Date.class, Date.class);

        xml = xs.toXML(pedido);

        File arquivo = new File(caminho);
        try {
            PrintWriter printWriter = new PrintWriter(arquivo);
            printWriter.write(xml);
            printWriter.flush();
            printWriter.close();
            Messages.addGlobalInfo("Gerado Xml com sucesso!");
            Messages.addGlobalInfo("Destino: " + caminho);
            return caminho;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void enviarArquivo_XML_Email(Pedido pedido) {
        String caminho = gerarArquivo_XML(pedido);
        enviarEmail(caminho, pedido);
    }

    public void enviarEmail(String arquivo, Pedido pedido) {
        try {
            Mensagem mensagem = new Mensagem();
            mensagem.setDestino(pedido.cliente.getEmail());
            String dataConvertida_BR = converteDataFormato_BR(pedido.data.toString());
            mensagem.setTitulo("Arquivo xml do pedido codigo: " + pedido.codigo + ", de " + dataConvertida_BR);
            mensagem.setMensagem("Segue arquivo xml");

            EmailUtils.conectaEmail();
            EmailUtils.enviaEmail(mensagem, arquivo);
            
            Messages.addGlobalInfo("Email enviado com sucesso para: "+pedido.getCliente().getEmail());

        } catch (Exception e) {
            Messages.addGlobalError("Email não foi enviado: Pode ser Problema Firewall or Antivirus!");
            e.printStackTrace();
        }
    }

    public String converteDataFormato_BR(String dataConverter) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date data = formato.parse(dataConverter);
            formato.applyPattern("dd/MM/yyyy");
            String dataFormatada = formato.format(data);
            return dataFormatada;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Data inválida!";
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        if (cliente == null) {
            return cliente = new Cliente();
        }
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ItemPedido> getItens() {
        if (itens == null) {
            return itens = new ArrayList<>();
        }
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    public String itens() {
        String resultado = "";
        for (ItemPedido itemPedido : itens) {
            resultado += "\n {Código: " + itemPedido.getProduto().getCodigo();
            resultado += " Nome: " + itemPedido.getProduto().getNome();
            resultado += " Descrição: " + itemPedido.getQuantidade() + "}";
        }

        return resultado;
    }

    public String visualizar() {
        return "Pedido{" + "codigo=" + codigo + ", cliente=" + cliente + ", data=" + data + ", status=" + status + ", total=" + total + ", itens=" + itens() + '}';
    }

}
