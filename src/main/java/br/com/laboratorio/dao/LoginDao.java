package br.com.laboratorio.dao;

import br.com.laboratorio.modelo.Login;
import br.com.laboratorio.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class LoginDao extends Generic_Dao<Login>{
    private Session sessao;   
    
    public Boolean pesquisarUsuario(String usuario){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Login.class);
            criteria.add(Restrictions.ilike("usuario", usuario));
            Login login = (Login) criteria.uniqueResult();
            if(login==null){
                return false;
            }        
        } catch (Exception e) {
            e.printStackTrace();     
        }finally{
            sessao.close();
        } 
        return true;
    }
    
    public Login validarLogin(Login obj){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Login.class);
            criteria.add(Restrictions.ilike("usuario", obj.getUsuario()));
            criteria.add(Restrictions.ilike("senha", obj.getSenha()));
            Login login = (Login) criteria.uniqueResult();
            if(login!=null){
                return login;
            }        
        } catch (Exception e) {
            e.printStackTrace();     
        }finally{
            sessao.close();
        } 
        return null;
    }
    
}
