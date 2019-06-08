/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.laboratorio.dao.EmpresaDao;
import br.com.laboratorio.modelo.Empresa;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class EmpresaTeste {
    @Test
    @Ignore
    public void cadastrar(){
        Empresa objeto = new Empresa();
        EmpresaDao empresaDao = new EmpresaDao();
        objeto.setNome("MotoEntrega Chapecó");
        objeto.setCnpj("02.212.121/0001-00");
        objeto.setEmail("cco@gmail.com");
        objeto.setTelefone("49-1233-000");
        objeto.setLogradouro("Rua Getulio Vargas");
        objeto.setBairro("Centro");
        objeto.setPontoReferencia("123s");
        empresaDao.salvarOuAtualizarObjeto(objeto);    
   }
    
   @Test
   public void listar(){
       EmpresaDao dao = new EmpresaDao();
        List<Empresa> objetos = dao.listarObjetos();
        System.out.println("-------------------------------------");
        for (Empresa objeto : objetos) {
            System.out.println(objeto.toString());
            System.out.println("-------------------------------------");
        }
   }
}
