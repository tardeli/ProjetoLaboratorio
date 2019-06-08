/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.laboratorio.controle;

import br.com.laboratorio.dao.ProdutoDao;
import br.com.laboratorio.modelo.Produto;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Da Rocha
 */
@Named(value = "produtoControle")
@SessionScoped
public class produtoControle implements Serializable {

    private List<Produto> produtos = new ArrayList<>();
    private Produto produto;
    private ProdutoDao produtodao = new ProdutoDao();
    
    @PostConstruct
    public void init(){
        produto = new Produto();
        this.produtos = produtodao.listarObjetos();
    }
    
    public void limpar(){
        produto = new Produto();
    }
    
    /**
     * Creates a new instance of produtoControle
     */
    public produtoControle() {
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
    
    
}
