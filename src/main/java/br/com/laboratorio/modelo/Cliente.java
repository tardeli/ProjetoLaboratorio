package br.com.laboratorio.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Cliente")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cliente extends PessoaFisica{
    @Override
    public String toString(){
        String texto = "";
        texto+= "Codigo: "+this.getCodigo()+"\n";
        texto+= "Nome: "+this.getNome()+"\n";
        texto+= "Cpf: "+this.getCpf()+"\n";
        texto+= "Telefone: "+this.getTelefone()+"\n";
        texto+= "Email: "+this.getEmail()+"\n";
        texto+= "Logradouro: "+this.getLogradouro()+"\n";
        texto+= "Bairro: "+this.getBairro()+"\n";
        texto+= "Ponto de Referencia: "+this.getPontoReferencia();
        return texto;
    } 
}
