package br.com.laboratorio.controle;

import br.com.laboratorio.dao.LoginDao;
import br.com.laboratorio.modelo.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.omnifaces.util.Messages;

/**
 *
 * @author Da Rocha
 */
@ManagedBean
@SessionScoped
public class LoginBean {
    
    private LoginDao loginDao = new LoginDao();
    private Login usuarioLogado = new Login();
    private Login login = new Login();
    
    

    public LoginBean() {
        this.criarUsuario();
    }
    
    public void criarUsuario(){
        Login obj = new Login("admim","admim");
        if(!loginDao.pesquisarUsuario(obj.getUsuario())){
            loginDao.salvar(obj);
            System.out.println("Login padrão criado com sucesso");
        }
        System.out.println("Login padrão já foi criado");
    }

    public void limpar() {
        login = new Login();
    }
    
    public String validarLogin() {
        usuarioLogado = loginDao.validarLogin(login);
        if (usuarioLogado!=null) {
            try {
                limpar();
                Messages.addGlobalInfo("Login efetuado com sucesso");
                //Faces.redirect("./index.xhtml"); 
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                if (session != null) {
                    session.setAttribute("usuario", usuarioLogado);
                    System.out.println("Usuário Logado "+usuarioLogado.getUsuario());
                } 
                return "/pages/index?faces-redirect=true";
            } catch (Exception ex) {
                //Messages.addGlobalError(ex.getMessage());
                ex.printStackTrace();
            }
        } else {
            Messages.addGlobalInfo("Usuário ou senha incorretos!");
            limpar();
        }
        return "/pages/login"; 
    }
    
    public String logOff() {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        session.invalidate();
        return "/pages/login?faces-redirect=true";
    }
    
    public Login getLogin() {
        if(login==null){
            return login = new Login();
        }
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public LoginDao getLoginDao() {
        return loginDao;
    }

    public void setLoginDao(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public Login getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Login usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
    
    
}
