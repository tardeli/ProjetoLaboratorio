/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.laboratorio.enumeradores;

/**
 *
 * @author Tardeli
 */
public enum Tamanho {
    VINTE_L("20 - L"),
    QUINHENTOS_ML("500 - ML"),
    DEZ_l("10 - L"),
    UM_L("1 - L"); 
    
    private final String nome;

    private Tamanho(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
     
}
