/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.laboratorio.controle;

import br.com.laboratorio.dao.EmpresaDao;
import br.com.laboratorio.dao.EntregadorDao;
import br.com.laboratorio.modelo.Empresa;
import br.com.laboratorio.modelo.Entregador;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class EntregadorBean implements Serializable {

    private Entregador entregador = new Entregador();
    private List<Entregador> listaObjetos;
    private EntregadorDao entregadorDao = new EntregadorDao();
    private EmpresaDao empresaDao = new EmpresaDao();
    
    private List<Empresa> listaEmpresas;
    

    public EntregadorBean() {
        getListaObjetos();
        getListaEmpresas();
    }

    public void novo() {
        this.entregador = new Entregador();
    }

    public String salvar() {
        if (this.entregador.getCodigo() == null) {
            entregadorDao.salvarOuAtualizarObjeto(this.entregador);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.entregador = new Entregador();
            return "erro";
        } else {
            entregadorDao.salvarOuAtualizarObjeto(entregador);
            getListaObjetos();
            Messages.addGlobalInfo("Atualizado com Sucesso!");
            this.entregador = new Entregador();
            return "erro.xhtml";
        }
    }

    public void excluir(Entregador c) {
        this.entregador = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        entregadorDao.deletarObjeto(entregador);
        getListaObjetos();
        this.entregador = new Entregador();
    }

    public void carregarDadosEditar(Entregador c) {
        this.entregador = c;
    }

    public EntregadorDao getEntregadorDao() {
        return entregadorDao;
    }

    public void setEntregadorDao(EntregadorDao entregadorDao) {
        this.entregadorDao = entregadorDao;
    }

    public Entregador getEntregador() {
        return entregador;
    }

    public void setEntregador(Entregador entregador) {
        this.entregador = entregador;
    }

    public List<Entregador> getListaObjetos() {
        return listaObjetos = entregadorDao.listarObjetos();
    }

    public void setListaObjetos(List<Entregador> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas = empresaDao.listarObjetos();
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }
    
    
}
