package br.com.laboratorio.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Funcionario")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Funcionario extends PessoaFisica{
    
    private String cargo;
    private String usuario;
    private String senha;

    public Funcionario() {
    }
    
    public Funcionario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }    

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
//    @Override
//    public String toString(){
//        String texto = "";
//        texto+= "Codigo: "+this.getCodigo()+"\n";
//        texto+= "Nome: "+this.getNome()+"\n";
//        texto+= "DataNascimento: "+this.getDataNascimento()+"\n";
//        texto+= "Cpf: "+this.getCpf()+"\n";
//        texto+= "Telefone: "+this.getTelefone()+"\n";
//        texto+= "Email: "+this.getEmail()+"\n";
//        texto+= "Cargo: "+this.getCargo()+"\n";
//        texto+= "Usuario: "+this.getUsuario()+"\n";
//        texto+= "Senha: "+this.getSenha()+"\n";
//        texto+= "Logradouro: "+this.getLogradouro()+"\n";
//        texto+= "Bairro: "+this.getBairro()+"\n";
//        texto+= "Ponto de Referencia: "+this.getPontoReferencia();
//        return texto;
//    } 
    
}
