
package br.com.laboratorio.modelo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *
 * @author Tardeli
 */
@Entity
@Table(name = "Entregador")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Entregador extends PessoaFisica{
    
    private String rg;
    @NotNull(message = "O Campo CPF é obrigatório")
    @ManyToOne
    @JoinColumn(nullable = false)
    private Empresa empresa;

    public Entregador() {
        this.empresa = new Empresa();
    }
            
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
    
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
        texto+= "Ponto de Referencia: "+this.getPontoReferencia()+"\n";;
        texto+= "CodigoEmpresa: "+this.getEmpresa().getCodigo()+"\n";
        texto+= "CodigoNome: "+this.getEmpresa().getNome()+"\n";
        texto+= "CodigoCnpj: "+this.getEmpresa().getCnpj()+"\n";
        return texto;
    }
    
    
    
     
}
