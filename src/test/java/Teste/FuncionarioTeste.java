/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teste;

import br.com.laboratorio.dao.FuncionarioDao;
import br.com.laboratorio.modelo.Funcionario;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Tardeli
 */
public class FuncionarioTeste {
    @Test
    @Ignore
    public void buscarFuncionario() {
        FuncionarioDao c = new FuncionarioDao();
        Long codigo = 1L;
        Funcionario objeto = c.buscarObjeto(codigo);
        System.out.println("-------------------------------------");
        System.out.println(objeto.toString());
        System.out.println("-------------------------------------");

    }
    
    @Test
    @Ignore
    public void cadastrar(){
        Funcionario objeto = new Funcionario();
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        objeto.setNome("Altobeli da Rocha");
        objeto.setDataNascimento(new Date());
        objeto.setCpf("111.111.111-14");
        objeto.setCargo("Gerente");
        objeto.setEmail("altobeli@gmail.com");
        objeto.setTelefone("49-1233-1212");
        objeto.setLogradouro("Rua a");
        objeto.setBairro("Passos dos Fortes");
        objeto.setPontoReferencia("123e");
        objeto.setUsuario("rocha");
        objeto.setSenha("123");
        funcionarioDao.salvarOuAtualizarObjeto(objeto);    
   }
    
    @Test
    public void autenticarLogin(){
        FuncionarioDao funcionarioDao = new FuncionarioDao();
        //boolean resultado = funcionarioDao.autenticarLogin("rocha", "");
        //System.out.println(resultado);
    }
    
}
