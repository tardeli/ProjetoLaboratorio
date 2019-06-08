package br.com.laboratorio.controle;

import br.com.laboratorio.dao.FuncionarioDao;
import br.com.laboratorio.modelo.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Messages;

/**
 *
 * @author Tardeli
 */
@ManagedBean
@SessionScoped
public class FuncionarioBean implements Serializable {

    private Funcionario funcionario;
    private Funcionario usuarioLogado;
    private FuncionarioDao funcionarioDao = new FuncionarioDao();
    private List<Funcionario> listaObjetos = new ArrayList<>();
    
    private boolean loggedIn;
        
    public FuncionarioBean() {
        this.criarUsuario();
        this.getListaObjetos();
    }
    
    public void criarUsuario(){
        Funcionario obj = new Funcionario("admim","admim");
        if(!funcionarioDao.pesquisarUsuario(obj.getUsuario())){
            funcionarioDao.salvar(obj);
            System.out.println("Funcionario padrão criado com sucesso");
        }
        System.out.println("Funcionario padrão já foi criado");
    }

    public void novo() {
        this.funcionario = new Funcionario();
    }
    
    public String validarLogin() {
       usuarioLogado = funcionarioDao.autenticarLogin(funcionario);
        if (usuarioLogado!=null) {
            try {
                novo();
                Messages.addGlobalInfo("Login efetuado com sucesso");
                //Faces.redirect("./index.xhtml"); 
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                if (session != null) {
                    session.setAttribute("usuario", usuarioLogado);
                    System.out.println("Usuário Logado "+usuarioLogado.getUsuario());
                    this.setLoggedIn(true);
                } 
                return "/pages/index?faces-redirect=true";
            } catch (Exception ex) {
                //Messages.addGlobalError(ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            Messages.addGlobalInfo("Usuário ou senha incorretos!");
            this.setLoggedIn(false);
            novo();
        }
        return "login";
    }
    
    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        this.setLoggedIn(false);
        return "/login.xhtml";
    }

    public String salvar() {
        if (this.funcionario.getCodigo() == null) {
            funcionarioDao.salvarOuAtualizarObjeto(this.funcionario);
            getListaObjetos();
            Messages.addGlobalInfo("Salvo com sucesso!");
            this.funcionario = new Funcionario();
            return "erro";
        } else {
            funcionarioDao.salvarOuAtualizarObjeto(this.funcionario);
            getListaObjetos();
            Messages.addGlobalInfo("Atualizado com Sucesso!");
            this.funcionario = new Funcionario();
            return "erro.xhtml";
        }
    }

    public void excluir(Funcionario c) {
        this.funcionario = c;
        Messages.addGlobalInfo("Excluido com Sucesso!");
        funcionarioDao.deletarObjeto(funcionario);
        getListaObjetos();
        this.funcionario = new Funcionario();
    }

    public void carregarDadosEditar(Funcionario c) {
        this.funcionario = c;
    }

    public Funcionario getFuncionario() {
        if(funcionario==null){
            return funcionario = new Funcionario();
        }
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
   
    public List<Funcionario> getListaObjetos() {
        return listaObjetos = funcionarioDao.listarObjetos();
    }

    public void setListaObjetos(List<Funcionario> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Funcionario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    
    
    
}
