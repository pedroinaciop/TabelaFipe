package br.com.pedro.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Dados(@JsonAlias("codigo") String codigo, @JsonAlias("nome") String nome) {


    @Override
    public String toString() {
        return "Cód: " + codigo + " Descrição: " + nome;
    }
}