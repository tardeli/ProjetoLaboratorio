package br.com.laboratorio.controle;

import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.modelo.Produto;
import br.com.laboratorio.enumeradores.Tamanho;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Tardeli
 */
@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class ProdutoBean implements Serializable {

    private Produto produto = new Produto();
    @SuppressWarnings("unused")
    private List<Produto> listaObjetos;
    private ProdutoDao produtoDao = new ProdutoDao();

    @SuppressWarnings("unused")
    private Tamanho[] tamanho;

    public Tamanho[] getTamanho() {
        return Tamanho.values();
    }

    public void setTamanho(Tamanho[] tamanho) {
        this.tamanho = tamanho;
    }

    public ProdutoBean() {
        listaObjetos = new ArrayList<>();
        getListaObjetos();
    }

    public void novo() {
        this.produto = new Produto();
    }

    public void upload(FileUploadEvent event) {
        try {
            UploadedFile arquivoUpload = event.getFile();
            Path arquivoTemporario = Files.createTempFile(null, null);
            Files.copy(arquivoUpload.getInputstream(), arquivoTemporario, StandardCopyOption.REPLACE_EXISTING);
            produto.setCaminho(arquivoTemporario.toString());
            Messages.addGlobalInfo("Imagem selecionada com sucesso!");
        } catch (IOException ex) {
            Messages.addGlobalInfo("Ocorreu um erro ao tentar efetuar Upload" + ex);
        }
    }

    public void salvar() {

        if (this.produto.getCodigo() == null) {
            try {
                /*if (this.produto.getCaminho() == null) {
                    Messages.addGlobalInfo("Adicione uma Imagem!");
                    return;
                }*/
                Produto produtoRetorno = produtoDao.salvar(this.produto);
                //Path origem = Paths.get(produto.getCaminho());
                //Path destino = Paths.get("C:/imagens/" + produtoRetorno.getCodigo() + ".png");
                //Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                getListaObjetos();
                Messages.addGlobalInfo("Salvo com sucesso!");
                this.produto = new Produto();
            } catch (Exception ex) {
                Messages.addGlobalError("Erro ao salvar" + ex);
                ex.printStackTrace();
            }
        } else {
            try {
                produtoDao.salvar(this.produto);

                //Path origem = Paths.get(produto.getCaminho());
                //Path destino = Paths.get("C:/imagens/" + produto.getCodigo() + ".png");
                //Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                getListaObjetos();
                Messages.addGlobalInfo("Atualizado com sucesso!");
                this.produto = new Produto();
            } catch (Exception ex) {
                Messages.addGlobalError("Erro ao atualizar" + ex);
                ex.printStackTrace();
            }
        }
    }

    public void excluir(Produto c) {
        try {
            this.produto = c;
            Messages.addGlobalInfo("Excluido com Sucesso!");
            Path arquivo = Paths.get("C:/imagens/" + produto.getCodigo() + ".png");

            Files.deleteIfExists(arquivo);

            produtoDao.deletarObjeto(produto);
            getListaObjetos();
            this.produto = new Produto();
        } catch (Exception ex) {
            Messages.addGlobalError("Erro ao excluir" + ex);
            ex.printStackTrace();
        }
    }

    public void carregarDadosEditar(Produto c) {
        this.produto = c;
        produto.setCaminho("C:/imagens/" + produto.getCodigo() + ".png");
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoDao getClienteDao() {
        return produtoDao;
    }

    public void setClienteDao(ProdutoDao produtoDao) {
        this.produtoDao = produtoDao;
    }

    public List<Produto> getListaObjetos() {
        return listaObjetos = produtoDao.listarObjetos();
    }

    public void setListaObjetos(List<Produto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }
}
