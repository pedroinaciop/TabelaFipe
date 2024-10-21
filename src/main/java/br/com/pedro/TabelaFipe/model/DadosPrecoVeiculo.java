package br.com.pedro.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosPrecoVeiculo(@JsonAlias("Valor") String valor,
                                @JsonAlias("Marca")  String marca,
                                @JsonAlias("Modelo") String modelo,
                                @JsonAlias("AnoModelo") Integer anoModelo,
                                @JsonAlias("Combustivel") String combustivel,
                                @JsonAlias("CodigoFipe") String codigoFipe) {

    @Override
    public String toString() {
        return  "Marca: " + marca + " - Modelo: " + modelo + " - Ano modelo: " + anoModelo +
                "\nValor: " + valor + " - Combustível: " + combustivel + " - Codigo FIPE: " + codigoFipe + "\n";
    }
}
