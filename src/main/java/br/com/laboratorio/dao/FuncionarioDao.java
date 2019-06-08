package br.com.laboratorio.dao;

import br.com.laboratorio.modelo.Funcionario;
import br.com.laboratorio.util.HibernateUtil;
import java.io.Serializable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class FuncionarioDao extends Generic_Dao<Funcionario> implements Serializable {

    private Session sessao;

    public Funcionario autenticarLogin(Funcionario obj) {
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Funcionario.class);
            criteria.add(Restrictions.eq("usuario", obj.getUsuario()));
            criteria.add(Restrictions.eq("senha", obj.getSenha()));
            Funcionario funcionario = (Funcionario) criteria.uniqueResult();
            return funcionario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            sessao.close();
        }
    }
    
    public Boolean pesquisarUsuario(String usuario){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Funcionario.class);
            criteria.add(Restrictions.ilike("usuario", usuario));
            Funcionario funcionario = (Funcionario) criteria.uniqueResult();
            if(funcionario==null){
                return false;
            }        
        } catch (Exception e) {
            e.printStackTrace();     
        }finally{
            sessao.close();
        } 
        return true;
    }
    
    
}
