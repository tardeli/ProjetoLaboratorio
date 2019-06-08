/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.laboratorio.dao;

import br.com.laboratorio.modelo.Entrega;
import br.com.laboratorio.modelo.ItemPedido;
import br.com.laboratorio.modelo.Pedido;
import br.com.laboratorio.util.HibernateUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Tardeli
 */
public class PedidoDao extends Generic_Dao<Pedido> {

//    EntityManagerFactory factory = Persistence.createEntityManagerFactory("diskAgua_mysql");
//    EntityManager manager = factory.createEntityManager();

    private Session sessao;
    private Transaction transacao;

    public List<Pedido> listaPedido(){
        try {
            sessao = (Session) HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = sessao.createCriteria(Pedido.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return criteria.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
    public Pedido buscarPedido(Long codigo){
        sessao = (Session) HibernateUtil.getSessionFactory().openSession();
        try {
            Criteria criteria = sessao.createCriteria(Pedido.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Restrictions.idEq(codigo));
            Pedido objeto = (Pedido) criteria.uniqueResult();
            return objeto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            sessao.close();
        } 
    }
    
   
//    public Pedido consultarPorId(Long id) {
//
//        Pedido pedido = manager.find(Pedido.class, 17L);
//        manager.close();
//        factory.close();
//
//        return pedido;
//    }
//    
//    public List<Pedido> getList()
//    {
//        Query query = manager.createQuery("SELECT p FROM Pedido p");
//        List<Pedido> pedidos = query.getResultList();
//        return pedidos;
//    }
    

}
