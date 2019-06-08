/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.laboratorio.dao.EntregaDao;
import br.com.laboratorio.dao.EntregadorDao;
import br.com.laboratorio.dao.PedidoDao;
import br.com.laboratorio.modelo.Entrega;
import br.com.laboratorio.modelo.Entregador;
import br.com.laboratorio.modelo.Pedido;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class EntregaTeste {
    @Test
    public void cadastrar(){
        Entrega entrega = new Entrega();
        EntregaDao entregaDao = new EntregaDao();
        
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = pedidoDao.buscarObjeto(4L);
        
        EntregadorDao entregadorDao = new EntregadorDao();
        Entregador entregador = entregadorDao.buscarObjeto(1L);
        
        entrega.setPedido(pedido);
        entrega.setEntregador(entregador);
        entrega.setTaxa(4.50);
        entrega.getHora();
        entregaDao.salvarOuAtualizarObjeto(entrega);
        
    }
}
