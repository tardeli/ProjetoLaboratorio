package br.com.laboratorio.util;

import br.com.laboratorio.controle.FuncionarioBean;
import br.com.laboratorio.modelo.Funcionario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DaRocha
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Captura o ManagedBean chamado “funcionario”
        FuncionarioBean funcionarioLogado = (FuncionarioBean) ((HttpServletRequest) request)
                .getSession().getAttribute("funcionarioBean");

        //Verifica se nosso ManagedBean ainda não 
        //foi instanciado ou caso a
        //variável loggedIn seja false, assim saberemos que  
        // o usuário não está logado
        if (funcionarioLogado == null || !funcionarioLogado.isLoggedIn()) {
            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
            //Redirecionamos o usuário imediatamente 
            //para a página de login.xhtml
            ((HttpServletResponse) response).sendRedirect(contextPath + "/login.xhtml");
        } else {
            //Caso ele esteja logado, apenas deixamos 
            //que o fluxo continue
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
