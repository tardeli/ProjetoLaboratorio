/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.laboratorio.dao.EmpresaDao;
import br.com.laboratorio.dao.EntregadorDao;
import br.com.laboratorio.modelo.Empresa;
import br.com.laboratorio.modelo.Entregador;
import java.util.Date;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class EntregadorTeste {
    @Test
    @Ignore
    public void cadastrar(){
        Entregador objeto = new Entregador();
        Empresa empresa;
        EntregadorDao entregadorDao = new EntregadorDao();
        EmpresaDao empresaDao = new EmpresaDao();
        objeto.setNome("Maria Fernandes");
        objeto.setDataNascimento(new Date());
        objeto.setCpf("000.323.111-12");
        objeto.setEmail("maria@gmail.com");
        objeto.setTelefone("49-1212-1223");
        objeto.setLogradouro("Paulo VI");
        objeto.setBairro("Alvorada");
        objeto.setPontoReferencia("sn");
        
        empresa = empresaDao.buscarObjeto(1L);
        objeto.setEmpresa(empresa);
        
        entregadorDao.salvarOuAtualizarObjeto(objeto);
   }
    
    @Test
    @Ignore
    public void listar(){
        EntregadorDao entregadorDao = new EntregadorDao();
        List<Entregador> entregadores = entregadorDao.listarObjetos();
        System.out.println("-------------------------------------------");
        for (Entregador entregador : entregadores) {
            System.out.println(entregador.toString());
            System.out.println("-------------------------------------------");
        }
    }
    @Test
    @Ignore
    public void buscarEntregadores(){
        EntregadorDao entregadorDao = new EntregadorDao();
        Entregador entregador = entregadorDao.buscarObjeto(3L);
        System.out.println("-------------------------------------------");
        System.out.println(entregador.toString());
        System.out.println("-------------------------------------------");     
    }
    
    @Test
    @Ignore
    public void buscar(){
        EntregadorDao entregadorDao = new EntregadorDao();
        Entregador entregador = entregadorDao.buscarObjeto(3L);
        System.out.println(entregador.toString());    
    }
}
